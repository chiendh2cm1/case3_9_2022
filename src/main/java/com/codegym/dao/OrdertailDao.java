package com.codegym.dao;

import com.codegym.connection.DBConnect;
import com.codegym.model.Orderdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdertailDao {
    Connection conn = null;
    PreparedStatement ps = null;
    public void addOrderDetail(Orderdetail orderdetail) throws SQLException {
        conn = DBConnect.getConnection();
        ps = conn.prepareStatement("insert into order_product (OrderID, ProductID, Quantity, PriceEach) values (?,?,?,?);");
        ps.setString(1, orderdetail.getId());
        ps.setString(2, orderdetail.getProduct().getProductId());
        ps.setInt(3, orderdetail.getQuantity());
        ps.setFloat(4, orderdetail.getPrice());
        ps.executeUpdate();
    }

}
