package KeyManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;
    public boolean space;

    public boolean esc;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        space = keys[KeyEvent.VK_SPACE];
        esc = keys[KeyEvent.VK_ESCAPE];

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
//        System.out.println("Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
//        System.out.println("Released!");

    }
}
