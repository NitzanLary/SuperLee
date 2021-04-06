package BussinessLayer;

import java.util.HashMap;

public class Order {
    private int orderID;
    private int supplierID;
    private boolean delivered;
    private HashMap<Integer,Integer> products; // <productID: Integer, quantity: Integer>
    private double price;

    //TODO: how to calculate price
    public Order(int orderID, int supplierID, boolean delivered, HashMap<Integer,Integer> products){
        this.orderID = orderID;
        this.supplierID = supplierID;
        this.delivered = delivered;
        this.products = products;
        this.price = price;
    }
}
