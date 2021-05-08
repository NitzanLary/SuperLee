package Employees.BuisnessLayer;
import Employees.DataAccessLayer.DAOs.ShiftDAO;
import Employees.DataAccessLayer.DTOs.ShiftDTO;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Date;

public class MorningShift extends Shift{
    public MorningShift(LocalDate _date, ShiftDAO dao) {
        super(_date, dao);
        start = LocalTime.of(6,0);
        end = LocalTime.of(14,0);
    }

    @Override
    public MorningShift clone() {
        MorningShift cloned = new MorningShift(date, new ShiftDAO());
        cloned.start = start;
        cloned.end = end;
        return cloned;
    }
}
