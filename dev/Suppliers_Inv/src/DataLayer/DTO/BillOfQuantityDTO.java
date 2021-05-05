package DataLayer.DTO;

public class BillOfQuantityDTO {

    private Integer supplierID;
    private Integer minQuantity;
    private Integer percentDiscount;

    public BillOfQuantityDTO(Integer ID, Integer minQuantity, Integer percentDiscount) {
        this.supplierID = ID;
        this.minQuantity = minQuantity;
        this.percentDiscount = percentDiscount;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public Integer getPercentDiscount() {
        return percentDiscount;
    }

    @Override
    public String toString() {
        return "BillOfQuantityDTO{" +
                "supplierID: '" + this.supplierID + '\'' +
                ", minimum quantity: '" + this.minQuantity + '\'' +
                ", percent discount: '" + this.percentDiscount + '\'' +
                '}';
    }
}
