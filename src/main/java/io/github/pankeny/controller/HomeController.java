package io.github.pankeny.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.pankeny.MainApp;
import io.github.pankeny.model.Client;
import io.github.pankeny.model.Room;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HomeController {



    // Client Table
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

    // Room Table


    @FXML
    TableView<Room> roomTableView;

    @FXML
    TableColumn<Room, Number> numberColumn;

    @FXML
    TableColumn<Room, Number> numberOfPeopleColumn;

    @FXML
    TableColumn<Room, String> isFreeColumn;

    @FXML
    TableColumn<Room, String> doubleRoomColumn;

    @FXML
    TableColumn<Room, Number> pricePerDayColumn;

    @FXML
    TableColumn<Room,String> extraColumn;

    @FXML
    Button addReservationButton;

    @FXML
    Button addClientButton;

    @FXML
    Button removeClientButton;

    @FXML
    Button editClientButton;

    Stage popupStage;
    AnchorPane newClientScene;
    AnchorPane editClientScene;

    private ObservableList<Client> clientObservableList;
    private Client selectedClient;

    private ObservableList<Room> roomObservableList;

    private DatabaseController dbController = new DatabaseController();

    private MainApp mainApp;

    @FXML
    void initialize() {
        initClientTable();
        initRoomTable();
    }

        clientTable.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> setCurrentClient(newValue));
    }

    @FXML
    public boolean addNewClient(){
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

    @FXML
    public void removeClient(){
        if (selectedClient == null) {
            System.out.println("Client has not been choosen");
        } else {
            dbController.removeClientFromDB(selectedClient.getId());
            initClientTable();
        }
    }

    @FXML
    public boolean editClient(){

        if (selectedClient != null){
            try{
                this.popupStage = new Stage();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/io/github/pankeny/view/EditClientDialog.fxml"));

                editClientScene = loader.load();

                popupStage.setTitle("Edycja klienta");
                popupStage.setScene(new Scene(editClientScene));
                popupStage.initModality(Modality.WINDOW_MODAL);
                popupStage.initOwner(mainApp.getPrimaryStage());

                EditClientDialogController controller = loader.getController();
                controller.setClient(selectedClient);
                controller.setParent(this);

                popupStage.showAndWait();
                initClientTable();

                return true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Brak zaznaczenia");
            alert.setContentText("Nie zaznaczono klienta! Jeżeli chcesz edytować dane, proszę zaznacz konkretnego klienta.");

            alert.showAndWait();
            return false;
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

    public Client getCurrentClient(){
        return selectedClient;
    }

    private void initRoomTable(){

        roomObservableList = FXCollections.observableArrayList(dbController.getRoomsFromDB());

        numberColumn.setCellValueFactory( e -> e.getValue().numberProperty() );
        numberOfPeopleColumn.setCellValueFactory( e -> e.getValue().numberOfPeopleProperty() );
        isFreeColumn.setCellValueFactory( e -> (e.getValue().isIsEngaged() ? new SimpleStringProperty("Nie") : new SimpleStringProperty("Tak"))); // convert boolean type to string yes/no
        doubleRoomColumn.setCellValueFactory( e -> (e.getValue().isDoubleRoom() ? new SimpleStringProperty("Tak") : new SimpleStringProperty("Nie"))); // convert boolean type to string yes/no
        pricePerDayColumn.setCellValueFactory( e -> e.getValue().pricePerDayProperty() );
        extraColumn.setCellValueFactory( e -> e.getValue().extraProperty() );

        roomTableView.setItems(roomObservableList);
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
