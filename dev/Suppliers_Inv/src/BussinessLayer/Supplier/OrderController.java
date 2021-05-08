package BussinessLayer.Supplier;

import DataLayer.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class OrderController {

    private static OrderController orderController = null;

    public HashMap<Integer, Order> orders; // <OrderID: Integer, Order>
    public HashMap<Integer, PeriodicOrder> periodicOrder; // <PeriodicOrderID: Integer, PeriodicOrder>
    public ProductController prodController;
    public int nextOrderID;
    public int nextPeriodOrderID;
    private Mapper mapper;

    private OrderController()
    {
        nextOrderID = 1;
        nextPeriodOrderID = 1;
        orders = new HashMap<>();
        periodicOrder = new HashMap<>();
        prodController = prodController.getInstance();
        mapper = Mapper.getInstance();
        mapper.loadOrders();
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

    public int createPeriodicOrder(int interval,LocalDate date){
        int orderID = nextPeriodOrderID;
        nextPeriodOrderID++;
        PeriodicOrder pOrder = new PeriodicOrder(orderID,date,interval);
        periodicOrder.put(orderID,pOrder);
        return orderID;
    }


    public void addProductToOrder(int orderID, int productID , int quantity) {
        orders.get(orderID).getProducts().put(productID, quantity);
    }

    public void addProductToPeriodicOrder(int orderID, int productID , int quantity) {
        periodicOrder.get(orderID).getProducts().put(productID, quantity);
    }

    public void removeFromOrder(int productID, int orderID) {
        Integer remove = orders.get(orderID).getProducts().remove(productID);
        if(remove == null){
            throw new IllegalArgumentException("The Item Does Not Exist In This Order");
        }
    }

    public void removeOrder(int orderID) {
        Order order = orders.remove(orderID);
        if (order == null){
            throw new IllegalArgumentException("Order Does Not Exist");
        }
    }

    public void removePOrder(int orderID) {
        PeriodicOrder order = periodicOrder.remove(orderID);
        if (order == null){
            throw new IllegalArgumentException("Order Does Not Exist");
        }
    }

    public void removePeriodicOrder(int orderID) {
        PeriodicOrder order = periodicOrder.remove(orderID);
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

    public String showAllPOrders(){
        String allOrders = '\n'+ "All Super-Lee Periodic Orders Are: " ;
        for(PeriodicOrder order : periodicOrder.values()){
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
        mapper.addOrder(orders.get(OrderID), finalPrice);
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

    public boolean isEmptyPOrder(int orderID){
        return periodicOrder.get(orderID).getProducts().isEmpty();
    }

    public void editMinQuantity(int supplierID, int pid, int newQ) {
        prodController.editMinQuantity(supplierID,pid,newQ);
    }

    public void editDiscount(int supplierID, int pid, int discount) {
        prodController.editDiscount(supplierID,pid,discount);
    }

    public void addProdToBill(int supplierID, int pid, int minQ, int discount) {
        mapper.addBillOfQuantity(supplierID,pid,minQ,discount);
        prodController.addProdToBill(supplierID,pid,minQ,discount);
    }

    public void removeProdFromBill(int supplierID, int pid) {
        prodController.removeProdFromBill(supplierID,pid);
    }

    public void productInOrder(int orderID, int prodID){
        boolean flag = orders.get(orderID).getProducts().containsKey(prodID);
        if (!flag)
            throw new IllegalArgumentException("This Product Does Not Exist In This Order, Deletion Failed");

    }

    public HashMap<Integer, Order> getOrders() {
        return orders;
    }

    public ProductController getProdController() {
        return prodController;
    }

    public HashMap<Integer, Integer> getProductOfporder(int orderID) {
        return periodicOrder.get(orderID).getProducts();
    }

    public boolean checkOrderPExist(int orderID) {
        return periodicOrder.containsKey(orderID);
    }

    public HashMap<Integer, PeriodicOrder> checkForApproachingPOrders() {
        LinkedList<PeriodicOrder> perOrds = mapper.loadPeriodic().value;
        HashMap<Integer, PeriodicOrder> orders = new HashMap<>();
        for(PeriodicOrder po : perOrds){
            LocalDate checkDate = po.getDateOfSupply().plusDays(po.getInterval()+1); // +1 represent check if the order is 24 hours before arriving time
            if(checkDate.equals(LocalDate.now()))
                orders.put(po.getpOrderID(),po);
        }
        //for each order that is 24 hours before delivering we create the order.
        return orders;
    }

    public void removeProdFromPOrder(int productID, int orderID) {
        Integer remove = periodicOrder.get(orderID).getProducts().remove(productID);
        if(remove == null){
            throw new IllegalArgumentException("The Product Does Not Exist In This Order");
        }
    }

    public void changeInterval(int interval, int orderID) {
        PeriodicOrder po = periodicOrder.get(orderID);
        if(po == null){
            throw new IllegalArgumentException("The Item Does Not Exist In This Order");
        }
        po.setInterval(interval);
    }

    public void editQuantityForPOrder(int orderID, int productID, int quant) {
        PeriodicOrder order = periodicOrder.get(orderID);
        if(order == null){
            throw new IllegalArgumentException("The Order Does Not Exist In This Order");
        }
        Integer check = order.getProducts().remove(productID);
        if(check == null){
            throw new IllegalArgumentException("The Product Does Not Exist In This Order");
        }
        order.getProducts().put(productID,quant);
    }
}
