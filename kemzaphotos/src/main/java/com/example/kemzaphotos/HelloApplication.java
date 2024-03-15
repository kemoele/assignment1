package com.example.kemzaphotos;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private static final int THUMBNAIL_SIZE = 150;

    private List<Image> images;
    private int currentIndex = 0;
    private Stage fullSizeImageStage;
    private ImageView fullSizeImageView;

    @Override
    public void start(Stage primaryStage) {
        images = loadImages();

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        GridPane thumbnailGrid = createThumbnailGrid();

        Button showAllButton = new Button("Show All Images");
        showAllButton.setOnAction(event -> showAllImages());

        root.getChildren().addAll(thumbnailGrid, showAllButton);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flower Gallery");
        primaryStage.show();
    }

    private GridPane createThumbnailGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        int col = 0;
        int row = 0;
        for (int i = 0; i < images.size(); i++) {
            Image image = images.get(i);
            ImageView thumbnail = createThumbnail(image);

            int finalI = i;
            thumbnail.setOnMouseClicked(event -> {
                currentIndex = finalI;
                showFullSizeImage(image);
            });

            gridPane.add(thumbnail, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        return gridPane;
    }

    private ImageView createThumbnail(Image image) {
        ImageView thumbnail = new ImageView(image);
        thumbnail.setFitWidth(THUMBNAIL_SIZE);
        thumbnail.setFitHeight(THUMBNAIL_SIZE);
        thumbnail.getStyleClass().add("thumbnail");
        return thumbnail;
    }

    private List<Image> loadImages() {
        // Load your images here
        List<Image> images = new ArrayList<>();

        images.add(new Image("1.jpg"));
        images.add(new Image("3.jpg"));
        images.add(new Image("2.jpg"));
        images.add(new Image("4.jpg"));
        images.add(new Image("5.jpg"));
        images.add(new Image("6.jpg"));
        images.add(new Image("7.jpg"));
        images.add(new Image("8.jpg"));
        images.add(new Image("9.jpg"));
        images.add(new Image("10.jpg"));
        images.add(new Image("11.jpg"));
        images.add(new Image("12.jpg"));

        return images;
    }

    private void showFullSizeImage(Image image) {
        if (fullSizeImageStage != null) {
            fullSizeImageStage.close();
        }
        fullSizeImageStage = new Stage();
        fullSizeImageView = new ImageView(image);
        fullSizeImageView.setPreserveRatio(true);
        fullSizeImageView.setFitWidth(500);
        fullSizeImageView.setFitHeight(500);

        Button previousButton = new Button("Previous");
        previousButton.setOnAction(event -> showPreviousImage());

        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> showNextImage());

        HBox buttonPane = new HBox(10);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(previousButton, nextButton);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(fullSizeImageView, buttonPane);

        Scene scene = new Scene(vbox);
        fullSizeImageStage.setScene(scene);
        fullSizeImageStage.setTitle("Full Size Image");
        fullSizeImageStage.show();
    }

    private void showNextImage() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
            showFullSizeImage(images.get(currentIndex));
        }
    }

    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            showFullSizeImage(images.get(currentIndex));
        }
    }

    private void showAllImages() {
        Stage allImagesStage = new Stage();
        VBox allImagesBox = new VBox(10);
        allImagesBox.setAlignment(Pos.CENTER);

        for (Image image : images) {
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(500);
            imageView.setFitHeight(500);
            allImagesBox.getChildren().add(imageView);
        }

        Scene allImagesScene = new Scene(allImagesBox);
        allImagesStage.setScene(allImagesScene);
        allImagesStage.setTitle("All Images");
        allImagesStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
