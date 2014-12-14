package core.controller.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.GamePlayState;
import core.MenuStartState;
import core.model.gameplay.World;
import core.model.menu.MenuPause;

public class MenuPauseController {

    private static MenuPauseController instance;

    private MenuPause menuPause;

    private MenuPauseController() {
        menuPause = MenuPause.getInstance();
    }

    public static MenuPauseController getInstance() {
        if (instance == null) {
            instance = new MenuPauseController();
        }
        return instance;
    }

    public void update(GameContainer gc, StateBasedGame game) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_S)) {
            menuPause.stepDown();
        }

        if (input.isKeyPressed(Input.KEY_W)) {
            menuPause.stepUp();
        }

        if (input.isKeyPressed(Input.KEY_ENTER)) {
            if (menuPause.getCurrentChoice() == menuPause.getMenuId("Resume")) {
                World.getInstance(); // resuming existing world
                game.enterState(GamePlayState.getInstance().getID());
            }
            else if (menuPause.getCurrentChoice() == menuPause.getMenuId("Load")) {

            }
            else if (menuPause.getCurrentChoice() == menuPause.getMenuId("Help")) {

            }
            else if (menuPause.getCurrentChoice() == menuPause.getMenuId("Main menu")) {
                game.enterState(MenuStartState.getInstance().getID());
                World.deleteInstance();
            }
        }
    }


}
