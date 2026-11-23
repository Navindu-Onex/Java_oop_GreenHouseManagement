package com.smartplant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/fxml/main-layout.fxml")
        );

        Scene scene = new Scene(loader.load(), 1280, 820);
        scene.getStylesheets().add(
                Main.class.getResource("/css/style.css").toExternalForm()
        );

        stage.setTitle("SmartGreen | Greenhouse Management");
        stage.setMinWidth(1050);
        stage.setMinHeight(700);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
