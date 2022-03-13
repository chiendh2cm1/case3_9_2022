package com.codegym.controller;

import com.codegym.dao.ProductDao;
import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SearchProductServlet", urlPatterns = "/search")
public class SearchProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
        String name = request.getParameter("searchName");
        if (name.equals("")) {
            String announcement = "Please input content";
            request.setAttribute("announcement", announcement);
            dispatcher.forward(request, response);
        } else {
            List<Product> productList = null;
                productList = productService.findAllProductByName(name);
            request.setAttribute("listProduct", productList);
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
