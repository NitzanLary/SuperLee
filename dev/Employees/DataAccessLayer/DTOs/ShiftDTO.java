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

    private ShiftDate shiftDate;
    private boolean closed;
    private Map<EmployeeDTO, Integer> constrains;
    private Map<String, EmployeeDTO> assignedEmployees;
    private Map<String, List<EmployeeDTO>> rolesMap;
    private ShiftDAO dao;

    public ShiftDTO(LocalDate date, LocalTime start, LocalTime end, boolean closed, Map<EmployeeDTO, Integer> constrains,
                    Map<String, EmployeeDTO> assignedEmployees, Map<String, List<EmployeeDTO>> rolesMap, ShiftDAO dao){
        shiftDate = new ShiftDate(date, start, end);
        this.closed = closed;
        this.constrains = constrains;
        this.assignedEmployees = assignedEmployees;
        this.rolesMap = rolesMap;
        this.dao = dao;
    }

    public LocalDate getDate() {
        return shiftDate.getDate();
    }

    public LocalTime getStart() {
        return shiftDate.getStart();
    }

    public LocalTime getEnd() {
        return shiftDate.getEnd();
    }

    public boolean isClosed() {
        return closed;
    }

    public Response setClosed(boolean closed) {
        this.closed = closed;
        return dao.setClose(shiftDate, closed);
    }

    public Map<EmployeeDTO, Integer> getConstrains() {
        return constrains;
    }

    public Response addConstrain(String id, int pref){
        return dao.addConstrain(shiftDate, id, pref);
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
        return shiftDate.getDate().equals(shiftDTO.getDate()) && shiftDate.getStart().equals(shiftDTO.shiftDate.getStart())
                && shiftDate.getEnd().equals(shiftDTO.shiftDate.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiftDate.getDate(), shiftDate.getStart(), shiftDate.getEnd());
    }

    @Override
    public String toString() {
        return "ShiftDTO{" +
                "date=" + shiftDate.getDate() +
                ", start=" + shiftDate.getStart() +
                ", end=" + shiftDate.getEnd() +
                ", closed=" + closed +
                ", \nconstrains=" + constrains +
                ", \nassignedEmployees=" + assignedEmployees +
                ", \nrolesMap=" + rolesMap +
                '}';
    }
}
