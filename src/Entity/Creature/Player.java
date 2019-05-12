package Entity.Creature;
import Entity.Current_Direction;
import Entity.Types.Bullet_Types;
import Entity.Types.Entity_Types;
import Game.*;
import Bullet.Bullet;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Entity.Types.Bullet_Appereance_Offset.Bullet_Appereance_Types.*;

import static Entity.Dimensions.Tank_Type_Bounds_Dimensions.*;
import static Entity.Types.Entity_Types.*;

public class Player extends Shooter{
//    private Animation animationDown, animationLeft, animationRight, animationUp;
    public Player(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type)
    {
        super(handler, x, y);
        EnemyBullet = false;

        this.speed = TankSpeed(tank_type);
        this.tank_type = tank_type;
        this.bullet_type = Entity_Types.GetBulletType(this.tank_type);
        images = Entity_Types.TankImages(this.tank_type);
        bounds.x = GetBoundsX(this.tank_type);
        bounds.y = GetBoundsY(this.tank_type);
        bounds.width = GetBoundsWidth(this.tank_type);
        bounds.height = GetBoundsHeight(this.tank_type);
        this.health = TankHealth(this.tank_type);
        this.attackCooldown = TankAttackTime(tank_type);
//        animationDown = new Animation(const_speed, Assets.player_down);
//        animationLeft = new Animation(const_speed, Assets.player_left);
//        animationRight = new Animation(const_speed, Assets.player_right);
//        animationUp = new Animation(const_speed, Assets.player_up);

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

        if (handler.getKeyManager().space) {
            if (tank_type == Entity_Types.Tank_Type.player_level_2) {
                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, Bullet_Types.Bullet_Type.bullet_3, middle));
                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, one_seventeenth));
                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, six_seventeenth));
            } else {
                handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, middle));
            }
        }
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
