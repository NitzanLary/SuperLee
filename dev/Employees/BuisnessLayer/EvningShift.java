package Employees.BuisnessLayer;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Date;

public class EvningShift extends Shift {

    EvningShift(LocalDate _date) {
        super(_date);
        start = LocalTime.of(14,0);
        end = LocalTime.of(22,0);
    }

    @Override
    public EvningShift clone() {
        EvningShift cloned = new EvningShift(date);
        cloned.start = start;
        cloned.end = end;
        return cloned;
    }
}
