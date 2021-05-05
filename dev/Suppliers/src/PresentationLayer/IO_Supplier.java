package PresentationLayer;
import BussinessLayer.FacadeController;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import java.util.HashMap;
import java.util.Scanner;
import static java.lang.System.exit;

/**
 * This is the presentation layer of the system.
 * The communication with the users are from here.
 */

public class IO_Supplier {

    private static IO_Supplier ioSupplier = null;
    public static FacadeController facadeC = FacadeController.getInstance();
    public static Scanner scanner = new Scanner(System.in);

    private IO_Supplier() {
        System.out.println('\n' + "----------------------------------------------------------");
        System.out.println('\n' +
                "0000  0  0  0000  0000  0000              0    0000  0000" + '\n' +
                "0     0  0  0  0  0     0  0              0    0     0   "+ '\n' +
                "0000  0  0  0000  0000  000     000000    0    0000  0000"+ '\n' +
                "   0  0  0  0     0     0 0               0    0     0   "+ '\n' +
                "0000  0000  0     0000  0  0              0000 0000  0000" + '\n');
        System.out.println("----------------------------------------------------------" + '\n');

        System.out.println('\n' + "Welcome To Super-Lee Supplier System!" + '\n' +
                "Choose What You Want To Do: "+ '\n');
    }

    public static IO_Supplier getInstance() {
        if (ioSupplier == null)
            ioSupplier = new IO_Supplier();

        return ioSupplier;
    }

    public void init() {
        System.out.println( "1. Main Menu " + '\n' + "2. Load Data example " );
        try{
            int caseNumber = Integer.parseInt(scanner.nextLine());
            switch (caseNumber) {
                case 2:
                    baseScenario();
                    ioSupplier.mainMenu();
                    break;

                case 1:
                    ioSupplier.mainMenu();
                    break;

                default:
                    System.out.println("Please Choose Only 1-2" + '\n');
                    init();
            }
        }catch(Exception e){
            System.out.println("Invalid Input - Please Enter 1-2 " + '\n');
            init();
        }


    }

    public void baseScenario() {

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

    public void mainMenu() {
        try{
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
        }catch (Exception e){
            System.out.println("Invalid Input - Please Enter 1-3 " + '\n');
            mainMenu();
        }

    }

    public void suppliersAndProducts() {

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
        try{
            int caseNumber = Integer.parseInt(scanner.nextLine());

            switch (caseNumber) {
                case 1:
                    createSupplierCard();
                    break;

                case 2:
                    System.out.println('\n' + "Enter Supplier ID You Would Like To Delete: ");
                    int SupplierID = Integer.parseInt(scanner.nextLine());
                    Response response = facadeC.deleteSupCard(SupplierID);
                    if (response.ErrorMessage != null) {
                        System.out.println(response.ErrorMessage);
                        return;
                    }
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
                    response = facadeC.deleteBillOfQuantity(SuppID);
                    if (response.ErrorMessage != null) {
                        System.out.println(response.ErrorMessage);
                        return;
                    }
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
                    response = facadeC.removeProductToSupplier(SupplID,pid);
                    if (response.ErrorMessage != null) {
                        System.out.println(response.ErrorMessage);
                        return;
                    }
                    break;

                case 9:
                    System.out.println('\n' + "Enter Supplier ID You Would Like To See His Product: ");
                    int SupID = Integer.parseInt(scanner.nextLine());
                    ResponseT<String> res = facadeC.showSupplierProducts(SupID);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    String allSuppliersProd = res.value;
                    System.out.println(allSuppliersProd);
                    break;

                case 10:
                    res = facadeC.showAllSupplier();
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    System.out.println('\n' + res.value);
                    break;

                case 11:
                    System.out.println("Enter Supplier ID You Would Like To See His Details: ");
                    int SupId = Integer.parseInt(scanner.nextLine());
                    ResponseT<String> resp = facadeC.showSupplierCard(SupId);
                    if (resp.ErrorMessage != null) {
                        System.out.println(resp.ErrorMessage);
                        return;
                    }
                    System.out.println(resp.value);
                    break;

                case 12:
                    return;

                default:
                    System.out.println("You Need To Choose Only 1-12");
            }
        }catch (Exception e){
            System.out.println("Invalid Input - Please Enter 1-3 " + '\n');
            suppliersAndProducts();
        }

    }

    public void orders(){
        System.out.println('\n' + "Please Choose One Of The Following Options : ");
        System.out.println("1. Create New Order");
        System.out.println("2. Delete Exist Order");
        System.out.println("3. Show All Orders From Suppliers");
        System.out.println("4. Show Order By Supplier");
        System.out.println("5. Back To Main Menu ");
        try{
            int caseNumber = Integer.parseInt(scanner.nextLine());
            switch (caseNumber) {
                case 1:
                    creatNewOrder();
                    break;

                case 2:
                    System.out.println('\n' + "Enter Order ID You Would Like To Delete: ");
                    int orderID = Integer.parseInt(scanner.nextLine());
                    Response res = facadeC.removeOrder(orderID);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    System.out.println("Deleted Order Successfully");
                    break;

                case 3:
                    ResponseT<String> resp = facadeC.showAllOrders();
                    if (resp.ErrorMessage != null) {
                        System.out.println(resp.ErrorMessage);
                        return;
                    }
                    System.out.println(resp.value);
                    break;

                case 4:
                    System.out.println('\n' + "Enter Supplier ID: ");
                    int supID = Integer.parseInt(scanner.nextLine());
                    if(!checkSupExist(supID)){return;}
                    orderBySupp(supID);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("You Need To Choose Only 1-5");
            }
        }catch (Exception e){
            System.out.println("Invalid Input - Please Enter 1-5 " + '\n');
            orders();
        }

    }

    public void createSupplierCard() {
        try{
            System.out.println('\n' + "Enter Supplier ID: ");
            int SupplierID = Integer.parseInt(scanner.nextLine());

            Response res = facadeC.checkSuppNotExist(SupplierID);
            if( res.ErrorMessage != null){
                System.out.println(res.ErrorMessage);
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
            res = facadeC.createSupCard(SupplierName,SupplierID,Address,mail,bankAcc,payment,contacts,infoSupplyDay,pickUp);
            if( res.ErrorMessage != null)
                System.out.println(res.ErrorMessage);
        }catch (Exception e){
            System.out.println("Invalid Input, Please Try Again" + '\n');
            createSupplierCard();
        }
    }

    public void updateSupplier(){
        try{
            System.out.println('\n' + "Enter Supplier ID You Would Like To Update: ");
            int SupplierID = Integer.parseInt(scanner.nextLine());

            if(!checkSupExist(SupplierID)){return;}

            System.out.println("1. Edit Supplier Name");
            System.out.println("2. Edit Supplier Address");
            System.out.println("3. Edit Supplier Email");
            System.out.println("4. Edit Supplier Bank Account");
            System.out.println("5. Edit Supplier Payment Method");
            System.out.println("6. Edit Supplier Contacts ");
            System.out.println("7. Edit Info Supply Day");
            System.out.println("8. Edit Supplier Pick Up");

            int caseNumber = Integer.parseInt(scanner.nextLine());
            Response res;

            switch (caseNumber) {
                case 1:
                    System.out.println("Enter New Name: ");
                    String name = scanner.nextLine();
                    res = facadeC.EditSupplierName(SupplierID, name);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
                    break;

                case 2:
                    System.out.println("Enter New Address: ");
                    String address = scanner.nextLine();
                    res = facadeC.EditAddress(SupplierID, address);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
                    break;

                case 3:
                    System.out.println("Enter New Email: ");
                    String mail = scanner.nextLine();
                    res = facadeC.EditEmail(SupplierID, mail);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
                    break;

                case 4:
                    System.out.println("Enter New Bank Account: ");
                    int bank = Integer.parseInt(scanner.nextLine());
                    res = facadeC.EditBankAccount(SupplierID, bank);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
                    break;

                case 5:
                    System.out.println("Enter New Payment Method: ");
                    String pay = scanner.nextLine();
                    res = facadeC.EditPaymentMethod(SupplierID, pay);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
                    break;

                case 6:
                    System.out.println("Enter New Contacts: ");
                    String contacts = scanner.nextLine();
                    res  = facadeC.EditContact(SupplierID, contacts);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
                    break;

                case 7:
                    System.out.println("Enter New Info Supply Day: ");
                    String supp = scanner.nextLine();
                    res = facadeC.EditInfoSupDay(SupplierID, supp);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
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
                    res = facadeC.EditPickup(SupplierID, pickUp);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                    }
                    break;
                default:
                    System.out.println("You Need To Choose Only 1-8");
            }
        }catch (Exception e){
            System.out.println("Invalid Input, Please Enter 1-8 Only");
            updateSupplier();
        }

    }

    public void createBillOfQ() {
        try{
            System.out.println("Enter Supplier ID: ");
            int SupplierID = Integer.parseInt(scanner.nextLine());

            if (!checkSupExist(SupplierID)) {
                return;
            }
            Response res = facadeC.checkBillExist(SupplierID);
            if (res.ErrorMessage != null) {
                System.out.println(res.ErrorMessage);
                return;
            }

            boolean exit = false;
            HashMap<Integer, Integer> minQuantityForDis = new HashMap<>();
            HashMap<Integer, Integer> discountList = new HashMap<>();

            while (!exit) {
                System.out.println("Enter Product ID Which You Want To Make Discount: ");
                int ProdID = Integer.parseInt(scanner.nextLine());
                res = facadeC.checkProductExist(SupplierID, ProdID);
                if (res.ErrorMessage != null) {
                    System.out.println(res.ErrorMessage);
                    return;
                }
                // if the user already insert this product to the supplier's bill of quantity
                if (minQuantityForDis.get(ProdID) != null) {
                    System.out.println("This Product Already Has A Discount");
                    return;
                }

                System.out.println("Enter The Minimum Amount Of Ordering This Product For The Discount: ");
                int minDis = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter The Discount In Percentage, 1-100: ");
                int percentage = Integer.parseInt(scanner.nextLine());
                if (percentage < 1 || percentage > 100) {
                    System.out.println("Invalid Discount");
                    return;
                }

                minQuantityForDis.put(ProdID, minDis);
                discountList.put(ProdID, percentage);

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
            res = facadeC.addBillOfQuantity(SupplierID, minQuantityForDis, discountList);
            if (res.ErrorMessage != null) {
                System.out.println(res.ErrorMessage);
                return;
            }
        }catch (Exception e){
            System.out.println("Invalid Input, Please Try Again" + '\n');
            createBillOfQ();
        }
    }

    public void updateBill(){
        try{
            System.out.println('\n' + "Enter Supplier ID You Would Like To Update His Bill Of Quantity: ");
            int SupplierID = Integer.parseInt(scanner.nextLine());

            Response res = facadeC.checkBillNotExist(SupplierID);
            if (res.ErrorMessage != null) {
                System.out.println(res.ErrorMessage);
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
                    res = facadeC.checkProductInBillOfQ(SupplierID, pid);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    System.out.println("Enter New Minimum Quantity: ");
                    int min = Integer.parseInt(scanner.nextLine());
                    res = facadeC.editMinQuantity(SupplierID, pid, min);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    break;

                case 2:
                    System.out.println("Enter Product ID: ");
                    pid = Integer.parseInt(scanner.nextLine());
                    res = facadeC.checkProductInBillOfQ(SupplierID, pid);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    System.out.println("Enter New Discount: ");
                    int discount = Integer.parseInt(scanner.nextLine());
                    if(discount<1 || discount> 100){
                        System.out.println("Invalid Discount");
                        return;
                    }
                    res = facadeC.editDiscount(SupplierID, pid, discount);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Enter Product ID: ");
                    pid = Integer.parseInt(scanner.nextLine());
                    res = facadeC.checkProductInBillOfQ(SupplierID, pid);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    // if the user already insert this product to the supplier's bill of quantity
                    res = facadeC.checkProductInBillOfQ(SupplierID,pid);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
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
                    res = facadeC.addProdToBill(SupplierID, pid, minQ, disc);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    break;

                case 4:
                    System.out.println("Enter Product ID: ");
                    pid = Integer.parseInt(scanner.nextLine());
                    res = facadeC.removeProdFromBill(SupplierID, pid);
                    if (res.ErrorMessage != null) {
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    break;

                default:
                    System.out.println("You Need To Choose Only 1-4");

            }
        }catch (Exception e){
            System.out.println("Invalid Input, Please Enter 1-4 Only");
            updateBill();
        }

    }

    public void addProdToSupp(){
        try{
            System.out.println("Enter Supplier ID: ");
            int SupplierID = Integer.parseInt(scanner.nextLine());

            if(!checkSupExist(SupplierID)){return;}

            System.out.println("Enter Product ID: ");
            int pid = Integer.parseInt(scanner.nextLine());

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

            Response res = facadeC.addProductToSupplier(SupplierID,pid,name,category,price);
            if (res.ErrorMessage != null) {
                System.out.println(res.ErrorMessage);
                return;
            }
        }catch (Exception e) {
            System.out.println("Invalid Input, Please Try Again");
            addProdToSupp();
        }
    }

    public void creatNewOrder(){
        try{
            System.out.println('\n' + "Enter Supplier ID Which You Would Like To Take Order From: ");
            int SupplierID = Integer.parseInt(scanner.nextLine());
            if(!checkSupExist(SupplierID)){return;}
            ResponseT<Integer> res = facadeC.createOrder(SupplierID);
            if (res.ErrorMessage != null) {
                System.out.println(res.ErrorMessage);
                return;
            }
            int orderID = res.value;
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
                        Response response = facadeC.checkProductExist(SupplierID, productID);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        System.out.println('\n' + "Enter Product Quantity:");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        response = facadeC.addProductToOrder(orderID,productID,quantity);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        break;

                    case 2:
                        System.out.println('\n' + "Enter Product ID You Would Like To Remove:");
                        productID = Integer.parseInt(scanner.nextLine());
                        response = facadeC.checkProductExist(SupplierID, productID);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        response = facadeC.productInOrder(orderID,productID);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        response = facadeC.removeFromOrder(productID,SupplierID);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        break;

                    case 3:
                        System.out.println('\n' + "Enter Product ID:");
                        productID = Integer.parseInt(scanner.nextLine());
                        response = facadeC.checkProductExist(SupplierID, productID);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        response = facadeC.productInOrder(orderID,productID);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        System.out.println('\n' + "Enter New Product Quantity:");
                        quantity = Integer.parseInt(scanner.nextLine());
                        response = facadeC.updateProdQuantity(orderID,productID,quantity);
                        if (response.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        break;

                    case 4:
                        ResponseT<String> resp = facadeC.showSupplierProducts(SupplierID);
                        if (resp.ErrorMessage != null) {
                            System.out.println(res.ErrorMessage);
                            return;
                        }
                        System.out.println(resp.value);
                        break;

                    case 5:
                        finishOrder = true;
                        if(facadeC.isEmptyOrder(orderID)){
                            System.out.println('\n' + "No Products In This Order, This Order Will Be Deleted");
                            response = facadeC.removeOrder(orderID);
                            if (response.ErrorMessage != null) {
                                System.out.println(res.ErrorMessage);
                                return;
                            }
                        }
                        else{
                            response = facadeC.finalPriceForOrder(orderID, SupplierID);
                            if (response.ErrorMessage != null) {
                                System.out.println(res.ErrorMessage);
                                return;
                            }
                            resp = facadeC.showOrder(orderID);
                            if (resp.ErrorMessage != null) {
                                System.out.println(res.ErrorMessage);
                                return;
                            }
                            System.out.println('\n' + "Order Summary: " + resp.value);
                        }
                        break;
                    default:
                        System.out.println("You Need To Choose Only 1-5");

                }
            }
        }catch (Exception e){
            System.out.println("Invalid Input, Please Try Again");
            creatNewOrder();
        }


    }

    public void orderBySupp(int supID){
        try{
            System.out.println('\n' + "Please Choose One Of The Following Options : ");
            System.out.println("1. Show All From Supplier");
            System.out.println("2. Show Specific Order From Supplier");

            int caseNumber = Integer.parseInt(scanner.nextLine());
            switch (caseNumber) {
                case 1:
                    ResponseT<String> res = facadeC.showOrdersBySupplier(supID);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    System.out.println(res.value);
                    break;

                case 2:
                    System.out.println("Enter Order ID You Would Like To Show: ");
                    int orderID = Integer.parseInt(scanner.nextLine());
                    res = facadeC.showOrder(orderID);
                    if(res.ErrorMessage != null){
                        System.out.println(res.ErrorMessage);
                        return;
                    }
                    System.out.println(res.value);
                    break;

            }
        }catch (Exception e){
            System.out.println("Invalid Input, Please Enter 1-2");
            orderBySupp(supID);
        }

    }

    private boolean checkSupExist(int SupplierID){
        Response res = facadeC.checkSuppExist(SupplierID);
        if(res.ErrorMessage != null){
            System.out.println(res.ErrorMessage);
            return false;
        }
        return true;
    }


}


