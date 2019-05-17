package World;

import Bullet.BulletManager;
import Entity.AnimationObjects.AnimationManager;
import Entity.Creature.Enemy;
import Entity.Creature.EnemyWithAnimations;
import Entity.Creature.Player;
import Entity.EntityManager;
import Entity.Static_Entity.Town;
import Entity.Static_Entity.Tree;
import Game.*;
import Item.ItemManager;
import State.Utils.Levels;
import Tile.Tile;
import Utils.Utils;
import static Entity.Types.Entity_Types.Tank_Type.*;
import static State.Utils.Levels.GetLevelWorld;

import java.awt.*;


public class World {
    private Handler handler;
    private int width, height, spawnX, spawnY;
    private int[][] tiles;
    private EntityManager entityManager;
    private BulletManager bulletManager;
    private int PlayerSpawnX, PlayerSpawnY;
    public BulletManager getBulletManager() {
        return bulletManager;
    }
    private ItemManager itemManager;
    private AnimationManager animationManager;

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public void setAnimationManager(AnimationManager animationManager) {
        this.animationManager = animationManager;
    }

    public void setBulletManager(BulletManager bulletManager) {
        this.bulletManager = bulletManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public World(Handler handler, Levels.Level level){
        this.handler = handler;
        bulletManager = new BulletManager(handler);
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);
        animationManager = new AnimationManager(handler);
        loadWorld(GetLevelWorld(level));
    }

    public void setWorld(String path){
        bulletManager = new BulletManager(handler);
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);
        animationManager = new AnimationManager(handler);

        loadWorld(path);
    }

    public void tick(){
        bulletManager.tick();
        entityManager.tick();
        itemManager.tick();
        animationManager.tick();


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
        entityManager.render(g);
        itemManager.render(g);
        bulletManager.render(g);
        animationManager.render(g);
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
                    case 5 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new Town(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT));
                        break;
                    case 3 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new Tree(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT));
                        break;
                    case -1 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new Enemy(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, tank_1));
                        break;
                    case -2 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new Enemy(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, tank_2));
                        break;
                    case -3 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new Enemy(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, tank_3));
                        break;
                    case -4 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new Enemy(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, tank_4));
                        break;
                    case -5 :
                        temp = Tile.grassTile.getId();
                        entityManager.addEntity(new EnemyWithAnimations(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, robot));
                        break;
                    case 11 :
                        temp = Tile.dirtTile.getId();
                        entityManager.addPlayer(new Player(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT));
//                        entityManager.getPlayer().setX(spawnX);
//                        entityManager.getPlayer().setY(spawnY);
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

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
