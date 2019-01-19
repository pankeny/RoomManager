package io.github.pankeny.controller;

import io.github.pankeny.MainApp;
import io.github.pankeny.model.Client;
import io.github.pankeny.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class SelectClientReservationsController {

    //Client TABLE
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


    private MainApp mainApp;
    private LocalDate checkIn, checkOut;
    private Room room;
    private ObservableList<Client> clientObservableList;
    private DatabaseController dbController = new DatabaseController();
    private Client selectedClient;

    @FXML
    public void initialize(){
        initClientTable();

        clientTable.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> setCurrentClient(newValue));

    }

    @FXML
    public void addReservation(){
        if (selectedClient != null) {
            Long daysBetween = DAYS.between(checkIn, checkOut);
            Double amountDue = daysBetween * room.getPricePerDay() * room.getNumberOfPeople();

            dbController.addReservation(selectedClient.getId(), room.getNumber(), checkIn, checkOut, amountDue);
            mainApp.showHome();
        } else {
            System.out.println("client not selected");
        }
    }

    @FXML
    public void handleCancel() {
        mainApp.showHome();
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void passData(LocalDate checkIn, LocalDate checkOut, Room room){
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
    }

    private void initClientTable(){
        clientObservableList = FXCollections.observableArrayList(dbController.getClientsFromDB());

        clientIdColumn.setCellValueFactory( e -> e.getValue().idProperty());
        clientNameColumn.setCellValueFactory( e -> e.getValue().nameProperty() );
        clientLastNameColumn.setCellValueFactory( e -> e.getValue().lastNameProperty());
        clientIdCardColumn.setCellValueFactory( e -> e.getValue().idCardNumberProperty());

        clientTable.setItems(clientObservableList);
    }

    private void setCurrentClient(Client client){
        this.selectedClient = client;
    }


}
