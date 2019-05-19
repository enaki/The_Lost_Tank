package Entity.Creature;

import AudioPlayer.AudioPlayer;
import Entity.Current_Direction;
import Game.*;
import Bullet.Bullet;
import Item.Item;
import Entity.Types.Entity_Types;

import java.awt.*;
import java.util.Random;

import static Entity.Types.Bullet_Appereance_Offset.Bullet_Appereance_Types.*;

public class Enemy extends Shooter{
    protected long lastMoveTimer, moveCoolDown = 2000, moveTimer = moveCoolDown;

    public Enemy(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type)
    {
        super(handler, x, y, tank_type);
        this.moveCoolDown = Entity_Types.MoveCoolDown(this.tank_type);
    }

    @Override
    public void die(){
        super.die();
        Random rand = new Random();
        int n = rand.nextInt(100);
        if (n < 25){
            handler.getWorld().getItemManager().addItem(Item.gold_chest.createNew((int)x, (int)y));
        }
        else if (n < 50){
            handler.getWorld().getItemManager().addItem(Item.health_chest.createNew((int)x, (int)y));
        }
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
//        AudioPlayer audioPlayer = new AudioPlayer("/sound/bullet/bullet_1.wav");
//        audioPlayer.play();
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
    public void addItem(Item item) {
        switch(item.getId()){
            case 0:
            case 1:
                this.health += 50;
                break;
            case 2:
                this.tank_type = Entity_Types.Tank_Type.tank_4;
                init();
                break;
            default:
                break;
        }
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
