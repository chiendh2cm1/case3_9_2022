package com.codegym.dao;

import com.codegym.connection.DBConnect;
import com.codegym.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailDao {
    public static final String SELECT_PRODUCT_DETAIL = "select  o.OrderID,p.ProductName, order_product.Quantity ,order_product.PriceEach\n" +
            "from order_product join product p on order_product.ProductID = p.ProductID\n" +
            "join orders o on order_product.OrderID = o.OrderID\n" +
            "where o.OrderID = ?;";
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getListProductDetailByCategoryId(String orderId) throws SQLException {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();
        ps = conn.prepareStatement(SELECT_PRODUCT_DETAIL);
        ps.setString(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            String productName = rs.getString("ProductName");
            float productPrice = rs.getFloat("PriceEach");
            int productQuantity = rs.getInt("Quantity");
            Product product = new Product(productName, productPrice, productQuantity);
            products.add(product);
        }
        return products;
    }

}
