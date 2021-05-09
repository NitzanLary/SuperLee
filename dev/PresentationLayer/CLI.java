package PresentationLayer;


import PresentationLayer.DeliveryPresentationLayer.DeliveryCLI;
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









}
