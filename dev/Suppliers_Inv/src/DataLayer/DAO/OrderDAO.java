package DataLayer.DAO;

import BussinessLayer.Response;
import DataLayer.DTO.OrderDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAO extends DAO{


    public Response insert(Integer orderID, Integer supplierID, boolean delivered, LocalDate supplyDate, double price) {

        String order = """
                INSERT INTO Orders (orderID, supplierID, delivered, supplyDate, price)
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try (Connection conn = getConn().value;
             PreparedStatement pstmt = conn.prepareStatement(order);) {

            // inserting to employee table
            pstmt.setInt(1, orderID);
            pstmt.setInt(2, supplierID);
            pstmt.setBoolean(3,delivered);
            pstmt.setDate(4, Date.valueOf(supplyDate));
            pstmt.setFloat(5, (float) price);


            pstmt.executeUpdate();

        } catch (SQLException e) {
            return new Response(e.getMessage());
        }
        return new Response();
    }


    public Response insert(OrderDTO order){
        return insert(order.getOrderID(), order.getSupplierID(), order.isDelivered(), order.getSupplyDate(), order.getPrice());
    }

    //TODO: update delete functions

}
