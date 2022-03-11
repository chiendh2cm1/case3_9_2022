<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/7/2022
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="/Login">Danh sách Tài Khoản</a>
    <h1>Cập nhật Tài Khoản</h1>
    <form action="/login?action=edit&accountId=${account.accountId}" method="post">
        <div class="mb-3">
            <label for="orderId" class="form-label">AccountId:</label>
            <input type="text" class="form-control" id="orderId" name="accountId" value="${account.accountId}" disabled>
        </div>
        <div class="mb-3">
            <label for="accountId" class="form-label">AccountName:</label>
            <input type="text" class="form-control" id="accountId" name="accountName" value="${account.accountName}" >
        </div>
        <div class="mb-3">
            <label for="orderDate" class="form-label">Account Login Name:</label>
            <input type="text" class="form-control" id="orderDate" name="loginName" value="${account.loginName}" >
        </div>
        <div class="mb-3">
            <label for="receiver" class="form-label">Account Access:</label>
            <select id="receiver" name="access" >
                <option value="${account.accountAccess}">${account.accountAccess}</option>
                <option value="1">Admin</option>
                <option value="0">User</option>
            </select>

        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Password:</label>
            <input type="text" class="form-control" id="address" name="password" value="${account.password}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Address:</label>
            <input type="text" class="form-control" id="email" name="address" value="${account.address}">
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">PhoneNumber:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${account.phoneNumber}">
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Gender:</label>
            <c:set var = "gender" scope = "session" value = "${account.gender}"/>
            <c:if test = "${gender==true}">
                <select name="gender">
                    <option value="1">Male</option>
                </select>
            </c:if>
            <c:if test = "${gender==false}">
                <select name="gender">
                    <option value="0">Female</option>
                </select>
            </c:if>

        </div>

        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>