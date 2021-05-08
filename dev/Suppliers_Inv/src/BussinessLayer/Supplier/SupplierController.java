package BussinessLayer.Supplier;

import java.util.HashMap;

public class SupplierController {

    private static SupplierController supplierController = null;

    private HashMap<Integer, SupplierCard> suppliers; // <supID: Integer, SupplierCard>
    private ProductController prodController;

    private SupplierController()
    {
        suppliers = new HashMap<>();
        prodController = prodController.getInstance();
    }

    public static SupplierController getInstance()
    {
        if (supplierController == null)
            supplierController = new SupplierController();

        return supplierController;
    }


    // *************** Supplier Functions: ***************

    public void createSupCard(String supplierName, int supplierID, String address, String email, int bankAcc,
                              String paymentMethod, String contacts, String infoSupplyDay, boolean pickUp){
        // check if the supplierCard already exists in the system.
        if (suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Already Exists In The System");
        }
        else{
            SupplierCard supCard = new SupplierCard(supplierName,supplierID,address,email,bankAcc,
                    paymentMethod,contacts,infoSupplyDay,pickUp);
            suppliers.put(supplierID , supCard);
            prodController.newSupplier(supplierID);
        }
    }

    public void addBillOfQuantity(int supplierID, HashMap<Integer, Integer> minQuantityForDis,  HashMap<Integer, Integer> discountList){
        prodController.addBillOfQuantity(supplierID, minQuantityForDis, discountList);
    }

    public void deleteSupCard(int supplierID){
        SupplierCard checkExists = suppliers.remove(supplierID);
        if(checkExists == null){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        else {
            prodController.deleteSupCard(supplierID);
        }
    }

    public String showSupplierCard(int supplierID){
        SupplierCard tmp = suppliers.get(supplierID);
        if(tmp == null){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        return tmp.toString();
    }

    public void deleteBillOfQuantity(int supplierID) {
        prodController.deleteBillOfQuantity(supplierID);
    }

    public void addProductToSupplier(int supplierID, int productID, String name, String category, double price) {
        prodController.addProductToSupplier(supplierID, productID, name, category, price);
    }

    public void removeProductToSupplier(int supplierID, int productID) {
        prodController.removeProductToSupplier(supplierID, productID);
    }

    public String showSupplierProducts(int supplierID){
        return prodController.showSupplierProducts(supplierID);
    }

    public String showAllSupplier(){
        String supplierList = "All The Supplier That Work With Super-Lee Are: " + '\n';
        for(SupplierCard sc : suppliers.values()){
            supplierList += "Name: " + sc.getSupplierName() + " ID: " + sc.getSupplierID() + '\n';
        }
        return supplierList + "Total "+ suppliers.size() + " Suppliers";
    }

    public void EditSupplierName(int supplierID, String supplierName) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setSupplierName(supplierName);
    }

    public void EditAddress(int supplierID, String address) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setAddress(address);    }

    public void EditEmail(int supplierID, String email) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setEmail(email);
    }

    public void EditBankAccount(int supplierID, int bankAccount) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setBankAcc(bankAccount);
    }

    public void EditPaymentMethod(int supplierID, String payment) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setPaymentMethod(payment);
    }

    public void EditContact(int supplierID, String contact) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setContacts(contact);
    }

    public void EditInfoSupDay(int supplierID, String infoSupDay) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setInfoSupplyDay(infoSupDay);
    }

    public void EditPickup(int supplierID, boolean pickup) {
        if(!suppliers.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        suppliers.get(supplierID).setPickUp(pickup);
    }

    public void checkSuppExist(int suppID){
        if(!suppliers.containsKey(suppID)){
            throw new IllegalArgumentException("This Supplier Does Not Exist");
        }
    }

    public void checkSuppNotExist(int suppID){
        if(suppliers.containsKey(suppID)){
            throw new IllegalArgumentException("This Supplier Already Exists");
        }
    }

    public void checkBillExist(int suppID){
        prodController.checkBillExist(suppID);
    }

    public void checkBillNotExist(int suppID){
        prodController.checkBillExist(suppID);
    }

    public void checkProductExist(int supID, int prodID){
        prodController.checkProductExist(supID, prodID);
    }

    public void checkProductInBillOfQ(int supID, int prodID){
        prodController.checkProductInBillOfQ(supID, prodID);
    }

    public HashMap<Integer, SupplierCard> getSuppliers() {
        return this.suppliers;
    }


}
