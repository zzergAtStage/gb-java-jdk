package com.zergatstage.lessons.l02;

import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    private static Random random = new Random();
    private final Color color;
    private float vectorX;
    private float vectorY;

    Ball() {
        halfHeight = 20 + (float) (Math.random() * 50f);
        halfWidth = halfHeight;
        color = new Color(random.nextInt());
        vectorX = 100 + (float) (Math.random() * 200f);
        vectorY = 100 + (float) (Math.random() * 200f);
    }
    @Override
    void update(MainCanvas canvas, float deltaTime){
        x += vectorX * deltaTime;
        y += vectorY * deltaTime;
        //check borders
        if(getLeft() < canvas.getLeft()){
            setLeft(canvas.getLeft());
            vectorX = -vectorX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vectorX = -vectorX;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vectorY = -vectorY;
        }
        if (getTop() < canvas.getTop()){
            setTop(canvas.getBottom());
            vectorY = -vectorY;
        }
    }
    @Override
    void render(MainCanvas canvas, Graphics graphics){
        graphics.setColor(color);
        graphics.fillOval((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}
