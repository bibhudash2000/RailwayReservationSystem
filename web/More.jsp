<%@page import="com.RRS.Utilities.StringFormatter"%>
<%@page import="com.RRS.Models.SavedUserTransactionCard"%>
<%@page import="com.RRS.DAO.SavedUserTransactionCardDAO"%>
<%@page import="com.RRS.Utilities.LoginSession"%>
<%@page import="com.RRS.Utilities.DateTime"%>
<%@page import="com.RRS.DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <%@include file="CommonCSS.jsp" %>
        <title>User Profile</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container-fluid"> 
            <div class="row PrimaryColor">
                <div class="col d-flex justify-content-center">
                    <div class="container">
                        <div class="row">
                            <div class="col d-flex justify-content-center" style="height: 160px;">
                                <div class="Profile mt-1">
                                    <div class="row">
                                        <div class="col px-0">
                                            <img src="img/icon_profile.png" class="Profile-Image">
                                        </div>
                                        <div class="col p-0">
                                            <div class="User-Name mt-5">   
                                                <label><%=user.getName()%></label>
                                            </div>
                                            <div class="User-Email">   
                                                <label><%=user.getEmail()%></label>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col PrimaryColor">
                                <ul class="nav nav-tabs text-white" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;Profile</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="privacy-tab" data-toggle="tab" href="#privacy" role="tab" aria-controls="privacy" aria-selected="false"><i class="fa fa-user-secret" aria-hidden="true"></i>&nbsp;Privacy & Security</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="account-tab" data-toggle="tab" href="#account" role="tab" aria-controls="account" aria-selected="false"><i class="fa fa-wrench" aria-hidden="true"></i>&nbsp;Account Settings</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>





                </div>
            </div>
            <div class="row mt-4">
                <div class="col">
                    <div class="container">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <h3>My Profile Information</h3>
                                <p class="text-muted">Manage your personal profile information to control what details are shared.</p>
                                <div class="container">
                                    <table class="table table-hover">
                                        <tbody>
                                            <tr>
                                                <th scope="row">Name</th>
                                                <td><%=user.getName()%></td>
                                                <td><a href="#" class="link-black"><i class="fa fa-ellipsis-v"></i></a></td>
                                            </tr>
                                            <tr>
                                                <th scope="row">Email</th>
                                                <td><%=user.getEmail()%></td>
                                                <td><a href="#" class="link-black"><i class="fa fa-ellipsis-v"></i></a></td>
                                            </tr>
                                            <tr>
                                                <th scope="row">Contact</th>
                                                <td><%=user.getContact()%></td>
                                                <td><a href="#" class="link-black"><i class="fa fa-ellipsis-v"></i></a></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="privacy" role="tabpanel" aria-labelledby="privacy-tab">
                                <h3>Privacy & Security</h3>
                                <p class="text-muted">Manage your personal profile information to control what details are shared.</p>
                                <div class="container">
                                    <table class="table table-hover">
                                        <tbody>
                                            <tr>
                                                <th scope="row">Password</th>
                                                <td>******************</td>
                                                <td><a href="#" class="link-black" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <i class="fa fa-ellipsis-v"></i>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <li class="dropdown-item">
                                                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#">Change Password</button>
                                                        </li>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th scope="row">Saved Card</th>
                                                    <%try {%>
                                                    <%SavedUserTransactionCard card = SavedUserTransactionCardDAO.getUserSavedTransactionCard(user);%>
                                                <td><%=StringFormatter.maskCardNumber(StringFormatter.formatCardNumber(card.getCARD_NUMBER()))%></td>
                                                <td><a href="#" class="link-black" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <i class="fa fa-ellipsis-v"></i>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <li class="dropdown-item">
                                                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#">Change Card</button>
                                                        </li>
                                                        <li class="dropdown-item">
                                                            <button type="button" class="btn PrimaryColor text-white btn-sm" data-toggle="modal" data-target="#">Remove Card</button>
                                                        </li>
                                                    </div>
                                                </td>
                                                <%} catch (Exception e) {%>
                                                <td>Card Not Added</td>
                                                <td><a href="#" class="link-black" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <i class="fa fa-ellipsis-v"></i>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <li class="dropdown-item">
                                                            <button type="button" class="btn PrimaryColor text-white btn-sm" data-toggle="modal" data-target="#">Add New Card</button>
                                                        </li>
                                                    </div>
                                                </td>
                                                <%}%>
                                            </tr>
                                            <tr>
                                                <th scope="row">Contact</th>
                                                <td><%=user.getContact()%></td>
                                                <td><a href="#" class="link-black"><i class="fa fa-ellipsis-v"></i></a></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="account" role="tabpanel" aria-labelledby="account-tab">
                                <h3>My Account Settings & Informations</h3>
                                <p class="text-muted">These settings can be changed later.</p>

                                <div class="container">
                                    <table class="table table-hover">
                                        <tbody>
                                            <tr>
                                                <th scope="row">Account Freeze status</th>
                                                    <%if (!user.getHasAccountFrozen()) {%>
                                                <td>
                                                    <div class="btn-group dropright">
                                                        <button type="button" class="btn btn-success">
                                                            Disabled
                                                        </button>
                                                        <button type="button" class="btn btn-success dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <span class="sr-only">Toggle Dropright</span>
                                                        </button>
                                                        <div class="dropdown-menu">
                                                            <li class="dropdown-item">
                                                                <button type="submit" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#passwordVerificationModal">Freeze my account</button>
                                                            </li>
                                                        </div>
                                                    </div>
                                                    <div class="modal fade" tabindex="-1" role="dialog" id="passwordVerificationModal" aria-labelledby="passwordVerificationModal" aria-hidden="true">
                                                        <div class="modal-dialog modal-sm">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">Verify your Identity</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form class="form-group" id="passwordVerificationForm">
                                                                        <div class="form-group mb-2">
                                                                            <input type="hidden" name="UserID" value="<%=user.getUserID()%>">
                                                                            <label for="staticEmail2" class="sr-only">Email</label>
                                                                            <input type="text" readonly class="form-control-plaintext" id="staticEmail2" value="<%=user.getEmail()%>">
                                                                        </div>
                                                                        <div class="form-group mb-2">
                                                                            <label for="inputPassword2" class="sr-only">Password</label>
                                                                            <input type="password" class="form-control" name="Password" id="Password" placeholder="Password">
                                                                        </div>
                                                                        <button id="btnLoader" type="submit" class="btn btn-primary mb-2">Confirm identity </button>
                                                                        <button id="btnReset" type="reset" class="btn btn-warning mb-2">Reset</button>
                                                                        <div id="response"></div>
                                                                    </form>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                    <button id="save" type="button" class="btn btn-primary">Save changes</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>

                                                </td>
                                                <%} else {%>
                                                <td>
                                                    <div class="btn-group dropright">
                                                        <button type="button" class="btn btn-danger">
                                                            Enabled
                                                        </button>
                                                        <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <span class="sr-only">Toggle Dropright</span>
                                                        </button>
                                                        <div class="dropdown-menu">
                                                            <li class="dropdown-item">
                                                                <a href="AccountUnlockRequest.jsp" class="btn btn-primary btn-sm">Unfreeze my account</a>
                                                            </li>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>

                                                </td>
                                                <%}%>
                                            </tr>
                                            <tr>
                                                <th scope="row">Last Login</th>
                                                <td>
                                                    <%try {%>
                                                    <%LoginSession ls = UserDAO.fetchLastLoginSession(user);%>
                                                    <label class="red">
                                                        <%=DateTime.getDateTime(ls.getLastLogin(), DateTime.DATE_TIME_yyyyMMdd_HHmmss, DateTime.DATE_TIME_HHmm_Day_Date_Mon_Year)%>
                                                    </label>
                                                    <%} catch (Exception e) {%>
                                                    INFORMATION NOT AVAILABLE
                                                    <%}%>
                                                </td>
                                                <td></td>
                                            </tr>

                                            <tr>
                                                <th scope="row">Logout of all devices</th>
                                                <td>
                                                    <form id="formLogoutFromAllDevices">
                                                        <input type="hidden" name="UserID" value="<%=user.getUserID()%>">
                                                        <button class="btn btn-danger" >Click here to Logout from everywhere</button>
                                                    </form>
                                                </td>
                                                <td></td>

                                            </tr>


                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="snackbar"></div>
        </div>


        <%@include file="CommonJS.jsp" %>
        <script>
            $(window).ready(function () {
                $('#save').attr('disabled', 'true');

                $('#save').on('click', function (e) {
                    var ID = $('input[name="UserID"]').val();

                    var timeleft = 4;
                    var Timer = setInterval(function () {
                        timeleft--;
                        document.getElementById("countdowntimer").textContent = timeleft;
                        if (timeleft <= 0) {
                            window.location.href = 'index.jsp';
                            clearInterval(Timer);
                        }
                    }, 1000);

                    $.ajax({
                        url: 'AccountActiveStatusServlet',
                        type: 'POST',
                        data: 'UserID=' + ID,
                        dataType: "html",
                        success: function (data, textStatus, jqXHR) {
                            if (data == '1') {
                                $('#response').html('<p style="color: green;">Please wait, You are going to redirected to home in <span id="countdowntimer"></span>s</p>');
                            } else if (data == '0') {
                                $('#response').html('<p style="color: orange;">Something went wrong. Please try again</p>');
                            }
                        }, error: function (jqXHR, textStatus, errorThrown) {
                            $('#response').html('<p style="color: orange;">Something went wrong. Please try again</p>');
                        }

                    });

                });

                $('#btnReset').on('click', function (e) {
//                    e.preventDefault();
                    $('#loader').remove();
                    $('#btnLoader').removeAttr("disabled");
                    $('#Password').removeAttr("disabled");
                    $('#response').html('');
                    $('#save').attr('disabled', 'true');
                });


                $('#passwordVerificationForm').on('submit', function (e) {
                    e.preventDefault();
                    var formData = $(this).serialize();
                    $('#btnLoader').append('<i id="loader" class="fa fa-spinner fa-spin"></i>');
                    $.ajax({
                        url: 'PasswordVerificationServlet',
                        type: 'POST',
                        data: formData,
                        success: function (data, textStatus, jqXHR) {
                            if (data == '1') {
                                $('#loader').remove();
                                $('#btnLoader').append('<i id="loader" class="fa fa-check"></i>');
                                $('#btnLoader').attr('disabled', 'true');
                                $('#Password').attr('disabled', 'true');
                                $('#save').removeAttr("disabled");
                            } else if (data == '0') {
                                $('#loader').remove();
                                $('#btnLoader').append('<i id="loader" class="fa fa-times"></i>');
                                $('#response').html('<p style="color: red;">Incorrect Password</p>');
                                $('#Password').attr('disabled', 'true');
                                $('#btnLoader').attr('disabled', 'true');
                                $('#save').attr('disabled', 'true');
                            } else {
                                $('#loader').remove();
                                $('#save').attr('disabled', 'true');
                                $('#Password').attr('disabled', 'true');
                                $('#btnLoader').attr('disabled', 'true');
                                $('#response').html('<p style="color: orange;">Something went wrong </p>');
                            }
                        }, error: function (jqXHR, textStatus, errorThrown) {
                            console.log(errorThrown);
                            $('#loader').remove();
                            $('#save').attr('disabled', 'true');
                            $('#Password').attr('disabled', 'true');
                            $('#btnLoader').attr('disabled', 'true');
                            $('#response').html('<p style="color: orange;">Something went wrong </p>');
                        }
                    });
                });

                $('#formLogoutFromAllDevices').on('submit', function (e) {
                    e.preventDefault();
                    var data = $(this).serialize();
                    $.ajax({
                        url: 'LogoutFromEverywhereServlet',
                        type: 'POST',
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            if (data == "1") {
                                $('#snackbar').html('Successfully Logged out of all Devices');
                                $('#snackbar').addClass('show');
                                setTimeout(function () {
                                    $('#snackbar').removeClass("show");
                                }, 3000);

                            } else if (data == "0") {
                                $('#snackbar').html('Something went wrong');
                                $('#snackbar').addClass('show');
                                setTimeout(function () {
                                    $('#snackbar').removeClass("show");
                                }, 3000);
                            } else {
                                swal("Error", "something went wrong", "error");
                            }

                        }, error: function (jqXHR, textStatus, errorThrown) {
                            console.log(errorThrown);
                            swal("Error", errorThrown, "error");
                        }
                    });
                });


            });
        </script>
    </body>
</html>
