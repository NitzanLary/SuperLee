package DataLayer.DAO;

import BussinessLayer.ResponseT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {
    private final String url = "jdbc:sqlite:dev/Suppliers_Inv/src/DataLayer/InventoryDB.db";

    protected ResponseT<Connection> getConn() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ResponseT<>(null, e.getMessage());
        }
        return new ResponseT<>(conn);
    }

}

