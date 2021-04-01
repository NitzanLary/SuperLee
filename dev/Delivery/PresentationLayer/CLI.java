package Delivery.PresentationLayer;

import Delivery.BusinessLayer.FacadeController;

import java.util.ArrayList;
import java.util.Scanner;

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
        while (!(op.equals("1") || op.equals("2") || op.equals("exit"))){
            System.out.println("Please choose one of the option 1, 2 or exit");
            op = in.nextLine();
        }
        if (op.equals("1")){

        }


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
        System.out.println("For loading press 1:");
        arr.add(in.nextLine());

        System.out.println("contact name:");
        arr.add(in.nextLine());
        return arr;
    }

}
