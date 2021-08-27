<%@page import="com.RRS.DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp"%>
        <title>Unlock Account Request</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <div class="card" style="width: 30rem;">
                        <div class="card-header"></div>
                        <div class="card-body">
                            <form id="emailVerifyForm" method="POST" action="EmailVerificationServlet">
                                <table>
                                    <tr>
                                        <td>Verify Your Email :</td>
                                        <td><input type="email" id="txtEmail" name="txtEmail" class="form-control" required ></td>
                                        <td><button id="btnEmailVerify" class="btn PrimaryColor text-white">Verify</button></td>
                                    </tr>
                                </table>
                            </form>

                            <form id="otpForm" method="POST" action="OTPVerificationServlet">
                                <hr>
                                <small class="my-5"><strong>Note: </strong>OTP has been sent to your registered mail id.</small>
                                <table>
                                    <tr>
                                        <td>Enter OTP:</td>
                                        <td><input type="number" name="txtOTP" class="form-control" required></td>
                                    </tr>
                                    <tr>
                                        <td><button type="submit" class="btn PrimaryColor">Submit</button></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div class="card-footer"></div>
                    </div>
                </div>
            </div>
        </div>




        <%@include file="CommonJS.jsp" %>
        <script>

            $(window).ready(function () {
                $('#otpForm').hide();
                $('#accountStatusAlert').hide();
            });
            $('#emailVerifyForm').on('submit', function (e) {
                e.preventDefault();
                var data = $(this).serialize();

                $.ajax({

                    url: 'EmailVerificationServlet',
                    type: 'POST',
                    data: data,
                    success: function (data, textStatus, jqXHR) {
                        if (data === "1") {
                            $('#otpForm').show();
                            $('#btnEmailVerify').addClass("disabled");
                            $('#txtEmail').attr('disabled', true);
                        } else {
                            swal("Email Auth", "Invalid Email Address Entered", "error");
                        }
                    }, error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });

            });

            $('#otpForm').on('submit', function (e) {
                e.preventDefault();
                var otpData = $(this).serialize();
                $.ajax({
                    url: 'OTPVerificationServlet',
                    type: 'POST',
                    data: otpData,
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        if (data === '1') {
                            swal("Success", "Please wait, you will be redirected to the home page", "success");
//                            $(location).attr('href', 'index.jsp');
                            window.setTimeout(function () {
                                window.location.href = 'index.jsp';
                            }, 3000);
                        } else if (data === '0') {
                            swal("OTP Error", "Invalid OTP Entered", "error");
                        } else {
                            swal("Server Error", "Something went wrong", "warning");
                        }
                    }, error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
            });
        </script>

    </body>
</html>
