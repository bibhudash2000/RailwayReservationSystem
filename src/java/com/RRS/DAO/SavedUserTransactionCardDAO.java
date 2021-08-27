package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Exceptions.DefaultException;
import com.RRS.Models.SavedUserTransactionCard;
import com.RRS.Models.User;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SavedUserTransactionCardDAO {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    private static final String SQL_ADD_USER_NEW_TRANSACTION_CARD = "INSERT INTO "
            + "SAVED_USER_TRANSACTION_CARDS(USER_ID, CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV, CARD_HOLDER_NAME)"
            + "VALUES(?,?,?,?,?,?)";

    private static final String SQL_REMOVE_USER_TRANSACTION_CARD = "DELETE FROM SAVED_USER_TRANSACTION_CARDS WHERE TRANSACTION_CARD_ID = ?";

    private static final String SQL_VERIFY_CARD_CVV_NUMBER = "SELECT CVV FROM SAVED_USER_TRANSACTION_CARDS WHERE USER_ID = ?";
    
    private static final String SQL_GET_USER_TRANSACTION_CARD = "SELECT * FROM SAVED_USER_TRANSACTION_CARDS WHERE USER_ID = ?";

    public static Boolean addNewTransactionCard(SavedUserTransactionCard card) throws DatabaseException, SQLException {
        int i = 0;
        Boolean added = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_USER_NEW_TRANSACTION_CARD);
        pst.setString(++i, card.getUSER_ID());
        pst.setString(++i, card.getCARD_NUMBER());
        pst.setString(++i, card.getEXPIRY_MONTH());
        pst.setString(++i, card.getEXPIRY_YEAR());
        pst.setString(++i, card.getCVV());
        pst.setString(++i, card.getCARD_HOLDER_NAME());
        added = pst.executeUpdate() > 0;
        return added;
    }

    public static Boolean removeTransactionCard(SavedUserTransactionCard card) throws DatabaseException, SQLException {
        int i = 0;
        Boolean removed = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_REMOVE_USER_TRANSACTION_CARD);
        pst.setString(++i, card.getTRANSACTION_CARD_ID());
        removed = pst.executeUpdate() > 0;
        return removed;
    }

    public static List<SavedUserTransactionCard> listUserTransactionCards(User user) throws DatabaseException, SQLException {
        List<SavedUserTransactionCard> list = new ArrayList();
        SavedUserTransactionCard card = null;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_USER_TRANSACTION_CARD);
        pst.setString(++i, user.getUserID());
        rs = pst.executeQuery();
        while (rs.next()) {
            String TRANSACTION_CARD_ID = rs.getString(SavedUserTransactionCard.SQL_TRANSACTION_CARD_ID);
            String USER_ID = rs.getString(SavedUserTransactionCard.SQL_USER_ID);
            String CARD_NUMBER = rs.getString(SavedUserTransactionCard.SQL_CARD_NUMBER);
            String EXPIRY_MONTH = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_MONTH);
            String EXPIRY_YEAR = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_YEAR);
            String CVV = rs.getString(SavedUserTransactionCard.SQL_CVV);
            String CARD_HOLDER_NAME = rs.getString(SavedUserTransactionCard.SQL_CARD_HOLDER_NAME);
            card = new SavedUserTransactionCard(TRANSACTION_CARD_ID, USER_ID, CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV, CARD_HOLDER_NAME);
            list.add(card);
        }
        return list;
    }

    public static List<SavedUserTransactionCard> listUserTransactionCards(String USER_ID) throws DatabaseException, SQLException {
        List<SavedUserTransactionCard> list = new ArrayList();
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_USER_TRANSACTION_CARD);
        pst.setString(++i, USER_ID);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TRANSACTION_CARD_ID = rs.getString(SavedUserTransactionCard.SQL_TRANSACTION_CARD_ID);
            USER_ID = rs.getString(SavedUserTransactionCard.SQL_USER_ID);
            String CARD_NUMBER = rs.getString(SavedUserTransactionCard.SQL_CARD_NUMBER);
            String EXPIRY_MONTH = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_MONTH);
            String EXPIRY_YEAR = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_YEAR);
            String CVV = rs.getString(SavedUserTransactionCard.SQL_CVV);
            String CARD_HOLDER_NAME = rs.getString(SavedUserTransactionCard.SQL_CARD_HOLDER_NAME);
            SavedUserTransactionCard card = new SavedUserTransactionCard(TRANSACTION_CARD_ID, USER_ID, CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV, CARD_HOLDER_NAME);
            list.add(card);
        }
        return list;
    }

    public static SavedUserTransactionCard getUserSavedTransactionCard(String UserID) throws DatabaseException, SQLException, DefaultException {
        SavedUserTransactionCard card = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_USER_TRANSACTION_CARD);
        pst.setString(1, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String TRANSACTION_CARD_ID = rs.getString(SavedUserTransactionCard.SQL_TRANSACTION_CARD_ID);
            String USER_ID = rs.getString(SavedUserTransactionCard.SQL_USER_ID);
            String CARD_NUMBER = rs.getString(SavedUserTransactionCard.SQL_CARD_NUMBER);
            String EXPIRY_MONTH = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_MONTH);
            String EXPIRY_YEAR = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_YEAR);
            String CVV = rs.getString(SavedUserTransactionCard.SQL_CVV);
            String CARD_HOLDER_NAME = rs.getString(SavedUserTransactionCard.SQL_CARD_HOLDER_NAME);
            card = new SavedUserTransactionCard(TRANSACTION_CARD_ID, USER_ID, CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV, CARD_HOLDER_NAME);
        } else {
            throw new DefaultException("No Card found");
        }
        
        con.close();
        return card;
    }
    
    public static SavedUserTransactionCard getUserSavedTransactionCard(User user) throws DatabaseException, SQLException, DefaultException {
        SavedUserTransactionCard card = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_USER_TRANSACTION_CARD);
        pst.setString(1, user.getUserID());
        rs = pst.executeQuery();
        if (rs.next()) {
            String TRANSACTION_CARD_ID = rs.getString(SavedUserTransactionCard.SQL_TRANSACTION_CARD_ID);
            String USER_ID = rs.getString(SavedUserTransactionCard.SQL_USER_ID);
            String CARD_NUMBER = rs.getString(SavedUserTransactionCard.SQL_CARD_NUMBER);
            String EXPIRY_MONTH = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_MONTH);
            String EXPIRY_YEAR = rs.getString(SavedUserTransactionCard.SQL_EXPIRY_YEAR);
            String CVV = rs.getString(SavedUserTransactionCard.SQL_CVV);
            String CARD_HOLDER_NAME = rs.getString(SavedUserTransactionCard.SQL_CARD_HOLDER_NAME);
            card = new SavedUserTransactionCard(TRANSACTION_CARD_ID, USER_ID, CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV, CARD_HOLDER_NAME);
        } else {
            throw new DefaultException("No Card found");
        }
        
        con.close();
        return card;
    }

    public static Boolean verifyCardCvvNumber(String CVV, String UserID) throws DatabaseException, SQLException, DefaultException {
        Boolean verified = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VERIFY_CARD_CVV_NUMBER);
        pst.setString(1, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String tempCVV = rs.getString(SavedUserTransactionCard.SQL_CVV);
            verified = CVV.equals(tempCVV);
        } else {
            throw new DefaultException("No Card found");
        }
        return verified;
    }

    public static void main(String[] args) throws DatabaseException, SQLException, DefaultException {
//        List<SavedUserTransactionCard> list = listUserTransactionCards("9");
//        System.out.println(list.size());
        //System.out.println(verifyCardCvvNumber("139", "212805084146"));
        SavedUserTransactionCard card = getUserSavedTransactionCard("212805084146");
        System.out.println(card.toString());
    }
}
