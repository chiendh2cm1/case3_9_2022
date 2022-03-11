package com.codegym.controller;

import com.codegym.dao.OrderDao;
import com.codegym.model.Order;
import com.codegym.service.IOrderService;
import com.codegym.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
    private IOrderService orderService;

    public OrderServlet() {
        this.orderService = new OrderService(new OrderDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editGet":
                showUpdateForm(request, response);
                break;
            case "deleteGet":
                showdeleteForm(request, response);
            default:
                try {
                    ShowListOrder(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        Order order = orderService.findById(orderId);
        request.setAttribute("order", order);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showdeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        Order order = orderService.findById(orderId);
        request.setAttribute("order", order);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void ShowListOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Order> orders = orderService.findAll();
        request.setAttribute("orders", orders);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/list.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "deletePost":
                deleteOrder(request, response);
                break;
            case "editPost":
                updateProduct(request, response);
                break;
            case "view":
                showOrdertail(request, response);
                break;


        }
    }

    private void showOrdertail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String thongBao = "Order Updated Successfully";
        String id = request.getParameter("id");
        String acountId = request.getParameter("accountId");
        String receiver = request.getParameter("receiver");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        Order order = new Order(id, acountId, receiver, address, email, phoneNumber);
        boolean isUpdateed = orderService.updateById(id, order);
        if (isUpdateed) {
            request.setAttribute("thongBao", thongBao);
            List<Order> orders = null;
            try {
                orders = orderService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("orders", orders);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/list.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String thongBao2 = "Order Deleted Successfully";
        String id = request.getParameter("id");
        boolean isDeleted = orderService.deleteById(id);
        if (isDeleted) {
            request.setAttribute("thongBao", thongBao2);
            List<Order> orders = null;
            try {
                orders = orderService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("orders", orders);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/list.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
