package Delivery.BusinessLayer;

import Delivery.Delivery;

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
}
