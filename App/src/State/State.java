package State;

import AudioPlayer.AudioPlayer;
import Game.Handler;
import UI.UIManager;

import java.awt.*;

public abstract class State {
    private static State currentState = null;
    protected AudioPlayer audioPlayer;
    protected Handler handler;
    boolean isUIManagerActive = false;
    public static boolean hasGameStarted = false;

    public State(Handler handler){
        this.handler = handler;
    }


    public static void setState(State state){
        if (!(state instanceof GameState)){
            state.setUIManagerActive(true);
        }
        state.SetUIManagerForMouseManager(state.getUiManager());
        currentState = state;
    }

    protected abstract UIManager getUiManager();

    public void SetUIManagerForMouseManager(UIManager uiManager){
        handler.getMouseManager().setUIManager(uiManager);
    }

    public static State getState(){
        return currentState;
    }

    public void setUIManagerActive(boolean UIManagerActive) {
        isUIManagerActive = UIManagerActive;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void drawBackground(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Font fnt0 = new Font("arial", Font.BOLD, 40);
        g.setFont(fnt0);
        g.setColor(Color.GREEN);
        String title = "The Lost Tank";
        int width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, handler.getWidth()/2-width/2, 80);
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
