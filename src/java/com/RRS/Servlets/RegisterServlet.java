package com.RRS.Servlets;

import com.RRS.DAO.ServerDAO;
import com.RRS.DAO.UserDAO;

import com.RRS.Models.Message;
import com.RRS.Models.Server;
import com.RRS.Models.User;
import com.RRS.Utilities.EmailManager;
import com.RRS.Utilities.EmailMessage;
import com.RRS.Utilities.Encrypt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {

    HttpSession session;
    Message msg;

//    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        try {
            String Name = req.getParameter("inputName");
            String Email = req.getParameter("inputEmail");
            String Contact = req.getParameter("inputContact");

            Boolean check = UserDAO.addUser(new User(Name, Email, Contact));
            if (check) {
                User u = UserDAO.viewUserByEmail(new User(Email));
                Server s = ServerDAO.getServer();
                String enc_key = Encrypt.toHexString(Encrypt.getSHA(u.getUserID()));
                msg = new Message("Please click on the link that we have sent to you in your email address", Message.ALERT_PRIMARY);
                String url = s.getServerAddress() + "/RailwayReservationSystem/AuthenticateNewUser.jsp?enc_key=" + enc_key + "&Email=" + u.getEmail();
                System.out.println(url);

                EmailMessage email = new EmailMessage();
                email.setEmailAddress(Email);
                email.setEmailUserName(Name);
                email.setEmailContent(email.getEmailConfirmationFormat(url));
                email.setEmailSubject("Email Confirmation");
//                EmailManager.send(Email, "RailwayReservationSystem | New User Registration", msg.getContent() + "\n" + "<a href='" + url + "'>Click here</a>");
                EmailManager.sendEmail(email);
                session.setAttribute("userMsg", msg);
                resp.sendRedirect("index.jsp");
            } else {
                msg = new Message("Something went wrong. Please try again", Message.ALERT_DANGER);
                session.setAttribute("userMsg", msg);
                resp.sendRedirect("NewUser.jsp");
            }

        } catch (Exception e) {
            msg = new Message(e.getMessage(), Message.ALERT_DANGER);
            session.setAttribute("userMsg", msg);
            resp.sendRedirect("NewUser.jsp");
            e.printStackTrace();
        }
    }

}
