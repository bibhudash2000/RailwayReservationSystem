<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="CommonCSS.jsp" %>
        <title>Error</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container-fluid mt-1 text-center">
            <img src="img/Error.gif" alt="" style="width: 550px; height: 400px;"/>
            <h3>Something went wrong</h3>
            <div class="container mt-4 d-flex justify-content-center">
                
                <p class="text-center">Try :</p>
                <ul>
                    <li>Refreshing the page</li>
                    <li>Restart the browser</li>
                    <li>Check your Internet connection</li>
                </ul>
            </div>


        </div>
        <%@include file="CommonJS.jsp" %>
    </body>
</html>
