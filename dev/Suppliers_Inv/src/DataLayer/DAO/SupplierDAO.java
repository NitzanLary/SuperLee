package DataLayer.DAO;
import BussinessLayer.Response;
import DataLayer.DTO.SupplierDTO;

import java.sql.*;


public class SupplierDAO extends DAO {

    public Response insert(Integer ID, String name, String address, String email, Integer bankAcc, String paymentMethod,
                           String infoSupDay, String contacts, boolean pickUp) {

        String supplier = """
                INSERT INTO Supplier (ID, name, address, email, bankAcc, paymentMethod ,infoSupDay, contacts, pickUp)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?,?)
                """;

        try (Connection conn = getConn().value;
             PreparedStatement pstmt = conn.prepareStatement(supplier);) {

            // inserting to employee table
            pstmt.setInt(1, ID);
            pstmt.setString(2, name);
            pstmt.setString(3,address);
            pstmt.setString(4, email);
            pstmt.setInt(5, bankAcc);
            pstmt.setString(6, paymentMethod);
            pstmt.setString(7, infoSupDay);
            pstmt.setString(8, contacts);
            pstmt.setBoolean(9, pickUp);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            return new Response(e.getMessage());
        }
        return new Response();
    }


    public Response insert(SupplierDTO supplier){
        return insert(supplier.getID(), supplier.getName(), supplier.getAddress(), supplier.getEmail(),
                supplier.getBankAcc(), supplier.getPaymentMethod(), supplier.getInfoSupDay(),
                supplier.getContacts(), supplier.isPickUp());
    }

    //TODO
    public Response update(String col, String ID, String newVal){
        String sql = String.format("""
                UPDATE Employees SET %s = ?
                WHERE EmpID = ?
                """, col);

        try(Connection conn = getConn().value;
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, newVal);
            pstmt.setString(2, ID);

            pstmt.executeUpdate();

        }catch(SQLException e){
            return new Response(e.getMessage());
        }

        return new Response();
    }

}