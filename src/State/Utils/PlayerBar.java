package State.Utils;

import Game.Assets;

import java.awt.*;

public class PlayerBar {
    protected int player_health, enemy_counter, number_of_coins;

    public void tick(int player_health, int enemy_counter, int number_of_coins) {
        this.player_health = player_health;
        this.enemy_counter = enemy_counter;
        this.number_of_coins = number_of_coins;

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
