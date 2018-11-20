package io.github.pankeny.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty lastName;
    private StringProperty idCardNumber;

    public Client(){

        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.idCardNumber = new SimpleStringProperty("");

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getIdCardNumber() {
        return idCardNumber.get();
    }

    public StringProperty idCardNumberProperty() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber.set(idCardNumber);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name=" + name +
                ", lastName=" + lastName +
                ", idCardNumber=" + idCardNumber +
                '}';
    }
}
