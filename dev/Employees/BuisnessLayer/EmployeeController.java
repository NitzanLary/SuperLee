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

    public void AddEmployee(String ID, String name, String bankAccount, int salary,
                            int sickDays, int studyFund, int daysOff, String roleName, LocalDate _dateOfHire){
        TermsOfEmployee terms = new TermsOfEmployee(sickDays, studyFund, daysOff);
        Role role = new Role(roleName);
        Employee e = new Employee(name, ID, _dateOfHire);
        e.setTerms(terms);
        e.AddRole(role);
        employees.put(e.getID().getValue(), e);
    }

    public ResponseT<Employee> getEmployee(String id){
        if (!employees.containsKey(id)) return new ResponseT(null, "No employee with this ID");
        return new ResponseT<Employee>(employees.get(id), null);
    }
}

