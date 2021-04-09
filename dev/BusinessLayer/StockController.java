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
            throw new RuntimeException("Cannot find Category: "+superName);
        if( getCategory(name) != null)
            throw new RuntimeException("Category "+name+" already exists");
        superCategory.addSubCategory(new Category(name));
    }

    public void addCategory(String name) {
        if (categories.contains(name))
            throw new RuntimeException("Category "+name+" already exists");
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

    public void addItem(int id, String name, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity, int minAlert, String catName) {
        Category c = getCategory(catName);
        if (c == null)
            throw new RuntimeException("Cannot find Category: "+catName);
        if (getItem(id) != null)
            throw new RuntimeException("Item id "+id+"+ already exists");
        c.addItem(id, name, price, cost, shelfNum, manufacturer, shelfQuantity, storageQuantity, minAlert);
    }

    public void removeItem(int id) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        c.removeItem(id);
    }

    public void removeFromShelf(int id, int amount) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        if(!c.removeFromShelf(id, amount))
            throw new RuntimeException("Amount is too large");
    }

    public void removeFromStorage(int id, int amount) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        c.removeFromStorage(id, amount);
    }

    public Category getCategory(int id) {
        for (Category c : categories) {
            Category output = c.getCategory(id);
            if (output != null)
                return output;
        }
        return null;
    }

    public void addToStorage(int id, int amount) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        c.addToStorage(id, amount);
    }

    public void moveToShelf(int id, int amount) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        if (!c.moveToShelf(id, amount))
            throw new RuntimeException("Amount is too large");
    }

    public void changeShelf(int id, int shelf) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        c.changeShelf(id, shelf);
    }


    public void addItemDiscount(LocalDate start, LocalDate end, int discountPr, int id) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        c.addItemDiscount(start, end, discountPr, id);
    }

    public void addCategoryDiscount(LocalDate start, LocalDate end, int discountPr, String catName) {
        Category c = getCategory(catName);
        if (c == null)
            throw new RuntimeException("Cannot find Category: "+catName);
        c.addCategoryDiscount(start, end, discountPr);
    }

    public void addManuDiscount(LocalDate start, LocalDate end, int discountPr, int id) {
        Category c = getCategory(id);
        if (c == null)
            throw new RuntimeException("Cannot find Item id: "+id);
        c.addManuDiscount(start, end, discountPr, id);
    }

    public String stkReport() {
        StringBuilder sb = new StringBuilder("\n");
        for (Category c : categories) {
            sb.append(c.toString());
        }
        return sb.toString();
    }

    public String catReport(List<String> catNames) {
        StringBuilder sb = new StringBuilder("\nReport of categories: "+catNames.toString()+"\n");
        for (String cat : catNames) {
            Category c = getCategory(cat);
            if (c == null)
                sb.append("Could not found category: "+cat+"\n");
            else
                sb.append(c.toString()+"\n");
        }
        return sb.toString();
    }
}
