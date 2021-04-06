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

    public void act(int action) {
        io = IO.getInstance();
        //add sale
        if (action == 1) {
            invCtrl.addSale(io.getInt("Enter item ID:"));
        }

        //add faulty
        if (action == 2){
            invCtrl.addFaulty(io.getInt("Enter item ID:"));
        }

        //add item
        if (action == 3) {
            int id = io.getInt("Enter item ID:");
            String name = io.getString("Enter item Name:");
            LocalDate expDate = io.getDate("Enter Expiration Date:");
            double price = io.getDouble("Enter item price:");
            double cost = io.getDouble("Enter item cost:");
            int shelf = io.getInt("Enter item shelf");
            String man = io.getString("Enter item manufacturer:");
            int shQuant = io.getInt("Enter amount on shelf");
            int stQuant = io.getInt("Enter amount in storage");
            String catName = io.getString("Enter item category:");
            invCtrl.addItem(id, name, expDate, price, cost, shelf, man, shQuant, stQuant, catName);
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
    }
}
