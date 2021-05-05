package PresentationLayer;

import PresentationLayer.Inventory.INV_IO;
import PresentationLayer.Supplier.IO_Supplier;

import java.util.Scanner;

import static java.lang.System.exit;

public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println('\n' + "----------------------------------------------------------");
        System.out.println('\n' +
                "0000  0  0  0000  0000  0000              0    0000  0000" + '\n' +
                "0     0  0  0  0  0     0  0              0    0     0   "+ '\n' +
                "0000  0  0  0000  0000  000     000000    0    0000  0000"+ '\n' +
                "   0  0  0  0     0     0 0               0    0     0   "+ '\n' +
                "0000  0000  0     0000  0  0              0000 0000  0000" + '\n');
        System.out.println("----------------------------------------------------------" + '\n');

        System.out.println('\n' + "Welcome To Super-Lee System!" + '\n' +
                "Choose Which System Do You Want To Use: "+ '\n');
        System.out.println( "1. Supplier System " + '\n' + "2. Inventory System " + '\n' + "3. Exit From System ");

        try{
            int caseNumber = Integer.parseInt(scanner.nextLine());
            switch (caseNumber) {
                // Supplier System
                case 1:
                    IO_Supplier.getInstance().init();
                    break;
                // Inventory System
                case 2:
                    INV_IO io = INV_IO.getInstance();
                    String init = io.getString("do you want to load initial data (y = yes)");
                    if (init.equals("y")) {
                        io.initData();
                    }
                    io.start();
                    break;

                case 3:
                    System.out.println('\n'+ "You Are Log Out From The System, Have A Nice Day.\n");
                    exit(0);

                default:
                    System.out.println("Please Choose Only 1-3" + '\n');
                    return;
            }
        }catch(Exception e){
            System.out.println("Invalid Input - Please Enter 1-2 " + '\n');
            return;
        }
    }
}
