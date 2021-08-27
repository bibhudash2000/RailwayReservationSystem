package com.RRS.Servlets;

import static com.RRS.DAO.CoachDAO.getCoachByClassCode;
import com.RRS.DAO.ReservationDAO;
import com.RRS.DAO.StationDAO;
import com.RRS.Models.Coach;
import com.RRS.Models.Message;
import com.RRS.Models.Passenger;
import com.RRS.Models.Reservation;
import com.RRS.Models.ReservationStatus;
import com.RRS.Models.Ticket;
import com.RRS.Models.TicketAvailability;
import com.RRS.Utilities.Calculator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddPassengersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Message msg;
        Passenger passenger;
        List<Passenger> passengersList = new ArrayList();
        Random random = new Random();
        try {
            String[] Name = request.getParameterValues("Name");
            String[] Age = request.getParameterValues("Age");
            String[] Gender = request.getParameterValues("Gender");

            Reservation reservation = (Reservation) session.getAttribute("NewReservation");

            String UserID = reservation.getBookedBy();
            String Class = reservation.getClassType();
            String Date = reservation.getDateOfJourney();
            String TrainNo = reservation.getTrainNo();
            String FarePrice = reservation.getFarePrice();
            Integer TotalPassengers = Name.length;

            Coach c = getCoachByClassCode(Class, TrainNo);
            c.getCOACH_ID();

            TicketAvailability ta = ReservationDAO.checkAvailableSeats(TrainNo, c.getCOACH_ID(), Date);
            Integer AvailableTickets = ta.getTicketCount();
            String SrcStationCode = reservation.getSourceStation();
            Integer srcStationID = StationDAO.getStationIDByStationCode(SrcStationCode);
            String DestStationCode = reservation.getDestinationStation();
            Integer destStationID = StationDAO.getStationIDByStationCode(DestStationCode);
            String PNR_NO = String.valueOf(random.nextInt(999999999));
            Double TicketPrice = Name.length * Double.valueOf(FarePrice);
            Double NetPrice = Calculator.getNetValue(5.0, TicketPrice);
            System.out.println("Price = " + TicketPrice);
            if (AvailableTickets >= Name.length) {
                Ticket ticket = new Ticket(PNR_NO, TrainNo, srcStationID, destStationID, SrcStationCode, DestStationCode, Date, TotalPassengers, c.getCOACH_ID(), TicketPrice, NetPrice, ReservationStatus.CNF, UserID);

                for (int i = 0; i < Name.length; i++) {
                    passenger = new Passenger(Name[i], Age[i], Gender[i]);
                    passengersList.add(passenger);
                }
                if (passengersList.size() <= 6) {
                    session.setAttribute("NewTicket", ticket);
                    session.setAttribute("PassengersList", passengersList);
                    response.sendRedirect("PaymentModes.jsp");
                    System.out.println(ticket.toString());
                    System.out.println(passengersList);
                    System.out.println("Session added");
                } else {
                    msg = new Message("You can't add more than 6 passengers in a ticket", Message.ALERT_DANGER);
                    session.setAttribute("userMsg", msg);
                    response.sendRedirect("Add-Passengers.jsp");
                }

            }
        } catch (Exception e) {
            msg = new Message("Something went wrong :" + e.getMessage(), Message.ALERT_DANGER);
            session.setAttribute("userMsg", msg);
            response.sendRedirect("index.jsp");
            e.printStackTrace();
        }

    }

}
