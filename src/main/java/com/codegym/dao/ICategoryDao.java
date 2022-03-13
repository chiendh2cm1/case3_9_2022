package com.codegym.dao;

import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDao {
    List<Category> getListCategory() throws SQLException;

    Category getCategory(String categoryId) throws SQLException;

    boolean addCategory(Category category);

    boolean updateCategory(String id, Category category);

    boolean deleteCategoryUsingProcedure(String id);

}
