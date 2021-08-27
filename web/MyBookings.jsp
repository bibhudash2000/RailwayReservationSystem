<%@page import="com.RRS.Models.Station"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%@page import="com.RRS.Models.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.ReservationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>My Bookings</title>
        <style>
            .reservation-list{
                transition: ease-in-out 0.4s;
                cursor: pointer;

            }
            .reservation-list:hover{
                background-color:  #3f51b5;
                color: #ffffff;
            }
            .no-bookings-found{
                background-image: url("img/sad2.gif");
                background-size: cover;
                height: 10rem;
                width: 10rem;
            }
            


        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container-fluid d-flex justify-content-center">
            <div class="card my-4" style="width: 40rem;">
                <div class="card-header">

                    <div class="input-group ">
                        <input type="search" class="form-control" placeholder="Search Tickets">
                        <div class="input-group-append">
                            <button class="btn PrimaryColor text-white" type="button"><i class="fa fa-search"></i></button>
                        </div>
                    </div>

                </div>
                <div class="card-body" style="overflow-y: scroll; max-height: 28rem;">
                    <%List<String> dateList = ReservationDAO.listDatesOfBookedTickets(user);%>
                    <%if (dateList.size() < 1) {%>
                    <!--If no bookings found-->
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4">
                                <div class="no-bookings-found">
                                    
                                </div>
                            </div>
                            <div class="col-8">
                                <h5>Ahhh... You don't have any booking till now</h5>
                                <div class="container text-center">
                                    <a href="Book-Ticket.jsp" class="btn btn-outline-info">Book Now</a>
                                </div>
                                
                            </div>
                        </div>
                    </div>


                    <%} else {%>

                    <%for (String date : dateList) {%>

                    <b><%=date%></b>
                    <%List<Ticket> ticketList = ReservationDAO.listTicketsBookedOnDate(user, date);%>
                    <%for (Ticket ticket : ticketList) {%>
                    <div class="card mb-3 reservation-list">
                        <div class="card-body">
                            <div class="row d-flex justify-content-between">
                                <div class="col">
                                    <%Station src = StationDAO.viewStation(ticket.getSourceStationID());%>
                                    <%=src.getStation_Name()%>
                                    <%=src.getStation_Code()%>
                                </div>
                                <div class="col" style="display: flex; flex-direction: column;">
                                    <%Station dest = StationDAO.viewStation(ticket.getDestinationStationID());%>
                                    <%=dest.getStation_Name()%>
                                    <%=dest.getStation_Code()%>
                                </div>
                            </div>


                        </div>
                    </div>
                    <%}%>

                    <%}%>
                    <%}%>

                </div>
            </div>
        </div>



        <%@include file="CommonJS.jsp" %>
    </body>
</html>
