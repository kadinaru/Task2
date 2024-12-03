package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape {
    public Pentagon(Color color) {
        super(color);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.fillPolygon(
                new double[]{150, 200, 250, 230, 170},
                new double[]{250, 150, 250, 300, 300},
                5
        );
    }

    @Override
    public String descriptor() {
        return "Пятиугольник";
    }
}
