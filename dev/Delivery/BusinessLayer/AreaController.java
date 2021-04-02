package Delivery.BusinessLayer;

import Delivery.BusinessLayer.Delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class AreaController {
    private HashMap<String, Area> controller;

    public AreaController(){
        this.controller = new HashMap<>();

    }

    public void addArea(String area){
        controller.put(area, new Area(area));
    }

    public void addLocation(String areaName, String address, String phoneNumber, String contactName){
        if (!this.controller.containsKey(areaName)){
            throw new InputMismatchException("Area name dose not exist.");
        }
        controller.get(areaName).addLocation(new Location(address, phoneNumber, contactName));
    }

    @Override
    public String toString() {
        String str = "";
        for (String areaName : controller.keySet()){
            str += controller.get(areaName).toString() + "\n";
        }
        return str;

    }

    public HashMap<Area, ArrayList<Location>> getLocationsByArea() {
        HashMap<Area, ArrayList<Location>> ret = new HashMap<>();
        for(Area a : controller.values()){
            ret.put(a, a.getLocations());
        }
        return ret;
    }
}
