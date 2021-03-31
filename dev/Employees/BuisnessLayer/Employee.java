package Employees.BuisnessLayer;

public class Employee {
    private String name;
    private String ID;
    private String bankAccount;
    private int salary;

    Employee(String _name, String _ID) {name = _name; ID = _ID;}

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

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }


}
