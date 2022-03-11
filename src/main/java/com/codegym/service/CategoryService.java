package com.codegym.service;

<<<<<<< HEAD
import com.codegym.dao.ICategoryDao;
=======
import com.codegym.dao.CategoryDao;
import com.codegym.dao.CategoryDao1;
>>>>>>> 93e5cc16bf7caafdcd67a75f7f8e17459afdb2a7
import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryService implements ICategoryService{
<<<<<<< HEAD
    private ICategoryDao categoryDao;

    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDao.getListCategory();
    }

    @Override
    public Category findById(String id) {
        try {
            return categoryDao.getCategory(id);
=======
    private CategoryDao1 categoryDao1 = new CategoryDao1();

    @Override
    public List<Category> findAll() {
        try {
            return categoryDao1.getListCategory();
>>>>>>> 93e5cc16bf7caafdcd67a75f7f8e17459afdb2a7
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
<<<<<<< HEAD
    public boolean create(Category category) {

        return categoryDao.addCategory(category);
=======
    public Category findById(String id) {
        return null;
    }

    @Override
    public boolean create(Category category) {
        return false;
>>>>>>> 93e5cc16bf7caafdcd67a75f7f8e17459afdb2a7
    }

    @Override
    public boolean updateById(String id, Category category) {
<<<<<<< HEAD

        return categoryDao.updateCategory(id,category);
=======
        return false;
>>>>>>> 93e5cc16bf7caafdcd67a75f7f8e17459afdb2a7
    }

    @Override
    public boolean deleteById(String id) {
<<<<<<< HEAD

        return categoryDao.deleteCategoryUsingProcedure(id);
    }
}
=======
        return false;
    }
}
>>>>>>> 93e5cc16bf7caafdcd67a75f7f8e17459afdb2a7
