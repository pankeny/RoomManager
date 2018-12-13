package io.github.pankeny.model;

import java.util.Date;

public class Reservation {

    private Integer reservationId;
    private Integer roomNubmer;
    private Integer clientId;
    private Date startDate;
    private Date endDate;
    private Double AmountDue;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getRoomNubmer() {
        return roomNubmer;
    }

    public void setRoomNubmer(Integer roomNubmer) {
        this.roomNubmer = roomNubmer;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAmountDue() {
        return AmountDue;
    }

    public void setAmountDue(Double amountDue) {
        AmountDue = amountDue;
    }
}
