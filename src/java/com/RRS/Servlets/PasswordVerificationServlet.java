package com.RRS.Servlets;

import com.RRS.DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordVerificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            String UserID = req.getParameter("UserID");
            String UserPassword = req.getParameter("Password");

            Boolean verified = UserDAO.verifyUserPassword(UserID, UserPassword);
            Thread.sleep(1000);
            if(verified){
                out.print("1");
            }else{
                 out.print("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
