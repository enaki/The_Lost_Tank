package State;

import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

public class HelpState extends State {
    private UIManager uiManager;
    public Rectangle returnToMenuButton  ;

    public HelpState(Handler handler) {

        super(handler);
        returnToMenuButton = new Rectangle(handler.getGame().getWidth() / 2 - 50, 350, 100, 50);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 50, 150, 100, 50, () -> {
            //isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }, "Return To Menu"));
    }

    @Override
    public void tick() {
        if (isUIManagerActive) {
            uiManager.tick();
        }
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
