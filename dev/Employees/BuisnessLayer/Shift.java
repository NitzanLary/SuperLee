package Employees.BuisnessLayer;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public abstract class Shift {

    private LocalDate date;
    protected LocalTime start;
    protected LocalTime end;
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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public boolean isClosed() {
        return closed;
    }

    public Response close() {
        if (closed)
            return new Response("Already closed");
        closed = true;
        return new Response();
    }

    public Response open() {
        if (!closed)
            return new Response("Already open");
        closed = false;
        return new Response();
    }


    public Response AddConstrain(Employee employee, int con){
        if (isClosed())
            return new Response("Shift already closed");
        constrains.put(employee, con);
        return new Response();
    }

//    public boolean compare(LocalDate date, int StartTime, int EndTime){
//        return (date.equals(this.date) && StartTime== this.start && EndTime == this.end);
//    }

    public boolean compare(LocalDate date, LocalTime StartTime, LocalTime EndTime){
        return (date.equals(this.date) && StartTime.compareTo(start) == 0  && EndTime.compareTo(end) == 0);
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

    public Response AssignEmployee(Employee e){
        if (isClosed())
            return new Response("Shift already closed");
        assignedEmployees.put(e.getID().getValue(), e);
        return new Response();
    }

    public Response getAllassignedEmployees(Employee e){
        return null;
    }


}