package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.fillPolygon(new double[]{150, 200, 250}, new double[]{250, 150, 250}, 3);
    }

    @Override
    public String descriptor() {
        return "Треугольник";
    }
}
