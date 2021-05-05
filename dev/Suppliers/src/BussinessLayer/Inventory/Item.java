package BussinessLayer.Inventory;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Item {
    private int id;
    //added price, expDate and cost that were not in the UML
    private String name;
    private Double price;
    private Double cost;
    private int shelfNum;
    private String manufacturer;
    private int shelfQuantity;
    private int storageQuantity;
    private List<Discount> priceDiscounts;
    private List<Discount> costDiscounts;
    private int minAlert;


    public Item(int id, String name, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity, int minAlert) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.shelfNum = shelfNum;
        this.manufacturer = manufacturer;
        this.shelfQuantity = shelfQuantity;
        this.storageQuantity = storageQuantity;
        priceDiscounts = new LinkedList<>();
        costDiscounts = new LinkedList<>();
        this.minAlert = minAlert;
    }

    public boolean addToStorage(int amount) {
        storageQuantity += amount;
        return true;
    }

    public boolean removeFromShelf(int amount) {
        if (amount > shelfQuantity)
            return false;
        shelfQuantity -= amount;
        return true;
    }

    public boolean removeFromStorage(int amount) {
        if (amount > storageQuantity)
            return false;
        storageQuantity -= amount;
        return true;
    }

    public boolean moveToShelf(int amount) {
        if (amount > storageQuantity) {
            return false;
        }
        storageQuantity -= amount;
        shelfQuantity += amount;
        return true;
    }

    public boolean changeShelf(int newShelf) {
        shelfNum = newShelf;
        return true;
    }

    public void addPriceDiscount(Discount d) {
        priceDiscounts.add(d);
    }

    public void addCostDiscount(Discount d) {
        costDiscounts.add(d);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        double currPrice = price;
        for(Discount dis : priceDiscounts) {
            LocalDate now = LocalDate.now();
            if (now.isAfter(dis.getStart()) && now.isBefore(dis.getEnd()))
                currPrice -= currPrice*dis.getDiscountPr()/100;
        }
        return currPrice;
    }

    public Double getCost() {
        double currCost = cost;
        for(Discount dis : costDiscounts) {
            LocalDate now = LocalDate.now();
            if (now.isAfter(dis.getStart()) && now.isBefore(dis.getEnd()))
                currCost -= currCost*dis.getDiscountPr()/100;
        }
        return currCost;
    }

    public String toString(String tabs) {
        String s = tabs + "Id: " + id +
                "\n" + tabs + "Name: " + name +
                "\n" + tabs + "Shelf Num: " + shelfNum +
                "\n" + tabs + "Manufacturer: " + manufacturer +
                "\n" + tabs + "Quantity: " + (shelfQuantity + storageQuantity) +
                "\n" + tabs + "Shelf Quantity: " + shelfQuantity +
                "\n" + tabs + "Storage Quantity: " + storageQuantity + "\n";
        if (shelfQuantity+storageQuantity <= minAlert)
            s += tabs + "Item is below the minimum that was set by: " + (minAlert-(shelfQuantity+storageQuantity)) + "\n";
        return s;
    }


}
