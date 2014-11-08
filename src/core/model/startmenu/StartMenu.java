package core.model.startmenu;

import org.newdawn.slick.state.StateBasedGame;

/**
 * Contains state of the start menu
 */
public abstract class StartMenu {

    public static final int NUMBER_OF_CHOICES = 4;
    public static final int START = 0;
    public static final int LOAD = 1;
    public static final int HELP = 2;
    public static final int EXIT = 3;

    private static int currentChoice;


    public static int getCurrentChoice() {
        return currentChoice;
    }

    public static void setCurrentChoice(int currentChoice) {
        StartMenu.currentChoice = currentChoice;
    }

}