package com.RRS.Servlets;

import com.RRS.DAO.UserDAO;
import com.RRS.Models.Message;
import com.RRS.Models.User;
import com.RRS.Utilities.Encrypt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticateNewUserServlet extends HttpServlet {

//    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            String Enc_key = req.getParameter("enc_key");
            String Password = req.getParameter("InputPassword");
            String Email = req.getParameter("email");
            System.out.println(Enc_key);
            System.out.println(Email);
            System.out.println(Password);
            User user = UserDAO.viewUserByEmail(new User(Email));
//            System.out.println(user.getUserID());
            String userEncKey = Encrypt.toHexString(Encrypt.getSHA(user.getUserID()));
            Message userMsg;
            if (Enc_key.equals(userEncKey)) {
                UserDAO.addUsersPassword(user.getUserID(),Password);
                userMsg = new Message("You have successfully created your account. You can login now....", Message.ALERT_SUCCESS);
                session.setAttribute("userMsg", userMsg);
                resp.sendRedirect("index.jsp");
            } else {
                userMsg = new Message("Invalid Credentials...Try Again", Message.ALERT_DANGER);
                session.setAttribute("userMsg", userMsg);
                resp.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
