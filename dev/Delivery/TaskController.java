package Delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class TaskController {
    HashMap<String, Task> controller;


    public TaskController(){
        controller = new HashMap<String, Task>();
    }

    public void addTask(String id, HashMap<String, Integer> listOfProduct, String loadingOrUnloading, ArrayList<Location> destinations){
        controller.put(id, new Task(id, listOfProduct, loadingOrUnloading, destinations));
    }

    public Task getTaskById(String id){
        if (!this.controller.containsKey(id)){
            throw new InputMismatchException("Task dose not exist.");
        }
        return this.controller.get(id);
    }

    @Override
    public String toString() {
        return "TaskController{" +
                "controller=" + controller +
                '}';
    }
}
