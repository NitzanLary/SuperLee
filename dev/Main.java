//import Employees.PresentationLayer.CLI;

import Delivery.PresentationLayer.CLI;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
//        CLI.getInstance().start();
        CLI cli = new Delivery.PresentationLayer.CLI();
        LocalDate l = cli.getFacade().parseToLocalDate("12-12-12");
        LocalTime time = cli.getFacade().parseToLocalTime("12:12");
        System.out.println(l);
        System.out.println(time);
    }

}
