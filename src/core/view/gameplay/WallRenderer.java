package core.view.gameplay;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.model.gameplay.Wall;

public class WallRenderer extends GameObjectRenderer {

    Image wallImage;

    public WallRenderer(Wall wall) {
        super(wall);

        try {
            wallImage = new Image("/res/Wall.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(wallImage, (float)gameObject.getX() - wallImage.getWidth() / 2,
                (float)gameObject.getY() - wallImage.getHeight() / 2);
    }

    @Override
    public void render(Graphics g, final double viewX, final double viewY, final double viewDirection) {
        float rotateAngle = (float) (viewDirection / Math.PI * 180 + 90);

        float xx = 320;
        float yy = 240;

        g.rotate(xx, yy, -rotateAngle);
        g.drawImage(wallImage, (float) (gameObject.getX() - wallImage.getWidth() / 2 - viewX),
                (float) (gameObject.getY() - wallImage.getHeight() / 2 - viewY));
        g.drawString("(" + String.valueOf(gameObject.getX()) + ";" + String.valueOf(gameObject.getY()) + ")",
                (float) (gameObject.getX() - viewX), (float) (gameObject.getY() - viewY));
        g.rotate(xx, yy, rotateAngle);
    }

}