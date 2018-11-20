package io.github.pankeny;

import io.github.pankeny.controller.DatabaseController;

public class testMain {

    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();

        System.out.println(databaseController.getClientsFromDB());


    }
}
