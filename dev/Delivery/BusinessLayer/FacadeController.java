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

    public boolean containsTruck(String id){
        return trc.containsTruck(id);
    }

    // here instead of returning a list of trucks (which the CLI shouldn't know) i returning a list of the truck numbers.
    public ArrayList<String> getTrucks(){
        ArrayList<String> ret = new ArrayList<>();
        for (Truck t : trc.getTrucks())
            ret.add(t.getId()+"\t"+t.getModel()+"\t"+t.getMaxWeight()+"\t"+t.getTruckWeight());
        return ret;
    }

    // - Delivery -
    public Delivery getDeliveryById(String id){
        return this.dec.getDeliveryById(id);
    }

    public Delivery getDeliveryByDate(String Date){return null;} // - optional -



    public void insertNewTask(){

    }


    public ArrayList<String> getDrivers() {
        ArrayList<String> ret = new ArrayList<>();
        for (Driver d : drc.getDrivers())
            ret.add(d.getName()+"\t"+d.getLicenceType());
        return ret;
    }

    public HashMap<String, ArrayList<String>> getLocationsByAreas() {
        HashMap<String, ArrayList<String>> ret = new HashMap<>();
        HashMap<Area, ArrayList<Location>> al = arc.getLocationsByArea();
        for (Area a : al.keySet()){
            ArrayList<String> locations = new ArrayList<>();
            for (Location l: a.getLocations()){
                locations.add(l.getAddress());
            }
            ret.put(a.getAreaName(), locations);
        }
        return ret;
    }
}
