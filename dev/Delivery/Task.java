package Delivery;

import java.util.ArrayList;
import java.util.HashMap;

public class Task {
    private String id;
    private HashMap<String, Integer>listOfProduct;
    private String loadingOrUnloading;
    ArrayList<Location> destination;

    public Task(String id, HashMap<String, Integer> listOfProduct, String loadingOrUnloading, ArrayList<Location> destinations){
        this.id = id;
        this.listOfProduct = listOfProduct;
        this.loadingOrUnloading = loadingOrUnloading;
        this.destination = destinations;
    }

    public String toString(){
        return "id-"+this.id+"\nlist of products "+this.listOfProduct+"\nmission-"+this.loadingOrUnloading;
    }
}
