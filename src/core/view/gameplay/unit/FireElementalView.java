package core.view.gameplay.unit;

import core.model.gameplay.gameobjects.GameObject;
import core.resourcemanager.ResourceManager;
import core.view.gameplay.Camera;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import java.io.IOException;

public class FireElementalView extends UnitView {

    private ParticleSystem ps;

    public FireElementalView(GameObject fireElemental) {
        super(fireElemental);
        animation = ResourceManager.getInstance().getAnimation("empty");
        try {
            ps = ParticleIO.loadConfiguredSystem("/res/particles/fire_elemental.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        ps.update(delta);
    }

    @Override
    public void render(Graphics g, Camera camera) {
        super.render(g, camera);
        rotate(g, camera, true);
        ps.render((float) (gameObject.getX() - camera.getX()), (float) (gameObject.getY() - camera.getY()));
        rotate(g, camera, false);
    }

}