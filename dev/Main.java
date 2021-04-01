import Employees.BuisnessLayer.Response;
import Employees.BuisnessLayer.Shift;
import Employees.BuisnessLayer.ShiftController;
import Employees.BuisnessLayer.WeeklyShifts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        System.out.println("!Hello world");
//        WeeklyShifts ws = new WeeklyShifts(LocalDate.now(), LocalDate.now().plusDays(7));
//        for(int i = 0 ; i < ws.getShifts().size() ; i++){
//            System.out.println(ws.getShifts().get(i).getDate());
//            System.out.println(ws.getShifts().get(i).getStart());
//            System.out.println(ws.getShifts().get(i).getEnd());
//            System.out.println("-----------------------------------");
//            LocalDateTime hour = LocalDate.now().atTime(14,30);
        ShiftController sc = ShiftController.getInstance();
        ShiftController.add4WeeksSlots();
        for (int i = 0 ; i < sc.getWeeklyShifts().size() ; i++) {
            System.out.println("\n");
            System.out.println("  Week: " + sc.getWeeklyShifts().get(i).getFromDate() + " Until the " + sc.getWeeklyShifts().get(i).getToDate());
            System.out.println("   " + "-----------------------------------");

            for (int j = 0; j < sc.getWeeklyShifts().get(i).getShifts().size(); j++) {
                System.out.println("|  " + sc.getWeeklyShifts().get(i).getShifts().get(j).getDate());
                System.out.println("|  " + sc.getWeeklyShifts().get(i).getShifts().get(j).getStart());
                System.out.println("|  " + sc.getWeeklyShifts().get(i).getShifts().get(j).getEnd());
                System.out.println("  -----------------------------------");
//            LocalDateTime hour = LocalDate.now().atTime(14,30);
            }

        }

    }

}