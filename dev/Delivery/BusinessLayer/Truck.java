package Delivery.BusinessLayer;

public class Truck {
    private String id;
    private String model;
    private int maxWeight;
    private int truckWeight;

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setTruckWeight(int truckWeight) {
        this.truckWeight = truckWeight;
    }

    public Truck(String id, String model, int maxWeight, int truckWeight){
        this.id = id;
        this.model = model;
        this.maxWeight = maxWeight;
        this.truckWeight = maxWeight;
    }

    public String getId(){
        return id;
    }

    public String getModel(){
        return this.model;
    }

    public int getMaxWeight(){
        return maxWeight;
    }

    public int getTruckWeight(){
        return truckWeight;
    }

    public String toString(){
        String ret = "";
        ret.concat("id-"+id);
        ret.concat("\nmodel-"+model).concat("\nmaxWeight-"+maxWeight).concat("\ntruckWeight-"+truckWeight);
        return ret;
    }

}
