package Entity.AnimationObjects;

import Game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimationManager {
    private Handler handler;
    private ArrayList<AnimationObject> animation_objects;
    protected int counter = 0;

    public int getCounter() {
        return counter;
    }

    public AnimationManager(Handler handler){
        this.handler = handler;
        animation_objects = new ArrayList<AnimationObject>();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<AnimationObject> getAnimation_objects() {
        return animation_objects;
    }

    public void setAnimation_objects(ArrayList<AnimationObject> animation_objects) {
        this.animation_objects = animation_objects;
    }

    public void tick(){
        Iterator<AnimationObject> it = animation_objects.iterator();

        while(it.hasNext()){
            AnimationObject e = it.next();
            e.tick();
            if (!e.isActive()){
                counter--;
                it.remove();
            }
        }
    }

    public void render(Graphics g){
        for (AnimationObject e : animation_objects){
            e.render(g);
        }
    }

    public void addAnimationObject(AnimationObject e){
        counter++;
        animation_objects.add(e);
    }
}
