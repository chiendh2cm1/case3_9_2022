package com.codegym.dao;

import com.codegym.connection.DBConnect;
import com.codegym.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao1 implements ICategoryDao{
    public static final String SQL_SELECT_ALL_CATEGORY = "SELECT * FROM category";
    private Connection connection = DBConnect.getConnection();

    @Override
    public List<Category> getListCategory() throws SQLException {
        List<Category> categories = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_CATEGORY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String categoryId = resultSet.getString("CategoryID");
            String categoryName = resultSet.getString("CategoryName");
            Category category = new Category(categoryId, categoryName);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public boolean deleteCategory(String id) {
        return false;
    }

    @Override
    public Category getCategory(String categoryId) throws SQLException {
        return null;
    }

    @Override
    public boolean addCategory(Category category) {
        return false;
    }

    @Override
    public boolean updateCategory(String id, Category category) {
        return false;
    }

    @Override
    public boolean deleteCategoryUsingProcedure(String id) {
        return false;
    }

    @Override
    public boolean updateCategory(Category category) {
        return false;
    }
}
