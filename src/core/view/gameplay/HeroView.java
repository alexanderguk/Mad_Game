package core.view.gameplay;

import core.model.gameplay.GameObjectState;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;

import core.model.gameplay.Hero;

/**
 * Provides functions for the definition of actors, as well as actor
 * operations, such as `receive`, `react`, `reply`, etc.
 */
public class HeroView extends GameObjectView {

    private GameObjectState previousState;

    public HeroView(Hero hero) throws SlickException {
        super(hero);

        final int imageWidth = 50;
        final int imageHeight = 50;

        SpriteSheet spriteSheet = new SpriteSheet("/res/Hero.png", imageWidth, imageHeight);
        animation = new Animation(spriteSheet, 1);
    }

    @Override
    public void render(Graphics g, final double viewX, final double viewY, final float viewDegreeAngle,
                       final int viewWidth, final int viewHeight) {
        Hero hero = (Hero)gameObject;
        if (hero.getCurrentState() != previousState) {
            if (hero.getCurrentState() == GameObjectState.WALK) {
                animation.start();
                animation.setSpeed((float) hero.getCurrentSpeed() / 6F);
            }
            else if (hero.getCurrentState() == GameObjectState.STAND) {
                animation.stop();
                animation.setCurrentFrame(4);
            } else if (hero.getCurrentState() == GameObjectState.RUN) {
                animation.start();
                animation.setSpeed((float)hero.getCurrentSpeed() / 6F);
            }
        }
        previousState = hero.getCurrentState();

        rotate(g, viewX, viewY, viewDegreeAngle, viewWidth, viewHeight, true);
        draw(viewX, viewY);
        rotate(g, viewX, viewY, viewDegreeAngle, viewWidth, viewHeight, false);

        // For debug
        g.drawString("(" + String.valueOf((int) gameObject.getX()) + ";" + String.valueOf((int) gameObject.getY())
                + ") dir=" + String.valueOf((int) (gameObject.getDirection() / Math.PI * 180) % 360),
                (float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY));
    }

    public Hero getHero() {
        return (Hero) gameObject;
    }

}