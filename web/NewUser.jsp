<%@page import="com.RRS.Models.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Message msg = (Message) session.getAttribute("userMsg");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>New Account</title>
        <%@include file="CommonCSS.jsp" %>
    </head>
    <body>
        <!--Navbar-->
        <%@ include file="navbar.jsp"%>
        <!--Navbar End-->
        
        <%if (user != null) {%>
            <%msg = new Message("You must Logout to create a new Account !!!!!",Message.ALERT_DANGER);%>
            <%session.setAttribute("msg", msg);%>
            <%response.sendRedirect("index.jsp");%>
        <%}%>


        <!--Message Alert-->
        <%if (msg != null) {%>
        <div class="alert <%=msg.getCSS()%>" role="alert">
            <%=msg.getContent()%>
        </div>
        <%}
            session.removeAttribute("userMsg");%>
        <!--Message Alert End-->

        <div class="container mt-4">
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <div class="card m-2" style="width: 350px;">

                        <div class="card-header text-white PrimaryColor">Register</div>
                        <div class="card-body">
                            <form method="POST" action="RegisterServlet">
                                <div class="form-group">
                                    <label for="inputName">Name</label>
                                    <input type="text" class="mx-auto form-control" name="inputName" id="inputName" placeholder="Full Name" required>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail">Email</label>
                                    <input type="email" class="mx-auto form-control" name="inputEmail" id="inputEmail" placeholder="Email Address" required>
                                </div>
                                <div class="form-group">
                                    <label for="inputContact">Contact</label>
                                    <input type="number" class="mx-auto form-control" name="inputContact" id="inputContact" placeholder="Mobile Number" required>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="inputCity">City</label>
                                        <input type="text" class="form-control" id="inputCity">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputState">State</label>
                                        <select id="inputState" class="form-control">
                                            <option selected>Choose...</option>
                                            <option>...</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="inputZip">Zip</label>
                                        <input type="text" class="form-control" id="inputZip">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="gridCheck" required>
                                        <label class="form-check-label" for="gridCheck">
                                            I accept all the <a href="#" data-toggle="modal" data-target="#modal-terms-conditions">Terms and Conditions</a>
                                        </label>
                                    </div>
                                </div>
                                <!-- Modal -->
                                
                                <button type="submit" class="btn PrimaryColor text-white">Sign in</button>
                            </form>
                        </div>
                        <div class="card-footer">Already have an account ? <a href="#loginModal" data-toggle="modal">Click here</a></div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="CommonJS.jsp"%>

    </body>
</html>
