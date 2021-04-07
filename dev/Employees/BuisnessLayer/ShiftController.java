package Employees.BuisnessLayer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


//This class is Singleton

public class ShiftController {

    private static ShiftController shiftController = null;
    private ArrayList<WeeklyShifts> weeklyShifts;

    private ShiftController(){
        weeklyShifts = new ArrayList<>();  //creating with 4 weeks slots
//        ShiftController.add4WeeksSlots();
    }

    // static method to create instance of Singleton class
    public static ShiftController getInstance()
    {
        if (shiftController == null)
            shiftController = new ShiftController();
        return shiftController;
    }

    public List<WeeklyShifts> getWeeklyShifts(){return weeklyShifts;}

    public Response add4WeeksSlots(){
        if (weeklyShifts.isEmpty()){
            LocalDate tempDate = LocalDate.now();
            for (int i = 0 ; i < 4 ; i++) {
                weeklyShifts.add(new WeeklyShifts(tempDate.plusWeeks(i), tempDate.plusWeeks(i+1)));
            }
        }
        else{
            //Star to add slots from day after the last day we have in our weeklyShifts list
            LocalDate tempDate = weeklyShifts.get(weeklyShifts.size()-1).getToDate().plusDays(1);
            for (int i = 0 ; i < 4 ; i++) {
                weeklyShifts.add(new WeeklyShifts(tempDate.plusWeeks(i), tempDate.plusWeeks(i+1)));
                }
        }
        return new Response();
    }

    public void add1WeeksSlot(){
        LocalDate tempDate;
        if (weeklyShifts.isEmpty()){
            tempDate = LocalDate.now();
        }
        else{
            //Star to add slots from day after the last day we have in our weeklyShifts list
            tempDate = weeklyShifts.get(weeklyShifts.size() - 1).getToDate();
        }
        weeklyShifts.add(new WeeklyShifts(tempDate, tempDate.plusWeeks(1)));
    }

    public ResponseT<Shift> findShift(LocalDate date, LocalTime StartTime, LocalTime EndTime){ // Todo: maybe optimize
        for(WeeklyShifts ws : weeklyShifts){
            for(Shift s : ws.getShifts()){
                if (s.compare(date, StartTime, EndTime))
                    return new ResponseT<>(s);
            }
        }
        return new ResponseT<>(null, "Shift not found");
    }

    public ResponseT<List<Shift>> getShiftsByDate(LocalDate date){
        List<Shift> shifts = new ArrayList<>();
        for (WeeklyShifts ws: weeklyShifts)
            for (Shift s: ws.getShifts())
                if (date.compareTo(s.getDate()) == 0)
                    shifts.add(s);
        if (shifts.isEmpty())
            return new ResponseT<>(null, "No shifts on this date");
        return new ResponseT<>(shifts);
    }

    public Response putConstrain(Employee employee, LocalDate date, LocalTime start, LocalTime end, int pref/*0-want 1-can 2-cant*/) {
        ResponseT<Shift> rS = findShift(date, start, end);
        if (!rS.isErrorOccured())
            return rS.getValue().AddConstrain(employee, pref);
        return rS;

    }

    public ResponseT<List<Shift>> getFutureShifts(){ // todo: need to be tested
        List<Shift> shifts = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for(WeeklyShifts week: weeklyShifts){
            if (now.compareTo(week.getToDate()) <= 0){ // positive if now date is after week.getToDate()
                for (Shift s: week.getShifts()){
                    if (now.compareTo(s.getDate()) <= 0)
                        shifts.add(s);
                }
            }
        }
        if(shifts.isEmpty())
            return new ResponseT<>(null, "No future shifts");
        return new ResponseT<>(shifts);
    }

    public Response assignToShift(Employee employee, LocalDate date, LocalTime start, LocalTime end, String role){
        if(!employee.haveRoleCheck(role))
            return new Response("The role does not match with the employee's roles");
        ResponseT<List<Shift>> rShifts = getShiftsByDate(date);
        if(rShifts.isErrorOccured())
            return rShifts;
        for(Shift s: rShifts.getValue())
            if (s.isAssigned(employee))
                return new ResponseT<>(null, "The employee already assigned to a shift on this day");
        ResponseT<Shift> rS = findShift(date, start, end);
        if(rS.isErrorOccured())
            return rS;
        return rS.getValue().AssignEmployee(employee);
    }


    public Response closeShift(LocalDate date, LocalTime start, LocalTime end) {
        ResponseT<Shift> rS = findShift(date, start, end);
        if(rS.isErrorOccured())
            return rS;
        return rS.getValue().close();
    }

    public Response openShift(LocalDate date, LocalTime start, LocalTime end) {
        ResponseT<Shift> rS = findShift(date, start, end);
        if(rS.isErrorOccured())
            return rS;
        return rS.getValue().open();
    }

    public ResponseT<String> getEmployeesConstrainsForShift(Employee employee, LocalDate date, LocalTime start, LocalTime end) {
        ResponseT<Shift> rS = findShift(date, start, end);
        if(rS.isErrorOccured())
            return new ResponseT<>(null, rS.getErrorMessage());
        return rS.getValue().getShiftConstrainsString();
    }

    public ResponseT<String> getWhoIWorkWith(Employee employee, LocalDate date, LocalTime start, LocalTime end) {
        ResponseT<Shift> rS = findShift(date, start, end);
        if (rS.isErrorOccured())
            return new ResponseT<>(null, rS.getErrorMessage());
        return rS.getValue().getWhoIWorkWith(employee);
    }

    public ResponseT<String> getMyPreferences(Employee employee, LocalDate date, LocalTime start, LocalTime end) {
        ResponseT<Shift> rS = findShift(date, start, end);
        if (rS.isErrorOccured())
            return new ResponseT<>(null, rS.getErrorMessage());
        return rS.getValue().getEmpPreferences(employee);
    }

}
