package DataLayer.DAO;

import BussinessLayer.Inventory.Item;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ItemDAO extends DAO {

    public Response create(Item item) {
        ItemDTO toInsert = new ItemDTO(item);
        String SQL = "INSERT INTO item (itemId, name, price, shelfNum, manufacturer, shelfQuantity, storageQuantity, minAlert, cost) VALUE (?,?,?,?,?,?,?,?)";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toInsert.getId());
                ps.setString(2, toInsert.getName());
                ps.setDouble(3, toInsert.getPrice());
                ps.setInt(4, toInsert.getShelfNum());
                ps.setString(5, toInsert.getManufacturer());
                ps.setInt(6, toInsert.getShelfQuantity());
                ps.setInt(7, toInsert.getStorageQuantity());
                ps.setInt(8, toInsert.getMinAlert());
                ps.setDouble(9, toInsert.getCost());
                if(!ps.execute()) {
                    return new Response("cannot add item to db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot add item to db");
        }
        return new Response();
    }

    public Response update(Item item) {
        ItemDTO toUpdate = new ItemDTO(item);
        String SQL = "UPDATE item SET name = ?, price = ?, shelfNum = ?, manufacturer = ?, shelfQuantity = ?, storageQuantity = ?, cost = ?, minAlert = ? WHERE itemId = ? ";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toUpdate.getName());
                ps.setDouble(2, toUpdate.getPrice());
                ps.setInt(3, toUpdate.getShelfNum());
                ps.setString(4, toUpdate.getManufacturer());
                ps.setInt(5, toUpdate.getShelfQuantity());
                ps.setInt(6, toUpdate.getStorageQuantity());
                ps.setDouble(7 , toUpdate.getCost());
                ps.setInt(8, toUpdate.getMinAlert());
                ps.setInt(9, toUpdate.getId());
                if(!ps.execute()) {
                    return new Response("cannot update item to db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot update item to db");
        }
        return new Response();
    }

    public Response delete(Item item) {
        ItemDTO toDelete = new ItemDTO(item);
        String SQL = "DELETE FROM item WHERE itemId = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setInt(1, toDelete.getId());
                if(!ps.execute()) {
                    return new Response("cannot delete item from db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot delete item from db");
        }
        return new Response();
    }
}
