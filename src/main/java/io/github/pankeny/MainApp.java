package io.github.pankeny;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("view/Home.fxml"));
        try{
            StackPane stackPane = loader.load();
            primaryStage.setScene(new Scene(stackPane));
        } catch(IOException e) {
            System.out.println("Cant load Home.fxml file");
            e.printStackTrace();
        }

        primaryStage.setTitle("Rooms Manager");

        primaryStage.show();

    }

}
