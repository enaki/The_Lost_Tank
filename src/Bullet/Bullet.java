package Bullet;

import Game.Assets;
import Game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private float x;
    private float y;
    protected Handler handler;

    BufferedImage image;

    public Bullet(float x, float y, Handler handler){
        this.x = x;
        this.y = y;
        this.handler = handler;
    }

    public void tick(){
        y -= 4;
    }

    public void render(Graphics g){
        g.drawImage(Assets.basic_bullet[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 32, 32, null);
        //waawg.drawImage(Assets.basic_bullet[0], (int) (x), (int) y, null);


    }
}
