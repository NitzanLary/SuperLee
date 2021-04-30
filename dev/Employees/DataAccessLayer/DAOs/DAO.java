package Employees.DataAccessLayer.DAOs;

import Employees.BuisnessLayer.ResponseT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {
    private final String url = "jdbc:sqlite:dev/SuperLeeDB.db";

    public ResponseT<Connection> getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            return new ResponseT<>(null, e.getMessage());
        }
        return new ResponseT<>(conn);
    }



}
