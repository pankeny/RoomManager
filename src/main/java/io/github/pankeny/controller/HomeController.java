package io.github.pankeny.controller;

import io.github.pankeny.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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


    private ObservableList<Client> clientObservableList;
    private DatabaseController dbController = new DatabaseController();

    @FXML
    void initialize() {

        initClientTable();

    }

    @FXML

    public void addNewClient(){
        System.out.println("ADDING NEW CLIENT");



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

}
