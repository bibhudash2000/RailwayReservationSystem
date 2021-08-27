<%@page import="com.DigiBank.Lib.APIRequestManager"%>
<%@page import="com.DigiBank.Lib.Payment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Initializing Payment</title>
    </head>
    <body>
        <h4>Initializing Payment, please wait...</h4>
        <h4>Redirecting to the Payment Gateway</h4>
        <p>Please don't press back button or refresh the page</p>

        <%String checksum = (String) session.getAttribute("checksum");%>
        <form method="POST" action="<%=APIRequestManager.initiateCardPaymentAPIRequestURL()%>">
            <input type="hidden" name="CHECKSUM" value="<%=checksum%>">
        </form>
        <script type="text/javascript">
             document.forms[0].submit();
        </script>
        <%@include file="CommonJS.jsp"%>
       
    </body>
</html>
