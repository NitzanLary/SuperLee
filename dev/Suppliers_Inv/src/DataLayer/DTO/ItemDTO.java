package DataLayer.DTO;
import BussinessLayer.Inventory.Discount;
import java.util.List;

public class ItemDTO {
    private int id;
    private String name;
    private Double price;
    private Double cost;
    private int shelfNum;
    private String manufacturer;
    private int shelfQuantity;
    private int storageQuantity;
    private List<Discount> priceDiscounts;
    private List<Discount> costDiscounts;
    private int minAlert;


    public ItemDTO(BussinessLayer.Inventory.Item blItem) {
        this.id = blItem.getId();
        this.name = blItem.getName();
        this.price = blItem.getPrice();
        this.cost = blItem.getCost();
        this.shelfNum = blItem.getShelfNum();
        this.manufacturer = blItem.getManufacturer();
        this.shelfQuantity = blItem.getShelfQuantity();
        this.storageQuantity = blItem.getStorageQuantity();
        priceDiscounts = blItem.getPriceDiscounts();
        costDiscounts = blItem.getCostDiscounts();
        this.minAlert = blItem.getMinAlert();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getShelfQuantity() {
        return shelfQuantity;
    }

    public int getStorageQuantity() {
        return storageQuantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getShelfNum() {
        return shelfNum;
    }

    public int getMinAlert() {
        return minAlert;
    }

    public List<Discount> getCostDiscounts() {
        return costDiscounts;
    }

    public List<Discount> getPriceDiscounts() {
        return priceDiscounts;
    }

    public Double getCost() {
        return cost;
    }

    public String toString(String tabs) {
        String s = tabs + "Id: " + id +
                "\n" + tabs + "Name: " + name +
                "\n" + tabs + "Shelf Num: " + shelfNum +
                "\n" + tabs + "Manufacturer: " + manufacturer +
                "\n" + tabs + "Quantity: " + (shelfQuantity + storageQuantity) +
                "\n" + tabs + "Shelf Quantity: " + shelfQuantity +
                "\n" + tabs + "Storage Quantity: " + storageQuantity + "\n";
        if (shelfQuantity+storageQuantity <= minAlert)
            s += tabs + "Item is below the minimum that was set by: " + (minAlert-(shelfQuantity+storageQuantity)) + "\n";
        return s;
    }

}
