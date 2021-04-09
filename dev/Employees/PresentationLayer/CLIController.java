package Employees.PresentationLayer;

import Employees.BuisnessLayer.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CLIController {
    private static CLIController clientController = null;
    private CLI cli;
    private FacadeController facade;



    private String userID;



    private CLIController(){
        facade = FacadeController.getInstance();
    }

    public static CLIController getInstance(){
        if (clientController == null)
            clientController = new CLIController();
        return clientController;
    }

    public void setUserID(String userID) {
        cli = CLI.getInstance();
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }


    public void initData() {
        facade.initData();
    }

    public ResponseT<Boolean> checkAuthorizedHrOrGenral(String id){
        return facade.checkAuthorizationBool(id);
    }


    public void Mmainmanue(int action) {
        if (action == 1){
            cli.MempMenu();
        }
        if (action == 2){
            cli.MshiftsMenu();
        }
    }

    public void MempMenu(int action) {
        if (action == 1){ addNewEmployee(); }
        if (action == 2){ cli.MempUpdateMenu(); }

    }

    public void MupdateEmployeeMenu(int action) {
        if (action == 1){ updateEmployeeName(); }
        if (action == 2){ updateEmployeeBankAc(); }
        if (action == 3){ updateEmployeeSalary(); }
        if (action == 4) { updateEmployeeSickDays(); }
        if (action == 5) { updateEmployeeStudyFound(); }
        if (action == 6) { updateEmployeeDaysOff(); }
        if (action == 7) { updateEmployeeRole(); }
    }

    public void MshiftsMenu(int action) {
        // get shift
        if (action == 1)
            handleSingleShiftMenu();
        if (action == 2)
            cli.weeksMenu();
        if (action == 3)
            generate1weeklyShift();
    }

    private void generate1weeklyShift() {
        Response r = facade.generate1weeklyShifts(userID);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else
            cli.print("The generation has done");
    }

    private char getShiftType(){
        char type = cli.getChar("Enter 'M' for Morning shift or 'E' for Evening shift");
        while(type != 'E' && type != 'M'){
            type = cli.getChar("Enter 'M' for Morning shift or 'E' for Evening shift");
        }
        return type;
    }

    private void handleSingleShiftMenu() {
        LocalDate date = cli.getDate("Please enter date");
        char type = getShiftType();
        ResponseT<Shift> shift = facade.getShift(date, type);
        if (shift.isErrorOccured()) {
            cli.print(shift.getErrorMessage());
            return;
        }
        cli.MSingleShiftMenu(shift.getValue());
    }


    public void EmainMenu(int action) {
        //Show employee information
        if (action == 1){ cli.print(showAllMyInformation()); }
        //Show employee preferences for a shift
        if (action == 2){ cli.print(showMyPreferences()); }
        //Assign employee preferences for a specific shift
        if (action == 3){ assignPreferenceForShift(); }
        // Show colleagues whom the employee work with in a shift
        if (action == 4) { cli.print(showColleaguesWorkWithMe()); }


    }

    public void addNewEmployee(){
        String EmpID = cli.getString("Enter employee ID:");
        String name = cli.getString("Enter employee name:");
        String bankAccount = cli.getString("Enter employee's bank account:");

        int salary = cli.getInt("Enter employee salary:");
        while (!isSalaryValid(salary))
            salary = cli.getInt("Invalid value Enter employee salary:");

        int sickDays = cli.getInt("Enter employee's sick days:");
        while(!isValidSickDays(sickDays))
            sickDays = cli.getInt("Invalid value Enter employee's sick days:");

        int studyFund = cli.getInt("Enter employee's study fund:");
        while(!isValidFund(studyFund))
            studyFund = cli.getInt("Invalid value Enter employee's study fund:");

        int daysOff = cli.getInt("Enter employee's days off:");
        while(!isValidDaysOff(daysOff))
            daysOff = cli.getInt("Invalid value Enter employee's days off:");

        String roleName = cli.getString("Enter employee's Role:");
        LocalDate dateOfHire = cli.getDate("Enter employee's Date of Hire");
        Response r = facade.addEmployee(clientController.userID, EmpID, name, bankAccount, salary, sickDays, studyFund,
                            daysOff, roleName, dateOfHire);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee added");
    }

    private boolean isValidDaysOff(int daysOff) {
        return daysOff > 0;
    }

    private boolean isValidFund(int studyFund) {
        return studyFund > 0;
    }

    private boolean isValidSickDays(int sickDays) {
        return sickDays > 0;
    }

    private boolean isSalaryValid(int salary) {
        return salary > 0;
    }


    public void updateEmployeeName(){
        String EmpID = cli.getString("Enter employee's ID:");
        String newEmpID = cli.getString("Enter new employee's name:");
        Response r = facade.updateEmpName(clientController.userID, EmpID, newEmpID);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee updated");
    }

    public void updateEmployeeBankAc(){
        String EmpID = cli.getString("Enter employee's ID:");
        String newBankAc = cli.getString("Enter new employee's bank account:");
        Response r = facade.updateEmpBankAccount(clientController.userID, EmpID, newBankAc);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee updated");
    }

    public void updateEmployeeSalary(){
        String EmpID = cli.getString("Enter employee's ID:");
        int newSalary = cli.getInt("Enter new employee's salary:");
        Response r =facade.updateEmpSalary(clientController.userID, EmpID, newSalary);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee updated");
    }

    public void updateEmployeeSickDays(){
        String EmpID = cli.getString("Enter employee's ID:");
        int newSickDays = cli.getInt("Enter new employee's sick Days:");
        Response r =facade.updateEmpSickDays(clientController.userID, EmpID, newSickDays);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee updated");
    }

    public void updateEmployeeStudyFound(){
        String EmpID = cli.getString("Enter employee's ID:");
        int newStudyFound = cli.getInt("Enter new employee's study Found:");
        Response r = facade.updateEmpStudyFund(clientController.userID, EmpID, newStudyFound);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee updated");
    }

    public void updateEmployeeDaysOff(){
        String EmpID = cli.getString("Enter employee's ID:");
        int newDaysOff = cli.getInt("Enter new employee's days off:");
        Response r = facade.updateEmpDaysOff(clientController.userID, EmpID, newDaysOff);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee updated");
    }


    public void updateEmployeeRole(){
        String EmpID = cli.getString("Enter employee's ID:");
        String newRole = cli.getString("Enter new employee's Role:");
        Response r = facade.addRoleToEmp(clientController.userID, EmpID, newRole);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Employee updated");
    }


    public String showAllMyInformation(){
        ResponseT<String> r = facade.getMyData(clientController.userID);
        if(r.isErrorOccured())
            return r.getErrorMessage();
        else
            return facade.getMyData(clientController.userID).getValue() + "\n";
    }

    public String showMyPreferences(){
        LocalDate shiftDate = cli.getDate("Enter Shift Date ID");
//        String MorningEvning = cli.getChar("Enter M for morning shift or Enter E for evening shift"); // todo: check if works after changed to char
        char MorningEvning = getShiftType();
        if (MorningEvning == ('M')){
            return facade.getMyPreferences(userID, shiftDate,
                    LocalTime.of(6,0), LocalTime.of(14,0)).getValue() + "\n";
        }
        else
            return facade.getMyPreferences(userID, shiftDate,
                    LocalTime.of(14,0), LocalTime.of(22,0)).getValue() + "\n";

    }


    public void assignPreferenceForShift(){
        LocalDate shiftDate = cli.getDate("Enter Shift Date ID");
//        String MorningEvning = cli.getString("Enter M for morning shift || Enter E for evning shift"); // todo: check if works after changed to char
        char MorningEvning = getShiftType();
        int preference = cli.getInt("""
                Enter 0 if you CANT work on this shift
                Enter 1 if you CAN work on this shift
                Enter 2 if you WANT work on this shift
                """);
        if (MorningEvning == ('M')){
             facade.putConstrain(clientController.userID, shiftDate,
                    LocalTime.of(6, 0), LocalTime.of(14,0), preference);
        }
        else
            facade.putConstrain(clientController.userID, shiftDate,
                    LocalTime.of(14, 0), LocalTime.of(22,0), preference);
    }

    public String showColleaguesWorkWithMe(){
        LocalDate shiftDate = cli.getDate("Enter Shift Date ID");
//        String MorningEvning = cli.getString("Enter M for morning shift or Enter E for evening shift"); // todo: check if works after changed to char
        char MorningEvning = getShiftType();
        if (MorningEvning == ('M')){
            return facade.getWhoIWorkWith(clientController.userID, shiftDate,
                    LocalTime.of(6, 0), LocalTime.of(14,0)).getValue() + "\n";
        }
        else
            return facade.getWhoIWorkWith(clientController.userID, shiftDate,
                    LocalTime.of(14, 0), LocalTime.of(22,0)).getValue() + "\n";
    }

    public void MSingleShiftOptions(int action, Shift shift) {
        if (action == 1)
            assignEmployee(shift);
        if(action == 2)
            getEmployeesPreferences(shift);
        if(action == 3)
            closeShift(shift);
        if(action == 4)
            openShift(shift);
        if(action == 5)
            showStatus(shift);
        if(action == 6)
            getAssignedEmployees(shift);
    }

    private void getAssignedEmployees(Shift shift) {
        ResponseT<List<Employee>> employees = facade.getAssignedEmpForShift(shift.getDate(), shift.getStart(), shift.getEnd());
        if(employees.isErrorOccured()){
            cli.print(employees.getErrorMessage());
            return;
        }
        cli.displayEmployees(employees.getValue());
    }

    private void showStatus(Shift shift) {
        if(shift.isClosed())
            cli.print("Shift is close");
        else
            cli.print("Shift is open");
    }

    private void openShift(Shift shift) {
        Response r = facade.openShift(userID, shift.getDate(), shift.getStart(), shift.getEnd());
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        cli.print("Shift Opened");
    }

    private void closeShift(Shift shift) {
        Response r = facade.closeShift(userID, shift.getDate(), shift.getStart(), shift.getEnd());
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        cli.print("Shift Closed");
    }

    private void getEmployeesPreferences(Shift shift) {
        ResponseT<String> r = facade.getEmployeesConstrainsForShift(userID, shift.getDate(), shift.getStart(), shift.getEnd());
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        cli.print(r.getValue());
    }

    private void assignEmployee(Shift shift) {
        String empID = cli.getString("Enter the employee's ID");
        while(!isValidID(empID))
            empID = cli.getString("Invalid ID, please try again");
        String role = cli.getString("Enter employee's role you want to assign to");
        Response r = facade.assignEmpToShift(userID, empID, shift.getDate(), shift.getStart(), shift.getEnd(), role);
        if(r.isErrorOccured())
            cli.print(r.getErrorMessage());
        else cli.print("Assigning accomplished");
    }

    private boolean isValidID(String empID) {
        return true;
    }

    public void MWeeksMenu(int action) {
        ResponseT<List<WeeklyShifts>> weeklyShifts = facade.getFutureWeeklyShifts();
        if(weeklyShifts.isErrorOccured()) {
            cli.print(weeklyShifts.getErrorMessage());
            return;
        }
        if(action == 1)
            cli.displayWeekly(weeklyShifts.getValue().subList(0,1));
        if(action == 2)
            cli.displayWeekly(weeklyShifts.getValue().subList(0,2));
        handleSingleShiftMenu();
    }


//    public String showThisShiftStatus(){
//        return facade.getShiftStatuts(clientController.userID).getValue();
//    }











}
