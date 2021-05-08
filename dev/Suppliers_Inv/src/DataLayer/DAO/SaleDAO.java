package DataLayer.DAO;

import BussinessLayer.Inventory.Sale;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.SaleDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class SaleDAO extends DAO {

    public SaleDAO() {
    }

    public ResponseT<SaleDTO> create(Sale s) {
        SaleDTO toInsert = new SaleDTO(s);
        String SQL = "INSERT INTO Sale (itemID, date, price, cost) VALUE (?, ?, ?, ?)";
        try {
            ResponseT<Connection> r = getConn();
            if (!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toInsert.getItemID());
                ps.setDate(2, Date.valueOf(toInsert.getDate().toLocalDate()));
                ps.setDouble(3, toInsert.getPrice());
                ps.setDouble(4, toInsert.getCost());
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseT("Could not add sale to DB");
        }
        return new ResponseT<>(toInsert);
    }

    public Response delete(Sale s) {
        SaleDTO toDelete = new SaleDTO(s);
        String SQL = "DELETE FROM Sale WHERE itemID = ? AND date = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toDelete.getItemID());
                ps.setDate(2, Date.valueOf(toDelete.getDate().toLocalDate()));
                if(!ps.execute()) {
                    return new Response("cannot delete sale from db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot delete sale from db");
        }
        return new Response();
    }

    public ResponseT<List<SaleDTO>> read() {
        String SQL = "SELECT * FROM Sale";
        List<SaleDTO> saleList = new LinkedList<>();
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    saleList.add(new SaleDTO(rs.getInt("itemID"),
                            LocalDateTime.of(rs.getDate("date").toLocalDate(),
                                    LocalTime.NOON), rs.getDouble("price"),
                            rs.getDouble("cost")));
                }
            }
        }catch (Exception e) {
            return new ResponseT("cannot read sale");
        }
        return new ResponseT<>(saleList);
    }
}
