package State;

import Game.*;
import State.Utils.PlayerBar;
import World.World;
import java.awt.*;
import static State.Utils.Levels.*;

public class GameState extends State {
    private World world;
    private PlayerBar playerBar;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, level_2);
        handler.setWorld(world);
        this.playerBar = new PlayerBar();
    }

    @Override
    public void tick() {
        if (handler.getWorld().getEntityManager().getCounter() == 0){
            world.setWorld(level_2);
        }

        if (handler.getKeyManager().esc){
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }
        world.tick();
        playerBar.tick(handler.getWorld().getEntityManager().getPlayer().getHealth(), handler.getWorld().getEntityManager().getCounter(),handler.getWorld().getEntityManager().getPlayer().getNumberOfCoins() );
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        playerBar.render(g);
        //g.drawImage(Assets.heart, 10,  10, 32, 32, null);

    }
}
