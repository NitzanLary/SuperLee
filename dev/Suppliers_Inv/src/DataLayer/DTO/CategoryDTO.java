package DataLayer.DTO;

import BussinessLayer.Inventory.Category;

public class CategoryDTO {
    private String name;

    public CategoryDTO(Category c) {
        name = c.getName();
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
