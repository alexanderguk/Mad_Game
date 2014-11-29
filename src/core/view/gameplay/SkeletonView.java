package core.view.gameplay;

import core.model.gameplay.Bandit;
import core.model.gameplay.Hero;
import core.model.gameplay.Skeleton;
import org.newdawn.slick.*;

import core.ResourceManager;
import core.model.gameplay.GameObject;

public class SkeletonView extends GameObjectView {

    public SkeletonView(GameObject enemy, ResourceManager resourceManager) throws SlickException {
        super(enemy, resourceManager);
        animation = resourceManager.getAnimation("skeleton");
    }

    @Override
    public void render(Graphics g, double viewX, double viewY, float viewDegreeAngle, double viewCenterX, double viewCenterY, Hero hero) {
        Skeleton skeleton = (Skeleton) gameObject;

        rotate(g, viewX, viewY, viewDegreeAngle, viewCenterX, viewCenterY, true);
        draw(viewX, viewY);
        // draw mask
        drawMask(g, viewX, viewY);

        // ----- For debug and FUN -----
        g.rotate((float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY),
                (float) (viewDegreeAngle - skeleton.getDirection() / Math.PI * 180));
        drawHealthbar(g, (int)(skeleton.getX() - viewX), (int)(skeleton.getY() - viewY) - 50, 60, 8, skeleton.getAttribute().getCurrentHP(),
                skeleton.getAttribute().getMaximumHP(), Color.red);
        drawHealthbar(g, (int) (skeleton.getX() - viewX), (int) (skeleton.getY() - viewY) - 38, 60, 8, skeleton.getAttribute().getCurrentMP(),
                skeleton.getAttribute().getMaximumMP(), Color.blue);
        /*g.drawString(String.valueOf((int) skeleton.getAttribute().getPAttack()) + "/" +
                        String.valueOf((int) skeleton.getAttribute().getMAttack()),
                (float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY - 80));
        g.drawString(String.valueOf((int) skeleton.getAttribute().getPArmor()) + "/" +
                        String.valueOf((int) skeleton.getAttribute().getMArmor()),
                (float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY - 60));
        g.drawString(String.valueOf((int) skeleton.getAttribute().getCurrentHP()) + "/" +
                        String.valueOf((int) skeleton.getAttribute().getMaximumHP()),
                (float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY - 40));
        g.drawString(String.valueOf((int) skeleton.getAttribute().getCurrentMP()) + "/" +
                        String.valueOf((int) skeleton.getAttribute().getMaximumMP()),
                (float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY - 20));
        g.drawString("(" + String.valueOf((int) gameObject.getX()) + ";" + String.valueOf((int) gameObject.getY()) + ")",
                (float) (gameObject.getX() - viewX), (float) (gameObject.getY() - viewY));*/
        g.rotate((float) (gameObject.getX() - viewX),
                (float) (gameObject.getY() - viewY),
                - (float) (viewDegreeAngle - skeleton.getDirection() / Math.PI * 180));
        // ----- END -----

        rotate(g, viewX, viewY, viewDegreeAngle, viewCenterX, viewCenterY, false);
    }

}