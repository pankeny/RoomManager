package io.github.pankeny.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;


public class HomeController {



    @FXML
    TableColumn clientIdColumn;

    @FXML
    TableColumn clientNameColumn;

    @FXML
    TableColumn clientLastNameColumn;

    @FXML
    TableColumn clientIdCardColumn;

    @FXML
    Button addReservationButton;

    @FXML
    Button addClientButton;

    @FXML
    void initialize() {

    }

    @FXML

    public void addNewClient(){
        System.out.println("ADDING NEW CLIENT");
    }

    @FXML
    public void addNewReservation(){

    }

}
