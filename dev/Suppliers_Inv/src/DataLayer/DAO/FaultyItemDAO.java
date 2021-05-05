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

public class FaultyItemDAO extends DAO{
    public FaultyItemDAO() {

    }
    public Response create(FaultyItem fi) {
        FaultyItemDTO toInsert = new FaultyItemDTO(fi);
        String SQL = "INSERT INTO faultyItem (itemId, exp, amount) VALUE (?,?,?)";
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
            return new Response("cannot add discount to db");
        }
        return new Response();
    }

    public Response update(FaultyItem fi) {
        FaultyItemDTO toUpdate = new FaultyItemDTO(fi);
        String SQL = "UPDATE faultyItem SET amount = ? WHERE itemId = ? ";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toUpdate.getName())
                if(!ps.execute()) {
                    return new Response("cannot update item to db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot update item to db");
        }
        return new Response();
    }

}
