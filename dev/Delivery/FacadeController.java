package Delivery;

import javax.sound.midi.Soundbank;
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

    public void runWithConsole(){
        Scanner in = new Scanner(System.in);
        System.out.println("welcome to Delivery Module!");
        System.out.println("for exit the simulation type 'exit' as input at any point");
        String s = "";
        while (!s.equals("exit")){
            System.out.println("system current state:");
            System.out.println(toString());
            System.out.println("chose action:\n1 add new delivery,\n2 update existing delivery\n3 create new appending task" +
                    "\n4 add Truck to the sys\n5 add Driver to the sys\nadd Area to the sys\n7 add location to the sys");
            s = in.nextLine();

        }
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

    public ArrayList<String> insertLocation(){
        ArrayList<String> arr = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("address:");
        arr.add(in.nextLine());

        System.out.println("phone number:");
        arr.add(in.nextLine());

        System.out.println("contact name:");
        arr.add(in.nextLine());
        return arr;

    }

    public void insertNewTask(){

    }

    public void createDelivery(){
        Scanner in = new Scanner(System.in);
//        System.out.println("Choose the option for a date:");
//        System.out.println("1 for today's date");
//        System.out.println("2 for insert a date"); ---- optional - What you say?
        System.out.println("Insert date:");
        String date = in.nextLine();
        // need to put here constraint about legal date
        System.out.println("Insert time of departure:");
        String timeOfDeparture = in.nextLine();

        System.out.println("Insert truck number:");
        String truckNumber = in.nextLine();

        System.out.println("Insert driver name:");
        String driverName = in.nextLine();

        System.out.println("Insert departure weight:");
        String departureWeight = in.nextLine();

        System.out.println("Insert origin location:");
        ArrayList<String> originLocation = this.insertLocation();

        System.out.println("Insert Task");
        System.out.println("1 create new task");
        System.out.println("2 choose existed task");
        String op = in.nextLine();
        if (op == "1"){

        }


    }
}
