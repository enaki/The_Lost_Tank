package Entity.Creature;

import Entity.Entity;
import Game.Assets;
import Game.*;
import Inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{
    private final int const_speed = 100;
    private Animation animationDown, animationLeft, animationRight, animationUp;

    //Atack timer
    private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;

    private Inventory inventory;

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

        inventory = new Inventory(handler);
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
       //Movement
       getInput();
       move();
       handler.getGameCamera().centerOnEntity(this);

       //Atack
        checkAttacks();
        inventory.tick();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown){
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }
        else if (handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - cb.height;
        }
        else if (handler.getKeyManager().aRight){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height/2 - arSize/2;
        }
        else if (handler.getKeyManager().aLeft){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height/2 - arSize/2;
        }
        else{
            return;
        }

        attackTimer = 0;

        for (Entity e: handler.getWorld().getEntityManager().getEntities()){
            if (e.equals(this)){
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }

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
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        inventory.render(g);

        //g.setColor(Color.RED);
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

    public Inventory getInvetory() {
        return inventory;
    }
}
