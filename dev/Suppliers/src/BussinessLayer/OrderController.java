package BussinessLayer;

import java.util.HashMap;
import java.util.LinkedList;

public class OrderController {

    private static OrderController orderController = null;

    public HashMap<Integer, Order> orders; // <OrderID: Integer, Order>
    public ProductController prodController;
    public int nextOrderID;

    private OrderController()
    {
        nextOrderID = 1;
        orders = new HashMap<>();
        prodController = prodController.getInstance();
    }

    public static OrderController getInstance()
    {
        if (orderController == null)
            orderController = new OrderController();

        return orderController;
    }

    // order functions:

    public int createOrder(int supplierID){
        int orderID = nextOrderID;
        nextOrderID++;
        HashMap<Integer,Integer> products = new HashMap<>();
        Order order = new Order(orderID,supplierID,false,products);
        orders.put(orderID,order);
        return orderID;
    }

    public void addProductToOrder(int orderID, int productID , int quantity) {
        orders.get(orderID).getProducts().put(productID, quantity);
    }

    public void removeFromOrder(int productID , int supplierID) {
    }

    public void removeOrder(int orderID) {
        Order order = orders.remove(orderID);
        if (order == null){
            throw new IllegalArgumentException("Order Does Not Exist");
        }
    }

    public void updateProdQuantity(int orderID, int productID, int quantity) {
        Order order = orders.get(orderID);
        order.getProducts().remove(productID);
        order.getProducts().put(productID,quantity);
    }

    public String showAllOrders(){
        String allOrders = '\n'+ "All Super-Lee Orders Are: " ;
        for(Order order : orders.values()){
            allOrders += '\n' + order.toString();
        }
        allOrders += '\n' + "Total Orders: " + orders.size();
        return  allOrders;

    }

    public Double finalPriceForOrder(int OrderID, int suppID){
        double finalPrice = 0;
        HashMap<Integer, Integer> products = orders.get(OrderID).getProducts();
        for(Integer productId : products.keySet()){
            int quantity = products.get(productId);
            finalPrice += prodController.calculateDiscount(productId, quantity, suppID);
        }
        orders.get(OrderID).setPrice(finalPrice);
        return finalPrice;
    }

    public String showOrder(int orderID){
        Order order = orders.get(orderID);
        if(order == null){throw new IllegalArgumentException("Order Does Not Exist");}
        return order.showAllDetails();
    }

    public boolean isEmptyOrder(int orderID){
        return orders.get(orderID).getProducts().isEmpty();
    }

}
