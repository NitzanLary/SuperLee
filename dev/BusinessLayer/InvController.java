package BusinessLayer;

import java.time.LocalDate;

public class InvController {
    private RecordController recCtrl;
    private StockController stockCtrl;

    public InvController() {
        recCtrl = new RecordController();
        stockCtrl = new StockController();
    }

    public void addSale(int id) {
        //Need to add the part that removes from stock and such
        Item item = stockCtrl.getItem(id);
        recCtrl.addSale(item.getId(), item.getCost(), item.getPrice());
    }

    public void addFaulty(int id) {
        //Need to add the part that removes from stock and such
        Item item = stockCtrl.getItem(id);
        recCtrl.addFaulty(item.getName(), item.getExpDate());
    }

    public void addItem(int id, String name, LocalDate expDate, double price, double cost, int shelf, String man, int shQuan, int stQuan, String catName) {
        stockCtrl.addItem(id,name,expDate,price,cost,shelf,man,shQuan,stQuan, catName);
    }

    public void addSubCategory(String name, String superName){
        stockCtrl.addSubCatagory(name, superName);
    }

    public void addCategory(String name) {
        stockCtrl.addCategory(name);
    }

    public String getFaultyReport(LocalDate startDate) {
        return recCtrl.faultyReport(startDate);
    }

}
