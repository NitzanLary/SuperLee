package Delivery.BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class TaskController {
    HashMap<String, Task> controller;
    // because we cant hold a tasks array in the cli (dont know Task, only facade)
    // maybe we hold here a arr of task that going to be added to a new delivery throw the facade that know Task
    ArrayList<Task> arrTask;


    public TaskController(){
        controller = new HashMap<String, Task>();
    }

    public String addTask(HashMap<String, Integer> listOfProduct, String loadingOrUnloading, ArrayList<String> originDestination){
        // TODO - NEED TO ADD NEW ID LIKE THE DELIVERY YOU MADE.
        String id = "temp id";
        controller.put(id, new Task(id, listOfProduct, loadingOrUnloading, originDestination));
        return id;
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
