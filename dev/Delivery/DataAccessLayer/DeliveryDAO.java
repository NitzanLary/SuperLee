package Delivery.DataAccessLayer;

import Delivery.DTO.AreaDTO;
import Delivery.DTO.DeliveryDTO;
import Delivery.DTO.LocationDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeliveryDAO extends DAO{
    private static DeliveryDAO instance = null;

    private DeliveryDAO(){
        super();
    }

    public static DeliveryDAO getInstance() {
        if (instance == null)
            instance = new DeliveryDAO();
        return instance;
    }

    public void storeDelivery(DeliveryDTO deliveryDTO){
        String sql = "INSERT INTO Delivery(id, date, timeOfDeparture, truckNumber, driverName, departureWeightInt, modification, origin) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deliveryDTO.getId());
            pstmt.setDate(2, Date.valueOf(deliveryDTO.getDate())); // TODO: need to check if this OK !
            pstmt.setTime(3, Time.valueOf(deliveryDTO.getTimeOfDeparture())); // TODO: need to check if this OK !
            pstmt.setString(4, deliveryDTO.getTruckNumber());
            pstmt.setString(5, deliveryDTO.getDriverName());
            pstmt.setInt(6, deliveryDTO.getDepartureWeight());
            pstmt.setString(7, deliveryDTO.getModification());
            pstmt.setString(8, deliveryDTO.getOrigin().getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        // todo - update tasks delivery ids
    }

    public DeliveryDTO getDeliveryByID(String deliveryId){
        String sql = "SELECT * FROM DELIVERIES WHERE DELIVERIES.ID = (?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            pstmt.setString(1, deliveryId);

            ResultSet rs = pstmt.executeQuery();
            String date = rs.getDate(2).toString();
//            return new DeliveryDTO(rs.getString(1))
            // TODO need to add here more details to send back a DeliveryDTO

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

}
