package com.RRS.Servlets;

import com.RRS.DAO.UserDAO;
import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.Message;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {

   

//    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(false);
            Boolean Updated = UserDAO.updateUserLastLogin(session);
            if(Updated){
                System.out.println("Added");
            }else{
                System.out.println("Failed");
            }
            System.out.println(session.getId());
            
            session.removeAttribute("visitor");
            response.sendRedirect("index.jsp");
            System.out.println("Logout Servlet");
            session.invalidate();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
            
        } catch (DatabaseException | SQLException ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
