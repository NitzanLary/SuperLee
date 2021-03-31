package Delivery;

import java.util.HashMap;

public class TruckController {
    HashMap<String, Truck> controller;

    public TruckController(){
        controller = new HashMap<String, Truck>();
    }

    @Override
    public String toString() {
        return "TruckController{" +
                "controller=" + controller +
                '}';
    }
}
