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
        closed = false; //**added default to be false by Yanay
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

    public Response setClosed(boolean closed) {
        this.closed = closed;
        return new Response();
    }

    public Response AddConstrain(Employee employee, int con){
        constrains.put(employee, con);
        return new Response();
    }

    public boolean compare(LocalDate date, int StartTime, int EndTime){
        return (date.equals(this.date) && StartTime== this.start && EndTime == this.end);
    }

    public ResponseT<String> getShiftConstrainsString() {
        StringBuilder res = new StringBuilder();
        for (Employee emp : constrains.keySet()){
            res.append("ID: ").append(emp.getID().getValue()).append(" | Name: ").append(emp.getName().getValue())
                    .append(" | Preference: ").append(this.constrains.get(emp)).append("\n");
        }
        return new ResponseT<String>(res.toString());
        //TODO: Need to change the Preference toString to "Can/Cant/Want" instead of number(0,1,2).
    }

    public void AssignEmployee(Employee e){
        assignedEmployees.put(e.getID().getValue(), e);
    }

    public Response getAllassignedEmployees(Employee e){
        return null;
    }





}