package BuisnessLayer.DeliveryBusinessLayer;

import DataAccessLayer.DeliveryDataAccessLayer.DTO.*;
import BuisnessLayer.EmployeesBuisnessLayer.ResponseT;
import BuisnessLayer.EmployeesBuisnessLayer.ShiftController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public Response<String> toStringResponse() {
        return new Response<>("\n\n-----  System Current State:  -----\n"+
                "\nDeliveries=\n" + dec +
                "\nDrivers=\n" + drc.toString("\t") +
                "\nAreas=\n" + arc +
                "\nTasks=\n" + tac +
                "\nTrucks=\n" + trc.toString("\t")+"\n");
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

    public boolean containsArea(String areaName){
        return this.arc.containsArea(areaName);
    }

    public Response<Boolean> addLocation(AreaDTO areaDTO, LocationDTO locationDTO){
        return arc.addLocation(areaDTO, locationDTO);
    }

    public ArrayList<AreaDTO> getAreas() {
//        ArrayList<AreaDTO> ret = new ArrayList<>();
//        for (Area a: arc.getAreas())
//            ret.add(new AreaDTO(a));
        return arc.getAreas();
    }

    public LocationDTO getLocationByAddress(Response<String> address){
        return new LocationDTO(arc.getLocation(address.getData()));
    }

    // - Task -
    public String addTask(HashMap<String, Integer> listOfProduct, String loadingOrUnloading,
                        String Destination){
        Location destination = arc.getLocation(Destination);
        return this.tac.addTask(listOfProduct, loadingOrUnloading, destination);
    }

    public TaskDTO addTask(TaskDTO t){
        if (t.getId() == null){
            t.setId(addTask(t.getListOfProduct(),t.getLoadingOrUnloading(),t.getDestination().getAddress()));
            return t;
        }
        return null;

    }

    // - Truck -
    public void addTruck(TruckDTO truckDTO){
        this.trc.addTruck(truckDTO);
    }

    public boolean containsTruck(String id){
        return trc.containsTruck(id);
    }

    public ArrayList<TruckDTO> getTrucks(){
//        ArrayList<TruckDTO> ret = new ArrayList<>();
//        for (Truck t : trc.getTrucks())
//            ret.add(new TruckDTO(t));
        return trc.getTrucks();
    }

    // - Driver -
    public boolean containsDriver(String name){
        return this.drc.containsDriver(name);
    }

    // - Delivery -
    public DeliveryDTO getDeliveryById(String id){
        return new DeliveryDTO(this.dec.getDeliveryById(id));
    }

    public void sendDelivery(DeliveryDTO deliveryDTO, Response<Boolean> storeIt){
        this.dec.sendDelivery(deliveryDTO, storeIt.getData());
    }

    // todo - should be integrated with employee module to get all drivers
//    public ArrayList<tmpEmployee> getAllDriverEmployees(){
//        ArrayList<tmpEmployee> ret = new ArrayList<>();
//        tmpEmployee emp1 = new tmpEmployee("yanay the sunny",15000);
//        tmpEmployee emp2 = new tmpEmployee("nitzan the lary", 20000);
//        tmpEmployee emp3 = new tmpEmployee("shaul the shauly", 15000);
//        tmpEmployee emp4 = new tmpEmployee("david the davidy", 20000);
//        ret.add(emp1);
//        ret.add(emp2);
//        ret.add(emp3);
//        ret.add(emp4);
//        return ret;
//    }


    public void tempAddDriver(ArrayList<tmpEmployee> arr){
        this.drc.tmpAddDriver(arr);
    }


    public HashMap<String, ArrayList<LocationDTO>> getLocationsByAreas() {
//        HashMap<String, ArrayList<LocationDTO>> ret = new HashMap<>();
        HashMap<String, ArrayList<LocationDTO>> ret = arc.getLocationsByArea();
//        for (Area a : al.keySet()){
//            ArrayList<LocationDTO> locations = new ArrayList<>();
//            for (Location l: a.getLocations()){
//                locations.add(new LocationDTO(l));
//            }
//            ret.put(a.getAreaName(), locations);
//        }
        return ret;
    }


    public DeliveryDTO createFullDelivery(DeliveryDTO del){
        ArrayList<Task> tasks = new ArrayList<>();
        for (TaskDTO t: del.getDestinations())
            tasks.add(tac.getAndRemoveTaskById(t.getId(), del.getId()));
        del.setId(dec.createFullDelivery(del.getDate(),del.getTimeOfDeparture(),del.getTruckNumber(),del.getDriverName(),del.getDepartureWeight(),del.getModification(), arc.getLocation(del.getOrigin().getAddress()),tasks));
        return del;
    }

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
                t = tac.getAndRemoveTaskById(td.getId(), newDel.getId());
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

    public ArrayList<AreaDTO> getAreasData() {
        return this.arc.getAreas();
    }

    public ArrayList<DriverDTO> getDriversData() {
        return this.drc.getDriversData(); // todo - integration!
    }

    public LocalDate parseToLocalDate(String date){
        String[] d = date.split("-");
        return LocalDate.of(Integer.parseInt(d[0]) + 2000, Integer.parseInt(d[1]), Integer.parseInt(d[2]));
    }

    public LocalTime parseToLocalTime(String timeOfDeparture){
        String[] t = timeOfDeparture.split(":");
        return LocalTime.of(Integer.parseInt(t[0]), Integer.parseInt(t[1]), 0);
    }

    public ArrayList<DriverDTO> getDriversToTruck(TruckDTO ride, String date, String timeOfDeparture) {
        ArrayList<DriverDTO> ret = new ArrayList<>();
        LocalDate localDate = parseToLocalDate(date);
        LocalTime localTime = parseToLocalTime(timeOfDeparture);
        ResponseT<List <BuisnessLayer.EmployeesBuisnessLayer.Employee>> drivers = ShiftController.getInstance().getAllAssignedDrivers(localDate, localTime);
//        for (Employees.BuisnessLayer.Employee driver : drivers.getValue()){
//            if (driver.getLicenceType() >= ride.getTruckWeight())
//                ret.add(new DriverDTO(driver.getLicenceType(),driver.getName()));
//        }
        return ret;
    }



    public TruckDTO getTruckByDelivery(DeliveryDTO ddto) {
        return trc. getTruckByID(ddto.getTruckNumber());
    }

    public ArrayList<DeliveryDTO> getAllAppendingDeliveries() {
        ArrayList<DeliveryDTO> ret = new ArrayList<>();
        for (Delivery d:dec.getDeliveries().values())
            ret.add(new DeliveryDTO(d));
        return ret;
    }

    public ArrayList<DriverDTO> getAllDrivers() {
        ArrayList<DriverDTO> arr = new ArrayList<>();
        for (Driver driver : drc.getDrivers()){
            arr.add(new DriverDTO(driver));
        }
        return arr;
    }

    public ArrayList<DeliveryDTO> getDeliveriesData() {
        return dec.getDeliveriesData();
    }
}