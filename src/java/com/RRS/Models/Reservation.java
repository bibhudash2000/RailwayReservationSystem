package com.RRS.Models;


public class Reservation {
    String ClassType;
    String DateOfJourney;
    String TrainNo;
    String FarePrice;
    String SourceStation;
    String DestinationStation;
    String BookedBy;

    public Reservation(String ClassType, String DateOfJourney, String TrainNo, String SourceStation, String DestinationStation, String BookedBy) {
        this.ClassType = ClassType;
        this.DateOfJourney = DateOfJourney;
        this.TrainNo = TrainNo;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
        this.BookedBy = BookedBy;
    }

    public Reservation(String ClassType, String DateOfJourney, String TrainNo, String FarePrice, String SourceStation, String DestinationStation, String BookedBy) {
        this.ClassType = ClassType;
        this.DateOfJourney = DateOfJourney;
        this.TrainNo = TrainNo;
        this.FarePrice = FarePrice;
        this.SourceStation = SourceStation;
        this.DestinationStation = DestinationStation;
        this.BookedBy = BookedBy;
    }
    
    

    public String getClassType() {
        return ClassType;
    }

    public void setClass(String ClassType) {
        this.ClassType = ClassType;
    }

    public String getDateOfJourney() {
        return DateOfJourney;
    }

    public void setDateOfJourney(String DateOfJourney) {
        this.DateOfJourney = DateOfJourney;
    }

    public String getTrainNo() {
        return TrainNo;
    }

    public void setTrainNo(String TrainNo) {
        this.TrainNo = TrainNo;
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

    public String getBookedBy() {
        return BookedBy;
    }

    public void setBookedBy(String BookedBy) {
        this.BookedBy = BookedBy;
    }

    public String getFarePrice() {
        return FarePrice;
    }
    
    
}
