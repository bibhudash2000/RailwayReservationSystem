package com.RRS.Servlets;

import com.RRS.DAO.StationDAO;
import com.RRS.Models.Message;
import com.RRS.Models.Station;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddNewStationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Message msg;
        HttpSession session = request.getSession();
        try {
            String StationCode = request.getParameter("stationCode");
            String StationName = request.getParameter("stationName");
            String City = request.getParameter("city");
            String State = request.getParameter("state");
            String ShortDesc = request.getParameter("shortDesc");
            String Zone = request.getParameter("zone");

            Station s = new Station(StationCode, StationName, City, State, ShortDesc, Zone);
            System.out.println(s.toString());
            Boolean added = StationDAO.addNewStation(s);
            if (added) {
                msg = new Message(Message.STATION_ADDED_SUCCESS, Message.ALERT_SUCCESS);
                session.setAttribute("msg", msg);
                response.sendRedirect("AdminHomeDirectory");
            } else {
                msg = new Message(Message.STATION_ADD_FAILED, Message.ALERT_DANGER);
                session.setAttribute("msg", msg);
                response.sendRedirect("AdminHomeDirectory");
            }

        } catch (Exception e) {
            msg = new Message("Error : " + e.getMessage(), Message.ALERT_SUCCESS);
            session.setAttribute("msg", msg);
            response.sendRedirect("AdminHomeDirectory");
            e.printStackTrace();
        }

    }
}
