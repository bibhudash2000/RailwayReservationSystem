package com.RRS.Servlets;

import com.RRS.DAO.UserDAO;
import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutFromEverywhereServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            HttpSession session = req.getSession(false);  
            String User = req.getParameter("UserID");
            User existingUser = (User) session.getAttribute("visitor");
            if (User.equals(existingUser.getUserID())) {
                Boolean updated = UserDAO.forceUpdateAllUserSessionsWhereLogoutIsNull(User);
                if (updated) {
                    out.print("1");
                } else {
                    out.print("0");
                }
            } else {
                out.print("0");
            }

        } catch (DatabaseException | SQLException ex) {
            Logger.getLogger(LogoutFromEverywhereServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
