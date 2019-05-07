package Entity.Creature;

import Game.Assets;
import Game.*;
import Bullet.Bullet;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{
    private final int const_speed = 100;
    private Animation animationDown, animationLeft, animationRight, animationUp;


    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        animationDown = new Animation(const_speed, Assets.player_down);
        animationLeft = new Animation(const_speed, Assets.player_left);
        animationRight = new Animation(const_speed, Assets.player_right);
        animationUp = new Animation(const_speed, Assets.player_up);

    }

    @Override
    public void die() {
        System.out.println("You Lose");
    }

    @Override
    public void tick() {
       animationDown.tick();
       animationLeft.tick();
       animationRight.tick();
       animationUp.tick();

       getInput();
       move();
       handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up)
            yMove = -speed;
        if (handler.getKeyManager().down)
            yMove = speed;
        if (handler.getKeyManager().left)
            xMove = -speed;
        if (handler.getKeyManager().right)
            xMove = speed;
        if (handler.getKeyManager().space)
            handler.getWorld().getBulletManager().addBullet(new Bullet(x,y, handler));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        g.setColor(Color.RED);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return animationLeft.getCurrentFrame();
        }
        else if(xMove > 0){
            return animationRight.getCurrentFrame();
        }
        else if (yMove < 0){
            return animationUp.getCurrentFrame();
        }
        else if (yMove > 0){
            return animationDown.getCurrentFrame();
        }
        else{
            return Assets.player;
        }
    }
}
