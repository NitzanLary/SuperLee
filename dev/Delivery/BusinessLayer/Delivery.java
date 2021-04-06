package Delivery.BusinessLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Delivery {
    private String id;
    private String date;
    private String timeOfDeparture;
    private String truckNumber;
    private String driverName;
    private int departureWeight;
    private String modification;
    private Location origin;
    private ArrayList<Task> destinations;


    public Delivery(String id, String date, String timeOfDeparture, String truckNumber, String driverName, int departureWeight, String modification, Location origin, ArrayList<Task> destinations){
        this.id = id;
        this.date =date;
        this.timeOfDeparture = timeOfDeparture;
        this.truckNumber = truckNumber;
        this.driverName = driverName;
        this.departureWeight = departureWeight;
        this.modification = modification; // " - old g121 -- new g123 -"
        this.origin = origin;
        this.destinations = destinations;
    }

    public Delivery(){}



    public void setDriver(Driver dr) {
        driverName = dr.getName();
    }

    public Delivery clone(){return null;} //todo  clone.modification = "- old "+this.id+" -"

    public void makeOld(String oldID){
        modification += "- new "+ oldID+ " -";
    }

    @Override
    public String toString() {
        return "\n\t\tDelivery : {" +
                "\n\t\tid ='" + id + '\'' +
                "\n\t\tdate = '" + date + '\'' +
                "\n\t\ttimeOfDeparture = '" + timeOfDeparture + '\'' +
                "\n\t\ttruckNumber = '" + truckNumber + '\'' +
                "\n\t\tdriverName = '" + driverName + '\'' +
                "\n\t\tdepartureWeight = " + departureWeight +
                "\n\t\tmodification = '" + modification + '\'' +
                "\n\t\torigin = " + origin +
                "\n\t\tdestinations= " + destinations +
                '}';
    }

    public String getID() {
        return id;
    }


    public boolean isUpdatable(){
//        SimpleDateFormat sdf = new SimpleDateFormat("d-M-uu");
//        SimpleDateFormat sdt = new SimpleDateFormat("h:m");
        String[] spl = date.split("-");
        String[] splTIme = timeOfDeparture.split(":");
        int year = Integer.parseInt(spl[2])+2000;
        int month = Integer.parseInt(spl[1]);
        int day = Integer.parseInt(spl[0]);
        int hour = Integer.parseInt(splTIme[0]);
        int min = Integer.parseInt(splTIme[1]);
        LocalDate l1 = LocalDate.of(year,month,day);
        LocalTime l2 = LocalTime.of(hour, min);
        LocalDate current = LocalDate.now();
        LocalTime curT = LocalTime.now();
        if (l1.compareTo(current) == 0) {
            if (l2.compareTo(curT) < 0) {
                return false;
            }
        }
        if (l1.compareTo(current) < 0)
            return false;
        return true;
    }
}
