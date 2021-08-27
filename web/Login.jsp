<%-- 
    Document   : Login
    Created on : 15 Apr, 2021, 2:34:09 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>Login</title>
    </head>
    <body>
        <!--Navbar-->
        <%@ include file="navbar.jsp"%>
        <!--Navbar End-->

        <div class="container">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-center">
                    <div class="card m-5 text-center" style="width: 350px;">
                        <form method="POST" action="LoginServlet">
                            <div class="card-header text-center text-white PrimaryColor">Login</div>
                            <div class="card-body">
                                    <div class="form-group d-flex my-4">
                                        <label for="InputEmail"><i class="fa fa-envelope" style="width: 20px;"></i></label>
                                        <input type="email" class="form-control" name="InputEmail" id="InputEmail" placeholder="Enter email" style="height: 35px;">
                                    </div>
                                    <div class="form-group d-flex my-4">
                                        <label for="InputPassword"><i class="fa fa-key" style="width: 20px;"></i></label>
                                        <input type="password" class="form-control" name="InputPassword" id="InputPassword" placeholder="Password" style="height: 35px;">
                                    </div>
                                    <div class="form-check my-4">
                                        <input type="checkbox" class="form-check-input" id="chkRemember">
                                        <label class="form-check-label" for="chkRemember">Remember me</label>
                                    </div>
                                    <button type="submit" class="btn PrimaryColor text-white">Login</button>
                            </div>
                            <div class="card-footer">Don't have an account ? <a class="" href="NewUser.jsp">Click here</a></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="CommonJS.jsp"%>
    </body>
</html>
