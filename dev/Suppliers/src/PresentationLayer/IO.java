package PresentationLayer;
import BussinessLayer.FacadeController;

import java.util.HashMap;
import java.util.Scanner;
import static java.lang.System.exit;

/**
 * This is the presentation layer of the system.
 * The communication with the users are from here.
 */

public class IO {

    private static IO io = null;
    public static FacadeController facadeC = FacadeController.getInstance();
    public static Scanner scanner = new Scanner(System.in);

    private IO() {
        System.out.println('\n' + "Welcome To Super-Lee Supplier System!" + '\n' +
                "Choose What You Want To Do: ");
    }

    public static IO getInstance() {
        if (io == null)
            io = new IO();

        return io;
    }

    public static void init() {
        System.out.println("1. Main Menu " + '\n' + "2. Load Data example " );

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

        facadeC.createSupCard("Sahar", 1234, "Raanana", "kalifa@gmail.com" , 45802000,
                "credit card","none" , "Sunday", false);


    }

    public static void mainMenu() {

        while (true) {
            System.out.println("1. Suppliers And Products");
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
                    System.out.println("You Are Log Out From The System, Have A Nice Day.\n");
                    exit(0);
                default:
                    System.out.println("You Need To Choose Only 1-3");
            }

        }
    }

    public static void suppliersAndProducts() {

        System.out.println("Please Choose One Of The Following Options : ");
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

        int caseNumber = Integer.parseInt(scanner.nextLine());

        switch (caseNumber) {
            case 1:
                createSupplierCard();
                break;

            case 2:
                System.out.println("Enter Supplier ID You Would Like To Delete: ");
                int SupplierID = Integer.parseInt(scanner.nextLine());
                facadeC.deleteSupCard(SupplierID);
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
                System.out.println("Enter Supplier ID You Would Like To See His Product: ");
                int SupID = Integer.parseInt(scanner.nextLine());
                String allSuppliersProd = facadeC.showSupplierProducts(SupID);
                System.out.println(allSuppliersProd);
                break;

            case 10:
                String allSuppliers = facadeC.showAllSupplier();
                System.out.println(allSuppliers);
                break;

            case 11:
                System.out.println("Enter Supplier ID You Would Like To See His Details: ");
                int SupId = Integer.parseInt(scanner.nextLine());
                String supCard = facadeC.showSupplierCard(SupId);
                System.out.println(supCard);
                break;

            default:
                System.out.println("You Need To Choose Only 1-11");
        }
    }

    public static void orders(){};

    public static void createSupplierCard() {
        System.out.println("Enter Supplier ID: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());

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
        }
        facadeC.createSupCard(SupplierName,SupplierID,Address,mail,bankAcc,payment,contacts,infoSupplyDay,pickUp);
    }

    public static void updateSupplier(){
        System.out.println("Enter Supplier ID You Would Like To Update: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());

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
                }
                facadeC.EditPickup(SupplierID, pickUp);
                break;
        }
    }

    public static void createBillOfQ() {
        System.out.println("Enter Supplier ID: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());
        boolean flagSup = facadeC.checkSuppExist(SupplierID);
        boolean flagBill = facadeC.checkSuppExist(SupplierID);

        if(!flagSup){ System.out.println("This Supplier Did Not Exist In The System");}
        if(flagBill){ System.out.println("This Supplier Already Have Bill Of Quantity");}

        else{
            boolean exit = false;
            HashMap<Integer, Integer> minQuantityForDis = new HashMap<>();
            HashMap<Integer, Integer> discountList = new HashMap<>();
            while(!exit){
                System.out.println("Enter Product ID Which You Want To Make Discount: ");
                int ProdID = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter The Minimum Amount Of Ordering This Product For The Discount? : ");
                int minDis = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter The Discount In Percentage: ");
                int percentage = Integer.parseInt(scanner.nextLine());

                minQuantityForDis.put(ProdID,minDis);
                discountList.put(ProdID,percentage);

                System.out.println("Do You Want To Add Another Product? " + '\n' + "1. Yes" + '\n' + "2. No");
                int caseNumber = Integer.parseInt(scanner.nextLine());
                switch (caseNumber) {
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
        System.out.println("Enter Supplier ID You Would Like To Update His Bill Of Quantity: ");
        int SupplierID = Integer.parseInt(scanner.nextLine());

        System.out.println("1. Edit Minimum Quantity For Product");
        System.out.println("2. Edit Discount For Product");
        System.out.println("3. Add New Product To Bill Of Quantity");
        System.out.println("4. Delete Product From Bill Of Quantity");

        int caseNumber = Integer.parseInt(scanner.nextLine());

        switch (caseNumber) {
            case 1:
                System.out.println("Enter Product ID: ");
                int pid = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter New Minimum Quantity: ");
                int min = Integer.parseInt(scanner.nextLine());
                facadeC.editMinQuantity(SupplierID, pid, min);
                break;

            case 2:
                System.out.println("Enter Product ID: ");
                int pid2 = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter New Discount: ");
                int discount = Integer.parseInt(scanner.nextLine());
                facadeC.editMinQuantity(SupplierID, pid2, discount);
                break;

            case 3:
                System.out.println("Enter Product ID: ");
                int pid3 = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter New Minimum Quantity:");
                int minQ = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter New Discount: ");
                int disc = Integer.parseInt(scanner.nextLine());
                facadeC.addProdToBill(SupplierID, pid3, minQ, disc);
                break;

            case 4:
                System.out.println("Enter Product ID: ");
                int pid4 = Integer.parseInt(scanner.nextLine());
                facadeC.removeProdFromBill(SupplierID, pid4);
                break;

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

        System.out.println("Enter Product Name : ");
        String name = scanner.nextLine();

        System.out.println("Enter Product Category : ");
        String category = scanner.nextLine();

        System.out.println("Enter Product Price : ");
        double price = Integer.parseInt(scanner.nextLine());

        facadeC.addProductToSupplier(SupplierID,pid,name,category,price);
    }



//    public static void printResult(String msg) {
//        System.out.println(msg);
//    }

}


