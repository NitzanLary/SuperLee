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
        recCtrl.addFaulty(item.getName(), LocalDate.now());
    }

    public void addItem(int id, String name, double price, double cost, int shelf, String man, int shQuan, int stQuan, String catName) {
        stockCtrl.addItem(id,name,price,cost,shelf,man,shQuan,stQuan, catName);
    }

    public void addSubCategory(String name, String superName){
        stockCtrl.addSubCategory(name, superName);
    }

    public void addCategory(String name) {
        stockCtrl.addCategory(name);
    }

    public String getFaultyReport(LocalDate startDate) {
        return recCtrl.faultyReport(startDate);
    }

    public boolean deleteItem(int id) {
        return stockCtrl.deleteItem(id);
    }

    public void addItemDiscount(LocalDate start, LocalDate end, int dis, int itemID) {
        stockCtrl.addItemDiscount(start, end, dis, itemID);
    }

    public void addCategoryDiscount(LocalDate start, LocalDate end, int dis, String category) {
        stockCtrl.addCategoryDiscount(start, end, dis, category);
    }
}
