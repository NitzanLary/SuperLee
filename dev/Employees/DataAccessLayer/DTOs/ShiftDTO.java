package Employees.DataAccessLayer.DTOs;

import Employees.BuisnessLayer.Employee;
import Employees.BuisnessLayer.Response;
import Employees.DataAccessLayer.DAOs.ShiftDAO;
import Employees.DataAccessLayer.Objects.ShiftDate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShiftDTO {

    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private boolean closed;
    private Map<EmployeeDTO, Integer> constrains;
    private Map<String, EmployeeDTO> assignedEmployees;
    private Map<String, List<EmployeeDTO>> rolesMap;
    private ShiftDAO dao;

    public ShiftDTO(LocalDate date, LocalTime start, LocalTime end, boolean closed, Map<EmployeeDTO, Integer> constrains,
                    Map<String, EmployeeDTO> assignedEmployees, Map<String, List<EmployeeDTO>> rolesMap, ShiftDAO dao){
        this.date = date;
        this.start = start;
        this.end = end;
        this.closed = closed;
        this.constrains = constrains;
        this.assignedEmployees = assignedEmployees;
        this.rolesMap = rolesMap;
        this.dao = dao;
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

    public Response setClosed(boolean closed) {
        this.closed = closed;
        return dao.setClose(new ShiftDate(date, start, end), closed);
    }

    public Map<EmployeeDTO, Integer> getConstrains() {
        return constrains;
    }

    public Map<String, EmployeeDTO> getAssignedEmployees() {
        return assignedEmployees;
    }

    public Map<String, List<EmployeeDTO>> getRolesMap() {
        return rolesMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShiftDTO shiftDTO = (ShiftDTO) o;
        return date.equals(shiftDTO.date) && start.equals(shiftDTO.start) && end.equals(shiftDTO.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, start, end);
    }

    @Override
    public String toString() {
        return "ShiftDTO{" +
                "date=" + date +
                ", start=" + start +
                ", end=" + end +
                ", closed=" + closed +
                ", \nconstrains=" + constrains +
                ", \nassignedEmployees=" + assignedEmployees +
                ", \nrolesMap=" + rolesMap +
                '}';
    }
}
