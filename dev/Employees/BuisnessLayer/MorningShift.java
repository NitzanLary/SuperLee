package Employees.BuisnessLayer;

import java.util.Date;

public class MorningShift extends Shift{
    MorningShift(Date _date) {
        super(_date);
        start = 6;
        end = 14;
    }
}
