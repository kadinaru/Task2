package com.example.task5;

import javafx.scene.layout.Pane;

public class BuilderIndicatorMini implements Builder {
    private Indicator indicator;
    private Pane mainPane;
    private float start;
    private float stop;

    public BuilderIndicatorMini() {
        indicator = new Indicator();
        mainPane = new Pane();
    }

    @Override
    public void lineBounds(float start, float stop) {
        this.start = start;
        this.stop = stop;
        indicator.setPaint(0, start, stop);
    }

    @Override
    public void linePaint(float measure) {
        indicator.setPaint(measure, start, stop);
        indicator.setMetka(measure, start, stop);
    }

    @Override
    public void lineMark(String measure) {
    }

    @Override
    public Pane getMainPane() {
        return mainPane;
    }

    @Override
    public Indicator build() {
        return indicator;
    }

    public void show(float measure) {
        indicator.show(mainPane, start, stop, measure);
    }
}
