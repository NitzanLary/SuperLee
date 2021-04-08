package BusinessLayer;

import java.time.LocalDate;

public class InvController {
    private RecordController recCtrl;
    private StockController stockCtrl;

    public InvController() {
        recCtrl = new RecordController();
        stockCtrl = new StockController();
    }

    //adds a sale to the records, removes one item from shelf
    public boolean addSale(int id, int amount) {
        if (!stockCtrl.removeFromShelf(id, amount))
            return false;
        Item item = stockCtrl.getItem(id);
        return recCtrl.addSale(item.getId(), item.getCost(), item.getPrice());
    }

    //adds a sale to the records, removes one item from shelf if opt is 1 or from storage if opt is 2
    public boolean addFaulty(int id, int opt, int amountOfFaulty) {
        if(opt == 1) {
            if(!stockCtrl.removeFromShelf(id, amountOfFaulty))
                return false;
        } else {
            if(!stockCtrl.removeFromStorage(id, amountOfFaulty))
                return false;
        }
        Item item = stockCtrl.getItem(id);
        return recCtrl.addFaulty(item.getName(), LocalDate.now() ,amountOfFaulty);
    }

    //adds a new Item to the System
    public boolean addItem(int id, String name, double price, double cost, int shelf, String man, int shQuan, int stQuan, String catName) {
        return stockCtrl.addItem(id,name,price,cost,shelf,man,shQuan,stQuan, catName);
    }

    //adds a category which is a sub category of `superName`
    public boolean addSubCategory(String name, String superName){
        return stockCtrl.addSubCategory(name, superName);
    }

    //adds a category which is not a sub category of any
    public boolean addCategory(String name) {
        return stockCtrl.addCategory(name);
    }

    //returns a report of all the faulty items that the System found or was reported to it
    public String getFaultyReport(LocalDate startDate) {
        return recCtrl.faultyReport(startDate);
    }

    //adds a discount for an item between the `start` and `end` date
    public boolean addItemDiscount(LocalDate start, LocalDate end, int dis, int itemID) {
        return stockCtrl.addItemDiscount(start, end, dis, itemID);
    }

    //adds a discount for all the items in a category between the `start` and `end` date
    public void addCategoryDiscount(LocalDate start, LocalDate end, int dis, String category) {
        stockCtrl.addCategoryDiscount(start, end, dis, category);
    }

    //adds a new supply for an item to the storage
    public boolean addToStorage(int itemID, int amountToAdd) {
        return stockCtrl.addToStorage(itemID, amountToAdd);
    }

    //adds a manufacturer discount for an item
    public boolean addManuDiscount(LocalDate start, LocalDate end, int dis, int itemId) {
        return stockCtrl.addManuDiscount(start, end, dis, itemId);
    }

    //changes an item shelf
    public boolean changeShelf(int itemId, int newShelf) {
        return stockCtrl.changeShelf(itemId, newShelf);
    }

    //moves `amountToMove` items from storage to shelf
    public boolean moveToShelf(int itemId, int amountToMove) {
        return stockCtrl.moveToShelf(itemId, amountToMove);
    }

    //returns a report of all items in stock
    public String stkReport() {
        return stockCtrl.stkReport();
    }

    //removes an item from the system
    public boolean removeItem(int itemId) {
        return stockCtrl.removeItem(itemId);
    }
}
