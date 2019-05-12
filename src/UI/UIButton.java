package UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIButton extends UIObject{
    private Rectangle rect;
    private ClickListener clicker;
    private String text;

    public UIButton(float x, float y, int width, int height, ClickListener clicker, String text) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.text = text;
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        if (hovering){
            g2d.draw(bounds);
            g.setColor(Color.yellow);
            g.drawString(text, bounds.x + 20, bounds.y + 30);

        } else{
            g.setColor(Color.white);
            g.drawString(text, bounds.x + 20, bounds.y + 30);
            g2d.draw(bounds);
        }

    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
