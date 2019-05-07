package Game;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 64, height = 64;

    public static BufferedImage player, soil, grass, tree, water, mountain;

    public static BufferedImage wood;

    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] btn_start;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_spritesheet.png"));
        player = sheet.crop(width * 0, height * 0, width, height);
        soil = ImageLoader.loadImage("/textures/soil.png");
        tree = ImageLoader.loadImage("/textures/tree.png");
        water = ImageLoader.loadImage("/textures/water.png");
        grass = ImageLoader.loadImage("/textures/grass.png");
        mountain = ImageLoader.loadImage("/textures/mountain.png");

        wood = ImageLoader.loadImage("/textures/wood.png");


        SpriteSheet btn_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/start_2.png"));
        btn_start = new BufferedImage[2];
        btn_start[0] = btn_sheet.crop(width * 0, height * 1, width * 4, height * 1);
        btn_start[1] = btn_sheet.crop(width * 0, height * 2, width * 4, height * 1);

        player_down = new BufferedImage[4];
        player_down[0] = sheet.crop(width * 0, height * 0, width, height);
        player_down[1] = sheet.crop(width * 1, height * 0, width, height);
        player_down[2] = sheet.crop(width * 2, height * 0, width, height);
        player_down[3] = sheet.crop(width * 3, height * 0, width, height);

        player_left = new BufferedImage[4];
        player_left[0] = sheet.crop(width * 0, height * 1, width, height);
        player_left[1] = sheet.crop(width * 1, height * 1, width, height);
        player_left[2] = sheet.crop(width * 2, height * 1, width, height);
        player_left[3] = sheet.crop(width * 3, height * 1, width, height);

        player_right = new BufferedImage[4];
        player_right[0] = sheet.crop(width * 0, height * 2, width, height);
        player_right[1] = sheet.crop(width * 1, height * 2, width, height);
        player_right[2] = sheet.crop(width * 2, height * 2, width, height);
        player_right[3] = sheet.crop(width * 3, height * 2, width, height);

        player_up = new BufferedImage[4];
        player_up[0] = sheet.crop(width * 0, height * 3, width, height);
        player_up[1] = sheet.crop(width * 1, height * 3, width, height);
        player_up[2] = sheet.crop(width * 2, height * 3, width, height);
        player_up[3] = sheet.crop(width * 3, height * 3, width, height);


    }

}
