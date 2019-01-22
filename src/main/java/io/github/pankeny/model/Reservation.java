package io.github.pankeny.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reservation {

    private IntegerProperty reservationId;
    private IntegerProperty roomNubmer;
    private IntegerProperty clientId;
    private StringProperty clientLastName;
    private ObjectProperty<LocalDate> startDate;
    private ObjectProperty<LocalDate> endDate;
    private DoubleProperty amountDue;


    public Reservation(){
        this.reservationId = new SimpleIntegerProperty();
        this.roomNubmer = new SimpleIntegerProperty();
        this.clientId = new SimpleIntegerProperty();
        this.clientLastName = new SimpleStringProperty("");
        this.startDate = new SimpleObjectProperty<>();
        this.endDate = new SimpleObjectProperty<>();
        this.amountDue = new SimpleDoubleProperty();
    }

    public int getReservationId() {
        return reservationId.get();
    }

    public IntegerProperty reservationIdProperty() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId.set(reservationId);
    }

    public int getRoomNubmer() {
        return roomNubmer.get();
    }

    public IntegerProperty roomNubmerProperty() {
        return roomNubmer;
    }

    public void setRoomNubmer(int roomNubmer) {
        this.roomNubmer.set(roomNubmer);
    }

    public int getClientId() {
        return clientId.get();
    }

    public IntegerProperty clientIdProperty() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId.set(clientId);
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public double getAmountDue() {
        return amountDue.get();
    }

    public DoubleProperty amountDueProperty() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue.set(amountDue);
    }

    public String getClientLastName(){ return clientLastName.get(); }

    public StringProperty clientLastNameProperty() { return clientLastName; }

    public void  setClientLastName(String clientLastName) { this.clientLastName.set(clientLastName);}
}
