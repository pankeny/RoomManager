package io.github.pankeny.controller;

import io.github.pankeny.model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

public class EditClientDialogController {

    @FXML
    TextField nameTextField;

    @FXML
    TextField lastnameTextField;

    @FXML
    TextField idCardTextField;

    @FXML
    Label validationInfoLabel;

    HomeController homeController;
    Client client;

    DatabaseController dbController = new DatabaseController();


    @FXML
    public void initialize() {
        validationInfoLabel.setVisible(false);
    }



    @FXML
    public void editClient(){
        if(inputValidation()) {
            Stage popup = homeController.getPopupStage();

            String newName = nameTextField.getText();
            String newLastname = lastnameTextField.getText();
            String newIdCardNumber = idCardTextField.getText();

            dbController.updateClientInDB(client.getId(), newName, newLastname, newIdCardNumber);

            popup.close();

        } else {
            validationInfoLabel.setText("Wprowadzone dane są niepoprawne");
            validationInfoLabel.setVisible(true);
        }
    }

    @FXML
    public void handleCancel(){
        Stage popup = homeController.getPopupStage();
        popup.close();
    }


    private boolean inputValidation(){

        boolean isValid = false;

        if(!(nameTextField.getText().equals("") || lastnameTextField.getText().equals(""))) isValid = true; // if not equals ""


        return isValid;
    }

    public void setParent(HomeController controller){
        this.homeController = controller;
    }
    public  void setClient(Client client) {

        this.client = client;

        if (client != null){
            nameTextField.setText(client.getName());
            lastnameTextField.setText(client.getLastName());
            idCardTextField.setText(client.getIdCardNumber());
        }
    }



}
