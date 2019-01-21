package io.github.pankeny;

import io.github.pankeny.controller.DatabaseController;
import io.github.pankeny.model.Reservation;

import java.util.ArrayList;
import java.util.HashMap;

public class testMain {

    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();

        HashMap<String, ArrayList<Object>> reservationsClientsMap = databaseController.getReservationsCombinedWithClients();

        ArrayList<Reservation> reservations = (ArrayList)reservationsClientsMap.get("reservations");

        reservations.forEach( e -> System.out.println(e.getReservationId()));



    }
}
