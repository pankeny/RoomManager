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

    Stage popupStage = new Stage();
    AnchorPane newClientScene;

    private ObservableList<Client> clientObservableList;
    private DatabaseController dbController = new DatabaseController();

    private Stage primaryStage;

    private MainApp mainApp;


    @FXML
    void initialize() {
        initClientTable();
    }


    @FXML
    public void addNewClient(ActionEvent event) throws IOException {

        System.out.println("ADDING NEW CLIENT");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/io/github/pankeny/view/NewClientDialog.fxml"));
        newClientScene = loader.load();

        popupStage.setTitle("Dodawanie klienta");
        popupStage.setScene(new Scene(newClientScene));
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage);

        NewClientDialogController controller = loader.getController();
        controller.setPopupStage(popupStage);
        popupStage.showAndWait();
        popupStage.close();
        initClientTable();
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

    public Stage getPopupStage(){
        return popupStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
