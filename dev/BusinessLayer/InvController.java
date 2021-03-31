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

}
