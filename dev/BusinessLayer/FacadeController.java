package BusinessLayer;

import BusinessLayer.Inventory.FacadeInv;
import BusinessLayer.Supplier.FacadeSupplier;

import java.util.HashMap;

/**
 * This is the Facade of the system.
 * This class hold two more facade for each package: suppliers and inventory
 */

public class FacadeController {
    private static FacadeController facadeController = null;
    public FacadeInv facadeInv;
    public FacadeSupplier facadeSupplier;
    public BusinessLayer.DeliveryBusinessLayer.FacadeController facadeDelivery;

    private FacadeController() {
        facadeInv = FacadeInv.getInstance();
        facadeSupplier = FacadeSupplier.getInstance();
        facadeDelivery = BusinessLayer.DeliveryBusinessLayer.FacadeController.getInstance();
    }

    public static FacadeController getInstance() {
        if (facadeController == null)
            facadeController = new FacadeController();

        return facadeController;
    }

    // after each order we send info to match delivery.
    public void sendOrderToDelivery(int orderID, int suppID) {
        HashMap<Integer,Integer> prodQuantity = facadeSupplier.getOrderController().orders.get(orderID).getProducts();
        //Supplier details:
        String address = facadeSupplier.getSupplierController().getSuppliers().get(suppID).getAddress();
        String infoSupplyDates = facadeSupplier.getSupplierController().getSuppliers().get(suppID).getInfoSupplyDay();
        // TODO: delivery module need to continue from here.
    }

    public void ordersByLack(HashMap<Integer, Integer> stkReport){
        facadeSupplier.ordersByLack(stkReport);
        //todo supplier module
    }
}
