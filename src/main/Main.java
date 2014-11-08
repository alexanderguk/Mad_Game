package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import core.GameStatesContainer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import core.GamePlayState;

public class Main {

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(GameStatesContainer.getInstance("Mad Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex) {
            Logger.getLogger(GamePlayState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}