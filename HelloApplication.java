package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class HelloApplication extends Application {
    private Button button1;
    private Button button2;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);

        button1 = new Button("Button 1");
        button1.setMinSize(100, 100);
        button1.setLayoutX(100);
        button1.setLayoutY(100);

        button2 = new Button("Button 2");
        button2.setMinSize(100, 100);
        button2.setLayoutX(100);
        button2.setLayoutY(300);

        pane.getChildren().addAll(button1, button2);
        ButtonCoords buttonCoords = new ButtonCoords();

        button1.setOnMousePressed(mouseEvent -> {
            buttonCoords.setX(mouseEvent.getSceneX() - button1.getLayoutX());
            buttonCoords.setY(mouseEvent.getSceneY() - button1.getLayoutY());

        });

        button1.setOnMouseDragged(mouseEvent -> {
            button1.setLayoutX(mouseEvent.getSceneX() - buttonCoords.getX());
            button1.setLayoutY(mouseEvent.getSceneY() - buttonCoords.getY());
            collisionCheck(button1, button2);
        });

        button2.setOnMousePressed(mouseEvent -> {
            buttonCoords.setX(mouseEvent.getSceneX() - button2.getLayoutX());
            buttonCoords.setY(mouseEvent.getSceneY() - button2.getLayoutY());
        });

        button2.setOnMouseDragged(mouseEvent -> {
            button2.setLayoutX(mouseEvent.getSceneX() - buttonCoords.getX());
            button2.setLayoutY(mouseEvent.getSceneY() - buttonCoords.getY());
            collisionCheck(button2, button1);
        });

        stage.setTitle("Buttons");
        stage.setScene(scene);
        stage.show();
    }

    private void collisionCheck(Button button2, Button button1) {
        if (button1.getBoundsInParent().intersects(button2.getBoundsInParent())) {
            double x = button2.getLayoutX() - button1.getLayoutX();
            double y = button2.getLayoutY() - button1.getLayoutY();

            if (100 - Math.abs(x) > 100 - Math.abs(y)){
                if (y > 0) button1.setLayoutY(button1.getLayoutY() - (102 - y));
                else button1.setLayoutY(button1.getLayoutY() + (102 + y));
            }
            else if (100 - Math.abs(y) > 100 - Math.abs(x)){
                if (x > 0) button1.setLayoutX(button1.getLayoutX() - (102 - x));
                else button1.setLayoutX(button1.getLayoutX() + (102 + x));
            }
            else {
                if (x > 0) button1.setLayoutX(button1.getLayoutX() - (102 - x));
                else button1.setLayoutX(button1.getLayoutX() + (102 + x));

                if (y > 0) button1.setLayoutY(button1.getLayoutY() - (102 - y));
                else button1.setLayoutY(button1.getLayoutY() + (102 + y));
            }

        }
    }
}