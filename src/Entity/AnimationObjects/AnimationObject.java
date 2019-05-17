package Entity.AnimationObjects;

import Game.Animation;
import Game.Assets;
import Game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationObject {
    protected float x, y;
    protected Handler handler;
    protected boolean active = true;
    protected Animation explosion;
    protected final int const_speed = 5;
    protected int lifeTime;
    protected BufferedImage current_image;

    public AnimationObject(Handler handler, float x, float y){
        this.x = x;
        this.y = y;
        this.handler = handler;
        this.lifeTime = Assets.bullet_explosion_animation.length;
        explosion = new Animation(const_speed, Assets.bullet_explosion_animation);
    }

    public void tick(){
        current_image = explosion.getCurrentFrame();
        explosion.tick();
        lifeTime--;
        if (lifeTime == 0){
            active = false;
        }
    }

    public void render(Graphics g){
        g.drawImage(current_image, (int) (x - handler.getGameCamera().getxOffset()+10-16), (int) (y - handler.getGameCamera().getyOffset()+10-16), 32, 32, null);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
