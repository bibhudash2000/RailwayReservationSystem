package com.RRS.Servlets;

import com.RRS.DAO.ReservationDAO;
import com.RRS.Models.Message;
import com.RRS.Models.PNR;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PNRStatusServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String PNR_NO = request.getParameter("txtPNR");
        Message msg;
        HttpSession session = request.getSession(false);
        try {
            PNR pnr = ReservationDAO.checkPNRStatus(PNR_NO);
            if (pnr != null) {
                session.setAttribute("PNR", pnr);
                response.sendRedirect("PNR-Status.jsp");
            } else {
                msg = new Message("Invalid PNR Number", Message.ALERT_DANGER);
                session.setAttribute("userMsg", msg);
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            msg = new Message("Error :" + e.getMessage(), Message.ALERT_DANGER);
            session.setAttribute("msg", msg);
            response.sendRedirect("index.jsp");
            e.printStackTrace();

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
