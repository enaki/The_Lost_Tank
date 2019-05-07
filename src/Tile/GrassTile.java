package Tile;

import Game.Assets;

import java.awt.image.BufferedImage;

public class GrassTile extends Tile {

    public GrassTile(int id) {
        super(Assets.grass, id);
    }

    @Override
    public boolean isSolid(){
        return false;
    }
}
