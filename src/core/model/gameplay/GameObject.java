package core.model.gameplay;

/**
 * Contains common fields to define a game object
 * */
public abstract class GameObject {

    private double x;
    private double y;
    private double direction;
    private double speed;

    public GameObject() {

    }

    public GameObject(final double x, final double y, final double direction, final double speed) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

}