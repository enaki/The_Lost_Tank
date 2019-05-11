package Entity.Creature;
import Entity.Current_Direction;
import Game.Assets;
import Game.*;
import Bullet.Bullet;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{
    private final int const_speed = 5;
    private Animation animationDown, animationLeft, animationRight, animationUp;
    //Atack timer
    private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
    private Current_Direction current_direction;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

//        animationDown = new Animation(const_speed, Assets.player_down);
//        animationLeft = new Animation(const_speed, Assets.player_left);
//        animationRight = new Animation(const_speed, Assets.player_right);
//        animationUp = new Animation(const_speed, Assets.player_up);

        animationUp = new Animation(const_speed, Assets.robot[0]);
        animationRight = new Animation(const_speed, Assets.robot[1]);
        animationDown = new Animation(const_speed, Assets.robot[2]);
        animationLeft = new Animation(const_speed, Assets.robot[3]);


        current_direction = Current_Direction.down;

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
       checkAttacks();

    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
            current_direction = Current_Direction.up;
            return;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            current_direction = Current_Direction.down;
            return;
        }

        if (handler.getKeyManager().left) {
            xMove = -speed;
            current_direction = Current_Direction.left;
            return;
        }

        if (handler.getKeyManager().right) {
            xMove = speed;
            current_direction = Current_Direction.right;
            return;
        }

    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown){
            return;
        }

        if (handler.getKeyManager().space)
            handler.getWorld().getBulletManager().addBullet(new Bullet(handler, current_direction, x + bounds.x , y + bounds.y ));
        else{
            return;
        }

        attackTimer = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width*3/2, height*3/2, null);
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
            if (current_direction == Current_Direction.up){
                return Assets.robot[0][4];
            }
            if (current_direction == Current_Direction.right){
                return Assets.robot[1][4];
            }
            if (current_direction == Current_Direction.down){
                return Assets.robot[2][4];
            }
            return Assets.robot[3][4];
        }
    }
}
