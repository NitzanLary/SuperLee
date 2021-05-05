package DataLayer.DAO;

import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.OrderDTO;

import java.sql.*;
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

    //SELECT
    public ResponseT<OrderDTO> get(Integer orderID){
        String orderSQL = String.format("""
                SELECT * FROM Orders
                WHERE orderID = %s
                """, orderID);

        try(Connection conn = getConn().value;
            Statement ordStmt = conn.createStatement();
            ResultSet ordRs = ordStmt.executeQuery(orderSQL);){

            if(ordRs.isClosed())
                return new ResponseT<>(null, String.format("orderID %s not found", orderID));
            OrderDTO order = new OrderDTO(ordRs.getInt("orderID"),
                    ordRs.getInt("supplierID"), ordRs.getBoolean("delivered"),
                    ordRs.getDate("supplyDate"), ordRs.getFloat("price"));

            return new ResponseT<>(order);

        }catch(SQLException e){
            e.printStackTrace();
            return new ResponseT<>(null, e.getMessage());
        }

    }

    //TODO: update delete functions

}
