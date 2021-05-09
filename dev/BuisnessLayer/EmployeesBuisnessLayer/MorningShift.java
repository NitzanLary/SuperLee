package BuisnessLayer.EmployeesBuisnessLayer;
import DataAccessLayer.EmployeesDataAccessLayer.DAOs.ShiftDAO;
import DataAccessLayer.EmployeesDataAccessLayer.DTOs.ShiftDTO;

import java.time.LocalDate;

import java.time.LocalTime;

public class MorningShift extends Shift{
    private final LocalTime start = LocalTime.of(6,0);
    private final LocalTime end = LocalTime.of(14,0);

    public MorningShift(LocalDate _date, ShiftDAO dao) {
        super(_date, dao);
//        start = LocalTime.of(6,0);
//        end = LocalTime.of(14,0);
    }

    public MorningShift(ShiftDTO dto) {
        super(dto.getDate(), dto.getDao());

    }

    @Override
    public MorningShift clone() {
        MorningShift cloned = new MorningShift(date, new ShiftDAO());
//        cloned.start = start;
//        cloned.end = end;
        return cloned;
    }
}
