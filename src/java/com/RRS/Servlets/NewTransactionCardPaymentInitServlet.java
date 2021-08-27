package com.RRS.Servlets;

import com.DigiBank.Lib.Payment;
import com.RRS.Models.SavedUserTransactionCard;
import com.RRS.Models.Ticket;
import com.RRS.Models.User;
import com.RRS.Utilities.APIRequestManager;
import com.RRS.Utilities.DateTime;
import com.RRS.Utilities.StringFormatter;
import com.RRS.Utilities.UrlRequestManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewTransactionCardPaymentInitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Ticket ticket = (Ticket) session.getAttribute("NewTicket");
        Double Amount = ticket.getNetAmount();
        User user = (User) session.getAttribute("visitor");
        String UserID = user.getUserID();
        String CardNumber = request.getParameter("cardNo");
        CardNumber = StringFormatter.formatCardNumber(CardNumber);
        String ExpiryMonth = request.getParameter("expiryMonth");
        String ExpiryYear = request.getParameter("expiryYear");
        String Expiry = ExpiryMonth + "/" + ExpiryYear;
        String CVVNumber = request.getParameter("cvvNo");
        String CardHolderName = request.getParameter("cardHolderName");
        String SaveCardDetails = request.getParameter("chkSaveCard");

        SavedUserTransactionCard card = new SavedUserTransactionCard(UserID, CardNumber, ExpiryMonth, ExpiryYear, CVVNumber, CardHolderName);
        Payment payment = new Payment(CardNumber, Expiry, CVVNumber, Amount, APIRequestManager.USERNAME, APIRequestManager.PASSWORD, APIRequestManager.API_KEY, UrlRequestManager.getCallBackURL());
        session.setAttribute("new-payment", payment);
        RequestDispatcher rd = request.getRequestDispatcher("PaymentInitializerServlet");
        rd.forward(request, response);
    }

}
