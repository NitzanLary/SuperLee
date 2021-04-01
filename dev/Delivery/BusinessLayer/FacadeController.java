package Delivery.BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FacadeController {
    DeliveryController dec;
    DriverController drc;
    AreaController arc;
    TaskController tac;
    TruckController trc;

    public FacadeController(){
        dec = new DeliveryController();
        drc = new DriverController();
        arc = new AreaController();
        tac = new TaskController();
        trc = new TruckController();
    }

    @Override
    public String toString() {
        return "Deliveries=" + dec +
                "\nDrivers=" + drc +
                "\nAreas=" + arc +
                "\nTasks=" + tac +
                "\nTrucks=" + trc +
                '}';
    }

    // - Area -
    public void addArea(String areaName){
        this.arc.addArea(areaName);

    }

    public void addlocation(String areaName, String address, String phoneNumber, String contactName){
        arc.addLocation(areaName, address, phoneNumber, contactName);
    }

    // - Task -
    public void addTask(String id, HashMap<String, Integer> listOfProduct, String loadingOrUnloading, ArrayList<Location> destination){
        this.tac.addTask(id, listOfProduct, loadingOrUnloading, destination);
    }
    public Task getTaskById(String id){
        return this.tac.getTaskById(id);
    }

    public HashMap<String, Integer> makeProductLst(){return null;} // optional - possible to add this in separate

    // - Truck -
    public void addTruck(String id, String model, int maxWeight, int truckWeight){
        this.trc.addTruck(id, model, maxWeight, truckWeight);
    }

    public ArrayList<Truck> getTrucks(){
        return this.trc.getTrucks();
    }

    // - Delivery -
    public Delivery getDeliveryById(String id){
        return this.dec.getDeliveryById(id);
    }

    public Delivery getDeliveryByDate(String Date){return null;} // - optional -



    public void insertNewTask(){

    }


}
