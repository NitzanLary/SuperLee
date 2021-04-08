package Delivery.BusinessLayer;

import Delivery.DTO.AreaDTO;
import Delivery.DTO.DriverDTO;
import Delivery.DataAccessLayer.DataController;

import java.util.ArrayList;
import java.util.HashMap;

public class DriverController {
    private HashMap<String, Driver> controller;
    private DataController dc = DataController.getInstance();

    public DriverController(){
        this.controller = new HashMap<String, Driver>();
//        tempController = new HashMap<>();
    }

    public boolean containsDriver(String name){
        return this.controller.containsKey(name);
    }

    public void tmpAddDriver(ArrayList<tmpEmployee> arr){
        for (tmpEmployee driver : arr){
            Driver d = new Driver(driver);
//            this.tempController.put(driver.getName(), driver);
            this.controller.put(driver.getName(),d);
        }
    }

    public String toString(String tabs) {
        String ret = "";
        for (Driver d: controller.values()){
            ret += tabs+d.getName()+" | "+d.getLicenceType()+"\n";
        }
        return  ret;
    }

    public ArrayList<Driver> getDrivers() {
        ArrayList<Driver> ret = new ArrayList<>();
        for (Driver d : controller.values()){
            ret.add(d);
        }
        return ret;
    }

    public ArrayList<DriverDTO> getDriversData() {
        //        this.dc.getAreas().values();
        ArrayList<DriverDTO> arr = new ArrayList<>(this.dc.getDrivers().values());
        return arr;
    }

//    public ArrayList<tmpEmployee> tmpGetDrivers() {
//        ArrayList<tmpEmployee> ret = new ArrayList<>();
//        for (tmpEmployee d : tempController.values()){
//            ret.add(d);
//        }
//        return ret;
}
