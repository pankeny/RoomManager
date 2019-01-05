package io.github.pankeny.controller;

import io.github.pankeny.model.Client;
import javafx.collections.ObservableList;

import java.sql.*;
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

        ArrayList<Client> clientsList = new ArrayList<Client>();

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


    public void removeClientFromDB(Integer id){

        String removeClientStatement = "DELETE FROM CLIENTS WHERE ClientId=" + id + ";";

        executeStatement(removeClientStatement);
    }

    public void executeStatement(String SQLStatement){
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
}
