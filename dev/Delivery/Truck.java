package Delivery;

public class Truck {
    private String id;
    private String model;
    private int maxWeight;
    private int truckWeight;

    public Truck(String id, String model, int maxWeight, int truckWeight){
        this.id = id;
        this.model = model;
        this.maxWeight = maxWeight;
        this.truckWeight = maxWeight;
    }

    public String toString(){
        String ret = "";
        ret.concat("id-"+id);
        ret.concat("\nmodel-"+model).concat("\nmaxWeight-"+maxWeight).concat("\ntruckWeight-"+truckWeight);
        return ret;
    }

}
