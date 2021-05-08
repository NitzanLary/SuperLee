package DataLayer.DAO;

import BussinessLayer.Response;
import DataLayer.DTO.ProductsOfSupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsOfSupplierDAO extends DAO {

    public Response insert(Integer productID, Integer supplierID, String name, String category, double price) {

        String order = "INSERT INTO ProductsOfSupplier (productID, supplierID, name, category, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConn().value;
             PreparedStatement pstmt = conn.prepareStatement(order);) {

            // inserting to employee table
            pstmt.setInt(1, productID);
            pstmt.setInt(2, supplierID);
            pstmt.setString(3,name);
            pstmt.setString(4, category);
            pstmt.setFloat(5, (float) price);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            return new Response(e.getMessage());
        }
        return new Response();
    }


    public Response insert(ProductsOfSupplierDTO productOfSup){
        return insert(productOfSup.getProductID(), productOfSup.getSupplierID(), productOfSup.getName(),
                productOfSup.getCategory(), productOfSup.getPrice());
    }

    //TODO: update delete functions

}
