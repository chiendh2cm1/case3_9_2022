package com.codegym.dao;


import com.codegym.model.Order;

import java.util.List;

public interface IOrderDao {
    List<Order> getRecentOrderList();

    String getMaxOrderIdByOrderIdHead(String orderIdHead);

    void createOrder(Order order);

    void addOrderProductFromCart(String orderId, String productId, int quantity, float priceEach, String accountId);

    void updateQuantityProduct(int quantity, String productId);

    List<Order> viewAllOrder();

    boolean deleteOder(String orderID);

    Order findByID(String orderIDToFind);

    boolean updateOrder(String orderId, Order order);
}
