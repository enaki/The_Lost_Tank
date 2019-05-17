package State;

import Entity.Creature.Player;
import Game.Assets;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

public class WinState extends State{
    private UIManager uiManager;

    public WinState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 140, handler.getGame().getHeight()-100, 280, 50, () -> {
            isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }, "Return To Menu"));
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
        if (handler.getKeyManager().esc){
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);

        if (isUIManagerActive){
            uiManager.render(g);
        }
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 35);
        g.setColor(Color.blue);
        g.setFont(fnt1);
        g.drawString("You WIN!", handler.getWidth()/2-100, handler.getHeight()/2 - 50);

        g.setColor(Color.white);
        fnt1 = new Font("Comic Sans MS", Font.BOLD, 25);
        g.setFont(fnt1);
        int score = Player.GetScore();
        g.drawString("Score : " + score, handler.getWidth()/2-80, handler.getHeight()/2);
        g.drawImage(Assets.pikachu_happy, handler.getWidth()/2-70, handler.getHeight()/2+ 30 , 120, 145, null);


    }
    public UIManager getUiManager() {
        return uiManager;
    }
}
