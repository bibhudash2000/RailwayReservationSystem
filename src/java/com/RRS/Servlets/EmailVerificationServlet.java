package com.RRS.Servlets;

import com.RRS.DAO.OtpDAO;
import com.RRS.DAO.UserDAO;
import com.RRS.Models.OTP;
import com.RRS.Models.User;
import com.RRS.Utilities.EmailManager;
import com.RRS.Utilities.EmailMessage;
import com.RRS.Utilities.OTPGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmailVerificationServlet extends HttpServlet {

//    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String Email = request.getParameter("txtEmail");
            User user = UserDAO.viewUserByEmail(new User(Email));
            HttpSession session = request.getSession();
            User existingUser = (User)session.getAttribute("visitor");
            if(user.getEmail().equals(existingUser.getEmail())){
                if (user != null) {
                    String OTPCode = OTPGenerator.generateOTP();
                    OtpDAO.storeOTP(new OTP(OTPCode, user.getUserID()));
                    EmailMessage email = new EmailMessage();
                    email.setEmailAddress(user.getEmail());
                    email.setEmailUserName(existingUser.getName());
                    email.setEmailContent(email.getEmailOTPVerificationFormat(OTPCode));
                    email.setEmailSubject("OTP Verification");
                    EmailManager.sendEmail(email);
                    System.out.println(OTPCode);
                    out.print(1);
                } else {
                    out.print(0);
                }
            }else{
                out.print(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
