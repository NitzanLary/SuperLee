package DataLayer.DAO;

import BussinessLayer.Response;
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

    //TODO: update delete functions

}
