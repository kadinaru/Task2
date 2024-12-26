package com.example.task5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Indicator {

    private float start, stop;
    private Pane panel;

    public Indicator() {
        panel = new Pane();
    }

    public void setPaint(float value, float start, float stop) {
        this.start = start;
        this.stop = stop;

        panel.getChildren().clear();

        Line boundaryLine = new Line(50, 100, 400, 100);
        boundaryLine.setStroke(Color.BLACK);

        panel.getChildren().add(boundaryLine);

        Text textStart = new Text(50, 120, String.valueOf(start));
        Text textEnd = new Text(400, 120, String.valueOf(stop));
        textStart.setFill(Color.BLACK);
        textEnd.setFill(Color.BLACK);

        panel.getChildren().add(textStart);
        panel.getChildren().add(textEnd);

        double xPos = 50 + (value - start) * 350 / (stop - start);
        Polygon triangle = new Polygon();

        triangle.getPoints().addAll(
                Double.valueOf(xPos), Double.valueOf(80),
                Double.valueOf(xPos - 10), Double.valueOf(100),
                Double.valueOf(xPos + 10), Double.valueOf(100)
        );

        if (value < start || value > stop) {
            triangle.setFill(Color.RED);
        } else if (value == start || value == stop) {
            triangle.setFill(Color.GOLD);
        } else {
            triangle.setFill(Color.GREEN);
        }

        panel.getChildren().add(triangle);
    }

    public void setMetka(float value, float start, float stop) {
        double xPos = 50 + (value - start) * 350 / (stop - start);

        Text metkaText = new Text(xPos - 10, 150, String.valueOf(value));
        metkaText.setFill(Color.BLACK);

        panel.getChildren().add(metkaText);
    }

    public void show(Pane pane, float start, float stop, float value) {
        this.start = start;
        this.stop = stop;
        setPaint(value, start, stop);
        setMetka(value, start, stop);
        pane.getChildren().clear();
        pane.getChildren().add(panel);
    }

    public Pane getPanel() {
        return panel;
    }
}
