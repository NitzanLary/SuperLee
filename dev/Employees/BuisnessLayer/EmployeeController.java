package Employees.BuisnessLayer;

import java.util.HashMap;

public class EmployeeController {
    HashMap<String, Employee> employees;

    EmployeeController() {
        employees = new HashMap<>();
    }

    public void AddEmployee(String ID, String name, String bankAccount, int salary,
                            int sickDays, int studyFund, int daysOff, String roleName){
        TermsOfEmployee terms = new TermsOfEmployee(sickDays, studyFund, daysOff);
        Role role = new Role(roleName);
        Employee e = new Employee(name, ID);
        e.setTerms(terms);
        e.AddRole(role);
        employees.put(e.getID().getValue(), e);
    }

    public ResponseT<Employee> getEmployee(String id){
        if (!employees.containsKey(id)) return new ResponseT(null, "No employee with this ID");
        return new ResponseT<Employee>(employees.get(id), null);
    }
}

