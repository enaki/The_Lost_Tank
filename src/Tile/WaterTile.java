package Tile;

import Game.Assets;

import java.awt.image.BufferedImage;

public class WaterTile extends Tile {
    public WaterTile(int id) {
        super(Assets.water, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
