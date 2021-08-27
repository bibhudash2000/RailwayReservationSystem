<%@page import="com.RRS.Utilities.APIRequestManager"%>
<%@page import="com.DigiBank.Lib.CardValidation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Validate Card</title>
    </head>
    <body>
        <%CardValidation validate = (CardValidation) session.getAttribute("validate-card");%>
        <form method="POST" action="<%=APIRequestManager.getRootAPIRequestURL()%>/validate-card">
            <input type="hidden" name="CardNumber" value="<%=validate.getCardNumber()%>">
            <input type="hidden" name="Expiry" value="<%=validate.getExpiry()%>">
            <input type="hidden" name="CVV" value="<%=validate.getCVV()%>">
            <input type="hidden" name="UserName" value="<%=validate.getUsername()%>">
            <input type="hidden" name="Password" value="<%=validate.getPassword()%>">
            <input type="hidden" name="API_KEY" value="<%=validate.getApiKey()%>">
            <input type="hidden" name="CALLBACK_URL" value="<%=validate.getCallbackURL()%>">
        </form>
        <script type="text/javascript">
            document.forms[0].submit();
        </script>

    </body>
</html>
