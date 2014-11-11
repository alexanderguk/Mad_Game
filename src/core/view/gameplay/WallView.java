package core.view.gameplay;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.model.gameplay.GameObject;

public class WallView extends GameObjectView {

    Image wallImage;

    public WallView(GameObject wall, Animation animation) {
        super(wall, animation);
        this.animation = animation;
    }

    @Override
    public void render(Graphics g, final double viewX, final double viewY, final float viewDegreeAngle,
                       final int viewWidth, final int viewHeight) {


        rotate(g, viewX, viewY, viewDegreeAngle, viewWidth, viewHeight, true);
        draw(viewX, viewY);
        // ----- For debug and FUN -----
        g.rotate((float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY),
                viewDegreeAngle);
        g.drawString("(" + String.valueOf(gameObject.getX()) + ";" + String.valueOf(gameObject.getY()) + ")",
                (float) (gameObject.getX() - viewX), (float) (gameObject.getY() - viewY));
        g.rotate((float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY),
                - viewDegreeAngle);
        // ----- END -----
        rotate(g, viewX, viewY, viewDegreeAngle, viewWidth, viewHeight, false);

    }

}