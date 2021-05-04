package Employees.DataAccessLayer.DAOs;

import Employees.BuisnessLayer.Response;
import Employees.BuisnessLayer.ResponseT;
import Employees.DataAccessLayer.DTOs.EmployeeDTO;
import Employees.DataAccessLayer.DTOs.RoleDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends DAO{

    public Response insert(String ID, String name, int BankAccount, int salary, int sickDays, int studyFund,
                           int daysOff, LocalDate date, String role, String driverLicence){

        String sql = """
                INSERT INTO Employees (EmpID, Name, BankAccount, Salary, SickDays, StudyFund ,DaysOff, DateOfHire)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        String roleSql = """
                INSERT INTO RolesEmployees (EmpID, RoleName, DriverLicence)
                VALUES
                (?, ?, ?)
                """;


        try(Connection conn = getConn().getValue();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(roleSql)){

            // inserting to employee table
            pstmt.setString(1, ID);
            pstmt.setString(2, name);
            pstmt.setInt(3,BankAccount);
            pstmt.setInt(4,salary);
            pstmt.setInt(5,sickDays);
            pstmt.setInt(6,studyFund);
            pstmt.setInt(7,daysOff);
            pstmt.setString(8,date.toString());

            //inserting to roleEmployee table
            pstmt2.setString(1, ID);
            pstmt2.setString(2, role);
            pstmt2.setString(3, driverLicence);

            pstmt.executeUpdate();
            pstmt2.executeUpdate();

        }catch(SQLException e){
            return new Response(e.getMessage());
        }
        return new Response();
    }

    public Response insert(String ID, String name, int BankAccount, int salary, int sickDays, int studyFund,
                           int daysOff, LocalDate date, String role){
        return insert(ID, name, BankAccount, salary, sickDays, studyFund, daysOff, date, role, null);
    }


    public Response update(String col, String ID, String newVal){
        String sql = String.format("""
                UPDATE Employees SET %s = ?
                WHERE EmpID = ?
                """, col);
        
        try(Connection conn = getConn().getValue();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, newVal);
            pstmt.setString(2, ID);

            pstmt.executeUpdate();

        }catch(SQLException e){
            return new Response(e.getMessage());
        }

        return new Response();
    }

    public Response update(String col, String ID, int newVal){
        return update(col, ID, String.valueOf(newVal));
    }

    public Response addRole(String ID, String role, String driverLicence){
        String sql = """
                INSERT INTO RolesEmployees (EmpID, RoleName, DriverLicence)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = getConn().getValue();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, ID);
            pstmt.setString(2, role);
            pstmt.setString(3, driverLicence);

            pstmt.executeUpdate();

        }catch(SQLException e){
            return new Response(e.getMessage());
        }
        return new Response();
    }

    public Response addRole(String ID, String role){
        return addRole(ID, role, null);
    }

    public ResponseT<EmployeeDTO> get(String id){
        String empSql = String.format("""
                SELECT* FROM Employees
                WHERE EmpID = %s
                """, id);

        String rolesSql = String.format("""
                SELECT RoleName, DriverLicence
                FROM RolesEmployees
                WHERE EmpID = %s
                """, id);

        try(Connection conn = getConn().getValue();
            Statement empStmt = conn.createStatement();
            Statement rolesStmt = conn.createStatement();
            ResultSet empRs = empStmt.executeQuery(empSql);
            ResultSet rolesRs = rolesStmt.executeQuery(rolesSql)){

            List<RoleDTO> roles = new ArrayList<>();
            while(rolesRs.next())
                roles.add(new RoleDTO(rolesRs.getString(1), rolesRs.getString(2)));

            empRs.next();
            EmployeeDTO employee = new EmployeeDTO(empRs.getString("Name"), id,
                    empRs.getString("BankAccount"), empRs.getInt("Salary"),
                    empRs.getInt("SickDays"), empRs.getInt("StudyFund"),
                    empRs.getInt("DaysOff"), empRs.getString("DateOfHire"), roles);

            return new ResponseT<>(employee);

        }catch(SQLException e){
            e.printStackTrace();
            return new ResponseT<>(null, e.getMessage());
        }

    }

}
