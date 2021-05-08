package Employees.BuisnessLayer;

import Employees.DataAccessLayer.DTOs.RoleDTO;

import java.util.Comparator;

public class Role{
    private String name;

    public Role(String _name) {name = _name;}

    public String getName() {
        return name;
    }

    public Role clone() {
        return new Role(name);
    }

    //RoleDto to Role

    public Role(RoleDTO other){
        name = other.getName();
    }

    public boolean compare(String r2){
        return name.equals(r2);
    }

    public RoleDTO toDTO(){
        return new RoleDTO(name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
