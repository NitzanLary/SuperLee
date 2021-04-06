package Delivery.DTO;

public class TruckDTO {
    private String id;
    private String model;
    private int maxWeight;
    private int truckWeight;

    public TruckDTO(String id, String model, int maxWeight, int truckWeight){
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
}
