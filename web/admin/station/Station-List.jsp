<%@page import="com.RRS.Models.Station"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../CommonCSS.jsp" %>
        <title>Station List</title>
    </head>
    <body>
        <%@include file="../navbar.jsp" %>

        <div class="container-fluid d-flex justify-content-center">
            <div class="card my-3" style="width: 60rem;">
                <div class="card-header PrimaryColor text-white d-flex justify-content-between">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-8">
                                Station Lists
                            </div>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <button class="btn btn-dark text-white" type="button" id="button-addon1"><i class="fa fa-search"></i></button>
                                    <input type="search" class="form-control" placeholder="Search Stations" aria-describedby="button-addon1">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <c:out value="${contextPath}" />
                    <div class="card card-shadow PrimaryColor text-white mb-3 p-2">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2">#</div>
                                <div class="col-md-2">Station Code</div>
                                <div class="col-md-2">Station Name</div>
                                <div class="col-md-2">City</div>
                                <div class="col-md-2">State</div>
                                <div class="col-md-2">Zone</div>
                            </div>
                        </div> 
                    </div>
                    <div class="card p-2 scroll-design" style="overflow-y: scroll; height: 25rem;">
                        <c:forEach items="${stationList}" var="station">
                            <a class="card mb-2 px-2 py-2 station-list">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-md-2">${station.getStation_ID()}</div>
                                        <div class="col-md-2">${station.getStation_Code()}</div>
                                        <div class="col-md-2">${station.getStation_Name()}</div>
                                        <div class="col-md-2">${station.getCity()}</div>
                                        <div class="col-md-2">${station.getState()}</div>
                                        <div class="col-md-2">${station.getZone()}</div>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>    
                    </div>
                </div>
                <div class="card-footer">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-end">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">Next</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <%@include file="../CommonJS.jsp" %>
        <script>

        </script>
    </body>
</html>
