package Delivery.DataAccessLayer;

import Delivery.BusinessLayer.Area;
import Delivery.BusinessLayer.Delivery;
import Delivery.BusinessLayer.Driver;
import Delivery.BusinessLayer.Task;
import Delivery.DTO.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DataController {
    private static DataController instance = null;
    private HashMap<String,DeliveryDTO> deliveries;
    private HashMap<String,TaskDTO> tasks;
    private HashMap<String,AreaDTO> areas;
    private HashMap<String,TruckDTO> trucks;
    private HashMap<String,DriverDTO> drivers;

    private DataController(){
        deliveries = new HashMap<>();
        tasks = new HashMap<>();
        areas = new HashMap<>();
        trucks = new HashMap<>();
        drivers = new HashMap<>();
    }

    public static DataController getInstance(){
        if (instance == null)
            instance = new DataController();
        return instance;
    }

    public void storeDelivery(DeliveryDTO delivery){
        this.deliveries.put(delivery.getId(), delivery);
    }

    public void storeTruck(TruckDTO truckDTO){
        this.trucks.put(truckDTO.getId(), truckDTO);
    }

    public void storeTask(TaskDTO taskDTO){
        this.tasks.put(taskDTO.getId(), taskDTO);
    }

    public void storeArea(AreaDTO areaDTO){
        this.areas.put(areaDTO.getAreaName(), areaDTO);
    }

    public void storeLocation(AreaDTO areaDTO, LocationDTO locationDTO){
        areas.get(areaDTO.getAreaName()).addLocation(locationDTO);
    }
}
