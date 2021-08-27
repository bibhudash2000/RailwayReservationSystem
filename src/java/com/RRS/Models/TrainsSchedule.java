package com.RRS.Models;

import com.RRS.Exceptions.DatabaseException;
import java.sql.SQLException;

public class TrainsSchedule extends Train {

    private Integer StationID;
    private String StationCode;
    private String StationName;
    private String Departure;
    private String Arrival;
    private String Halt;
    private Integer Day;
    private Integer Distance;
    private String TripDuration;
    private Seat Seat;
    private String SourceStationCode;
    private String StartDate;
    private String DestinationStationCode;
    private String EndDate;
    private Boolean Mon;
    private Boolean Tue;
    private Boolean Wed;
    private Boolean Thu;
    private Boolean Fri;
    private Boolean Sat;
    private Boolean Sun;

    public Week getWeek() {
        return Week;
    }

    public void setWeek(Week Week) {
        this.Week = Week;
    }

    public TrainsSchedule(String TrainNo, String TrainName, Week Week, String Departure, String Arrival, Integer Day, Integer Distance, String TripDuration, String SourceStation, String SourceStationCode, String StartDate, String DestinationStation, String DestinationStationCode, String EndDate) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Week = Week;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.SourceStationCode = SourceStationCode;
        this.StartDate = StartDate;
        this.DestinationStationCode = DestinationStationCode;
        this.DestinationStation = DestinationStation;
        this.EndDate = EndDate;
    }

    public TrainsSchedule(String StationCode, String StationName, String Departure, String Arrival, String Halt, Integer Day, Integer Distance, String TripDuration, String SourceStation, String DestinationStation) {
        this.StationCode = StationCode;
        this.StationName = StationName;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Halt = Halt;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
    }

    public TrainsSchedule(String TrainNo, Integer StationID, String StationCode, String StationName, String Departure, String Arrival, String Halt, Integer Day, Integer Distance) {
        this.TrainNo = TrainNo;
        this.StationID = StationID;
        this.StationCode = StationCode;
        this.StationName = StationName;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Halt = Halt;
        this.Day = Day;
        this.Distance = Distance;
    }

    public TrainsSchedule() {
    }

    public TrainsSchedule(String TrainNo, String StationName, String Departure, String Arrival, Integer Day, Integer Distance) {
        this.TrainNo = TrainNo;
        this.StationName = StationName;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
    }

    public TrainsSchedule(String TrainNo, String TrainName, String Departure, String Arrival, Integer Day, Integer Distance, String SourceStation, String DestinationStation) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
    }

    public TrainsSchedule(String TrainNo, String TrainName, String Departure, String Arrival, Integer Day, Integer Distance, String TripDuration, String SourceStation, String DestinationStation) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
    }

    public TrainsSchedule(String TrainNo, String TrainName, Week Week, CheckAvailableCoaches Coaches, String Departure, String Arrival, Integer Day, Integer Distance, String TripDuration, String SourceStation, String SourceStationCode, String DestinationStation, String DestinationStationCode) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Week = Week;
        this.Coaches = Coaches;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.SourceStationCode = SourceStationCode;
        this.DestinationStationCode = DestinationStationCode;
        this.DestinationStation = DestinationStation;
    }

    public TrainsSchedule(String TrainNo, String TrainName, Week Week, CheckAvailableCoaches Coaches, Seat Seat, String Departure, String Arrival, Integer Day, Integer Distance, String TripDuration, String SourceStation, String SourceStationCode, String StartDate, String DestinationStation, String DestinationStationCode, String EndDate) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Week = Week;
        this.Coaches = Coaches;
        this.Seat = Seat;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.SourceStationCode = SourceStationCode;
        this.StartDate = StartDate;
        this.DestinationStationCode = DestinationStationCode;
        this.DestinationStation = DestinationStation;
        this.EndDate = EndDate;
    }

    public TrainsSchedule(String TrainNo, String TrainName, Week Week, Seat Seat, String Departure, String Arrival, Integer Day, Integer Distance, String TripDuration, String SourceStation, String SourceStationCode, String StartDate, String DestinationStation, String DestinationStationCode, String EndDate) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Week = Week;
        this.Seat = Seat;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.SourceStationCode = SourceStationCode;
        this.StartDate = StartDate;
        this.DestinationStationCode = DestinationStationCode;
        this.DestinationStation = DestinationStation;
        this.EndDate = EndDate;
    }

    public TrainsSchedule(String TrainNo, String TrainName, Week Week, CheckAvailableCoaches Coaches, String Departure, String Arrival, Integer Day, Integer Distance, String TripDuration, String SourceStation, String SourceStationCode, String StartDate, String DestinationStation, String DestinationStationCode, String EndDate) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Week = Week;
        this.Coaches = Coaches;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.SourceStationCode = SourceStationCode;
        this.StartDate = StartDate;
        this.DestinationStationCode = DestinationStationCode;
        this.DestinationStation = DestinationStation;
        this.EndDate = EndDate;
    }

    public TrainsSchedule(String TrainNo, String TrainName, String Departure, String Arrival, Integer Day, Integer Distance, String TripDuration, String SourceStation, String DestinationStation, Boolean Mon, Boolean Tue, Boolean Wed, Boolean Thu, Boolean Fri, Boolean Sat, Boolean Sun) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.TripDuration = TripDuration;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
        this.Mon = Mon;
        this.Tue = Tue;
        this.Wed = Wed;
        this.Thu = Thu;
        this.Fri = Fri;
        this.Sat = Sat;
        this.Sun = Sun;
    }

    public TrainsSchedule(String TrainNo, String TrainName, String Departure, String Arrival, Integer Day, Integer Distance, String SourceStation, String DestinationStation, Boolean Mon, Boolean Tue, Boolean Wed, Boolean Thu, Boolean Fri, Boolean Sat, Boolean Sun) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Day = Day;
        this.Distance = Distance;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
        this.Mon = Mon;
        this.Tue = Tue;
        this.Wed = Wed;
        this.Thu = Thu;
        this.Fri = Fri;
        this.Sat = Sat;
        this.Sun = Sun;
    }

    public Integer getStationID() {
        return StationID;
    }

    public String getStationCode() {
        return StationCode;
    }

    public void setStationCode(String StationCode) {
        this.StationCode = StationCode;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String StationName) {
        this.StationName = StationName;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String Departure) {
        this.Departure = Departure;
    }

    public String getArrival() {
        return Arrival;
    }

    public void setArrival(String Arrival) {
        this.Arrival = Arrival;
    }

    public String getHalt() {
        return Halt;
    }

    public void setHalt(String Halt) {
        this.Halt = Halt;
    }

    public Integer getDay() {
        return Day;
    }

    public void setDay(Integer Day) {
        this.Day = Day;
    }

    public Integer getDistance() {
        return Distance;
    }

    public void setDistance(Integer Distance) {
        this.Distance = Distance;
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

    public String getTripDuration() {
        return TripDuration;
    }

    public void setTripDuration(String TripDuration) {
        this.TripDuration = TripDuration;
    }

    public Boolean getMon() {
        return Mon;
    }

    public void setMon(Boolean Mon) {
        this.Mon = Mon;
    }

    public Boolean getTue() {
        return Tue;
    }

    public void setTue(Boolean Tue) {
        this.Tue = Tue;
    }

    public Boolean getWed() {
        return Wed;
    }

    public void setWed(Boolean Wed) {
        this.Wed = Wed;
    }

    public Boolean getThu() {
        return Thu;
    }

    public void setThu(Boolean Thu) {
        this.Thu = Thu;
    }

    public Boolean getFri() {
        return Fri;
    }

    public void setFri(Boolean Fri) {
        this.Fri = Fri;
    }

    public Boolean getSat() {
        return Sat;
    }

    public void setSat(Boolean Sat) {
        this.Sat = Sat;
    }

    public Boolean getSun() {
        return Sun;
    }

    public void setSun(Boolean Sun) {
        this.Sun = Sun;
    }

    public String getSourceStationCode() {
        return SourceStationCode;
    }

    public void setSourceStationCode(String SourceStationCode) {
        this.SourceStationCode = SourceStationCode;
    }

    public String getDestinationStationCode() {
        return DestinationStationCode;
    }

    public void setDestinationStationCode(String DestinationStationCode) {
        this.DestinationStationCode = DestinationStationCode;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public Seat getSeat() {
        return Seat;
    }

    public void setSeat(Seat Seat) {
        this.Seat = Seat;
    }

    @Override
    public String toString() {
        return "TrainsSchedule{" + "TrainNo=" + TrainNo + ", StationCode=" + StationCode + ", StationName=" + StationName + ", Departure=" + Departure + ", Arrival=" + Arrival + ", Halt=" + Halt + ", Day=" + Day + ", Distance=" + Distance + '}';
    }

    public String trainList() {
        return "TrainsSchedule{" + "TrainNo=" + TrainNo + ", TrainName=" + TrainName + ", Source Station=" + SourceStation + ", Departure=" + Departure + ", Destination Station=" + DestinationStation + ", Arrival=" + Arrival + ", Day=" + Day + ", Distance=" + Distance + ", TripDuration=" + TripDuration + '}';
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, DatabaseException {

    }

}
