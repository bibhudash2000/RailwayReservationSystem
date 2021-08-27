<%@page import="com.RRS.Models.TrainCategory"%>
<%@page import="com.RRS.Utilities.Fare"%>
<%@page import="com.RRS.Models.TicketAvailability"%>
<%@page import="com.RRS.DAO.ReservationDAO"%>
<%@page import="com.RRS.DAO.CoachDAO"%>
<%@page import="com.RRS.Models.TrainFare"%>
<%@page import="com.RRS.Models.User"%>
<%@page import="com.RRS.Utilities.DateTime"%>
<%@page import="com.RRS.Models.Station"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%@page import="com.RRS.DAO.TrainDAO"%>
<%@page import="com.RRS.Models.Coach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.RRS.Models.TrainsSchedule"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@ include file="CommonCSS.jsp"%>
        <title>Trains</title>
        <style>
            .card-shadow{
                box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                -webkit-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                -moz-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
            }
            input[type="date"]::-webkit-inner-spin-button,
            input[type="date"]::-webkit-calendar-picker-indicator {
                background: transparent;
                bottom: 0;
                color: transparent;
                cursor: pointer;
                height: auto;
                left: 0;
                position: absolute;
                right: 0;
                top: 0;
                width: auto;
            }
        </style>
    </head>
    <body>
        <!--Navbar-->
        <%@include file="navbar.jsp" %>
        <!--Navbar End-->

        <!--Java Init Section-->
        <%try {%>
        <%  String src = request.getParameter("src");
            String dest = request.getParameter("dest");
            String date = request.getParameter("date");
            String Class = request.getParameter("class");
            Station srcStation = StationDAO.viewStation(src);
            Station destStation = StationDAO.viewStation(dest);
            String Trip = srcStation.getCity() + "(" + srcStation.getStation_Code() + ")" + " --> " + destStation.getCity() + "(" + destStation.getStation_Code() + ")";
            User u = user;
        %>
        <%List<TrainsSchedule> intermediateStationTrainList = TrainDAO.listIntermediateTrains(src, dest, DateTime.getDateTime(date, DateTime.DATE_TIME_yyyyMMdd, DateTime.DATE_TIME_ddMMyyyy));%>
        <%List<TrainsSchedule> directStationTrainList = TrainDAO.getDirectTrainListBetweenStations(src, dest, DateTime.getDateTime(date, DateTime.DATE_TIME_yyyyMMdd, DateTime.DATE_TIME_ddMMyyyy));%>
        <!--Java Init Section End-->


        <div class="container-fluid w-100">
            <div class="form-row my-3 mx-4 px-0">
                <div class="col-md-6 col-lg form-group">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="From">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-location-arrow"></i></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg form-group">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="To">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-location-arrow"></i></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-lg form-group">
                    <div class="input-group mb-3">
                        <input type="date" class="form-control" data-date-inline-picker="true">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-lg form-group">
                    <input type="text" class="form-control">
                </div>
                <div class="col-md-4 col-lg form-group">
                    <button class="btn PrimaryColor w-100 text-white">Search</button>
                </div>
            </div>
            <div class="row my-3 mx-0 px-0">
                <!--Search Filters-->
                <div class="col-3">
                    <div class="container-fluid">
                        <div class="card">
                            <div class="card-body">
                                <form>
                                    <div class="container-fluid mx-0 px-0 mb-4">
                                        <h5>Quick Filters</h5>

                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chkAC">
                                            <label class="custom-control-label" for="chkAC">AC</label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chkAvailable">
                                            <label class="custom-control-label" for="chkAvailable">Available</label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chkDep6PM">
                                            <label class="custom-control-label" for="chkDep6PM">Departure after 6 PM</label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chkDep12PM">
                                            <label class="custom-control-label" for="chkDep12PM">Departure after 12 PM</label>
                                        </div>
                                    </div>

                                    <div class="container-fluid mx-0 px-0 mb-4">
                                        <h5 class="mb-2">Journey Class Filters</h5>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chk2A">
                                            <label class="custom-control-label" for="chk2A">2 Tier AC - <small class="text-sm">2A</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chk3A">
                                            <label class="custom-control-label" for="chk3A">3 Tier AC - <small class="text-sm">3A</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chkSL">
                                            <label class="custom-control-label" for="chkSL">Sleeper - <small class="text-sm">SL</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chk2S">
                                            <label class="custom-control-label" for="chk2S">Second Seating - <small class="text-sm">2S</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input class="custom-control-input" type="checkbox" id="chk1A">
                                            <label class="custom-control-label" for="chk1A">1st AC - <small class="text-sm">1A</small></label>
                                        </div>
                                    </div>

                                    <div class="container-fluid mx-0 px-0 mb-4">
                                        <h5 class="mb-2">Arrival in <%=srcStation.getCity()%></h5>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkArrivalNight">
                                            <label class="custom-control-label" for="chkArrivalNight">Night - <small class="text-sm">12 AM  to 6 AM</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkArrivalMorning">
                                            <label class="custom-control-label" for="chkArrivalMorning">Morning - <small class="text-sm">6 AM  to 12 PM</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkArrivalAfternoon">
                                            <label class="custom-control-label" for="chkArrivalAfternoon">Afternoon - <small class="text-sm">12 PM  to 6 PM</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkArrivalEvening">
                                            <label class="custom-control-label" for="chkArrivalEvening">Evening - <small class="text-sm">6 PM  to 12 AM</small></label>
                                        </div>
                                    </div>

                                    <div class="container-fluid mx-0 px-0 mb-4">
                                        <h5 class="mb-2">Departure from <%=destStation.getCity()%></h5>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkDepartureNight">
                                            <label class="custom-control-label" for="chkDepartureNight">Night - <small class="text-sm">12 AM  to 6 AM</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkDepartureMorning">
                                            <label class="custom-control-label" for="chkDepartureMorning">Morning - <small class="text-sm">6 AM  to 12 PM</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkDepartureAfternoon">
                                            <label class="custom-control-label" for="chkDepartureAfternoon">Afternoon - <small class="text-sm">12 PM  to 6 PM</small></label>
                                        </div>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="chkDepartureEvening">
                                            <label class="custom-control-label" for="chkDepartureEvening">Evening - <small class="text-sm">6 PM  to 12 AM</small></label>
                                        </div>


                                    </div>

                                    <div class="container-fluid mx-0 px-0 mb-4">
                                        <h5 class="mb-2">Train Types</h5>
                                        <%List<TrainCategory> trainCategoryList = TrainDAO.listTrainCategories();%>
                                        <%for (TrainCategory trainCategory : trainCategoryList) {%>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="<%=trainCategory.getTRAIN_CATEGORY_ID()%>">
                                            <label class="custom-control-label" for="<%=trainCategory.getTRAIN_CATEGORY_ID()%>"><%=trainCategory.getCATEGORY_NAME()%></label>
                                        </div>

                                        <%}%>
                                    </div>

                                    <div class="container-fluid mx-0 px-0 mb-4">
                                        <h5 class="mb-2">Stations in <%=destStation.getCity()%></h5>
                                        <%List<Station> depStationlist = StationDAO.getStationsListByCityOrStateName(destStation.getCity());%>
                                        <%for (Station s : depStationlist) {%>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="<%=s.getStation_Code()%>">
                                            <label class="custom-control-label" for="<%=s.getStation_Code()%>"><%=s.getStation_Name()%> - <small class="text-sm"><%=s.getStation_Code()%></small></label>
                                        </div>
                                        <%}%>
                                    </div>
                                    <div class="container-fluid mx-0 px-0 mb-4">
                                        <h5>Stations in <%=srcStation.getCity()%></h5>
                                        <%List<Station> srcStationlist = StationDAO.getStationsListByCityOrStateName(srcStation.getCity());%>
                                        <%for (Station s : srcStationlist) {%>
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <input type="checkbox" class="custom-control-input" id="customControlInline">
                                            <label class="custom-control-label" for="customControlInline"><%=s.getStation_Name()%> - <small class="text-sm"><%=s.getStation_Code()%></small></label>
                                        </div>
                                        <%}%>
                                    </div>
                                    <button type="button" class="btn PrimaryColor text-white btn-sm w-100 mb-2">Apply Filters</button>
                                    <button class="btn btn-outline-dark btn-sm w-100" type="reset">Reset Filters</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Search Results-->
                <div class="col-9">
                    <div class="container-fluid">
                        <div class="container-fluid m-0 p-0">
                            <div class="row">
                                <div class="col">
                                    <div class="text-center"><h4><%=Trip%></h4></div>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col">
                                    <div class="card PrimaryColor text-white d-flex justify-content-center card-shadow"> 
                                        <div class="container my-2">
                                            <div class="row">
                                                <div class="col d-flex justify-content-between train-availability-dates" style="">
                                                    <%List<DateTime> list = DateTime.listDatesupto15Days();%>
                                                    <%for (DateTime dt : list) {%>
                                                    <div class="mx-1">
                                                        <a href="Search-Results.jsp?src=<%=src%>&dest=<%=dest%>&date=<%=dt.getValue()%>&class=<%=Class%>"  class="btn btn-outline-info link">
                                                            <%=dt.getDate()%>
                                                        </a>
                                                    </div>
                                                    <%}%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>   

                            <div class="row mt-3">
                                <div class="col">
                                    <div class="card PrimaryColor text-white d-flex justify-content-center card-shadow"> 
                                        <div class="container my-2 justify-content-center">
                                            <div class="row text-center">
                                                <div class="col-5"><h6><i class="fa fa-train" aria-hidden="true"></i>&nbsp;Train Name/No</h6></div>
                                                <div class="col-2"><h6><i class="fa fa-arrow-circle-o-up" aria-hidden="true"></i>&nbsp;Departure</h6></div>
                                                <div class="col-3"><h6><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;Trip Duration / Distance</h6></div>
                                                <div class="col-2"><h6><i class="fa fa-arrow-circle-o-down" aria-hidden="true"></i>&nbsp;Arrival</h6></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <!--Direct Trains-->  

                        <div class="container mt-4 m-0 p-0">
                            <div class="row">
                                <div class="col-12">
                                    <h6>Direct Trains</h6>
                                    <%for (TrainsSchedule ts : directStationTrainList) {
                                            if (ts != null) {%>
                                    <%Boolean trainCancelled = TrainDAO.verifyIfTrainIsCancelled(date, ts.getTrainNo());%>
                                    <%List<TrainFare> tf = CoachDAO.getFareList(ts.getTrainNo());%>
                                    <div class="card m-0 p-0">
                                        <%if (trainCancelled) {%>
                                        <span class="badge badge-danger">Train Cancelled</span>
                                        <%}%>
                                        <div class="card-header PrimaryColor text-white">

                                            <div class="row">
                                                <div class="col-5 text-center">
                                                    <a class="link" href="Train-Info.jsp?TrainNo=<%=ts.getTrainNo()%>">
                                                        <div class="train-name"><%=ts.getTrainName()%></div>
                                                        <div class="train-no"><%=ts.getTrainNo()%></div>
                                                    </a>
                                                    <div class="train-schedule mx-3 px-4">
                                                        <ul class="week text-center">
                                                            <%if (ts.getWeek().getMon()) {%>
                                                            <li class="available">M</li>
                                                                <%} else {%>
                                                            <li>M</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getTue()) {%>
                                                            <li class="available">T</li>
                                                                <%} else {%>
                                                            <li class="">T</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getWed()) {%>
                                                            <li class="available">W</li>
                                                                <%} else {%>
                                                            <li>W</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getThu()) {%>
                                                            <li class="available">T</li>
                                                                <%} else {%>
                                                            <li>T</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getFri()) {%>
                                                            <li class="available">F</li>
                                                                <%} else {%>
                                                            <li>F</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getSat()) {%>
                                                            <li class="available">S</li>
                                                                <%} else {%>
                                                            <li>S</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getSun()) {%>
                                                            <li class="available sun">S</li>
                                                                <%} else {%>
                                                            <li class="sun">S</li>
                                                                <%}%>
                                                        </ul>
                                                    </div>

                                                    <a class="link" href="Train-Info.jsp?TrainNo=<%=ts.getTrainNo()%>">View Time Table ></a>

                                                </div>
                                                <div class="col-2 text-center">
                                                    <div class="time"><%=DateTime.filterDate(ts.getDeparture())%></div>
                                                    <a class="link" href="Station-Info.jsp?station_code=<%=ts.getSourceStationCode()%>">
                                                        <div class="station-code"><%=ts.getSourceStationCode()%></div>
                                                        <div class="station-name"><%=ts.getSourceStation()%></div>
                                                    </a>
                                                </div>
                                                <div class="col-3 pt-1">
                                                    <div class="duration text-center"><i class="fa fa-clock-o"></i><%=ts.getTripDuration()%></div>
                                                    <div class="d-flex align-items-center">
                                                        <div class="circle"></div>
                                                        <div class="duration-design"></div>
                                                        <div class="circle"></div>
                                                    </div>
                                                    <div class="distance text-center"><i class="fa fa-map-marker"></i><%=ts.getDistance()%> KM</div>
                                                </div>
                                                <div class="col-2 text-center">
                                                    <div class="time"><%=DateTime.filterDate(ts.getArrival())%></div>
                                                    <a class="link" href="Station-Info.jsp?station_code=<%=ts.getDestinationStationCode()%>">
                                                        <div class="station-code"><%=ts.getDestinationStationCode()%></div>
                                                        <div class="station-name"><%=ts.getDestinationStation()%></div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col d-flex">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <%for (TrainFare fare : tf) {%>
                                                                <div class="col">
                                                                    <div class="card" style="width:fit-content; height: fit-content">
                                                                        <div class="card-header d-flex justify-content-between">
                                                                            <strong><%=fare.getCoach().getCLASS_CODE()%></strong> 
                                                                            <strong>₹&nbsp;<%double amt = Fare.getFare(ts.getDistance(), fare.getFARE_PER_KM(), fare.getMINIMUM_DISTANCE(), fare.getMINIMUM_FARE());%>
                                                                                <%if (amt <= 0) {%>
                                                                                N.A
                                                                                <%} else {%>
                                                                                <%=Math.round(amt)%>
                                                                                <%}%>
                                                                            </strong>
                                                                        </div>
                                                                        <div class="card-body text-center">
                                                                            <div class="seatAvailable">
                                                                                <%TicketAvailability ta = ReservationDAO.checkAvailableSeats(ts.getTrainNo(), fare.getCoach().getCOACH_ID(), date);%>
                                                                                <%=ta.getAvailabilityMessage()%> - <%=ta.getTicketCount()%>
                                                                            </div>
                                                                        </div>
                                                                        <div class="card-footer text-center">
                                                                            <%if (!trainCancelled) {%>
                                                                            <%if (u != null) {%>
                                                                            <form method="POST" action="TicketServlet">
                                                                                <input type="hidden" name="class" value="<%=fare.getCoach().getCLASS_CODE()%>">
                                                                                <input type="hidden" name="date" value="<%=date%>">
                                                                                <input type="hidden" name="TrainNo" value="<%=ts.getTrainNo()%>">
                                                                                <input type="hidden" name="farePrice" value="<%=Math.round(amt)%>">
                                                                                <input type="hidden" name="src" value="<%=ts.getSourceStationCode()%>">
                                                                                <input type="hidden" name="dest" value="<%=ts.getDestinationStationCode()%>">
                                                                                <input type="hidden" name="user" value="<%=u.getUserID()%>">
                                                                                <button type="submit" class="btn btn-outline-info btn-sm">Book Now</button>
                                                                            </form>    
                                                                            <%} else {%>
                                                                            <button data-target="#loginModal" data-toggle="modal" class="btn btn-outline-info btn-sm">Book Now</button>
                                                                            <%}%>

                                                                            <%} else {%>
                                                                            <button type="submit" class="btn btn-outline-info btn-sm disabled book-btn">Book Now</button>
                                                                            <%}%>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <%}%>
                                                            </div>
                                                        </div>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <div class="container-fluid">
                                                <button class="btn text-white PrimaryColor" type="button" data-toggle="collapse" data-target="#train-schedule" aria-expanded="false" aria-controls="train-schedule">
                                                    View 7 Days Schedule ->
                                                </button>
                                                <div class="collapse mt-2" id="train-schedule">
                                                    <div class="card card-body">
                                                        <table class="table table-responsive-md table-hover text-center">
                                                            <tr>
                                                                <td></td>
                                                                <%for (TrainFare fare : tf) {%>
                                                                <th><%=fare.getCoach().getCLASS_CODE()%></th>
                                                                <%}%>
                                                            </tr>
                                                            <%List<DateTime> scheduleOf7Days = DateTime.listDatesUpto7Days(ts.getWeek());%>
                                                            <%for (DateTime dateTime : scheduleOf7Days) {%>
                                                            <tr>
                                                                <td><%=dateTime.getDate()%></td>
                                                                <%for (TrainFare fare : tf) {%>
                                                                <td>
                                                                    <%TicketAvailability ta = ReservationDAO.checkAvailableSeats(ts.getTrainNo(), fare.getCoach().getCOACH_ID(), dateTime.getDate());%>
                                                                    <%=ta.getAvailabilityMessage()%> - <%=ta.getTicketCount()%>
                                                                </td>


                                                                <%}%>

                                                            </tr>
                                                            <%}%>

                                                        </table>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <%}%>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <!--Direct Trains Section End-->

                        <!--Intermediate Trains Section-->
                        <div class="container mt-4 m-0 p-0">
                            <div class="row">
                                <div class="col-12">
                                    <h6>Intermediate Trains</h6>
                                    <%for (TrainsSchedule ts : intermediateStationTrainList) {
                                            if (ts != null) {%>
                                    <%Boolean trainCancelled = TrainDAO.verifyIfTrainIsCancelled(date, ts.getTrainNo());%>
                                    <%List<TrainFare> tf = CoachDAO.getFareList(ts.getTrainNo());%>
                                    <div class="card m-0 p-0">
                                        <%if (trainCancelled) {%>
                                        <span class="badge badge-danger">Train Cancelled</span>
                                        <%}%>
                                        <div class="card-header PrimaryColor text-white">
                                            <div class="row">
                                                <div class="col-5 text-center">
                                                    <a class="link " href="Train-Info.jsp?TrainNo=<%=ts.getTrainNo()%>">
                                                        <div class="train-name"><%=ts.getTrainName()%></div>
                                                        <div class="train-no"><%=ts.getTrainNo()%></div>
                                                    </a>
                                                    <div class="train-schedule mx-3 px-4">
                                                        <ul class="week text-center">
                                                            <%if (ts.getWeek().getMon()) {%>
                                                            <li class="available">M</li>
                                                                <%} else {%>
                                                            <li>M</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getTue()) {%>
                                                            <li class="available">T</li>
                                                                <%} else {%>
                                                            <li class="">T</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getWed()) {%>
                                                            <li class="available">W</li>
                                                                <%} else {%>
                                                            <li>W</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getThu()) {%>
                                                            <li class="available">T</li>
                                                                <%} else {%>
                                                            <li>T</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getFri()) {%>
                                                            <li class="available">F</li>
                                                                <%} else {%>
                                                            <li>F</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getSat()) {%>
                                                            <li class="available">S</li>
                                                                <%} else {%>
                                                            <li>S</li>
                                                                <%}%>
                                                                <%if (ts.getWeek().getSun()) {%>
                                                            <li class="available sun">S</li>
                                                                <%} else {%>
                                                            <li class="sun">S</li>
                                                                <%}%>
                                                        </ul>
                                                    </div>

                                                    <a class="link" href="Train-Info.jsp?TrainNo=<%=ts.getTrainNo()%>">View Time Table ></a>

                                                </div>
                                                <div class="col-2 text-center">
                                                    <div class="time"><%=DateTime.filterDate(ts.getDeparture())%></div>
                                                    <a class="link" href="Station-Info.jsp?station_code=<%=ts.getSourceStationCode()%>">
                                                        <div class="station-code"><%=ts.getSourceStationCode()%></div>
                                                        <div class="station-name"><%=ts.getSourceStation()%></div>
                                                    </a>
                                                </div>
                                                <div class="col-3 pt-1">
                                                    <div class="duration text-center"><i class="fa fa-clock-o"></i><%=ts.getTripDuration()%></div>
                                                    <div class="d-flex align-items-center">
                                                        <div class="circle"></div>
                                                        <div class="duration-design"></div>
                                                        <div class="circle"></div>
                                                    </div>
                                                    <div class="distance text-center"><i class="fa fa-map-marker"></i><%=ts.getDistance()%> KM</div>
                                                </div>
                                                <div class="col-2 text-center">
                                                    <div class="time"><%=DateTime.filterDate(ts.getArrival())%></div>
                                                    <a class="link" href="Station-Info.jsp?station_code=<%=ts.getDestinationStationCode()%>">
                                                        <div class="station-code"><%=ts.getDestinationStationCode()%></div>
                                                        <div class="station-name"><%=ts.getDestinationStation()%></div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col d-flex">
                                                        <%for (TrainFare fare : tf) {%>
                                                        <div class="card mr-4" style="width: 180px;">
                                                            <div class="card-header d-flex justify-content-between">
                                                                <strong><%=fare.getCoach().getCLASS_CODE()%></strong> 
                                                                <strong>₹&nbsp;
                                                                    <%double amt = Fare.getFare(ts.getDistance(), fare.getFARE_PER_KM(), fare.getMINIMUM_DISTANCE(), fare.getMINIMUM_FARE());%>
                                                                    <%if (amt <= 0) {%>
                                                                    N.A
                                                                    <%} else {%>
                                                                    <%=Math.round(amt)%>
                                                                    <%}%>
                                                                </strong>
                                                            </div>
                                                            <div class="card-body text-center">
                                                                <div class="seatAvailable">
                                                                    <%TicketAvailability ta = ReservationDAO.checkAvailableSeats(ts.getTrainNo(), fare.getCoach().getCOACH_ID(), date);%>
                                                                    <%=ta.getAvailabilityMessage()%> - <%=ta.getTicketCount()%>
                                                                </div>
                                                            </div>
                                                            <div class="card-footer text-center">

                                                                <%if (!trainCancelled) {%>
                                                                <%if (u != null) {%>
                                                                <form method="POST" action="TicketServlet">
                                                                    <input type="hidden" name="class" value="<%=fare.getCoach().getCLASS_CODE()%>">
                                                                    <input type="hidden" name="date" value="<%=date%>">
                                                                    <input type="hidden" name="TrainNo" value="<%=ts.getTrainNo()%>">
                                                                    <input type="hidden" name="farePrice" value="<%=Math.round(amt)%>">
                                                                    <input type="hidden" name="src" value="<%=ts.getSourceStationCode()%>">
                                                                    <input type="hidden" name="dest" value="<%=ts.getDestinationStationCode()%>">
                                                                    <input type="hidden" name="user" value="<%=u.getUserID()%>">
                                                                    <button type="submit" class="btn btn-outline-info btn-sm">Book Now</button>
                                                                </form>    
                                                                <%} else {%>
                                                                <button data-target="#loginModal" data-toggle="modal" class="btn btn-outline-info">Book Now</button>
                                                                <%}%>

                                                                <%} else {%>
                                                                <button type="submit" class="btn btn-outline-info btn-sm disabled book-btn">Book Now</button>
                                                                <%}%>
                                                            </div>
                                                        </div>
                                                        <%}%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%}
                                        }%>
                                </div>
                            </div>
                        </div>
                        <!--Intermediate Trains Section End-->

                        <div class="container mt-3 mx-0 px-0">
                            <div class="card PrimaryColor text-white d-flex justify-content-center" style="box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                                 -webkit-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                                 -moz-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);height: 40px;width: 18rem;">
                                <div class="container">
                                    <h6>More Trains on alternative days</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="snackbar"></div>
        </div>
        <%} catch (Exception e) {%>
        Nothing to show

        <%=e.getMessage()%>
        <%}%>


        <%@ include file="CommonJS.jsp"%>
        <script>
            $(window).ready(function () {

                $('.book-btn').on('click', function () {
                    $('#snackbar').html('Train is Cancelled');
                    $('#snackbar').addClass('show');
                    setTimeout(function () {
                        $('#snackbar').removeClass("show");
                    }, 3000);
                });
            });
        </script>
    </body>
</html>
