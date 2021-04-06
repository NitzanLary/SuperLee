package Employees.BuisnessLayer;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Date;

public class MorningShift extends Shift{
    MorningShift(LocalDate _date) {
        super(_date);
        start = LocalTime.of(6,0);
        end = LocalTime.of(14,0);
    }
}
