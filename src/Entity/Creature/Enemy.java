package Entity.Creature;

import Entity.Current_Direction;
import Game.*;
import Bullet.Bullet;
import Tile.Tile;
import Entity.Types.Entity_Types;

import java.awt.*;
import java.util.Random;

import static Entity.Dimensions.Tank_Type_Bounds_Dimensions.*;
import static Entity.Types.Bullet_Appereance_Offset.Bullet_Appereance_Types.*;
import static Entity.Types.Entity_Types.TankSpeed;

public class Enemy extends Shooter{
    protected long lastMoveTimer, moveCoolDown = 2000, moveTimer = moveCoolDown;

    public Enemy(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type)
    {
        super(handler, x, y);
        this.tank_type = tank_type;
        this.speed = TankSpeed(tank_type);
        this.images = Entity_Types.TankImages(this.tank_type);
        this.attackCooldown = Entity_Types.TankAttackTime(this.tank_type);
        this.speed = TankSpeed(this.tank_type);
        this.health = Entity_Types.TankHealth(this.tank_type);
        this.bullet_type = Entity_Types.GetBulletType(this.tank_type);
        this.moveCoolDown = Entity_Types.MoveCoolDown(this.tank_type);
        bounds.x = GetBoundsX(this.tank_type);
        bounds.y = GetBoundsY(this.tank_type);
        bounds.width = GetBoundsWidth(this.tank_type);
        bounds.height = GetBoundsHeight(this.tank_type);

        current_direction = Current_Direction.down;
    }

    @Override
    public void die(){
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    private void getInput(){

        Random rand = new Random();
        int n = rand.nextInt(2);
        if (n == 0){
            checkAttacks();
        }
        moveTimer += System.currentTimeMillis() - lastMoveTimer;
        lastMoveTimer = System.currentTimeMillis();
        if (moveTimer < moveCoolDown){
            return;
        }
        xMove = 0;
        yMove = 0;
        n = rand.nextInt(110);
        if (n < 25) {
            yMove = -speed;
            current_direction = Current_Direction.up;
            set_Bounds_Dimension(true);
        }else
        if (n < 50) {
            yMove = speed;
            current_direction = Current_Direction.down;
            set_Bounds_Dimension(true);
        } else

        if (n < 75) {
            xMove = -speed;
            current_direction = Current_Direction.left;
            set_Bounds_Dimension(false);
        } else

        if (n < 100) {
            xMove = speed;
            current_direction = Current_Direction.right;
            set_Bounds_Dimension(false);
        }
        else if (n < 110){
            xMove = 0;
            yMove = 0;
        }
        moveTimer = 0;
    }

    protected void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }
        if (tank_type == Entity_Types.Tank_Type.tank_4){
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, quarter));
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, threequarter));
        }
        else {
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, this, bullet_type, middle));
        }

        attackTimer = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//        g.setColor(Color.YELLOW);
//        g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

}
