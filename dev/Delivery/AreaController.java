package Delivery;

import java.util.ArrayList;
import java.util.HashMap;

public class AreaController {
    private HashMap<String, Area> controller;

    public AreaController(){
        this.controller = new HashMap<>();
    }

    public void addArea(Area area){
        controller.put(area.getAreaName(), area);
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
