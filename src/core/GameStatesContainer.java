package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameStatesContainer extends StateBasedGame {

    private static GameStatesContainer instance;

    private GameStatesContainer(String title) {
        super(title);
    }

    public static GameStatesContainer getInstance(String title) {
        if (instance == null) {
            instance = new GameStatesContainer(title);
        }
        return instance;
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(StartMenuState.getInstance());
        addState(GamePlayState.getInstance());
    }

}
