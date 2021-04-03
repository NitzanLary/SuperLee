import Delivery.BusinessLayer.*;
import Delivery.PresentationLayer.CLI;

import java.util.ArrayList;

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
        cli.getFacade().addTruck("1234567","mer", 123,123);
        cli.getFacade().addTruck("12345678","mer", 123,123);
        ArrayList<tmpEmployee> arr = cli.getFacade().getAllDriverEmployees();
        cli.getFacade().tempAddDriver(arr);


        Location location1 = new Location("berechia 58", "086755143", "RAFA");
        Location location2 = new Location("Lavi", "0867123456", "DR GANOT");

        Area area = new Area("ISR");
        area.addLocation(location1);
        area.addLocation(location2);

        AreaController areaController = new AreaController();
        areaController.addArea(area.getAreaName(), area);
        cli.getFacade().tempAddNewArea(area.getAreaName(), area);
        cli.runWithConsole();
//        System.out.println(areaController);

        DeliveryController deliveryController = new DeliveryController();
        deliveryController.createFullDelivery("1/1/2000", "08:00", "12345", "YANAY", 15800, "", location1, new ArrayList<Task>());

        System.out.println(deliveryController);




    }
}
