package Entity.Creature;
import Entity.Current_Direction;
import Game.Assets;
import Game.*;
import Bullet.Bullet;
import java.awt.*;
import java.awt.image.BufferedImage;
import static Entity.Dimensions.Bounds_Dimensions.*;

public class Player extends Shooter{
    //private Animation animationDown, animationLeft, animationRight, animationUp;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y);
        images = Assets.player_level_1;
        bounds.x = Player_level_1_bounds_x;
        bounds.y = Player_level_1_bounds_y;
        bounds.width = Player_level_1_bounds_width;
        bounds.height = Player_level_1_bounds_height;
//        animationDown = new Animation(const_speed, Assets.player_down);
//        animationLeft = new Animation(const_speed, Assets.player_left);
//        animationRight = new Animation(const_speed, Assets.player_right);
//        animationUp = new Animation(const_speed, Assets.player_up);

//        animationUp = new Animation(const_speed, Assets.tank_1);
//        animationRight = new Animation(const_speed, Assets.robot[1]);
//        animationDown = new Animation(const_speed, Assets.robot[2]);
//        animationLeft = new Animation(const_speed, Assets.robot[3]);
        current_direction = Current_Direction.down;
    }

    @Override
    public void die() {
        System.out.println("You Lose");
    }

    @Override
    public void tick() {
//       animationDown.tick();
//       animationLeft.tick();
//       animationRight.tick();
//       animationUp.tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        checkAttacks();

    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
            current_direction = Current_Direction.up;
            set_Bounds_Dimension(true);
            return;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            current_direction = Current_Direction.down;
            set_Bounds_Dimension(true);
            return;
        }

        if (handler.getKeyManager().left) {
            xMove = -speed;
            current_direction = Current_Direction.left;
            set_Bounds_Dimension(false);
            return;
        }

        if (handler.getKeyManager().right) {
            xMove = speed;
            current_direction = Current_Direction.right;
            set_Bounds_Dimension(false);
            return;
        }

    }

    protected void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown){
            return;
        }

        if (handler.getKeyManager().space)
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, current_direction, x , y, Assets.bullet_2, 1, true));
        else{
            return;
        }

        attackTimer = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        g.setColor(Color.RED);
        g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
        //g.drawOval((int)x,(int) y, 10, 10);
    }

}
