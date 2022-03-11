package com.codegym.service;

import com.codegym.dao.CategoryDao;
import com.codegym.dao.CategoryDao1;
import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryService implements ICategoryService{
    private CategoryDao1 categoryDao1 = new CategoryDao1();

    @Override
    public List<Category> findAll() {
        try {
            return categoryDao1.getListCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category findById(String id) {
        return null;
    }

    @Override
    public boolean create(Category category) {
        return false;
    }

    @Override
    public boolean updateById(String id, Category category) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
