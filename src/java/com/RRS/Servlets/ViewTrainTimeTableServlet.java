package com.RRS.Servlets;

import com.RRS.DAO.TrainDAO;
import com.RRS.Models.Message;
import com.RRS.Models.Train;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewTrainTimeTableServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("txtTrainNameOrNumber");
        HttpSession session = req.getSession();
        try {
            System.out.println(input);
            Train train = TrainDAO.getTrainInfo(input);
            req.getRequestDispatcher("Train-Info.jsp?TrainNo=" + train.getTrainNo()).forward(req, resp);

        } catch (Exception e) {
            Message msg = new Message(Message.INVALID_INPUT, Message.ALERT_DANGER);
            req.getRequestDispatcher("TrainTimeTable.jsp").forward(req, resp);
            session.setAttribute("msg", msg);
            e.printStackTrace();
        }
    }

}
