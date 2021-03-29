package Delivery;

import java.util.ArrayList;

public class DeliveryController {
    private Delivery delivery;

    public void createFullDelivery(String id, String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, Location origin, ArrayList<Task> destinations){
        Delivery delivery = new Delivery(id, date, timeOfDeparture, truckNumber, driverName, departureWeight, modification, origin, destinations);

    }
}
