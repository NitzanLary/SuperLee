package Delivery.PresentationLayer;

import Delivery.BusinessLayer.Area;
import Delivery.BusinessLayer.FacadeController;

import javax.xml.parsers.ParserConfigurationException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.time.format.*;

public class CLI {
    FacadeController fc;

    public CLI() {
        fc = new FacadeController();
    }


    public void runWithConsole() {
        Scanner in = new Scanner(System.in);
        System.out.println("welcome to Delivery Module!");
        System.out.println("for exit the simulation type 'exit' as input at any point");
        String s = "";
        while (!s.equals("exit")) {
            System.out.println("system current state:");
            System.out.println(this.fc.toString());
            System.out.println("chose action:\n1 add new delivery,\n2 update existing delivery\n3 create new appending task" +
                    "\n4 add Truck to the sys\n5 add Driver to the sys\n6 add Area to the sys\n7 add location to the sys");
            s = in.nextLine().strip();
            chooseAction(s); // Todo: its tachles nees to be after the while, to the case that press immediate exit
        }

    }

    private void chooseAction(String s) {
        switch (s) {
            case ("1"): {
                this.createNewDelivery();
                break;
            }
            case ("2"): {

            }
            case ("3"): {
                this.addNewTask();
                break;
            }
            case ("4"): {
                this.addNewTrack();
                break;
            }
            case ("5"): {
                this.addNewDriver();
                break;
            }
            case ("6"): {
                this.addNewArea();
                break;
            }
            case ("7"): {
                this.addNewLocation();
                break;
            }
        }
    }

    private void addNewDriver() { // REMOVE - NOT NEED THIS FUCKSHIT
        System.out.println("helooo i'm here !!");
    }

    public boolean isLegalDate(String date) {
        try {
            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("d-M-uu")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String insertDate(Scanner in) {
        String date = "";
        do {
            System.out.println("Insert date: dd-mm-yy");
            date = in.nextLine();
        } while (!isLegalDate(date) && !date.equals("exit"));
        return date;
    }

    public String insertTimeOfDeparture(Scanner in) {
        String timeOfDeparture = "";
        do {
            System.out.println("Insert time of departure: hh:mm");
            timeOfDeparture = in.nextLine();
        } while (!isLegalTime(timeOfDeparture) && !timeOfDeparture.equals("exit"));
        return timeOfDeparture;
    }

    private String chooseTruck(Scanner in) {
        String inp = "";
        ArrayList<String> truckLst = fc.getTrucks();
        do {
            System.out.println("choose a truck for the delivery: ");
            for (int i = 1; i <= truckLst.size(); i++) {
                System.out.println(i + ") " + truckLst.get(i - 1));
            }
            inp = in.nextLine();
        } while (!isLegalChoice(truckLst.size(), inp) && !inp.equals("exit"));
        if (inp.equals("exit"))
            return inp;
        return truckLst.get(Integer.parseInt(inp)-1);
    }

    private boolean isLegalChoice(int size, String input) {
        int p = -1;
        try {
            p = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        if (p <= 0 || p > size)
            return false;
        return true;
    }

    public void createNewDelivery() {
        Scanner in = new Scanner(System.in);
        String date = insertDate(in);
        if (date.equals("exit"))
            return;

        String timeOfDeparture = insertTimeOfDeparture(in);
        if (timeOfDeparture.equals("exit"))
            return;

        String truck = chooseTruck(in);
        if (truck.equals("exit"))
            return;

        String driverName = chooseDriver(in);
        if (driverName.equals("exit"))
            return;

        String departureWeight = insertDepartureWeight(in);
        if (departureWeight.equals("exit"))
            return;
        int departureWeightInt = Integer.parseInt(departureWeight);

        String originLocation = chooseLocation(in);
        if (originLocation.equals("exit"))
            return;

        ArrayList<ArrayList<String>> arrTaskStr = insertTasksToDelivery(in);
        if (arrTaskStr == null)
            return;

        // summery:
        System.out.println("your creation:\n Delivery id - " + fc.getNextDeliveryID() + "\n Leaving at - " + date + " " + timeOfDeparture
                + "\n Truck - " + truck + "\n Driver - " + driverName + "\n Departure Weight - " + departureWeight + "\n From - " + originLocation
                + "\n Tasks - " + arrTaskStr.toString());
        String approve = "";
        System.out.println("Create the delivery? y/n");
        while (!(approve.equals("y") || approve.equals("n")))
            approve = in.nextLine();
        if (approve.equals("y")) {
            ArrayList<String> tasksForDelIDs = new ArrayList<>();
            for (ArrayList<String> al : arrTaskStr){
                tasksForDelIDs.add(al.get(0));
            }
            fc.createFullDelivery(date, timeOfDeparture, truck.split(" ")[0], driverName, departureWeightInt, "", originLocation, tasksForDelIDs);
        }
    }

    private ArrayList<ArrayList<String>> insertTasksToDelivery(Scanner in) {
        String op = "";
        ArrayList<ArrayList<String>> arrTaskStr = new ArrayList<>();
        ArrayList<ArrayList<String>> allTasks = fc.getTasks();
        while (!(op.equals("exit") || op.equals("3"))){
            System.out.println("Insert Task\n 1 create new task\n 2 choose existed task\n 3 continue");
            op = in.nextLine();
            if (op.equals("1")) {
                arrTaskStr.add(addNewTask());
            }else if (op.equals("2")) {
                if (fc.getTasks().size() == 0) {
                    System.out.println("no appending tasks in the system. please insert before choosing this option");
                    op = "not good";
                    continue;
                }
                ArrayList<String> chosen = chooseTask(in, allTasks);
                if (chosen != null) {
                    arrTaskStr.add(chosen); // [id, list, loading, address]
                    allTasks.remove(chosen);
                }
            }
        }
        if (op.equals("exit"))
            return null;
        return arrTaskStr;
    }

    private ArrayList<String> addNewTask(){
        ArrayList<String> task = this.userTaskCreator();
        HashMap<String, Integer> hashOfProduct = this.str2Hash(task.get(0));
        String loadingOrUnloading = task.get(1);
        String Destination = task.get(2);
        String id = this.fc.addTask(hashOfProduct, loadingOrUnloading, Destination);
        ArrayList<String> incID = new ArrayList<>();
        incID.add(id);
        incID.addAll(task);
        return task;
    }

    private ArrayList<String> chooseTask(Scanner in, ArrayList<ArrayList<String>> tasksLst) {
        String inp = "";
        do {
            if(tasksLst.size() == 0){
                System.out.println("no tasks available, insert new task");
                inp = "exit";
                continue;
            }
            System.out.println("choose a task for the delivery: ");
            for (int i = 1; i <= tasksLst.size(); i++) {
                System.out.println(i + ") " + tasksLst.get(i - 1));
            }
            inp = in.nextLine();
        } while (!isLegalChoice(tasksLst.size(), inp) && !inp.equals("exit"));
        if (inp.equals("exit"))
            return null;
        return tasksLst.get(Integer.parseInt(inp)-1);
    }

    public HashMap<String, Integer> str2Hash(String strOfProduct){
        String[] keyValuePairs = strOfProduct.split(",");              //split the string to create key-value pairs
        HashMap<String,Integer> map = new HashMap<>();

        for(String pair : keyValuePairs)                        //iterate over the pairs
        {
            String[] entry = pair.split(":");                   //split the pairs to get key and value

            map.put(entry[0].trim(), Integer.parseInt(entry[1].trim()));          //add them to the hashmap and trim whitespaces, parse to int
        }
        return map;
    }


    private String chooseLocation(Scanner in) {
        // todo - test it!
        String inp = "";
        String[] arrayInput;
        HashMap<String, ArrayList<String>> locationsByAreas = fc.getLocationsByAreas();
        HashMap<String, String> joinNumberToArea = new HashMap<>();
        boolean legal = false;
        do {
            System.out.println("choose the origin location for the delivery: <area number> <location number>");
            int i = 0;
            for (String a : locationsByAreas.keySet()) {
                ArrayList<String> locations = locationsByAreas.get(a);
                System.out.println(++i + ") " + a);
                joinNumberToArea.put(Integer.toString(i), a);
                for (int j = 1; j <= locations.size(); j++) {
                    System.out.println("\t" + j + ") " + locations.get(j - 1));
                }
            }
            inp = in.nextLine();
            arrayInput = inp.split(" ");
            if (arrayInput.length == 2) {
                if (isLegalChoice(locationsByAreas.size(), arrayInput[0])) {
                    if (isLegalChoice(locationsByAreas.get(joinNumberToArea.get(arrayInput[0])).size(), arrayInput[1]))
                        legal = true;
                }
            }
        } while (!legal);
        return locationsByAreas.get(joinNumberToArea.get(arrayInput[0])).get(Integer.valueOf(arrayInput[1]) - 1);
    }


    private boolean isLegalFloat(String input) {
        try {
            Long.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private String insertDepartureWeight(Scanner in) {
        String input = "";
        do {
            System.out.println("Insert legal departure weight:");
            input = in.nextLine();
        } while (!isLegalFloat(input));
        return input;
    }

    private String chooseDriver(Scanner in) {
        String inp = "";
        ArrayList<String> driversLst = fc.getDrivers();
        do {
            System.out.println("choose a driver for the delivery: ");
            for (int i = 1; i <= driversLst.size(); i++) {
                System.out.println(i + ") " + driversLst.get(i - 1));
            }
            inp = in.nextLine();
        } while (!isLegalChoice(driversLst.size(), inp) && !inp.equals("exit"));
        if (inp.equals("exit"))
            return inp;
        return driversLst.get(Integer.parseInt(inp) - 1);
    }

    private boolean isLegalTime(String timeOfDeparture) {
        String[] spl = timeOfDeparture.split(":");
        if (spl.length != 2)
            return false;
        if (!isLegalFloat(spl[0]) || !isLegalFloat(spl[1]))
            return false;
        int hour = Integer.parseInt(spl[0]);
        int minutes = Integer.parseInt(spl[1]);
        if (hour > 24 || hour < 0 || minutes > 59 || minutes < 0)
            return false;
        return true;
    }

    public ArrayList<String> addNewLocationHelper() {
        ArrayList<String> arr = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Insert new location:");
        System.out.println("address:");
        String address = in.nextLine();
        if (address.equals("exit"))
            return null;
        arr.add(address);
        String phoneNum = "";
        while(!(isLegalFloat(phoneNum) || phoneNum.length() == 10)) {
            System.out.println("phone number: <10 digits>");
            phoneNum = in.nextLine();
            if (phoneNum.equals("exit"))
                break;
        }
        arr.add(phoneNum);
        if (phoneNum.equals("exit"))
            return null;
        System.out.println("contact name:");
        String contactName = in.nextLine();
        if (contactName.equals("exit"))
            return null;
        arr.add(contactName);
        return arr;
    }

    public void addNewLocation(){
        // Todo: fix input -3 bug and input more than the size
        Scanner in = new Scanner(System.in);
        ArrayList<String> areas;
        String inp = "";
        String areaName = "";
        ArrayList<String> arr = this.addNewLocationHelper();
        if (arr == null)
            return;
        do {
            System.out.println("Choose an area name for the location:");
            areas = this.fc.getAreas();
            for (int i = 1; i <= areas.size(); i++){
                System.out.println(i + ") " + areas.get(i - 1));
            }
            inp = in.nextLine();
            areaName = areas.get(Integer.parseInt(inp));
        } while (!isLegalChoice(areas.size(), inp) && !inp.equals("exit"));
        if (inp.equals("exit"))
            return;
        this.fc.addLocation(areaName, arr.get(0), arr.get(1), arr.get(2));
    }

    public ArrayList<String> userTaskCreator() {
        ArrayList<String> arr = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        // TODO - NEED TO ADD NEW ID LIKE THE DELIVERY YOU MADE. BUT IN THE BUSINESS LAYER - @ASAF, LAVI, ISRAEL
        System.out.println("Insert list of product:");
        System.out.println("Please wright in the EXACT format:");
        System.out.println("<Product>:<Quantity>");
        System.out.println("For example: Banana:40,bread:30");
        String productStr = in.nextLine();
        arr.add(productStr);
        // TODO : NEED TO CHECK IF THE FORMAT ABOVE IS GOOD

        System.out.println("For loading press 1");
        System.out.println("For unloading press 2");
        String op = in.nextLine();
        while (!(op.equals("1") || op.equals("2") || op.equals("exit"))) {
            System.out.println("Please choose one of the option 1, 2 or exit");
            op = in.nextLine();
        }

        String loadOrUnload = "";
        switch (op) {
            case ("1"): {
                loadOrUnload = "loading";
                break;
            }
            case ("2"): {
                loadOrUnload = "unloading";
                break;
            }
        }
        arr.add(loadOrUnload);


//        System.out.println("choose location:");
//        ArrayList<String> originLocation = chooseLocation(in);
//        arr.add(originLocation.get(0));
//        arr.add(originLocation.get(1));
//        arr.add(originLocation.get(2));
        String destination = chooseLocation(in);
        arr.add(destination);

    // arr = [list of product, un\loading, location.address, location.phone number, location.contact name]
        return arr;
    }

    public void addNewTrack() {
        Scanner in = new Scanner(System.in);
        String truckNumber = "";
        do {
            System.out.println("insert truck number: xx-xxx-xx || xxx-xx-xxx");
            truckNumber = in.nextLine();
        } while (!(isLegalTruck(truckNumber) || truckNumber.equals("exit")));
        if (truckNumber.equals("exit"))
            return;

        String truckModel = "";
        do {
            System.out.println("insert truck model:");
            truckModel = in.nextLine();
        } while (!(truckModel.length()  != 0 || truckModel.equals("exit")));
        if (truckModel.equals("exit"))
            return;

        int maxWeight = 0;
        String input = "";
        do {
            System.out.println("insert truck's max weight: kg");
            input = in.nextLine();
        } while (!(isLegalFloat(input) || input.equals("exit")));
        if (input.equals("exit"))
            return;
        maxWeight = Integer.parseInt(input);

        int truckWeight = 0;
        do {
            System.out.println("insert truck's weight");
            input = in.nextLine();
        } while (!(isLegalFloat(input) || input.equals("exit")));
        if (input.equals("exit"))
            return;
        truckWeight = Integer.parseInt(input);
        fc.addTruck(truckNumber, truckModel, maxWeight, truckWeight);
    }

    private boolean isLegalTruck(String input) {
        input = input.replace("-", "");
        if (input.length() != 7 && input.length() != 8)
            return false;
        if (!isLegalFloat(input))
            return false;
        if (fc.containsTruck(input))
            return false;
        return true;
    }

    public FacadeController getFacade() {
        return fc;
    }

    public void addNewArea(){
        Scanner in = new Scanner(System.in);
        String areaName = "";
        do {
            System.out.println("Insert area name:");
            areaName = in.nextLine();
        } while (this.fc.containsArea(areaName) && !areaName.equals("exit"));
        if (areaName.equals("exit"))
            return;
        this.fc.addNewArea(areaName);
    }
    public void tempAddArea(String s, Area area){
        this.fc.tempAddNewArea(s, area);
    }


}
