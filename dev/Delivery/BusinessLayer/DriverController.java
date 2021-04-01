package Delivery.BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class DriverController {
    HashMap<String, Driver> controller;

    public DriverController(){
        controller = new HashMap<String, Driver>();
    }

    public void addDriverToSys(String name, int license){
        tmpEmployee e = new tmpEmployee(name, license);
        controller.put(name, new Driver(e));
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
