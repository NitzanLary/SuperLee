package BussinessLayer;

public class Product {
    private int productID;
    private int supplierID;
    private String name;
    private String category;
    private double price;

    public Product(int productID, int supplierID, String name, String category, double price){
        this.productID = productID;
        this.supplierID = supplierID;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String toString(){
        return "product ID: " + productID + '\t' + "product Name: " + name + '\t' +"Price: " + price;
    }

}
