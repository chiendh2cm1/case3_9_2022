package com.codegym.controller;

import com.codegym.dao.OrderDao;
import com.codegym.model.Order;
import com.codegym.service.IOrderService;
import com.codegym.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
                break;
            case "view":
                showOrdertail(request, response);
                break;
            default:
                ShowListOrder(request, response);
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

    private void ShowListOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.findAll();
        request.setAttribute("orders", orders);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showOrdertail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        Order order = orderService.findById(orderId);
        request.setAttribute("order", order);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/view.jsp");
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
                updateOrder(request, response);
                break;
        }
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            List<Order> orders = orderService.findAll();
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
            List<Order> orders = orderService.findAll();
            request.setAttribute("orders", orders);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/order/list.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
