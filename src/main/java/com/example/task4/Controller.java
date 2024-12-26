package com.example.task4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {
    @FXML
    private ImageView imageView;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button chooseDirButton;
    @FXML
    private MenuButton formatButton;

    private ConcreteAggregate slides;
    private Iterator iterator;
    private String selectedFormat = "jpg";
    private boolean isPlaying = false;

    public void initialize() {
        MenuItem jpgItem = new MenuItem("JPG");
        jpgItem.setOnAction(e -> {
            selectedFormat = "jpg";
            formatButton.setText("Формат: JPG");
            loadImages();
        });

        MenuItem pngItem = new MenuItem("PNG");
        pngItem.setOnAction(e -> {
            selectedFormat = "png";
            formatButton.setText("Формат: PNG");
            loadImages();
        });

        formatButton.getItems().addAll(jpgItem, pngItem);

        startButton.setOnAction(e -> startSlideshow());
        stopButton.setOnAction(e -> stopSlideshow());
        nextButton.setOnAction(e -> showNextImage());
        backButton.setOnAction(e -> showPreviousImage());
    }

    @FXML
    private void chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            slides = new ConcreteAggregate(selectedDirectory.getAbsolutePath(), selectedFormat);
            iterator = slides.getIterator();
            loadImages();
        }
    }

    private void loadImages() {
        if (slides != null) {
            slides = new ConcreteAggregate(slides.getFiletop(), selectedFormat);
            iterator = slides.getIterator();
            showNextImage();
        }
    }

    private void startSlideshow() {
        isPlaying = true;
        new Thread(() -> {
            while (isPlaying && iterator.hasNext()) {
                Image image = (Image) iterator.next();
                if (image != null) {
                    imageView.setImage(image);
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void stopSlideshow() {
        isPlaying = false;
    }

    private void showNextImage() {
        if (iterator != null && iterator.hasNext()) {
            Image image = (Image) iterator.next();
            if (image != null) {
                imageView.setImage(image);
            }
        }
    }

    private void showPreviousImage() {
        if (iterator != null && iterator.hasPrevious()) {
            Image image = (Image) iterator.previous();
            if (image != null) {
                imageView.setImage(image);
            }
        }
    }
}