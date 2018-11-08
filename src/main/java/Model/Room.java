package Model;

public class Room {

    private Integer number;
    private Integer NumberOfPeople;
    private Boolean isEngaged;
    private Boolean doubleRoom;
    private Double pricePerDay;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberOfPeople() {
        return NumberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        NumberOfPeople = numberOfPeople;
    }

    public Boolean getEngaged() {
        return isEngaged;
    }

    public void setEngaged(Boolean engaged) {
        isEngaged = engaged;
    }

    public Boolean getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(Boolean doubleRoom) {
        this.doubleRoom = doubleRoom;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
