package Employees.BuisnessLayer;

import java.time.LocalDate;
import java.time.LocalTime;
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
     *This function add new Employee to EmployeeController Hashmap of Employees only if userID is
     *HR manage or General Manger and return Response if successful, else return Response(error message)
     * @param userID
     * @param EmpID
     * @param name
     * @param bankAccount
     * @param salary
     * @param sickDays
     * @param studyFund
     * @param daysOff
     * @param roleName
     * @param _dateOfHire
     * @return
     */
    public Response addEmployee(String userID, String EmpID, String name, String bankAccount, int salary,
                                int sickDays, int studyFund, int daysOff, String roleName, LocalDate _dateOfHire){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.AddEmployee(EmpID, name, bankAccount, salary, sickDays, studyFund,
                daysOff, roleName, _dateOfHire);
    }


    public Response updateEmpName(String userID, String EmpID, String newEmpName){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.setEmpName(EmpID, newEmpName);
    }

    public Response updateEmpBankAccount(String userID, String EmpID, String newBankAccount) {
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.updateEmpBankAccount(EmpID, newBankAccount);
    }

    public Response updateEmpSalary(String userID, String EmpID, int newSalary) {
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.updateEmpSalary(EmpID, newSalary);
    }

    public Response updateEmpSickDays(String userID, String EmpID, int UpdatedsickDays){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.updateEmpSickDays(EmpID, UpdatedsickDays);
    }

    public Response updateEmpStudyFund(String userID, String EmpID, int newStudyFund) {
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.updateEmpStudyFund(EmpID, newStudyFund);
    }

    public Response updateEmpDaysOff(String userID, String EmpID, int newDaysOff) {
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.updateEmpDaysOff(EmpID, newDaysOff);
    }

    public Response addRoleToEmp(String userID, String EmpID, String role){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return employeeController.addRoleToEmp(EmpID, role);
    }

    public Response putConstrain(String userID, LocalDate date, LocalTime start, LocalTime end, int pref/*0-cant 1-can 2-want*/){
        ResponseT<Employee> rE = employeeController.getEmployee(userID);
        if(rE.isErrorOccured())
            return rE;
        return shiftController.putConstrain(rE.getValue(), date, start, end, pref);
    }


    public Response generate2WeeklyShifts(String userID) {
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return shiftController.add2WeeksSlots();
    } // only HR and general manager

    public Response generate1weeklyShifts(String userID) {
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return shiftController.add1WeeksSlot();
    }

    public ResponseT<List<Shift>> getFutureShifts(String userID){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return new ResponseT<>(null, rE.getErrorMessage());
        return shiftController.getFutureShifts();
    }

    public Response assignEmpToShift(String userID, String EmpID, LocalDate date, LocalTime start, LocalTime end, String role){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if(rE.isErrorOccured())
            return rE;
        rE = employeeController.getEmployee(EmpID);
        if(rE.isErrorOccured())
            return rE;
        return shiftController.assignToShift(rE.getValue(), date, start, end, role);
    }

    public Response closeShift(String userID, LocalDate date, LocalTime start, LocalTime end){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return shiftController.closeShift(date, start, end);
    } // only HR and general manager

    public Response openShift(String userID, LocalDate date, LocalTime start, LocalTime end){
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return rE;
        return shiftController.openShift(date, start, end);
    }

    public ResponseT<String> getWhoIWorkWith(String userID, LocalDate date, LocalTime start, LocalTime end) {
        ResponseT<Employee> rE = employeeController.getEmployee(userID);
        if(rE.isErrorOccured())
            return new ResponseT(null, rE.getErrorMessage());
        return shiftController.getWhoIWorkWith(rE.getValue(), date, start, end);
    } // return names and IDs only

    public ResponseT<String> getMyPreferences(String userID, LocalDate date, LocalTime start, LocalTime end){ // Todo: nice toString
        ResponseT<Employee> rE = employeeController.getEmployee(userID);
        if(rE.isErrorOccured())
            return new ResponseT(null, rE.getErrorMessage());
        return shiftController.getMyPreferences(rE.getValue(), date, start, end);
    }

    public ResponseT<Shift> getShift(LocalDate date, String type){
        return shiftController.findShift(date, type);
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
    public ResponseT<String> getEmployeesConstrainsForShift(String userID, LocalDate date, LocalTime start, LocalTime end) {
        ResponseT<Employee> rE = checkAuthorization(userID);
        if (rE.isErrorOccured())
            return new ResponseT(null, rE.getErrorMessage());
        return shiftController.getEmployeesConstrainsForShift(rE.getValue(), date, start, end);
    }// only shift assigner **Yanay think that General Manager should be authorized as well

    public ResponseT<String> getMyData(String userID) {
        ResponseT<Employee> rE = employeeController.getEmployee(userID);
        if(rE.isErrorOccured())
            return new ResponseT(null, rE.getErrorMessage());
        return employeeController.getEmpData(rE.getValue());

        //What kind of Error response should it return?
    } // toString (req 12)

    /**
     * This method confirm the authorization of an employee to get access for sort of other methods.
     * @param userID The ID of the user who tries to get access.
     * @return Response<Employee> which gives back the Employee object if no error occurred, otherwise returns the
     * correct error
     */
    private ResponseT<Employee> checkAuthorization(String userID){
        ResponseT<Employee> r = employeeController.getEmployee(userID);
        if (r.isErrorOccured())
            return r;
        Employee employee = r.getValue();
        ResponseT<Boolean> rA = employee.checkAuthorizedHrOrGenral();
        if (rA.isErrorOccured())
            return new ResponseT<>(null, rA.getErrorMessage());
        if (!rA.getValue())
            return new ResponseT<>(null, "Not Authorized! Only HR Manager Or General Manager Authorized For This Action");
        return r;
    }

    public ResponseT<Boolean> checkAuthorizationBool(String userID){
        ResponseT<Employee> r = employeeController.getEmployee(userID);
        if (r.isErrorOccured())
            return new ResponseT<>(null, r.getErrorMessage());
        Employee employee = r.getValue();
        return employee.checkAuthorizedHrOrGenral();
    }

    // init with 2 managers and 1 week forward
    public void initData(){
        employeeController.initData();
        shiftController.add1WeeksSlot();
        assignEmpToShift("312174295", "123456789", LocalDate.now().plusDays(1), LocalTime.of(14,0), LocalTime.of(22,0), "Ose LOLAVIM");
        assignEmpToShift("312174295", "987654321", LocalDate.now().plusDays(1), LocalTime.of(14,0), LocalTime.of(22,0), "Ose ETROGIM");

    }

//    public ResponseT<List<Shift>> getShiftsHistory(String userID){}

    //public ResponseT<shift> generateCustomShift(String userID, LocalDate from, LocalDate to) // only HR and general manager

}