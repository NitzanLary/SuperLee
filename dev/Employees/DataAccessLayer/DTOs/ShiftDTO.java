package Employees.DataAccessLayer.DTOs;

import Employees.BuisnessLayer.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class ShiftDTO {

    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private boolean closed;
    private Map<Employee, Integer> constrains;
    private Map<String, Employee> assignedEmployees;

    public ShiftDTO(LocalDate date, LocalTime start, LocalTime end, boolean closed, Map<Employee, Integer> constrains,
                    Map<String, Employee> assignedEmployees){
        this.date = date;
        this.start = start;
        this.end = end;
        this.closed = closed;
        this.constrains = constrains;
        this.assignedEmployees = assignedEmployees;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean isClosed() {
        return closed;
    }

    public Map<Employee, Integer> getConstrains() {
        return constrains;
    }

    public Map<String, Employee> getAssignedEmployees() {
        return assignedEmployees;
    }
}
