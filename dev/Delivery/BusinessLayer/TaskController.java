package Delivery.BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class TaskController {
    HashMap<String, Task> controller;
    private String nextID = "A0000";


    public TaskController(){
        controller = new HashMap<String, Task>();
    }

    public String addTask(HashMap<String, Integer> listOfProduct, String loadingOrUnloading, Location destination){
        // TODO - NEED TO ADD NEW ID LIKE THE DELIVERY YOU MADE.
        String id = getNewTaskID();
        controller.put(id, new Task(id, listOfProduct, loadingOrUnloading, destination));
        return id;
    }

    public Task getTaskById(String id){
        if (!this.controller.containsKey(id)){
            throw new InputMismatchException("Task dose not exist.");
        }
        return this.controller.get(id);
    }

    public String getNewTaskID(){
        String ret = nextID;
        String[] splitted = {nextID.substring(0,1), nextID.substring(1)};
        if (splitted[1].equals("9999")) {
            int index = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(splitted[0]) + 1;
            try {
                nextID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(index) + "0000";
            }catch (IndexOutOfBoundsException e){
                System.out.println("overloaded system, resetting delivery ids");
            }
        }
        else{
            nextID = splitted[0] + String.format("%1$04d",Integer.parseInt(splitted[1])+1);
        }
        return ret;
    }

    public String getNextTaskID(){
        return nextID;
    }

    @Override
    public String toString() {
        return "TaskController{" +
                "controller=" + controller +
                '}';
    }

    public void storeTask(Task tsk){
        //todo
    }

    public Task getAndRemoveTaskById(String id) {
        Task ret = getTaskById(id);
        controller.remove(id);
        storeTask(ret);
        return ret;
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> ret = new ArrayList<>();
        for (Task t: controller.values())
            ret.add(t);
        return ret;
    }
}
