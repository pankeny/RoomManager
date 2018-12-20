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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    Stage popupStage;
    AnchorPane newClientScene;

    private ObservableList<Client> clientObservableList;
    private ObservableList<Room> roomObservableList;

    private DatabaseController dbController = new DatabaseController();

    private MainApp mainApp;

    @FXML
    void initialize() {
        initClientTable();
        initRoomTable();
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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
