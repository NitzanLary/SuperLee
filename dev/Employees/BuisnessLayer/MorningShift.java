package Employees.BuisnessLayer;
import java.time.LocalDate;

import java.util.Date;

public class MorningShift extends Shift{
    MorningShift(LocalDate _date) {
        super(_date);
        start = 6;
        end = 14;
    }
}
