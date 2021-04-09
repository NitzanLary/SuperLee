import Employees.BuisnessLayer.*;
import Employees.PresentationLayer.CLI;
import Employees.PresentationLayer.CLIController;
import Employees.PresentationLayer.MassageHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println("!Hello world");

//        FacadeController f1 = FacadeController.getInstance();
//        FacadeController f2 = FacadeController.getInstance();

//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("123", 5);
//        System.out.println(map.containsKey("123"));




//        System.out.println("1) Add sale\n" +
//                "2) Report Faulty\n" +
//                "3) Add Item\n" +
//                "4) Add Category\n" +
//                "5) Faulty Report\n" +
//                "6) Edit\n" +
//                "7) Inventory report\n" +
//                "9) Exit");

//        LocalTime lt = LocalTime.of(6,0);
//        LocalTime end = LocalTime.of(14,0);
//
//        Employee emp1 = new Employee("Nitzan Lary", "311541214", LocalDate.parse("2019-04-03"));
//        Employee emp2 = new Employee("Assaf Stern", "224266120", LocalDate.parse("2019-03-03"));
//        Employee emp3 = new Employee("Refael Farjune", "300545411", LocalDate.parse("2019-06-02"));
//        ShiftController sc = ShiftController.getInstance();
//        sc.add1WeeksSlot();
//        sc.add1WeeksSlot();
//        sc.add1WeeksSlot();
//        sc.add1WeeksSlot();
//        sc.findShift(LocalDate.parse("2021-04-09"),lt,end).getValue().AddConstrain(emp1, 1);
//        sc.findShift(LocalDate.parse("2021-04-09"),lt,end).getValue().AddConstrain(emp2, 1);
//        sc.findShift(LocalDate.parse("2021-04-09"),lt,end).getValue().AddConstrain(emp3, 1);
//        System.out.println(sc.findShift(LocalDate.parse("2021-04-09"),lt,end).getValue().getShiftConstrainsString().getValue());



//        System.out.println(sc.getWeeklyShifts().size());
//        for (int i = 0 ; i < sc.getWeeklyShifts().size() ; i++) {
//            System.out.println("\n");
//            System.out.println("  Week: " + sc.getWeeklyShifts().get(i).getFromDate() + " Until the " + sc.getWeeklyShifts().get(i).getToDate());
//            System.out.println("   " + "-----------------------------------");
//
//            for (int j = 0; j < sc.getWeeklyShifts().get(i).getShifts().size(); j++) {
//                System.out.println("|  " + sc.getWeeklyShifts().get(i).getShifts().get(j).getDate());
//                System.out.println("|  " + sc.getWeeklyShifts().get(i).getShifts().get(j).getStart());
//                System.out.println("|  " + sc.getWeeklyShifts().get(i).getShifts().get(j).getEnd());
//                System.out.println("  -----------------------------------");
//            }
//
//        }

        FacadeController.getInstance().initData();
        CLI.getInstance().start();

    }

}