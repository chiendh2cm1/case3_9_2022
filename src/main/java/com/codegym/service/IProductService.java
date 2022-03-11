package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    public List<Product> findAllProductByName (String name);
}
