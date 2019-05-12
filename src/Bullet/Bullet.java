package Bullet;

import Entity.Creature.Creature;
import Entity.Current_Direction;
import Entity.Entity;
import Game.Assets;
import Game.Handler;
import Tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet  extends Entity {
    private final int image_size = 20;
    private int xMove, yMove;
    private final int speed = 3;
    private int firepower;
    private boolean enemy_fire = true;
    BufferedImage current_image;

    private Current_Direction current_direction;

    public Bullet(Handler handler, Current_Direction current_direction, float x, float y, BufferedImage [] buffer_images, int fire_power, boolean friendly_fire){
        super(handler, x, y, 10, 10);
        this.firepower = fire_power;
        this.enemy_fire = friendly_fire;
        bounds.x = image_size/4;
        bounds.y = image_size/4;
        xMove = 0;
        yMove = 0;
        switch (current_direction){
            case up :
                current_image = buffer_images[0];
                yMove = -speed;
                this.x += Creature.DEFAULT_CREATURE_WIDTH/2 - image_size/2;
                this.y -= image_size;
                break;
            case right:
                current_image = buffer_images[1];
                xMove = speed;
                this.y += Creature.DEFAULT_CREATURE_HEIGHT/2 - image_size/2;
                this.x += Creature.DEFAULT_CREATURE_WIDTH;
                break;
            case down:
                current_image = buffer_images[2];
                yMove = speed;
                this.x += Creature.DEFAULT_CREATURE_WIDTH/2 - image_size/2;
                this.y += Creature.DEFAULT_CREATURE_HEIGHT;
                break;
            case left:
                current_image = buffer_images[3];
                xMove = -speed;
                this.y +=  Creature.DEFAULT_CREATURE_HEIGHT/2 - image_size/2;
                this.x -= image_size;

                break;

        }
    }

    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
            if (e.equals(this) ){
                continue;
            }

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                e.hurt(firepower);
                return true;
            }
        }
        return false;
    }

    //@Override
    public void die() {
        for (int i = 0; i < 16; i++){
            //g.drawImage(Assets.bullet_explosion_animation, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 20, 20, null);

        }
    }

    public void tick(){
        move();
        if (checkEntityCollisions(xMove, 0f)){
            active = false;
        }
        if (checkEntityCollisions(0f, yMove)){
            active = false;
        }
    }

    public void render(Graphics g){

        g.drawImage(current_image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 20, 20, null);
        //waawg.drawImage(Assets.bullet_1[0], (int) (x), (int) y, null);
//        g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);

    }

    public void move(){
        if (!checkEntityCollisions(xMove, 0f)){
            moveX();
        }
        if (!checkEntityCollisions(0f, yMove)){
            moveY();
        }
    }

    public void moveX(){
        if (xMove >0){
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)){
                x += xMove;
            }
            else{
                active = false;
            }
        }
        else if (xMove < 0){
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)){
                x += xMove;
            }
            else{
                active = false;
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
                active = false;
            }
        }
        else if (yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
            else{
                active = false;
            }
        }

    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isNotTraverseble();
    }
}
