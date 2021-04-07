package Delivery.BusinessLayer;

import Delivery.DTO.*;

import java.time.LocalDate;
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
    public void addNewArea(AreaDTO areaDTO){
        this.arc.addNewArea(areaDTO);

    }

    public void tempAddNewArea(String s, Area area){
        this.arc.addArea(s,area);
    }

    public boolean containsArea(String areaName){
        return this.arc.containsArea(areaName);
    }

    public void addLocation(AreaDTO areaDTO, LocationDTO locationDTO){
        arc.addLocation(areaDTO, locationDTO);
    }
//        public void addLocation(AreaPresentationInterfacle areaName, String address, String phoneNumber, String contactName){
//        arc.addLocation(areaName, address, phoneNumber, contactName);
//    }

//    public ArrayList<AreaPresentationInterface> getAreas() {
//        return new ArrayList<>(arc.getAreas());
//    }

    public ArrayList<AreaDTO> getAreas() {
        ArrayList<AreaDTO> ret = new ArrayList<>();
        for (Area a: arc.getAreas())
            ret.add(new AreaDTO(a));
        return ret;
    }

    public LocationDTO getLocationByAddress(Response<String> address){
        return new LocationDTO(arc.getLocation(address.getData()));
    }

    // - Task -
    public TaskDTO addTask(HashMap<String, Integer> listOfProduct, String loadingOrUnloading,
                        String Destination){
        Location destination = arc.getLocation(Destination);
        return this.tac.addTask(listOfProduct, loadingOrUnloading, destination);
    }

    public TaskDTO addTask(TaskDTO t){
        if (t.getId() == null){
            return addTask(t.getListOfProduct(),t.getLoadingOrUnloading(),t.getDestination().getAddress());
        }
        return null;
    }
    public Task getTaskById(String id){
        return this.tac.getTaskById(id);
    }

    public HashMap<String, Integer> makeProductLst(){return null;} // optional - possible to add this in separate

    // - Truck -
    public void addTruck(TruckDTO truckDTO){
        this.trc.addTruck(truckDTO);
    }

    public boolean containsTruck(String id){
        return trc.containsTruck(id);
    }

    // here instead of returning a list of trucks (which the CLI shouldn't know) i returning a list of the truck numbers.
    public ArrayList<TruckDTO> getTrucks(){
        ArrayList<TruckDTO> ret = new ArrayList<>();
        for (Truck t : trc.getTrucks())
            ret.add(new TruckDTO(t));
        return ret;
    }

    // - Driver -
    public boolean containsDriver(String name){
        return this.drc.containsDriver(name);
    }

    // - Delivery -
    public DeliveryDTO getDeliveryById(String id){
        return new DeliveryDTO(this.dec.getDeliveryById(id));
    }

    public Delivery getDeliveryByDate(String Date){return null;} // - optional -

    public void addTask2Delivery(String id){
        Task task = this.tac.getTaskById(id);
    }

    public void sendDelivery(DeliveryDTO deliveryDTO){
        this.dec.sendDelivery(deliveryDTO);
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
    public HashMap<String, ArrayList<LocationDTO>> getLocationsByAreas() {
        HashMap<String, ArrayList<LocationDTO>> ret = new HashMap<>();
        HashMap<Area, ArrayList<Location>> al = arc.getLocationsByArea();
        for (Area a : al.keySet()){
            ArrayList<LocationDTO> locations = new ArrayList<>();
            for (Location l: a.getLocations()){
                locations.add(new LocationDTO(l));
            }
            ret.put(a.getAreaName(), locations);
        }
        return ret;
    }

//    public void createFullDelivery(String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, String origin, ArrayList<String> destinations) {
//        Location locationOrigin = arc.getLocation(origin);
//        ArrayList<Task> tasks = new ArrayList<>();
//        for (String id: destinations) {
//            tasks.add(tac.getAndRemoveTaskById(id));
//        }
//        dec.createFullDelivery(date, timeOfDeparture, truckNumber, driverName, departureWeight, modification, locationOrigin, tasks);
//    }

    public void createFullDelivery(DeliveryDTO del){
        ArrayList<Task> tasks = new ArrayList<>();
        for (TaskDTO t: del.getDestinations())
            tasks.add(tac.getAndRemoveTaskById(t.getId()));
        dec.createFullDelivery(del.getDate(),del.getTimeOfDeparture(),del.getTruckNumber(),del.getDriverName(),del.getDepartureWeight(),del.getModification(), arc.getLocation(del.getOrigin().getAddress()),tasks);
    }

    public String getNextDeliveryID() {
        return dec.getNextDeliveryID();
    }


    // todo - transfer it to task controller!
    public ArrayList<TaskDTO> getTasks() {
        ArrayList<TaskDTO> ret = new ArrayList<>();
        for (Task t : tac.getTasks()) {
            ret.add(new TaskDTO(t));
        }
        return ret;
    }


    public ArrayList<DeliveryDTO> getUpdatableDeliveries() {
        ArrayList<DeliveryDTO> ret = new ArrayList<>();
        for (Delivery d:dec.getUpdatableDeliveries())
            ret.add(new DeliveryDTO(d));
        return ret;
    }

    public DeliveryDTO updateDelivery(DeliveryDTO newDel, String oldDelId) {
        ArrayList<Task> tasks = new ArrayList<>();
//        System.out.println("test - \n"+newDel.getDestinations()+"\n");
        for(TaskDTO td: newDel.getDestinations()){
            Task t = dec.getTasksFromDelivery(td.getId(), oldDelId);
            if (t==null)
                t = tac.getAndRemoveTaskById(td.getId());
            tasks.add(t);
        }
        Location orig = arc.getLocation(newDel.getOrigin().getAddress());
        newDel.setId(null);
        Delivery newD = dec.createNewDelivery(newDel, orig, tasks);
        return new DeliveryDTO(dec.updateDelivery(newD, oldDelId));
    }

    public Response<String> isLegalDepartureWeight(String input, DeliveryDTO deliveryDTO) {
        if (trc.getTruckByID(deliveryDTO.getTruckNumber()).getMaxWeight() <  Integer.parseInt(input))
            return new Response(input + " Exceeded");
        return new Response(input);
    }
}
