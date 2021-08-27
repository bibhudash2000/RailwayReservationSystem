<%@page import="com.RRS.DAO.StationDAO"%>
<%@page import="com.RRS.Models.Station"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>JSP Page</title>
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
                                <table class="field_wrapper table table-bordered text-center">
                                    <tr>
                                        <th>Station</th>
                                        <th>Departure</th>
                                        <th>Arrival</th>
                                        <th>Halt</th>
                                        <th>Distance</th>
                                        <th>Day</th>
                                        <th>Actions</th>
                                    </tr>
                                    <tr class="form-group">
                                        <td><select name="station" class="station">
                                                <option selected disabled>Select Station</option>
                                                <%for (Station s : sList) {%>
                                                <option value="<%=s.getStation_ID()%>"><%=s.getCity() + " (" + s.getStation_Code() + ")"%></option>
                                                <%}%>
                                            </select>
                                        </td>
                                        <td><input type="time" name="departure"  class="form-control" required></td>
                                        <td><input type="time" name="arrival"  class="form-control" required></td>
                                        <td><input type="text" name="halt"  class="form-control" required></td>
                                        <td><input type="number" name="distance" class="form-control" required></td>
                                        <td><input type="number" name="Day" class="form-control" required></td>

                                        <td><button class="btn PrimaryColor text-white add_button"><i class="fa fa-plus"></i></button></td>
                                    </tr>
                                </table>
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
            $('.station').chosen();

            $(document).ready(function () {
                var maxField = 50; //Input fields increment limitation
                var addButton = $('.add_button'); //Add button selector
                var wrapper = $('.field_wrapper'); //Input field wrapper
                var fieldHTML = '<tr class="form-group">'+
        '<td><select name="station" class="station">'+
        '<option selected disabled>Select Station</option>'+
        '<%for (Station s : sList) {%>'+
        '<option value="<%=s.getStation_ID()%>"><%=s.getCity() + " (" + s.getStation_Code() + ")"%></option>'+
        '<%}%>'+
        '</select>'+
        '</td>' +
            
        '<td><button class="btn PrimaryColor text-white remove_button"><i class="fa fa-minus"></i></button></td>'+
        '</tr>'; //New input field html 
                var x = 1; //Initial field counter is 1

                //Once add button is clicked
                $(addButton).click(function (e) {
                    //Check maximum number of input fields
                    e.preventDefault();
                    if (x < maxField) {
                        x++; //Increment field counter
                        $(wrapper).append(fieldHTML); //Add field html
                    }
                });

                //Once remove button is clicked
                $(wrapper).on('click', '.remove_button', function (e) {
                    e.preventDefault();
                    $(this).parents('tr').remove(); //Remove field html
                    x--; //Decrement field counter
                });
            });

        </script>
    </body>
</html>
