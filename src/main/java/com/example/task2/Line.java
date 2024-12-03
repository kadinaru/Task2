package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    public Line(Color color) {
        super(color);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getColor());
        gc.setLineWidth(3);
        gc.strokeLine(150, 150, 250, 250);
    }

    @Override
    public String descriptor() {
        return "Отрезок";
    }
}
