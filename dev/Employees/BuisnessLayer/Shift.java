package Employees.BuisnessLayer;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.*;

public abstract class Shift {

    protected LocalDate date;
    protected LocalTime start;
    protected LocalTime end;
    private boolean closed;


    private HashMap<Employee, Integer> constrains;
    private HashMap<String, Employee> assignedEmployees;

    Shift(LocalDate _date) {
        closed = false; // **added default to be false by Yanay.
        date = _date;
        constrains = new HashMap<Employee, Integer>();
        assignedEmployees = new HashMap<String, Employee>();
    }

    public abstract Shift clone();

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
        if (con > 2 || con < 0)
            return new Response("Invalid preference");
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


    public ResponseT<String> getWhoIWorkWith(Employee employee){
        if (!assignedEmployees.containsKey(employee.getID()))
            return new ResponseT<>(null, "This employee is not assigned to this shift");
        return new ResponseT<String>(getIdsNames());
    }

    protected String getIdsNames(){
        StringBuilder sb = new StringBuilder();
        sb.append("|\t").append(date.toString()).append('\n');
        sb.append("|\t").append(start.toString()).append('\n');
        sb.append("|\t").append(end.toString()).append('\n');
        for(Map.Entry<String,Employee> entry: assignedEmployees.entrySet()) {
            sb.append(entry.getKey()).append('\t').append(entry.getValue()).append('\n');
        }
        sb.deleteCharAt(sb.length()-1); // remove the last \n
        return sb.toString();
    }

    public ResponseT<String> getEmpPreferences(Employee employee){
        if (!constrains.containsKey(employee))
            return new ResponseT("Employee did not assign into this shift");
        int pref = constrains.get(employee);
        switch (pref){
            case 0:
                return new ResponseT("The preference to this shift is: Can't work on this shift");
            case 1:
                return new ResponseT("The preference to this shift is: Can work on this shift");
            case 2:
                return new ResponseT("The preference to this shift is: Want work on this shift");
            default:
                return new ResponseT(null, "for some reason, we reached the default"); // never happens
        }
    }

    public boolean isAssigned(Employee employee){
        return assignedEmployees.containsKey(employee);
    }
}