package Tile;

import Game.Assets;

public class RockTile extends Tile {
    public RockTile(int id) {
        super(Assets.mountain, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
