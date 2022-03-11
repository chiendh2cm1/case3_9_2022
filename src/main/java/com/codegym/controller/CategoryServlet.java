package com.codegym.controller;

import com.codegym.dao.CategoryDao;
import com.codegym.dao.ProductDao;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.CategoryService;
import com.codegym.service.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    private ICategoryService categoryService;
    ProductDao productDao = new ProductDao();

    public CategoryServlet() {
        this.categoryService = new CategoryService(new CategoryDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "deleteGet":
                showDeleteForm(request, response);
                break;
            case "create":
                ShowCreateForm(request, response);
                break;
            case "view":
                String id = request.getParameter("id");
                try {
                    List<Product> products = productDao.getListProductByCategoryId(id);
                    request.setAttribute("products", products);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin/category/view.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "editGet":
                ShowEditForm(request, response);
                break;
            default:
                ShowListOrden(request, response);
                break;
        }
    }

    private void ShowEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category category = categoryService.findById(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/category/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category category = categoryService.findById(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/category/delete.jsp");
        dispatcher.forward(request, response);
    }


    private void ShowCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categories = categoryService.findAll();
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/category/create.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ShowListOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categories = categoryService.findAll();
            request.setAttribute("categories", categories);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/category/list.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createPost":
                CreateCategory(request, response);
                break;
            case "editPost":
                String categoryId = request.getParameter("id");
                String categoryName = request.getParameter("categoryName");
                Category category = new Category(categoryId, categoryName);
                boolean isUpdated = categoryService.updateById(categoryId,category);
                String thongBao = "";
                if (isUpdated){
                    thongBao="Update Successfully!";
                    request.setAttribute("thongBao",thongBao);
                    List<Category> categories = null;
                    try {
                        categories = categoryService.findAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("categories", categories);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/category/list.jsp");
                    requestDispatcher.forward(request,response);
                }

                break;
            case "deletePost":
                DeleteCategory(request, response);
                break;

        }
    }

    private void DeleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
       boolean isDeleted = categoryService.deleteById(id);
       String thongBao = "";
       if (isDeleted){
           List<Category> categories = null;
           try {
               categories = categoryService.findAll();
           } catch (SQLException e) {
               e.printStackTrace();
           }
           request.setAttribute("thongBao2", thongBao);
           request.setAttribute("categories", categories);
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/category/list.jsp");
           try {
               requestDispatcher.forward(request,response);
           } catch (ServletException e) {
               e.printStackTrace();
           }
       }
    }

    private void CreateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CategoryID = request.getParameter("categoryId");
        String CategoryName = request.getParameter("categoryName");
        Category category = new Category(CategoryID, CategoryName);
        categoryService.create(category);
        response.sendRedirect("/category");
    }
}
