<%@page import="com.DigiBank.Lib.APIRequestManager"%>
<%@page import="com.DigiBank.Lib.Payment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Payment payment = (Payment) session.getAttribute("refund-payment");%>
        <form method="POST" action="<%=APIRequestManager.initiateRefundAPIRequestURL()%>">
            <input type="hidden" name="TXNID" value="<%=payment.getTxnID()%>">
            <input type="hidden" name="TXNDATETIME" value="<%=payment.getTxtDateTime()%>">
            <input type="hidden" name="CARDNUMBER" value="<%=payment.getCardNumber()%>">
            <input type="hidden" name="EXPIRY" value="<%=payment.getExpiry()%>">
            <input type="hidden" name="CVV" value="<%=payment.getCVV()%>">
            <input type="hidden" name="AMOUNT" value="<%=payment.getAmount()%>">
            <input type="hidden" name="UserName" value="<%=payment.getUsername()%>">
            <input type="hidden" name="Password" value="<%=payment.getPassword()%>">
            <input type="hidden" name="API_KEY" value="<%=payment.getApiKey()%>">
            <input type="hidden" name="CALLBACK_URL" value="<%=payment.getCallbackURL()%>">
        </form>
        <script type="text/javascript">
            document.forms[0].submit();
        </script>
    </body>
</html>
