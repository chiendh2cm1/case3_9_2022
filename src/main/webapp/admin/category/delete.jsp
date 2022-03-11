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
    <a href="/category">List Category</a>
    <h1>Xóa danh mục sản phẩm </h1>
    <form action="/category?action=deletePost&id=${category.categoryId}" method="Post">
        <div class="mb-3">
            <label for="orderId" class="form-label">OrderId:</label>
            <input type="text" class="form-control" id="orderId" name="categoryId" value="${category.categoryId}" disabled>
        </div>
        <div class="mb-3">
            <label for="accountId" class="form-label">AccountID:</label>
            <input type="text" class="form-control" id="accountId" name="categoryName" value="${category.categoryName}" disabled>
        </div>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>