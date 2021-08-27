<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.TrainDAO"%>
<%@page import="com.RRS.Models.Train"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Message msg = (Message) session.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@ include file="CommonCSS.jsp"%>
        <title>Train Time Table</title>
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



        <div class="container mt-3 ">
            <div class="row">
                <div class="col-12">
                    <div class="card d-flex justify-content-center card-shadow-1">
                        <div class="card-body justify-content-center d-flex">
                            <form method="POST" action="ViewTrainTimeTableServlet" class="form-inline">
                                <div class="form-group mx-sm-3 mb-2">
                                    <%List<Train> trainList = TrainDAO.getTrainList();%>
                                    <select id="txtTrainNameOrNumber" name="txtTrainNameOrNumber" autocomplete required>
                                        <option selected disabled value="-1">Select Source</option>
                                        <%for (Train train : trainList) {%>
                                        <option value="<%=train.getTrainNo()%>"><%=train.getTrainName() + " (" + train.getTrainNo() + ")"%></option>
                                        <%}%>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary mb-2"><i class="fa fa-search"></i>Search Train</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="CommonJS.jsp" %>
        <script>
            $('#txtTrainNameOrNumber').chosen();
        </script>
    </body>
</html>
