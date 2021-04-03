package Delivery.PresentationLayer;

import Delivery.BusinessLayer.Area;
import Delivery.BusinessLayer.FacadeController;

import javax.xml.parsers.ParserConfigurationException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.*;
import java.util.SplittableRandom;

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
                this.createDelivery();
                break;
            }
            case ("2"): {

            }
            case ("3"): {

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
        if (p < 0 || p > size)
            return false;
        return true;
    }

    public void createDelivery() {
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
        int departureWeightInt = Integer.parseInt(insertDepartureWeight(in));


//        System.out.println("Insert origin location:");
//        ArrayList<String> originLocation = this.insertLocation();
        String originLocation = chooseLocation(in);
        if (originLocation.equals("exit"))
            return;

        System.out.println("Insert Task");
        System.out.println("1 create new task");
        System.out.println("2 choose existed task");
        String op = in.nextLine();
        while (!(op.equals("1") || op.equals("2") || op.equals("exit"))) {
            System.out.println("Please choose one of the option 1, 2 or exit");
            op = in.nextLine();
        }
        if (op.equals("1")) {
            ArrayList<String> arrTaskStr = this.addNewTask();
            HashMap<String, Integer> hashOfProduct = this.str2Hash(arrTaskStr.get(0));
            String loadingOrUnloading = arrTaskStr.get(1);
            ArrayList<String> originDestination = new ArrayList<>();
            originDestination.add(arrTaskStr.get(2));
            originDestination.add(arrTaskStr.get(3));
            originDestination.add(arrTaskStr.get(4));

            String id = this.fc.addTask(hashOfProduct, loadingOrUnloading, originDestination);
            this.fc.addTask2Delivery(id);
        }


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

    // todo : discuss with asaf about it - 1. why not first choose an area than choose location from it
                                   //todo: 2. did NOT test it yet - better it will be you
    private String chooseLocation(Scanner in) {
        // this function should ask for location choice from the following format:
        // 1) area1
        //   1) location1 of this area
        //   2) locations2
        //   3) location3
        // 2) area2
        //   1) first of area 2
        //
        // and the input should be 1 2 (second location of first area)
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
        int hour = Integer.parseInt(spl[0]);
        int minutes = Integer.parseInt(spl[1]);
        System.out.println(hour + " " + minutes);
        if (hour > 24 || hour < 0 || minutes > 59 || minutes < 0)
            return false;
        return true;
    }

    public ArrayList<String> insertLocation() {
        ArrayList<String> arr = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Insert new location:");
        System.out.println("address:");
        arr.add(in.nextLine());

        System.out.println("phone number:");
        arr.add(in.nextLine());

        System.out.println("contact name:");
        arr.add(in.nextLine());
        return arr;
    }

    public void addNewLocation(){
        Scanner in = new Scanner(System.in);
        String areaName = "";
        ArrayList<String> arr = this.insertLocation();
        do {
            System.out.println("Insert an area name for the location:");
            areaName = in.nextLine();
        } while (!(this.fc.containsArea(areaName) || areaName.equals("exit")));
        if (areaName.equals("exit"))
            return;
        this.fc.addLocation(areaName, arr.get(0), arr.get(1), arr.get(2));




    }

    public ArrayList<String> addNewTask() {
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


        System.out.println("Insert location:");
        ArrayList<String> originLocation = this.insertLocation();
        arr.add(originLocation.get(0));
        arr.add(originLocation.get(1));
        arr.add(originLocation.get(2));

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
        } while (!(truckModel.length() < 1 || truckModel.equals("exit")));
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
