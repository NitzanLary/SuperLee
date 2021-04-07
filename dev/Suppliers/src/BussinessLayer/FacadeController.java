package BussinessLayer;
import java.util.HashMap;
import java.util.LinkedList;


// Singleton
public class FacadeController {

    private static FacadeController facadeController = null;

    private SupplierController supController;
    private OrderController orderController;
    //private HashMap<Integer, LinkedList<Order>> supplierOrder;
    private HashMap<Integer, LinkedList<Integer>> supplierOrder;


    private FacadeController() {
        supController = SupplierController.getInstance();
        orderController = OrderController.getInstance();
        supplierOrder = new HashMap<>();
    }

    public static FacadeController getInstance() {
        if (facadeController == null)
            facadeController = new FacadeController();

        return facadeController;
    }


    /***************** Supplier & Products Functions: ***************/

    public void createSupCard(String supplierName, int supplierID, String address, String email, int bankAcc,
                              String paymentMethod, String contacts, String infoSupplyDay, boolean pickUp) {
        supController.createSupCard(supplierName, supplierID, address, email, bankAcc, paymentMethod, contacts, infoSupplyDay, pickUp);
    }

    public void deleteSupCard(int supplierID){
        supController.deleteSupCard(supplierID);
    }

    public void addBillOfQuantity(int supplierID, HashMap<Integer, Integer> minQuantityForDis,  HashMap<Integer, Integer> discountList) {
        supController.addBillOfQuantity(supplierID, minQuantityForDis,discountList);
    }

    public void deleteBillOfQuantity(int supplierID) {
        supController.deleteBillOfQuantity(supplierID);
    }

    public void editMinQuantity(int supplierID, int pid, int newQ) {
        orderController.editMinQuantity(supplierID,pid,newQ);
    }

    public void editDiscount(int supplierID, int pid, int discount) {
        orderController.editDiscount(supplierID,pid,discount);
    }

    public void addProdToBill(int supplierID, int pid, int minQ, int discount) {
        orderController.addProdToBill(supplierID,pid,minQ,discount);
    }

    public void removeProdFromBill(int supplierID, int pid) {
        orderController.removeProdFromBill(supplierID,pid);
    }

    public void EditSupplierName(int supplierID, String supplierName) {
        supController.EditSupplierName(supplierID , supplierName);
    }

    public void EditAddress(int supplierID, String address) {
        supController.EditAddress(supplierID , address);
    }

    public void EditEmail(int supplierID, String email) {
        supController.EditEmail(supplierID , email);
    }

    public void EditBankAccount(int supplierID, int bankAccount) {
        supController.EditBankAccount(supplierID , bankAccount);
    }

    public void EditPaymentMethod(int supplierID, String payment) {
        supController.EditPaymentMethod(supplierID , payment);
    }

    // TODO: if time will allow --> upgrade to Alex idea
    public void EditContact(int supplierID, String contact) {
        supController.EditContact(supplierID , contact);
    }

    public void EditInfoSupDay(int supplierID, String infoSupDay) {
        supController.EditInfoSupDay(supplierID , infoSupDay);
    }

    public void EditPickup(int supplierID, boolean pickup) {
        supController.EditPickup(supplierID , pickup);
    }

    public String showSupplierCard(int supplierID){
        return supController.showSupplierCard(supplierID);
    }

    public void addProductToSupplier(int supplierID, int productID, String name, String category, double price) {
        supController.addProductToSupplier(supplierID, productID, name, category, price);
    }

    public void removeProductToSupplier(int supplierID, int productID) {
        supController.removeProductToSupplier(supplierID, productID);
    }

    public String showSupplierProducts(int supplierID){
        return supController.showSupplierProducts(supplierID);
    }

    public String showAllSupplier(){
        return supController.showAllSupplier();
    }

    public boolean checkSuppExist(int suppID){
        return supController.checkSuppExist(suppID);
    }

    public boolean checkBillExist(int suppID){
        return supController.checkBillExist(suppID);
    }

    public boolean checkProductExist(int supID, int prodID){
        return supController.checkProductExist(supID,prodID);
    }

    public boolean checkProductInBillOfQ(int supID, int prodID){
        return supController.checkProductInBillOfQ(supID,prodID);
    }

    /***************** Orders Functions: ***************/


    public int createOrder(int supplierID){
        int orderID = orderController.createOrder(supplierID);

        LinkedList<Integer> ordersOfSupplier = supplierOrder.get(supplierID);
        if( ordersOfSupplier != null){
            ordersOfSupplier.add(orderID);
        }
        else{
            ordersOfSupplier = new LinkedList<Integer>();
            ordersOfSupplier.add(orderID);
            supplierOrder.put(supplierID,ordersOfSupplier);
        }
        return orderID;
    }

    public void addProductToOrder(int orderID, int productID , int quantity) {
        orderController.addProductToOrder(orderID, productID, quantity);
    }

    public void removeFromOrder(int productID , int supplierID) {
        orderController.removeFromOrder(productID , supplierID);
    }

    public void removeOrder(int orderID) {
        orderController.removeOrder(orderID);
    }

    public void updateProdQuantity(int orderID, int productID, int quantity) {
        orderController.updateProdQuantity(orderID, productID,quantity);
    }

    public String showAllOrders(){
        return orderController.showAllOrders();
    }

    public String showOrdersBySupplier(int supplierID) {
        String allOrders = '\n'+ "All Supplier Number:" + supplierID + " Orders Are: " ;
        if(!checkSuppExist(supplierID)){ throw new IllegalArgumentException("Supplier Does Not Exist");}
        LinkedList<Integer> listOfOrders = supplierOrder.get(supplierID);
        if (listOfOrders == null){
            return allOrders + '\n' + " No Orders Yet For This Supplier";
        }
        for(Integer order : listOfOrders){
            allOrders += '\n' + "Order ID: "+ order +  ", Date: " + orderController.orders.get(order).getDate()  ;
        }
        return allOrders + '\n';

    }

    public void finalPriceForOrder(int OrderID, int suppID){
        orderController.finalPriceForOrder(OrderID, suppID);
    }

    public String showOrder(int orderID){
        return orderController.showOrder(orderID);
    }

    public boolean isEmptyOrder(int orderID){
        return orderController.isEmptyOrder(orderID);
    }

    public boolean productInOrder(int orderID, int prodID){
        return orderController.productInOrder(orderID, prodID);
    }

    //TODO: where to catch exceptions

}