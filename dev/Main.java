import Delivery.PresentationLayer.CLI;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
//        CLI.getInstance().start();
        CLI cli = new CLI();
        LocalTime time = LocalTime.of(12,0);
        LocalTime start = LocalTime.of(12,0);
        LocalTime end = LocalTime.of(14,0);
        System.out.println(time.equals(start));
        System.out.println(time.isBefore(end));

        LocalDate date = cli.getFacade().parseToLocalDate("12-12-12");
        LocalTime t = cli.getFacade().parseToLocalTime("12:12");
        System.out.println(date);
        System.out.println(t);
        System.out.println(date.toString());

    }

}
