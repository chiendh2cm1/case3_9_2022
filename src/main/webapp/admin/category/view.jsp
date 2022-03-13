<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
  <a href="/category">Danh sách danh mục sản phẩm</a>
  <h1>Chi tiết danh mục sản phẩm</h1>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Price</th>
      <th scope="col">Quantity</th>
      <th scope="col">Image</th>
      <th scope="col">Status</th>
      <th scope="col">Description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" varStatus="loop" items="${products}">
      <tr>
        <th scope="row">${loop.count}</th>
        <td>${product.getProductName()}</td>
        <td>${product.getProductPrice()}₫</td>
        <td>${product.getQuantityInStock()}</td>
        <td><img src="${product.getImage()}" alt="Error" width="50" height="50"></td>
        <td>${product.getStatus()}</td>
        <td>${product.getDescription()}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>