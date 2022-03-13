package com.codegym.dao;

import com.codegym.connection.DBConnect;
import com.codegym.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    private static final String SELECT_ALL_PRODUCT = "select * from chiendemo.product";
    private static final String SELECT_PRODUCT_BY_PRODUCTID = "select * from chiendemo.product where ProductID = ?";
    public static final String SELECT_PRODUCT_BY_CATEGORYID = "select * from chiendemo.product where CategoryID = ?";
    public static final String SELECT_FRODUCT_BY_NAME = "select productId, categoryName, ProductName, ProductPrice, QuantityInStock,Image,Status,Description from product join category on product.CategoryID = category.categoryId where ProductName like ?";
    public static final String SQL_SELECT_ALL_PRODUCT = "select productId, categoryName, ProductName, ProductPrice, QuantityInStock,Image,Status,Description \n" +
            "from product join category on product.CategoryID = category.categoryId order by ProductPrice;";
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

    public List<Product> selectAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_PRODUCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return products;
    }

    public List<Product> displayAllProduct() {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ALL_PRODUCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("productId");
                String categoryName = rs.getString("categoryName");
                String name = rs.getString("ProductName");
                float price = rs.getFloat("ProductPrice");
                int quantity = rs.getInt("QuantityInStock");
                String image = rs.getString("Image");
                int status = rs.getInt("Status");
                String description = rs.getString("Description");
                Product product = new Product(id, name, price, quantity, image, status, description, categoryName);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getListProductByCategoryId(String categoryId) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_CATEGORYID);
            ps.setString(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productId = rs.getString(1);
                String cateId = rs.getString(2);
                String productName = rs.getString(3);
                float productPrice = rs.getFloat(4);
                int quantityInStock = rs.getInt(5);
                String image = rs.getString(6);
                int status = rs.getInt(7);
                String description = rs.getString(8);
                products.add(new Product(productId, cateId, productName, productPrice, quantityInStock, image, status, description));
            }
        } catch (Exception e) {
        }

        return products;
    }

    public Product selectProduct(String productId) throws SQLException {
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_PRODUCTID);
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public boolean create(Product product){
        conn = DBConnect.getConnection();
        try {
            ps = conn.prepareStatement("INSERT INTO product (ProductID, CategoryID, ProductName, ProductPrice, QuantityInStock, Image, Status, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getProductId());
            ps.setString(2, product.getCategoryId());
            ps.setString(3, product.getProductName());
            ps.setFloat(4, product.getProductPrice());
            ps.setInt(5, product.getQuantityInStock());
            ps.setString(6, product.getImage());
            ps.setInt(7, product.getStatus());
            ps.setString(8, product.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(String productId, Product product) {
        conn = DBConnect.getConnection();
        try {
            ps = conn.prepareStatement("UPDATE product SET ProductID = ?, CategoryID = ?, ProductName = ?, ProductPrice = ?, QuantityInStock = ?, Image = ?, Status = ?, Description = ? WHERE productId = ?");
            ps.setString(1, product.getProductId());
            ps.setString(2, product.getCategoryId());
            ps.setString(3, product.getProductName());
            ps.setFloat(4, product.getProductPrice());
            ps.setInt(5, product.getQuantityInStock());
            ps.setString(6, product.getImage());
            ps.setInt(7, product.getStatus());
            ps.setString(8, product.getDescription());
            ps.setString(9, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteProduct(String productId) throws SQLException {
        ps = conn.prepareStatement("DELETE FROM  product Where ProductID = ?");
        ps.setString(1, productId);
        return ps.executeUpdate() > 0;
    }

    public List<Product> findAllProductByName(String name) {
        List<Product> products = new ArrayList<>();

        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SELECT_FRODUCT_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("productId");
                String categoryName = rs.getString("categoryName");
                String productName = rs.getString("ProductName");
                float price = rs.getFloat("ProductPrice");
                int quantity = rs.getInt("QuantityInStock");
                String image = rs.getString("Image");
                int status = rs.getInt("Status");
                String description = rs.getString("Description");
                Product product = new Product(id, productName, price, quantity, image, status, description, categoryName);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
