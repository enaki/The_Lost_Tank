package Entity.Creature;

import Entity.Current_Direction;
import Game.Assets;
import Game.*;
import Bullet.Bullet;
import Tile.Tile;

import java.awt.*;
import java.util.Random;

import static Entity.Dimensions.Bounds_Dimensions.*;
import static Entity.Dimensions.Bounds_Dimensions.Player_level_1_bounds_height;

public class Enemy extends Shooter{
    protected long lastMoveTimer, moveCoolDown = 2000, moveTimer = moveCoolDown;

    public Enemy(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type)
    {
        super(handler, x, y);
        this.images = Entity_Types.TankImages(tank_type);
        this.attackCooldown = Entity_Types.TankAttackTime(tank_type);
        this.speed = Entity_Types.TankSpeed(tank_type);
        this.health = Entity_Types.TankHealth(tank_type);
        bounds.x = Player_level_1_bounds_x;
        bounds.y = Player_level_1_bounds_y;
        bounds.width = Player_level_1_bounds_width;
        bounds.height = Player_level_1_bounds_height;

        current_direction = Current_Direction.down;
    }

    @Override
    public void die() {
        System.out.println("You Lose");
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
        System.out.println("Generez miscare");
        xMove = 0;
        yMove = 0;
        n = rand.nextInt(100);
        if (n < 25) {
            yMove = -speed;
            current_direction = Current_Direction.up;
        }else
        if (n < 50) {
            yMove = speed;
            current_direction = Current_Direction.down;
        } else

        if (n < 75) {
            xMove = -speed;
            current_direction = Current_Direction.left;
        } else

        if (n < 100) {
            xMove = speed;
            current_direction = Current_Direction.right;
        }
        moveTimer = 0;
    }

    protected void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown){
            return;
        }
        handler.getWorld().getBulletManager().addBullet(new Bullet(handler, current_direction, x + bounds.x , y + bounds.y, Assets.bullet_2, 1, false ));

        attackTimer = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        g.setColor(Color.RED);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

    public void move(){
        if (checkEntityCollisions(xMove, 0f)){
            xMove = -xMove;
        }
        if (checkEntityCollisions(0f, yMove)){
            yMove = -yMove;
        }
        moveX();
        moveY();

    }

    public void moveX(){
        if (xMove >0){
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)){
                x += xMove;
            }
            else{
                xMove = -xMove;
            }
        }
        else if (xMove < 0){
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)){
                x += xMove;
            }
            else{
                xMove = -xMove;
            }
        }
    }

    public void moveY(){
        if (yMove < 0){
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
            else{
                yMove = -yMove;
            }
        }
        else if (yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
            else{
                yMove = -yMove;
            }
        }

    }

}
