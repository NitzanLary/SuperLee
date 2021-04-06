package Delivery.DTO;

import Delivery.BusinessLayer.Location;
import Delivery.BusinessLayer.Task;

import java.util.HashMap;

public class TaskDTO {
    private String id;
    private HashMap<String, Integer> listOfProduct;
    private String loadingOrUnloading;
    Location destination;

    public TaskDTO(String id, HashMap<String, Integer> listOfProduct, String loadingOrUnloading, Location destination){
        this.id = id;
        this.listOfProduct = listOfProduct;
        this.loadingOrUnloading = loadingOrUnloading;
        this.destination = destination;
    }

    public TaskDTO(Task task){
        this.id = task.getId();
        this.listOfProduct = task.getListOfProduct();
        this.loadingOrUnloading = task.getLoadingOrUnloading();
        this.destination = task.getDestination();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Integer> getListOfProduct() {
        return listOfProduct;
    }

    public void setListOfProduct(HashMap<String, Integer> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public String getLoadingOrUnloading() {
        return loadingOrUnloading;
    }

    public void setLoadingOrUnloading(String loadingOrUnloading) {
        this.loadingOrUnloading = loadingOrUnloading;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }
}
