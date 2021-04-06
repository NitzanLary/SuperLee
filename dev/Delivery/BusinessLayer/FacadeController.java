package Delivery.BusinessLayer;

import Delivery.PresentationLayer.AreaPresentationInterface;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void addNewArea(String areaName){
        this.arc.addNewArea(areaName);
    }

    public void tempAddNewArea(String s, Area area){
        this.arc.addArea(s,area);
    }

    public boolean containsArea(String areaName){
        return this.arc.containsArea(areaName);
    }

    public void addLocation(String areaName, String address, String phoneNumber, String contactName){
        arc.addLocation(areaName, address, phoneNumber, contactName);
    }
//        public void addLocation(AreaPresentationInterface areaName, String address, String phoneNumber, String contactName){
//        arc.addLocation(areaName, address, phoneNumber, contactName);
//    }

//    public ArrayList<AreaPresentationInterface> getAreas() {
//        return new ArrayList<>(arc.getAreas());
//    }

    public ArrayList<String> getAreas() {
        ArrayList<String> ret = new ArrayList<>();
        for (Area a: arc.getAreas())
            ret.add(a.getAreaName());
        return ret;
    }

    // - Task -
    public String addTask(HashMap<String, Integer> listOfProduct, String loadingOrUnloading,
                        String Destination){
        Location destination = arc.getLocation(Destination);
        return this.tac.addTask(listOfProduct, loadingOrUnloading, destination);
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

    // - Driver -
    public boolean containsDriver(String name){
        return this.drc.containsDriver(name);
    }

    // - Delivery -
    public Delivery getDeliveryById(String id){
        return this.dec.getDeliveryById(id);
    }

    public Delivery getDeliveryByDate(String Date){return null;} // - optional -

    public void addTask2Delivery(String id){
        Task task = this.tac.getTaskById(id);


    }

    // todo - should be integrated with employee module to get all drivers
    public ArrayList<tmpEmployee> getAllDriverEmployees(){
        ArrayList<tmpEmployee> ret = new ArrayList<>();
        tmpEmployee emp1 = new tmpEmployee("yanay the sunny",15000);
        tmpEmployee emp2 = new tmpEmployee("nitzan the lary", 20000);
        tmpEmployee emp3 = new tmpEmployee("shaul the shauly", 15000);
        tmpEmployee emp4 = new tmpEmployee("david the davidy", 20000);
        ret.add(emp1);
        ret.add(emp2);
        ret.add(emp3);
        ret.add(emp4);
        return ret;
    }
    public void tempAddDriver(ArrayList<tmpEmployee> arr){
        this.drc.tmpAddDriver(arr);
    }


    public void insertNewTask(){

    }


    public ArrayList<String> getDrivers() {
        ArrayList<String> ret = new ArrayList<>();
//        for (Driver d : drc.getDrivers())
        for (tmpEmployee d : drc.tmpGetDrivers())
            ret.add(d.getName()+"\t"+d.getLicenceType());
        return ret;
    }

    // todo - transfer it to area controller!
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

    public void createFullDelivery(String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, String origin, ArrayList<String> destinations) {
        Location locationOrigin = arc.getLocation(origin);
        ArrayList<Task> tasks = new ArrayList<>();
        for (String id: destinations) {
            tasks.add(tac.getAndRemoveTaskById(id));
        }
        dec.createFullDelivery(date, timeOfDeparture, truckNumber, driverName, departureWeight, modification, locationOrigin, tasks);
    }

    public String getNextDeliveryID() {
        return dec.getNextDeliveryID();
    }


    // todo - transfer it to task controller!
    public ArrayList<ArrayList<String>> getTasks() {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        for (Task t : tac.getTasks()) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(t.getId());
            arr.add(t.getDestination().getAddress());
            arr.add(t.getLoadingOrUnloading());
            arr.add(t.getListOfProduct().toString());
            ret.add(arr);
        }
        return ret;
    }


    public ArrayList<String> getUpdatableDeliveries() {
        ArrayList<String> ret = new ArrayList<>();
        for (Delivery d:dec.getUpdatableDeliveries())
            ret.add(d.getID());
        return ret;
    }
}
