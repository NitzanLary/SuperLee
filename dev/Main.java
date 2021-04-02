import Employees.BuisnessLayer.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        System.out.println("!Hello world");

//
        Employee emp1 = new Employee("Nitzan Lary", "311541214", LocalDate.parse("2019-04-03"));
        Employee emp2 = new Employee("Assaf Stern", "224266120", LocalDate.parse("2019-03-03"));
        Employee emp3 = new Employee("Refael Farjune", "300545411", LocalDate.parse("2019-06-02"));
        ShiftController sc = ShiftController.getInstance();
        ShiftController.getInstance().add1WeeksSlot();
        ShiftController.getInstance().add1WeeksSlot();
        ShiftController.getInstance().add1WeeksSlot();
        ShiftController.getInstance().add1WeeksSlot();
        ShiftController.getInstance().findShift(LocalDate.parse("2021-04-03"),14,22).AddConstrain(emp1, 3);
        ShiftController.getInstance().findShift(LocalDate.parse("2021-04-03"),14,22).AddConstrain(emp2, 3);
        ShiftController.getInstance().findShift(LocalDate.parse("2021-04-03"),14,22).AddConstrain(emp3, 3);
        System.out.println(ShiftController.getInstance().findShift(LocalDate.parse("2021-04-03"),14,22).getShiftConstrainsString().getValue());



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

    }

}