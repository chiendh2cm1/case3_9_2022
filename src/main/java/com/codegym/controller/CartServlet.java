package com.codegym.controller;

import com.codegym.dao.AccountDao;
import com.codegym.dao.OrderDao;
import com.codegym.dao.OrdertailDao;
import com.codegym.dao.ProductDao;
import com.codegym.model.Account;
import com.codegym.model.Order;
import com.codegym.model.Orderdetail;
import com.codegym.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    ProductDao productDao = new ProductDao();
    OrderDao orderDao = new OrderDao();
    AccountDao accountDao = new AccountDao();
    OrdertailDao ordertailDao = new OrdertailDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "addProductToCart":
                addProductToCatr(request, response);
                break;
            case "deleteProductInCart":
                deleteProductInCart(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "updateCart":
                updateCart(request, response);
                break;
            case "checkInfoCustomer":
                checkInfoCustomer(request, response);
                break;
            case "checkOrderInformation":
                checkOderInformationAndInsertOrderDetail(request, response);
                break;

        }
    }

    private void checkOderInformationAndInsertOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String accountId = request.getParameter("accountId");
        String receiver = request.getParameter("receiver");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        Order orderSession = (Order) session.getAttribute("order");
        List<Orderdetail> list = orderSession.getOrderdetails();

        Order order = new Order(orderId, accountId, receiver, address, email, phoneNumber);
        orderDao.createOrder(order);
        for (int i = 0; i < list.size(); i++) {
            String productId = list.get(i).getProduct().getProductId();
            String quantity = String.valueOf(list.get(i).getQuantity());
            String priceEach = String.valueOf(list.get(i).getPrice());
            orderDao.addOrderProductFromCart(orderId, productId, Integer.parseInt(quantity), Float.parseFloat(priceEach), accountId);
        }
        List<Integer> orderQuantities = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            orderQuantities.add(list.get(i).getQuantity());
        }

        List<Integer> quantityInStock = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            quantityInStock.add(list.get(i).getProduct().getQuantityInStock());
        }

        for (int i = 0; i < quantityInStock.size(); i++) {
            quantityInStock.set(i, (quantityInStock.get(i) - orderQuantities.get(i)));
        }

        for (int i = 0; i < quantityInStock.size(); i++) {
            orderDao.updateQuantityProduct(quantityInStock.get(i), list.get(i).getProduct().getProductId());
        }


        String announcementOrderSuccessful = "Order Completed ! Please wait Admin to Confirm";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
        request.setAttribute("announcementOrderSuccessful", announcementOrderSuccessful);
        requestDispatcher.forward(request, response);
        session.removeAttribute("order");
    }

    private void checkInfoCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String isLogined = (String) session.getAttribute("cookieIsLogin");
        if (isLogined.equals("not yet")) {
            String notification = "Please login first!";
            request.setAttribute("announcementToLogin", notification);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            String email = request.getParameter("email");
            String receiver = request.getParameter("fullName");
            ;
            String address = request.getParameter("address");
            ;
            String phoneNumber = request.getParameter("phoneNumber");
            ;
            if (receiver.equals("") || address.equals("") || phoneNumber.equals("") || email.equals("")) {
                String announcementToFillFields = "Do not leave blank";
                request.setAttribute("announcementToFillFields", announcementToFillFields);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String orderUserName = (String) session.getAttribute("cookieUserName");
                Account loginedAccount = accountDao.findByLoginName(orderUserName);

                Order order = (Order) session.getAttribute("order");
                List<Orderdetail> list = order.getOrderdetails();
                for (int i = 0; i < list.size(); i++) {
                    try {
                        ordertailDao.addOrderDetail(list.get(i));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                String orderDate = String.valueOf(LocalDate.now());
                String[] dateArray = orderDate.split("-");
                int no;
                String orderIdHead = "OD" + dateArray[0] + dateArray[1] + dateArray[2];
                String maxOrderIdHaveOrderIdHead = orderDao.getMaxOrderIdByOrderIdHead(orderIdHead);
                if (maxOrderIdHaveOrderIdHead.equals("")) {
                    no = 1;
                } else {
                    String orderIdTails = maxOrderIdHaveOrderIdHead.substring(10);
                    no = Integer.parseInt(orderIdTails) + 1;
                }

                String orderId = orderIdHead + String.valueOf(no);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("confirmOrder.jsp");
                request.setAttribute("orderId", orderId);
                request.setAttribute("list", list);
                request.setAttribute("accountId", loginedAccount.getAccountId());
                request.setAttribute("accountName", receiver);
                request.setAttribute("orderDate", orderDate);
                request.setAttribute("receiver", receiver);
                request.setAttribute("address", address);
                request.setAttribute("phoneNumber", phoneNumber);
                request.setAttribute("email", email);

                requestDispatcher.forward(request, response);
            }
        }
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] quantity = request.getParameterValues("quantity");
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Orderdetail> list = order.getOrderdetails();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setQuantity(Integer.parseInt(quantity[i]));
        }

        session.removeAttribute("order");
        session.setAttribute("order", order);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addProductToCatr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int quantity = 1;
        String id;
        if (request.getParameter("productId") != null) {
            id = request.getParameter("productId");
            try {
                Product product = productDao.selectProduct(id);
                if (product != null) {
                    if (request.getParameter("quantity") != null) {
                        quantity = Integer.parseInt(request.getParameter("quantity"));
                    }
                    HttpSession session = request.getSession();
                    if (session.getAttribute("order") == null) {
                        Order order = new Order();
                        List<Orderdetail> orderdetailList = new ArrayList<>();
                        Orderdetail orderdetail = new Orderdetail();
                        orderdetail.setQuantity(quantity);
                        orderdetail.setProduct(product);
                        orderdetail.setPrice(product.getProductPrice());
                        orderdetailList.add(orderdetail);
                        order.setOrderdetails(orderdetailList);
                        session.setAttribute("order", order);

                    } else {
                        Order order = (Order) session.getAttribute("order");
                        List<Orderdetail> orderdetailList = order.getOrderdetails();
                        boolean check = false;
                        for (Orderdetail orderdetail : orderdetailList) {
                            if (orderdetail.getProduct().getProductId().equals(product.getProductId())) {
                                orderdetail.setQuantity(orderdetail.getQuantity() + quantity);
                                check = true;
                            }
                        }
                        if (!check) {
                            Orderdetail orderdetail = new Orderdetail();
                            orderdetail.setQuantity(quantity);
                            orderdetail.setProduct(product);
                            orderdetail.setPrice(product.getProductPrice());
                            orderdetailList.add(orderdetail);
                        }
                        session.setAttribute("order", order);
                    }
                    response.sendRedirect(request.getContextPath() + "/pagination");
                } else {
                    response.sendRedirect(request.getContextPath() + "/pagination");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteProductInCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Orderdetail> list = order.getOrderdetails();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProduct().getProductId().equals(productId)) {
                list.remove(i);
            }
        }

        session.removeAttribute("order");
        session.setAttribute("order", order);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
