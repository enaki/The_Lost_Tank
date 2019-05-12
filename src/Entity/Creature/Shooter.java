package Entity.Creature;

import Entity.Current_Direction;
import Entity.Types.Bullet_Types;
import Entity.Types.Entity_Types;
import Game.Handler;

import java.awt.image.BufferedImage;

import static Entity.Dimensions.Tank_Type_Bounds_Dimensions.*;


public abstract class Shooter extends Creature{
    protected BufferedImage[] images;
    protected final int const_speed = 5;
    //Atack timer
    protected long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;
    protected Current_Direction current_direction;
    protected Entity_Types.Tank_Type tank_type;
    protected Bullet_Types.Bullet_Type bullet_type;
    protected boolean EnemyBullet = true;

    public Shooter(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        current_direction = Current_Direction.down;

    }

    protected void set_Bounds_Dimension(boolean vertical){
        if(vertical){
            bounds.x = GetBoundsX(this.tank_type);
            bounds.y = GetBoundsY(this.tank_type);
            bounds.width = GetBoundsWidth(this.tank_type);
            bounds.height = GetBoundsHeight(this.tank_type);
        }
        else{
            bounds.x = GetBoundsY(this.tank_type);
            bounds.y = GetBoundsX(this.tank_type);
            bounds.width = GetBoundsHeight(this.tank_type);
            bounds.height = GetBoundsWidth(this.tank_type);

        }
    }

    public boolean isEnemyBullet() {
        return EnemyBullet;
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

    public Current_Direction getCurrent_direction() {
        return current_direction;
    }
}
