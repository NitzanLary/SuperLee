package Employees.DataAccessLayer.DAOs;

import Employees.BuisnessLayer.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO extends DAO{

    public Response update(String col, String ID, String newVal){
        String sql = """
                UPDATE Employees SET ? = ?
                WHERE ID = ?
                """;
        
        try(Connection conn = getConn().getValue();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, col);
            pstmt.setString(2, newVal);
            pstmt.setString(3, ID);

            pstmt.executeUpdate();

        }catch(SQLException e){
            return new Response(e.getMessage());
        }

        return new Response();
    }

    public Response createTable(){
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";

        try(Connection conn = getConn().getValue();
            Statement stmt = conn.createStatement()){

            stmt.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
            return new Response(e.getMessage());
        }

        return new Response();
    }
}
