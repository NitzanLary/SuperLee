package Employees.PresentationLayer;

import Employees.BuisnessLayer.FacadeController;

public class CLI {
    private static CLI cli = null;
    private CLIController cliController;
    MassageHandler mh;

    private CLI(){
        cliController = CLIController.getInstance();
        mh = System.out::println; // same as mg = s -> sout(s)
    }

    public static CLI getInstance(){
        if (cli == null)
            cli = new CLI();
        return cli;
    }

}
