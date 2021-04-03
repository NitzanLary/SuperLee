package Delivery.BusinessLayer;

import Delivery.BusinessLayer.Delivery;

import java.util.ArrayList;
import java.util.Collection;

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
        if (str.equals(""))
            return str;
        str = str.substring(0, str.length() - 1);

        return areaName + ": " + str;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
