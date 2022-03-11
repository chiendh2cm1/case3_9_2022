package com.codegym.service;

import com.codegym.dao.ICategoryDao;
import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryService implements ICategoryService{
    private ICategoryDao categoryDao;

    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public CategoryService() {

    }


    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDao.getListCategory();
    }

    @Override
    public Category findById(String id) {
        try {
            return categoryDao.getCategory(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Category category) {

        return categoryDao.addCategory(category);
    }

    @Override
    public boolean updateById(String id, Category category) {

        return categoryDao.updateCategory(id,category);
    }

    @Override
    public boolean deleteById(String id) {

        return categoryDao.deleteCategoryUsingProcedure(id);
    }
}