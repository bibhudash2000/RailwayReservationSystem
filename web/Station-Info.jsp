<%@page import="java.util.List"%>
<%@page import="com.RRS.Models.Train"%>
<%@page import="com.RRS.Models.Station"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%String StationCode;
    Station s;
    List<Train> passingByTrainList;
    List<Train> startingTrainList;

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="CommonCSS.jsp" %>
        <title>Station-Info</title>
    </head>
    <body>
        <!--Navbar-->
        <%@include file="navbar.jsp" %>
        <!--Navbar End-->
        <%try {
                StationCode = request.getParameter("station_code");
                s = StationDAO.viewStationWithDesc(StationCode);
                passingByTrainList = StationDAO.listStationPasstingByTrains(StationCode);
                startingTrainList = StationDAO.listTrainsStartingFromStation(StationCode);
                if (s != null) {%>

        <!--Code starts from here-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="card mt-3" style="box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                         -webkit-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);
                         -moz-box-shadow: -1px 4px 6px 0px rgba(0,0,0,0.83);">
                        <div class="card-header PrimaryColor text-center text-white">
                            <i class="fa fa-building fa-2x" aria-hidden="true"></i>
                            <h3><%=s.getStation_Name()%></h3>
                            <div class="row">
                                <div class="col"><small>City Name : <%=s.getCity()%></small></div>
                                <div class="col"><small>Station code : <%=s.getStation_Code()%></small></div>
                                <div class="col"><small>State : <%=s.getState()%></small></div>
                            </div>

                        </div>
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <div class="card">
                                            <div class="card-body">
                                                <strong class="stn-Name">About <%=s.getStation_Name()%> :</strong>
                                                <p class="text-muted mt-3"><%=s.getShort_Description()%></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row mt-3">
                                    <div class="col">
                                        <div class="card">
                                            <div class="card-header PrimaryColor text-white"><strong>Trains Passes Through <%=s.getStation_Name()%> :</strong></div>
                                            <div class="card-body">
                                                <div class="container d-flex">
                                                    <%for (Train t : passingByTrainList) {%>
                                                    <%if (t != null) {%>
                                                    <a class="element text-center" href="Train-Info.jsp?TrainNo=<%=t.getTrainNo()%>">
                                                        <%=t.getTrainName()%>
                                                        (<%=t.getTrainNo()%>)
                                                    </a>
                                                    <%} else {%>
                                                    <p class="text-muted">No Trains Passes from this Station</p>

                                                    <%}%>
                                                    <%}%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row mt-3">
                                    <div class="col">
                                        <div class="card">
                                            <div class="card-header PrimaryColor text-white"><strong>Trains starting from <%=s.getStation_Name()%> :</strong></div>
                                            <div class="card-body">
                                                <div class="container d-flex">
                                                    <%for (Train t : startingTrainList) {%>

                                                    <%if (t != null) {%>
                                                    <a class="element text-center" href="Train-Info.jsp?TrainNo=<%=t.getTrainNo()%>">
                                                        <%=t.getTrainName()%>
                                                        (<%=t.getTrainNo()%>)
                                                    </a>
                                                    <%} else {%>
                                                    <p class="">No Trains Starts from this Station</p>
                                                    <%}%>
                                                    <%}%>
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
        Information NA
        <%}
        } catch (Exception e) {%>
        <h3><%=e.getMessage()%></h3>
        <%}%>



        <%@include file="CommonJS.jsp" %>
    </body>
</html>
