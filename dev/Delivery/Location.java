package Delivery;

public class Location {
    private String address;
    private String phoneNumber;
    private String contactName;

    public Location(String address, String phoneNumber, String contactName){
        this.address = address;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "{" +
                "address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactName='" + contactName + '\'' +
                '}';
    }
}
