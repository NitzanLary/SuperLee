package BussinessLayer.Inventory;


import java.time.LocalDate;

public class FaultyItem {
    private String name;
    private LocalDate expDate;
    private int amount;

    public FaultyItem(String name, LocalDate expDate, int amount){
        this.name = name;
        this.expDate = expDate;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String toString(){
        String str = "Name:\t\t\t\t" + name +"\n" +
                "Expiration date: \t" + expDate + "\n" +
                "Amount: \t\t\t" + amount+"\n";
        return str;
    }
}
