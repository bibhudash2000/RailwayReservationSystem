<%@page import="com.RRS.Exceptions.DatabaseException"%>
<%@page import="com.RRS.DAO.NoticeDAO"%>
<%@page import="com.RRS.Models.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.Models.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Message msg = (Message) session.getAttribute("userMsg");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>RRS | Index</title>
        <link rel="icon" type="image/icon type" href="img/train_ico.png">
        <%@include file="CommonCSS.jsp"%>
    </head>
    <body>


        <!--Navbar-->
        <%@include file="navbar.jsp" %>
        <!--Navbar End-->

        <!--Breadcrumb-->

        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item active" aria-current="page">Home</li>
            </ol>
        </nav>

        <!--Breadcrumb end-->


        <!--Message Alert-->
        <%if (msg != null) {%>
        <div class="alert <%=msg.getCSS()%>" role="alert">
            <%=msg.getContent()%>
        </div>
        <%}
            session.removeAttribute("userMsg");%>
        <!--Message Alert End-->

        <!--Jumbotron-->
        <div class="jumbotron">
            <h1 class="display-4">Welcome to Advanced Railway Reservation System</h1>
            <p class="lead">The Railway Reservation System facilitates the passengers to enquire about the trains available on the basis of source and destination, Booking and Cancellation of tickets, enquire about the status of the booked ticket, etc.</p>
            <hr class="my-4">
            <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
            <p class="lead">
                <a id="btnSignUp" class="btn PrimaryColor btn-lg text-white" href="NewUser.jsp" role="button">Sign Up</a>
            </p>
        </div>
        <!--Jumbotron End-->

        <!--Notice Section-->

        <div class="container-fluid mb-2">
            <div class="card" style="width: 18rem; height: 300px;">
                <div class="card-header PrimaryColor text-white text-center">Notices</div>
                <div class="card-body p-0" style="overflow-y: hidden; word-wrap: break-word;">
                    <%try {%>
                    <%List<Notice> listNotice = NoticeDAO.getNoticeList();%>
                    <%if (!listNotice.isEmpty()) {%>
                    <marquee direction = "up" height="100%" scrollamount="2">
                        <ul class="list-group">
                            <%for (Notice n : listNotice) {%>
                            <li class="list-group-item"><a class="link-black" href="NoticeBoard.jsp?NoticeID=<%=n.getNoticeID()%>"><%=n.getNoticeTitle()%></a></li>
                                <%}%>
                        </ul>
                    </marquee>




                    <%} else {%>
                    <div class="container-fluid text-center">
                        Information not available
                    </div>

                    <%}%>
                    <%} catch (Exception e) {%>
                    <%=e.getMessage()%>
                    <%}%>
                </div>
            </div>
        </div>

        <!--Notice Section End-->



        <!--Footer Section-->

        <!--Footer Section End-->

        <%@include file="CommonJS.jsp" %>
        <script>
            <%if (user != null) {%>
            $('#btnSignUp').hide();
            <%}%>
        </script>

    </body>
</html>
