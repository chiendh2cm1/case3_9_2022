<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/7/2022
  Time: 11:32 AM
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
    <a href="/order">Danh sách order</a>
    <h1>Chi tiết order</h1>
    <form>
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
            <input type="text" class="form-control" id="receiver" name="receiver" value="${order.receiver}">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address:</label>
            <input type="text" class="form-control" id="address" name="address" value="${order. address}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="text" class="form-control" id="email" name="email" value="${order.email}">
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">PhoneNumber:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${order.phoneNumber}">
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status:</label>
            <input type="text" class="form-control" id="status" name="status" value="${order.status}" disabled>
        </div>
        <a href="/products" class="btn btn-secondary"><Return>
        </Return></a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>