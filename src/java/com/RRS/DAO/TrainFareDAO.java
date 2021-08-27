package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Exceptions.DefaultException;
import com.RRS.Models.Coach;
import com.RRS.Models.TrainFare;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainFareDAO {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    private static final String SQL_GET_TRAIN_FARE_BY_FARE_ID = "SELECT * FROM TRAIN_FARES WHERE FARE_ID = ?";

    public static TrainFare getTrainFareByFareID(String FareID) throws DatabaseException, SQLException, DefaultException {
        int i = 0;
        TrainFare tf = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TRAIN_FARE_BY_FARE_ID);
        pst.setString(++i, FareID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String FARE_ID = rs.getString(TrainFare.SQL_FARE_ID);
            String TRAIN_NO = rs.getString(TrainFare.SQL_TRAIN_NO);
            String COACH_ID = rs.getString(TrainFare.SQL_COACH_ID);
            double MINIMUM_FARE = rs.getDouble(TrainFare.SQL_MINIMUM_FARE);
            int MINIMUM_DISTANCE = rs.getInt(TrainFare.SQL_MINIMUM_DISTANCE);
            double FARE_PER_KM = rs.getDouble(TrainFare.SQL_FARE_PER_KM);
            tf = new TrainFare(FARE_ID, TRAIN_NO, COACH_ID, MINIMUM_FARE, MINIMUM_DISTANCE, FARE_PER_KM);
        } else {
            throw new DefaultException("Invalid Fare ID :" + FareID);
        }

        return tf;
    }
    
    public static void main(String[] args) throws DatabaseException, SQLException, DefaultException {
        TrainFare tf = getTrainFareByFareID("12");
        System.out.println(tf.getCOACH_ID());
    }

}
