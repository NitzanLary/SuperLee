import Delivery.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        System.out.println("!Hello world");
//        DeliveryController dc = new DeliveryController();
//        for(int i = 0; i< 260000; i++)
//            System.out.println(dc.getNewDeliveryID());
//        //////////////////////////////////////

        FacadeController facadeController = new FacadeController();
        facadeController.runWithConsole();

        Location location1 = new Location("berechia 58", "086755143", "RAFA");
        Location location2 = new Location("Lavi", "0867123456", "DR GANOT");

        Area area = new Area("ISR");
        area.addLocation(location1);
        area.addLocation(location2);

        AreaController areaController = new AreaController();
        areaController.addArea(area.getAreaName());

        System.out.println(areaController.toString());
//        System.out.println(area.toString());

        DeliveryController deliveryController = new DeliveryController();
        deliveryController.createFullDelivery("1/1/2000", "08:00", "12345", "YANAY", 15800, "", location1, new ArrayList<Task>());

        System.out.println(deliveryController);


    }
}
