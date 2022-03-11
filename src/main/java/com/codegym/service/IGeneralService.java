package com.codegym.service;

import java.sql.SQLException;
import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll() throws SQLException;

    T findById(String id);

    boolean create(T t);

    boolean updateById(String id, T t);

    boolean deleteById(String id);
}