import Delivery.BusinessLayer.*;
import Delivery.DTO.*;
import Delivery.PresentationLayer.CLI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("!Hello world");
//        DeliveryController dc = new DeliveryController();
//        for(int i = 0; i< 260000; i++)
//            System.out.println(dc.getNewDeliveryID());
//        //////////////////////////////////////
        CLI cli = new CLI();


        /////////////  legal date tests
//        System.out.println(cli.isLegalDate("31-7-20"));
//        System.out.println(cli.isLegalDate("31-6-20"));
//        System.out.println(cli.isLegalDate("31-5-20"));
//        System.out.println(cli.isLegalDate("31-4-20"));
//        System.out.println(cli.isLegalDate("31-3-20"));
//        System.out.println(cli.isLegalDate("30-2-20"));
//        System.out.println(cli.isLegalDate("31-7-21"));
//        System.out.println(cli.isLegalDate("31-6-21"));
//        System.out.println(cli.isLegalDate("31-5-21"));
//        System.out.println(cli.isLegalDate("31-4-21"));
//        System.out.println(cli.isLegalDate("31-3-21"));
//        System.out.println(cli.isLegalDate("31-2-21"));
        //////////// end date test
//        FacadeController facadeController = new FacadeController();
//        cli.addNewTrack();
//        cli.insertLocation();

        ////// test is Datable //////
//        Delivery d = new Delivery("1","6-4-21","18:30","1","1",1,"",new Location("1","1","1"),new ArrayList<Task>());
//        System.out.println(d.isUpdatable());
//        d = new Delivery("1","5-4-21","18:30","1","1",1,"",new Location("1","1","1"),new ArrayList<Task>());
//        System.out.println(d.isUpdatable());
//        d = new Delivery("1","7-4-21","18:30","1","1",1,"",new Location("1","1","1"),new ArrayList<Task>());
//        System.out.println(d.isUpdatable());
//        d = new Delivery("1","6-4-21","19:30","1","1",1,"",new Location("1","1","1"),new ArrayList<Task>());
//        System.out.println(d.isUpdatable());
//        System.out.println(d);
        //// end tes is updatable ///////
        AreaDTO areaDTONorth = new AreaDTO("North");
        AreaDTO areaDTOSouth = new AreaDTO("South");
        LocationDTO lavi = new LocationDTO("Lavi", "0503993502", "Asaf");
        LocationDTO berechia = new LocationDTO("Berechia", "0503993504", "Farjun");
        LocationDTO beerSheva = new LocationDTO("Beer Sheva", "0503993505", "Shaul the shauli");

        System.out.println("The system is ready to initiate!");
        System.out.println("Please choose the beginning state of the system:");
        System.out.println(" 1) with the first high-priority scenario");
        System.out.println(" 2) with the second high-priority scenario (Strongly Satisfy)");
        System.out.println(" 3) without initial state");
        Scanner in = new Scanner(System.in);
        String inp = in.nextLine();
        while (!inp.equals("1") && !inp.equals("2") && !inp.equals("3")){
            System.out.println("choose 1 2 or 3");
            inp = in.nextLine();
        }
        if (inp.equals("1")){
            initiateFirstScenario(cli);
        }
        if (inp.equals("2")){
            initiateSecondScenario(cli);
        }
        cli.runWithConsole();

//        cli.getFacade().addTruck("1234567","mer", 123,123);
//        cli.getFacade().addTruck("12345678","mer", 123,123);
//        cli.getFacade().addNewArea("North");
//        cli.getFacade().addNewArea("South");
//        cli.getFacade().addLocation("North","Lavi", "0503993502", "Asaf");
//        cli.getFacade().addLocation("South","Berechia", "0503993504", "Farjun");
//        cli.getFacade().addLocation("South","Beer Sheva", "0503993505", "Shaul the shauli");
//        ArrayList<tmpEmployee> arr = cli.getFacade().getAllDriverEmployees();
//        cli.getFacade().tempAddDriver(arr);
//        HashMap<String, Integer> t1 = new HashMap<>();
//        t1.put("c", 1);
//        t1.put("b", 10);
//        HashMap<String, Integer> t2 = new HashMap<>();
//        t2.put("d", 1);
//        t2.put("c3po", 100);
//        cli.getFacade().addTask(t1,"loading","Berechia");
//        cli.getFacade().addTask(t2,"loading","Beer Sheva");
//        cli.runWithConsole();


//        Location location1 = new Location("berechia 58", "086755143", "RAFA");
//        Location location2 = new Location("Lavi", "0867123456", "DR GANOT");
//
//        Area area = new Area("ISR");
//        area.addLocation(location1);
//        area.addLocation(location2);
//
//        AreaController areaController = new AreaController();
//        areaController.addArea(area.getAreaName(), area);
//        cli.getFacade().tempAddNewArea(area.getAreaName(), area);
//        cli.runWithConsole();
////        System.out.println(areaController);
//
//        DeliveryController deliveryController = new DeliveryController();
//        deliveryController.createFullDelivery("1/1/2000", "08:00", "12345", "YANAY", 15800, "", location1, new ArrayList<Task>());
//
//        System.out.println(deliveryController);




    }

    private static void initiateSecondScenario(CLI cli) {
        // trucks:
        TruckDTO truck1 = new TruckDTO("4755857","Mercedes x", 15000,8000);
        TruckDTO truck2 = new TruckDTO("34556123","Renault Fluence", 20000,8500);
//        TruckDTO truck2 = new TruckDTO("34556123","Renault Fluence", 20000,12000);
        cli.getFacade().addTruck(truck1);
        cli.getFacade().addTruck(truck2);
        // area % locations:
        AreaDTO south = new AreaDTO("South");
        cli.getFacade().addNewArea(south);
        LocationDTO bs = new LocationDTO("Ben-Gurion 1, Beer Sheva", "0866445531", "Refael Farjun");
        LocationDTO b = new LocationDTO("Brechia 22, Brechia", "0504536303", "Shaul");
        LocationDTO kg = new LocationDTO("King-David 4, Kiryat-Gat", "052535606", "Asaf Stern");
        cli.getFacade().addLocation(south, bs);
        cli.getFacade().addLocation(south, b);
        cli.getFacade().addLocation(south, kg);
        // drivers:
        ArrayList<tmpEmployee> drivers = new ArrayList<>();
        drivers.add(new tmpEmployee("Yanay the Sunny", 15000));
//        drivers.add(new tmpEmployee("Yanay the Sunny", 11000));
        drivers.add(new tmpEmployee("Nitzan the Lary", 20000));
        cli.getFacade().tempAddDriver(drivers);
        // tasks:
        HashMap<String, Integer> task1Items = new HashMap<>();
        task1Items.put("6Cola",800);
        HashMap<String, Integer> task2Items = new HashMap<>();
        task2Items.put("eggsXL",500);
        task2Items.put("watermelon",20);
        HashMap<String, Integer> task3Items = new HashMap<>();
        task3Items.put("milk",400);
        task3Items.put("Eden-Water",1200);
        HashMap<String, Integer> task4Items = new HashMap<>();
        task4Items.put("6Cola",800);
        task4Items.put("milk",400);
        task4Items.put("Eden-Water",1200);
        TaskDTO task1 = new TaskDTO(task1Items,"unloading",kg);
        TaskDTO task2 = new TaskDTO(task2Items,"loading",b);
        TaskDTO task3 = new TaskDTO(task3Items,"unloading",kg);
        TaskDTO task4 = new TaskDTO(task4Items,"unloading",kg);
        task1 = cli.getFacade().addTask(task1);
        task2 = cli.getFacade().addTask(task2);
        task3 = cli.getFacade().addTask(task3);
        task4 = cli.getFacade().addTask(task4);
        ArrayList<TaskDTO> destinations1 = new ArrayList<>();
        ArrayList<TaskDTO> destinations2 = new ArrayList<>();
        ArrayList<TaskDTO> destinations3 = new ArrayList<>();
        destinations1.add(task1);
        destinations2.add(task2);
        destinations2.add(task3);
        destinations3.add(task4);
        // deliveries:
//        DeliveryDTO ddt3 = new DeliveryDTO("23-3-2021","8:00","4755857","Yannay the Sunny",20800,"",bs ,destinations3);
//        cli.getFacade().createFullDelivery(ddt3);
//        DeliveryDTO ddt1 = new DeliveryDTO("23-3-2021","10:30","4755857","Yanay the Sunny",0,"",bs ,destinations1);
//        cli.getFacade().updateDelivery(ddt1, ddt3.getId());
        DeliveryDTO ddt3 = new DeliveryDTO("23-3-2021","8:00","4755857","Nitzan the Lary",20800,"",bs ,destinations1);
        cli.getFacade().createFullDelivery(ddt3);
        DeliveryDTO ddt2 = new DeliveryDTO("23-3-2021","16:00","34556123","Nitzan the Lary",0,"",bs ,destinations2);
        cli.getFacade().createFullDelivery(ddt2);
    }

    private static void initiateFirstScenario(CLI cli) {
        // trucks:
        TruckDTO truck = new TruckDTO("12345678","Mercedes", 20000,8000);
        cli.getFacade().addTruck(truck);
        // areas & locations:
        AreaDTO south = new AreaDTO("South");
        cli.getFacade().addNewArea(south);
        LocationDTO dest = new LocationDTO("Omer 17, Omer", "086754921", "Nitzan Lary");
        LocationDTO orig = new LocationDTO("Ben-Gurion 87, Beer Sheva", "086745833", "Yanay Shamshi");
        cli.getFacade().addLocation(south, dest);
        cli.getFacade().addLocation(south,orig);
        // drivers:
        ArrayList<tmpEmployee> drivers = new ArrayList<>();
        drivers.add(new tmpEmployee("Yanay Sun", 20000));
        cli.getFacade().tempAddDriver(drivers);
        // tasks:
        HashMap<String, Integer> taskItems = new HashMap<>();
        taskItems.put("milk",40);
        taskItems.put("bread",25);
        TaskDTO task = new TaskDTO(taskItems,"loading",dest);
        task = cli.getFacade().addTask(task);
        ArrayList<TaskDTO> destinations = new ArrayList<>();
        destinations.add(task);
        // destinations:
        DeliveryDTO ddt = new DeliveryDTO("23-3-2021","12:00","12345678","Yanay Sun",15874,"",orig ,destinations);
        cli.getFacade().createFullDelivery(ddt);
    }
}
