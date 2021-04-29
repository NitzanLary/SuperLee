package Employees.DataAccessLayer.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {
    private final String url = "jdbc:sqlite:dev/SuperLeeDB.db";

    public Connection getConn() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {

        }
        return conn;
    }

}
