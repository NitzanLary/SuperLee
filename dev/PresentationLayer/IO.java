package PresentationLayer;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IO {

    private static IO instance;
    private IO_Controller ioCtrl;
    private Scanner scanner;

    //singletone class
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

    //adds a default data to the system for testing
    public void initData() {
        ioCtrl.initData();
    }

    //starts the main menu of the program
    public void start() {
        int action;
        do {
            System.out.println("1) Add sale\n" +
                    "2) Report Faulty\n" +
                    "3) Add Item\n" +
                    "4) Add Category\n" +
                    "5) Faulty Report\n" +
                    "6) Edit\n" +
                    "7) Inventory report\n" +
                    "8) Categories report\n" +
                    "\n9) Exit");
            action = scanner.nextInt();
            if (action > 8 || action < 1)
                break;
            scanner.nextLine();
            ioCtrl.mainMenu(action);
        } while (true);

    }

    //starts the edit menu of the program
    public void editMenu() {
        int action;
        do {
            System.out.println("1) Add Item discount\n" +
                    "2) Add Category discount\n" +
                    "3) Add to storage\n" +
                    "4) Add manufacturer discount\n"+
                    "5) Change item shelf\n" +
                    "6) Move item to shelf from storage\n" +
                    "7) Remove Item\n" +
                    "\n9) Back");
            action = scanner.nextInt();
            if (action > 7 || action < 1)
                break;
            scanner.nextLine();
            ioCtrl.editMenu(action);
        } while (true);
    }

    //display `msg` to the user and returns an int read from the user
    public int getInt(String msg) {
        System.out.println(msg);
        int out = scanner.nextInt();
        scanner.nextLine();
        return out;
    }

    //display `msg` to the user and returns a double read from the user
    public double getDouble(String msg) {
        System.out.println(msg);
        return scanner.nextDouble();
    }

    //display `msg` to the user and returns a string read from the user
    public String getString(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    //display `msg` to the user and returns LocalDate read from the user
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
    //display `msg` to the user
    public void print(String msg) {
        System.out.println(msg);
    }

    public void badInput(String msg) {
        print(msg);
        scanner.nextLine();
    }

    public List<String> getList(String msg) {
        System.out.println(msg);
        List<String> cats = new LinkedList<>();
        String ans = "";
        int i = 1;
        while (!ans.equals("-1")) {
            System.out.println("'-1' to exit");
            ans = getString("Enter category name #" + i++);
            if (!ans.equals("-1")) {
                cats.add(ans);
            }
        }
        return cats;
    }
}
