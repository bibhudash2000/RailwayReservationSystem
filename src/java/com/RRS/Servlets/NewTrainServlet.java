package com.RRS.Servlets;

import com.RRS.DAO.TrainDAO;
import com.RRS.Models.CheckAvailableCoaches;
import com.RRS.Models.Train;
import com.RRS.Models.Week;
import com.RRS.Utilities.BoolValue;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewTrainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String TrainNo = request.getParameter("trainNo");
            String TrainName = request.getParameter("trainName");
            String SourceStation = (request.getParameter("sourceStation"));
            String DestStation = (request.getParameter("destStation"));
            
            Boolean CC = BoolValue.getBooleanValue(request.getParameter("CC"));
            Boolean AC1 = BoolValue.getBooleanValue(request.getParameter("AC1"));
            Boolean AC2 = BoolValue.getBooleanValue(request.getParameter("AC2"));
            Boolean AC3 = BoolValue.getBooleanValue(request.getParameter("AC3"));
            Boolean SL = BoolValue.getBooleanValue(request.getParameter("SL"));
            Boolean SS = BoolValue.getBooleanValue(request.getParameter("SS"));
            CheckAvailableCoaches availableCoaches = new CheckAvailableCoaches(CC, AC1, AC2, AC3, SL, SS);

            Boolean Mon = BoolValue.getBooleanValue(request.getParameter("Mon"));
            Boolean Tue = BoolValue.getBooleanValue(request.getParameter("Tue"));
            Boolean Wed = BoolValue.getBooleanValue(request.getParameter("Wed"));
            Boolean Thu = BoolValue.getBooleanValue(request.getParameter("Thu"));
            Boolean Fri = BoolValue.getBooleanValue(request.getParameter("Fri"));
            Boolean Sat = BoolValue.getBooleanValue(request.getParameter("Sat"));
            Boolean Sun = BoolValue.getBooleanValue(request.getParameter("Sun"));
            Week week = new Week(Mon, Tue, Wed, Thu, Fri, Sat, Sun);

//            out.println(TrainNo+" " + TrainName+" " + SourceStation+" " + DestStation);
//            out.println(CC +" "+ AC1 +" "+ AC2+" " + AC3+" " + SL+" " + SS);
//            out.println(Mon +" "+ Tue +" "+ Wed+" " + Thu+" " + Fri+" " + Sat+" "+Sun);
            Train train = new Train(TrainName, TrainNo, SourceStation, DestStation, week, availableCoaches);
            Boolean isAdded = TrainDAO.addTrain(train);
            if(isAdded){
                out.print("Add success");
            }else{
                out.print("Add Failed");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
