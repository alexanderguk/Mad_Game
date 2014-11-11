package core.model.gameplay;

public class EnemyManager extends MovingGameObjectManager {

    private Enemy enemy;

    public EnemyManager(final MovingGameObject enemy) {
        super(enemy);
        this.enemy = (Enemy) enemy;
    }

    public void followTarget(final double x, final double y) {
        enemy.setDirection(Math.atan2(y - movingGameObject.getY(), x - movingGameObject.getX()));
        enemy.setCurrentSpeed(movingGameObject.getMaximumSpeed());
    }

}