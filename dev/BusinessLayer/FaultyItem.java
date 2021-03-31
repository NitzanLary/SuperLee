package BusinessLayer;

import java.time.LocalDate;

public class FaultyItem {
    private String name;
    private LocalDate expDate;

    public FaultyItem(String name, LocalDate expDate){
        this.name = name;
        this.expDate = expDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String toString(){
        String str = "Name:\t\t\t\t" + name +"\n" +
                "Expiration date: \t" + expDate;
        return str;
    }
}
