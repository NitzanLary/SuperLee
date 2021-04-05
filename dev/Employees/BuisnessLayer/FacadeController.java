package Employees.BuisnessLayer;

import java.time.LocalDate;
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

    /**
     *
     This function add new Employee to EmployeeController Hashmap of Employees only if userID is
     HR manage or General Manger and return Response if successful, else return Response(error message)
     */

    public Response addEmployee(String userID, String EmpID, String name, String bankAccount, int salary,
                                int sickDays, int studyFund, int daysOff, String roleName, LocalDate _dateOfHire){
        if (!employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue())
            return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
        Response  response = employeeController.AddEmployee(EmpID, name, bankAccount, salary, sickDays, studyFund,
                daysOff, roleName, _dateOfHire);

        return response;

    } // only HR and general manager


    public Response updateEmpName(String userID, String EmpID, String newEmpName){
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return employeeController.getEmployee(EmpID).getValue().setName(newEmpName);
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

    public Response updateEmpBankAccount(String userID, String EmpID, String newBankAccount) {
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return employeeController.getEmployee(EmpID).getValue().setBankAccount(newBankAccount);
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

    public Response updateEmpSalary(String userID, String EmpID, int newSalary) {
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return employeeController.getEmployee(EmpID).getValue().setSalary(newSalary);
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

    public Response updateEmpSickDays(String userID, String EmpID, int UpdatedsickDays){
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return employeeController.getEmployee(EmpID).getValue().getTerms().getValue().setSickDays(UpdatedsickDays);
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

    public Response updateEmpStudyFund(String userID, String EmpID, int newStudyFund) {
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return employeeController.getEmployee(EmpID).getValue().getTerms().getValue().setAdvancedStudyFund(newStudyFund);
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

    public Response updateEmpDaysOff(String userID, String EmpID, int newDaysOff) {
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return employeeController.getEmployee(EmpID).getValue().getTerms().getValue().setDaysOff(newDaysOff);
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

    public Response putConstrain(String userID, LocalDate date, int start, int end,
                                      int pref/*0-want 1-can 2-cant*/){
        Shift shift = shiftController.findShift(date, start, end);
        if (shift != null){
            return shift.AddConstrain(employeeController.getEmployee(userID).getValue(), pref);
        }
        return new Response("ERROR! No Matches Found For This Shift");
    }

//public ResponseT<shift> generateCustomeShift(String userID, LocalDateTime from, LocalDateTime to) // only HR and general manager

    public Response generate4WeeklyShifts(String userID) {
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return shiftController.add4WeeksSlots();
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

    public ResponseT<List<Shift>> getFutureShifts(String userID){ //
        if (!employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue())
            return new ResponseT<>(null, "Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
        return shiftController.getFutureShifts();
    }

    public ResponseT<List<Shift>> getShiftsHistory(String userID){
        return null;
    }

    public Response assignEmpToShift(String userID, String EmpID, LocalDateTime date, int start, int end, String role){
        return null; //TODO: NEED TO IMPLEMENT
    } // only HR and general manager + check req 14

    public Response closeShift(String userID, LocalDate date, int start, int end){
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return shiftController.findShift(date, start, end).setClosed(true);
        }
        return new Response("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    } // only HR and general manager

//public Response openShift(String userID, LocalDateTime date, int start, int end)

    public ResponseT<List<String>> getWhoIWorkWith(String userID) {
        return null;
    } // return names only

    public ResponseT<String> getMyPreferences(String userID){ // Todo: nice toString
        return null;
    }

    /**
     *
     This function return all the constrains for a specific shift given as parameter as a Response<String> while
     the Response's Value look like this:

     ID: 1233214544
     Name: Nitzan Lary
     Preference: 3
     TODO: need to change instead of numbers to strings(Can/Cant/Want)

     the function will return Failure Response if the user that call the function is not HR Manager Or General Manager
     */

    public ResponseT<String> getEmployeesConstrainsForShift(String userID, LocalDate date, int start, int end) {
        if (employeeController.getEmployee(userID).getValue().checkAuthorizedHrOrGenral().getValue()){
            return (shiftController.findShift(date, start, end).getShiftConstrainsString());
        }
        return new ResponseT("Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
    }// only shift assigner **Yanay think that General Manager should be authorized as well

    public ResponseT<String> getMyData(String userID) {
        return (EmployeeController.getInstance().getEmployee(userID).getValue().getEmpDataTostring());
        //What kind of Error response should it return?
    } // toString (req 12)
}