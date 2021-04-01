package Employees.BuisnessLayer;
import java.time.LocalDate;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public abstract class Shift {

    private LocalDate date;
    protected int start;
    protected int end;
    private boolean closed;
    private HashMap<Employee, Integer> constrains;
    private HashMap<String, Employee> assignedEmployees;

    Shift(LocalDate _date) {
        date = _date;
        constrains = new HashMap<Employee, Integer>();
        assignedEmployees = new HashMap<String, Employee>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void AddConstrain(Employee employee, int con){
        constrains.put(employee, con);
    }

    public void AssignEmployee(Employee e){
        assignedEmployees.put(e.getID(), e);
    }





}