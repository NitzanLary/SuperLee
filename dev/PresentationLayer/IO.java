package PresentationLayer;

import java.util.Scanner;

public class IO {
    private static IO instance;
    private IO_Controller ioCtrl;
    private Scanner scanner;

    public static IO getInstance() {
        if (instance != null) {
            instance = new IO();
        }
        return instance;
    }

    private IO() {
        ioCtrl = new IO_Controller();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("1 to add sale, 2 to report faulty");
        int action = scanner.nextInt();
        ioCtrl.act(action);
    }

    public int getId() {
        System.out.println("Enter item id");
        return scanner.nextInt();
    }
}
