package Employees.BuisnessLayer;
import java.time.LocalDate;

import java.util.Date;

public class EvningShift extends Shift {

    EvningShift(LocalDate _date) {
        super(_date);
        start = 14;
        end = 22;
    }
}
