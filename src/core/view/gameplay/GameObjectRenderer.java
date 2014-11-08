package core.view.gameplay;

import org.newdawn.slick.Graphics;

import core.model.gameplay.GameObject;

public abstract class GameObjectRenderer {

    protected GameObject gameObject;

    public GameObjectRenderer(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract void render(Graphics g);

    public abstract void render(Graphics g, final double viewX, final double viewY, final double viewDirection);

}