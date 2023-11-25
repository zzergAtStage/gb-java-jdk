package com.zergatstage.lessons.l02.common;

import java.awt.*;

public interface CanvasRepaintListener {
    void onDrawFrame(MainCanvas canvas, Graphics graphics, float deltaTime);
}
