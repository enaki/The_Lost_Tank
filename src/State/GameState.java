package State;

import Game.*;
import World.World;
import java.awt.*;
import static State.Levels.*;

public class GameState extends State {
    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, level_1);
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().esc){
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
