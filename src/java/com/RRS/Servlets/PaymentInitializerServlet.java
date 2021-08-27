package com.RRS.Servlets;

import com.DigiBank.Lib.Checksum.Checksum;
import com.DigiBank.Lib.Checksum.EncryptionManager;
import com.DigiBank.Lib.Merchant;
import com.DigiBank.Lib.Payment;
import com.RRS.Utilities.APIRequestManager;
import com.RRS.Utilities.DateTime;
import com.RRS.Utilities.IDGenerator;
import com.RRS.Utilities.UrlRequestManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentInitializerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Payment payment = (Payment) session.getAttribute("new-payment");
        String TxnID = IDGenerator.generateID();
        String TxnDateTime = DateTime.getDateTime(DateTime.DATE_TIME_yyyyMMdd_HHmmss);

        String CardNumber = payment.getCardNumber();
        String Expiry = payment.getExpiry();
        String CVV = payment.getCVV();
        Double Amount = payment.getAmount();

        Merchant merchantInfo = new Merchant(APIRequestManager.USERNAME, APIRequestManager.PASSWORD, APIRequestManager.API_KEY, UrlRequestManager.getCallBackURL());
        Payment PaymentInfo = new Payment(CardNumber, Expiry, CVV, Amount, TxnID, TxnDateTime);
        String checksum = "";
        try {
            checksum = Checksum.parsePaymentInfo(merchantInfo, PaymentInfo);
            System.out.println("checksum : " + checksum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("checksum", checksum);
//        response.sendRedirect("PaymentInit.jsp");
        request.getRequestDispatcher("PaymentInit.jsp").forward(request, response);
    }

}
