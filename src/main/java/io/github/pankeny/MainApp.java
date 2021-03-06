package io.github.pankeny;


import io.github.pankeny.controller.HomeController;
import io.github.pankeny.controller.ReservationController;
import io.github.pankeny.controller.SelectClientReservationsController;
import io.github.pankeny.model.Client;
import io.github.pankeny.model.Room;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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


    public void showHome(){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pankeny/view/Home.fxml"));

            BorderPane homeView =  loader.load();

            rootLayout.setCenter(homeView);

            HomeController homeController = loader.getController();

            homeController.setMainApp(this);


        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void showReservation(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Reservation.fxml"));

            BorderPane reservationView = loader.load();

            rootLayout.setCenter(reservationView);

            ReservationController controller = loader.getController();
            controller.setMainApp(this);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSelectClient(LocalDate checkIn, LocalDate checkOut, Room room){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/SelectClientReservations.fxml"));

            BorderPane selectClientView = loader.load();

            rootLayout.setCenter(selectClientView);

            SelectClientReservationsController controller = loader.getController();
            controller.setMainApp(this);
            controller.passData(checkIn, checkOut, room);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

}

