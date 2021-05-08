package DataLayer.DTO;

import java.time.LocalDate;

public class PeriodicOrderDTO {

    private Integer orderID;
    private LocalDate supplyDate;
    private Integer intervals;
    private Integer productID;
    private Integer quantity;

    public PeriodicOrderDTO(Integer orderID, LocalDate supplyDate, Integer intervals, Integer productID, Integer quantity) {
        this.orderID = orderID;
        this.supplyDate = supplyDate;
        this.intervals = intervals;
        this.productID = productID;
        this.quantity = quantity;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public LocalDate getSupplyDate() {
        return supplyDate;
    }

    public Integer getIntervals() {
        return intervals;
    }

    public Integer getProductID() {
        return productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "PeriodicOrderDTO{" +
                "orderID: '" + this.orderID + '\'' +
                ", supplyDate: '" + this.supplyDate + '\'' +
                ", intervals: '" + this.intervals + '\'' +
                ", productID: '" + this.productID + '\'' +
                ", quantity: '" + this.quantity + '\'' +
                '}';
    }
}
