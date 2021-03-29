package Delivery;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DeliveryController {
    private HashMap<String, Delivery> deliveries; // changes it to deliveries
    private String nextID = "A000";

    public DeliveryController(){
        deliveries = new HashMap<>();
    }

    public void createFullDelivery(String id, String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, Location origin, ArrayList<Task> destinations){
        deliveries.put(id ,new Delivery(id, date, timeOfDeparture, truckNumber, driverName, departureWeight, modification, origin, destinations));
    }

    public void addDriver(Driver dr, String delID){
        deliveries.get(delID).setDriver(dr);
    }

    public void storeDelivery(Delivery toStore){} // send to database

    // todo - delivery update mechanism {will do ID = 44546A, 44546B, 44546C ... }
    public Delivery updateDelivery(String delID){
        Delivery toStore = deliveries.remove(delID);
        storeDelivery(toStore);
        Delivery newDel = cloneDelivery(toStore);
        return null;
    } //todo decide if necessary

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
        if (splitted[1].equals("9999")) {
            int index = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(splitted[0]) + 1;
            try {
                nextID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(index) + "0000";
            }catch (IndexOutOfBoundsException e){
                System.out.println("overloaded system, resetting delivery ids");
            }
        }
        else{
            nextID = splitted[0] + String.format("%1$04d",Integer.parseInt(splitted[1])+1);
        }
        return ret;
    }


    public Delivery createNewDelivery(){
        return null;
    }

    public void addDate(String delID, String date){
        deliveries.get(delID);
    }




}
