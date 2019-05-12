package Entity.Creature;

import Entity.Current_Direction;
import Game.Handler;

import java.awt.image.BufferedImage;

import static Entity.Dimensions.Bounds_Dimensions.*;
import static Entity.Dimensions.Bounds_Dimensions.Player_level_1_bounds_width;


public abstract class Shooter extends Creature{
    protected BufferedImage[] images;
    protected final int const_speed = 5;
    //Atack timer
    protected long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
    protected Current_Direction current_direction;

    public Shooter(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        current_direction = Current_Direction.down;

    }

    protected void set_Bounds_Dimension(boolean vertical){
        if(vertical){
            bounds.x = Player_level_1_bounds_x;
            bounds.y = Player_level_1_bounds_y;
            bounds.width = Player_level_1_bounds_width;
            bounds.height = Player_level_1_bounds_height;
        }
        else{
            bounds.x = Player_level_1_bounds_y;
            bounds.y = Player_level_1_bounds_x;
            bounds.width = Player_level_1_bounds_height;
            bounds.height = Player_level_1_bounds_width;
        }
    }

    protected abstract void checkAttacks();

    protected BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return images[3];
        }
        else if(xMove > 0){
            return images[1];
        }
        else if (yMove < 0){
            return images[0];
        }
        else if (yMove > 0){
            return images[2];
        }
        else{
            if (current_direction == Current_Direction.up){
                return images[0];
            }
            if (current_direction == Current_Direction.right){
                return  images[1];
            }
            if (current_direction == Current_Direction.down){
                return  images[2];
            }
            return images[3];
        }
    }
}
