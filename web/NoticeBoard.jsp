<%@page import="com.RRS.Utilities.ValueFilter"%>
<%@page import="com.RRS.Models.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.NoticeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Message msg = (Message) session.getAttribute("userMsg");%>

<%String NoticeID = "";%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>RRS | Notice Board</title>
        <link rel="icon" type="image/icon type" href="img/train_ico.png">
        <%@include file="CommonCSS.jsp"%>
    </head>
    <body>
        <!--Navbar-->
        <%@include file="navbar.jsp" %>
        <!--Navbar End-->

        <!--Message Alert-->
        <%if (msg != null) {%>
        <div class="alert <%=msg.getCSS()%>" role="alert">
            <%=msg.getContent()%>
        </div>
        <%}
            session.removeAttribute("userMsg");%>
        <!--Message Alert End-->

        <%try {%>
        <%NoticeID = request.getParameter("NoticeID");%>
        <%if (NoticeID.equals(null) || NoticeID.equals("")) {%>
        <%NoticeID = "1";%>
        <%}%>
        <%} catch (Exception e) {%>

        <%}%>


        <div class="container-fluid mt-3 mb-2">
            <div class="row">
                <div class="col-8">
                    <div class="card" style="height: 380px; width: 50rem;">
                        <%try {%>
                        <%Notice notice = NoticeDAO.getNoticeByID(NoticeID);%>
                        <%if (notice != null) {%>
                        <div class="card-header PrimaryColor text-center text-white">Notice Header</div>
                        <div class="card-body" style="overflow-y: scroll; word-wrap: break-word;">

                            <div class="card-title"><h3><%=notice.getNoticeTitle()%></h3></div>
                            <div class="card-subtitle"><p class="text-muted" style="font-size: 12px;"><%=notice.getNoticePostedOn().getDate()%> | <%=notice.getNoticePostedOn().getTime()%></p></div>
                            <div class="card-text">
                                <%=notice.getNoticeContent()%>
                            </div>

                        </div>
                        <div class="card-footer text-center">
                            <button class="btn PrimaryColor text-white" data-toggle="tooltip" data-placement="bottom" title="Previous Notice" >
                                <i class="fa fa-arrow-left" aria-hidden="true"></i>
                            </button>
                            <%if (notice.getHasPDF()) {%>
                            <button class="btn PrimaryColor text-white" data-toggle="tooltip" data-placement="bottom" title="Download PDF" >
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <%} else {%>
                            <button class="btn PrimaryColor text-white disabled" data-toggle="tooltip" data-placement="bottom" title="PDF not Available">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <%}%>
                            <button class="btn PrimaryColor text-white" id="shareNotice" data-toggle="tooltip" data-placement="bottom" title="Share" >
                                <i class="fa fa-share-alt" aria-hidden="true"></i>
                            </button>
                            <button class="btn PrimaryColor text-white" data-toggle="tooltip" data-placement="bottom" title="Next Notice" >
                                <i class="fa fa-arrow-right" aria-hidden="true"></i>
                            </button>
                        </div>

                        <%} else {%>

                        <div class="card-header PrimaryColor text-center text-white">Notice Header</div>
                        <div class="card-body" style="overflow-y: scroll; word-wrap: break-word;">
                            Click any notice to view
                        </div>
                        <div class="card-footer text-center">
                            <button class="btn PrimaryColor text-white not-allowed" data-toggle="tooltip" data-placement="bottom" title="Previous Notice" disabled >
                                <i class="fa fa-arrow-left" aria-hidden="true"></i>
                            </button>
                            <button class="btn PrimaryColor text-white not-allowed" data-toggle="tooltip" data-placement="bottom" title="Download PDF" disabled >
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn PrimaryColor text-white not-allowed" data-toggle="tooltip" data-placement="bottom" title="Share" disabled >
                                <i class="fa fa-share-alt" aria-hidden="true"></i>
                            </button>
                            <button class="btn PrimaryColor text-white not-allowed" data-toggle="tooltip" data-placement="bottom" title="Next Notice" disabled>
                                <i class="fa fa-arrow-right" aria-hidden="true"></i>
                            </button>
                        </div>
                        <%}%>
                        <%} catch (Exception e) {%>
                        <%=e.getMessage()%>
                        <%}%>

                    </div>
                </div>
                <div class="col-4" style="overflow-y: hidden;" >
                    <div class="container-fluid" >
                        <div class="card" style="overflow-y: scroll; height: 500px;">
                            <%try {%>
                            <%List<Notice> listNotice = NoticeDAO.getNoticeList();%>
                            <%if (!listNotice.isEmpty()) {%>
                            <div class="list-group" style="width: content-box;">
                                <%for (Notice noticeList : listNotice) {%>
                                <a href="NoticeBoard.jsp?NoticeID=<%=noticeList.getNoticeID()%>" class="list-group-item list-group-item-action flex-column align-items-start">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h6 class="mb-1"><%=noticeList.getNoticeTitle()%></h6>
                                        <small><%=noticeList.getNoticePostedOn().getDate()%></small>
                                    </div>
                                        <p class="mb-1" style="font-size: 12px;"><%=ValueFilter.filterContent(noticeList.getNoticeContent())%></p>
                                </a>
                                <%}%>
                            </div>
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
            </div>
            <div id="snackbar"></div>
        </div>





        <%@include file="CommonJS.jsp" %>
        <script>
            $(window).ready(function () {

            });
        </script>
    </body>
</html>
