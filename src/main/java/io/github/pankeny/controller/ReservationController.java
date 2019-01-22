package io.github.pankeny.controller;

import io.github.pankeny.MainApp;
import io.github.pankeny.model.Client;
import io.github.pankeny.model.Reservation;
import io.github.pankeny.model.Room;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReservationController {

    @FXML
    DatePicker arrivalDateDP;

    @FXML
    DatePicker departureDateDP;

    @FXML
    Label errorLabel;

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

    ObservableList<Room> roomObservableList;
    DatabaseController dbController = new DatabaseController();

    Room selectedRoom;

    private MainApp mainApp;

    @FXML
    public void initialize(){

        arrivalDateDP.setOnAction( (ActionEvent e) -> {
            System.out.println(arrivalDateDP.getValue().getClass());
        } );

        roomTableView.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> setCurrentRoom(newValue));

    }


    @FXML
    public void checkAvailableRooms(){

        System.out.println(isDateCorrect());

        if (isDateCorrect()){

            errorLabel.setText("");
            errorLabel.setVisible(false);
            initRoomTable();

        } else {

            errorLabel.setText("Wybrane daty są niepoprawne.");
            errorLabel.setVisible(true);

        }
    }

    @FXML
    public void addReservation(){
        if(selectedRoom != null){
            mainApp.showSelectClient(arrivalDateDP.getValue(), departureDateDP.getValue(), selectedRoom);
        } else {
            errorLabel.setText("Nie wybrano żadnego pokoju");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    public void handleCancel(){
        mainApp.showHome();
    }

    private void initRoomTable(){

        roomObservableList = FXCollections.observableArrayList(dbController.getAvailableRoomsFromDB(arrivalDateDP.getValue(), departureDateDP.getValue()));

        numberColumn.setCellValueFactory( e -> e.getValue().numberProperty() );
        numberOfPeopleColumn.setCellValueFactory( e -> e.getValue().numberOfPeopleProperty() );
        isFreeColumn.setCellValueFactory( e -> (e.getValue().isIsEngaged() ? new SimpleStringProperty("Nie") : new SimpleStringProperty("Tak"))); // convert boolean type to string yes/no
        doubleRoomColumn.setCellValueFactory( e -> (e.getValue().isDoubleRoom() ? new SimpleStringProperty("Tak") : new SimpleStringProperty("Nie"))); // convert boolean type to string yes/no
        pricePerDayColumn.setCellValueFactory( e -> e.getValue().pricePerDayProperty() );
        extraColumn.setCellValueFactory( e -> e.getValue().extraProperty() );
        roomTableView.setItems(roomObservableList);

    }
    public boolean isDateCorrect(){

        boolean checkIsCorrect = false;

        if ((arrivalDateDP.getValue() != null && departureDateDP.getValue() != null) && arrivalDateDP.getValue().isBefore(departureDateDP.getValue()))
            checkIsCorrect = true;

        return checkIsCorrect;
    }

    private void setCurrentRoom(Room room){
        this.selectedRoom = room;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
