package core.view.gameplay;

import core.model.gameplay.gameobjects.Bot;
import core.model.gameplay.gameobjects.GameObjectSolid;
import core.model.gameplay.gameobjects.Hero;
import org.newdawn.slick.*;

import core.resourcemanager.ResourceManager;

public class SkeletonMageView extends GameObjectView {

    public SkeletonMageView(GameObjectSolid enemy, ResourceManager resourceManager) throws SlickException {
        super(enemy, resourceManager);
        animation = resourceManager.getAnimation("skeleton_mage");
    }

    @Override
    public void render(Graphics g, double viewX, double viewY, float viewDegreeAngle, double viewCenterX, double viewCenterY, Hero hero) {
        Bot skeletonMage = (Bot) gameObjectSolid;

        rotate(g, viewX, viewY, viewDegreeAngle, viewCenterX, viewCenterY, true);
        draw(viewX, viewY);
        // draw mask
        drawMask(g, viewX, viewY);

        // ----- For debug and FUN -----
        g.rotate((float) (gameObjectSolid.getX() - viewX),
                (float) (gameObjectSolid.getY() - viewY),
                (float) (viewDegreeAngle - skeletonMage.getDirection() / Math.PI * 180));
        drawHealthbar(g, (int)(skeletonMage.getX() - viewX), (int)(skeletonMage.getY() - viewY) - 50, 60, 8,
                skeletonMage.getAttribute().getHP().getCurrent(),
                skeletonMage.getAttribute().getHP().getMaximum(),
                Color.red);
        drawHealthbar(g, (int) (skeletonMage.getX() - viewX), (int) (skeletonMage.getY() - viewY) - 38, 60, 8,
                skeletonMage.getAttribute().getMP().getCurrent(),
                skeletonMage.getAttribute().getMP().getMaximum(),
                Color.blue);
        g.rotate((float) (gameObjectSolid.getX() - viewX),
                (float) (gameObjectSolid.getY() - viewY),
                - (float) (viewDegreeAngle - skeletonMage.getDirection() / Math.PI * 180));
        // ----- END -----

        rotate(g, viewX, viewY, viewDegreeAngle, viewCenterX, viewCenterY, false);
    }

}