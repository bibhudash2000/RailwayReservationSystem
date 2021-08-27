<%@page import="com.RRS.Models.Reservation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Reservation r = (Reservation) session.getAttribute("NewReservation");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="CommonCSS.jsp" %>
        <title>Add-Passengers</title>
    </head>
    <body>
        <!--Navbar-->
        <%@include file="navbar.jsp"%>
        <!--Navbar End-->

        <div class="container mt-3">
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <form action="AddPassengersServlet" method="POST">

                        <input type="hidden" value="<%=r.getBookedBy()%>" name="UserID">
                        <input type="hidden" value="<%=r.getClassType()%>" name="Class">
                        <input type="hidden" value="<%=r.getDateOfJourney()%>" name="Date">
                        <input type="hidden" value="<%=r.getTrainNo()%>" name="TrainNo">
                        <input type="hidden" value="<%=r.getSourceStation()%>" name="SrcStationCode">
                        <input type="hidden" value="<%=r.getDestinationStation()%>" name="DestStationCode">
                        <div class="card" style="width: 800px;">
                            <div class="card-header PrimaryColor text-center text-white">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col text-center">
                                            <h5><i class="fa fa-user-plus"></i>&nbsp;Add Passengers</h5>
                                        </div>
                                    </div>    
                                    <div class="row">
                                        <div class="col d-flex justify-content-end"> 
                                            <i class="fa fa-info-circle fa-2x" data-placement="right" data-toggle="tooltip" title="Some tooltip text!"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="notice">
                                    <span style="color: red; font-weight: bold;">*</span>
                                    <strong>Note :</strong>
                                    <small class="text-muted">
                                        <ol>
                                            <li>The Name must be same as on Aadhar card.</li>
                                            <li>In a ticket, you can add upto 6 passengers only.</li>
                                        </ol>

                                    </small>
                                </div>

                                <table class="field_wrapper table table-bordered text-center">
                                    <tr>
                                        <th>#</th>
                                        <th>Full Name of Passenger</th>
                                        <th>Age</th>
                                        <th>Gender</th>
                                        <th>Actions</th>
                                    </tr>
                                    <tr class="form-group">
                                        <td>1</td>
                                        <td><input type="text" name="Name"  class="form-control" required></td>
                                        <td><input type="number" name="Age" class="form-control" required></td>
                                        <td><select name="Gender" class="form-control">
                                                <option value="Male">Male</option>
                                                <option value="Female">Female</option>
                                            </select>
                                        </td>
                                        <td><button class="btn PrimaryColor text-white add_button"><i class="fa fa-plus"></i></button></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="card-footer text-center">
                                <button type="submit" class="btn PrimaryColor text-white">Proceed to Pay</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <%@include file="CommonJS.jsp" %>
        <script>

            $(document).ready(function () {
                var maxField = 6; //Input fields increment limitation
                var addButton = $('.add_button'); //Add button selector
                var wrapper = $('.field_wrapper'); //Input field wrapper
                var x = 1; //Initial field counter is 1



                //Once add button is clicked
                $(addButton).click(function (e) {
                    //Check maximum number of input fields
                    e.preventDefault();
                    if (x < maxField) {
                        x++; //Increment field counter
                        var fieldHTML = '<tr class="form-group"><td>' + (x) + '</td><td><input type="text" name="Name" class="form-control"></td><td><input type="number" name="Age" class="form-control"></td><td><select name="Gender" class="form-control">' +
                                '<option value="Male">Male</option><option value="Female">Female</option></select></td>' +
                                '<td><button class="btn btn-danger remove_button"><i class="fa fa-trash"></i></button></td></tr>'; //New input field html 
                        $(wrapper).append(fieldHTML); //Add field html

                    } else if (x >= maxField) {
                        $(addButton).attr('disabled', 'true');
                    }
                });

                //Once remove button is clicked
                $(wrapper).on('click', '.remove_button', function (e) {
                    e.preventDefault();
                    $(addButton).removeAttr('disabled');
                    $(this).parents('tr').remove(); //Remove field html
                    x--; //Decrement field counter
                });
            });
        </script>
    </body>
</html>
