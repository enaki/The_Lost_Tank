package Entity.Creature;

import Game.Assets;

import java.awt.image.BufferedImage;

public class Entity_Types {

    public enum Tank_Type{
        tank_1, tank_2, tank_3, tank_4
    }

    public static float TankSpeed(Tank_Type e){
        switch (e){
            case tank_1:
                return 1f;
            case tank_2:
                return 2f;
            case tank_3:
                return 3f;
            case tank_4:
                return 4f;

        }
        return 1f;
    }

    public static int TankHealth(Tank_Type e){
        switch (e){
            case tank_1:
                return 400;
            case tank_2:
                return 500;
            case tank_3:
                return 600;
            case tank_4:
                return 700;
        }
        return 400;
    }

    public static long TankAttackTime(Tank_Type e){
        switch (e){
            case tank_1:
                return 2000;
            case tank_2:
                return 500;
            case tank_3:
                return 3000;
            case tank_4:
                return 1000;
        }
        return 500;
    }

    public static BufferedImage[] TankImages(Tank_Type e){
        switch (e){
            case tank_1:
                return Assets.tank_1;
            case tank_2:
                return Assets.tank_2;
            case tank_3:
                return Assets.tank_3;
            case tank_4:
                return Assets.tank_4;
        }
        return Assets.tank_1;
    }
}

