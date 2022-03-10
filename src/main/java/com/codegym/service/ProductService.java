package com.codegym.service;

import com.codegym.dao.ProductDao;
import com.codegym.model.Product;

import java.util.List;

public class ProductService implements IProductService{
    private ProductDao productDao = new ProductDao();

    @Override
    public List<Product> findAll() {
        return productDao.displayAllProduct();
    }

    @Override
    public Product findById(String id) {
        return null;
    }

    @Override
    public boolean create(Product product) {
        return productDao.create(product);
    }

    @Override
    public boolean updateById(String id, Product product) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
