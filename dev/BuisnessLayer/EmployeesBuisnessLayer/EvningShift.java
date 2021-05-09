package BuisnessLayer.EmployeesBuisnessLayer;
import DataAccessLayer.EmployeesDataAccessLayer.DAOs.ShiftDAO;
import DataAccessLayer.EmployeesDataAccessLayer.DTOs.ShiftDTO;

import java.time.LocalDate;

import java.time.LocalTime;

public class EvningShift extends Shift {
    private final LocalTime start = LocalTime.of(14,0);
    private final LocalTime end = LocalTime.of(22,0);

    EvningShift(LocalDate _date, ShiftDAO dao) {
        super(_date, dao);
    }

    public EvningShift(ShiftDTO dto) {
        super(dto.getDate(), dto.getDao());
    }

    @Override
    public EvningShift clone() {
        EvningShift cloned = new EvningShift(date, new ShiftDAO());
        return cloned;
    }
}
