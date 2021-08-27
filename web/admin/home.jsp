<%@page import="com.RRS.DAO.TrainDAO"%>
<%@page import="com.RRS.DAO.ReservationDAO"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Message msg = (Message) session.getAttribute("msg");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>Admin Dashboard</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>

        <!--Message Alert-->
        <%if (msg != null) {%>
        <div class="alert <%=msg.getCSS()%>" role="alert">
            <%=msg.getContent()%>
        </div>
        <%}
            session.removeAttribute("msg");%>
        <!--Message Alert End-->

        <div class="container-fluid">
            <div class="row d-flex justify-content-center">
                <div class="col-sm-3">
                    <div class="card mt-3 card-shadow" style="">
                        <div class="card-header d-flex justify-content-between"><b>Today's Booked Tickets</b>
                            <div class="dropdown mx-1">
                                <a class="link-black" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v"></i>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li><a class="dropdown-item" href="#">View Tickets</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="container-fluid">
                                <div class="row text-center">
                                    <div class="col"><i class="fa fa-ticket fa-4x"></i></div>
                                    <div class="col"><%=ReservationDAO.countTodaysBookedTicketList()%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3 ">
                    <div class="card mt-3 card-shadow" style="">
                        <div class="card-header d-flex justify-content-between"><b>Registered Users</b>
                            <div class="dropdown mx-1">
                                <a class="link-black" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v"></i>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <li><a class="dropdown-item" href="#">Add new user</a></li>
                                    <li><a class="dropdown-item" href="#">Search User</a></li>
                                    <li><a class="dropdown-item" href="#">View All Users</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="container-fluid">
                                <div class="row text-center">
                                    <div class="col"><i class="fa fa-user-plus fa-4x"></i></div>
                                    <div class="col"><%=UserDAO.countCustomerList()%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3 ">
                    <div class="card mt-3 card-shadow" style="">
                        <div class="card-header d-flex justify-content-between"><b>Trains</b>
                            <div class="dropdown mx-1">
                                <a class="link-black" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v"></i>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <li><a class="dropdown-item" href="train/New-Train.jsp">Add new Train</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li><a class="dropdown-item" href="train/Train-List.jsp">View All Trains</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="container-fluid">
                                <div class="row text-center">
                                    <div class="col "><i class="fa fa-train fa-4x"></i></div>
                                    <div class="col"><%=TrainDAO.countTrainsList()%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="card mt-3 card-shadow" style="">
                        <div class="card-header d-flex justify-content-between"><b>Stations</b>
                            <div class="dropdown mx-1">
                                <a class="link-black" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v"></i>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <li><a class="dropdown-item" href="station/New-Station.jsp">Add new Station</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/StationListServlet">View All Stations</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="container-fluid">
                                <div class="row text-center">
                                    <div class="col"><i class="fa fa-building fa-4x"></i></div>
                                    <div class="col "><%=StationDAO.countStationsList()%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="CommonJS.jsp" %>
    </body>
</html>
