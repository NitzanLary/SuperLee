package PresentationLayer;
import BussinessLayer.FacadeController;

import java.util.HashMap;
import java.util.Scanner;
import static java.lang.System.exit;

/**
 * This is the presentation layer of the system.
 * The communication with the users are from here.
 */

//TODO: check if the functions need to be static?

public class IO {

    private static IO io = null;
    public static FacadeController facadeC = FacadeController.getInstance();
    public static Scanner scanner = new Scanner(System.in);

    private IO() {
        System.out.println('\n' + "----------------------------------------------------------");
        System.out.println('\n' +
                "8888  8  8  8888  8888  8888              8    8888  8888" + '\n' +
                "8     8  8  8  8  8     8  8              8    8     8   "+ '\n' +
                "8888  8  8  8888  8888  888     888888    8    8888  8888"+ '\n' +
                "   8  8  8  8     8     8 8               8    8     8   "+ '\n' +
                "8888  8888  8     8888  8  8              8888 8888  8888" + '\n');
        System.out.println("----------------------------------------------------------" + '\n');

        System.out.println('\n' + "Welcome To Super-Lee Supplier System!" + '\n' +
                "Choose What You Want To Do: "+ '\n');
    }

    public static IO getInstance() {
        if (io == null)
            io = new IO();

        return io;
    }

    public static void init() {

        System.out.println( "1. Main Menu " + '\n' + "2. Load Data example " );

        int caseNumber = Integer.parseInt(scanner.nextLine());

        switch (caseNumber) {
            case 2:
                baseScenario();
                mainMenu();
                break;

            case 1:
                mainMenu();
                break;
        }
    }

    public static void baseScenario() {

        facadeC.createSupCard("Sahar", 001, "Raanana", "kalifa@gmail.com" , 45802000,
                "credit card","shimon 052-6093400" , "Sunday", false);

        facadeC.addProductToSupplier(001,800,"Bamba", "Snacks", 3.5);
        facadeC.addProductToSupplier(001,801,"Bisly", "Snacks", 4.5);
        facadeC.addProductToSupplier(001,802,"Apropo", "Snacks", 3);

        facadeC.createSupCard("Batya", 002, "Tel-Aviv", "batya@gmail.com" , 45650208,
                "Cash","Shiri 052-6763400" , "Friday", false);

        facadeC.addProductToSupplier(002,300,"Coca-Cola", "Drink", 4);
        facadeC.addProductToSupplier(002,301,"Sprite", "Drink", 4);
        facadeC.addProductToSupplier(002,302,"Fusetea", "Drink", 3.5);

        facadeC.createSupCard("Xavi", 3, "Peru", "xavi@gmail.com" , 7004000,
                "All Method","sahar 050-98223400" , "Monday", true);

        facadeC.addProductToSupplier(3,700,"Water", "Drink", 1.5);
        facadeC.addProductToSupplier(3,701,"Pasta", "Pasta", 5);

    }

    public static void mainMenu() {

        while (true) {
            System.out.println('\n' + "1. Suppliers And Products");
            System.out.println("2. Orders");
            System.out.println("3. Exit");

            int caseNumber = Integer.parseInt(scanner.nextLine());
            switch (caseNumber) {
                case 1:
                    suppliersAndProducts();
                    break;
                case 2:
                    orders();
                    break;
                case 3:
                    System.out.println('\n'+ "You Are Log Out From The System, Have A Nice Day.\n");
                    exit(0);
                default:
                    System.out.println("You Need To Choose Only 1-3");
            }

        }
    }

    public static void suppliersAndProducts() {

        System.out.println('\n' + "Please Choose One Of The Following Options : ");
        System.out.println("1. Add New Supplier");
        System.out.println("2. Delete Supplier");
        System.out.println("3. Update Supplier");
        System.out.println("4. Create Bill Of Quantity");
        System.out.println("5. Delete Bill Of Quantity");
        System.out.println("6. Edit Bill Of Quantity");
        System.out.println("7. Add Product To Supplier");
        System.out.println("8. Delete Product from Supplier");
        System.out.println("9. Show All Supplier Products");
        System.out.println("10. Show All Suppliers ");
        System.out.println("11. Show Specific Supplier ");
        System.out.println("12. Back To Main Menu ");

        int caseNumber = Integer.parseInt(scanner.nextLine());

        switch (caseNumber) {
            case 1:
                createSupplierCard();
                break;

            case 2:
                System.out.println('\n' + "Enter Supplier ID You Would Like To Delete: ");
                int SupplierID = Integer.parseInt(scanner.nextLine());
                facadeC.deleteSupCard(SupplierID);
                System.out.println("Deleted Successfully" + '\n');
                break;

            case 3:
                updateSupplier();
                break;

            case 4:
                createBillOfQ();
                break;

            case 5:
                System.out.println("Enter Supplier ID You Would Like To Delete His Bill Of Quantity: ");
                int SuppID = Integer.parseInt(scanner.nextLine());
                facadeC.deleteBillOfQuantity(SuppID);
                break;

            case 6:
                updateBill();
                break;

            case 7:
                addProdToSupp();
                break;

            case 8:
                System.out.println("Enter Supplier ID You Would Like To Delete His Product: ");
                int SupplID = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter Product ID You Would Like To Delete: ");
                int pid = Integer.parseInt(scanner.nextLine());
                facadeC.removeProductToSupplier(SupplID,pid);
                break;

            case 9:
                System.out.println('\n' + "Enter Supplier ID You Would Like To See His Product: ");
                int SupID = Integer.parseInt(scanner.nextLine());
                String allSuppliersProd = facadeC.showSupplierProducts(SupID);
                System.out.println(allSuppliersProd);
                break;

            case 10:
                String allSuppliers = facadeC.showAllSupplier();
                System.out.println('\n' + allSuppliers);
                break;

            case 11:
                System.out.println("Enter Supplier ID You Would Like To See His Details: ");
                int SupId = Integer.parseInt(scanner.nextLine());
                String supCard = facadeC.showSupplierCard(SupId);
                System.out.println(supCard);
                break;

            case 12:
                return;

            default:
                System.out.println("You Need To Choose Only 1-12");
        }
    }

    public static void orders(){
        System.out.println('\n' + "Please Choose One Of The Following Options : ");
        System.out.println("1. Create New Order");
        System.out.println("2. Delete Exist Order");
        System.out.println("3. Show All Orders From Suppliers");
        System.out.println("4. Show Order By Supplier");
        System.out.println("5. Back To Main Menu ");

        int caseNumber = Integer.parseInt(scanner.nextLine());
        switch (caseNumber) {
            case 1:
                creatNewOrder();
                break;

            case 2:
                System.out.println('\n' + "Enter Order ID You Would Like To Delete: ");
                int orderID = Integer.parseInt(scanner.nextLine());
                facadeC.removeOrder(orderID);
                break;

            case 3:
                facadeC.showAllOrders();
                break;

            case 4:
                System.out.println('\n' + "Enter Supplier ID: ");
                int supID = Integer.parseInt(scanner.nextLine());
                orderBySupp(supID);
                break;

            case 5:
                return;

            default:
                System.out.println("You Need To Choose Only 1-5");
        }
    }

    public static void createSupplierCard() {
        System.out.println('\n' + "Enter Supplier ID: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());

        if(facadeC.checkSuppExist(SupplierID)){
            System.out.println("Supplier ID Already Exists");
            return;
        }

        System.out.println("Enter Supplier Name: ");
        String SupplierName = scanner.nextLine();

        System.out.println("Enter Address: ");
        String Address = scanner.nextLine();

        System.out.println("Enter E-mail: ");
        String mail = scanner.nextLine();

        System.out.println("Enter Bank Account Number: ");
        int bankAcc = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Payment Method: ");
        String payment = scanner.nextLine();

        System.out.println("Enter Contacts Details: ");
        String contacts = scanner.nextLine();

        System.out.println("Enter Days of Supply: ");
        String infoSupplyDay = scanner.nextLine();

        System.out.println("The Supplier Need Pickup?, (Y/N): ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        boolean pickUp = false;
        int caseNumber = Integer.parseInt(scanner.nextLine());
        switch (caseNumber) {
            case 1:
                pickUp = true;
                break;
            case 2:
                break;
            default:
                System.out.println("You Need To Choose Only 1-2");
        }
        facadeC.createSupCard(SupplierName,SupplierID,Address,mail,bankAcc,payment,contacts,infoSupplyDay,pickUp);
    }

    public static void updateSupplier(){
        System.out.println('\n' + "Enter Supplier ID You Would Like To Update: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());

        if(!facadeC.checkSuppExist(SupplierID)){
            System.out.println('\n' + "Supplier ID Does Not Exist " + '\n');
            return;
        }

        System.out.println("1. Edit Supplier Name");
        System.out.println("2. Edit Supplier Address");
        System.out.println("3. Edit Supplier Email");
        System.out.println("4. Edit Supplier Bank Account");
        System.out.println("5. Edit Supplier Payment Method");
        System.out.println("6. Edit Supplier Contacts ");
        System.out.println("7. Edit Info Supply Day");
        System.out.println("8. Edit Supplier Pick Up");

        int caseNumber = Integer.parseInt(scanner.nextLine());

        switch (caseNumber) {
            case 1:
                System.out.println("Enter New Name: ");
                String name = scanner.nextLine();
                facadeC.EditSupplierName(SupplierID, name);
                break;

            case 2:
                System.out.println("Enter New Address: ");
                String address = scanner.nextLine();
                facadeC.EditAddress(SupplierID, address);
                break;

            case 3:
                System.out.println("Enter New Email: ");
                String mail = scanner.nextLine();
                facadeC.EditEmail(SupplierID, mail);
                break;

            case 4:
                System.out.println("Enter New Bank Account: ");
                int bank = Integer.parseInt(scanner.nextLine());
                facadeC.EditBankAccount(SupplierID, bank);
                break;

            case 5:
                System.out.println("Enter New Payment Method: ");
                String pay = scanner.nextLine();
                facadeC.EditPaymentMethod(SupplierID, pay);
                break;

            case 6:
                System.out.println("Enter New Contacts: ");
                String contacts = scanner.nextLine();
                facadeC.EditContact(SupplierID, contacts);
                break;

            case 7:
                System.out.println("Enter New Info Supply Day: ");
                String supp = scanner.nextLine();
                facadeC.EditInfoSupDay(SupplierID, supp);
                break;

            case 8:
                System.out.println("The Supplier Need Pickup?, (Y/N): ");
                System.out.println("1. Yes");
                System.out.println("2. No");
                boolean pickUp = false;
                int caseNumber2 = Integer.parseInt(scanner.nextLine());
                switch (caseNumber2) {
                    case 1:
                        pickUp = true;
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("You Need To Choose Only 1-2");
                }
                facadeC.EditPickup(SupplierID, pickUp);
                break;
            default:
                System.out.println("You Need To Choose Only 1-8");
        }
    }

    public static void createBillOfQ() {
        System.out.println("Enter Supplier ID: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());
        boolean flagSup = facadeC.checkSuppExist(SupplierID);
        boolean flagBill = facadeC.checkBillExist(SupplierID);

        if(!flagSup){ System.out.println("This Supplier Does Not Exist In The System"); return;}
        if(flagBill){ System.out.println("This Supplier Already Have Bill Of Quantity"); return;}

        else{
            boolean exit = false;
            HashMap<Integer, Integer> minQuantityForDis = new HashMap<>();
            HashMap<Integer, Integer> discountList = new HashMap<>();
            while(!exit){
                System.out.println("Enter Product ID Which You Want To Make Discount: ");
                int ProdID = Integer.parseInt(scanner.nextLine());
                if(!facadeC.checkProductExist(SupplierID, ProdID)){
                    System.out.println("This Supplier Does Not Have This Product ID");
                    return;
                }
                // if the user already insert this product to the supplier's bill of quantity
                if (minQuantityForDis.get(ProdID) != null){
                    System.out.println("This Product Already Has A Discount");
                    return;
                }

                System.out.println("Enter The Minimum Amount Of Ordering This Product For The Discount: ");
                int minDis = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter The Discount In Percentage, 1-100: ");
                int percentage = Integer.parseInt(scanner.nextLine());
                if(percentage<1 || percentage> 100){
                    System.out.println("Invalid Discount");
                    return;
                }

                minQuantityForDis.put(ProdID,minDis);
                discountList.put(ProdID,percentage);

                System.out.println("Do You Want To Add Another Product? " + '\n' + "1. Yes" + '\n' + "2. No");
                int caseNumber = Integer.parseInt(scanner.nextLine());
                switch (caseNumber) {
                    case 1:
                        break;

                    case 2:
                        exit = true;
                        break;

                    default:
                        System.out.println("You Need To Choose Only 1 Or 2");
                }
            }
            facadeC.addBillOfQuantity(SupplierID,minQuantityForDis,discountList);
        }
    }

    public static void updateBill(){
        System.out.println('\n' + "Enter Supplier ID You Would Like To Update His Bill Of Quantity: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());

        if(!facadeC.checkBillExist(SupplierID)){
            System.out.println("This Supplier Does Not Have Bill Of Quantity");
            return;
        }

        System.out.println("1. Edit Minimum Quantity For Product");
        System.out.println("2. Edit Discount For Product");
        System.out.println("3. Add New Product To Bill Of Quantity");
        System.out.println("4. Delete Product From Bill Of Quantity");

        int caseNumber = Integer.parseInt(scanner.nextLine());

        switch (caseNumber) {
            case 1:
                System.out.println("Enter Product ID: ");
                int pid = Integer.parseInt(scanner.nextLine());
                if(!facadeC.checkProductInBillOfQ(SupplierID, pid)){
                    System.out.println("This Product Does Not Exist In The Bill Of Quantity");
                    return;
                }
                System.out.println("Enter New Minimum Quantity: ");
                int min = Integer.parseInt(scanner.nextLine());
                facadeC.editMinQuantity(SupplierID, pid, min);
                break;

            case 2:
                System.out.println("Enter Product ID: ");
                pid = Integer.parseInt(scanner.nextLine());
                if(!facadeC.checkProductInBillOfQ(SupplierID, pid)){
                    System.out.println("This Product Does Not Exist In The Bill Of Quantity");
                }
                System.out.println("Enter New Discount: ");
                int discount = Integer.parseInt(scanner.nextLine());
                if(discount<1 || discount> 100){
                    System.out.println("Invalid Discount");
                    return;
                }
                facadeC.editMinQuantity(SupplierID, pid, discount);
                break;

            case 3:
                System.out.println("Enter Product ID: ");
                pid = Integer.parseInt(scanner.nextLine());
                if(!facadeC.checkProductExist(SupplierID, pid)){
                    System.out.println("This Supplier Does Not Have This Product ID");
                    return;
                }
                // if the user already insert this product to the supplier's bill of quantity
                if (facadeC.checkProductInBillOfQ(SupplierID,pid)){
                    System.out.println("This Product Already Has A Discount");
                    return;
                }
                System.out.println("Enter New Minimum Quantity:");
                int minQ = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter New Discount, 1-100: ");
                int disc = Integer.parseInt(scanner.nextLine());
                if(disc<1 || disc> 100){
                    System.out.println("Invalid Discount");
                    return;
                }
                facadeC.addProdToBill(SupplierID, pid, minQ, disc);
                break;

            case 4:
                System.out.println("Enter Product ID: ");
                pid = Integer.parseInt(scanner.nextLine());
                facadeC.removeProdFromBill(SupplierID, pid);
                break;

            default:
                System.out.println("You Need To Choose Only 1-4");

        }
    }

    public static void addProdToSupp(){
        System.out.println("Enter Supplier ID: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());

        boolean exist = facadeC.checkSuppExist (SupplierID);
        if (!exist){
            System.out.println("This Supplier Did Not Exist In The System");
            return;
        }

        System.out.println("Enter Product ID: ");
        int pid = Integer.parseInt(scanner.nextLine());
        if(facadeC.checkProductExist(SupplierID, pid)){
            System.out.println("This Product Already Exist In Supplier List");
        }

        System.out.println("Enter Product Name : ");
        String name = scanner.nextLine();

        System.out.println("Enter Product Category : ");
        String category = scanner.nextLine();

        System.out.println("Enter Product Price : ");
        double price = Double.parseDouble(scanner.nextLine());
        if(price<=0){
            System.out.println("Price is Invalid");
            return;
        }

        facadeC.addProductToSupplier(SupplierID,pid,name,category,price);
    }

    public static void creatNewOrder(){
        System.out.println('\n' + "Enter Supplier ID Which You Would Like To Take Order From: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());
        if(!facadeC.checkSuppExist(SupplierID)){
            System.out.println("Supplier Does Not Exist");
            return;
        }
        int orderID = facadeC.createOrder(SupplierID);

        boolean finishOrder = false;

        while (!finishOrder) {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product Quantity");
            System.out.println("4. Show Products Of The Supplier");
            System.out.println("5. Finish Order");

            int caseNumber = Integer.parseInt(scanner.nextLine());

            switch (caseNumber) {
                case 1:
                    System.out.println('\n' + "Enter Product ID:");
                    int productID = Integer.parseInt(scanner.nextLine());
                    if(!facadeC.checkProductExist(SupplierID, productID)){
                        System.out.println("This Supplier Does Not Have This Product ID");
                        return;
                    }
                    System.out.println('\n' + "Enter Product Quantity:");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    facadeC.addProductToOrder(orderID,productID,quantity);
                    break;

                case 2:
                    System.out.println('\n' + "Enter Product ID You Would Like To Remove:");
                    productID = Integer.parseInt(scanner.nextLine());
                    if(!facadeC.checkProductExist(SupplierID, productID)){
                        System.out.println("This Supplier Does Not Have This Product ID");
                        return;
                    }
                    if(!facadeC.productInOrder(orderID,productID)){
                        System.out.println("This Product Does Not Exist In This Order, Deletion Failed");
                        return;
                    }
                    facadeC.removeFromOrder(productID,SupplierID);
                    break;

                case 3:
                    System.out.println('\n' + "Enter Product ID:");
                    productID = Integer.parseInt(scanner.nextLine());
                    if(!facadeC.checkProductExist(SupplierID, productID)){
                        System.out.println("This Supplier Does Not Have This Product ID");
                        return;
                    }
                    if(!facadeC.productInOrder(orderID,productID)){
                        System.out.println("This Product Does Not Exist In This Order, You Need To Add It First");
                        return;
                    }
                    System.out.println('\n' + "Enter New Product Quantity:");
                    quantity = Integer.parseInt(scanner.nextLine());
                    facadeC.updateProdQuantity(orderID,productID,quantity);
                    break;

                case 4:
                    String products = facadeC.showSupplierProducts(SupplierID);
                    System.out.println(products);
                    break;

                case 5:
                    finishOrder = true;
                    if(facadeC.isEmptyOrder(orderID)){
                        System.out.println('\n' + "No Products In This Order, This Order Will Be Deleted");
                        facadeC.removeOrder(orderID);
                    }
                    else{
                        facadeC.finalPriceForOrder(orderID, SupplierID);
                        System.out.println('\n' + "Order Summary: " + facadeC.showOrder(orderID));
                    }
                    break;
                default:
                    System.out.println("You Need To Choose Only 1-5");

            }
        }

    }

    public static void orderBySupp(int supID){
        System.out.println('\n' + "Please Choose One Of The Following Options : ");
        System.out.println("1. Show All From Supplier");
        System.out.println("2. Show Specific Order From Supplier");
        //TODO: check if the supplier exist

        int caseNumber = Integer.parseInt(scanner.nextLine());
        switch (caseNumber) {
            case 1:
                facadeC.showOrdersBySupplier(supID);
                break;

            case 2:
                System.out.println("Enter Order ID You Would Like To Show: ");
                int orderID = Integer.parseInt(scanner.nextLine());
                facadeC.showOrder(orderID);
                break;

        }
    }


//    public static void printResult(String msg) {
//        System.out.println(msg);
//    }

}


