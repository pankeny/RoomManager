package io.github.pankeny.controller;

import io.github.pankeny.model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class NewClientDialogController {



    @FXML
    TextField nameTextField;

    @FXML
    TextField lastnameTextField;

    @FXML
    TextField idCardTextField;

    @FXML
    Label validationInfoLabel;

    ControllerInterface controller;


    @FXML
    public void initialize() {
        validationInfoLabel.setVisible(false);
    }

//    public void setParent(HomeController controller){
//        this.homeController = controller;
//    }
//    public void setParent(SelectClientReservationsController selectClientReservationsController) {this.selectClientReservationsController = selectClientReservationsController;}
    public void setParent(ControllerInterface controller){
        this.controller = controller;
    }

    @FXML
    public void addNewClient(){

        if (inputValidation()) {
            Stage popup = controller.getPopupStage();
            Client client = new Client();

            String name = nameTextField.getText();
            String lastName = lastnameTextField.getText();
            String idCard = idCardTextField.getText();


            client.setName(name);
            client.setLastName(lastName);

            DatabaseController dbController = new DatabaseController();

            dbController.addNewClient(name, lastName, idCard);

            popup.close();
        } else {
            validationInfoLabel.setText("Wprowadzone dane są niepoprawne");
            validationInfoLabel.setVisible(true);
        }


    }

    @FXML
    public void handleCancel(){
        Stage popup = controller.getPopupStage();
        popup.close();
    }

    private boolean inputValidation(){

        boolean isValid = false;

        if(!(nameTextField.getText().equals("") || lastnameTextField.getText().equals(""))) isValid = true; // if not equals ""


      return isValid;
    }

}
