<%@page import="com.RRS.Models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%User user = (User)session.getAttribute("visitor");
session.setAttribute("user", user);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>Hello <c:out value="${user.getName()}"/></title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        
        
        <%@include file="CommonJS.jsp" %>
    </body>
</html>
