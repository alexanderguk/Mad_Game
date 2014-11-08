package core.view.gameplay;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.model.gameplay.GameObject;
import core.model.gameplay.Hero;
import core.model.gameplay.Wall;

/*
* Renders game play game state
* */
public class GamePlayRenderer {

    private static GamePlayRenderer instance;

    private ArrayList<GameObjectRenderer> gameObjectRenderers;
    private HeroRenderer heroRenderer;
    private View view;

    private GamePlayRenderer(ArrayList<GameObject> gameObjects, Hero hero) throws SlickException {
        gameObjectRenderers = new ArrayList<GameObjectRenderer>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Wall) {
                gameObjectRenderers.add(new WallRenderer((Wall) gameObject));
            }
        }

        heroRenderer = new HeroRenderer(hero);

        view = new View();
    }

    // Singleton pattern method
    public static GamePlayRenderer getInstance(ArrayList<GameObject> gameObjects, Hero hero) throws SlickException {
        if (instance == null) {
            instance = new GamePlayRenderer(gameObjects, hero);
        }
        return instance;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        view.setDirection(heroRenderer.getHero().getDirection());
        view.setX(heroRenderer.getHero().getX() - gc.getWidth() / 2);
        view.setY(heroRenderer.getHero().getY() - gc.getHeight() / 2);

        heroRenderer.render(g);

        for (GameObjectRenderer gameObjectRenderer : gameObjectRenderers) {
            gameObjectRenderer.render(g, view.getX(), view.getY(), view.getDirection());
        }
    }


}