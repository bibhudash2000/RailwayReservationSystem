<%@page import="com.RRS.DAO.UserDAO"%>
<%@page import="com.RRS.Models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String email = request.getParameter("Email");
String enc_key = request.getParameter("enc_key");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="CommonCSS.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <!--Navbar-->
        <%@ include file="navbar.jsp"%>
        <!--Navbar End-->

        <div class="container">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-center">
                    <div class="card m-5 text-center" style="width: 350px;">
                        <form method="POST" action="AuthenticateNewUserServlet">
                            <div class="card-header text-center text-white PrimaryColor">Add your Password</div>
                            <div class="card-body">
                                <input type="hidden" name="enc_key" value="<%=enc_key%>">
                                <input type="hidden" name="email" value="<%=email%>">
                                <div class="form-group d-flex my-4">
                                    <label for="DisplayEmail"><i class="fa fa-user" style="width: 20px;"></i></label>
                                    <input type="text" disabled class="form-control" value="<%=email%>" name="InputEmail" id="DisplayEmail" style="height: 35px;">
                                </div>
                                <div class="form-group d-flex my-4">
                                    <label for="InputPassword"><i class="fa fa-key" style="width: 20px;"></i></label>
                                    <input type="password" class="form-control" name="InputPassword" id="InputPassword" placeholder="Password" style="height: 35px;">
                                </div>
                                <div class="form-group d-flex my-4">
                                    <label for="InputConfirmPassword"><i class="fa fa-key" style="width: 20px;"></i></label>
                                    <input type="password" class="form-control" name="InputConfirmPassword" id="InputConfirmPassword" placeholder="Confirm-Password" style="height: 35px;">
                                </div>
                                
                                <button type="submit" class="btn PrimaryColor text-white">Submit</button>
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
