package DataLayer.DTO;

import BussinessLayer.Inventory.FaultyItem;

import java.time.LocalDate;
public class FaultyItemDTO {
    private int itemId;
    private LocalDate expDate;
    private int amount;

    public FaultyItemDTO(FaultyItem fi) {
        this.itemId = fi.getItemId();
        this.expDate = fi.getExpDate();
        this.amount = fi.getAmount();
    }

    public int getItemId() {
        return itemId;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public int getAmount() {
        return amount;
    }
}
