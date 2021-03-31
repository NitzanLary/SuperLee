package BusinessLayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class RecordController {
    private List<FaultyItem> faultyItems;
    private List<Sale> sales;

    public RecordController(){
        faultyItems = new LinkedList<FaultyItem>(); //need to change?
        sales = new LinkedList<Sale>(); //need to change?
    }

    public Sale addSale(int itemId, double itemCost, double salePrice) {
        Sale newSale = new Sale(itemId, itemCost, salePrice);
        sales.add(newSale);
        return newSale;
    }

    public FaultyItem addFaulty(String name, LocalDate expDate) {
        FaultyItem newFI = new FaultyItem(name, expDate);
        faultyItems.add(newFI);
        return newFI;
    }

    public String faultyReport(LocalDate from) {
        return faultyReport(from, LocalDate.now());
    }

    public String faultyReport(LocalDate from, LocalDate to) {
        String str = "";
        int i = 1;
        for(FaultyItem fi : faultyItems) {
            if ((fi.getExpDate().isAfter(from) || fi.getExpDate().isEqual(from))
                    && (fi.getExpDate().isBefore(to) || fi.getExpDate().isEqual(to))) {
                str = i + ")\n" + fi + "\n";
            }
        }
        return str;
    }

    public String saleReport(LocalDateTime from) {
        return saleReport(from, LocalDateTime.now());
    }

    public String saleReport(LocalDateTime from, LocalDateTime to) {
        String str = "";
        int i = 1;
        for(Sale s : sales) {
            if ((s.getSaleDate().isAfter(from) || s.getSaleDate().isEqual(from))
                    && (s.getSaleDate().isBefore(to) || s.getSaleDate().isEqual(to))) {
                str = i + ")\n" + s + "\n";
            }
        }
        return str;
    }
}
