package DataLayer.DAO;

import BussinessLayer.Inventory.Sale;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.SaleDTO;

import java.sql.*;

public class SaleDAO extends DAO {

    public SaleDAO() {
    }

    public Response create(Sale s) {
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

                if (!ps.execute()) {
                    return new Response("Could not add sale to DB");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response("Could not add sale to DB");
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

    public Response read(int itemID, Date date) {
        String SQL = "SELECT * FROM Sale WHERE itemID = ? AND date = ?";
        SaleDTO toGet = null;
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, itemID);
                ps.setDate(2, date);
                ResultSet rs = ps.executeQuery();
                if(!rs.isClosed()) {
                    toGet =  new SaleDTO(rs.getInt("itemID"), rs.getDate("date").toLocalDate().atStartOfDay(), rs.getDouble("price"), rs.getDouble("cost"));
                }
                if (toGet == null) {
                    return new Response("cannot read sale");
                }
            }
        }catch (Exception e) {
            return new Response("cannot read sale");
        }
        return new ResponseT<>(toGet);
    }
}
