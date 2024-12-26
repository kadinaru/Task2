package com.example.task5;

import javafx.scene.layout.Pane;

public interface Builder {
    void lineBounds(float start, float stop);

    void linePaint(float measure);

    void lineMark(String measure);

    Indicator build();

    Pane getMainPane();

    void show(float measure);
}
