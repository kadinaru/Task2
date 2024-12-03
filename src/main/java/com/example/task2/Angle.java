package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle extends Shape {
    public Angle(Color color) {
        super(color);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getColor());
        gc.setLineWidth(3);
        gc.strokeLine(150, 150, 250, 150);
        gc.strokeLine(150, 150, 150, 250);
    }

    @Override
    public String descriptor() {
        return "Угол";
    }
}
