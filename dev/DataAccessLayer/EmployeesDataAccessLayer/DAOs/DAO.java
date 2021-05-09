package DataAccessLayer.EmployeesDataAccessLayer.DAOs;

import BuisnessLayer.EmployeesBuisnessLayer.ResponseT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {
    private final String url = "jdbc:sqlite:dev/DataBase/SuperLeeDB.db";

    protected Connection getConn() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url);
        return conn;
    }

}
