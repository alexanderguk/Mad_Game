package core;

import core.controller.startmenu.StartMenuController;
import core.view.startmenu.StartMenuRenderer;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;

/*
* Execute start menu
* */
public class StartMenuState extends BasicGameState {

    private static StartMenuState instance;

    public static final int STATE_ID = 0;

    private StartMenuController startMenuController;
    private StartMenuRenderer startMenuRenderer;

    private StartMenuState() {
        startMenuController = StartMenuController.getInstance();
        startMenuRenderer = StartMenuRenderer.getInstance();
    }

    public static StartMenuState getInstance() {
        if (instance == null) {
            instance = new StartMenuState();
        }
        return instance;
    }

    @Override
    public int getID() {
        return STATE_ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        startMenuRenderer.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        startMenuController.update(gameContainer, stateBasedGame);
    }

}

