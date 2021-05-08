package Employees.BuisnessLayer;

import Employees.DataAccessLayer.DTOs.RoleDTO;

public class DriverRole extends Role{
    String licence;

    public DriverRole(String _name, String licence_) {
        super(_name);
        licence = licence_;
    }

    public String getLicence() {
        return licence;
    }

    @Override
    public DriverRole clone() {
        return new DriverRole(getName(), licence);
    }

    @Override
    public RoleDTO toDTO() {
        return new RoleDTO(getName(),licence);
    }

    @Override
    public String toString() {
        return "DriverRole{" +
                "licence='" + licence + '\'' +
                '}';
    }
}
