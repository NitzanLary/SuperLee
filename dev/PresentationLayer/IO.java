package PresentationLayer;

import java.time.LocalDate;
import java.util.Scanner;

public class IO {
    private static IO instance;
    private IO_Controller ioCtrl;
    private Scanner scanner;

    public static IO getInstance() {
        if (instance == null) {
            instance = new IO();
        }
        return instance;
    }

    private IO() {
        ioCtrl = new IO_Controller();
        scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("1)Add sale\n" +
                "2)Report Faulty\n" +
                "3)Add Item\n" +
                "4)Add Category");
        int action = scanner.nextInt();
        while (action <= 4 && action >= 1) {
            ioCtrl.act(action);
            System.out.println("1)Add sale\n" +
                    "2)Report Faulty\n" +
                    "3)Add Item\n" +
                    "4)Add Category");
            action = scanner.nextInt();
        }

    }

    public int getInt(String msg) {
        System.out.println(msg);
        return scanner.nextInt();
    }

    public double getDouble(String msg) {
        System.out.println(msg);
        return scanner.nextDouble();
    }

    public String getString(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    public LocalDate getDate(String msg) {
        System.out.println(msg);
        System.out.println("Enter the day:");
        int day = scanner.nextInt();
        System.out.println("Enter the month:");
        int month = scanner.nextInt();
        System.out.println("Enter the year:");
        int year = scanner.nextInt();
        return LocalDate.of(year,month,day);
    }
}
