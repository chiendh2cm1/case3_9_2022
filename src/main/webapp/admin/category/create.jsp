<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/9/2022
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="/Category">List Category</a>
    <h1>Tạo mới danh mục sản phẩm</h1>
    <c:if test="${message!=null}">
        <p class="alert alert-success">${message}</p>
    </c:if>
    <form action="/category?action=createPost" method="post">
        <div class="mb-3">
            <label for="categoryId" class="form-label">Mã danh mục :</label>
            <input type="text" class="form-control" id="categoryId" name="categoryId">
        </div>
        <div class="mb-3">
            <label for="categoryName" class="form-label">Tên danh mục :</label>
            <input type="text" class="form-control" id="categoryName" name="categoryName">
        </div>
        <button type="submit" class="btn btn-primary">Tạo mới</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>