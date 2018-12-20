package io.github.pankeny.model;

import javafx.beans.property.*;

public class Room {

    private IntegerProperty number;
    private IntegerProperty numberOfPeople;
    private BooleanProperty isEngaged;
    private BooleanProperty doubleRoom;
    private DoubleProperty pricePerDay;
    private StringProperty extra;

    public Room(){
        this.number = new SimpleIntegerProperty();
        this.numberOfPeople = new SimpleIntegerProperty();
        this.isEngaged = new SimpleBooleanProperty();
        this.doubleRoom = new SimpleBooleanProperty();
        this.pricePerDay = new SimpleDoubleProperty();
        this.extra = new SimpleStringProperty("");
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public int getNumberOfPeople() {
        return numberOfPeople.get();
    }

    public IntegerProperty numberOfPeopleProperty() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople.set(numberOfPeople);
    }

    public boolean isIsEngaged() {
        return isEngaged.get();
    }

    public BooleanProperty isEngagedProperty() {
        return isEngaged;
    }

    public void setIsEngaged(boolean isEngaged) {
        this.isEngaged.set(isEngaged);
    }

    public boolean isDoubleRoom() {
        return doubleRoom.get();
    }

    public BooleanProperty doubleRoomProperty() {
        return doubleRoom;
    }

    public void setDoubleRoom(boolean doubleRoom) {
        this.doubleRoom.set(doubleRoom);
    }

    public double getPricePerDay() {
        return pricePerDay.get();
    }

    public DoubleProperty pricePerDayProperty() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay.set(pricePerDay);
    }

    public String getExtra() {
        return extra.get();
    }

    public StringProperty extraProperty() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra.set(extra);
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", numberOfPeople=" + numberOfPeople +
                ", isEngaged=" + isEngaged +
                ", doubleRoom=" + doubleRoom +
                ", pricePerDay=" + pricePerDay +
                ", extra=" + extra +
                '}';
    }
}
