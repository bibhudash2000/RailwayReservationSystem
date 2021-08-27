<%@page import="com.RRS.Models.TicketBookingStatus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>Ticket Booking Status</title>
        <style>
            .status{
                height: 100%;
                width: 100%;
                align-items: center;
                justify-content: center;               
            }
            .status .status-img{
                background-size: cover;
                height: 100px;
                width: 100px;
                display: inline-block;
            }
            .status .status-info{
                display: block;
            }
        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <%TicketBookingStatus status = (TicketBookingStatus) session.getAttribute("booking-status");%>


        <div class="container d-flex justify-content-center">
            <div class="card mt-3" style="width: 25rem;">
                <div class="card-header PrimaryColor text-white"><b>Transaction ID : </b><%=status.getTransactionID()%> </div>
                <div class="card-body">
                    <div class="status text-center">
                        <%if (status.getBookingStatus().toString().equals("SUCCESS")) {%>
                        <div class="status-img" style="background-image: url(img/happy-emoji.gif);"></div>
                        <div class="status-info">
                            <h4 class="text-success">Ticket Booked Successfully</h4>
                            <p class="mt-4">Your Booking has been confirmed. The ticket has been sent to <%=user.getEmail()%></p>
                        </div>
                        <%} else if (status.getBookingStatus().toString().equals("PENDING")) {%>
                        <div class="status-img" style="background-image: url(img/sad2.gif);"></div>
                        <div class="status-info">
                            <h4 class="text-warning">Ticket Confirmation Pending</h4>
                        </div>
                        <%} else {%>
                        <div class="status-img" style="background-image: url(img/sad-emoji.gif);"></div>
                        <div class="status-info">
                            <h4 class="text-danger">Booking Failed</h4>
                            <p class="mt-3"><span class="font-weight-bold">NOTE: </span> If any amount is deducted from your account will be refunded to you back within 48hrs</p>
                        </div>
                        <div class="container justify-content-center d-flex">
                            <a href="PaymentInit.jsp" class="btn PrimaryColor text-white align-items-center d-flex">
                               
                                <span class="material-icons">
                                    refresh
                                </span>&nbsp;Retry</a>
                        </div>
                        <%}%>



                    </div>
                </div>
            </div>

        </div>





        <%@include file="CommonJS.jsp" %>        
    </body>
</html>
