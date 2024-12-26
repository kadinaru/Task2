package com.example.task4;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate implements Aggregate {
    private final String filetop;
    private final List<Image> images;
    private String filterFormat;

    public ConcreteAggregate(String filetop, String filterFormat) {
        this.filetop = filetop;
        this.filterFormat = filterFormat;
        this.images = new ArrayList<>();
        loadImages();
    }

    public String getFiletop() {
        return filetop;
    }

    private void loadImages() {
        File folder = new File(filetop);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Указанный путь не является папкой или не существует.");
            return;
        }
        loadImagesFromFolder(folder);
    }

    private void loadImagesFromFolder(File folder) {
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                loadImagesFromFolder(file);
            } else if (file.isFile() && file.getName().endsWith("." + filterFormat)) {
                images.add(new Image("file:" + file.getAbsolutePath()));
            }
        }
    }

    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }

    private class ImageIterator implements Iterator {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < images.size();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return images.get(current++);
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public Object previous() {
            if (this.hasPrevious()) {
                return images.get(--current);
            }
            return null;
        }
    }
}