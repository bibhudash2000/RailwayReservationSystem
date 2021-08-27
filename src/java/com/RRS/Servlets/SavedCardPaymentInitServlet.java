package com.RRS.Servlets;

import com.DigiBank.Lib.Merchant;
import com.DigiBank.Lib.Payment;
import com.RRS.DAO.SavedUserTransactionCardDAO;
import com.RRS.Models.Message;
import com.RRS.Models.SavedUserTransactionCard;
import com.RRS.Models.Ticket;
import com.RRS.Models.User;
import com.RRS.Utilities.APIRequestManager;
import com.RRS.Utilities.UrlRequestManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SavedCardPaymentInitServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {
            Ticket ticket = (Ticket) session.getAttribute("NewTicket");
            Double Amount = ticket.getNetAmount();
            User user = (User) session.getAttribute("visitor");
            if (user != null) {
                String CVV = request.getParameter("cvvNo");
                Boolean Validated = SavedUserTransactionCardDAO.verifyCardCvvNumber(CVV, user.getUserID());
                if (Validated) {
                    SavedUserTransactionCard card = SavedUserTransactionCardDAO.getUserSavedTransactionCard(user.getUserID());

                    String CardNumber = card.getCARD_NUMBER();
                    String Expiry = card.getEXPIRY_MONTH() + "/" + card.getEXPIRY_YEAR();
                    String CVVNumber = card.getCVV();
                    
                    Payment payment = new Payment(CardNumber, Expiry, CVVNumber, Amount, APIRequestManager.USERNAME, APIRequestManager.PASSWORD, APIRequestManager.API_KEY, UrlRequestManager.getCallBackURL());

                    System.out.println(payment.getCardNumber() + " " + payment.getExpiry());
                    session.setAttribute("new-payment", payment);
                    RequestDispatcher rd = request.getRequestDispatcher("PaymentInitializerServlet");
                    rd.forward(request, response);
                } else {
                    Message msg = new Message(Message.INVALID_CVV_NUMBER, Message.ALERT_DANGER);
                    session.setAttribute("userMsg", msg);
                    response.sendRedirect("PaymentModes.jsp");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
