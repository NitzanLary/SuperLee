package Delivery;

import java.util.ArrayList;

public class Delivery {
    private String id;
    private String date;
    private String timeOfDeparture;
    private String truckNumber;
    private String driverName;
    private int departureWeight;
    private String modification;
    private Location origin;
    private ArrayList<Task> destinations;

    public Delivery(String id, String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, Location origin, ArrayList<Task> destinations){
        this.id = id;
        this.date =date;
        this.timeOfDeparture = timeOfDeparture;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.departureWeight = departureWeight;
        this.modification = modification;
        this.origin = origin;
        this.destinations = destinations;
    }
}
