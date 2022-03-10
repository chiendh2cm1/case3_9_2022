<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/5/2022
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    <style>
        .container{
            margin-top:10vh;
        }
        #cover {
            background: #222 url('https://unsplash.it/1920/1080/?random') center center no-repeat;
            background-size: cover;
            height: 100%;
            text-align: center;
            display: flex;
            align-items: center;
            position: relative;
        }

        #cover-caption {
            width: 100%;
            position: relative;
            z-index: 1;
        }

        /* only used for background overlay not needed for centering */
        form:before {
            content: '';
            height: 100%;
            left: 0;
            position: absolute;
            top: 0;
            width: 100%;
            background-color: rgba(0,0,0,0.3);
            z-index: -1;
            border-radius: 10px;
        }

    </style>
</head>
<body class="bodyImage" style='background-image: url("https://unsplash.it/1920/1080/?random"); '>

<%--HEADER--%>

<section id="cover" class="min-vh-100">
    <div id="cover-caption">
        <div class="container">
            <div class="row text-white">
                <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
                    <h1 class="display-4 py-2 text-truncate">Login</h1>
                    <div class="px-2">
                        <form action="/login?action=login" method="post" class="justify-content-center">
                            <div class="form-group">
                                <label class="sr-only">Name</label>
                                <input type="text" class="form-control" name="username" placeholder="Jane Doe">
                            </div>
                            <div class="form-group">
                                <label class="sr-only">Password</label>
                                <input type="password" name="password" class="form-control">
                            </div>
                            <a href="/pagination"><i style="font-size: 25px" class="fas fa-home"></i></a>
                            <a href="/signUp.jsp"><button type="submit"  value="SignUp" class="btn btn-primary btn-lg">Sign Up </button></a>
                            <button type="submit" class="btn btn-primary btn-lg">Login</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
