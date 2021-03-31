package Employees.BuisnessLayer;

import java.util.Date;
import java.util.List;

public class WeeklyShifts {

    private Date fromDate;
    private Date toDate;
    private List<Shift> shifts;

    WeeklyShifts(Date _fromDate, Date _todate){
        fromDate = _fromDate;
        toDate = _todate;

    }





    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<Shift> getShifts() {
        return shifts;
    }



}
