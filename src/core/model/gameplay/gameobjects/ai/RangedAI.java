package core.model.gameplay.gameobjects.ai;

import core.model.Timer;
import core.model.gameplay.World;
import core.model.gameplay.gameobjects.Bot;
import core.model.gameplay.items.ItemInstanceKind;
import core.model.gameplay.skills.BulletShot;
import core.model.gameplay.skills.SkillInstanceKind;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

public class RangedAI extends BotAI {

    public RangedAI() {
        this(null);
    }

    public RangedAI(Bot bot) {
        super(bot);
    }

    private enum RangedAIState implements BotAI.BotAIState {

        STAND, WALK, PURSUE, ATTACK

    }

    @Override
    protected void init() {
        final int standTime = 3000;
        final int pursueDistance = 400;
        final int attackDistance = 200;
        currentState = RangedAIState.STAND;
        stateMap.put(RangedAIState.STAND, new AIState() {
            private Timer timer;
            public void enter()           { timer = new Timer(standTime);                                                  }
            public void run(int delta)    { owner.stand();                                                                 }
            public void update(int delta) { if (timer.update(delta))                  currentState = RangedAIState.WALK;
                                            if (getDistanceToHero() < pursueDistance && seeTarget(World.getInstance().getHero())) currentState = RangedAIState.PURSUE; }
        });
        stateMap.put(RangedAIState.WALK, new AIState() {
            private Point target;
            public void enter()           { target = getRandomTarget();                                                     }
            public void run(int delta)    { followTarget(target);                                                           }
            public void update(int delta) { if (getDistanceToHero() < pursueDistance && seeTarget(World.getInstance().getHero())) currentState = RangedAIState.PURSUE;
                                            if (getDistanceToTarget(target) < 2)      currentState = RangedAIState.STAND;   }
        });
        stateMap.put(RangedAIState.PURSUE, new AIState() {
            private boolean isFollowing;
            public void enter()           { isFollowing = true;                                                                  }
            public void run(int delta)    { isFollowing = followHero();         }
            public void update(int delta) { if (getDistanceToHero() >= pursueDistance || !isFollowing) currentState = RangedAIState.STAND;
                                            if (getDistanceToHero() < attackDistance && seeTarget(World.getInstance().getHero())) currentState = RangedAIState.ATTACK; }
        });
        stateMap.put(RangedAIState.ATTACK, new AIState() {
            private boolean isAttacking;
            public void enter()           { isAttacking = true;                                                             }
            public void run(int delta)    { isAttacking = attackHeroWithRangedSkill();                                      }
            public void update(int delta) { if (getDistanceToHero() >= attackDistance || !isAttacking) currentState = RangedAIState.PURSUE; }
        });
    }

    private boolean attackHeroWithRangedSkill() {
        owner.stand();
        owner.setDirection(getPredictedDirection(0));
        if (seeTarget(World.getInstance().getHero())) {
            if (owner.getSkillList().get(0).getKind() == SkillInstanceKind.BOW_SHOT) {
                owner.getInventory().useItem(owner.getInventory().addItem(ItemInstanceKind.STRONG_BOW));
                owner.startCastSkill(SkillInstanceKind.BOW_SHOT);
            } else {
                owner.getInventory().useItem(owner.getInventory().addItem(ItemInstanceKind.STAFF));
                owner.startCastSkill(SkillInstanceKind.FIREBALL);
            }
            return true;
        }
        return false;
    }

    private double getPredictedDirection(int skillIndex) {
        Vector2f v = new Vector2f((float) World.getInstance().getHero().getX() - (float)owner.getX(),
                (float)World.getInstance().getHero().getY() - (float)owner.getY());
        double angleToTarget = v.getTheta() / 180 * Math.PI;
        double targetSpeed = World.getInstance().getHero().getAttribute().getCurrentSpeed();
        double targetDirection = World.getInstance().getHero().getDirection() + World.getInstance().getHero().getRelativeDirection();
        double bulletSpeed = ((BulletShot) owner.getSkillList().get(skillIndex)).getBulletSpeed();

        if (targetSpeed > 0) {
            double alphaAngle = (Math.PI - targetDirection) + angleToTarget;
            double neededOffsetAngle = Math.asin(Math.sin(alphaAngle) * targetSpeed / bulletSpeed);
            return angleToTarget + neededOffsetAngle;
        } else {
            return angleToTarget;
        }
    }
    
}