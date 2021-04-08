package PresentationLayer.DTO;

import BusinessLayer.Discount;

import java.util.List;

public class PItem {
    private int id;
    private String name;
    private Double price;
    private Double cost;
    private int shelfNum;
    private String manufacturer;
    private int shelfQuantity;
    private int storageQuantity;
    private int minAlert;
    public PItem(int id, String name, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity,int minAlert, int storageQuantity, String catName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.shelfNum = shelfNum;
        this.manufacturer = manufacturer;
        this.shelfQuantity = shelfQuantity;
        this.shelfQuantity = storageQuantity;
        this.minAlert = minAlert;
    }

    public PItem(int id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
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

    public int getMinAlert() {
        return minAlert;
    }

    public int getShelfNum() {
        return shelfNum;
    }

    public int getShelfQuantity() {
        return shelfQuantity;
    }

    public int getStorageQuantity() {
        return storageQuantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setMinAlert(int minAlert) {
        this.minAlert = minAlert;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShelfNum(int shelfNum) {
        this.shelfNum = shelfNum;
    }

    public void setShelfQuantity(int shelfQuantity) {
        this.shelfQuantity = shelfQuantity;
    }

    public void setStorageQuantity(int storageQuantity) {
        this.storageQuantity = storageQuantity;
    }
}
