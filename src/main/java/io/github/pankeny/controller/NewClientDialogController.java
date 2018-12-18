package io.github.pankeny.controller;

import io.github.pankeny.model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class NewClientDialogController {



    @FXML
    TextField nameTextField;

    @FXML
    TextField lastnameTextField;

    @FXML
    TextField idCardTextField;

    HomeController homeController;
    Stage popupStage;


    @FXML
    public void initialize() {

    }

    public void setParent(HomeController controller){
        this.homeController = controller;
    }

    @FXML
    public void addNewClient(){
        Stage popup = homeController.getPopupStage();
        Client client = new Client();

        String name = nameTextField.getText();
        String lastName = lastnameTextField.getText();

        client.setName(name);
        client.setLastName(lastName);

        DatabaseController dbController = new DatabaseController();

        dbController.addNewClient(name, lastName);
        System.out.println("ADDING NEW CLIENT INSIDE DIALOG WINDOW");
        popup.setTitle("OK");

        popup.close();
    }



}
