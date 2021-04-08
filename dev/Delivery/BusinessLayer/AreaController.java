package Delivery.BusinessLayer;

import Delivery.BusinessLayer.Delivery;
import Delivery.DTO.AreaDTO;
import Delivery.DTO.LocationDTO;
import Delivery.DataAccessLayer.DataController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Set;

public class AreaController {
    private HashMap<String, Area> controller;
    private DataController dc = DataController.getInstance();

    public AreaController(){
        this.controller = new HashMap<>();

    }

    public boolean containsArea(String areaName){
        return this.controller.containsKey(areaName);
    }

    public void addNewArea(AreaDTO areaDTO){
        controller.put(areaDTO.getAreaName(), new Area(areaDTO.getAreaName()));
        dc.storeArea(areaDTO);
    }

    public void addArea(String areaName, Area area){
        controller.put(areaName, area);
    }

    public void addLocation(AreaDTO areaDTO, LocationDTO locationDTO){
        if (!this.controller.containsKey(areaDTO.getAreaName())){
            throw new InputMismatchException("Area name dose not exist.");
        }
        controller.get(areaDTO.getAreaName()).addLocation(new Location(locationDTO.getAddress(), locationDTO.getPhoneNumber(), locationDTO.getContactName()));
        dc.storeLocation(areaDTO, locationDTO);
    }

//    public void addLocation(AreaPresentationInterface areaName, String address, String phoneNumber, String contactName){
//        if (!this.controller.containsKey(areaName.getName())){
//            throw new InputMismatchException("Area name dose not exist.");
//        }
//        controller.get(areaName.getName()).addLocation(new Location(address, phoneNumber, contactName));
//    }

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

    public Location getLocation(String destination) {
        for (Area a : controller.values()){
            for (Location l: a.getLocations()){
                if (l.getAddress().equals(destination)){
                    return l;
                }
            }
        }
        return null;
    }

    public ArrayList<Area> getAreas() {
        ArrayList<Area> arr = new ArrayList<>();
        for (Area areaName : controller.values()){
            arr.add(areaName);
        }
        return arr;
    }

    public ArrayList<AreaDTO> getAreasData() {
        ArrayList<AreaDTO> arr = new ArrayList<>();
//        this.dc.getAreas().values();
        for (AreaDTO area : this.dc.getAreas().values()){
            arr.add(area);
        }
        return arr;
    }
}
