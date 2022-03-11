package com.codegym.dao;

import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDao {
    public List<Category> getListCategory() throws SQLException;
    public boolean deleteCategory(String id);
    public Category getCategory(String categoryId) throws SQLException;
    public boolean addCategory(Category category);
<<<<<<< HEAD
    public boolean updateCategory (String id, Category category);
    boolean deleteCategoryUsingProcedure(String id);

=======
    public boolean updateCategory (Category category);
>>>>>>> 93e5cc16bf7caafdcd67a75f7f8e17459afdb2a7
}
