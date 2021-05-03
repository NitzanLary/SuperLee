package Employees.DataAccessLayer.DTOs;

import java.time.LocalDate;
import java.util.List;

public class EmployeeDTO {
    private String name;
    private String ID;
    private String bankAccount;
    private int salary;
    private int sickDays;
    private int advancedStudyFund;
    private int daysOff;
    LocalDate dateOfHire;
    List<RoleDTO> roles;

    public EmployeeDTO(String name, String id, String bankAccount, int salary, int sickDays, int advancedStudyFund,
                       int daysOff, String date, List<RoleDTO> roles){
        this.name = name;
        this.ID = id;
        this.bankAccount = bankAccount;
        this.salary = salary;
        this.sickDays = sickDays;
        this.advancedStudyFund = advancedStudyFund;
        this.daysOff = daysOff;
        dateOfHire = LocalDate.parse(date);
        this.roles = roles;
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

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", salary=" + salary +
                ", sickDays=" + sickDays +
                ", advancedStudyFund=" + advancedStudyFund +
                ", daysOff=" + daysOff +
                ", dateOfHire=" + dateOfHire +
                ", \nroles=" + roles +
                '}';
    }
}
