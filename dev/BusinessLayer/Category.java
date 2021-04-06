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

    public List<Item> getItems() {
        return items;
    }

    public void addItem(int id, String name, LocalDate expDate, double price, double cost, int shelfNum, String manufacturer, int shelfQuantity, int storageQuantity) {
        items.add(new Item(id, name, expDate, price, cost, shelfNum, manufacturer, shelfQuantity, storageQuantity));
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

}
