package com.codegym.service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();

    T findById(String id);

    boolean create(T t);

    boolean updateById(String id, T t);

    boolean deleteById(String id);
}