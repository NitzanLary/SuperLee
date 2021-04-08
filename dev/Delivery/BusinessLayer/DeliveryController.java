package Delivery.BusinessLayer;

import Delivery.DTO.DeliveryDTO;
import Delivery.DataAccessLayer.DataController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DeliveryController {
    private HashMap<String, Delivery> deliveries; // changes it to deliveries
    private String nextID = "A000";
    private DataController dataController;

    public DeliveryController(){
        deliveries = new HashMap<>();
        dataController = DataController.getInstance();
    }

    public String createFullDelivery(String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, Location origin, ArrayList<Task> destinations){
        String id = getNewDeliveryID();
        deliveries.put(id ,new Delivery(id, date, timeOfDeparture, truckNumber, driverName, departureWeight, modification, origin, destinations));
        return id;
    }

    public void addDriver(Driver dr, String delID){
        deliveries.get(delID).setDriver(dr);
    }

    public void storeDelivery(Delivery toStore){
        DeliveryDTO deliveryDTO = new DeliveryDTO(toStore);
        this.dataController.storeDelivery(deliveryDTO);
    } // send to database todo

    public Delivery updateDelivery(Delivery newDel, String OldDelID){
        Delivery toStore = deliveries.remove(OldDelID);
        toStore.addModification("- newer "+newDel.getID()+" -");
        newDel.addModification("- older "+OldDelID+" -");
        storeDelivery(toStore);
//        Delivery newDel = cloneDelivery(toStore);
//        return null;
        return newDel;
    }

    public Delivery cloneDelivery(Delivery oldDel){
        //Delivery clone = OldDel.clone()
        // todo copy all data types
        //oldDel.makeOld(clone.getID()); <letter><num num num> 9999 z999
        // return clone
        return null;
    }

    public void storeDelivery(String delID){} // start with th line - Delivery newDel = updateDelivery(delID); newDel.set

    public void updateDeliveryDate(String delID, String newDate){}

    public void updateDeliveryTime(String delID, String newTime){}

    public void updateDeliveryTruckNumber(String delID, String newTrack){}

    public void updateDeliveryDriver(String delID, String newDriverName){}

    public void updateDeliveryDepartureWeight(String delID, int newWeight){}

    public void updateDeliveryOrigin(String delID, Location newOrig){}

    public void insertDeliveryTasks(String deID, ArrayList<Task> newTasks){} //Delivery newDel = updateDelivery(delID); newDel.set

    public void removeDeliveryTasks(String delID, ArrayList<Task> remTasks){}

    public void replaceDeliveryTasks(String delID, ArrayList<String> TaskToChange, ArrayList<Task> newTask){
        /*
        Delivery updated = updateDelivery(String delID);
        Delivery newDel = cloneDelivery(delID);
        newDel.removeTask
        deliveries[delID].remove
         */
    }

    public String getNewDeliveryID(){
        String ret = nextID;
        String[] splitted = {nextID.substring(0,1), nextID.substring(1)};
        if (splitted[1].equals("999")) {
            int index = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(splitted[0]) + 1;
            try {
                nextID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(index) + "000";
            }catch (IndexOutOfBoundsException e){
                System.out.println("overloaded system, resetting delivery ids");
            }
        }
        else{
            nextID = splitted[0] + String.format("%1$03d",Integer.parseInt(splitted[1])+1);
        }
        return ret;
    }

    public String getNextDeliveryID(){
        return nextID;
    }


    public Delivery createNewDelivery(DeliveryDTO newDel, Location origin, ArrayList<Task> destinations){
        if (newDel.getId() == null)
            newDel.setId(getNewDeliveryID());
        Delivery ret = new Delivery(newDel.getId(),newDel.getDate(),newDel.getTimeOfDeparture(),newDel.getTruckNumber(),newDel.getDriverName(),newDel.getDepartureWeight(),newDel.getModification(),origin,destinations);
        deliveries.put(newDel.getId(), ret);
        return ret;
    }

    public void addDate(String delID, String date){
        deliveries.get(delID);
    }

    public Delivery getDeliveryById(String id){
        if (!this.deliveries.containsKey(id)){
            throw new InputMismatchException("Delivery dose not exist.");
        }
        return this.deliveries.get(id);
    }

    public void createDelivery(){
        String newId = this.getNewDeliveryID();


    }

    @Override
    public String toString() {
        return "\n\t" + deliveries +
                '}';
    }

    public ArrayList<Delivery> getUpdatableDeliveries() {
        ArrayList<Delivery> ret = new ArrayList<>();
        for (Delivery d: deliveries.values()){
            if(d.isUpdatable())
                ret.add(d);
        }
        return ret;
    }

    public LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public LocalDateTime getCurrentTime(){
        return LocalDateTime.now();
    }

    public Task getTasksFromDelivery(String id, String oldDelId) {
        ArrayList<Task> ta = deliveries.get(oldDelId).getDestinations();
        for (Task t: ta){
            if (t.getId().equals(id))
                return t;
        }
        return null;
    }

    public void sendDelivery(DeliveryDTO deliveryDTO, boolean storeIt) {
        Delivery delivery = this.deliveries.get(deliveryDTO.getId());
        delivery.setDepartureWeight(deliveryDTO.getDepartureWeight());
        if (storeIt) {
            this.deliveries.remove(deliveryDTO.getId());
            storeDelivery(delivery);
        }
    }
}
