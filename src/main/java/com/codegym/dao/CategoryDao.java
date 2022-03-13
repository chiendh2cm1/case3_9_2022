package com.codegym.dao;

import com.codegym.connection.DBConnect;
import com.codegym.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao {
    public static final String UPDATE_CATEGORY = "update category set CategoryName = ? where CategoryID = ?;";
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM chiendemo.category";
    public static final String SELECT_CATEGORY_BY_CATEGORY_ID = "select * from chiendemo.category where CategoryID = ?;";
    public static final String CREATE_CATEGORY = "insert into chiendemo.category(CategoryID, CategoryName) value (?,?);";
    public static final String CALL_PROCEDURE_DELETE_CATEGORY = "call delete_category(?)";

    @Override
    public List<Category> getListCategory() throws SQLException {
        Connection connection = DBConnect.getConnection();
        String sql = SELECT_ALL_CATEGORY;
        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Category> list = new ArrayList<>();
        while (rs.next()) {
            Category category = new Category();
            category.setCategoryId(rs.getString("CategoryID"));
            category.setCategoryName(rs.getString("CategoryName"));
            list.add(category);
        }
        return list;
    }

    @Override
    public Category getCategory(String categoryId) throws SQLException {
        Connection connection = DBConnect.getConnection();
        Category category = new Category();
       PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_CATEGORY_ID);
       preparedStatement.setString(1, categoryId);
       ResultSet rs = preparedStatement.executeQuery();
       while (rs.next()){
           String CategoryName = rs.getString("CategoryName");
           category = new Category(categoryId,CategoryName );
       }
       return category;
    }

    @Override
    public boolean addCategory(Category category) {

       Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(CREATE_CATEGORY);
            preparedStatement.setString(1, category.getCategoryId());
            preparedStatement.setString(2, category.getCategoryName());
        return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateCategory(String id,Category category) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryId());
            return preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCategoryUsingProcedure(String id) {
        Connection connection = DBConnect.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall(CALL_PROCEDURE_DELETE_CATEGORY);
            callableStatement.setString(1, id);
            return  callableStatement.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
