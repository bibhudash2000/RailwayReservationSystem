package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.RecentTrainSearch;
import com.RRS.Models.User;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecentTrainSearchDAO {
    
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;
    
    private static final String SQL_UPDATE_RECENT_TRAIN_SEARCH = "UPDATE USERS_RECENT_TRAINS_SEARCH_HISTORY SET "
            + "SOURCE_STATION_ID = ?,DESTINATION_STATION_ID = ?,DATE = ?,CLASS_TYPE = ? WHERE USER_ID = ?";
    
    private static final String SQL_VIEW_RECENT_TRAIN_SEARCH_EXISTS = "SELECT * FROM USERS_RECENT_TRAINS_SEARCH_HISTORY WHERE USER_ID = ?";
    
    private static final String SQL_ADD_RECENT_TRAIN_SEARCH = "INSERT INTO USERS_RECENT_TRAINS_SEARCH_HISTORY"
            + "(USER_ID,SOURCE_STATION_ID,DESTINATION_STATION_ID,DATE,CLASS_TYPE) VALUES(?,?,?,?,?)";
    
    public static void addRecentTrainSearch(RecentTrainSearch search) throws DatabaseException, SQLException {
        int i = 0;
        con = Database.connect();
        Boolean checkIfRecordExists = checkIfRecentTrainSearchExists(search.getUSER_ID());
        if (checkIfRecordExists) {
            updateRecentTrainSearch(search);
        } else {
            pst = con.prepareStatement(SQL_ADD_RECENT_TRAIN_SEARCH);
            pst.setString(++i, search.getUSER_ID());
            pst.setInt(++i, search.getSOURCE_STATION_ID());
            pst.setInt(++i, search.getDESTINATION_STATION_ID());
            pst.setString(++i, search.getDATE());
            pst.setString(++i, search.getCLASS_TYPE());
            pst.executeUpdate();
            con.close();
            System.out.println("Recent Search Added");
        }
        
    }
    
    public static Boolean checkIfRecentTrainSearchExists(String UserID) throws SQLException, DatabaseException {
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VIEW_RECENT_TRAIN_SEARCH_EXISTS);
        pst.setString(++i, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void updateRecentTrainSearch(RecentTrainSearch search) throws DatabaseException, SQLException {
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_UPDATE_RECENT_TRAIN_SEARCH);
        pst.setInt(++i, search.getSOURCE_STATION_ID());
        pst.setInt(++i, search.getDESTINATION_STATION_ID());
        pst.setString(++i, search.getDATE());
        pst.setString(++i, search.getCLASS_TYPE());
        pst.setString(++i, search.getUSER_ID());
        pst.executeUpdate();
        con.close();
        System.out.println("Recent Search Updated");
    }
    
    public static RecentTrainSearch viewRecentTrainSearches(String UserID) throws DatabaseException, SQLException {
        int i = 0;
        RecentTrainSearch search = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VIEW_RECENT_TRAIN_SEARCH_EXISTS);
        pst.setString(++i, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String USER_ID = rs.getString(RecentTrainSearch.SQL_USER_ID);
            Integer SOURCE_STATION_ID = rs.getInt(RecentTrainSearch.SQL_SOURCE_STATION_ID);
            Integer DESTINATION_STATION_ID = rs.getInt(RecentTrainSearch.SQL_DESTINATION_STATION_ID);
            String DATE = rs.getString(RecentTrainSearch.SQL_DATE);
            String CLASS_TYPE = rs.getString(RecentTrainSearch.SQL_CLASS_TYPE);
            search = new RecentTrainSearch(USER_ID, SOURCE_STATION_ID, DESTINATION_STATION_ID, DATE, CLASS_TYPE);
        }
        return search;
    }
    
    public static RecentTrainSearch viewRecentTrainSearches(User u) throws DatabaseException, SQLException {
        int i = 0;
        RecentTrainSearch search = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VIEW_RECENT_TRAIN_SEARCH_EXISTS);
        pst.setString(++i, u.getUserID());
        rs = pst.executeQuery();
        if (rs.next()) {
            String USER_ID = rs.getString(RecentTrainSearch.SQL_USER_ID);
            Integer SOURCE_STATION_ID = rs.getInt(RecentTrainSearch.SQL_SOURCE_STATION_ID);
            Integer DESTINATION_STATION_ID = rs.getInt(RecentTrainSearch.SQL_DESTINATION_STATION_ID);
            String DATE = rs.getString(RecentTrainSearch.SQL_DATE);
            String CLASS_TYPE = rs.getString(RecentTrainSearch.SQL_CLASS_TYPE);
            search = new RecentTrainSearch(USER_ID, SOURCE_STATION_ID, DESTINATION_STATION_ID, DATE, CLASS_TYPE);
        }
        return search;
    }
}
