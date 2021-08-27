/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RRS.Servlets;

import com.RRS.DAO.UserDAO;
import com.RRS.Models.Message;
import com.RRS.Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountActiveStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        Message msg;
        try {
            User u = (User) session.getAttribute("visitor");
            String UserID = req.getParameter("UserID");
            System.out.println(u.toString());
            System.out.println(UserID);
            if (u.getUserID().equals(UserID)) {
                System.out.println("ID matched");
                if (!u.getHasAccountFrozen()) {
                    Boolean isTaskCompleted = UserDAO.setUserAccountFreeze(u);
                    if (isTaskCompleted) {
                        System.out.println("Task Completed");
                        msg = new Message("Account Successfully Disabled", Message.ALERT_SUCCESS);
                        session.setAttribute("msg", msg);
                        session.invalidate();
//                        resp.sendRedirect("index.jsp");
                        out.print("1");

                    } else {
                        System.out.println("Failed to complete task");
                        msg = new Message("Something went wrong....", Message.ALERT_DANGER);
                        session.setAttribute("msg", msg);
                        //resp.sendRedirect("index.jsp");
                        out.print("0");
                    }
                } else {
                    System.out.println("Error in fetching details");
                    msg = new Message("Error in fetching details", Message.ALERT_DANGER);
                    session.setAttribute("msg", msg);
                    //resp.sendRedirect("index.jsp");
                    out.print("0");
                }
            } else {
                System.out.println("ID doesn't matched");
                msg = new Message("Something went wrong....", Message.ALERT_DANGER);
                session.setAttribute("msg", msg);
//                resp.sendRedirect("index.jsp");
                out.print("0");
            }
        } catch (Exception e) {
            msg = new Message("Something went wrong..  " + e.getMessage(), Message.ALERT_DANGER);
            session.setAttribute("msg", msg);
//            resp.sendRedirect("index.jsp");
            out.print("0");
            e.printStackTrace();
        }

    }
}
