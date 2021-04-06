package BusinessLayer;

import java.time.LocalDate;

public class Item {
    private int id;
    //added price, expDate and cost that were not in the UML
    private Double price;
    private Double cost;
    private String name;
    private LocalDate expDate;
    private int shelfNum;
    private String manifacturer;
    private int shelfQuantity;
    private int storageQuantity;

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
