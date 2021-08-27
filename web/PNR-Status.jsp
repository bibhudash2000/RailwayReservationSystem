<%@page import="com.RRS.Utilities.DateTime"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.Models.Passenger"%>
<%@page import="com.RRS.Models.PNR"%>
<%@page import="com.RRS.DAO.ReservationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%Integer i = 0;%>
<%Message msg = (Message) session.getAttribute("msg");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>PNR-Status</title>
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

        <div class="container mt-3">
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <div class="card mb-3" style="width: 50rem;">
                        <div class="card-header PrimaryColor text-white">Check Your PNR - Status</div>
                        <div class="card-body">
                            <div class="card" id="bodyStatus">
                                <%try {%>
                                <%PNR PNR_NO = (PNR) session.getAttribute("PNR");%>
                                <%List<Passenger> list = ReservationDAO.listPassengersFromPNR(PNR_NO.getTicket().getPNR_No());%>
                                <div class="card-header"><label><b>PNR No : <%=PNR_NO.getTicket().getPNR_No()%></b></label></div>
                                <div class="card-body PrimaryColor text-white">
                                    <div class="row ">
                                        <div class="col-5 d-flex ">
                                            <div class="d-block">
                                                <label for="locFrom"><b>FROM</b></label>
                                                <div>
                                                    <label id="locFrom"><%=PNR_NO.getSrcStation().getCity()%>&nbsp;(<%=PNR_NO.getSrcStation().getStation_Code()%>)</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="d-flex">
                                                <i class="fa fa-arrow-right"></i>
                                            </div>
                                        </div>
                                        <div class="col-5 d-flex justify-content-end">
                                            <div class="d-block">
                                                <label for="locTo" class="float-right"><b>TO</b></label>
                                                <div>
                                                    <label class="float-right" id="locTo">
                                                        <%=PNR_NO.getDestStation().getCity()%>&nbsp;(<%=PNR_NO.getDestStation().getStation_Code()%>)
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-4 d-flex justify-content-between">
                                        <div class="col-4">
                                            <div class="d-block">
                                                <label for="lblDate"><b>DATE:</b></label>
                                                <div><label id="lblDate"><%=PNR_NO.getTicket().getDate_Of_Journey()%></label></div>
                                            </div>
                                        </div>                   
                                        <div class="col-4">
                                            <div class="d-block">
                                                <label for="lblTime"><b>TIME:</b></label>
                                                <div><label id="lblTime"><%=DateTime.filterDate(PNR_NO.getTicket().getDepartureTime())%></label></div>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="d-block">
                                                <label for="lblTrain" class="float-right"><b>TRAIN</b></label>
                                                <div><label id="lblTrain" class="float-right"><%=PNR_NO.getTrain().getTrainName()%>(<%=PNR_NO.getTrain().getTrainNo()%>)</label></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-4">
                                        <div class="col">
                                            <table class="table text-center">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">#</th>
                                                        <th scope="col">Passengers</th>
                                                        <th scope="col">Age</th>
                                                        <th scope="col">Gender</th>
                                                        <th scope="col">Seat No</th>
                                                        <th scope="col">Booking Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%for (Passenger p : list) {%>
                                                    <%i++;%>
                                                    <tr>
                                                        <th scope="row"><%=i%></th>
                                                        <td><%=p.getName()%></td>
                                                        <td><%=p.getAge()%></td>
                                                        <td><%=p.getGender()%></td>
                                                        <td><%=p.getSeatNo()%></td>
                                                        <%if (PNR_NO.getTicket().getStatus().name() == "CNF") {%>
                                                        <td class="green"><%=PNR_NO.getTicket().getStatus()%></td>
                                                        <%} else if (PNR_NO.getTicket().getStatus().name() == "WL") {%>
                                                        <td class="orange"><%=PNR_NO.getTicket().getStatus()%></td>
                                                        <%} else {%>
                                                        <td class="red"><%=PNR_NO.getTicket().getStatus()%></td>
                                                        <%}%>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <%} catch (Exception e) {%>
                                <%=e.getMessage()%>
                                <%}%>
                            </div>
                        </div>
                        <div class="card-footer text-center">
                            <a href="index.jsp" class="btn btn-outline-info">Back to Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="CommonJS.jsp" %>
    </body>
</html>
