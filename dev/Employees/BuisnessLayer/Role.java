package Employees.BuisnessLayer;

import java.util.Comparator;

public class Role{
    private String name;

    public Role(String _name) {name = _name;}

    public String getName() {
        return name;
    }

    public boolean compare(String r2){
        return name.equals(r2);
    }
}
