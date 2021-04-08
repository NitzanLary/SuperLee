package Delivery.BusinessLayer;

import Delivery.DTO.DriverDTO;
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

    public void addTruck(TruckDTO truckDTO){
        this.controller.put(truckDTO.getId(), new Truck(truckDTO));
        dataController.storeTruck(truckDTO);
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
        return controller.values().toString();
    }

    public Truck getTruckByID(String truckNumber) {
        for (Truck truck : controller.values()){
            if (truck.getId() == truckNumber)
                return truck;
        }
        return null;
    }

    public ArrayList<TruckDTO> getTruckData() {
        //        this.dc.getAreas().values();
        return new ArrayList<>(this.dataController.getTrucks().values());
    }
}
