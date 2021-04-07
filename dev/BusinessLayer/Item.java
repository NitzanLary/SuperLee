package BusinessLayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private List<Discount> discounts;

    public Item(int id, String name, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.shelfNum = shelfNum;
        this.manufacturer = manufacturer;
        this.shelfQuantity = shelfQuantity;
        this.storageQuantity = storageQuantity;
        discounts = new LinkedList<>();
    }

    public void addDiscount(Discount d) {
        discounts.add(d);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        double currPrice = price;
        for(Discount dis : discounts) {
            LocalDate now = LocalDate.now();
            if (now.isAfter(dis.getStart()) && now.isBefore(dis.getEnd()))
                currPrice -= currPrice*dis.getDiscountPr()/100;
        }
        return currPrice;
    }

    public Double getCost() {
        return cost;
    }


}
