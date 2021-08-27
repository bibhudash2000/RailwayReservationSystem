package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.PaymentRecord;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentRecordDAO {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static final String SQL_ADD_PAYMENT_RECORD = "INSERT INTO PAYMENT_RECORDS(TRANSACTION_ID,TICKET_NO,PAYMENT_STATUS_ID) VALUES(?,?,?)";
    

    static {
        try {
            con = Database.connect();
        } catch (DatabaseException ex) {
            Logger.getLogger(PaymentRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addPaymentRecord(PaymentRecord record) throws DatabaseException, SQLException {
        String TransactionID = record.getTransactionID();
        Boolean paymentRecordAdded = PaymentRecordDAO.addNewPaymentRecord(record);
        if (paymentRecordAdded) {
            System.out.println("Payment Record Added for Transaction ID : " + TransactionID);
        } else {
            System.out.println("Error in adding the Payment Record of Transaction ID " + TransactionID);
        }
    }

    public static Boolean addNewPaymentRecord(PaymentRecord record) throws DatabaseException, SQLException {
        Boolean added = false;
        int i = 0;
        pst = con.prepareStatement(SQL_ADD_PAYMENT_RECORD);
        pst.setString(++i, record.getTransactionID());
        pst.setString(++i, record.getTicketNo());
        pst.setString(++i, record.getPaymentStatusID());
        added = pst.executeUpdate() > 0;
        return added;
    }

    public static void main(String[] args) {
        try {
            PaymentRecord rec = new PaymentRecord(SQL_ADD_PAYMENT_RECORD, "111", "22", "1");
            System.out.println(addNewPaymentRecord(rec));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
