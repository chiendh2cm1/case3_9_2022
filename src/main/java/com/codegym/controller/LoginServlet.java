package com.codegym.controller;

import com.codegym.dao.AccountDao;
import com.codegym.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    AccountDao accountDao = new AccountDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "login":
                Login(request, response);
                break;
            case "signup":
                signUp(request, response);
                break;
            case "edit":
                editAcount(request, response);
                break;
            case "delete":
                deleteAccount(request, response);
                break;
            case "create":
                createAccount(request,response);
                break;
        }
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountId = request.getParameter("accountId");
        String accountName = request.getParameter("accountName");
        String loginName = request.getParameter("loginName");
        String accountAccess = request.getParameter("access");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        boolean status = true;


        Account account = new Account(accountId,accountName,loginName,accountAccess,password,address,phoneNumber,gender,status);
        accountDao.addNewAccount(account);
        response.sendRedirect("/login");
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountId = request.getParameter("accountId");
        accountDao.deleteAccountById(accountId);
        response.sendRedirect("/login");
    }

    private void editAcount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountId = request.getParameter("accountId");
        String accountName = request.getParameter("accountName");
        String loginName = request.getParameter("loginName");
        String accountAccess = request.getParameter("access");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Account account = new Account(accountId,accountName,loginName,accountAccess,password,address,phoneNumber,gender,status);
        accountDao.updateAccountById(account);

        response.sendRedirect("/login");
    }

    private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String admin = "admin";
        String user = "user";
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountDao.findByLoginName(userName);

        if (account == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            String thongBao = "Not found username. Please SignUp!";
            request.setAttribute("thongBao", thongBao);
            requestDispatcher.forward(request, response);
        } else {
            if (account.getAccountAccess().equals("1")) {
                if (!password.equals(account.getPassword())) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                    String thongBao = "Wrong 'password'. Please try again!";
                    request.setAttribute("thongBao", thongBao);
                    requestDispatcher.forward(request, response);
                } else {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/mangament.jsp");
                    requestDispatcher.forward(request, response);
                    session.setAttribute("role", admin);
                }
            } else if (account.getPassword().equals(password)) {
                String logined = "ok";
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("cookieUserName",userName);
                httpSession.removeAttribute("cookieIsLogin");
                httpSession.setAttribute("cookieIsLogin",logined);
                httpSession.setAttribute("role",user);
                response.sendRedirect("/pagination");
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                String thongBao = "Wrong 'password'. Please try again!";
                request.setAttribute("thongBao", thongBao);
                requestDispatcher.forward(request, response);
            }
        }
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String reTypePassword = request.getParameter("RetypePassword");
        String fullname = request.getParameter("name");
        boolean gender;
        String genderValue = request.getParameter("gender");
        if (genderValue.equals("male")) {
            gender = true;
        } else {
            gender = false;
        }
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String maxAccountId = accountDao.getMaxAccountID();
        String id = "KH".concat(String.valueOf(Integer.parseInt(maxAccountId.substring(2)) + 1));
        System.out.println(maxAccountId.charAt(2));
        System.out.println(maxAccountId.charAt(2) + 1);
        Account accountFindOut = accountDao.findByLoginName(username);
        boolean matchUserName = username.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        boolean matchPassword = password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");  //Tối thiểu tám ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường và một số
        boolean matchRetypePassword = password.equals(reTypePassword);
        boolean matchPhoneNumber = phoneNumber.matches("[0-9]{10,11}");

        String validateUserName = "";
        String validatePassword = "";
        String validateReTypePassword = "";
        String validatePhoneNumber = "";
        String validateFullName = "";
        String validateAddress = "";
        String signUpOK = "Register Successfully, Please Sign In";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("signUp.jsp");
        if (!matchUserName || username.equals(" ")) {
            validateUserName = "Invalid format";
        } else if (accountFindOut != null) {
            validateUserName = "Existed account, please select other username";
        }
        if (!matchPassword || password.equals("example") || password.equals(" ")) {
            validatePassword = "Invalid format";
        }
        if (!matchRetypePassword || reTypePassword.equals("example") || reTypePassword.equals(" ")) {
            validateReTypePassword = "Not Match Password";
        }
        if (fullname.equals(" ")) {
            validateFullName = "Do not leave blank";
        }
        if (address.equals(" ")) {
            validateAddress = "Do not leave blank";
        }
        if (!matchPhoneNumber || password.equals(" ")) {
            validatePhoneNumber = "Invalid format";
        }
        request.setAttribute("validateUserName", validateUserName);
        request.setAttribute("validateName", validateFullName);
        request.setAttribute("validatePassword", validatePassword);
        request.setAttribute("validateReTypePassword", validateReTypePassword);
        request.setAttribute("validateAddress", validateAddress);
        request.setAttribute("validatePhoneNumber", validatePhoneNumber);
        if (matchUserName && matchPassword && matchRetypePassword && matchPhoneNumber && accountFindOut == null) {
            Account account = new Account(id, fullname, username, password, "0", address, phoneNumber, gender, true);
            accountDao.addNewAccount(account);
            request.setAttribute("signUpOK", signUpOK);
        }
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }

        switch (action){
            case "edit":
                showEditAccountForm(request, response);
                break;
            case "create":

                showCreateForm(request, response);
                break;
            case "delete":
                showDeleteAccountForm(request, response);
                break;
            default:
                showListAccountForm(request, response);
                break;

        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/account/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showDeleteAccountForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String accountId = request.getParameter("accountId");
        Account account = new Account();
        account = accountDao.findById(accountId);
        request.setAttribute("account",account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/account/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showEditAccountForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        Account account = new Account();
        account = accountDao.findById(accountId);
        request.setAttribute("account",account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/account/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showListAccountForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Account> accounts = new ArrayList<>();
        accounts = accountDao.viewAllAccount();
        request.setAttribute("accounts", accounts);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/account/list.jsp");
        requestDispatcher.forward(request, response);
    }
}
