package com.codegym.service;

import com.codegym.dao.IOrderDao;
import com.codegym.dao.OrderDao;
import com.codegym.model.Order;

import java.util.List;

public class OrderService implements IOrderService{
private IOrderDao orderDao;

    public OrderService(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAll() {
        return orderDao.viewAllOrder();
    }

    @Override
    public Order findById(String id) {
        return orderDao.findByID(id);
    }

    @Override
    public boolean create(Order order) {
        return false;
    }

    @Override
    public boolean updateById(String id, Order order) {
        return orderDao.updateOrder(id, order);
    }

    @Override
    public boolean deleteById(String id) {
        return orderDao.deleteOder(id);
    }
}
