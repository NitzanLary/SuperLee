package Employees.BuisnessLayer;

import java.time.LocalDateTime;
import java.util.List;

public class FacadeController {
    private static FacadeController facadeController = null;
    private EmployeeController employeeController;
    private ShiftController shiftController;

    private FacadeController(){
        employeeController = EmployeeController.getInstance();
        shiftController = ShiftController.getInstance();
    }


    public static FacadeController getInstance(){
        if (facadeController == null)
            facadeController = new FacadeController();
        return facadeController;
    }

    public Response AddEmployee(String ID, String name, String bankAccount, int salary,
                                int sickDays, int studyFund, int daysOff, String roleName, LocalDateTime _dateOfHire){
        return null;
    } // only HR and general manager

    public Response updateEmpName(String userID, String EmpID, String newName){
        return null;
    } // only HR and general manager

    public Response updateEmpBankAccount(String userID, String EmpID, String newBankAccount) {
        return null;
    } // only HR and general manager

    public Response updateEmpSalary(String userID, String EmpID, int newSalary) {
        return null;
    } // only HR and general manager

    public Response updateEmpSickDays(String userID, String EmpID, int sickDays){
        return null;
    } // only HR and general manager

    public Response updateEmpStudyFund(String userID, String EmpID, int newStudyFund) {
        return null;
    } // only HR and general manager

    public Response updateEmpDaysOff(String userID, String EmpID, int newDaysOff) {
        return null;
    } // only HR and general manager

    public Response selfAssignToShift(String userID, LocalDateTime date, int start, int end,
                                      int pref/*0-want 1-can 2-cant*/){
        return null;
    } // TODO: how will we identify a shift?

//public ResponseT<shift> generateCustomeShift(String userID, LocalDateTime from, LocalDateTime to) // only HR and general manager

    public Response generate4WeeklyShifts(String ID) {
        return null;
    } // only HR and general manager

    public ResponseT<List<Shift>> getAllShifts(String userID){
        return null;
    }

    public Response assignEmpToShift(String userID, String EmpID, LocalDateTime date, int start, int end, String role){
        return null;
    } // only HR and general manager + check req 14

    public Response closeShift(String userID, LocalDateTime date, int start, int end){
        return null;
    }

//public Response openShift(String userID, LocalDateTime date, int start, int end)

    public ResponseT<List<String>> getWhoIWorkWith(String userID) {
        return null;
    } // return names only

    public ResponseT<String> getMyPreferences(String userID){ // Todo: nice toString
        return null;
    }

    public ResponseT<String> getEmployeesConstrainsForShift(String userID, LocalDateTime date, int start, int end) {
        return null;
    }// only shift assigner

    public ResponseT<String> getMyData(String userID) {
        return null;
    } // toString (req 12)
}