/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RRS.Servlets;


import com.RRS.Utilities.APIRequestManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class AddPaymentCardServlet extends HttpServlet {
/*cardNo
expiryMonth
expiryYear
cvvNo
cardHolderName*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String CardNumber = request.getParameter("cardNo");
            String ExpiryMonth = request.getParameter("expiryMonth");
            String ExpiryYear = request.getParameter("expiryYear");
            String CVVNo = request.getParameter("cvvNo");
            String CardHolderName = request.getParameter("cardHolderName");
            
            APIRequestManager.getRootAPIRequestURL();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
