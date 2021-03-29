package Delivery;
public class Driver {
    private int licenseType;
    private Employee employee;

    public Driver(int licenseType){
        this.licenseType = licenseType;
    }

    public void setEmployee(Employee e){
        employee = e;
    }

    public String getName(){
        return employee.getName();
    }
}

