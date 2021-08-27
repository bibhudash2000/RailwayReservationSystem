<%@page import="com.RRS.DAO.UserDAO"%>
<%@page import="com.RRS.Models.Message"%>
<%@page import="com.RRS.Models.User"%>

<%User user = (User) session.getAttribute("visitor");%>

<%if (user != null) {%>
<%Boolean validated = UserDAO.validateIfSessionExpired(session.getId());
    if (validated) {
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
%>
<nav class="navbar navbar-expand-lg navbar-dark PrimaryColor">
    <a class="navbar-brand" href="index.jsp"><i class="fa fa-train"></i>&nbsp;RRS</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
           
            <li class="nav-item">
                <a class="nav-link" href="Book-Ticket.jsp"><i class="fa fa-ticket"></i>Train Tickets</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="NoticeBoard.jsp"><i class="fa fa-list-alt" aria-hidden="true"></i>&nbsp;Notice Board</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Actions
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#PNRModal"><i class="fa fa-search"></i>&nbsp;Check PNR Status</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="Book-Ticket.jsp"><i class="fa fa-ticket"></i>&nbsp;Book Ticket</a>
                    <a class="dropdown-item" href="FAQ.jsp"><i class="fa fa-question-circle-o" aria-hidden="true"></i>&nbsp;FAQ's</a>
                    <a class="dropdown-item" href="FAQ.jsp"><i class="material-icons" style="">history</i>Refund Status</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav">
            <form method="POST" action="LogoutServlet">
                <li class="nav-item dropdown">
                    <button type="button" class="btn btn-outline-info my-2 my-sm-0" data-target="authDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-user-circle"></i>&nbsp;<%=user.getName()%>
                    </button>

                    <div class="dropdown-menu dropdown-menu-right"  id="authDropdown" >
                        <a class="dropdown-item" href="MyBookings.jsp"><i class="fa fa-clone" aria-hidden="true"></i>&nbsp;My Bookings</a>
                        <a class="dropdown-item" href="More.jsp"><i class="fa fa-ellipsis-h" aria-hidden="true"></i>&nbsp;More</a>
                        <div class="dropdown-divider"></div>
                        <button class="dropdown-item" type="submit"><i class="fa fa-sign-out"></i>&nbsp;Logout</button>
                    </div>
                </li>
            </form>

        </ul>   
    </div>

</nav>
<!--Account Active Alert-->
<%if (user.getHasAccountFrozen()) {%>
<div class="container-fluid mt-1" id="accountStatusAlert">
    <div class="alert <%=Message.ALERT_DANGER%> alert-dismissible fade show" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="alert-heading">The Account is Frozen as per your request</h4>
        <p>You can't book tickets until you unfreeze the account.</p>
        <hr>
        <p class="mb-0">To unfreeze <a href="AccountUnlockRequest.jsp">Click here</a></p>
    </div>
</div>
<%}%>
<!--Account Active Alert End-->



<%} else {%>

<nav class="navbar navbar-expand-lg navbar-dark PrimaryColor" >
    <a class="navbar-brand" href="index.jsp"><i class="fa fa-train"></i>&nbsp;RRS</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="Book-Ticket.jsp"><i class="fa fa-ticket"></i>Train Tickets</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="NoticeBoard.jsp"><i class="fa fa-list-alt" aria-hidden="true"></i>&nbsp;Notice Board</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-info-circle"></i>Train Information
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#PNRModal"><i class="fa fa-search"></i>PNR Status</a>
                    <a class="dropdown-item" href="TrainTimeTable.jsp"><i class="fa fa-calendar"></i>Time Table</a>
                    <a class="dropdown-item" href="Book-Ticket.jsp"><i class="material-icons">airline_seat_recline_normal</i>Seat Availability</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v"></i>More
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                    <a class="dropdown-item" href="FAQ.jsp"><i class="fa fa-question-circle-o"></i>FAQs</a>
                    <a class="dropdown-item" href="#"><i class="fa fa-users"></i>About Us</a>
                    <a class="dropdown-item" href="#"><i class="fa fa-envelope"></i>Contact Us</a>
                    <a class="dropdown-item" href="#"><i class="fa fa-times-circle"></i>Cancellations and Refunds</a>
                </div>
            </li>
        </ul>
    </div>




    <div class="form-inline my-2 my-lg-0">
        <button id="btnLogin" class="btn btn-outline-info my-2 my-sm-0" data-toggle="modal" data-target="#loginModal">Login</button>

        <!-- Modal -->
        <div  class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div style="width: 350px;" class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header PrimaryColor text-white">
                        <h5 class="modal-title text-center" id="exampleModalLabel">Login</h5>
                        <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12 d-flex justify-content-center">
                                    <div class="container-fluid">
                                        <form  method="POST" action="LoginServlet">
                                            <div class="form-group my-3">
                                                <label  for="InputEmail"><i class="fa fa-envelope"></i></label>
                                                <input type="email" class="m-2 form-control" name="InputEmail" id="InputEmail" placeholder="Enter email">
                                            </div>

                                            <div class="form-group my-3">
                                                <label for="InputPassword"><i class="fa fa-key"></i></label>
                                                <input type="password" class="m-2 form-control" name="InputPassword" id="InputPassword" placeholder="Password">
                                            </div>

                                            <div class="form-check m-3">
                                                <input type="checkbox" class="form-check-input" id="chkRemember">
                                                <label class="form-check-label" for="chkRemember">Remember me</label>
                                            </div>

                                            <div class="container text-center">
                                                <button id="btnLoginSubmit" type="submit" class="btn PrimaryColor text-white">Login</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="small">Don't have an account ? <a class="" href="NewUser.jsp">Click here</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>



<%}%>

<!--PNR Modal-->

<div class="modal fade" id="PNRModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Check Your PNR Status</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-group" method="Post" action="PNRStatusServlet">
                    <input type="number" name="txtPNR" class="form-control" required>
                    <button type="submit" class="btn PrimaryColor mt-3 text-white">Check</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!--PNR Modal-->

<!--Terms and Conditions Modal-->
<div class="modal fade" id="modal-terms-conditions" tabindex="-1" role="dialog" aria-labelledby="terms-conditions" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-center" id="terms-conditions">Terms & Conditions</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Lorem ipsum dolor sit amet, 
                consectetuer adipiscing elit.
                Aenean commodo ligula eget dolor. 
                Aenean massa. Cum sociis natoque
                penatibus et magnis dis parturient 
                montes, nascetur ridiculus mus. 
                Donec quam felis, ultricies nec, 
                pellentesque eu, pretium quis, sem. 
                Nulla consequat massa quis enim. Donec 
                pede justo, fringilla vel, aliquet 
                nec, vulputate eget, arcu. In enim 
                justo, rhoncus ut, imperdiet a, 
                venenatis vitae, justo. Nullam dictum 
                felis eu pede mollis pretium. Integer 
                tincidunt. Cras dapibus. Vivamus elementum 
                semper nisi. Aenean vulputate eleifend tellus.
                Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>