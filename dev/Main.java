import BuisnessLayer.EmployeesBuisnessLayer.FacadeController;
import BuisnessLayer.EmployeesBuisnessLayer.ShiftController;
import DataAccessLayer.DeliveryDataAccessLayer.DTO.*;
import DataAccessLayer.DeliveryDataAccessLayer.TruckDAO;
import PresentationLayer.CLI;
import PresentationLayer.DeliveryPresentationLayer.DeliveryCLI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
//        CLI cli = new CLI();
//        cli.start();

//        DeliveryCLI cli = DeliveryCLI.getInstance();
//        cli.runWithConsole();
        

    }
//    public static void main1(String[] args) {
////        System.out.println("!Hello world");
////        DeliveryController dc = new DeliveryController();
////        for(int i = 0; i< 260000; i++)
////            System.out.println(dc.getNewDeliveryID());
////        ///////////////////////////////////////
//        DeliveryCLI cli = new DeliveryCLI();
//        LocalTime time = LocalTime.of(12,0);
//        LocalTime start = LocalTime.of(12,0);
//        LocalTime end = LocalTime.of(14,0);
//        System.out.println(time.equals(start));
//        System.out.println(time.isBefore(end));
//        TruckDTO trucktest = new TruckDTO("1111111","Mercedes x", 15000,8000);
//        TruckDAO truckDAO = TruckDAO.getInstance();
//        truckDAO.storeTruck(trucktest);
//
//        /////////////  legal date tests
////        System.out.println(cli.isLegalDate("31-7-20"));
////        System.out.println(cli.isLegalDate("31-6-20"));
////        System.out.println(cli.isLegalDate("31-5-20"));
////        System.out.println(cli.isLegalDate("31-4-20"));
////        System.out.println(cli.isLegalDate("31-3-20"));
////        System.out.println(cli.isLegalDate("30-2-20"));
////        System.out.println(cli.isLegalDate("31-7-21"));
////        System.out.println(cli.isLegalDate("31-6-21"));
////        System.out.println(cli.isLegalDate("31-5-21"));
////        System.out.println(cli.isLegalDate("31-4-21"));
////        System.out.println(cli.isLegalDate("31-3-21"));
////        System.out.println(cli.isLegalDate("31-2-21"));
//        //////////// end date test
////        FacadeController facadeController = new FacadeController();
////        cli.addNewTrack();
////        cli.insertLocation();
//
//        LocalDate date = cli.getFacade().parseToLocalDate("12-12-12");
//        LocalTime t = cli.getFacade().parseToLocalTime("12:12");
//        System.out.println(date);
//        System.out.println(t);
//        System.out.println(date.toString());
//
//    }



}
