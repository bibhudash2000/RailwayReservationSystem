package com.RRS.Servlets;

import com.RRS.DAO.RecentTrainSearchDAO;
import com.RRS.DAO.StationDAO;
import com.RRS.DAO.TrainDAO;
import com.RRS.Models.Message;
import com.RRS.Models.RecentTrainSearch;
import com.RRS.Models.TrainsSchedule;
import com.RRS.Models.User;
import com.RRS.Utilities.DateTime;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchTrainsServlet extends HttpServlet {

    Message msg;

//    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {

            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            String source = request.getParameter("sourceStation");
            String destination = request.getParameter("destinationStation");
            String BoardingDate = request.getParameter("boardingDate");
            String Class = request.getParameter("class");
            User u = (User) session.getAttribute("visitor");
            if (source == null || destination == null) {
                msg = new Message(Message.STATION_NULL_VALUE, Message.ALERT_DANGER);
                session.setAttribute("msg", msg);
                request.getRequestDispatcher("Book-Ticket.jsp").forward(request, response);
                
            } else {
                if (u != null) {
                    int SourceStationID = StationDAO.getStationIDByStationCode(source);
                    int DestinationStationID = StationDAO.getStationIDByStationCode(destination);

                    RecentTrainSearch recentSearch = new RecentTrainSearch(u.getUserID(), SourceStationID, DestinationStationID, BoardingDate, Class);
                    RecentTrainSearchDAO.addRecentTrainSearch(recentSearch);
                }

                String formatDate = DateTime.getDateTime(BoardingDate, DateTime.DATE_TIME_yyyyMMdd, DateTime.DATE_TIME_ddMMyyyy);
                List<TrainsSchedule> intermediateStationTrainList = TrainDAO.listIntermediateTrains(source, destination, formatDate);
                List<TrainsSchedule> directStationTrainList = TrainDAO.getDirectTrainListBetweenStations(source, destination, formatDate);
                System.out.println("Intermediate Station List :" + intermediateStationTrainList.size());
                System.out.println("Direct Station List :" + directStationTrainList.size());

                if (intermediateStationTrainList.isEmpty() && directStationTrainList.isEmpty()) {
                    msg = new Message("No Trains found in this route", Message.ALERT_DANGER);
                    session.setAttribute("msg", msg);
                    response.sendRedirect("Book-Ticket.jsp");
                } else {
                    response.sendRedirect("Search-Results.jsp?src=" + source + "&dest=" + destination + "&date=" + BoardingDate + "&class=" + Class);
                }
            }
        } catch (Exception e) {
            msg = new Message(e.getMessage(), Message.ALERT_DANGER);
            session.setAttribute("msg", msg);
            response.sendRedirect("Book-Ticket.jsp");
            e.printStackTrace();
        }
    }

}
