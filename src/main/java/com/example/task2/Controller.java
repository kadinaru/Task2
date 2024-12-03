package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Map;

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private TextField fieldSides;

    @FXML
    private MenuButton menuColor;

    @FXML
    private Text figureText; // Текст для отображения информации о фигуре

    private Color selectedColor = Color.BLACK; // Цвет по умолчанию

    @FXML
    public void initialize() {
        // Карта сопоставления названий цветов и их значений
        Map<String, Color> colorMap = Map.of(
                "Красный", Color.RED,
                "Оранжевый", Color.ORANGE,
                "Желтый", Color.YELLOW,
                "Зеленый", Color.GREEN,
                "Синий", Color.BLUE,
                "Фиолетовый", Color.PURPLE
        );

        // Установка обработчиков для элементов меню
        for (MenuItem item : menuColor.getItems()) {
            item.setOnAction(e -> {
                String colorName = item.getText();
                selectedColor = colorMap.getOrDefault(colorName, Color.BLACK); // Если цвет не найден, использовать черный
                menuColor.setText(colorName); // Обновление текста MenuButton
            });
        }
    }

    @FXML
    private void onDrawButtonClick() {
        try {
            // Получение количества сторон из текстового поля
            int sides = Integer.parseInt(fieldSides.getText());

            // Создание фабрики фигур
            ShapeFactory factory = new ShapeFactory();
            Shape shape = factory.createPolygon(sides, selectedColor);

            // Установка текста с названием фигуры
            figureText.setText("Фигура: " + shape.descriptor());

            // Получение графического контекста для рисования
            var gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Очистка холста
            shape.draw(gc); // Рисование фигуры
        } catch (NumberFormatException e) {
            figureText.setText("Ошибка: некорректное значение количества сторон");
        } catch (IllegalArgumentException e) {
            figureText.setText("Фигура: Неправильное количество сторон");
        }
    }
}
