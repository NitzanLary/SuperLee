package Delivery.BusinessLayer;

import Delivery.DataAccessLayer.DataController;

import java.util.ArrayList;
import java.util.HashMap;

public class DriverController {
    private HashMap<String, Driver> controller;
    private HashMap<String, tmpEmployee> tempController;// REMOVE - TEMP !
    private DataController dc = DataController.getInstance();

    public DriverController(){
        this.controller = new HashMap<String, Driver>();
        tempController = new HashMap<>();

    }

    public boolean containsDriver(String name){
        return this.controller.containsKey(name);
    }

    public void tmpAddDriver(ArrayList<tmpEmployee> arr){
        for (tmpEmployee driver : arr){
            this.tempController.put(driver.getName(), driver);

        }
    }

    @Override
    public String toString() {
        return "DriverController{" +
                "controller=" + controller +
                '}';
    }

    public ArrayList<Driver> getDrivers() {
        ArrayList<Driver> ret = new ArrayList<>();
        for (Driver d : controller.values()){
            ret.add(d);
        }
        return ret;
    }

    public ArrayList<tmpEmployee> tmpGetDrivers() {
        ArrayList<tmpEmployee> ret = new ArrayList<>();
        for (tmpEmployee d : tempController.values()){
            ret.add(d);
        }
        return ret;
    }
}
