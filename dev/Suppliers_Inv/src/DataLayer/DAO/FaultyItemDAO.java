package DataLayer.DAO;

import BussinessLayer.Inventory.Discount;
import BussinessLayer.Inventory.FaultyItem;
import BussinessLayer.Inventory.Item;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.DiscountDTO;
import DataLayer.DTO.FaultyItemDTO;
import DataLayer.DTO.ItemDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class FaultyItemDAO extends DAO{
    public FaultyItemDAO() {

    }

    public ResponseT<FaultyItemDTO> get(int itemID, LocalDate expDate) {
        String SQL = "SELECT * FROM faultyItem WHERE itemId = ? AND expDate = ?";
        FaultyItemDTO toGet = null;
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, itemID);
                ps.setDate(2 , Date.valueOf(expDate));
                ResultSet rs = ps.executeQuery();
                if(!rs.isClosed()) {
                    toGet =  new FaultyItemDTO(rs.getInt("itemID"), rs.getDate("expDate").toLocalDate(), rs.getInt("amount"));
                }
                if (toGet == null) {
                    return new ResponseT<>(null, "cannot get");
                }
            }
        }catch (Exception e) {
            return new ResponseT(null,"cannot get");
        }
        return new ResponseT<FaultyItemDTO>(toGet);
    }

    public Response create(FaultyItem fi) {
        FaultyItemDTO toInsert = new FaultyItemDTO(fi);
        String SQL = "INSERT INTO faultyItem (itemId, exp, amount) VALUES (?,?,?)";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toInsert.getItemId());
                ps.setDate(2 , Date.valueOf(toInsert.getExpDate()));
                ps.setInt(3 , toInsert.getAmount());
                if(!ps.execute()) {
                    return new Response("cannot add faulty item to db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot add faulty item to db");
        }
        return new Response();
    }

    public Response update(FaultyItem fi) {
        FaultyItemDTO toUpdate = new FaultyItemDTO(fi);
        String SQL = "UPDATE faultyItem SET amount = ? WHERE itemId = ? AND expDate = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toUpdate.getAmount());
                ps.setInt(2, toUpdate.getItemId());
                ps.setDate(3, Date.valueOf(toUpdate.getExpDate()));
                if(!ps.execute()) {
                    return new Response("cannot update faulty item to db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot update faulty item to db");
        }
        return new Response();
    }

    public Response delete(FaultyItem fi) {
        FaultyItemDTO toDelete = new FaultyItemDTO(fi);
        String SQL = "DELETE FROM faultyItem WHERE itemId = ? AND expDate = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toDelete.getItemId());
                ps.setDate(2, Date.valueOf(toDelete.getExpDate()));
                if(!ps.execute()) {
                    return new Response("cannot delete faulty item from db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot delete faulty item from db");
        }
        return new Response();
    }

}
