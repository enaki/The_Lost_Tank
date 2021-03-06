package State.Utils;

import Game.Assets;
import Game.Handler;
import State.State;
import UI.UIManager;

import java.awt.*;

public class PlayerBar extends State {
    protected int player_health, enemy_counter, number_of_coins;

    public PlayerBar(Handler handler){
        super(handler);
    }

//    public void tick(int player_health, int enemy_counter, int number_of_coins) {
//        this.player_health = handler.getWorld().getEntityManager().getPlayer().getHealth();
//        this.enemy_counter = handler.getWorld().getEntityManager().getCounter();
//        this.number_of_coins = handler.getWorld().getEntityManager().getPlayer().getNumberOfCoins();
//    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    @Override
    public void tick() {
        this.player_health = handler.getWorld().getEntityManager().getPlayer().getHealth();
        this.enemy_counter = handler.getWorld().getEntityManager().getCounter();
        this.number_of_coins = handler.getWorld().getEntityManager().getPlayer().getNumberOfCoins();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.heart, 10, 10, 32, 32, null);
        g.drawImage(Assets.enemy_counter, 90, 10, 32, 32, null);
        g.drawImage(Assets.number_of_coins, 170, 10, 32, 32, null);

        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        g.setColor(Color.white);
        g.drawString(Integer.toString(player_health), 40, 33);
        g.drawString(Integer.toString(enemy_counter), 120, 33);
        g.drawString(Integer.toString(number_of_coins), 200, 33);
    }
}
