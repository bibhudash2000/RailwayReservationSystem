package com.RRS.Servlets;

import com.DigiBank.Lib.Response;
import com.DigiBank.Lib.ResponseVerifier;
import com.RRS.DAO.PaymentRecordDAO;
import com.RRS.DAO.ReservationDAO;
import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.BookStatus;
import com.RRS.Models.Passenger;
import com.RRS.Models.PaymentRecord;
import com.RRS.Models.Ticket;
import com.RRS.Models.TicketBookingStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentVerifierServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DatabaseException, SQLException {
        System.out.println("Payment Verifier Servlet");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Ticket ticket = (Ticket) session.getAttribute("NewTicket");
        List<Passenger> passengerList = (List<Passenger>) session.getAttribute("PassengersList");
        System.out.println(passengerList.size());
        System.out.println(ticket.toString());
        PrintWriter out = response.getWriter();
        Response res = Response.parse(request);
        System.out.println("Result Status :" + res.getSTATUS());
        TicketBookingStatus status;

        String TransactionID = res.getTXN_ID();
        String Amount = ticket.getAmount().toString();
        if (res.getSTATUS().equals(ResponseVerifier.S.toString())) {
            try {
                Integer TicketNo = ReservationDAO.addTicket(ticket);
                Boolean passengersAdded = ReservationDAO.addPassengers(passengerList, TicketNo);
                if (passengersAdded) {
                    String PNR = ReservationDAO.getPNRNumber(String.valueOf(TicketNo));
                    status = new TicketBookingStatus(TransactionID, Amount, PNR, BookStatus.SUCCESS);
                    PaymentRecord rec = new PaymentRecord(TransactionID, String.valueOf(TicketNo), "1");
                    PaymentRecordDAO.addPaymentRecord(rec);
                    session.setAttribute("booking-status", status);
                    session.removeAttribute("checksum");
                    response.sendRedirect("Ticket-Booking-Status.jsp");
                }
            } catch (Exception ex) {
                status = new TicketBookingStatus(TransactionID, Amount, BookStatus.PENDING);
                PaymentRecord rec = new PaymentRecord(TransactionID, "3");
                PaymentRecordDAO.addPaymentRecord(rec);
                session.setAttribute("booking-status", status);
                response.sendRedirect("Ticket-Booking-Status.jsp");
                ex.printStackTrace();
            }
        } else if (res.getSTATUS().equals(ResponseVerifier.F.toString())) {
            PaymentRecord rec = new PaymentRecord(TransactionID, "2");
            PaymentRecordDAO.addPaymentRecord(rec);
            status = new TicketBookingStatus(TransactionID, Amount, BookStatus.FAILED);
            session.setAttribute("booking-status", status);
            response.sendRedirect("Ticket-Booking-Status.jsp");
        } else {
            status = new TicketBookingStatus(TransactionID, Amount, BookStatus.PENDING);
            PaymentRecord rec = new PaymentRecord(TransactionID, "3");
            PaymentRecordDAO.addPaymentRecord(rec);
            session.setAttribute("booking-status", status);
            response.sendRedirect("Ticket-Booking-Status.jsp");
            System.out.println(res.getSTATUS());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DatabaseException ex) {
            Logger.getLogger(PaymentVerifierServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentVerifierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DatabaseException ex) {
            Logger.getLogger(PaymentVerifierServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentVerifierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
