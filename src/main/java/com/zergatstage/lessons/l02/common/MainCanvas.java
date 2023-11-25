package com.zergatstage.lessons.l02.common;

import com.zergatstage.lessons.l02.circles.BubbleWindow;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {
    private final CanvasRepaintListener controller;
    private float lastFrameTime;
    public MainCanvas(CanvasRepaintListener controller){
        setBackground(Color.GRAY);
        this.controller = controller;
        lastFrameTime = System.nanoTime();
    }
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        //call sleep to enable nonstop rendering
        try {
            Thread.sleep(16);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        float deltaTime = (System.nanoTime() - lastFrameTime) * 0.000000001f;
        controller.onDrawFrame(this, graphics, deltaTime);
        lastFrameTime = System.nanoTime();
        repaint();
    }

    public int getLeft(){return 0;}
    public int getRight() {return getWidth() -1;}
    public int getTop(){ return 0;}
    public int getBottom(){return getHeight()-1;}
}
