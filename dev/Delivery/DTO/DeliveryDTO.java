package Delivery.DTO;

import Delivery.BusinessLayer.Delivery;
import Delivery.BusinessLayer.Location;
import Delivery.BusinessLayer.Task;

import java.util.ArrayList;

public class DeliveryDTO {
    private String id;
    private String date;
    private String timeOfDeparture;
    private String truckNumber;
    private String driverName;
    private int departureWeight;
    private String modification;
    private Location origin;
    private ArrayList<Task> destinations;


     public DeliveryDTO(String id, String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, Location origin, ArrayList<Task> destinations) {
        this.id = id;
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.departureWeight = departureWeight;
        this.modification = modification; // " - old g121 -- new g123 -"
        this.origin = origin;
        this.destinations = destinations;
    }
}
