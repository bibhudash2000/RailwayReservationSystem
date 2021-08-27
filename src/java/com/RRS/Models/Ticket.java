package com.RRS.Models;

/*
TICKET_NO
PNR_NO
TRAIN_NO
SOURCE
DESTINATION
FARE_ID
DATE_OF_JOURNEY
TOTAL_PERSONS
AMOUNT
RESERVATION_STATUS
BOOKED_BY
 */
public class Ticket {

    public static final String SQL_TICKET_NO = "TICKET_NO";
    public static final String SQL_PNR_NO = "PNR_NO";
    public static final String SQL_TRAIN_NO = "TRAIN_NO";
    public static final String SQL_SOURCE = "SOURCE";
    public static final String SQL_DESTINATION = "DESTINATION";
    public static final String SQL_FARE_ID = "FARE_ID";
    public static final String SQL_DATE_OF_JOURNEY = "DATE_OF_JOURNEY";
    public static final String SQL_TOTAL_PERSONS = "TOTAL_PERSONS";
    public static final String SQL_AMOUNT = "AMOUNT";
    public static final String SQL_RESERVATION_STATUS = "RESERVATION_STATUS";
    public static final String SQL_BOOKED_BY = "BOOKED_BY";

    protected Integer TicketNo;
    protected String PNR_No;
    protected String TrainNo;
    protected Integer SourceStationID;
    protected Integer DestinationStationID;
    protected String SourceStationCode;
    protected String DestinationStationCode;
    protected String Date_Of_Journey;
    protected Integer TotalPersons;
    protected Double Amount;
    protected Double NetAmount;
    protected String Fare_ID;
    protected ReservationStatus Status;
    protected String DepartureTime;
    protected String BookedBy;

    public Ticket(Integer TicketNo, String PNR_No, String Date_Of_Journey, Integer TotalPersons, String Fare_ID, ReservationStatus Status) {
        this.TicketNo = TicketNo;
        this.PNR_No = PNR_No;
        this.Date_Of_Journey = Date_Of_Journey;
        this.TotalPersons = TotalPersons;
        this.Fare_ID = Fare_ID;
        this.Status = Status;
    }

    public Ticket(Integer TicketNo, String PNR_No, String Date_Of_Journey, String DepartureTime, Integer TotalPersons, String Fare_ID, ReservationStatus Status) {
        this.TicketNo = TicketNo;
        this.PNR_No = PNR_No;
        this.Date_Of_Journey = Date_Of_Journey;
        this.DepartureTime = DepartureTime;
        this.TotalPersons = TotalPersons;
        this.Fare_ID = Fare_ID;
        this.Status = Status;
    }

    public Ticket(Integer TicketNo, String PNR_No, String TrainNo, Integer SourceStationID, Integer DestinationStationID, String Date_Of_Journey, Integer TotalPersons, ReservationStatus Status, String BookedBy) {
        this.TicketNo = TicketNo;
        this.PNR_No = PNR_No;
        this.TrainNo = TrainNo;
        this.SourceStationID = SourceStationID;
        this.DestinationStationID = DestinationStationID;
        this.Date_Of_Journey = Date_Of_Journey;
        this.TotalPersons = TotalPersons;
        this.Status = Status;
        this.BookedBy = BookedBy;
    }

    public Ticket(Integer TicketNo, String PNR_No, String TrainNo, Integer SourceStationID, Integer DestinationStationID, String SourceStationCode, String DestinationStationCode, String Date_Of_Journey, Integer TotalPersons, String Fare_ID, ReservationStatus Status, String BookedBy) {
        this.TicketNo = TicketNo;
        this.PNR_No = PNR_No;
        this.TrainNo = TrainNo;
        this.SourceStationID = SourceStationID;
        this.DestinationStationID = DestinationStationID;
        this.SourceStationCode = SourceStationCode;
        this.DestinationStationCode = DestinationStationCode;
        this.Date_Of_Journey = Date_Of_Journey;
        this.TotalPersons = TotalPersons;
        this.Fare_ID = Fare_ID;
        this.Status = Status;
        this.BookedBy = BookedBy;
    }

    public Ticket(String PNR_No, String TrainNo, Integer SourceStationID, Integer DestinationStationID, String Date_Of_Journey, Integer TotalPersons, ReservationStatus Status, String BookedBy) {
        this.PNR_No = PNR_No;
        this.TrainNo = TrainNo;
        this.SourceStationID = SourceStationID;
        this.DestinationStationID = DestinationStationID;
        this.Date_Of_Journey = Date_Of_Journey;
        this.TotalPersons = TotalPersons;
        this.Status = Status;
        this.BookedBy = BookedBy;
    }

    public Ticket(String PNR_No, String TrainNo, Integer SourceStationID, Integer DestinationStationID, String SourceStationCode, String DestinationStationCode, String Date_Of_Journey, Integer TotalPersons, String Fare_ID, ReservationStatus Status, String BookedBy) {
        this.PNR_No = PNR_No;
        this.TrainNo = TrainNo;
        this.SourceStationID = SourceStationID;
        this.DestinationStationID = DestinationStationID;
        this.SourceStationCode = SourceStationCode;
        this.DestinationStationCode = DestinationStationCode;
        this.Date_Of_Journey = Date_Of_Journey;
        this.TotalPersons = TotalPersons;
        this.Fare_ID = Fare_ID;
        this.Status = Status;
        this.BookedBy = BookedBy;
    }

    public Ticket() {
    }

    public Ticket(String PNR_No, String TrainNo, Integer SourceStationID, Integer DestinationStationID,
            String Fare_ID, String Date_Of_Journey, Integer TotalPersons, Double Amount, ReservationStatus Status, String BookedBy) {
        this.PNR_No = PNR_No;
        this.TrainNo = TrainNo;
        this.SourceStationID = SourceStationID;
        this.DestinationStationID = DestinationStationID;
        this.Date_Of_Journey = Date_Of_Journey;
        this.TotalPersons = TotalPersons;
        this.Amount = Amount;
        this.Fare_ID = Fare_ID;
        this.Status = Status;
        this.BookedBy = BookedBy;
    }

    public Ticket(String PNR_NO, String TrainNo, Integer srcStationID,
            Integer destStationID, String SrcStationCode, String DestStationCode,
            String Date, Integer TotalPassengers, Double TicketPrice, ReservationStatus reservationStatus,
            String UserID) {
        this.PNR_No = PNR_NO;
        this.TrainNo = TrainNo;
        this.SourceStationID = srcStationID;
        this.DestinationStationID = destStationID;
        this.SourceStationCode = SrcStationCode;
        this.DestinationStationCode = DestStationCode;
        this.Date_Of_Journey = Date;
        this.TotalPersons = TotalPassengers;
        this.Amount = TicketPrice;
        this.Status = reservationStatus;
        this.BookedBy = UserID;
    }

    public Ticket(String PNR_NO, String TrainNo, Integer srcStationID,
            Integer destStationID, String SrcStationCode, String DestStationCode,
            String Date, Integer TotalPassengers, String ClassType, Double TicketPrice, Double NetAmount, ReservationStatus reservationStatus,
            String UserID) {
        this.PNR_No = PNR_NO;
        this.TrainNo = TrainNo;
        this.SourceStationID = srcStationID;
        this.DestinationStationID = destStationID;
        this.SourceStationCode = SrcStationCode;
        this.DestinationStationCode = DestStationCode;
        this.Date_Of_Journey = Date;
        this.TotalPersons = TotalPassengers;
        this.Fare_ID = ClassType;
        this.Amount = TicketPrice;
        this.NetAmount = NetAmount;
        this.Status = reservationStatus;
        this.BookedBy = UserID;
    }

    public Ticket(Integer TicketNo, String PNR_No, String TrainNo,
            Integer SourceStationID, Integer DestinationStationID, String Date_Of_Journey,
            Integer TotalPersons, Double Amount, String Fare_ID, ReservationStatus Status, String BookedBy) {
        this.PNR_No = PNR_No;
        this.TicketNo = TicketNo;
        this.TrainNo = TrainNo;
        this.SourceStationID = SourceStationID;
        this.DestinationStationID = DestinationStationID;
        this.Date_Of_Journey = Date_Of_Journey;
        this.TotalPersons = TotalPersons;
        this.Fare_ID = Fare_ID;
        this.Amount = Amount;
        this.Status = Status;
        this.BookedBy = BookedBy;
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

    public String getFare_ID() {
        return Fare_ID;
    }

    public void setFare_ID(String Fare_ID) {
        this.Fare_ID = Fare_ID;
    }

    public Integer getTicketNo() {
        return TicketNo;
    }

    public void setTicketNo(Integer TicketNo) {
        this.TicketNo = TicketNo;
    }

    public String getPNR_No() {
        return PNR_No;
    }

    public void setPNR_No(String PNR_No) {
        this.PNR_No = PNR_No;
    }

    public String getTrainNo() {
        return TrainNo;
    }

    public void setTrainNo(String TrainNo) {
        this.TrainNo = TrainNo;
    }

    public Integer getSourceStationID() {
        return SourceStationID;
    }

    public void setSourceStationID(Integer SourceStationID) {
        this.SourceStationID = SourceStationID;
    }

    public Integer getDestinationStationID() {
        return DestinationStationID;
    }

    public void setDestinationStationID(Integer DestinationStationID) {
        this.DestinationStationID = DestinationStationID;
    }

    public String getDate_Of_Journey() {
        return Date_Of_Journey;
    }

    public void setDate_Of_Journey(String Date_Of_Journey) {
        this.Date_Of_Journey = Date_Of_Journey;
    }

    public Integer getTotalPersons() {
        return TotalPersons;
    }

    public void setTotalPersons(Integer TotalPersons) {
        this.TotalPersons = TotalPersons;
    }

    public ReservationStatus getStatus() {
        return Status;
    }

    public void setStatus(ReservationStatus Status) {
        this.Status = Status;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String DepartureTime) {
        this.DepartureTime = DepartureTime;
    }

    public String getBookedBy() {
        return BookedBy;
    }

    public void setBookedBy(String BookedBy) {
        this.BookedBy = BookedBy;
    }

    public Double getAmount() {
        return Amount;
    }

    public Double getNetAmount() {
        return NetAmount;
    }

    @Override
    public String toString() {
        return "Ticket{" + "TicketNo=" + TicketNo + ", PNR_No=" + PNR_No + ", TrainNo=" + TrainNo + ", SourceStationID=" + SourceStationID + ", DestinationStationID=" + DestinationStationID + ", SourceStationCode=" + SourceStationCode + ", DestinationStationCode=" + DestinationStationCode + ", Date_Of_Journey=" + Date_Of_Journey + ", TotalPersons=" + TotalPersons + ", Amount=" + Amount + ", Fare_ID=" + Fare_ID + ", Status=" + Status + ", DepartureTime=" + DepartureTime + ", BookedBy=" + BookedBy + '}';
    }

}
