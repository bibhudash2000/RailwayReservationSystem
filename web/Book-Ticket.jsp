<%@page import="com.RRS.Models.RecentTrainSearch"%>
<%@page import="com.RRS.DAO.RecentTrainSearchDAO"%>
<%@page import="com.RRS.DAO.TrainDAO"%>
<%@page import="com.RRS.Utilities.DateTime"%>
<%@page import="com.RRS.Models.Coach"%>
<%@page import="com.RRS.Models.CheckAvailableCoaches"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.RRS.Models.TrainsSchedule"%>
<%@page import="com.RRS.Models.Message"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%@page import="com.RRS.Models.Station"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="CommonCSS.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <!--Navbar-->
        <%@include file="navbar.jsp" %>
        <!--Navbar End-->
        <%try {%>
        <%List<Station> sList = StationDAO.getStationsList();%>
        <%Message msg = (Message) session.getAttribute("msg");%>




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
                <div class="col-12">
                    <div class="card d-flex justify-content-center card-shadow-1" style="">

                        <div class="card-header"></div>
                        <div class="card-body">
                            <form id="form" method="POST" action="SearchTrainsServlet">
                                <div class="container" style="">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <!--Source-->
                                            <label for="selSource"><h6>Source</h6></label>

                                            <select id="selSource" name="sourceStation" class="" required>
                                                <option selected disabled value="-1">Select Source</option>
                                                <%for (Station s : sList) {%>
                                                <option value="<%=s.getStation_Code()%>"><%=s.getCity() + " (" + s.getStation_Code() + ")"%></option>
                                                <%}%>

                                            </select>


                                        </div>
                                        <div class="col-md-6">
                                            <!--Destination-->
                                            <label for="selDestination"><h6>Destination</h6></label>
                                            <select id="selDestination" name="destinationStation" required>
                                                <option disabled selected value="-1">Select Destination</option>
                                                <%for (Station s : sList) {%>
                                                <option value="<%=s.getStation_Code()%>"><%=s.getCity() + " (" + s.getStation_Code() + ")"%></option>
                                                <%}%>
                                            </select>
                                        </div>    

                                    </div>
                                    <div class="row mt-3">
                                        <div class="col">
                                            <div class="form-inline">
                                                <label for="date"><h6>Boarding Date</h6></label>
                                                <input type="date" class="mx-3 form-control" min="<%=DateTime.getDateTime(DateTime.DATE_TIME_yyyyMMdd)%>" max="<%=DateTime.addDateUpto4Months()%>" value="<%=DateTime.getDateTime(DateTime.DATE_TIME_yyyyMMdd)%>" name="boardingDate" id="date">
                                                <label for="class"><h6>Class</h6></label>
                                                <select class="mx-3 form-control" name="class" id="class">
                                                    <option>All Classes</option>
                                                    <option>1AC</option>
                                                    <option>2AC</option>
                                                    <option>3AC</option>
                                                    <option>CC</option>
                                                    <option>SL</option>
                                                </select> 
                                            </div>
                                            <div class="container text-center mt-3">
                                                <button class="btn PrimaryColor text-white">Search Trains</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
                                                
        <%if (user != null) {%>
        <div class="container my-4">
            <h4><span class="material-icons">history</span>Recent Searches</h4>
            <div class="card card-shadow-1" style="width: fit-content">
                <%RecentTrainSearch recentSearch = RecentTrainSearchDAO.viewRecentTrainSearches(user);%>
                <%if (recentSearch != null) {%>
                <div class="card-body">

                    <div class="row">
                        <div class="col">
                            <%Station src = StationDAO.viewStation(recentSearch.getSOURCE_STATION_ID());%>
                            <div class="d-block">
                                <div class="d-block"><%=src.getStation_Code()%></div>
                                <div class="d-block"><%=src.getCity()%></div>
                            </div>
                        </div>
                        <div class="col d-flex justify-content-center">
                            <i class="fa fa-arrow-right"></i>
                        </div>
                        <div class="col">
                            <%Station dest = StationDAO.viewStation(recentSearch.getDESTINATION_STATION_ID());%>
                            <div class="d-block">
                                <div class="d-block"><%=dest.getStation_Code()%></div>
                                <div class="d-block"><%=dest.getCity()%></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer d-flex justify-content-between">
                    <label><%=DateTime.getDateTime(recentSearch.getDATE(), DateTime.DATE_TIME_yyyyMMdd, DateTime.DATE_TIME_Day_Date_Mon)%></label>
                    <label><%=recentSearch.getCLASS_TYPE()%></label>
                </div>
                <%} else {%>

                <%}%>

            </div>
        </div>
        <%}%>

        <%} catch (Exception e) {
                Message msg = new Message(e.getMessage(), Message.ALERT_DANGER);
                session.setAttribute("userMsg", msg);
                e.printStackTrace();
                response.sendRedirect("index.jsp");
            }%>



        <%@include file="CommonJS.jsp" %>
        <script>
            $('#selSource').chosen();
            $('#selDestination').chosen();
        </script>

    </body>
</html>
