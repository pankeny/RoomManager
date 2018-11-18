package io.github.pankeny.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Reservations{

    static{
        try{

            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection getConnection() throws SQLException{

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/roomsmanager", "malochak", "logitech24");

    }

    private void closeConnection(Connection connection){

        if(connection == null) return;

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addReservation(String clientName, String clientLastname, int RoomNumber, Date startDate, Date endDate) {



    }

    public void createNewClient(String clientName, String clientLastName) {



    }
}
