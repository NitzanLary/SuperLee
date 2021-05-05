package DataLayer.DTO;

import BussinessLayer.Inventory.Category;

public class CategoryDTO {
    private String name;

    public CategoryDTO(Category c) {
        name = c.getName();
    }
}
