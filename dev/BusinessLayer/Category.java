package BusinessLayer;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Category {
    private String name;
    private List<Category> subCategories;
    private List<Item> items;

    public Category(String name) {
        this.name = name;
        subCategories = new LinkedList<>();
        items = new LinkedList<>();
    }

    public boolean addItem(int id, String name, LocalDate expDate, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity) {
        return items.add(new Item(id, name, expDate, price, cost, shelfNum, manufacturer, shelfQuantity, storageQuantity));
    }

    public boolean deleteItem(int id) {
        Item toDelete = getItem(id);
        if (toDelete == null)
            return false;
        return items.remove(toDelete);
    }

    public void addSubCategory(Category sub) {
        subCategories.add(sub);
    }

    public Item getItem(int id) {
        for (Item i:items) {
            if (i.getId() == id)
                return i;
        }
        for (Category c : subCategories) {
            Item i = c.getItem(id);
            if (i != null)
                return i;
        }
        return null;
    }

    public Category getCategory(String name) {
        if (this.name.equals(name))
            return this;
        for (Category c: subCategories) {
            Category curr = c.getCategory(name);
            if (curr != null)
                return curr;
        }
        return null;
    }

    public Category getCategory(int id) {
        if (getItem(id) != null)
            return this;
        for (Category c : subCategories) {
            if (c.getCategory(id) != null)
                return c;
        }
        return null;
    }

}