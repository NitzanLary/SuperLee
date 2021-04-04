package Delivery.BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class Task {
    private String id;
    private HashMap<String, Integer>listOfProduct;
    private String loadingOrUnloading;
    Location destination;

    public Task(String id, HashMap<String, Integer> listOfProduct, String loadingOrUnloading, Location destination){
        this.id = id;
        this.listOfProduct = listOfProduct;
        this.loadingOrUnloading = loadingOrUnloading;
        this.destination = destination;
    }

    public Location getDestination() {
        return destination;
    }

    public String getId() {
        return id;
    }

    public String getLoadingOrUnloading() {
        return loadingOrUnloading;
    }

    public HashMap<String, Integer> getListOfProduct() {
        return listOfProduct;
    }

    public String toString(){
        return "id-"+this.id+"\nlist of products "+this.listOfProduct+"\nmission-"+this.loadingOrUnloading;
    }
}
