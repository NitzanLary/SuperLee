package Employees.PresentationLayer;

import Employees.BuisnessLayer.FacadeController;

public class CLIController {
    private static CLIController clientController = null;
    private CLI cli;
    private FacadeController facade;
    MassageHandler mh;

    private CLIController(){
        cli = CLI.getInstance();
        facade = FacadeController.getInstance();
        mh = System.out::println; // same as mg = s -> sout(s)
    }

    public static CLIController getInstance(){
        if (clientController == null)
            clientController = new CLIController();
        return clientController;
    }


    public static void initData() {
        // todo
    }
}
