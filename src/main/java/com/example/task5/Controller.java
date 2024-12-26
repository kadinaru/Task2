package com.example.task5;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {

    @FXML
    private TextField startField;

    @FXML
    private TextField stopField;

    @FXML
    private TextField currentField;

    @FXML
    private Pane indicatorPane;

    @FXML
    private Label valueLabel;

    private Director director = new Director();

    @FXML
    public void onBuildIndicator() {
        try {
            float start = Float.parseFloat(startField.getText());
            float stop = Float.parseFloat(stopField.getText());
            float current = Float.parseFloat(currentField.getText());

            Builder builder = new BuilderIndicatorMini();

            director.construct(builder, current, start, stop);

            builder.show(current);

            indicatorPane.getChildren().clear();
            indicatorPane.getChildren().add(builder.getMainPane());

            updateValueLabel(current, start, stop);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Некорректные данные");
            alert.setContentText("Введите правильные числовые значения.");
            alert.showAndWait();
        }
    }

    private void updateValueLabel(float value, float start, float stop) {
        String status;
        String color;

        if (value < start) {
            status = "Значение за пределами (ниже нормы)";
            color = "red";
        } else if (value > stop) {
            status = "Значение за пределами (выше нормы)";
            color = "red";
        } else if (value == start || value == stop) {
            status = "Значение на границе";
            color = "gold";
        } else {
            status = "Значение в норме";
            color = "green";
        }

        valueLabel.setText("Значение: " + status + " (" + value + ")");
        valueLabel.setStyle("-fx-text-fill: " + color + ";");
    }
}
