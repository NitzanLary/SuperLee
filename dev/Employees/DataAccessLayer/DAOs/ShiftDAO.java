package Employees.DataAccessLayer.DAOs;

import Employees.BuisnessLayer.Response;
import Employees.DataAccessLayer.DTOs.ShiftDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftDAO extends DAO{
    private static class ShiftDAO_Holder {
        private static ShiftDAO instance = new ShiftDAO();
    }

    private ShiftDAO(){}

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

        }catch(SQLException e){
            return new Response(e.getMessage());
        }
        return new Response();
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

}
