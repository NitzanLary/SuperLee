package BusinessLayer;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category cat;

    @BeforeEach
    void before(){
        cat = new Category("test");
        cat.addItem(1, "testItem", 20, 10, 1, "tester", 7, 15, 20);
    }
    @org.junit.jupiter.api.Test
    void addItem() {

    }

    @org.junit.jupiter.api.Test
    void removeItem() {
        cat.removeItem(1);
    }

    @org.junit.jupiter.api.Test
    void addSubCategory() {
        Category subCat = new Category("subTest");
        cat.addSubCategory(subCat);
        assertEquals(cat.getCategory("subTest").getName(), subCat.getName());
    }
}