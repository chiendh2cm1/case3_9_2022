package com.codegym.service;

import com.codegym.dao.ProductDao;
import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService implements IProductService{
    private ProductDao productDao = new ProductDao();

    @Override
    public List<Product> findAll() {
        return productDao.displayAllProduct();
    }

    @Override
    public Product findById(String id) {
        try {
            return productDao.selectProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Product product) {
        return productDao.create(product);
    }

    @Override
    public boolean updateById(String id, Product product) {
        return productDao.updateById(id, product);
    }

    @Override
    public boolean deleteById(String id) {
        try {
            return productDao.deleteProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> findAllProductByName (String name) {
        name = "%" + name + "%";
        return productDao.findAllProductByName(name);
    }
}
