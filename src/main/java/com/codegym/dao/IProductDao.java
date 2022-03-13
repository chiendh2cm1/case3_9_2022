package com.codegym.dao;

import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {

    List<Product> selectAllProduct();

    List<Product> getListProductByCategoryId(String categoryId) throws SQLException;

    Product selectProduct(String productId) throws SQLException;

    boolean create(Product product) throws SQLException;

    boolean updateById(String productId, Product product) throws SQLException;

    boolean deleteProduct(String productId) throws SQLException;

}
