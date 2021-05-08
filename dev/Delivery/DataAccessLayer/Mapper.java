package Delivery.DataAccessLayer;

import Delivery.BusinessLayer.Area;
import Delivery.BusinessLayer.Task;
import Delivery.BusinessLayer.Truck;
import Delivery.DTO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Mapper {
    private static Mapper instance = null;
//    private HashMap<String,DeliveryDTO> deliveries;
//    private HashMap<String,TaskDTO> tasks;
//    private HashMap<String,AreaDTO> areas;
//    private HashMap<String,TruckDTO> trucks;
//    private HashMap<String,DriverDTO> drivers;
    private DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
    private AreaDAO areaDAO = AreaDAO.getInstance();
    private TaskDAO taskDAO = TaskDAO.getInstance();
    private TruckDAO truckDAO = TruckDAO.getInstance();

    private Mapper(){
//        deliveries = new HashMap<>();
//        tasks = new HashMap<>();
//        areas = new HashMap<>();
//        trucks = new HashMap<>();
//        drivers = new HashMap<>();
    }

    public static Mapper getInstance(){
        if (instance == null)
            instance = new Mapper();
        return instance;
    }

//    public void storeDelivery(DeliveryDTO delivery){
//        this.deliveries.put(delivery.getId(), delivery);
//    }
//
//    public void storeTruck(TruckDTO truckDTO){
//        this.trucks.put(truckDTO.getId(), truckDTO);
//    }
//
//    public void storeTask(TaskDTO taskDTO){
//        this.tasks.put(taskDTO.getId(), taskDTO);
//    }
//
//    public void storeArea(AreaDTO areaDTO){
//        this.areas.put(areaDTO.getAreaName(), areaDTO);
//    }

    public DeliveryDTO getDeliveryByID(String deliveryId){
        String sqlDel = "SELECT * FROM DELIVERIES WHERE DELIVERIES.ID = (?)";
        String sqlLoc = "SELECT * FROM Locations WHERE Locations.address = (?)";
        String sqlTask = "SELECT * FROM Tasks WHERE Tasks.DeliveryID = (?)";
        String sqlProduct = "SELECT * FROM Products WHERE Products.TaskID = (?)";
        DeliveryDTO retDel = null;

        try (Connection conn = deliveryDAO.connect();
             PreparedStatement pstmtDel  = conn.prepareStatement(sqlDel);
             PreparedStatement pstmtLoc  = conn.prepareStatement(sqlLoc);
             PreparedStatement pstmtTask  = conn.prepareStatement(sqlTask);
             PreparedStatement pstmtProduct  = conn.prepareStatement(sqlProduct)) {

            pstmtDel.setString(1, deliveryId);
            ResultSet rs = pstmtDel.executeQuery();
            String origin = rs.getString("origin");

            // get location
            pstmtLoc.setString(1, origin);
            ResultSet rsLoc =  pstmtLoc.executeQuery();
            LocationDTO locDTO = new LocationDTO(origin, rsLoc.getString(2), rsLoc.getString(3));

            // get tasks
            pstmtTask.setString(1, deliveryId);
            ResultSet rsTask = pstmtTask.executeQuery();

            ArrayList<TaskDTO> arrTasks = new ArrayList<>();
            while (rsTask.next()){
                // get products
                String taskID = rsTask.getString(1);
                pstmtProduct.setString(1, taskID);
                ResultSet rsProduct = pstmtTask.executeQuery();
                HashMap<String, Integer> products = new HashMap<>();
                while (rsProduct.next()){
                    products.put(rsProduct.getString(1), rsProduct.getInt(2));
                }
                pstmtLoc.setString(1, rsTask.getString("destination"));
                ResultSet destination = pstmtLoc.executeQuery();
                //add into tasks
                arrTasks.add(new TaskDTO(taskID, products, rsTask.getString(3),
                        new LocationDTO(destination.getString(1), destination.getString(2), destination.getString(3))));
            }


//            String date = rs.getDate(2).toString();
            retDel =  new DeliveryDTO(rs.getString(1), rs.getDate(2).toString(),
                    rs.getTime(3).toString(), rs.getString(4), rs.getString(5),
                    rs.getInt(6), rs.getString(7), locDTO, arrTasks);
            // TODO check if this is good

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retDel;

    }


    public AreaDTO getArea(String areaName){
        String sql = "SELECT * FROM Areas WHERE Areas.name = (?)";

        try (Connection conn = areaDAO.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1, areaName);

            ResultSet rs = pstmt.executeQuery();

            // if there is no row
            if (rs.next())
                return null;



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new AreaDTO(areaName);
    }

    public HashMap<String, ArrayList<LocationDTO>> getLocationsByArea(){
        String sql = "SELECT * FROM Locations";
        HashMap<String, ArrayList<LocationDTO>> ret = new HashMap<>();

        try (Connection conn = areaDAO.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                if (ret.containsKey(rs.getString(1))){
                    ret.get(rs.getString(1)).add(new LocationDTO(rs.getString(2),
                            rs.getString(3), rs.getString(4)));
                }
                ArrayList<LocationDTO> arr = new ArrayList<>();
                arr.add(new LocationDTO(rs.getString(2), rs.getString(3), rs.getString(4)));
                ret.put(rs.getString(1), arr);
            }

            // if there is no row
            if (rs.next())
                return null;

            return ret;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new AreaDTO(areaName);
    }

    public HashMap<String, TaskDTO> getTasks() {
        return tasks;
    }

//    public HashMap<AreaDTO, ArrayList<LocationDTO>> getLocations(){
//        String sql = "SELECT * FROM Locations";
//
//        try (Connection conn = areaDAO.connect();
//             PreparedStatement pstmt  = conn.prepareStatement(sql)){
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public HashMap<String, AreaDTO> getAreas() {
//        return areas;
//    }

    public HashMap<String, TruckDTO> getTrucks() {
        return trucks;
    }

    public HashMap<String, DriverDTO> getDrivers() {
        return drivers;
    }

//    public void storeLocation(AreaDTO areaDTO, LocationDTO locationDTO){
//        areas.get(areaDTO.getAreaName()).addLocation(locationDTO);
//    }

}
