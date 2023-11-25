package com.zergatstage.lessons.l02.circles;

import com.zergatstage.lessons.l02.common.MainCanvas;
import com.zergatstage.lessons.l02.common.Intractable;

import java.awt.*;

public class Background implements Intractable {
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;


    public void update(MainCanvas canvas, float deltaTime){
        time += deltaTime;
        int red = calcAmplitude(2f);
        int greed = calcAmplitude(3f);
        int blue = calcAmplitude(1f);
        color = new Color(red,greed,blue);
    }

    public void render(MainCanvas canvas, Graphics graphics){
        canvas.setBackground(color);
    }
    private int calcAmplitude(float offset) {
        return Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * offset));
    }
}
