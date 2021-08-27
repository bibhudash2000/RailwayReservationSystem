package com.RRS.Servlets;

import com.RRS.DAO.UserDAO;
import com.RRS.Models.Message;
import com.RRS.Models.User;
import com.RRS.Models.UserRole;
import com.RRS.Utilities.LoginSession;
import com.RRS.Utilities.UserAgentDetect;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Message msg;
        String requestedPage = request.getHeader("Referer");
        String UserAgent = request.getHeader("user-agent");
        LoginSession loginSession = new LoginSession(UserAgent, request, session);

        try {
            String Email = request.getParameter("InputEmail");
            String Password = request.getParameter("InputPassword");
            User visitor = UserDAO.authenticateUser(new User(Email, Password), loginSession);
            if (visitor != null) {
                UserRole role = new UserRole();
                Boolean isAdmin = role.isUserAdmin(visitor.getUserID());
                if (isAdmin) {
                    session.setAttribute("admin", visitor);
                    response.sendRedirect("admin/home.jsp");
                } else {
                    session.setAttribute("visitor", visitor);
                    response.sendRedirect(requestedPage);
                    System.out.println(visitor.toString());
                }

            } else {
                msg = new Message("Invalid Credentials...Please try again", Message.ALERT_DANGER);
                session.setAttribute("userMsg", msg);
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            msg = new Message(ex.getMessage(), Message.ALERT_DANGER);
            session.setAttribute("userMsg", msg);
            response.sendRedirect("index.jsp");
        }
    }
}
