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
        int action;
        do {
            System.out.println("1) Add sale\n" +
                    "2) Report Faulty\n" +
                    "3) Add Item\n" +
                    "4) Add Category\n" +
                    "5) Faulty Report");
            action = scanner.nextInt();
            if (action > 5 || action < 1)
                break;
            scanner.nextLine();
            ioCtrl.act(action);
        } while (true);

    }

    public int getInt(String msg) {
        System.out.println(msg);
        int out = scanner.nextInt();
        scanner.nextLine();
        return out;
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
