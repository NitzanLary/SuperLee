package BussinessLayer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProductController {

    private static ProductController productController = null;

    private HashMap<Integer, HashMap<Integer,Product>> supplierProd; // <supID: Integer, HashMap<Integer:productID,Product>>
    private HashMap<Integer,BillOfQuantities> discounts; // <supID: Integer, List<Product>>

    private ProductController()
    {
        supplierProd = new HashMap<>();
        discounts = new HashMap<>();
    }

    public static ProductController getInstance()
    {
        if (productController == null)
            productController = new ProductController();

        return productController;
    }


    public void addBillOfQuantity(int supplierID, HashMap<Integer, Integer> minQuantityForDis,  HashMap<Integer, Integer> discountList){
        if (discounts.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Already Have Bill Of Quantities");
        }
        else{
            BillOfQuantities billOfQ = new BillOfQuantities(minQuantityForDis,discountList );
            discounts.put(supplierID , billOfQ);
        }
    }

    public void deleteSupCard(int supplierID){
        supplierProd.remove(supplierID);
        discounts.remove(supplierID);
    }

    public void deleteBillOfQuantity(int supplierID) {
        BillOfQuantities tmp = discounts.remove(supplierID);
        if (tmp == null){
            throw new IllegalArgumentException("This Supplier Does Not Have Bill Of Quantity To Delete");
        }
    }

    public void addProductToSupplier(int supplierID, int productID, String name, String category, double price) {
        if (!supplierProd.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        if (supplierProd.get(supplierID).containsKey(productID)){
            throw new IllegalArgumentException("This Item Already Exists In The Supplier Products List");
        }
        else{
            Product prod = new Product(productID,supplierID, name,category, price);
            supplierProd.get(supplierID).put(productID,prod);
        }
    }

    public void removeProductToSupplier(int supplierID, int productID) {
        if (!supplierProd.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        if (!supplierProd.get(supplierID).containsKey(productID)){
            throw new IllegalArgumentException("This Item Does Not Exists In The Supplier Products List");
        }
        else{
            supplierProd.get(supplierID).remove(productID);
        }
    }

    public String showSupplierProducts(int supplierID){
        if (!supplierProd.containsKey(supplierID)){
            throw new IllegalArgumentException("This Supplier Does Not Exists In The System");
        }
        HashMap<Integer,Product> supItems = supplierProd.get(supplierID);
        if (supItems.isEmpty()){
            throw new IllegalArgumentException("This Supplier Does Not Have Any Products");
        }

        String productsList = '\n' + "products list: " + '\n';
        for(Product p : supItems.values()){
            productsList += p.toString() + '\n';
        }
        return productsList;
    }

    public boolean checkBillExist(int suppID){
        if(!discounts.containsKey(suppID)){
            return false;
        }
        else return true;
    }

    public double calculateDiscount(int productId, int quantity, int suppID){
        double priceBeforeDiscount = supplierProd.get(suppID).get(productId).getPrice() * quantity;
        BillOfQuantities bill = discounts.get(suppID);
        if(bill == null ) //no bill for this supplier
            return priceBeforeDiscount;
        Integer minQ = bill.getMinQuantityForDis().get(productId);
        if(minQ == null || (minQ > quantity)) //no discount for this product
            return priceBeforeDiscount;
        int percentDis = bill.getDiscountList().get(productId);
        double substract =  (priceBeforeDiscount * percentDis)/100;
        return priceBeforeDiscount - substract;
    }

    public void newSupplier(int supplierID){
        HashMap<Integer,Product> products = new HashMap<>();
        supplierProd.put(supplierID, products);
    }

    public boolean checkProductExist(int supID, int prodID){
        Product prod = supplierProd.get(supID).get(prodID);
        return prod != null;
    }

    //TODO: FLAGGGGGGGGGGGGGG
    public void addProductToOrder(int supplierID, int productID , int quantity) {

    }

    public boolean checkProductInBillOfQ(int supID, int prodID){
        return discounts.get(supID).getMinQuantityForDis().containsKey(prodID);
    }


}
