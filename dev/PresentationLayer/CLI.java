package PresentationLayer;


import BuisnessLayer.EmployeesBuisnessLayer.FacadeController;
import BuisnessLayer.EmployeesBuisnessLayer.ResponseT;
import PresentationLayer.DeliveryPresentationLayer.DeliveryCLI;
import PresentationLayer.EmployeesPresentationLayer.CLIController;
import PresentationLayer.EmployeesPresentationLayer.EmployeeCLI;

import java.util.Scanner;

public class CLI {
    EmployeeCLI employeeCLI;
    DeliveryCLI deliveryCLI;
//    private String userID;
//

    Scanner scanner;


    public CLI(){
        employeeCLI = EmployeeCLI.getInstance();
        deliveryCLI = DeliveryCLI.getInstance();
        scanner = new Scanner(System.in);


    }

    private boolean isIdValid(String id){
        return id.length() == 9;
    }

    public void start() {

        String ID;
        int action;

        do {
            employeeCLI.DisloginMenu();
//            ID = scanner.next();
            ID = "205952971";
            if(ID.equals("0"))
                break;
            ResponseT<Boolean> r = FacadeController.getInstance().isDeliveryManager(ID);
            while(r.isErrorOccured()) {
                System.out.println("ID not found, please try again");
                ID = scanner.next();
                r = FacadeController.getInstance().isDeliveryManager(ID);
            }
            if (r.getValue()) {
                do {
                    //The User is Delivery manager
                    deliveryCLI.runWithConsole();
                    action = scanner.nextInt();
                    if (action == 0)
                        break;
//                    scanner.nextLine();
//                    cliController.Mmainmanue(action);
                } while (true);
            } else {
                do {
                    employeeCLI.start(ID);
                    action = scanner.nextInt();
                    if (action == 0)
                        break;
                    scanner.nextLine();
                    employeeCLI.start(ID);
                } while (true);

            }
        }while (true);
    }




}
