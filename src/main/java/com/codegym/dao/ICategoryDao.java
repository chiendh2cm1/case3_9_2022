package com.codegym.dao;

import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDao {
    public List<Category> getListCategory() throws SQLException;
    public boolean deleteCategory(String id);
    public Category getCategory(String categoryId) throws SQLException;
    public boolean addCategory(Category category);
    public boolean updateCategory (Category category);
}
