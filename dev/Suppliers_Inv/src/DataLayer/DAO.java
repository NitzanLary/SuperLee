package DataLayer;

import BussinessLayer.ResponseT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {
    private final String url = "jdbc:sqlite:dev/DataBase/SuperLeeDB.db";

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

