package Tile;

import Game.Assets;

import java.awt.image.BufferedImage;

public class SoilTile extends Tile {

    public SoilTile(int id) {
        super(Assets.soil, id);
    }

    @Override
    public boolean isSolid(){
        return false;
    }
}
