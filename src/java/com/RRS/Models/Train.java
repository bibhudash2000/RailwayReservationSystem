package com.RRS.Models;

import com.RRS.Utilities.DateTime;
import static com.RRS.Utilities.DateTime.DATE_TIME_ddMM_EEE;
import static com.RRS.Utilities.DateTime.DATE_TIME_ddMMyyyy;
import static com.RRS.Utilities.DateTime.addDate;
import static com.RRS.Utilities.DateTime.getDateTime;
import static com.RRS.Utilities.DateTime.getDayName;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Train {

     String TrainName;
     Integer TotalCoaches;
     String TrainNo;
     String SourceStation;
     String DestinationStation;
     String TotalDistance;
     String Duration;
     String HaltStations;
     Week Week;
     CheckAvailableCoaches Coaches;

    public Train(String TrainName, String TrainNo, String SourceStation, String DestinationStation, Week Week, CheckAvailableCoaches Coaches) {
        this.TrainName = TrainName;
        this.TrainNo = TrainNo;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
        this.Week = Week;
        this.Coaches = Coaches;
    }

    public Train(String TrainName, String TrainNo, String SourceStation, String DestinationStation) {
        this.TrainName = TrainName;
        this.TrainNo = TrainNo;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
    }
    

    public Train() {
    }

    public Train(String TrainNo) {
        this.TrainNo = TrainNo;
    }

     
     
    public Train(String TrainName, Integer TotalCoaches, String TrainNo, String SourceStation, String DestinationStation, String TotalDistance, String Duration, String HaltStations, Week Week) {
        this.TrainName = TrainName;
        this.TotalCoaches = TotalCoaches;
        this.TrainNo = TrainNo;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
        this.TotalDistance = TotalDistance;
        this.Duration = Duration;
        this.HaltStations = HaltStations;
        this.Week = Week;
    }

    public Train(String TrainNo, String TrainName) {
        this.TrainName = TrainName;
        this.TrainNo = TrainNo;
    }

    

     
    
     
    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String TrainName) {
        this.TrainName = TrainName;
    }

    public Integer getTotalCoaches() {
        return TotalCoaches;
    }

    public void setTotalCoaches(Integer TotalCoaches) {
        this.TotalCoaches = TotalCoaches;
    }

    public String getTrainNo() {
        return TrainNo;
    }

    public void setTrainNo(String TrainNo) {
        this.TrainNo = TrainNo;
    }

    public String getTotalDistance() {
        return TotalDistance;
    }

    public void setTotalDistance(String TotalDistance) {
        this.TotalDistance = TotalDistance;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getHaltStations() {
        return HaltStations;
    }

    public void setHaltStations(String HaltStations) {
        this.HaltStations = HaltStations;
    }

    public String getSourceStation() {
        return SourceStation;
    }

    public void setSourceStation(String SourceStation) {
        this.SourceStation = SourceStation;
    }

    public String getDestinationStation() {
        return DestinationStation;
    }

    public void setDestinationStation(String DestinationStation) {
        this.DestinationStation = DestinationStation;
    }

    public Week getWeek() {
        return Week;
    }

    public void setWeek(Week Week) {
        this.Week = Week;
    }

    public CheckAvailableCoaches getCoaches() {
        return Coaches;
    }

    public void setCoaches(CheckAvailableCoaches Coaches) {
        this.Coaches = Coaches;
    }

    @Override
    public String toString() {
        return "Train{" + "TrainName=" + TrainName + ", TotalCoaches=" + TotalCoaches + ", TrainNo=" + TrainNo + ", SourceStation=" + SourceStation + ", DestinationStation=" + DestinationStation + ", TotalDistance=" + TotalDistance + ", Duration=" + Duration + ", HaltStations=" + HaltStations + ", Week=" + Week + ", Coaches=" + Coaches + '}';
    }

    
    public static Boolean checkTrainAvailableDays(Week week, String Date) throws ParseException {
        List<DateTime> list = new ArrayList();
        Boolean isTrainAvailable = false;
        DateTime dt;
        int counter = 0;
        String date = "";
        String formatDate = "";
        while (counter <= 119) {
            date = (addDate(getDateTime(), counter, DATE_TIME_ddMMyyyy));
            formatDate = getDateTime(date, DATE_TIME_ddMMyyyy, DATE_TIME_ddMM_EEE);
            if (week.getMon()) {
                if (getDayName(date).equals("Mon")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getTue()) {
                if (getDayName(date).equals("Tue")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }

            }
            if (week.getWed()) {
                if (getDayName(date).equals("Wed")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getThu()) {
                if (getDayName(date).equals("Thu")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getFri()) {
                if (getDayName(date).equals("Fri")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSat()) {
                if (getDayName(date).equals("Sat")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSun()) {
                if (getDayName(date).equals("Sun")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            counter++;
        }
        for (DateTime s : list) {
            if (Date.equals(s.getValue())) {
                isTrainAvailable = true;
            }
        }
        return isTrainAvailable;
    }
    

    

    
    
    
}
