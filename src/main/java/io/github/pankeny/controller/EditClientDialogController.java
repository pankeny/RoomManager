package io.github.pankeny.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditClientDialogController {

    @FXML
    TextField nameTextField;

    @FXML
    TextField lastnameTextField;

    @FXML
    TextField idCardTextField;

    @FXML
    Label validationInfoLabel;

    public void editClient(){

        System.out.println("edit ");

    }

    public void handleCancel(){
        System.out.println("cancel");
    }

}
