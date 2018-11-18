package io.github.pankeny.controller;

import java.sql.*;

public class DatabaseController {

    static{
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection getConnection() throws SQLException{

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/roomsmanager", "root", "logitech24");

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

    public void addNewClient(String clientName, String clientLastName) {


        String addClientStatement = "INSERT INTO clients(name, lastName, idCardNumber) VALUES( '" + clientName.trim() + "', '" + clientLastName.trim() + "', 'insert later');";

        Connection connection = null;

        try{

            connection = getConnection();
            System.out.println("DatabaseController: INSIDE connection try IN createNewCLient");
            PreparedStatement preparedStatement = connection.prepareStatement(addClientStatement);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }



    }
}
