package PresentationLayer;


import BuisnessLayer.EmployeesBuisnessLayer.FacadeController;
import BuisnessLayer.EmployeesBuisnessLayer.ResponseT;
import PresentationLayer.DeliveryPresentationLayer.DeliveryCLI;
import PresentationLayer.EmployeesPresentationLayer.CLIController;
import PresentationLayer.EmployeesPresentationLayer.EmployeeCLI;

import java.util.Scanner;

public class CLI {
    EmployeeCLI employeeCLI = null;
    DeliveryCLI deliveryCLI = null;
    private String userID;
    Scanner scanner;


    private CLI(){
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
            ID = scanner.next();
            if(ID.equals("0"))
                break;
            ResponseT<Boolean> r = FacadeController.getInstance().isDeliveryManager(ID);
            while(r.isErrorOccured()) {
                System.out.println("ID not found, please try again");
                ID = scanner.next();
                r = cliController.checkAuthorizedHrOrGenral(ID);
            }
            if (r.getValue()) {
                do {
                    //The User is Hr or General manager
                    DisMmainMenu();
                    action = scanner.nextInt();
                    if (action == 0)
                        break;
                    scanner.nextLine();
                    cliController.Mmainmanue(action);
                } while (true);
            } else {
                do { //TODO
                    //The User is Regular Employee (not Hr or General manager)
                    DisEmainMenu();
                    action = scanner.nextInt();
                    if (action == 0)
                        break;
                    scanner.nextLine();
                    cliController.EmainMenu(action);
                } while (true);

            }
        }while (true);
    }






    }









}
