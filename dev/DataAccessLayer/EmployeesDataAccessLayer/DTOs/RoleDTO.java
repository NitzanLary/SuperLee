package DataAccessLayer.EmployeesDataAccessLayer.DTOs;

public class RoleDTO {
    private String name;
    private String license = null;

    public RoleDTO(String name, String license){
        this.name = name;
        this.license= license;
    }

    public RoleDTO(String name){
        this.name = name;
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
