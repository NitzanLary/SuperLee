package DataLayer.DAO;

import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.ProductsInOrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsInOrderDAO extends DAO {

    public Response insert(Integer orderID, Integer productID, String quantity, Integer supplierID) {

        String order = "INSERT INTO ProductsInOrder (orderID, productID, quantity, supplierID) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConn().value;
             PreparedStatement pstmt = conn.prepareStatement(order);) {

            // inserting to employee table
            pstmt.setInt(1, orderID);
            pstmt.setInt(2, productID);
            pstmt.setString(3,quantity);
            pstmt.setInt(4, supplierID);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            return new Response(e.getMessage());
        }
        return new Response();
    }


    public Response insert(ProductsInOrderDTO productInOrder){
        return insert(productInOrder.getOrderID(), productInOrder.getProductID(), productInOrder.getQuantity(),
                productInOrder.getSupplierID());
    }

    public Response delete(Integer orderID, Integer productID, Integer supplierID) {
        String SQL = "DELETE FROM ProductsInOrder WHERE orderID = ? AND productID = ? AND supplierID = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, orderID);
                ps.setInt(2, productID);
                ps.setInt(3, supplierID);

                if(!ps.execute()) {
                    return new Response("cannot delete "+productID+" from order "+orderID+ " in db");
                }
            }
        }catch (SQLException e) {
            return new Response(e.getMessage());
        }
        return new Response();
    }
    //TODO: update functions???

}
