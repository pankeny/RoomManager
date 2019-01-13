package io.github.pankeny.controller;

import io.github.pankeny.model.Client;
import io.github.pankeny.model.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseController {

    static{
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection getConnection() throws SQLException{

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/roomsmanager?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Test123.");

    }

    private void closeConnection(Connection connection){

        if(connection == null) return;

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void addNewClient(String clientName, String clientLastName, String idCardNumber) {


        String addClientStatement = "INSERT INTO clients(name, lastName, idCardNumber) VALUES( '" + clientName.trim() + "', '" + clientLastName.trim() + "', '" + idCardNumber.trim() + "');";

        executeStatement(addClientStatement);
    }

    public ArrayList<Client> getClientsFromDB() {

        ArrayList<Client> clientsList = new ArrayList<>();

        Connection connection = null;

        try{

            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CLIENTS");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                Client client = new Client();

                client.setId(resultSet.getInt("ClientId"));
                client.setName(resultSet.getString("Name"));
                client.setLastName(resultSet.getString("LastName"));
                client.setIdCardNumber(resultSet.getString("IdCardNumber"));

                clientsList.add(client);

            }

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return clientsList;
    }


    public ArrayList<Room> getRoomsFromDB(){
        ArrayList<Room> roomsList = new ArrayList<>();

        Connection connection = null;

        try{

            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ROOMS");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                roomsList.add(setRoomDetails(resultSet));

            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return roomsList;


    }

    public ArrayList<Room> getAvailableRoomsFromDB(LocalDate startDate, LocalDate endDate){
        ArrayList<Room> availableRooms = new ArrayList<>();

        System.out.println(startDate + " " + endDate);
        String getAvailableRoomsStatement = "SELECT * FROM rooms WHERE RoomNumber NOT IN( SELECT RoomId from reservations where StartDate >= '" + startDate  + "'  AND EndDate <= '" + endDate + "');";

        Connection connection = null;

        try{
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(getAvailableRoomsStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){


                availableRooms.add(setRoomDetails(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return availableRooms;
    }


    public void removeClientFromDB(Integer id){

        String removeClientStatement = "DELETE FROM CLIENTS WHERE ClientId=" + id + ";";

        executeStatement(removeClientStatement);
    }

    public void updateClientInDB(Integer id, String name, String lastName, String idCardNumber){

        String updateClientStatement = "update clients set Name=" + "\"" + name.trim() + "\"" + ",LastName=" + "\"" + lastName.trim() + "\"" + ",IdCardNumber=" + "\"" + idCardNumber.trim() + "\"" + " where Clientid=" + id + ";";

        executeStatement(updateClientStatement);

    }



    private void executeStatement(String SQLStatement){
        Connection connection = null;

        try{
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private Room setRoomDetails(ResultSet resultSet) throws SQLException{
        Room room = new Room();
        room.setNumber(resultSet.getInt("RoomNumber"));
        room.setNumberOfPeople(resultSet.getInt("NumberOfPeople"));
        room.setDoubleRoom(resultSet.getInt("DoubleRoom") == 1 ? true : false); // in mysql there's only values 0 and 1 for boolean type, so its parsed boolean from int.
        room.setIsEngaged(resultSet.getInt("IsEngaged") == 1 ? true : false);
        room.setPricePerDay(resultSet.getDouble("PricePerDay"));
        room.setExtra(resultSet.getString("Extra"));

        return room;
    }

}
