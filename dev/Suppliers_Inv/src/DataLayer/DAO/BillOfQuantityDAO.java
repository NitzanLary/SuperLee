package DataLayer.DAO;

import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.BillOfQuantityDTO;

import java.sql.*;

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


    //SELECT
    public ResponseT<BillOfQuantityDTO> get(Integer supplierID){
        String billSQL = String.format("""
                SELECT* FROM BillOfQuantity
                WHERE supplierID = %s
                """, supplierID);

        try(Connection conn = getConn().value;
            Statement billStmt = conn.createStatement();
            ResultSet billRs = billStmt.executeQuery(billSQL);){

            if(billRs.isClosed())
                return new ResponseT<>(null, String.format("supplierID %s not found", supplierID));
            BillOfQuantityDTO bill = new BillOfQuantityDTO(billRs.getInt("supplierID"),
                    billRs.getInt("minQuantity"), billRs.getInt("percentDiscount"));

            return new ResponseT<>(bill);

        }catch(SQLException e){
            e.printStackTrace();
            return new ResponseT<>(null, e.getMessage());
        }

    }


    //TODO: update delete functions
}
