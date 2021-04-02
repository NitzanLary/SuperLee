package Delivery.BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class DriverController {
    HashMap<String, Driver> controller;

    public DriverController(){
        controller = new HashMap<String, Driver>();
    }

    public boolean containsDriver(String name){
        return this.controller.containsKey(name);
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
}
