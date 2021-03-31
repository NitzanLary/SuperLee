package Delivery;

import java.util.HashMap;

public class Task {
    private String id;
    private HashMap<String, Integer>listOfproduct;
    private String loadingOrUnloading;

    public Task(String id, HashMap<String, Integer> listOfProduct, String loadingOrUnloading){
        this.id = id;
        this.listOfproduct = listOfproduct;
        this.loadingOrUnloading = loadingOrUnloading;
    }

    public String toString(){
        return "id-"+this.id+"\nlist of products "+this.listOfproduct+"\nmission-"+this.loadingOrUnloading;
    }
}
