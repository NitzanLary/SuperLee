package DataLayer.DAO;

import BussinessLayer.Inventory.Discount;
import BussinessLayer.Inventory.Item;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.DiscountDTO;
import DataLayer.DTO.ItemDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DiscountDAO extends DAO{
    private String table;

    public DiscountDAO(String table) {
        this.table = table;
    }

    public ResponseT<DiscountDTO> get(int itemId, LocalDate start, LocalDate end) {
        String SQL = "SELECT * FROM " + table + " WHERE itemId = ? AND start = ? AND end = ?";
        DiscountDTO toGet = null;
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, itemId);
                ps.setDate(2 , Date.valueOf(start));
                ps.setDate(3 , Date.valueOf(end));
                ResultSet rs = ps.executeQuery();
                if(!rs.isClosed()) {
                    toGet =  new DiscountDTO(rs.getDate("start").toLocalDate(), rs.getDate("end").toLocalDate(),
                    rs.getInt("discountPr"), rs.getInt("itemId"));
                }
                if (toGet == null) {
                    return new ResponseT<>(null, "cannot get");
                }
            }
        }catch (Exception e) {
            return new ResponseT(null,"cannot get");
        }
        return new ResponseT<DiscountDTO>(toGet);
    }

    public Response create(Discount dis, int itemId) {
        DiscountDTO toInsert = new DiscountDTO(dis, itemId);
        String SQL = "INSERT INTO " + table + " (itemId, start, end, discountPr) VALUES (?,?,?,?)";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toInsert.getItemId());
                ps.setDate(2 , Date.valueOf(toInsert.getStart()));
                ps.setDate(3 , Date.valueOf(toInsert.getEnd()));
                ps.setInt(4, toInsert.getDiscountPr());
                if(!ps.execute()) {
                    return new Response("cannot add discount to db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot add discount to db");
        }
        return new Response();
    }

    public Response update(Discount dis, int itemId) {
        DiscountDTO toUpdate = new DiscountDTO(dis, itemId);
        String SQL = "UPDATE " + table + " SET discountPr = ? WHERE itemId = ? AND start = ? AND end = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toUpdate.getDiscountPr());
                ps.setInt(2, toUpdate.getItemId());
                ps.setDate(3, Date.valueOf(toUpdate.getStart()));
                ps.setDate(4, Date.valueOf(toUpdate.getEnd()));

                if(!ps.execute()) {
                    return new Response("cannot update discount to db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot update discount to db");
        }
        return new Response();
    }

    public Response delete(Discount dis, int itemId) {
        DiscountDTO toDelete = new DiscountDTO(dis, itemId);
        String SQL = "DELETE FROM  " + table + "  WHERE itemId = ? AND start = ? AND end = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toDelete.getItemId());
                ps.setDate(2, Date.valueOf(toDelete.getStart()));
                ps.setDate(3, Date.valueOf(toDelete.getEnd()));
                if(!ps.execute()) {
                    return new Response("cannot delete discount from db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot delete discont from db");
        }
        return new Response();
    }
}
