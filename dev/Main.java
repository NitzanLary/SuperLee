import Delivery.BusinessLayer.*;
import Delivery.DTO.AreaDTO;
import Delivery.DTO.LocationDTO;
import Delivery.DTO.TruckDTO;
import Delivery.PresentationLayer.CLI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

        cli.getFacade().addTruck(new TruckDTO("1234567","mer", 123,123));
        cli.getFacade().addTruck(new TruckDTO("12345678","mer", 123,123));
        cli.getFacade().addNewArea(areaDTONorth);
        cli.getFacade().addNewArea(areaDTOSouth);
        cli.getFacade().addLocation(areaDTONorth, lavi);
        cli.getFacade().addLocation(areaDTOSouth,berechia);
        cli.getFacade().addLocation(areaDTOSouth, beerSheva);
        ArrayList<tmpEmployee> arr = cli.getFacade().getAllDriverEmployees();
        cli.getFacade().tempAddDriver(arr);
        HashMap<String, Integer> t1 = new HashMap<>();
        t1.put("c", 1);
        t1.put("b", 10);
        HashMap<String, Integer> t2 = new HashMap<>();
        t2.put("d", 1);
        t2.put("c3po", 100);
        cli.getFacade().addTask(t1,"loading","Berechia");
        cli.getFacade().addTask(t2,"loading","Beer Sheva");
        cli.runWithConsole();


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
}
