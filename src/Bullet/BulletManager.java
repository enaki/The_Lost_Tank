package Bullet;

import Game.Handler;

import java.awt.*;
import java.util.LinkedList;

public class BulletManager {
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    Handler handler;
    Bullet TempBullet;

    public BulletManager(Handler handler){
        this.handler = handler;
    }

    public void tick(){
        for (int i = 0; i < b.size(); i++){
            b.get(i).tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < b.size(); i++){
            b.get(i).render(g);
        }
    }

    public void addBullet(Bullet block){
        b.add(block);
    }

    public void removeBullet(Bullet block){
        b.remove(block);
    }
}
