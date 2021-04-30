package Employees.DataAccessLayer.DTOs;

public class EmployeeDTO {
    private String name;
    private String ID;
    private String bankAccount;
    private int salary;
    private int sickDays;
    private int advancedStudyFund;
    private int daysOff;

    public EmployeeDTO(String name, String id, String bankAccount, int salary, int sickDays, int advancedStudyFund,
                       int daysOff){
        this.name = name;
        this.ID = id;
        this.bankAccount = bankAccount;
        this.salary = salary;
        this.sickDays = sickDays;
        this.advancedStudyFund = advancedStudyFund;
        this.daysOff = daysOff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        // TODO
    }

    public String getID() {
        return ID;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public int getSalary() {
        return salary;
    }

    public int getSickDays() {
        return sickDays;
    }

    public int getAdvancedStudyFund() {
        return advancedStudyFund;
    }

    public int getDaysOff() {
        return daysOff;
    }
}
