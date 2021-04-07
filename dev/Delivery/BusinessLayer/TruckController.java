package Delivery.BusinessLayer;

import Delivery.DTO.TruckDTO;
import Delivery.DataAccessLayer.DataController;

import java.util.ArrayList;
import java.util.HashMap;

public class TruckController {
    HashMap<String, Truck> controller;
    private DataController dataController;

    public TruckController(){
        controller = new HashMap<String, Truck>();
        dataController = DataController.getInstance();
    }

    public void addTruck(String id, String model, int maxWeight, int truckWeight){
        this.controller.put(id, new Truck(id, model, maxWeight, truckWeight));
//        dataController.storeTruck(new TruckDTO(id, str));
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

    public Truck getTruckByID(String truckNumber) {
        for (Truck truck : controller.values()){
            if (truck.getId() == truckNumber)
                return truck;
        }
        return null;
    }
}
