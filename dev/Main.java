import Delivery.DeliveryController;

public class Main {
    public static void main(String[] args) {
        System.out.println("!Hello world");
        DeliveryController dc = new DeliveryController();
        for(int i = 0; i< 260000; i++)
            System.out.println(dc.getNewDeliveryID());
    }
}
