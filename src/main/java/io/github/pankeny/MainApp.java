package io.github.pankeny;


import io.github.pankeny.controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
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
        showHome();


    }


    private void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Root.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

}


    private void showHome(){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pankeny/view/Home.fxml"));

            System.out.println(this);

            BorderPane homeView =  loader.load();

            rootLayout.setCenter(homeView);

            HomeController controller = loader.getController();

            controller.setMainApp(this);


        } catch(IOException e) {

        }

    }


    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

}

