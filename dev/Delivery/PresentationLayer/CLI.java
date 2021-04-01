package Delivery.PresentationLayer;

import Delivery.BusinessLayer.FacadeController;

import javax.xml.parsers.ParserConfigurationException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.*;

public class CLI {
    FacadeController fc;

    public CLI(){
        fc = new FacadeController();
    }


    public void runWithConsole(){
        Scanner in = new Scanner(System.in);
        System.out.println("welcome to Delivery Module!");
        System.out.println("for exit the simulation type 'exit' as input at any point");
        String s = "";
        while (!s.equals("exit")){
            System.out.println("system current state:");
            System.out.println(this.fc.toString());
            System.out.println("chose action:\n1 add new delivery,\n2 update existing delivery\n3 create new appending task" +
                    "\n4 add Truck to the sys\n5 add Driver to the sys\nadd Area to the sys\n7 add location to the sys");
            s = in.nextLine().strip();
            chooseAction(s);
        }
    }

    private void chooseAction(String s) {
        switch (s){
            case ("1"):{
                this.createDelivery();
            }
            case ("2"):{

            }
            case ("3"):{

            }
            case ("4"):{

            }
            case ("5"):{

            }
            case ("6"):{

            }
            case ("7"):{

            }
        }
    }

    public boolean isLegalDate(String date){
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

    private String insertDate(Scanner in){
        String date = "";
        do {
            System.out.println("Insert date: dd-mm-yy");
            date = in.nextLine();
        } while (!isLegalDate(date) || !date.equals("exit"));
        return date;
    }

    public String insertTimeOfDeparture(Scanner in){
        String timeOfDeparture = "";
        do {
            System.out.println("Insert time of departure: hh:mm");
            timeOfDeparture = in.nextLine();
        } while (!isLegalTime(timeOfDeparture) || !timeOfDeparture.equals("exit"));
        return timeOfDeparture;
    }

    private String chooseTruck(Scanner in){
        String inp = "";
        ArrayList<String> truckLst = fc.getTrucks();
        do {
            System.out.println("choose a truck for the delivery: ");
            for (int i = 1; i <= truckLst.size(); i++) {
                System.out.println(i + ") " + truckLst.get(i - 1));
            }
            inp = in.nextLine();
        }while (isLegalChoice(truckLst.size(), inp));
        return truckLst.get(Integer.parseInt(inp));
    }

    private boolean isLegalChoice(int size, String input) {
        int p = -1;
        try {
            p = Integer.parseInt(input);
        }catch (NumberFormatException e){
            return false;
        }
        if (p < 0 || p > size)
            return false;
        return true;
    }

    public void createDelivery(){
        Scanner in = new Scanner(System.in);
//        System.out.println("Choose the option for a date:");
//        System.out.println("1 for today's date");
//        System.out.println("2 for insert a date"); ---- optional - What you say? i say no
        String date = insertDate(in);
        String timeOfDeparture = insertTimeOfDeparture(in);

        // farj, im changing here the handling. the user chose from list instead of inserting a pre-known data. asaf.
//        System.out.println("Insert truck number:");
//        String truckNumber = in.nextLine();

        String truck = chooseTruck(in);
        String driverName = chooseDriver(in);
        // todo - need to continue here to make every action as a method. so it can be ahnded correctly.

        System.out.println("Insert departure weight:");
        String departureWeight = in.nextLine();

        System.out.println("Insert origin location:");
        ArrayList<String> originLocation = this.insertLocation();

        System.out.println("Insert Task");
        System.out.println("1 create new task");
        System.out.println("2 choose existed task");
        String op = in.nextLine();
        while (!(op.equals("1") || op.equals("2") || op.equals("exit"))){
            System.out.println("Please choose one of the option 1, 2 or exit");
            op = in.nextLine();
        }
        if (op.equals("1")){

        }


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
        }while (isLegalChoice(driversLst.size(), inp));
        return driversLst.get(Integer.parseInt(inp));
    }

    private boolean isLegalTime(String timeOfDeparture) {
        String[] spl = timeOfDeparture.split(":");
        if (spl.length != 2)
            return false;
        int hour = Integer.parseInt(spl[0]);
        int minutes = Integer.parseInt(spl[0]);
        if (hour > 24 || hour < 0 || minutes > 59 || minutes < 60)
            return false;
        return true;
    }

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

    public ArrayList<String> insertTask(){
        ArrayList<String> arr = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        // TODO - NEED TO ADD NEW ID LIKE THE DELIVERY YOU MADE - @ASAF, LAVI, ISRAEL
        System.out.println("Insert list of product:");
        System.out.println("Please wright in the EXACT format:");
        System.out.println("<Product>:<Quantity>");
        System.out.println("For example: Banana:40,bread:30");
        String productStr = in.nextLine();
        // TODO : NEED TO CHECK IF THE FORMAT IS GOOD
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
            }
            case ("2"): {
                loadOrUnload = "unloading";
            }
        }
//        arr.add(in.nextLine());
        System.out.println("Insert location:");
        ArrayList<String> originLocation = this.insertLocation();
//        arr.add(in.nextLine());
        // Todo - should we return an array or void??? 


        return arr;
    }

}
