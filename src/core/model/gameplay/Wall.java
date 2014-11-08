package core.model.gameplay;

/**
 * Contains fields to define the wall state
 * */
public class Wall extends GameObject {

    public Wall() {
        super();
    }

    public Wall(double x, double y, double direction, double speed) {
        super(x, y, direction, speed);
    }

}