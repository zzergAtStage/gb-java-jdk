package com.zergatstage.lessons.l02;

import javax.swing.*;
import java.awt.*;

public class BubbleWindow extends JFrame {
    public static final int WINDOW_POSX = 200;
    public static final int WINDOW_POSY = 100;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 400;

    private final Sprite[] sprites = new Sprite[10];
    public BubbleWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_POSX,WINDOW_POSY,WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("BubbleWindow");
        //create canvas
        MainCanvas canvas = new MainCanvas(this);
        //create instances
        for (int i = 0; i < sprites.length; i++) {
            sprites[i]=new Ball();
        }

        add(canvas);
        setVisible(true);


    }

    public static void main(String[] args) {
        new BubbleWindow();
    }

    public void onDrawFrame(MainCanvas canvas, Graphics graphics, float deltaTime){
        update(canvas,deltaTime);
        render(canvas, graphics);
    }


    private void update(MainCanvas canvas, float deltaTime) {
        for (Sprite sprite : sprites) {
            sprite.update(canvas, deltaTime);
        }
    }
    private void render(MainCanvas canvas, Graphics graphics) {
        for (Sprite sprite : sprites) {
            sprite.render(canvas, graphics);
        }
    }
}
