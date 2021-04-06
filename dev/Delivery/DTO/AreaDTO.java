package Delivery.DTO;

import Delivery.BusinessLayer.Area;
import Delivery.BusinessLayer.Location;

import java.util.ArrayList;

public class AreaDTO {
    private ArrayList<LocationDTO> locations;

    public AreaDTO(Area a){
        locations = new ArrayList<>();
        for (Location l : a.getLocations()){
            locations.add(new LocationDTO(l));
        }
    }

    public ArrayList<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<LocationDTO> locations) {
        this.locations = locations;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    private String areaName; // we didnt put this data type in the diagram - but its necessary

    public AreaDTO(String areaName){
        this.locations = new ArrayList<>();
        this.areaName = areaName;
    }
}
