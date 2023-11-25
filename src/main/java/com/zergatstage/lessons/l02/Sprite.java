package com.zergatstage.lessons.l02;

import java.awt.*;

public abstract class Sprite {
    protected float x;
    protected float y;

    protected float halfWidth;
    protected float halfHeight;

    //left
    protected float getLeft() {
        return x - halfWidth;
    }

    protected void setLeft(float left) {
        x = left + halfWidth;
    }

    //right
    protected float getRight() {
        return x + halfWidth;
    }

    protected void setRight(float right) {
        x = right + halfWidth;
    }

    //top
    protected float getTop() {
        return y - halfHeight;
    }

    protected void setTop(float top) {
        y = top + halfHeight;
    }

    //bottom
    protected float getBottom() {
        return y - halfHeight;
    }

    protected void setBottom(float bottom) {
        y = bottom + halfHeight;
    }

    protected float getWidth() {
        return 2f * halfWidth;
    }

    protected float getHeight() {
        return 2f * halfHeight;
    }

    void update(MainCanvas canvas, float deltaTime) {
    }

    void render(MainCanvas canvas, Graphics graphics) {
    }
}
