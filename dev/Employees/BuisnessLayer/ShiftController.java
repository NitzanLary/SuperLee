package Employees.BuisnessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//This class is Singleton

public class ShiftController {

    private static ShiftController shiftController = null;
    private ArrayList<WeeklyShifts> weeklyShifts;

    private ShiftController(){
        weeklyShifts = new ArrayList<WeeklyShifts>();  //creating with 4 weeks slots
//        ShiftController.add4WeeksSlots();
    }

    // static method to create instance of Singleton class
    public static ShiftController getInstance()
    {
        if (shiftController == null)
            shiftController = new ShiftController();
        return shiftController;
    }

    public Response add4WeeksSlots(){
        if (shiftController.weeklyShifts.isEmpty()){
            LocalDate tempDate = LocalDate.now();
            for (int i = 0 ; i < 4 ; i++) {
                shiftController.getInstance().weeklyShifts.add(new WeeklyShifts(tempDate.plusWeeks(i), tempDate.plusWeeks(i+1)));
            }
        }
        else{
            //Star to add slots from day after the last day we have in our weeklyShifts list
            LocalDate tempDate = shiftController.weeklyShifts.get(shiftController.weeklyShifts.size()-1).getToDate().plusDays(1);
            for (int i = 0 ; i < 4 ; i++) {
                shiftController.weeklyShifts.add(new WeeklyShifts(tempDate.plusWeeks(i), tempDate.plusWeeks(i+1)));
                }
        }
        return new Response();
    }

    public void add1WeeksSlot(){
        if (shiftController.weeklyShifts.isEmpty()){
            LocalDate tempDate = LocalDate.now();
            shiftController.weeklyShifts.add(new WeeklyShifts(tempDate, tempDate.plusWeeks(1)));
        }
        else{
            //Star to add slots from day after the last day we have in our weeklyShifts list
            LocalDate tempDate = shiftController.weeklyShifts.get(shiftController.weeklyShifts.size()-1).getToDate();
            shiftController.weeklyShifts.add(new WeeklyShifts(tempDate, tempDate.plusWeeks(1)));
        }
    }

    public Shift findShift(LocalDate date, int StartTime, int EndTime){
        for(WeeklyShifts ws : weeklyShifts){
            for(Shift s : ws.getShifts()){
                if (s.compare(date, StartTime, EndTime))
                    return s;
            }
        }
        return null;
    }



}
