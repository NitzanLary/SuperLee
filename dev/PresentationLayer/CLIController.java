package PresentationLayer;


import BusinessLayer.EmployeesBuisnessLayer.FacadeController;
import PresentationLayer.DeliveryPresentationLayer.DeliveryCLI;
import PresentationLayer.EmployeesPresentationLayer.EmployeeCLI;
import PresentationLayer.Inventory.INV_IO;
import PresentationLayer.Supplier.IO_Supplier;
import serviceObjects.ResponseT;

public class CLIController {
    FacadeController facade;
    EmployeeCLI employeeCLI;
    DeliveryCLI deliveryCLI;
    IO_Supplier supplierCLI;
    INV_IO inventoryCLI;

    public CLIController() {
        facade = FacadeController.getInstance();
        employeeCLI = EmployeeCLI.getInstance();
        deliveryCLI = DeliveryCLI.getInstance();
        supplierCLI = IO_Supplier.getInstance();
        inventoryCLI = INV_IO.getInstance();
    }

    public void handleManagement(int action, String userID){
        switch (action){
            case 1:
                supplierCLI.init();
            case 2:
                employeeCLI.start(userID);
            case 3:
                inventoryCLI.start();
            case 4:
                deliveryCLI.runWithConsole();
            default:
                System.out.println("Incorrect typing");
        }
    }

    public boolean hasRole(String id, String role){
        ResponseT<Boolean> r = facade.hasRole(id, role);
        if(r.isErrorOccured()) {
            System.out.println(r.getErrorMessage());
            return false;
        }
        return r.getValue();
    }

    public ResponseT<Boolean> isEmployee(String id) {
        return facade.isEmployee(id);
    }
}
