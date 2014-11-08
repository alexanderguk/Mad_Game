package core.view.gameplay;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;

import core.model.gameplay.Hero;

/**
 * Provides functions for the definition of actors, as well as actor
 * operations, such as `receive`, `react`, `reply`, etc.
 */
public class HeroRenderer extends GameObjectRenderer {

    private int imageWidth, imageHeight;
    private final int animationSpeed = 100;
    private Animation animation;

    public HeroRenderer(Hero hero) throws SlickException {
        super(hero);

        imageWidth = 50;
        imageHeight = 50;

        SpriteSheet spriteSheet = new SpriteSheet("/res/Hero.png", imageWidth, imageHeight);
        animation = new Animation(spriteSheet, animationSpeed);
    }

    @Override
    public void render(Graphics g) {
        double direction = gameObject.getDirection();
        while (direction < 0)
            direction += 2 * Math.PI;
        while (direction > 2 * Math.PI)
            direction -= 2 * Math.PI;

        animation.draw(320 - imageWidth/2, 240 - imageHeight/2);

        // For debug
        g.drawString("(" + String.valueOf((int)gameObject.getX()) + ";" + String.valueOf((int)gameObject.getY())
                        + ") dir=" + String.valueOf((int)(gameObject.getDirection() / Math.PI * 180) % 360), 320, 240);
    }

    @Override
    public void render(Graphics g, final double viewX, final double viewY, final double viewDirection) {
        render(g);
    }

    public Hero getHero() {
        return (Hero) gameObject;
    }

}