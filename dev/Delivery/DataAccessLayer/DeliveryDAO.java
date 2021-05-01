package Delivery.DataAccessLayer;

public class DeliveryDAO {
    private static DeliveryDAO instance = null;

    public static DeliveryDAO getInstance() {
        if (instance == null)
            instance = new DeliveryDAO();
        return instance;
    }
}
