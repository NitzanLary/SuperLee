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

    public void createOrder(int supplierID){

    }

    public void addProductToOrder(int supplierID, int productID , int quantity) {
    }

    //public HashMap<Product, Integer> finishOrder() {}

    public void removeFromOrder(int productID , int supplierID) {
    }

    public void removeOrder() {
    }

    public void updateProdQuantity(int productID, int quantity) {
    }

    //public LinkedList<String> showAllOrders(){}

    //public LinkedList<String> showOrderBySupplier(int supplierID) { }

    //public Double finalPrice(){}
}
