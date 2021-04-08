package Employees.BuisnessLayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class EmployeeController {
    private static EmployeeController employeeController;
    HashMap<String, Employee> employees;

    private EmployeeController()
    {
        employees = new HashMap<>();
    }

    public static EmployeeController getInstance(){
        if (employeeController == null)
            employeeController = new EmployeeController();
        return employeeController;
    }

    public Response AddEmployee(String ID, String name, String bankAccount, int salary,
                            int sickDays, int studyFund, int daysOff, String roleName, LocalDate _dateOfHire){
        if (sickDays < 0 || studyFund < 0 || daysOff < 0)
            return new Response("All values of terms must be greater than 0");
        TermsOfEmployee terms = new TermsOfEmployee(sickDays, studyFund, daysOff);
        Role role = new Role(roleName); // Todo: validate roleName
        Employee e;
        try{
             e = new Employee(name, ID, _dateOfHire);
        }catch (IllegalArgumentException exception){
            return new Response(exception.getMessage());
        }
        e.setTerms(terms);
        e.setBankAccount(bankAccount);
        e.setSalary(salary);
        e.AddRole(role);
        employees.put(e.getID().getValue(), e);
        return new Response();
    }

    public ResponseT<Employee> getEmployee(String id){
        if (!employees.containsKey(id)) return new ResponseT(null, "No employee with this ID");
        return new ResponseT<>(employees.get(id));
    }

    public Response setEmpName(String ID, String newEmpName) {
        ResponseT<Employee> rE = getEmployee(ID);
        if(rE.isErrorOccured())
            return rE;
        return rE.getValue().setName(newEmpName);
    }

    public Response updateEmpBankAccount(String ID, String newBankAccount) {
        ResponseT<Employee> rE = getEmployee(ID);
        if(rE.isErrorOccured())
            return rE;
        return rE.getValue().setBankAccount(newBankAccount);
    }

    public Response updateEmpSalary(String ID, int newSalary) {
        ResponseT<Employee> rE = getEmployee(ID);
        if(rE.isErrorOccured())
            return rE;
        return rE.getValue().setSalary(newSalary);
    }

    public Response updateEmpSickDays(String ID, int updatedSickDays) {
        ResponseT<Employee> rE = getEmployee(ID);
        if(rE.isErrorOccured())
            return rE;
        return rE.getValue().getTerms().setSickDays(updatedSickDays);
    }

    public Response updateEmpStudyFund(String ID, int newStudyFund) {
        ResponseT<Employee> rE = getEmployee(ID);
        if(rE.isErrorOccured())
            return rE;
        return rE.getValue().getTerms().setAdvancedStudyFund(newStudyFund);
    }

    public Response updateEmpDaysOff(String ID, int newDaysOff) {
        ResponseT<Employee> rE = getEmployee(ID);
        if(rE.isErrorOccured())
            return rE;
        return rE.getValue().getTerms().setDaysOff(newDaysOff);
    }

    public ResponseT<String> getEmpData(Employee employee) {
        return employee.getEmpDataTostring();
    }

    public void initData() {
        AddEmployee("312174295", "Yanay", "12345", 1000, 30, 500, 30,
                "General Manager", LocalDate.now());
        AddEmployee("205952971", "Nitzan", "12345", 1000, 30, 500, 30,
                "HR Manager", LocalDate.now());
    }
}

