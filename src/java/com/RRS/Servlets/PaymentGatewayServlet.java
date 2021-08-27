package com.RRS.Servlets;

import com.DigiBank.Lib.Payment;
import com.RRS.DAO.SavedUserTransactionCardDAO;
import com.RRS.Models.Message;
import com.RRS.Models.SavedUserTransactionCard;
import com.RRS.Models.Ticket;
import com.RRS.Models.User;
import com.RRS.Utilities.APIRequestManager;
import com.RRS.Utilities.DateTime;
import com.RRS.Utilities.IDGenerator;
import com.RRS.Utilities.StringFormatter;
import com.RRS.Utilities.UrlRequestManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentGatewayServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        try {
//
//            Ticket ticket = (Ticket) session.getAttribute("NewTicket");
//            Double Amount = ticket.getNetAmount();
//            User user = (User) session.getAttribute("visitor");
//            String UserID = user.getUserID();
//            String CardNumber = request.getParameter("cardNo");
//            CardNumber = StringFormatter.formatCardNumber(CardNumber);
//            String ExpiryMonth = request.getParameter("expiryMonth");
//            String ExpiryYear = request.getParameter("expiryYear");
//            String Expiry = ExpiryMonth + "/" + ExpiryYear;
//            String CVVNumber = request.getParameter("cvvNo");
//            String CardHolderName = request.getParameter("cardHolderName");
//            String SaveCardDetails = request.getParameter("chkSaveCard");
//            System.out.println(CardNumber);
//            System.out.println(Amount);
//
//            SavedUserTransactionCard card = new SavedUserTransactionCard(UserID, CardNumber, ExpiryMonth, ExpiryYear, CVVNumber, CardHolderName);
//            Boolean validated = UrlRequestManager.validateUserTransactionCardWithBank(CardNumber, Expiry, CVVNumber);
//            if (validated) {
//                if (SaveCardDetails != null) {
//                    SavedUserTransactionCardDAO.addNewTransactionCard(card);
//                }
//                String TxnID = IDGenerator.generateID();
//                Payment payment = new Payment(CardNumber, Expiry, CVVNumber, Amount, TxnID, DateTime.getDateTime(DateTime.DATE_TIME_yyyyMMdd_HHmmss), APIRequestManager.USERNAME, APIRequestManager.PASSWORD, APIRequestManager.API_KEY, UrlRequestManager.getCallBackURL());
//                session.setAttribute("new-payment", payment);
//                response.sendRedirect("PaymentInit.jsp");
//            }
//        } catch (Exception e) {
//            Message msg = new Message("Error :" + e.getMessage(), Message.ALERT_DANGER);
//            session.setAttribute("msg", msg);
//            response.sendRedirect("PaymentModes.jsp");
//            e.printStackTrace();
//        }

    }

}
