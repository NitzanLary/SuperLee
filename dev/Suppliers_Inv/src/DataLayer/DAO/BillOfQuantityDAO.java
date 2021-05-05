package DataLayer.DAO;

import BussinessLayer.Response;
import DataLayer.DTO.BillOfQuantityDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillOfQuantityDAO extends DAO {


    public Response insert(Integer supplierID, Integer minQuantity, Integer percentDiscount) {

        String billOfQuantity = """
                INSERT INTO BillOfQuantity (supplierID, minQuantity, percentDiscount)
                VALUES
                (?, ?, ?)
                """;

        try (Connection conn = getConn().value;
             PreparedStatement pstmt = conn.prepareStatement(billOfQuantity);) {

            // inserting to employee table
            pstmt.setInt(1, supplierID);
            pstmt.setInt(2, minQuantity);
            pstmt.setInt(3,percentDiscount);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            return new Response(e.getMessage());
        }
        return new Response();
    }


    public Response insert(BillOfQuantityDTO boq){
        return insert(boq.getSupplierID(), boq.getMinQuantity(), boq.getPercentDiscount());
    }

    //TODO: update delete functions
}
