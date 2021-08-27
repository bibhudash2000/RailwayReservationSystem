/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RRS.Servlets;

import com.RRS.DAO.OtpDAO;
import com.RRS.DAO.UserDAO;
import com.RRS.Models.Message;
import com.RRS.Models.OTP;
import com.RRS.Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class OTPVerificationServlet extends HttpServlet {

//    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            PrintWriter out = response.getWriter();
            Message msg;
            String UserOTP = request.getParameter("txtOTP");
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("visitor");

            OTP OTP = OtpDAO.fetchOTP(user.getUserID());
            if (UserOTP.equals(OTP.getOTP())) {
                Boolean isTaskCompleted = UserDAO.setUserAccountUnfreeze(user);
                if (isTaskCompleted) {
                    session.invalidate();
                    out.print("1");
                } else {
                    msg = new Message("Something went wrong....", Message.ALERT_DANGER);
                    session.setAttribute("msg", msg);
//                    response.sendRedirect("index.jsp");
                    out.print("0");
                }
                

            } else {
                out.print("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
