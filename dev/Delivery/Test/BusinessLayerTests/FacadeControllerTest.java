package Delivery.Test.BusinessLayerTests;

import Delivery.BusinessLayer.*;
import Delivery.DTO.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
// in the add fuction we check the gets function - so we dont need to implement those
// not sure if we can hold fields of other classes to use them
// cant run it - need to find a way why
// I think we need to add only CLITest. both CLITest and facadeTest cover every thing we need.

class FacadeControllerTest {
    FacadeController fc;
    DeliveryController dec;
    DriverController drc;
    AreaController arc;
    TaskController tac;
    TruckController trc;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fc = new FacadeController();
        dec = new DeliveryController();
        drc = new DriverController();
        arc = new AreaController();
        tac = new TaskController();
        trc = new TruckController();

        ArrayList<tmpEmployee> drivers = new ArrayList<>();
        drivers.add(new tmpEmployee("Nitzan the Lary", 20000));
        fc.tempAddDriver(drivers);
        AreaDTO testArea = new AreaDTO("TestArea");
        fc.addNewArea(testArea);
        TruckDTO testTruck = new TruckDTO("12345678","Mercedes", 20000,8000);
        fc.addTruck(testTruck);
        LocationDTO testKg = new LocationDTO("King-David 4, Kiryat-Gat", "052535606", "Asaf Stern");
        fc.addLocation(testArea, testKg);
        HashMap<String, Integer> task1Items = new HashMap<>();
        task1Items.put("6Cola",800);
        HashMap<String, Integer> task2Items = new HashMap<>();
        task2Items.put("eggsXL",500);
        task2Items.put("watermelon",20);
        TaskDTO task1 = new TaskDTO(task1Items,"unloading",testKg);
        TaskDTO task2 = new TaskDTO(task2Items,"loading",testKg);
        task1 = fc.addTask(task1);
        task2 = fc.addTask(task2);
        ArrayList<TaskDTO> destinations1 = new ArrayList<>();
        ArrayList<TaskDTO> destinations2 = new ArrayList<>();
        DeliveryDTO testDel1 = new DeliveryDTO("23-3-2021","8:00","4755857","Nitzan the Lary",20800,"",testKg ,destinations1);
        fc.createFullDelivery(testDel1);
        DeliveryDTO testDel2 = new DeliveryDTO("23-4-2021","16:00","34556123","Nitzan the Lary",0,"",testKg ,destinations2);
        fc.createFullDelivery(testDel2);

    }

//    @org.junit.jupiter.api.AfterEach
//    void tearDown() {
//    }

    @org.junit.jupiter.api.Test
    void addNewArea() {
        AreaDTO south = new AreaDTO("South");
        assertFalse(fc.getAreas().contains(south));
        fc.addNewArea(south);
        System.out.println(fc.getAreas());
//        assertTrue(fc.getAreas().get(0).getAreaName().equals("South"));

    }

    @org.junit.jupiter.api.Test
    void addLocation() {
        LocationDTO bs = new LocationDTO("Ben-Gurion 1, Beer Sheva", "0866445531", "Refael Farjun");
        AreaDTO south = new AreaDTO("South");
        fc.addNewArea(south);

        assertNotEquals(fc.getLocationByAddress(new Response<String>("Ben-Gurion 1, Beer Sheva") ), bs);
        fc.addLocation(south, bs);
        assertEquals(fc.getLocationByAddress(new Response<String>("Ben-Gurion 1, Beer Sheva") ), bs);
    }

//    @org.junit.jupiter.api.Test
//    void getAreas() {
//    }
//
//    @org.junit.jupiter.api.Test
//    void getLocationByAddress() {
//    }

    @org.junit.jupiter.api.Test
    void addTask() {
        LocationDTO dest = new LocationDTO("Omer 17, Omer", "086754921", "Nitzan Lary");
        HashMap<String, Integer> taskItems = new HashMap<>();
        taskItems.put("milk",40);
        taskItems.put("bread",25);
        TaskDTO task = new TaskDTO(taskItems,"loading",dest);
        assertFalse(fc.getTasks().contains(task));
        assertNull(fc.addTask(task).getId()); // check if there is NO an ID
        task = fc.addTask(task);
        assertTrue(fc.getTasks().contains(task));
        assertNotNull(task.getId()); // check if there is an ID
    }


    @org.junit.jupiter.api.Test
    void addTruck() {
        TruckDTO truck1 = new TruckDTO("4755857","Mercedes x", 15000,8000);
        assertFalse(fc.getTrucks().contains(truck1));
        fc.addTruck(truck1);
        assertTrue(fc.getTrucks().contains(truck1));
    }

//    @org.junit.jupiter.api.Test
//    void getTrucks() {
//    }
//
//    @org.junit.jupiter.api.Test
//    void getDeliveryById() {
//    }
//
    @org.junit.jupiter.api.Test
    void sendDelivery() {
        // add delivery and check if it in the data
        Delivery delivery = dec.getDeliveries().get("A000");
        DeliveryDTO deliveryDTO = new DeliveryDTO(delivery);
        fc.sendDelivery(deliveryDTO, new Response<>(true));
        assertTrue(fc.getDeliveryData().contains(deliveryDTO));
    }

    @org.junit.jupiter.api.Test
    void createFullDelivery() {
        LocationDTO orig = new LocationDTO("Ben-Gurion 1, Beer Sheva", "0866445531", "Refael Farjun");
        LocationDTO dest = new LocationDTO("Omer 17, Omer", "086754921", "Nitzan Lary");
        HashMap<String, Integer> taskItems = new HashMap<>();
        taskItems.put("milk",40);
        taskItems.put("bread",25);
        TaskDTO destinations = new TaskDTO(taskItems,"loading",dest);
        ArrayList<TaskDTO> arr = new ArrayList<>();
        arr.add(destinations);
        DeliveryDTO ddt = new DeliveryDTO("23-3-2021","12:00","12345678","Yanay Sun",15874,"",orig ,arr);

        ddt = fc.createFullDelivery(ddt);
        assertEquals(fc.getDeliveryById(ddt.getId()), ddt);
        assertFalse(fc.getDeliveryData().contains(ddt)); // False - because we insert new Delivery to data only when it send

    }

//    @org.junit.jupiter.api.Test
//    void getTasks() {
//    }
//
    @org.junit.jupiter.api.Test
    void getUpdatableDeliveries() {
        // add 2 delivery one is updateable and one isnt and check them
        Delivery delivery1 = dec.getDeliveries().get("A000");
        Delivery delivery2 = dec.getDeliveries().get("A001");
        assertTrue(fc.getUpdatableDeliveries().contains(delivery1));
        assertFalse(fc.getUpdatableDeliveries().contains(delivery2));
    }
//
//    @org.junit.jupiter.api.Test
//    void updateDelivery() {
//    }
//
    @org.junit.jupiter.api.Test
    void isLegalDepartureWeight() {
        Delivery delivery = dec.getDeliveries().get("A000");
        DeliveryDTO deliveryDTO = new DeliveryDTO(delivery);
        assertFalse(fc.isLegalDepartureWeight("1000000",deliveryDTO).getData().contains("Exceeded"));

    }
}