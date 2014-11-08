package core.model.startmenu;

import core.GamePlayState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class StartMenuManager {

    public void stepUp() {
        if (StartMenu.getCurrentChoice() == 0) {
            StartMenu.setCurrentChoice(StartMenu.NUMBER_OF_CHOICES - 1);
        }
        else {
            StartMenu.setCurrentChoice(StartMenu.getCurrentChoice() - 1);
        }
    }

    public void stepDown() {
        if (StartMenu.getCurrentChoice() == StartMenu.NUMBER_OF_CHOICES - 1) {
            StartMenu.setCurrentChoice(0);
        }
        else {
            StartMenu.setCurrentChoice(StartMenu.getCurrentChoice() + 1);
        }
    }

}
