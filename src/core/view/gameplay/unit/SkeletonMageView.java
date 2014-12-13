package core.view.gameplay.unit;

import org.newdawn.slick.SlickException;

import core.model.gameplay.gameobjects.GameObject;
import core.resourcemanager.ResourceManager;

public class SkeletonMageView extends UnitView {

    public SkeletonMageView(GameObject skeletonMage) throws SlickException {
        super(skeletonMage);
        animation = ResourceManager.getInstance().getAnimation("skeleton_mage");
    }

}