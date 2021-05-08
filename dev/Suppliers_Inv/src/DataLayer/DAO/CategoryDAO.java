package DataLayer.DAO;

import BussinessLayer.Inventory.Category;
import BussinessLayer.Response;
import BussinessLayer.ResponseT;
import DataLayer.DTO.CategoryDTO;
import DataLayer.DTO.CategoryItemsDTO;
import DataLayer.DTO.subCategoriesDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO extends DAO{

    public CategoryDAO() {
    }

    public Response create(Category c) {
        CategoryDTO toInsert = new CategoryDTO(c);
        String SQL = "INSERT INTO Category (name) VALUE (?)";
        try {
            ResponseT<Connection> r = getConn();
            if (!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toInsert.getName());
                if (!ps.execute()) {
                    return new Response("Could not add category to DB");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response("Could not add category to DB");
        }
        return new ResponseT<>(toInsert);
    }

    public Response createSubCategory(Category c, String fatherName) {
        subCategoriesDTO toInsert = new subCategoriesDTO(fatherName, c.getName());
        String SQL = "INSERT INTO subCategories (fatherCategory, childCategory) VALUE (?, ?)";
        try {
            ResponseT<Connection> r = getConn();
            if (!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toInsert.getFatherCategory());
                ps.setString(2, toInsert.getChildCategory());
                if (!ps.execute()) {
                    return new Response("Could not add sub category to DB");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response("Could not add sub category to DB");
        }
        return new ResponseT<>(toInsert);
    }

    public Response createCategoryItems(Category c, int itemID) {
        CategoryItemsDTO toInsert = new CategoryItemsDTO(c.getName(), itemID);
        String SQL = "INSERT INTO CategoryItems (catName, itemID) VALUE (?, ?)";
        try {
            ResponseT<Connection> r = getConn();
            if (!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toInsert.getCatName());
                ps.setInt(2, toInsert.getItemID());
                if (!ps.execute()) {
                    return new Response("Could not add category-item to DB");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response("Could not add category-item to DB");
        }
        return new ResponseT<>(toInsert);
    }

    public Response delete(Category c) {
        CategoryDTO toDelete = new CategoryDTO(c);
        String SQL = "DELETE FROM Category WHERE catName = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toDelete.getName());

                if(!ps.execute()) {
                    return new Response("cannot delete category from db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot delete category from db");
        }
        return new Response();
    }

    public Response deleteSubCategory(Category c, String fatherName) {
        subCategoriesDTO toDelete = new subCategoriesDTO(c.getName(), fatherName);
        String SQL = "DELETE FROM subCategories WHERE childCategory = ? AND fatherCategory = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toDelete.getChildCategory());
                ps.setString(2, toDelete.getFatherCategory());

                if(!ps.execute()) {
                    return new Response("cannot delete sub-category from db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot delete sub-category from db");
        }
        return new Response();
    }

    public Response deleteCategoryItems(Category c, int itemID) {
        CategoryItemsDTO toDelete = new CategoryItemsDTO(c.getName(), itemID);
        String SQL = "DELETE FROM CategoryItems WHERE catName = ? AND itemID = ?";
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, toDelete.getCatName());
                ps.setInt(2, toDelete.getItemID());

                if(!ps.execute()) {
                    return new Response("cannot delete category-item from db");
                }
            }
        }catch (Exception e) {
            return new Response("cannot delete category-item from db");
        }
        return new Response();
    }

    public Response read(String catName) {
        String SQL = "SELECT * FROM Category WHERE catName = ?";
        CategoryDTO toGet = null;
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, catName);
                ResultSet rs = ps.executeQuery();
                if(!rs.isClosed()) {
                    toGet =  new CategoryDTO(rs.getString("catName"));
                }
                if (toGet == null) {
                    return new Response("cannot read category");
                }
            }
        }catch (Exception e) {
            return new Response("cannot read category");
        }
        return new ResponseT<>(toGet);
    }

    public Response readSubCategory(Category c, String fatherName) {
        String SQL = "SELECT * FROM subCategories WHERE childCategory = ? AND fatherCategory = ?";
        subCategoriesDTO toGet = null;
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, c.getName());
                ps.setString(2, fatherName);
                ResultSet rs = ps.executeQuery();
                if(!rs.isClosed()) {
                    toGet =  new subCategoriesDTO(rs.getString("fatherCategory"), rs.getString("childCategory"));
                }
                else {
                    return new Response("cannot read sub-category");
                }
            }
        }catch (Exception e) {
            return new Response("cannot read sub-category");
        }
        return new ResponseT<>(toGet);
    }

    public Response readCategoryItems(Category c, int itemID) {
        String SQL = "SELECT * FROM CategoryItems WHERE catName = ? AND itemID = ?";
        subCategoriesDTO toGet = null;
        try {
            ResponseT<Connection> r = getConn();
            if(!r.ErrorOccured()) {
                PreparedStatement ps = r.value.prepareStatement(SQL);
                ps.setString(1, c.getName());
                ps.setInt(2, itemID);
                ResultSet rs = ps.executeQuery();
                if(!rs.isClosed()) {
                    toGet =  new subCategoriesDTO(rs.getString("fatherCategory"), rs.getString("childCategory"));
                }
                else {
                    return new Response("cannot read category-item");
                }
            }
        }catch (Exception e) {
            return new Response("cannot read category-item");
        }
        return new ResponseT<>(toGet);
    }
}
