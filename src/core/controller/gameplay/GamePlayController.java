package core.controller.gameplay;

import core.StartMenuState;
import core.model.startmenu.StartMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.model.gameplay.World;
import core.view.gameplay.GamePlayRenderer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Game play controller class, that uses game object's controllers to process external events (like user input).
 */
public class GamePlayController {

    private static GamePlayController instance;
    private World world;
    private GamePlayRenderer gamePlayRenderer;
    private HeroController heroController;

    private GamePlayController(final World world, final GamePlayRenderer gamePlayRenderer) throws SlickException {
        this.world = world;
        this.gamePlayRenderer = gamePlayRenderer;

        heroController = new HeroController(world.getHeroManager());
    }

    // Singleton pattern method
    public static GamePlayController getInstance(final World world, final GamePlayRenderer gamePlayRenderer) throws SlickException {
        if (instance == null) {
            instance = new GamePlayController(world, gamePlayRenderer);
        }
        return instance;
    }


    public void update(GameContainer gc, StateBasedGame stateBasedGame, final int delta) {
        /* Must enter to pause menu in future */
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            stateBasedGame.enterState(StartMenuState.STATE_ID);
        }

        heroController.update(gc, delta);
    }

}