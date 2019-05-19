package State;

import Game.*;
import UI.ClickListener;
import UI.UIButton;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

public class MenuState extends State {
    protected UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        //New game Button
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 230, 180, 50, () -> {
            isUIManagerActive = false;
            hasGameStarted = true;
            handler.getGame().gameState.startNewGame();
            State.setState(handler.getGame().gameState);
        }, "New Game"));
        //Help Button
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 310, 180, 50, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().helpState);
        }, "Help"));
        //Exit Button
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 390, 180, 50, () -> {
            isUIManagerActive = false;
            System.exit(0);
        }, "Exit"));
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
//        State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        if (isUIManagerActive) {
            uiManager.render(g);
        }
//        Font fnt1 = new Font("arial", Font.BOLD, 30);
//        g.setFont(fnt1);
//        g.drawString("Play", playButton.x + 20, playButton.y + 30);
//        g.drawString("Help", helpButton.x + 20, helpButton.y + 30);
//        g.drawString("Exit", exitButton.x + 20, exitButton.y + 30);
//
//        g2d.draw(playButton);
//        g2d.draw(helpButton);
//        g2d.draw(exitButton);

    }
}
