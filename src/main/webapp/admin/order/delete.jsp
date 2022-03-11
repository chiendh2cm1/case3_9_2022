<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/7/2022
  Time: 3:13 PM
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
    <a href="/order">Danh sách đơn hàng</a>
    <h1>Xóa đơn hàng</h1>
    <form action="/order?action=deletePost&id=${order.orderID}" method="post">
        <div class="mb-3">
            <label for="orderId" class="form-label">OrderId:</label>
            <input type="text" class="form-control" id="orderId" name="orderId" value="${order.orderID}" disabled>
        </div>
        <div class="mb-3">
            <label for="accountId" class="form-label">AccountID:</label>
            <input type="text" class="form-control" id="accountId" name="accountId" value="${order.accountID}" disabled>
        </div>
        <div class="mb-3">
            <label for="orderDate" class="form-label">Order Date:</label>
            <input type="text" class="form-control" id="orderDate" name="orderDate" value="${order.orderDate}" disabled>
        </div>
        <div class="mb-3">
            <label for="receiver" class="form-label">Receiver:</label>
            <input type="text" class="form-control" id="receiver" name="receiver" value="${order.receiver}" disabled>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address:</label>
            <input type="text" class="form-control" id="address" name=" address" value="${order. address}" disabled>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="text" class="form-control" id="email" name="email" value="${order.email}" disabled>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">PhoneNumber:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${order.phoneNumber}" disabled>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status:</label>
            <input type="text" class="form-control" id="status" name="status" value="${order.status}" disabled>
        </div>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>