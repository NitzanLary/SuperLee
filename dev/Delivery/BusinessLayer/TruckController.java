package Delivery.BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class TruckController {
    HashMap<String, Truck> controller;

    public TruckController(){
        controller = new HashMap<String, Truck>();
    }

    public void addTruck(String id, String model, int maxWeight, int truckWeight){
        this.controller.put(id, new Truck(id, model, maxWeight, truckWeight));
    }

    public ArrayList<Truck> getTrucks(){
        ArrayList<Truck> arr = new ArrayList();
        for (String id : controller.keySet()){
            arr.add(controller.get(id));
        }
        return arr;
    }

    public boolean containsTruck(String id){
        return controller.containsKey(id);
    }

    @Override
    public String toString() {
        return "TruckController{" +
                "controller=" + controller +
                '}';
    }
}
