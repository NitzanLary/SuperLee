package Delivery.BusinessLayer;

import Delivery.DTO.Response;
import Delivery.DTO.TruckDTO;
import Delivery.DataAccessLayer.Mapper;
import Delivery.DataAccessLayer.TruckDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class TruckController {
    HashMap<String, Truck> controller;
    private TruckDAO dataController;
    private Mapper mapper = Mapper.getInstance();

    public TruckController(){
        controller = new HashMap<String, Truck>();
        dataController = TruckDAO.getInstance();
    }

    public void addTruck(TruckDTO truckDTO){
        this.controller.put(truckDTO.getId(), new Truck(truckDTO));
        dataController.storeTruck(truckDTO);
    }

    public ArrayList<TruckDTO> getTrucks(){
//        ArrayList<Truck> arr = new ArrayList();
//        for (String id : controller.keySet()){
//            arr.add(controller.get(id));
//        }
        return mapper.getTrucks();
    }

    public boolean containsTruck(String id){
        return mapper.containsTruck(new Response<String>(id)).getData();
    }

    @Override
    public String toString() {
        return controller.values().toString();
    }

    public Truck getTruckByID(String truckNumber) {
        for (Truck truck : controller.values()){
            if (truck.getId() == truckNumber)
                return truck;
        }
        return null;
    }

    public String toString(String tabs) {
        String ret = "";
        for (Truck t: controller.values()){
            ret += tabs + t.getModel()+" | "+t.getId()+" | "+t.getTruckWeight()+" | "+t.getMaxWeight()+"\n";
        }
        return ret;
    }

//    public ArrayList<TruckDTO> getTruckData() { // Todo add get trucks to the new database
//        return new ArrayList<>(this.dataController.getTrucks().values());
//    }
}
