package com.RRS.Servlets;

import com.RRS.Models.Message;
import com.RRS.Models.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TicketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        String Class = req.getParameter("class");
        String DateOfJourney = req.getParameter("date");
        String TrainNo = req.getParameter("TrainNo");
        String FarePrice = req.getParameter("farePrice");
        String SourceStationCode = req.getParameter("src");
        String DestinationStationCode = req.getParameter("dest");
        String BookedBy = req.getParameter("user");

        Boolean verified = false;
        List<String> list = new ArrayList();
        list.add(Class);
        list.add(DateOfJourney);
        list.add(TrainNo);
        list.add(FarePrice);
        list.add(SourceStationCode);
        list.add(DestinationStationCode);
        list.add(BookedBy);
        for (String s : list) {
            if (!s.equals("") || s.equals(null)) {
                verified = true;
            } else {
            }
        }
        if (verified) {
            Reservation r = new Reservation(Class, DateOfJourney, TrainNo, FarePrice, SourceStationCode, DestinationStationCode, BookedBy);
            session.setAttribute("NewReservation", r);
            resp.sendRedirect("Add-Passengers.jsp");
        } else {
            Message msg = new Message("Your request can't be processed at the moment", Message.ALERT_DANGER);
            session.setAttribute("msg", msg);
            resp.sendRedirect("Book-Ticket.jsp");
        }

    }

}
