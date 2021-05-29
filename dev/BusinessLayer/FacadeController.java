package BusinessLayer;

import BusinessLayer.Inventory.FacadeInv;
import BusinessLayer.Supplier.FacadeSupplier;

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

}
