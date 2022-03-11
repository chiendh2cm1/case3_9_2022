package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.CategoryService;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Product1Servlet", value = "/products")
public class Product1Servlet extends HttpServlet {
    private IProductService productService = new ProductService();
    private ICategoryService categoryService = new CategoryService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                showEditProductForm(request, response);
                break;
            case "delete":
                showDeleteProductForm(request, response);
                break;
            case "create":
                showCreateProductForm(request, response);
                break;
            default:
                showAllProduct(request, response);
                break;
        }
    }

    private void showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = null;
        try {
            categories = categoryService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categories", categories);
        String productId = request.getParameter("id");
        Product product = productService.findById(productId);
        request.setAttribute("product", product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/product/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showDeleteProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = null;
        try {
            categories = categoryService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categories", categories);
        String productId = request.getParameter("id");
        Product product = productService.findById(productId);
        request.setAttribute("product", product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/product/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = null;
        try {
            categories = categoryService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = null;
        try {
            products = productService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q = request.getParameter("q");
        if (q != null) {
            products = productService.findAllProductByName(q);
        }
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/product/list.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                editProduct(request, response);
                break;
            case "create":
                createProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String alertEdit = "";
        String productId = request.getParameter("id");
        String categoryId = request.getParameter("categoryId");
        String productName = request.getParameter("name");
        float productPrice = Float.parseFloat(request.getParameter("price"));
        int quantityInStock = Integer.parseInt(request.getParameter("quantity"));
        String image = request.getParameter("image");
        int status = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");
        Product product = new Product(productId, categoryId, productName, productPrice, quantityInStock, image, status, description);
        boolean isUpdated = productService.updateById(productId, product);
        if (isUpdated) {
            alertEdit = "Edit success";
        } else {
            alertEdit = "Edit fail";
        }
        request.setAttribute("alertEdit", alertEdit);
        List<Product> products = null;
        try {
            products = productService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/product/list.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String alertDelete = "";
        String id = request.getParameter("id");
        boolean isDeleted = productService.deleteById(id);
        if (isDeleted) {
            alertDelete = "Delete success";
        } else {
            alertDelete = "Delete Fail";
        }
        request.setAttribute("alertDelete", alertDelete);
        List<Product> products = null;
        try {
            products = productService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/product/list.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String alertCreate = "";
        String productId = request.getParameter("productId");
        String categoryId = request.getParameter("categoryId");
        String productName = request.getParameter("name");
        float productPrice = Float.parseFloat(request.getParameter("price"));
        int quantityInStock = Integer.parseInt(request.getParameter("quantity"));
        String image = request.getParameter("image");
        int status = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");
        Product product = new Product(productId, categoryId, productName, productPrice, quantityInStock, image, status, description);
        boolean isCreated = productService.create(product);
        if (isCreated) {
            alertCreate = "Create success";
        } else {
            alertCreate = "Create fail";
        }
        request.setAttribute("alertCreate", alertCreate);
        List<Product> products = null;
        try {
            products = productService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/product/list.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
