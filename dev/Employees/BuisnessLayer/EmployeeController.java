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
        if (employeeController != null)
            return employeeController;
        return new EmployeeController();
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
        e.AddRole(role);
        employees.put(e.getID().getValue(), e);
        return new Response();
    }

    public ResponseT<Employee> getEmployee(String id){
        if (!employees.containsKey(id)) return new ResponseT(null, "No employee with this ID");
        return new ResponseT<Employee>(employees.get(id), null);
    }

    public Response setEmpName(Employee employee, String newEmpName) {
        employee.setName(newEmpName);
        return new Response();
    }

    public Response updateEmpBankAccount(Employee employee, String newBankAccount) {
        employee.setBankAccount(newBankAccount);
        return new Response();
    }

    public Response updateEmpSalary(Employee employee, int newSalary) {
        employee.setSalary(newSalary);
        return new Response();
    }

    public Response updateEmpSickDays(Employee employee, int updatedSickDays) {
        employee.getTerms().setSickDays(updatedSickDays);
        return new Response();
    }

    public Response updateEmpStudyFund(Employee employee, int newStudyFund) {
        employee.getTerms().setAdvancedStudyFund(newStudyFund);
        return new Response();
    }

    public Response updateEmpDaysOff(Employee employee, int newDaysOff) {
        employee.getTerms().setDaysOff(newDaysOff);
        return new Response();
    }

    public ResponseT<String> getEmpData(Employee employee) {
        return employee.getEmpDataTostring();
    }
}

