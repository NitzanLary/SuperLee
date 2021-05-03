package Employees.DataAccessLayer.DTOs;

public class RoleDTO {
    private String name;
    private String license;

    public RoleDTO(String name, String license){
        this.name = name;
        this.license= license;
    }

    public String getName(){
        return name;
    }

    public String getLicense() {
        return license;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "name='" + name + '\'' +
                ", license='" + license + '\'' +
                '}';
    }
}
