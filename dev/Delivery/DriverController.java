package Delivery;

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
}
