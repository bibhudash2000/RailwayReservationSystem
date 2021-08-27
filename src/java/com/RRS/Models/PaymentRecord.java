package com.RRS.Models;

/*PAYMENT_RECORD_ID	TRANSACTION_ID	TICKET_NO	PAYMENT_STATUS_ID*/
public class PaymentRecord {

    public static final String SQL_PAYMENT_RECORD_ID = "PAYMENT_RECORD_ID";
    public static final String SQL_TRANSACTION_ID = "TRANSACTION_ID";
    public static final String SQL_TICKET_NO = "TICKET_NO";
    public static final String SQL_PAYMENT_STATUS_ID = "PAYMENT_STATUS_ID";

    String PaymentRecordID;
    String TransactionID;
    String TicketNo;
    String PaymentStatusID;

    public PaymentRecord(String TransactionID, String PaymentStatusID) {
        this.TransactionID = TransactionID;
        this.PaymentStatusID = PaymentStatusID;
    }

    
    public PaymentRecord(String TransactionID, String TicketNo, String PaymentStatusID) {
        this.TransactionID = TransactionID;
        this.TicketNo = TicketNo;
        this.PaymentStatusID = PaymentStatusID;
    }
    

    public PaymentRecord(String PaymentRecordID, String TransactionID, String TicketNo, String PaymentStatusID) {
        this.PaymentRecordID = PaymentRecordID;
        this.TransactionID = TransactionID;
        this.TicketNo = TicketNo;
        this.PaymentStatusID = PaymentStatusID;
    }

    public String getPaymentRecordID() {
        return PaymentRecordID;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public String getTicketNo() {
        return TicketNo;
    }

    public String getPaymentStatusID() {
        return PaymentStatusID;
    }

}
