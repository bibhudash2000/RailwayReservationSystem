<%@page import="com.RRS.Models.TrainFare"%>
<%@page import="com.RRS.DAO.TrainFareDAO"%>
<%@page import="com.RRS.Models.Coach"%>
<%@page import="com.RRS.DAO.CoachDAO"%>
<%@page import="com.RRS.Models.Train"%>
<%@page import="com.RRS.Models.TrainCoaches"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.TrainDAO"%>
<%String TrainNo;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>Coach Position</title>
        <style>
            .coaches-hz{
                display: flex;
                align-items: center;
                justify-content: center;
                display: flex;
                flex-direction: row;
            }
            .coaches-vt{
                display: block;
                
            }
            .coach{
                height: 2rem;
                width: 3rem;
                margin: 3px;
                border-radius: 5px;
                align-items: center;
                justify-content: center;
                display: flex;
                color: #ffffff;
            }
            .coaches-vt > .coach{
                height: 2rem;
                width: 3rem;
                margin: 3px;
                border-radius: 5px;
                align-items: center;
                justify-content: center;
                display: inline-block;
            }

            .coaches-vt .coach-text{
                height: 2rem;
                width: 3rem;
                margin: 3px;
                border-radius: 5px;
                align-items: center;
                justify-content: center;
                display: inline;

            }
        </style>
    </head>

    <body>
        <%@include file="navbar.jsp" %>
        <%try {
                TrainNo = request.getParameter("TrainNo");
                Train train = TrainDAO.getTrainInfo(TrainNo);

                List<TrainCoaches> coachList = TrainDAO.listTrainsCoachPositions(TrainNo);
        %>
        <%if (coachList.size() < 1 || train == null) {%>
        Information not Available
        <%} else {%>
        <div class="container-fluid">
            <h5 class="text-center mt-2">Coach Position of <%=train.getTrainName()%>(<%=train.getTrainNo()%>)</h5>
            <div class="card w-100 mt-3">
                <div class="card-body">
                    <div class="coaches-hz">
                        <i class="fa fa-arrow-circle-o-left"></i>
                        <%for (TrainCoaches coaches : coachList) {%>
                        <%TrainFare tf = TrainFareDAO.getTrainFareByFareID(coaches.getFARE_ID());%>
                        <%Coach CoachInfo = CoachDAO.getCoachInformationByCoachID(tf.getCOACH_ID());%>
                        <span class="coach" style="background-color: <%=CoachInfo.getCOLOR_CODE()%>"><%=coaches.getCOACH_NAME()%></span>
                        <%}%>
                    </div>
                </div>
            </div>
            <div class="container-fluid justify-content-center d-flex my-3">
                <%List<Coach> coachTypesList = CoachDAO.listCoachTypes();%>
                <div class="card" style="width: 25rem;" >
                    <div class="card-header PrimaryColor text-white">Coach Abbreviations</div>
                    <div class="card-body" style="overflow-y: scroll; max-height: 20rem;">
                        <%for (Coach c : coachTypesList) {%> 
                        <div class="coaches-vt">
                            <span class="coach text-center align-items-center" style="background-color: <%=c.getCOLOR_CODE()%>"><%=c.getCOACH_CODE()%>
                            </span>
                            <div class="coach-text">
                                <%=c.getCOACH_TYPE()%>
                            </div>
                        </div>
                        <%}%>
                        
                    </div>
                </div>
            </div>

        </div>


        <%}%>

        <%} catch (Exception e) {%>
        <%=e.getMessage()%>
        <%}%>




        <%@include file="CommonJS.jsp" %>
    </body>
</html>
