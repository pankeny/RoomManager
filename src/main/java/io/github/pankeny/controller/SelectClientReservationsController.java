package io.github.pankeny.controller;

import io.github.pankeny.MainApp;
import io.github.pankeny.model.Client;
import io.github.pankeny.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class SelectClientReservationsController implements ControllerInterface{

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

    @FXML
    TextField lastNameTextField;

    private MainApp mainApp;
    private Stage popupStage;
    AnchorPane newClientScene;
    private LocalDate checkIn, checkOut;
    private Room room;
    private ObservableList<Client> clientObservableList;
    private DatabaseController dbController = new DatabaseController();
    private Client selectedClient;

    @FXML
    public void initialize(){
        initClientTable();

        clientTable.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> setCurrentClient(newValue));

        lastNameTextField.textProperty().addListener( (observable, oldValue, newValue) -> {
            if (!newValue.equals("")){
                initClientTable(newValue);
            } else {
                initClientTable();
            }
        });
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
        setItemsInReservationTable(clientObservableList);
    }

    private void initClientTable(String lastName){

        clientObservableList = FXCollections.observableArrayList(dbController.getClientsByLastName(lastName));
        setItemsInReservationTable(clientObservableList);

    }

    private void setCurrentClient(Client client){
        this.selectedClient = client;
    }

    private void setItemsInReservationTable(ObservableList<Client> clientObservableList){

        clientIdColumn.setCellValueFactory( e -> e.getValue().idProperty());
        clientNameColumn.setCellValueFactory( e -> e.getValue().nameProperty() );
        clientLastNameColumn.setCellValueFactory( e -> e.getValue().lastNameProperty());
        clientIdCardColumn.setCellValueFactory( e -> e.getValue().idCardNumberProperty());

        clientTable.setItems(clientObservableList);

    }

    public Stage getPopupStage(){
        return this.popupStage;
    }
}
