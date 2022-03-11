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
      <th scope="col">Index</th>
      <th scope="col">Mã Sản Phẩm</th>
      <th scope="col">Tên sản phẩm</th>
      <th scope="col">Giá sản phẩm</th>
      <th scope="col">Mô tả sản phẩm</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" varStatus="loop" items="${products}">
      <tr>
        <td>${loop.count}</td>
        <td>${product.productId}</td>
        <td>${product.productName}</td>
        <td>${product.productPrice}</ td>
        <td>${product.description}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>