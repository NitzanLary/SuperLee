package DataLayer.DTO;

import BussinessLayer.Inventory.Sale;

import java.time.LocalDateTime;

public class SaleDTO {
    private int itemID;
    private LocalDateTime date;
    private double price;
    private double cost;

    public SaleDTO(Sale s) {
        this.itemID = s.getItemId();
        this.date = s.getSaleDate();
        this.price = s.getSalePrice();
        this.cost = s.getItemCost();
    }

    public SaleDTO(int itemID, LocalDateTime date, double price, double cost) {
        this.itemID = itemID;
        this.date = date;
        this.price = price;
        this.cost = cost;
    }

    public int getItemID() {
        return itemID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public double getCost() {
        return cost;
    }
}
