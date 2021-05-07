import Employees.PresentationLayer.CLI;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
//        CLI.getInstance().start();
        LocalTime time = LocalTime.of(12,0);
        LocalTime start = LocalTime.of(12,0);
        LocalTime end = LocalTime.of(14,0);
        System.out.println(time.equals(start));
//        System.out.println(time.isBefore(end));
    }

}
