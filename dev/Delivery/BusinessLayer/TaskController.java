package Delivery.BusinessLayer;

import Delivery.DTO.Response;
import Delivery.DTO.TaskDTO;
import Delivery.DataAccessLayer.Mapper;
import Delivery.DataAccessLayer.TaskDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class TaskController {
    HashMap<String, Task> controller;
    private String nextID = "A0000";
    private TaskDAO dc = TaskDAO.getInstance();
    private Mapper mapper = Mapper.getInstance();


    public TaskController(){
        controller = new HashMap<String, Task>();
    }

    public String addTask(HashMap<String, Integer> listOfProduct, String loadingOrUnloading, Location destination){
        String id = getNewTaskID();
        Task newTask = new Task(id, listOfProduct, loadingOrUnloading, destination);
        storeTask(newTask, null);
        controller.put(id, newTask);
        return id;
//        return new TaskDTO(newTask);
    }

    public Task getTaskById(String id){
        if (this.controller.containsKey(id)){
            return this.controller.get(id);

        }
        throw new InputMismatchException("Task dose not exist.");
//        return new Task(mapper.getTaskByID(id));
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
        ArrayList<String> destin = new ArrayList<>();
        for (Task t: controller.values()){
            destin.add("\n"+t.toString("\t")+"\n");
        }
        String destinStr = destin.toString().substring(1,destin.toString().length()-1);
        return destinStr;
    }

    public void storeTask(Task tsk,String delId){
        dc.storeTask(new TaskDTO(tsk), new Response<>(delId));
    }

    public Task getAndRemoveTaskById(String taskId, String delId) {
        Task ret = getTaskById(taskId);
        controller.remove(taskId);
        dc.updateTask(new TaskDTO(ret), new Response<>(delId));
        return ret;
    }
    // TODO: getTasks is from the DAL or from the controller ???
    public ArrayList<Task> getTasks() {
        ArrayList<Task> ret = new ArrayList<>();
        for (Task t: controller.values())
            ret.add(t);
        return ret;
    }
//
//    public ArrayList<TaskDTO> getTasksData() {
//        //        this.dc.getAreas().values();
//        return new ArrayList<>(this.dc.getTasks().values());
//    }
}
