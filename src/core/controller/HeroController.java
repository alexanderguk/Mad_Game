package core.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import core.model.HeroManager;

public class HeroController {

    private HeroManager heroManager;

    public HeroController(HeroManager heroManager) {
        this.heroManager = heroManager;
    }

    public void update(GameContainer gc, final int delta) {
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            heroManager.move(0, delta);
        }
        if (gc.getInput().isKeyDown(Input.KEY_W)) {
            heroManager.move(1, delta);
        }
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            heroManager.move(2, delta);
        }
        if (gc.getInput().isKeyDown(Input.KEY_S)) {
            heroManager.move(3, delta);
        }

        heroManager.rotate(gc.getInput().getMouseX(), gc.getInput().getMouseY());

    }

}