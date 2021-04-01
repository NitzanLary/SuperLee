package Delivery.PresentationLayer;

import Delivery.BusinessLayer.FacadeController;

import java.util.Scanner;

public class CLI {
    FacadeController fc;

    public CLI(){
        fc = new FacadeController();
    }


    public void runWithConsole(){
        Scanner in = new Scanner(System.in);
        System.out.println("welcome to Delivery Module!");
        System.out.println("for exit the simulation type 'exit' as input at any point");
        String s = "";
        while (!s.equals("exit")){
            System.out.println("system current state:");
            System.out.println(toString());
            System.out.println("chose action:\n1 add new delivery,\n2 update existing delivery\n3 create new appending task" +
                    "\n4 add Truck to the sys\n5 add Driver to the sys\nadd Area to the sys\n7 add location to the sys");
            s = in.nextLine().strip();
            chooseAction(s);
        }
    }

    private void chooseAction(String s) {
        switch (s){
            case ("1"){

            }
            case ("2"){

            }
            case ("3"){

            }
            case ("4"){

            }
            case ("5"){

            }
            case ("6"){

            }
            case ("7"){

            }
        }
    }
}
