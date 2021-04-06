package BussinessLayer;

import java.util.HashMap;
import java.util.List;

public class BillOfQuantities {

    private HashMap<Integer, Integer> minQuantityForDis; // <prodID: Integer, minQuantity: Integer>
    private HashMap<Integer, Integer> discountList; // <prodID: Integer, percentDis: Integer>

    public BillOfQuantities(HashMap<Integer, Integer> minQuantityForDis, HashMap<Integer, Integer> discountList){
        this.minQuantityForDis = minQuantityForDis;
        this.discountList = discountList;
    }
}
