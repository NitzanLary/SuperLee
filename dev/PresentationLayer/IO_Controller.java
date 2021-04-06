package PresentationLayer;

import BusinessLayer.InvController;

public class IO_Controller {
    private InvController invCtrl;
    private IO io;

    public IO_Controller() {
        invCtrl = new InvController();
        io = IO.getInstance();
    }

    public void act(int action) {
        switch (action) {
            case 1:
                invCtrl.sale(io.getId());
            case 2:
                invCtrl.faulty(io.getId());
        }
    }

    public void sale(int id) {
        invCtrl.sale(id);
    }

    public void Faulty(int id) {
        invCtrl.faulty(id);
    }
}
