package com.RRS.Models;

public class Passenger extends Ticket {

    private Integer PID;
    private String Name;
    private String Age;
    private String Gender;
    private String SeatNo;
    private Quota Quota;

    
    public Passenger(Integer PID, String Name, String Age, String Gender, String SeatNo, Quota Quota, Integer TicketNo, String PNR_No, String TrainNo, Integer SourceStationID, Integer DestinationStationID, String SourceStationCode, String DestinationStationCode, String Date_Of_Journey, Integer TotalPersons, String Class_Type, ReservationStatus Status, String BookedBy) {
        super(TicketNo, PNR_No, TrainNo, SourceStationID, DestinationStationID, SourceStationCode, DestinationStationCode, Date_Of_Journey, TotalPersons, Class_Type, Status, BookedBy);
        this.PID = PID;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.SeatNo = SeatNo;
        this.Quota = Quota;
    }

    public Passenger(Integer PID, String Name, String Age, String Gender, String SeatNo, Quota Quota) {
        this.PID = PID;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.SeatNo = SeatNo;
        this.Quota = Quota;
    }

    
    
    public Passenger(Integer PID, String PNR_No, String Name, String Age, String Gender, String SeatNo) {
        this.PID = PID;
        this.PNR_No = PNR_No;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.SeatNo = SeatNo;
    }

    public Passenger(String PNR_No, String Name, String Age, String Gender, String SeatNo) {
        this.PNR_No = PNR_No;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.SeatNo = SeatNo;
    }

    public Passenger(int TicketNo, String Name, String Age, String Gender, Quota Quota) {
        this.TicketNo = TicketNo;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.Quota = Quota;
    }
    public Passenger(int TicketNo, String Name, String Age, String Gender) {
        this.TicketNo = TicketNo;
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
    }
    public Passenger(String Name, String Age, String Gender) {
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
    }

    public Passenger() {
    }

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String SeatNo) {
        this.SeatNo = SeatNo;
    }

    public Quota getQuota() {
        return Quota;
    }

    public void setQuota(Quota Quota) {
        this.Quota = Quota;
    }

    @Override
    public String toString() {
        return "Passenger{" + "PID=" + PID + ", Name=" + Name + ", Age=" + Age + ", Gender=" + Gender + ", SeatNo=" + SeatNo + ", Quota=" + Quota + '}';
    }

   

}
