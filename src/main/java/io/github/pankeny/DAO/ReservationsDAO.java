package io.github.pankeny.DAO;

import java.sql.Date;

public interface ReservationsDAO {

    public void addReservation(String clientName, String clientLastname, int RoomNumber, Date startDate, Date endDate);
    public void createNewClient (String clientName, String clientLastName);

}
