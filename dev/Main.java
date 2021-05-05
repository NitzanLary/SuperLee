import Employees.BuisnessLayer.MorningShift;
import Employees.BuisnessLayer.Shift;
import Employees.DataAccessLayer.DTOs.ShiftDTO;
import Employees.DataAccessLayer.Objects.ShiftDate;
import Employees.PresentationLayer.CLI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        CLI.getInstance().start();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        ShiftDate s1 = new ShiftDate(date, time, time);
        ShiftDate s2 = new ShiftDate(date, time, time);

        Map<ShiftDate, Integer> map = new HashMap<>();
        map.put(s1, 5);
        System.out.println(map.get(s2));

    }



}