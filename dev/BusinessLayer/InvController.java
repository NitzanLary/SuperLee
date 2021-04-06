package BusinessLayer;

import java.time.LocalDate;

public class InvController {
    private RecordController recCtrl;
    private StockController stockCtrl;

    public InvController() {
        recCtrl = new RecordController();
        stockCtrl = new StockController();
    }

    public void sale(int id) {
        //Need to add the part that removes from stock and such
        Item item = getItem(id);
        recCtrl.addSale(item.getId(), item.getCost(), item.getPrice());
    }

    public void faulty(int id) {
        //Need to add the part that removes from stock and such
        Item item = getItem(id);
        recCtrl.addFaulty(item.getName(), item.getExpDate());
    }

    public Item getItem(int id) {
        return null;
    }

    /*
    public Item addItem(int id, String name, LocalDate expDate, double price, double cost, int shelf, String man, int shQuan, int stQuan, String catName) {
        return  stockCtrl.addItem(id,name,expDate,price,cost,shelf,man,shQuan,stQuan);
    }

    public Catagory addCategory(String name, String superName){
        return stockCtrl.addCategory(name, superName);
    }
     */
}
