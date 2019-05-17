package State;

import Game.Assets;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

import static State.Utils.Levels.GetLevelName;
import static State.Utils.Levels.previousLevel;

public class IntermediateState extends State {
    private UIManager uiManager;

    public IntermediateState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 140, handler.getGame().getHeight() - 100, 210, 50, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().gameState);
        }, "Next Level"));
    }

    @Override
    public void tick() {
        if (isUIManagerActive) {
            uiManager.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        if (isUIManagerActive) {
            uiManager.render(g);
        }
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 25);
        g.setColor(Color.cyan);
        g.setFont(fnt1);
        g.drawString(GetLevelName(previousLevel(GameState.current_level)) + " passed", handler.getWidth() / 2 - 120, handler.getHeight() / 2 - 50);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

}
