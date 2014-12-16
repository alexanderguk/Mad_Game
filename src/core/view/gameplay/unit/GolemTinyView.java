package core.view.gameplay.unit;

import core.model.gameplay.gameobjects.Bot;
import core.model.gameplay.gameobjects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.resourcemanager.ResourceManager;
import core.view.gameplay.Camera;

public class GolemTinyView extends UnitView {

    public GolemTinyView(GameObject golem) throws SlickException {
        super(golem);
        animation = ResourceManager.getInstance().getAnimation("golem_tiny");
    }

    @Override
    public void render(Graphics g, Camera camera) throws SlickException {
        super.render(g, camera);
    }

}
