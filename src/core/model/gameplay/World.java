package core.model.gameplay;

import java.util.ArrayList;

/**
 * Main model class, that imitates game world.
 * Contains all game objects.
 */
public class World {

    private static World instance;

    private ArrayList<GameObject> gameObjects;
    private Hero hero;
    private HeroManager heroManager;

    private World() {
        gameObjects = new ArrayList<GameObject>();

        hero = new Hero(200, 100, 0, 0.12f);

        gameObjects.add(new Wall(100, 100, 0, 0));
        gameObjects.add(new Wall(300, 300, 0, 0));

        heroManager = new HeroManager(hero);
    }

    public void update() {
        // TODO
    }

    // Singleton pattern method
    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public HeroManager getHeroManager() {
        return heroManager;
    }

    public Hero getHero() {
        return hero;
    }


}