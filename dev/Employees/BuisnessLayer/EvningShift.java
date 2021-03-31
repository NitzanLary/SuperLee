package Employees.BuisnessLayer;

import java.util.Date;

public class EvningShift extends Shift {

    EvningShift(Date _date) {
        super(_date);
        start = 14;
        end = 22;
    }
}
