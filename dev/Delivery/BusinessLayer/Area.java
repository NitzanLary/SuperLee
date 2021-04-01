package Delivery.BusinessLayer;

import Delivery.Delivery;

import java.util.ArrayList;

public class Area {
    private ArrayList<Location> locations;
    private String areaName; // we didnt put this data type in the diagram - but its necessary

    public Area(String areaName){
        this.locations = new ArrayList<>();
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void addLocation(Location location){
        this.locations.add(location);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < locations.size() - 1; i++){
            str += locations.toString() + ", ";
        }
        str = str.substring(0, str.length() - 2);

        return areaName + ": " + str;
    }
}
