package BusinessLayer;

import java.time.LocalDate;

public class Item {
    private int id;
    //added price, expDate and cost that were not in the UML
    private String name;
    private LocalDate expDate;
    private Double price;
    private Double cost;
    private int shelfNum;
    private String manufacturer;
    private int shelfQuantity;
    private int storageQuantity;

    public Item(int id, String name, LocalDate expDate, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity) {
        this.id = id;
        this.name = name;
        this.expDate = expDate;
        this.price = price;
        this.cost = cost;
        this.shelfNum = shelfNum;
        this.manufacturer = manufacturer;
        this.shelfQuantity = shelfQuantity;
        this.storageQuantity = storageQuantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getCost() {
        return cost;
    }

    public LocalDate getExpDate() {
        return expDate;
    }



}
