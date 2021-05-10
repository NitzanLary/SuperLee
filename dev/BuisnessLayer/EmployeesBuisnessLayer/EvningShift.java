package BuisnessLayer.EmployeesBuisnessLayer;
import DataAccessLayer.EmployeesDataAccessLayer.DAOs.ShiftDAO;
import DataAccessLayer.EmployeesDataAccessLayer.DTOs.ShiftDTO;

import java.time.LocalDate;

import java.time.LocalTime;

public class EvningShift extends Shift {

    EvningShift(LocalDate _date, ShiftDAO dao) {
        super(_date, dao);
        start = LocalTime.of(14,0);
        end = LocalTime.of(22,0);
    }

    public EvningShift(ShiftDTO dto) {
        super(dto.getDate(), dto.getDao());
    }

    @Override
    public EvningShift clone() {
        return new EvningShift(date, new ShiftDAO());
    }
}
