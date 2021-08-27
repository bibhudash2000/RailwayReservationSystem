package com.RRS.Models;


public class TicketBookingStatus {
    String TransactionID;
    String Amount;
    String PNR;
    BookStatus BookingStatus;

    public TicketBookingStatus(String TransactionID, String Amount, BookStatus BookingStatus) {
        this.TransactionID = TransactionID;
        this.Amount = Amount;
        this.BookingStatus = BookingStatus;
    }

    public TicketBookingStatus(String TransactionID, String Amount, String PNR, BookStatus BookingStatus) {
        this.TransactionID = TransactionID;
        this.Amount = Amount;
        this.PNR = PNR;
        this.BookingStatus = BookingStatus;
    }
    
    
    

    public String getTransactionID() {
        return TransactionID;
    }

    public String getAmount() {
        return Amount;
    }

    public BookStatus getBookingStatus() {
        return BookingStatus;
    }

    public String getPNR() {
        return PNR;
    }
    
    
}
