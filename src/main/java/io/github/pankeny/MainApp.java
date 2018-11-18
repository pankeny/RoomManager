package io.github.pankeny;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Rooms Manager");

        initRootLayout();

    }

    public void initRootLayout(){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Home.fxml"));
            rootLayout = loader.load();
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        } catch(IOException e) {
            System.out.println("Cant load Home.fxml file");
            e.printStackTrace();
        }

    }

}
