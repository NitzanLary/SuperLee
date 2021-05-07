package Employees.BuisnessLayer;

public class DriverRole extends Role{
    String licence;

    public DriverRole(String _name, String licence_) {
        super(_name);
        licence = licence_;
    }

    public String getLicence() {
        return licence;
    }
}
