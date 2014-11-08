package core.controller.startmenu;

import core.GamePlayState;
import core.model.startmenu.StartMenu;
import core.model.startmenu.StartMenuManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

/*
* Provides functions to handle user menu navigate events
* */
public class StartMenuController {

    private static StartMenuController instance;

    public static StartMenuController getInstance() {
        if (instance == null) {
            instance = new StartMenuController();
        }
        return instance;
    }

    StartMenuManager startMenuManager = new StartMenuManager();

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        Input input = gameContainer.getInput();
        if (input.isKeyPressed(Input.KEY_S)) {
            startMenuManager.stepDown();
        }

        if (input.isKeyPressed(Input.KEY_W)) {
            startMenuManager.stepUp();
        }

        if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (StartMenu.getCurrentChoice()) {
                case StartMenu.START:
                    stateBasedGame.enterState(GamePlayState.STATE_ID);
                    break;
                case StartMenu.LOAD:
                    break;
                case StartMenu.HELP:
                    break;
                case StartMenu.EXIT:
                    gameContainer.exit();
                    break;
            }
        }
    }

}
