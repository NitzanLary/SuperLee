package BusinessLayer;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class StockController {
    private List<Category> categories;

    public StockController() {
        categories = new LinkedList<>();
    }

    private Category getCategory(String name) {
        for (Category c: categories){
            Category curr = c.getCategory(name);
            if (curr != null && curr.getCategory(name) != null)
                return curr;
        }
        return null;
    }

    public void addSubCatagory(String name, String superName) {
        Category superCategory = getCategory(superName);
        if (superCategory == null)
            categories.add(new Category(name));
        else
            superCategory.addSubCategory(new Category(name));
    }

    public void addCategory(String name) {
        categories.add(new Category(name));
    }

    public Item getItem(int id) {
        for (Category c : categories) {
            Item i = c.getItem(id);
            if (i != null)
                return i;
        }
        return null;
    }

    public void addItem(int id, String name, LocalDate expDate, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity, String catName) {
        Category c = getCategory(catName);
        if (c != null) {
            c.addItem(id, name, expDate, price, cost, shelfNum, manufacturer, shelfQuantity, storageQuantity);
        }
    }
}
