package Delivery;

import java.util.HashMap;

public class TaskController {
    HashMap<String, Task> controller;


    public TaskController(){
        controller = new HashMap<String, Task>();
    }

    @Override
    public String toString() {
        return "TaskController{" +
                "controller=" + controller +
                '}';
    }
}
