package Employees.BuisnessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WeeklyShifts {

    private LocalDate fromDate;
    private LocalDate toDate;
    private ArrayList<Shift> shifts;

    public WeeklyShifts(LocalDate _fromDate, LocalDate _toDate){
        fromDate = _fromDate;
        toDate = _toDate;
        shifts = new ArrayList<>();
        for (int i=0; i < 7 ; i++){
            shifts.add(new MorningShift(fromDate.plusDays(i)));
            shifts.add(new EvningShift(fromDate.plusDays(i)));
        }

    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public List<Shift> getShifts() {
        return shifts;
    }



}
