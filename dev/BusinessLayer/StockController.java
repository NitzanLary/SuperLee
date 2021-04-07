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
        for (Category c : categories) {
            Category curr = c.getCategory(name);
            if (curr != null && curr.getCategory(name) != null)
                return curr;
        }
        return null;
    }

    public void addSubCategory(String name, String superName) {
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

    public boolean addItem(int id, String name, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity, String catName) {
        Category c = getCategory(catName);
        if (c == null)
            return false;
        if (getItem(id) != null)
            return false;
        return c.addItem(id, name, price, cost, shelfNum, manufacturer, shelfQuantity, storageQuantity);
    }

    public Category getCategory(int id) {
        for (Category c : categories) {
            Category output = c.getCategory(id);
            if (output != null)
                return output;
        }
        return null;
    }

    public boolean deleteItem(int id) {
        if (getItem(id) == null)
            return false;
        Category c = getCategory(id);
        c.deleteItem(id);
        return true;
    }

    public void addItemDiscount(LocalDate start, LocalDate end, int discountPr, int id) {
        Category c = getCategory(id);
        if (c != null)
            c.addItemDiscount(start, end, discountPr, id);
    }

    public void addCategoryDiscount(LocalDate start, LocalDate end, int discountPr, String catName) {
        Category c = getCategory(catName);
        if (c != null)
            c.addCategoryDiscount(start, end, discountPr);
    }
}
