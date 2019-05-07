package World;

import Entity.Creature.Player;
import Entity.EntityManager;
import Entity.Static_Entity.Tree;
import Game.*;
import Item.ItemManager;
import Tile.Tile;
import Utils.Utils;

import java.awt.*;

public class World {
    private Handler handler;
    private int width, height, spawnX, spawnY;
    private int[][] tiles;
    private EntityManager entityManager;
    private ItemManager itemManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        entityManager.addEntity(new Tree(handler, 100, 100));
        itemManager = new ItemManager(handler);
        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

    }

    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset()/Tile.TILEWIDTH );
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT );
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        for (int y = yStart; y < yEnd; y++){
            for (int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        itemManager.render(g);
        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null){
            return Tile.dirtTile;
        }
        return t;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        tiles = new int[width][height];
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                int temp = Utils.parseInt(tokens[(x + y * width) + 4]);
                switch(temp){
                    case 3 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new Tree(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT));
                        break;
                    default:
                        break;

                }
                tiles[x][y] = temp;
            }
        }

    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
