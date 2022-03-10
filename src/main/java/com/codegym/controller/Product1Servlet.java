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
            case "create":
                showCreateProductForm(request, response);
                break;
            default:
                showAllProduct(request, response);
                break;
        }
    }

    private void showCreateProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
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
            case "create":
                String productId = request.getParameter("productId");
                String categoryId = request.getParameter("categoryId");
                String productName = request.getParameter("name");
                float productPrice = Float.parseFloat(request.getParameter("price"));
                int quantityInStock = Integer.parseInt(request.getParameter("quantity"));
                String image = request.getParameter("image");
                int status = Integer.parseInt(request.getParameter("status"));
                String description = request.getParameter("description");
                Product product = new Product(productId, categoryId, productName, productPrice, quantityInStock, image, status, description);
                productService.create(product);
                response.sendRedirect("/products");
                break;
            default:

        }
    }
}
