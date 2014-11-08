package core.model.gameplay;

import core.DirectionKey;

/**
 * Contains methods to define hero behavior
 * */
public class HeroManager {

    private Hero hero;

    public HeroManager(final Hero hero) {
        this.hero = hero;
    }

    public void move(final DirectionKey keyIndex, final int delta) {
        double length, lengthDirX, lengthDirY;
        length = hero.getSpeed() * delta;

        switch (keyIndex) {
            case RIGHT:
                lengthDirX = Math.cos(hero.getDirection() + Math.PI / 2) * length;
                lengthDirY = Math.sin(hero.getDirection() + Math.PI / 2) * length;
                hero.setX(hero.getX() + lengthDirX);
                hero.setY(hero.getY() + lengthDirY);
                break;
            case BOTTOM:
                lengthDirX = Math.cos(hero.getDirection()) * length;
                lengthDirY = Math.sin(hero.getDirection()) * length;
                hero.setX(hero.getX() - lengthDirX);
                hero.setY(hero.getY() - lengthDirY);
                break;
            case LEFT:
                lengthDirX = Math.cos(hero.getDirection() - Math.PI / 2) * length;
                lengthDirY = Math.sin(hero.getDirection() - Math.PI / 2) * length;
                hero.setX(hero.getX() + lengthDirX);
                hero.setY(hero.getY() + lengthDirY);
                break;
            case TOP:
                lengthDirX = Math.cos(hero.getDirection()) * length;
                lengthDirY = Math.sin(hero.getDirection()) * length;
                hero.setX(hero.getX() + lengthDirX);
                hero.setY(hero.getY() + lengthDirY);
                break;
        }
    }

    public void rotate(final int targetX, final int targetY) {
        double angle = Math.atan2((targetY - hero.getY()), (targetX - hero.getX()));
        hero.setDirection(angle);
    }

    public void rotate(final double angleOffset) {
        hero.setDirection(hero.getDirection() + angleOffset);
    }

}