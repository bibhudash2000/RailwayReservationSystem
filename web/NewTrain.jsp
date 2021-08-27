<%@page import="com.RRS.DAO.StationDAO"%>
<%@page import="com.RRS.Models.Station"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>New Train</title>
    </head>
    <body>
        <%List<Station> sList = StationDAO.getStationsList();%>
        <div class="container-fluid mt-4">
            <div class="row">
                <div class="col">
                    <div class="card">

                        <form method="post" action="NewTrainServlet">
                            <div class="card-header PrimaryColor text-center text-white">New Train</div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <div class="form-group row">
                                            <label for="trainNo" class="col-sm-4 col-form-label">Train No</label>
                                            <div class="col-sm-8">
                                                <input type="number" name="trainNo" class="form-control" id="trainNo" placeholder="Train no">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="trainName" class="col-sm-4 col-form-label">Train Name</label>
                                            <div class="col-sm-8">
                                                <input type="text" name="trainName" class="form-control" id="trainName" placeholder="Train Name">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="sourceStation" class="col-sm-4 col-form-label">Source Station</label>
                                            <div class="col-sm-8">
                                                <select id="sourceStation" name="sourceStation">
                                                    <option selected disabled>Select Source</option>
                                                    <%for (Station s : sList) {%>
                                                    <option value="<%=s.getStation_ID()%>"><%=s.getCity() + " (" + s.getStation_Code() + ")"%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="destStation" class="col-sm-4 col-form-label">Dest Station</label>
                                            <div class="col-sm-8">
                                                <select id="destStation" name="destStation">
                                                    <option selected disabled>Select Destination</option>
                                                    <%for (Station s : sList) {%>
                                                    <option value="<%=s.getStation_ID()%>"><%=s.getCity() + " (" + s.getStation_Code() + ")"%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>



                                    </div>
                                    <div class="col">
                                        <div class="form-group row">
                                            <div class="col-sm-2">Classes</div>
                                            <div class="col-sm-10">
                                                <div class="form-check">
                                                    <input class="form-check-input" name="CC" type="checkbox" id="CC">
                                                    <label class="form-check-label" for="CC">AC Chair Car</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="AC1" type="checkbox" id="AC1">
                                                    <label class="form-check-label" for="AC1">First AC</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="AC2" type="checkbox" id="AC2">
                                                    <label class="form-check-label" for="AC2">Second AC</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="AC3"  type="checkbox" id="AC3">
                                                    <label class="form-check-label" for="AC3">Third AC</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="SL" type="checkbox" id="SL">
                                                    <label class="form-check-label" for="SL">Sleeper</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="SS" type="checkbox" id="SS">
                                                    <label class="form-check-label" for="SS">Second Seating</label>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <div class="col-sm-2">Departs on</div>
                                            <div class="col-sm-10 d-flex justify-content-between align-items-center">
                                                <div class="form-check ">
                                                    <input class="form-check-input" name="Mon" type="checkbox" id="Mon">
                                                    <label class="form-check-label" for="Mon">Mon</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="Tue" type="checkbox" id="Tue">
                                                    <label class="form-check-label" for="Tue">Tue</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="Wed" type="checkbox" id="Wed">
                                                    <label class="form-check-label" for="Wed">Wed</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="Thu" type="checkbox" id="Thu">
                                                    <label class="form-check-label" for="Thu">Thu</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="Fri" type="checkbox" id="Fri">
                                                    <label class="form-check-label" for="Fri">Fri</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="Sat" type="checkbox" id="Sat">
                                                    <label class="form-check-label" for="Sat">Sat</label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="Sun" type="checkbox" id="Sun">
                                                    <label class="form-check-label" for="Sun">Sun</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>


                            <div class="card-footer text-center">
                                <div class="form-group row">
                                    <div class="col">
                                        <button type="submit" class="btn btn-primary">Add Train</button>
                                    </div>
                                </div>
                            </div>

                        </form>  
                    </div>
                </div>
            </div>
        </div>


        <%@include file="CommonJS.jsp" %>
        <script>
            $('#sourceStation').chosen();
            $('#destStation').chosen();
        </script>
    </body>
</html>
