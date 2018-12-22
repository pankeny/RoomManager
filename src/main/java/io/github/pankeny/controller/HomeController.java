package io.github.pankeny.controller;

import io.github.pankeny.MainApp;
import io.github.pankeny.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {


    @FXML
    TableView<Client> clientTable;

    @FXML
    TableColumn<Client, Number> clientIdColumn;

    @FXML
    TableColumn<Client, String> clientNameColumn;

    @FXML
    TableColumn<Client, String> clientLastNameColumn;

    @FXML
    TableColumn<Client, String> clientIdCardColumn;

    @FXML
    Button addReservationButton;

    @FXML
    Button addClientButton;

    @FXML
    Button removeClientButton;

    Stage popupStage;
    AnchorPane newClientScene;

    private ObservableList<Client> clientObservableList;
    Client selectedClient;

    private DatabaseController dbController = new DatabaseController();

    private MainApp mainApp;

    @FXML
    void initialize() {
        initClientTable();

        clientTable.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> setCurrentClient(newValue));
    }

    @FXML
    public boolean addNewClient(ActionEvent event) throws IOException {
    try{
        this.popupStage = new Stage();
        System.out.println("ADDING NEW CLIENT");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/io/github/pankeny/view/NewClientDialog.fxml"));
        newClientScene = loader.load();

        popupStage.setTitle("Dodawanie klienta");
        popupStage.setScene(new Scene(newClientScene));
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(mainApp.getPrimaryStage());

        NewClientDialogController controller = loader.getController();

        controller.setParent(this);

        popupStage.showAndWait();
        initClientTable();

        return true;

    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    }

    public void removeClient(){
        if (selectedClient == null) {
            System.out.println("Client has not been choosen");
        } else {
            dbController.removeClientFromDB(selectedClient.getId());
            initClientTable();
        }
    }

    @FXML
    public void addNewReservation(){

    }

    // INIT METHODS

    private void initClientTable(){
        clientObservableList = FXCollections.observableArrayList(dbController.getClientsFromDB());

        clientIdColumn.setCellValueFactory( e -> e.getValue().idProperty());
        clientNameColumn.setCellValueFactory( e -> e.getValue().nameProperty() );
        clientLastNameColumn.setCellValueFactory( e -> e.getValue().lastNameProperty());
        clientIdCardColumn.setCellValueFactory( e -> e.getValue().idCardNumberProperty());

        clientTable.setItems(clientObservableList);
    }

    public void setCurrentClient(Client client){
        this.selectedClient = client;
        System.out.println(client);
    }

    private void setUpPopup(){

    }

    public Stage getPopupStage(){
        return popupStage;
    }

    public ObservableList<Client> getClientObservableList() {
        return clientObservableList;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
