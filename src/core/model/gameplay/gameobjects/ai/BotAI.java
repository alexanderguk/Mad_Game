package core.model.gameplay.gameobjects.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.model.gameplay.skills.BulletShot;
import core.model.gameplay.skills.Skill;
import org.newdawn.slick.geom.Point;

import core.MathAdv;
import core.model.gameplay.CollisionManager;
import core.model.gameplay.gameobjects.*;
import org.newdawn.slick.geom.Vector2f;

public abstract class BotAI {

    protected Bot owner;
    protected Map<BotAIState, AIState> stateMap;
    private boolean isInited;
    protected BotAIState currentState;
    private BotAIState previousState;
    private AStar aStar;
    private double lastTargetX;
    private double lastTargetY;
    private boolean updatePathIfSeeTarget;

    public BotAI() {
        this.stateMap = new HashMap<>();
        this.isInited = false;
        this.currentState = null;
        this.previousState = null;
        this.aStar = new AStar();
        this.updatePathIfSeeTarget = true;
    }

    public interface BotAIState {

    }

    public void run(Bot bot, int delta) {
        if (!isInited) {
            this.owner = bot;
            isInited = true;
            init();
        }
        if (currentState != previousState) {
            stateMap.get(currentState).enter();
            previousState = currentState;
        }
        stateMap.get(currentState).run(delta);
        stateMap.get(currentState).update(delta);
    }

    protected abstract void init();

    // AI methods

    protected double getDistanceToHero() {
        return MathAdv.getDistance(Hero.getInstance().getX(), Hero.getInstance().getY(),
                owner.getX(), owner.getY());
    }

    protected Point getRandomTarget() {
        Point target = new Point(0, 0);
        int attemptNumber = 0;
        while (attemptNumber < 5) {
            double randomDistance = 100 + Math.random() * 10;
            double randomAngle = Math.random() * 2 * Math.PI;
            double tmpX = owner.getX() + MathAdv.lengthDirX(randomAngle, randomDistance);
            double tmpY = owner.getY() + MathAdv.lengthDirY(randomAngle, randomDistance);
            if (CollisionManager.getInstance().isPlaceFreeAdv(owner, tmpX, tmpY)) {
                target.setX((float) tmpX);
                target.setY((float) tmpY);
                return target;
            }
            attemptNumber++;
        }
        target.setX((float)owner.getX());
        target.setY((float)owner.getY());
        return target;
    }

    protected double getPredictedDirection(Skill skill) {
        Vector2f v = new Vector2f((float) Hero.getInstance().getX() - (float)owner.getX(),
                (float)Hero.getInstance().getY() - (float)owner.getY());
        double angleToTarget = v.getTheta() / 180 * Math.PI;
        double targetSpeed = Hero.getInstance().getAttribute().getCurrentSpeed();
        double targetDirection = Hero.getInstance().getDirection() + Hero.getInstance().getRelativeDirection();
        double bulletSpeed = ((BulletShot) skill).getBulletSpeed();

        if (targetSpeed > 0) {
            double alphaAngle = (Math.PI - targetDirection) + angleToTarget;
            double neededOffsetAngle = Math.asin(Math.sin(alphaAngle) * targetSpeed / bulletSpeed);
            return angleToTarget + neededOffsetAngle;
        } else {
            return angleToTarget;
        }
    }

    protected boolean followTarget(double x, double y) {
        return followTarget(new Point((float) x, (float) y));
    }

    protected boolean followTarget(Point target) {
        double direction = Math.atan2(target.getY() - owner.getY(), target.getX() - owner.getX());
        owner.setDirection(direction);
        owner.move();
        if (MathAdv.getDistance(owner.getX(), owner.getY(), target.getX(), target.getY()) < 3 &&
                CollisionManager.getInstance().isPlaceFreeAdv(owner, target.getX(), target.getY())) {
            owner.setX(target.getX());
            owner.setY(target.getY());
            return false;
        }
        return true;
    }

    protected boolean followHero() {
        Unit hero = Hero.getInstance();
        if (seeTarget(hero)) {
            lastTargetX = hero.getX();
            lastTargetY = hero.getY();
            if (isDirectPathFree(hero)) {
                updatePathIfSeeTarget = true;
                return followTarget(lastTargetX, lastTargetY);
            } else {
                if (updatePathIfSeeTarget) {
                    aStar.buildPath(hero, owner, lastTargetX, lastTargetY);
                    updatePathIfSeeTarget = false;
                }
                Point currentPoint = aStar.getFirstReachablePoint(owner);
                aStar.removeFrom(currentPoint, false);
                boolean isFollowing = followTarget(currentPoint);
                if (!isFollowing) {
                    aStar.removeFrom(currentPoint, true);
                }
                if (aStar.getPath().size() > 1) {
                    return true;
                } else {
                    if (!isFollowing) {
                        updatePathIfSeeTarget = true;
                    }
                    return isFollowing;
                }
            }
        } else {
            updatePathIfSeeTarget = true;
            if (aStar.getPath().isEmpty()) {
                return false;
            }
            aStar.removeFrom(aStar.getFirstReachablePoint(owner), false);
            boolean isFollowing = followTarget(aStar.getFirstReachablePoint(owner));
            if (!isFollowing) {
                aStar.removeFrom(aStar.getFirstReachablePoint(owner), true);
            }
            if (aStar.getPath().size() > 1) {
                return true;
            } else {
                return isFollowing;
            }
        }
    }

    protected boolean seeTarget(GameObjectSolid target) {
        return seeTarget(target, owner.getX(), owner.getY());
    }

    protected boolean seeTarget(GameObjectSolid target, double x, double y) {
        int step = 1;
        double direction = MathAdv.getAngle(x, y, target.getX(), target.getY());
        Bullet dummy = new Bullet(owner, x, y, direction, 0, 0, 0, 1, GameObjInstanceKind.ARROW);
        for (int i = 0; i < MathAdv.getDistance(x, y, target.getX(), target.getY()) / step; ++i) {
            GameObjectSolid collisionObject = CollisionManager.getInstance().collidesWith(dummy,
                    x + MathAdv.lengthDirX(direction, step * i),
                    y + MathAdv.lengthDirY(direction, step * i));
            if (collisionObject != owner && collisionObject != null && collisionObject != target && !(collisionObject instanceof Bullet)) {
                return false;
            }
        }
        return true;
    }

    protected double getDistanceToTarget(Point target) {
        return MathAdv.getDistance(target.getX(), target.getY(), owner.getX(), owner.getY());
    }

    protected boolean isDirectPathFree(Unit target) {
        int step = 5;
        double currentDirection = Math.atan2(target.getY() - owner.getY(), target.getX() - owner.getX());
        for (int j = 0; j < MathAdv.getDistance(owner.getX(), owner.getY(), target.getX(), target.getY()) / step; ++j) {
            GameObjectSolid collisionObject = CollisionManager.getInstance().collidesWith(owner,
                    owner.getX() + MathAdv.lengthDirX(currentDirection, step * j),
                    owner.getY() + MathAdv.lengthDirY(currentDirection, step * j));
            if (collisionObject != owner && collisionObject != null && !(collisionObject instanceof Bullet) && collisionObject != target) {
                return false;
            }
        }
        return true;
    }

    public List<Cell> getPath() {
        return aStar.getPath();
    }

    // Getters and setters

    public BotAIState getCurrentState() {
        return currentState;
    }

    public void setOwner(Bot owner) {
        this.owner = owner;
    }

    public AStar getAStar() {
        return aStar;
    }
}