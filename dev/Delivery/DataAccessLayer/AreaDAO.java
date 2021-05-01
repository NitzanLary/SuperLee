package Delivery.DataAccessLayer;

public class AreaDAO {
    private static AreaDAO instance = null;
    public static AreaDAO getInstance() {
        if (instance == null)
            instance = new AreaDAO();
        return instance;
    }
}
