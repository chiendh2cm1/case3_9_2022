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
    <a href="/login">Danh sách Tài Khoản</a>
    <h1>Cập nhật Tài Khoản</h1>
    <div class="form-group">
        <form action="/login?action=edit&accountId=${account.accountId}" method="post">
            <div class="mb-3">
                <label for="orderId" class="form-label">AccountId:</label>
                <input type="text" class="form-control" id="orderId" name="accountId" value="${account.accountId}" readonly>
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
                <c:set var = "accountAccess" scope = "session" value = "${account.accountAccess}"/>
                <c:choose>
                    <c:when test="${accountAccess == true}">
                        <select id="receiver" name="access" >
                            <option value=true selected>Admin</option>
                            <option value=false>User</option>
                        </select>
                    </c:when>
                    <c:when test="${accountAccess==false}">
                        <select id="receiver" name="access" >
                            <option value=false selected>User</option>
                            <option value=true>Admin</option>
                        </select>
                    </c:when>

                </c:choose>


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
                <label for="receiver" class="form-label">Gender:</label>

<%--                <c:set var = "gender1" scope = "session" value = "${account.gender}"/>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${gender1== true}">--%>
<%--&lt;%&ndash;                        <select id="receiver" name="access" >&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <option value='true' selected>Male</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <option value='false'>Female</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </select>&ndash;%&gt;--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${gender1==false}">--%>
                        <select id="receiver" name="gender" >
                            <option value ="false">Female</option>
                            <option value = "true">Male</option>
                        </select>
<%--                    </c:when>--%>

<%--                </c:choose>--%>



            </div>

            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>