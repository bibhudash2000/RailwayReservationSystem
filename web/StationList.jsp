<%@page import="com.RRS.Models.Station"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Station List</title>
        <%@include file="CommonCSS.jsp" %>
    </head>
    <body>


        <div class="container">
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>
                                EXPORTABLE TABLE
                            </h2>
                            <ul class="header-dropdown m-r--5">
                                <li class="dropdown">
                                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">more_vert</i>
                                    </a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:void(0);">Action</a></li>
                                        <li><a href="javascript:void(0);">Another action</a></li>
                                        <li><a href="javascript:void(0);">Something else here</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped table-hover dataTable js-exportable">
                                    <thead>
                                        <tr>
                                            <th>Station ID</th>
                                            <th>Station Code</th>
                                            <th>Station Name</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Station ID</th>
                                            <th>Station Code</th>
                                            <th>Station Name</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <%List<Station> list = StationDAO.getStationsList();%>
                                        <%for (Station s : list) {%>
                                        <tr>
                                            <td><%=s.getStation_ID()%></td>
                                            <td><%=s.getStation_Code()%></td>
                                            <td><%=s.getStation_Name()%></td>
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



        <!--Javascript-->

        <%@include file="CommonJS.jsp" %>

        <!--Javascript End-->
    </body>

</html>