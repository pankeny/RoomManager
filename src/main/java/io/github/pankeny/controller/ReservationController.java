package io.github.pankeny.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;


public class ReservationController {

    @FXML
    DatePicker arrivalDateDP;

    @FXML
    DatePicker departureDateDP;

    @FXML
    public void initialize(){

        arrivalDateDP.setOnAction( (ActionEvent e) -> {
            System.out.println(arrivalDateDP.getValue());
        } );

    }


}
