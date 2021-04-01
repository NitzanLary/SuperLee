package Employees.BuisnessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String ID;
    private String bankAccount;
    private int salary;
    private List<Role> roles;
    private TermsOfEmployee terms;
    private LocalDate dateOfHire;

    Employee(String _name, String _ID, LocalDate _dateOfHire) {
        name = _name;
        ID = _ID;
        dateOfHire = _dateOfHire;
        roles = new ArrayList<>();
    }

    public ResponseT<String> getName() {
        return new ResponseT<String>(name);
    } // TODO: change return type to Response<String>

    public ResponseT<String> getID() {
        return new ResponseT<String>(ID);
    }

    public ResponseT<String> getBankAccount() {
        return new ResponseT<String>(bankAccount);
    }

    public ResponseT<Integer> getSalary() {
        return new ResponseT<Integer>(salary);
    }


    public ResponseT<List<Role>> getRoles() {
        return new ResponseT<>(roles);
    }

    public ResponseT<TermsOfEmployee> getTerms() {
        return new ResponseT<>(terms);
    }

    public Response setSalary(int salary) { // TODO: return type response();
        this.salary = salary;
        return new Response();
    }

    public Response setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return new Response();
    }

    public Response AddRole(Role role){
        roles.add(role);
        return new Response();
    }

    public Response setTerms(TermsOfEmployee terms) {
        this.terms = terms;
        return new Response();
    }
}
