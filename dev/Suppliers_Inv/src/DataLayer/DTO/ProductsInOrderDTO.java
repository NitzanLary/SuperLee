package DataLayer.DTO;

public class ProductsInOrderDTO {

    private Integer orderID;
    private Integer productID;
    private String quantity;
    private Integer supplierID;

    public ProductsInOrderDTO(Integer orderID, Integer productID, String quantity, Integer supplierID) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.supplierID = supplierID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Integer getProductID() {
        return productID;
    }

    public String getQuantity() {
        return quantity;
    }

    public Integer getSupplierID() {
        return supplierID;
    }
}
