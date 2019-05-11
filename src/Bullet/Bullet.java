package Bullet;

import Entity.Current_Direction;
import Game.Assets;
import Game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private float x;
    private float y;
    protected Handler handler;
    private int xMove, yMove;
    private final int speed = 5;

    BufferedImage image;
    private Current_Direction current_direction;

    public Bullet(Handler handler, Current_Direction current_direction, float x, float y ){
        this.x = x;
        this.y = y;
        xMove = 0;
        yMove = 0;
        this.handler = handler;
        switch (current_direction){
            case up :
                image = Assets.basic_bullet[0];
                yMove = -speed;
                break;
            case right:
                image = Assets.basic_bullet[1];
                xMove = speed;
                break;
            case down:
                image = Assets.basic_bullet[2];
                yMove = speed;
                break;
            case left:
                image = Assets.basic_bullet[3];
                xMove = -speed;
                break;

        }
    }

    public void tick(){
        x += xMove;
        y += yMove;
    }

    public void render(Graphics g){

        g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 32, 32, null);
        //waawg.drawImage(Assets.basic_bullet[0], (int) (x), (int) y, null);


    }
}
