package com.example.task2;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape createPolygon(int numberOfSides, Color color) {
        return switch (numberOfSides) {
            case 0 -> new Circle(color);
            case 1 -> new Line(color);
            case 2 -> new Angle(color);
            case 3 -> new Triangle(color);
            case 4 -> new Square(color);
            case 5 -> new Pentagon(color);
            default -> throw new IllegalArgumentException("Недопустимое количество сторон: " + numberOfSides);
        };
    }
}
