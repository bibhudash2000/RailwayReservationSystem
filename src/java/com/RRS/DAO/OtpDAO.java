package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.OTP;
import com.RRS.Utilities.Database;
import com.RRS.Utilities.IDGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OtpDAO {

    private static final String SQL_ADD_NEW_OTP = "INSERT INTO OTP_BANK(OTP,GENERATED_FOR) VALUES(?,?)";
    private static final String SQL_GET_OTP_BY_GENERATED_FOR = "SELECT * FROM otp_bank where GENERATED_FOR = ? ORDER BY OTP_ID DESC LIMIT 1";

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public static Boolean storeOTP(OTP otp) throws DatabaseException, SQLException {
        int i = 0;
        Boolean isAdded = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_NEW_OTP);
        pst.setString(++i, otp.getOTP());
        pst.setString(++i, otp.getGENERATED_FOR());
        isAdded = pst.executeUpdate() > 0;
        return isAdded;
    }

    public static OTP fetchOTP(String UserID) throws DatabaseException, SQLException {
        int i = 0;
        OTP otp = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_OTP_BY_GENERATED_FOR);
        pst.setString(++i, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String OTP_ID = rs.getString("OTP_ID");
            String OTP = rs.getString("OTP");
            String GENERATED_FOR = rs.getString("GENERATED_FOR");
            otp = new OTP(OTP_ID, OTP, GENERATED_FOR);
        }
        return otp;
    }

    public static void main(String[] args) throws DatabaseException, SQLException {

        OTP code = fetchOTP("211205114948");
        if (code != null) {
            System.out.println(code.getGENERATED_FOR());
            System.out.println(code.getOTP());
        }

    }

}
