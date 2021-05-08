package Employees.BuisnessLayer;
import Employees.DataAccessLayer.DAOs.ShiftDAO;
import Employees.DataAccessLayer.DTOs.EmployeeDTO;
import Employees.DataAccessLayer.DTOs.ShiftDTO;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Shift {

    protected LocalDate date;
    protected LocalTime start;
    protected LocalTime end;
    private boolean closed;
    private HashMap<Employee, Integer> constrains;
    private HashMap<String, Employee> assignedEmployees;
    private HashMap<String, List<Employee>> assignedRolesEmp; //Yanay's Plaster for getting the specific roles that emps assigned to..
    protected ShiftDTO dto;

    Shift(LocalDate _date, ShiftDAO _dao) {
        closed = false; // **added default to be false by Yanay.
        date = _date;
        constrains = new HashMap<Employee, Integer>();
        assignedEmployees = new HashMap<String, Employee>();
        assignedRolesEmp = new HashMap<>();
        dto = new ShiftDTO(date, start, end, closed, getConstrainsDTO(), getAssigneesDTO(), getRolesMap(), _dao);
    }

    private Map<EmployeeDTO, Integer> getConstrainsDTO() {
        Map<EmployeeDTO, Integer> constraintsDTO = new HashMap<>();
        for (Map.Entry<Employee, Integer> entry : constrains.entrySet())
            constraintsDTO.put(entry.getKey().getDTO(), entry.getValue());
        return constraintsDTO;
    }

    private Map<String, EmployeeDTO> getAssigneesDTO() {
        Map<String, EmployeeDTO> assigneesDTO = new HashMap<>();
        for (Map.Entry<String, Employee> entry : assignedEmployees.entrySet())
            assigneesDTO.put(entry.getKey(), entry.getValue().getDTO());
        return assigneesDTO;
    }

    private Map<String, List<EmployeeDTO>> getRolesMap() {
        Map<String, List<EmployeeDTO>> rolesMap = new HashMap<>();
        for (Map.Entry<String, List<Employee>> entry : assignedRolesEmp.entrySet()){
            List<EmployeeDTO> dtos = entry.getValue().stream().map(Employee::getDTO).collect(Collectors.toList());
            rolesMap.put(entry.getKey(), dtos);
        }
        return rolesMap;
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
        if(!hasManager())
            return new Response("Can not close before a shift manager is assigned to the shift");
        closed = true;
        return new Response();
    }

    protected boolean hasManager(){
        for(Map.Entry<String, Employee> entry: assignedEmployees.entrySet()){
            if(entry.getValue().haveRoleCheck("HR Manager") || entry.getValue().haveRoleCheck("Shift Manager"))
                return true;
        }
        return false;
    }

    public Response open() {
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

    }

    public Response AssignEmployee(Employee e, String role){
        if (isClosed())
            return new Response("Shift already closed");
        assignedEmployees.put(e.getID().getValue(), e);
        List<Employee> employees = assignedRolesEmp.computeIfAbsent(role, k -> new ArrayList<>());
        employees.add(e);
        return new Response();
    }

    public ResponseT<List<Employee>> getAllAssignedEmployees(){
        List<Employee> employees = new ArrayList<>();
        for(Map.Entry<String, Employee> entry: assignedEmployees.entrySet())
            employees.add(new Employee(entry.getValue()));
        return new ResponseT<>(employees);
    }


    public ResponseT<String> getWhoIWorkWith(Employee employee){
        if (!assignedEmployees.containsKey(employee.getID().getValue()))
            return new ResponseT<>(null, "This employee is not assigned to this shift");
        return new ResponseT<String>(getIdsNames());
    }

    protected String getIdsNames(){
        StringBuilder sb = new StringBuilder();
        sb.append("|\t").append(date.toString()).append('\n');
        sb.append("|\t").append(start.toString()).append('\n');
        sb.append("|\t").append(end.toString()).append("\n\n");
        for(Map.Entry<String,Employee> entry: assignedEmployees.entrySet()) {
            sb.append(entry.getKey()).append('\t').append(entry.getValue().getName().getValue()).append('\n');
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
        return assignedEmployees.containsKey(employee.getID().getValue());
    }

    public Response removeEmp(Employee employee){
        if (!isAssigned(employee))
            return new Response("Employee is not assigned to this shift");
        assignedEmployees.remove(employee.getID().getValue());
        return new Response();
    }


    public ResponseT<List<Employee>> getAllAssignedDrivers(){
        for(Map.Entry<String, List<Employee>> entry: assignedRolesEmp.entrySet()){
            if (entry.getKey().equals("Driver"))
                return new ResponseT<>(entry.getValue());
        }
        return new ResponseT<>(null, "for some reason there is no drivers in this shift");
    }

    @Override
    public String toString() {
        return "Shift{" +
                "date=" + date +
                ", start=" + start +
                ", end=" + end +
                ", closed=" + closed +
                ", \nconstrains=" + constrains +
                ", \nassignedEmployees=" + assignedEmployees +
                ", \nassignedRolesEmp=" + assignedRolesEmp +
                '}';
    }
}