
import PresentationLayer.IO;

public class Main {
    public static void main(String[] args) {
        IO io = IO.getInstance();
        String init = io.getString("do you want to load initial data (y = yes)");
        if (init.equals("y")) {
            io.initData();
        }
        io.start();
    }
}
