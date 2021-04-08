package Employees.PresentationLayer;

import Employees.BuisnessLayer.FacadeController;

import java.util.Scanner;

public class CLI {
    private static CLI cli = null;
    private CLIController cliController;
    MassageHandler mh;
    Scanner scanner;

    private CLI(){
        cliController = CLIController.getInstance();
        mh = System.out::println; // same as mg = s -> sout(s)
        scanner = new Scanner(System.in);
    }

    public static CLI getInstance(){
        if (cli == null)
            cli = new CLI();
        return cli;
    }

    public void initData(){
        CLIController.initData();
    }

//    public void start(){
//        int action;
//        do {
//
//        }
//    }

    private void displayMain(){
//        mh.print("")
    }

}
