package Employees.PresentationLayer;

import Employees.BuisnessLayer.EmployeeController;
import Employees.BuisnessLayer.FacadeController;

import java.time.LocalDate;
import java.util.Scanner;

public class CLI {
    private static CLI cli = null;
    private CLIController cliController;
    private String userID;
    Scanner scanner;

    private CLI(){
        cliController = CLIController.getInstance();
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

    private boolean isNameValid(String name){
        return name != null && !name.equals("") && !name.equals(" ");
    }

    private boolean isIdValid(String id){
        return id.length() == 9;
    }

//    private boolean isDateValid(LocalDate date){
//        LocalDate now = LocalDate.now();
//        return true;
//    }



    //starts the login menu of the program
    public void start() {
        String ID;
        int action;
        DisloginMenu();
        ID = scanner.next();
        cliController.setUserID(ID);
        if(cliController.checkAuthorizedHrOrGenral(ID).getValue()) {
            do {
                //The User is Hr or General manager
                DisMmainMenu();
                action = scanner.nextInt();
                if (action == 3)
                    break;
                scanner.nextLine();
                cliController.Mmainmanue(action);
            } while (true);
        }

        else {
            do { //TODO
                //The User is Regular Employee (not Hr or General manager)
                DisEmainMenu();
                action = scanner.nextInt();
                if (action == 9)
                    break;
                scanner.nextLine();
                cliController.Mmainmanue(action);
            } while (true);

        }
    }

    //------------------------------MANAGER ONLY----------------------------------

    public void MempMenu() {
        int action;
        do {
            DisMempMenu();
            action = scanner.nextInt();
            if (action == 6)
                break;
            scanner.nextLine();
            cliController.MempMenu(action);
        } while (true);

    }

    public void MempUpdateMenu() {
        int action;
        do {
            DisMempUpdateMenu();
            action = scanner.nextInt();
            if (action == 8)
                break;
            scanner.nextLine();
            cliController.MupdateEmployeeMenu(action);
        } while (true);

    }

    public void MshiftsMenu() {
        int action;
        do {
            DisMshiftsMenu();
            action = scanner.nextInt();
            if (action == 4)
                break;
            scanner.nextLine();
            cliController.MshiftsMenu(action);
        } while (true);

    }

    public void MfutureShiftsMenu() {
        int action;
        do {
            DisMshiftsMenu();
            action = scanner.nextInt();
            if (action == 3)
                break;
            scanner.nextLine();
            cliController.MfutureShiftsMenu(action);
        } while (true);

    }

    public void MsingleShiftMenu() {
        int action;
        do {
            DisMsingleShiftMenu();
            action = scanner.nextInt();
            if (action == 6)
                break;
            scanner.nextLine();
            cliController.MfutureShiftsMenu(action);
        } while (true);

    }






    public void DisloginMenu(){
        System.out.println("""
                Welcome!
                Please Enter ID:
                """);
    }

    public void DisMmainMenu(){
        System.out.println("""
                1) Employees Menu
                2) Shifts Menu
                3) Exit""");
    }



    public void DisMempMenu(){
        System.out.println("""
                1) Add new employee to the system
                2) update or edit existing employee
                3) Exit""");
    }

    public void DisMempUpdateMenu(){
        System.out.println("""
                1) Update employee name
                2) Update employee bank account
                3) Update employee salary
                4) Update employee sick days
                5) Update employee study found
                6) Update employee days off
                7) Update employee role
                8) Exit""");
    }

    public void DisMshiftsMenu(){
        System.out.println("""
                1) Choose shift by date
                2) Choose shift from future shifts
                3) Generate 1 Week Shifts to future shifts
                4) Exit""");
    }

    public void  DisMfutureShiftMenu(){
        System.out.println("""
                1) This week shifts 
                1) Next week shifts
                2) Week after next week shifts
                3) Exit""");
    }

    public void  DisWeeksMenu(){
        System.out.println("""
                1) Next Week Shifts
                2) Week after next week shifts
                3) Exit""");
    }

    public void  DisMsingleShiftMenu(){
        System.out.println("""
                1) Show this shift's status 
                2) Show employees preferences for this shift 
                3) Assign employee to this shift 
                4) Change this shift's status to CLOSE
                5) Change this Shift's status to OPEN
                6) Exit""");
    }

    //----------------------------------------------------------------------------




    //------------------------------EMPLOYEE ONLY---------------------------------

    public void EmainMenu() {
        int action;
        do {
            DisEmainMenu();
            action = scanner.nextInt();
            if (action == 5)
                break;
            scanner.nextLine();
            cliController.EmainMenu(action);
        } while (true);

    }








    public void DisEmainMenu(){
        System.out.println("""
                1) Show all my information
                2) Show my preferences for a shift
                3) Apply preferences for a shift
                4) Show colleagues whom work with me in a shift
                5) Exit""");
    }

    //-----------------------------------------------------------------------------

    //display `msg` to the user and returns an int read from the user
    public int getInt(String msg) {
        System.out.println(msg);
        int out = scanner.nextInt();
        scanner.nextLine();
        return out;
    }

    //display `msg` to the user and returns a double read from the user
    public double getDouble(String msg) {
        System.out.println(msg);
        return scanner.nextDouble();
    }

    //display `msg` to the user and returns a string read from the user
    public String getString(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    //display `msg` to the user and returns LocalDate read from the user
    public LocalDate getDate(String msg) {
        System.out.println(msg);
        System.out.println("Enter the day:");
        int day = scanner.nextInt();
        System.out.println("Enter the month:");
        int month = scanner.nextInt();
        System.out.println("Enter the year:");
        int year = scanner.nextInt();
        return LocalDate.of(year,month,day);
    }
    //display `msg` to the user
    public void print(String msg) {
        System.out.println(msg);
    }



}









