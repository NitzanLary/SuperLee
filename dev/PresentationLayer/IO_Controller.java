package PresentationLayer;

import BusinessLayer.InvController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class IO_Controller {
    private InvController invCtrl;
    private IO io;

    public IO_Controller() {
        invCtrl = new InvController();
    }

    public void initData() {
        invCtrl.addCategory("Dairy");
        invCtrl.addItem(1,"Milk", 5, 3,11, "Tnova", 10, 15, "Dairy");
    }

    public void act(int action) {
        io = IO.getInstance();
        //add sale
        if (action == 1) {
            invCtrl.addSale(io.getInt("Enter item ID:"));
        }

        //add faulty
        if (action == 2){
            int itemId = io.getInt("Enter item ID:");
            int opt = io.getInt("Where was the faulty item found (1 - shelf , 2 - storage)");
            invCtrl.addFaulty(itemId, opt);
        }

        //add item
        if (action == 3) {
            int id = io.getInt("Enter item ID:");
            String name = io.getString("Enter item Name:");
            double price = io.getDouble("Enter item price:");
            double cost = io.getDouble("Enter item cost:");
            int shelf = io.getInt("Enter item shelf");
            String man = io.getString("Enter item manufacturer:");
            int shQuant = io.getInt("Enter amount on shelf");
            int stQuant = io.getInt("Enter amount in storage");
            String catName = io.getString("Enter item category:");
            invCtrl.addItem(id, name, price, cost, shelf, man, shQuant, stQuant, catName);
        }
        // add category
        if (action == 4) {
            String catName = io.getString("Enter catagory name");
            String subCatOf = io.getString("Enter the name of the catagory above" + catName +": (enter 0 if there isn't one)");
            if (subCatOf.equals("0"))
                invCtrl.addCategory(catName);
            else
                invCtrl.addSubCategory(catName, subCatOf);
        }
        // faulty report
        if (action == 5) {
            LocalDate date = io.getDate("Enter faulty report starting date");
            System.out.println(invCtrl.getFaultyReport(date));
        }
        //go to edit menu
        if (action == 6) {
            io.editMenu();
        }
    }

    public void editAct(int action) {
        //add discount to item
        if(action == 1) {
            LocalDate start = io.getDate("Enter discount starting date");
            LocalDate end = io.getDate("Enter discount end date");
            int dis = io.getInt("Enter the amount of discount");
            int itemId = io.getInt("Enter Item ID");
            invCtrl.addItemDiscount(start, end, dis, itemId);
        }

        //add discount to category
        if(action == 2) {
            LocalDate start = io.getDate("Enter discount starting date");
            LocalDate end = io.getDate("Enter discount end date");
            int dis = io.getInt("Enter the amount of discount");
            String category  = io.getString("Enter Category name");
            invCtrl.addCategoryDiscount(start, end, dis, category);
        }

        //add items to storage
        if(action == 3) {
            int itemID = io.getInt("Enter item id");
            int amountToAdd = io.getInt("Enter amount to add");
            invCtrl.addToStorage(itemID, amountToAdd);
        }

        //add a manufacturer discount to item
        if(action == 4) {
            LocalDate start = io.getDate("Enter discount starting date");
            LocalDate end = io.getDate("Enter discount end date");
            int dis = io.getInt("Enter the amount of discount");
            int itemId = io.getInt("Enter Item ID");
            invCtrl.addManuDiscount(start, end, dis, itemId);
        }

        //change the shelf of an item
        if(action == 5) {
            int itemId = io.getInt("Enter Item ID");
            int newShelf = io.getInt("Enter the new shelf for the item");
            invCtrl.changeShelf(itemId, newShelf);
        }

        //moves items from storage to the shelf
        if(action == 6) {
            int itemId = io.getInt("Enter Item ID");
            int amountToMove = io.getInt("Enter the amount of items to move");
            invCtrl.moveToShelf(itemId, amountToMove);
        }
    }
}
