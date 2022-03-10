<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 10/03/2022
  Time: 08:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Tempusdominus Bootstrap 4 -->
    <link rel="stylesheet" href="../plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="../plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- JQVMap -->
    <link rel="stylesheet" href="../plugins/jqvmap/jqvmap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/adminlte.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="../plugins/daterangepicker/daterangepicker.css">
    <!-- summernote -->
    <link rel="stylesheet" href="../plugins/summernote/summernote-bs4.min.css">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->


        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="" class="img-circle elevation-2">
                </div>
                <div class="info">
                    <a href="#" class="d-block">${account.accountName}</a>
                </div>
            </div>

            <!-- SidebarSearch Form -->
            <div class="form-inline">
                <div class="input-group" data-widget="sidebar-search">
                    <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
                    <div class="input-group-append">
                        <button class="btn btn-sidebar">
                            <i class="fas fa-search fa-fw"></i>
                        </button>
                    </div>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                Mangament Product

                            </p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href=#" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                Mangament Category

                            </p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/login" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                Mangament Account

                            </p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/order" class="nav-link">
                            <i class="nav-icon fas fa-th"></i>
                            <p>
                                Mangament Order

                            </p>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>
    <div class = "content-wrapper">
        <div class = "content-header"></div>
        <section class="content">
            <div class = "container-fluid">
                <div class ="row mb-2">

                </div>
                <h2>Danh sách tài khoản</h2>
                <a href="/login?action=create">Tạo tài khoản</a>
                <table class = "table table-bordered">
                    <th>AccountID</th>
                    <th>AccountName</th>
                    <th>LoginName</th>
                    <th>Password</th>
                    <th>AccountAccess</th>
                    <th>Address</th>
                    <th>PhoneNumber</th>
                    <th>Gender</th>

                    <th colspan="2"></th>
                    <c:forEach var="account"  items="${accounts}">
                        <tr>
                            <td>${account.accountId}</td>
                            <td>${account.accountName}</td>
                            <td>${account.loginName}</td>
                            <td>${account.password}</td>

                            <c:set var = "accountAccess" scope = "session" value = "${account.accountAccess}"/>
                            <c:if test = "${accountAccess==true}">
                                <td><c:out value = "Admin"/></td>
                            </c:if>
                            <c:if test = "${accountAccess==false}">
                                <td><c:out value = "User"/></td>
                            </c:if>

                            <td>${account.address}</td>
                            <td>${account.phoneNumber}</td>

                            <c:set var = "gender" scope = "session" value = "${account.gender}"/>
                                <c:if test = "${gender==true}">
                                    <td><c:out value = "Male"/></td>
                                </c:if></td>
                                <c:if test = "${gender==false}">
                                    <td><c:out value = "Female"/></td>
                                 </c:if>


                            <td><a href="/login?action=edit&accountId=${account.accountId}"><button class="btn btn-primary">Edit</button></a></td>
                            <td><a href="/login?action=delete&accountId=${account.accountId}"><button class="btn btn-danger">Delete</button></a></td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
        </section>
    </div>



</div>
</body>
</html>


</body>
</html>
