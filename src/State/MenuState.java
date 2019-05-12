package State;

import Game.*;
import UI.ClickListener;
import UI.UIButton;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    public Rectangle playButton, helpButton, exitButton;

    public MenuState(Handler handler){

        super(handler);
        playButton = new Rectangle(handler.getGame().getWidth()/2 - 50, 150, 100, 50);
        helpButton = new Rectangle(handler.getGame().getWidth()/2 - 50, 250, 100, 50);
        exitButton = new Rectangle(handler.getGame().getWidth()/2 - 50, 350, 100, 50);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 50, 150, 100, 50, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().gameState);
        }, "Play"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 50, 250, 100, 50, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().gameState);
        }, "Help"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 50, 350, 100, 50, () -> {
            isUIManagerActive = false;
            System.exit(0);
        }, "Exit"));

    }



    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }

        //handler.getMouseManager().setUIManager(null);
//        State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
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
