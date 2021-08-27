<%@page import="com.RRS.Models.Zone"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.ZoneDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../CommonCSS.jsp" %>
        <title>New Station</title>
    </head>
    <body>
        <%@include file="../navbar.jsp" %>
        <div class="container-fluid d-flex justify-content-center">
            <div class="card my-3" style="width: 30rem;">
                <div class="card-header PrimaryColor text-white">
                    New Station
                </div>
                <div class="card-body">
                    <form class="row g-3" method="POST" action="<%=request.getContextPath()%>/AddNewStationServlet">
                        <div class="col-md-6">
                            <label for="stationCode" class="form-label">Station Code</label>
                            <input type="text" name="stationCode" class="form-control" id="stationCode" placeholder="e.g., ROU">
                        </div>
                        <div class="col-md-6">
                            <label for="stationName" class="form-label">Station Name</label>
                            <input type="text" name="stationName" class="form-control" id="stationName" placeholder="e.g., Rourkela Junction">
                        </div>
                        <div class="col-12">
                            <label for="city" class="form-label">City</label>
                            <input type="text" name="city" class="form-control" id="city" placeholder="e.g., Rourkela">
                        </div>
                        <div class="col-12">
                            <label for="state" class="form-label">State</label>
                            <input type="text" name="state" class="form-control" id="state" placeholder="e.g., Odisha">
                        </div>
                        <div class="col-md-6">
                            <label for="shortDesc" class="form-label">Short Description</label>
                            <textarea class="form-control" name="shortDesc" id="shortDesc"></textarea>
                        </div>
                        <div class="col-md-6">
                            <label for="zone" class="form-label">Zone</label>
                            <select id="zone" name="zone" class="form-select">
                                <option selected disabled >Choose Zone</option>
                                <%List<Zone> zoneList = ZoneDAO.getZoneList();%>
                                <%for (Zone z : zoneList) {%>
                                <option value="<%=z.getZone_ID()%>"><%=z.getZone_Name()%>&nbsp;(<%=z.getZone_Abbreviation()%>)</option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn PrimaryColor text-white">Save Changes</button>
                            <a href="<%=request.getContextPath()%>/AdminHomeDirectory" type="button" class="btn btn-outline-dark">Cancel</a>
                        </div>
                    </form>
                </div>
                <div class="card-footer">

                </div>
            </div>
        </div>


        <%@include file="../CommonJS.jsp" %>
    </body>
</html>
