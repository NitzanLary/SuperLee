package Employees.DataAccessLayer.DAOs;

import Employees.BuisnessLayer.Employee;
import Employees.BuisnessLayer.Response;
import Employees.BuisnessLayer.ResponseT;
import Employees.DataAccessLayer.DTOs.EmployeeDTO;
import Employees.DataAccessLayer.DTOs.RoleDTO;
import Employees.DataAccessLayer.DTOs.ShiftDTO;
import Employees.DataAccessLayer.Objects.Mapper;
import Employees.DataAccessLayer.Objects.ShiftDate;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShiftDAO extends DAO{
    private Mapper mapper;
    private static class ShiftDAO_Holder {
        private static ShiftDAO instance = new ShiftDAO();
    }

    private ShiftDAO() {}

    public static ShiftDAO getInstance(){
        return ShiftDAO_Holder.instance;
    }

    public Response insertShift(LocalDate date, LocalTime start, LocalTime end, boolean closed){
        String sql = """
                INSERT INTO Shift (Date, Start, End, Closed)
                VALUES
                (?, ?, ?, ?)
                """;

        try(Connection conn = getConn().getValue();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, date.toString());
            pstmt.setString(2, start.toString());
            pstmt.setString(3, end.toString());
            pstmt.setString(4, String.valueOf(closed));

        }catch(SQLException e){
            return new Response(e.getMessage());
        }
        return new Response();
    }

    public Response insertShift(ShiftDTO shift){
        return insertShift(shift.getDate(), shift.getStart(), shift.getEnd(), shift.isClosed());
    }

    public Response addConstrain(LocalDate date, LocalTime start, LocalTime end, String empID, int preference){
        String sql = """
                INSERT INTO ShiftConstrains (Date, Start, End, EmpID, Preference)
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try(Connection conn = getConn().getValue();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, date.toString());
            pstmt.setString(2, start.toString());
            pstmt.setString(3, end.toString());
            pstmt.setString(4, empID);
            pstmt.setInt(5, preference);

            return new Response();

        }catch(SQLException e){
            return new Response(e.getMessage());
        }
    }

    public Response addEmployee(LocalDate date, LocalTime start, LocalTime end, String empID, String role){
        String sql = """
                INSERT INTO ShiftAssignees (Date, Start, End, EmpID, Role)
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try(Connection conn = getConn().getValue();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, date.toString());
            pstmt.setString(2, start.toString());
            pstmt.setString(3, end.toString());
            pstmt.setString(4, empID);
            pstmt.setString(5, role);

        }catch(SQLException e){
            return new Response(e.getMessage());
        }
        return new Response();
    }

    public ResponseT<ShiftDTO> get(ShiftDate shiftDate){
        String shiftSql = """
                SELECT* FROM Shift
                WHERE Date = ? AND Start = ? AND End = ?
                """;

        String shiftConstrainsSql = """
                SELECT* FROM ShiftConstrains
                WHERE Date = ? AND Start = ? AND End = ?
                """;

        String shiftAssigneesSql = """
                SELECT* FROM ShiftAssignees
                WHERE Date = ? AND Start = ? AND End = ?
                """;

        try(Connection conn = getConn().getValue();
            PreparedStatement shiftSqlStmt = conn.prepareStatement(shiftSql);
            PreparedStatement shiftConstrainsSqlStmt = conn.prepareStatement(shiftConstrainsSql);
            PreparedStatement shiftAssigneesSqlStmt = conn.prepareStatement(shiftAssigneesSql)){

            prepareShiftStatement(shiftSqlStmt, shiftDate);
            ResultSet shiftRs = shiftSqlStmt.executeQuery();
            shiftRs.next();
            if (shiftRs.isClosed())
                return new ResponseT<>(null, "Shift not found");
            boolean closed = shiftRs.getBoolean(5);

            shiftRs.close();
            // constrains part
            prepareShiftStatement(shiftConstrainsSqlStmt, shiftDate);
            ResultSet consRs = shiftConstrainsSqlStmt.executeQuery();
            ResponseT<Map<EmployeeDTO, Integer>> constrains = getMapConstrains(consRs);
            // assignees part
            prepareShiftStatement(shiftAssigneesSqlStmt, shiftDate);
            ResultSet assRs = shiftAssigneesSqlStmt.executeQuery();
            Map<String, EmployeeDTO> assignedEmployees = new HashMap<>();
            Map<String, List<EmployeeDTO>> rolesMap = new HashMap<>();
            Response r = fillAssigneesAndRoles(assRs, assignedEmployees, rolesMap);
            if (r.isErrorOccured())
                return new ResponseT<>(null, r.getErrorMessage());

            ShiftDTO shift = new ShiftDTO(shiftDate.getDate(), shiftDate.getStart(), shiftDate.getEnd(), closed,
                    constrains.getValue(), assignedEmployees, rolesMap);

            return new ResponseT<>(shift);

        }catch(SQLException e){
            e.printStackTrace();
            return new ResponseT<>(null, e.getMessage());
        }
    }

    private Response fillAssigneesAndRoles(ResultSet assRs, Map<String, EmployeeDTO> assignedEmployees,
                                           Map<String, List<EmployeeDTO>> rolesMap) throws SQLException {
        while (assRs.next()){
            String empId = assRs.getString(4);
            String roleName = assRs.getString(5);
            ResponseT<EmployeeDTO> employee = Mapper.getInstance().getEmployee(empId);
            if (employee.isErrorOccured())
                return employee;
            assignedEmployees.put(empId, employee.getValue());
            List<EmployeeDTO> emps = rolesMap.computeIfAbsent(roleName, k -> new ArrayList<>());
            emps.add(employee.getValue());
        }
        // TODO very important: I WAS VERY TIRED! POSSIBLY A LOT OF MISTAKES
        return new Response();
    }

    private ResponseT<Map<EmployeeDTO, Integer>> getMapConstrains(ResultSet consRs) throws SQLException {
        Map<EmployeeDTO, Integer> constrains = new HashMap<>();
        while(consRs.next()){
            String empId = consRs.getString(4);
            ResponseT<EmployeeDTO> employee = Mapper.getInstance().getEmployee(empId);
            if(employee.isErrorOccured())
                return new ResponseT<>(null, employee.getErrorMessage());
            constrains.put(employee.getValue(), consRs.getInt(5));
        }
        return new ResponseT<>(constrains);
    }

    private void prepareShiftStatement(PreparedStatement shiftSqlStmt, ShiftDate shiftDate) throws SQLException {
        shiftSqlStmt.setString(1, shiftDate.getDate().toString());
        shiftSqlStmt.setString(2, shiftDate.getStart().toString());
        shiftSqlStmt.setString(3, shiftDate.getEnd().toString());
    }
}