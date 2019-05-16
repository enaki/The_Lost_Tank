package State;

import Game.*;
import UI.UIManager;

import java.awt.*;

public abstract class State {

    private static State currentState = null;
    protected Handler handler;
    boolean isUIManagerActive = false;


    public State(Handler handler){
        this.handler = handler;
    }

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    public void setUIManagerActive(boolean UIManagerActive) {
        isUIManagerActive = UIManagerActive;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
