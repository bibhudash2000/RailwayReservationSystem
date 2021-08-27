<%@page import="com.RRS.Models.TrainCategory"%>
<%@page import="com.RRS.Models.Week"%>
<%@page import="com.RRS.DAO.CoachDAO"%>
<%@page import="com.RRS.Models.Coach"%>
<%@page import="com.RRS.Models.TrainsSchedule"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.TrainDAO"%>
<%@page import="com.RRS.Models.Train"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String TrainNo;
    Train t;
    List<TrainsSchedule> list;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="CommonCSS.jsp" %>
        <title>Train Time-Table</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>

        <%try {
                TrainNo = request.getParameter("TrainNo");
                t = TrainDAO.getTrainInfo(TrainNo);
                list = TrainDAO.getSchedule(TrainNo);
                if (t != null) {%>
        <!--Code starts from here-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="card mt-3" style="box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                         -webkit-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                         -moz-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);">
                        <div class="card-header PrimaryColor text-center text-white">
                            <i class="fa fa-train fa-2x"></i>
                            <h3><%=t.getTrainName()%></h3>
                            <small>Train No : <%=t.getTrainNo()%></small>
                        </div>
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <div class="card">
                                            <div class="card-body PrimaryColor text-white">
                                                <div class="form-inline mb-4">
                                                    <label class="mr-5">
                                                        <strong>Classes : &nbsp;</strong>
                                                        <%List<Coach> coachList = CoachDAO.getAvailableCoaches(t);%>
                                                        <%if (!coachList.isEmpty()) {%>
                                                        <%for (Coach c : coachList) {%>
                                                        <label data-toggle="tooltip" data-placement="bottom" title="<%=c.getCOACH_TYPE()%>"><%=c.getCLASS_CODE()%></label>&nbsp;
                                                        <%}%>
                                                        <%} else {%>
                                                        NA
                                                        <%}%>
                                                    </label>

                                                    <label class="mr-5">
                                                        <strong>No of Coaches : &nbsp;</strong>
                                                        <%Integer coachesCount = CoachDAO.getCountCoachesOfTrain(t);%>
                                                        <label data-toggle="tooltip" data-placement="bottom" title=""><%=coachesCount%></label>
                                                    </label>
                                                    <label class="mr-5">
                                                        <strong>Coach Position : <a class="" href="CoachPosition.jsp?TrainNo=<%=t.getTrainNo()%>">click here to view coach position of <%=t.getTrainName()%></a></strong>
                                                    </label>
                                                </div>
                                                <div class="form-group">
                                                    <label style="display: flex;">
                                                        <strong>Service Days : &nbsp;</strong>
                                                        <%Week week = TrainDAO.getTrainRunningDays(t);%>
                                                        <%if (week != null) {%>
                                                        <ul class="week text-center" style="margin-left: -38px; margin-top: 3px;">
                                                            <%if (week.getMon()) {%>
                                                            <li class="available">M</li>
                                                                <%} else {%>
                                                            <li>M</li>
                                                                <%}%>
                                                                <%if (week.getTue()) {%>
                                                            <li class="available">T</li>
                                                                <%} else {%>
                                                            <li class="">T</li>
                                                                <%}%>
                                                                <%if (week.getWed()) {%>
                                                            <li class="available">W</li>
                                                                <%} else {%>
                                                            <li>W</li>
                                                                <%}%>
                                                                <%if (week.getThu()) {%>
                                                            <li class="available">T</li>
                                                                <%} else {%>
                                                            <li>T</li>
                                                                <%}%>
                                                                <%if (week.getFri()) {%>
                                                            <li class="available">F</li>
                                                                <%} else {%>
                                                            <li>F</li>
                                                                <%}%>
                                                                <%if (week.getSat()) {%>
                                                            <li class="available">S</li>
                                                                <%} else {%>
                                                            <li>S</li>
                                                                <%}%>
                                                                <%if (week.getSun()) {%>
                                                            <li class="available sun">S</li>
                                                                <%} else {%>
                                                            <li class="sun">S</li>
                                                                <%}%>
                                                        </ul>
                                                        <%} else {%>
                                                        NA
                                                        <%}%>
                                                    </label>
                                                    <label class="mr-5">
                                                        <strong>Train Type :&nbsp;</strong>
                                                        <%TrainCategory trainCategory = TrainDAO.getTrainCategory(t);%>
                                                        <%=trainCategory.getCATEGORY_NAME()%>
                                                    </label>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="mr-5">
                                                        <strong>Halts Stations :&nbsp;</strong>
                                                        <%=t.getHaltStations()%>
                                                    </label>
                                                    <label class="mr-5">
                                                        <strong>Duration :&nbsp;</strong>
                                                        <%=t.getDuration()%>
                                                    </label>
                                                    <label>
                                                        <strong>Distance Covers :&nbsp;</strong>
                                                        <%=t.getTotalDistance()%>&nbsp;KM
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row mt-3">
                                    <div class="col">
                                        <div class="card">
                                            <div class="card-header PrimaryColor text-center text-white"><%=t.getTrainName()%> (<%=t.getTrainNo()%>) Schedule</div>
                                            <div class="card-body">
                                                <div class="container-fluid">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">#</th>
                                                                <th scope="col">Station Name</th>
                                                                <th scope="col">Arrives</th>
                                                                <th scope="col">Depart</th>
                                                                <th scope="col">Halt Time</th>
                                                                <th scope="col">Distance Traveled</th>
                                                                <th scope="col">Day</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%int i = 0;%>
                                                            <%for (TrainsSchedule ts : list) {%>
                                                            <tr>
                                                                <th scope="row"><%=++i%></th>
                                                                <td><a href="Station-Info.jsp?station_code=<%=ts.getStationCode()%>"><%=ts.getStationName()%></a> (<%=ts.getStationCode()%>)</td>
                                                                <td><%=ts.getArrival()%></td>
                                                                <td><%=ts.getDeparture()%></td>
                                                                <td><%=ts.getHalt()%></td>
                                                                <td><%=ts.getDistance()%> KM</td>
                                                                <td><%=ts.getDay()%></td>
                                                            </tr>
                                                            <%}%>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer"></div>
                    </div>
                </div>
            </div>
        </div>












        <!--Code Ends here-->



        <%} else {%>
        Nothing to show  
        <%}
        } catch (Exception e) {%>
        Nothing to show  
        <%=e.getMessage()%>
        <%}%>


        <%@include file="CommonJS.jsp" %>
    </body>
</html>
