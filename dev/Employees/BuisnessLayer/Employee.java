package Employees.BuisnessLayer;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String ID;
    private String bankAccount;
    private int salary;
    private List<Role> roles;
    private TermsOfEmployee terms;

    Employee(String _name, String _ID) {
        name = _name;
        ID = _ID;
        roles = new ArrayList<Role>();
    }

    public String getName() {return name;}

    public int getSalary() {
        return salary;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getID() {
        return ID;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public TermsOfEmployee getTerms() {
        return terms;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void AddRole(Role role){
        roles.add(role);
    }

    public void setTerms(TermsOfEmployee terms) {
        this.terms = terms;
    }
}
