package Delivery;

import java.util.HashMap;

public class Task {
    private String id;
    private HashMap<String, Integer>listOfproduct;
    private String loadingOrunloading;

    public Task(String id, HashMap<String, Integer> listOfproduct, String loadingOrunloading){
        this.id = id;
        this.listOfproduct = listOfproduct;


    }
}
