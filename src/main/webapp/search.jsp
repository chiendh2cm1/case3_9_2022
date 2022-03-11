<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/8/2022
  Time: 11:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="boostrap/css/bootstrap.css">
    <link rel="stylesheet" href="boostrap/css/mainStyle.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="header-infor.jsp"></jsp:include>

<%--SLIDE BAR--%>
<jsp:include page="slide-bar.jsp"></jsp:include>

<%--BODY--%>
<div class="container">
    <div class="searchContent" style="margin: 100px 0">
        <div class="table">
            <form action="/search" method="post">
                <table>
                    <tr>
                        <td style="border-top-color : white"><input name="searchName" type="text"
                                                                    placeholder=" What are you looking for ?"
                                                                    style="height: 70px; width: 800px; border-radius: 50px;border: 1px solid grey; text-indent: 30px">
                        </td>
                        <td style="border-top-color : white"><input type="submit" value="Search"
                                                                    style="height: 70px; width: 200px;border-radius: 50px; border: 1px solid grey">
                        </td>
                    </tr>
                    <tr>
                        <td style="border-top-color : white; text-indent: 30px; color: red"><i>${announcement}</i></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>


    <div class="categoryName" style="margin-bottom: 20px">
        <h3 style="color: darkslategray">We bring you the best!!!</h3>
    </div>
    <div>
    </div>
    <div class="productListShow" style="margin: 30px">
        <div style="padding-top: 40px; padding-bottom: 60px">
            <%--        Vồng lặp hiển thị sản phẩm theo tên--%>
            <c:forEach items="${requestScope['listProduct']}" var="product">

                <div class="categories-block"
                     style="margin-bottom:20px; box-shadow: 5px 5px rgba(43,134,126,0.5);border-radius: 20px; border: 1px rgba(43,134,126,0.5) solid; padding: 40px; height: 580px;">
                    <table style="margin-bottom: 60px">
                        <tr>
                            <td rowspan="4" style="height: 350px; width: 580px;"><img src="${product.getImage()}"
                                                                                      height="500" width="500">
                            </td>
                            <td><h4>${product.getProductName()}</h4></td>
                        </tr>
                        <tr>
                            <td style="vertical-align: top; color: red">Giá bán : <fmt:formatNumber
                                    value="${product.getProductPrice()}"></fmt:formatNumber>₫
                            </td>

                        </tr>
                        <tr>
                            <td style="line-height:25px; vertical-align: top">
                                    ${product.getDescription()}
                            </td>
                        </tr>
                        <tr>
                            <td><a href="productDetails.jsp?productId=${product.getProductId()}"
                                   class="btn btn-primary">Chi Tiết</a></td>
                        </tr>
                    </table>
                    <br>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<br>
<%--FOOTER--%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>