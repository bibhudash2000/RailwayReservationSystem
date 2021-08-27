package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.TrainsSchedule;
import com.RRS.Models.Zone;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ZoneDAO {

    private static final String SQL_GET_ZONES_LIST = "SELECT * FROM ZONES";

    private static final String SQL_ADD_ZONES_TO_LIST = "INSERT INTO ZONES(ZONE_ABBREVIATION,ZONE_NAME,HEADQUATER,DIVISIONS) VALUES(?,?,?,?)";

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public static List<Zone> getZoneList() throws SQLException, DatabaseException {
        List<Zone> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_ZONES_LIST);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer Zone_ID = rs.getInt("ZONE_ID");
            String Zone_Abbreviation = rs.getString("ZONE_ABBREVIATION");
            String Zone_Name = rs.getString("ZONE_NAME");
            String Zone_Headquater = rs.getString("HEADQUATER");
            String Zone_Divisions= rs.getString("DIVISONS");
            list.add(new Zone(Zone_ID, Zone_Abbreviation, Zone_Name, Zone_Headquater, Zone_Divisions));
        }
        return list;
    }

    public static void addZonesInList(List<Zone> z) throws DatabaseException, SQLException {

        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_ZONES_TO_LIST);
        con.setAutoCommit(false);
        for (Iterator< Zone> iterator = z.iterator(); iterator.hasNext();) {
            Zone zone = (Zone) iterator.next();
            pst.setString(1, zone.getZone_Abbreviation());
            pst.setString(2, zone.getZone_Name());
            pst.setString(3, zone.getZone_Headquater());
            pst.setString(4, zone.getZone_Divisions());
            pst.addBatch();
        }
        int[] updateCounts = pst.executeBatch();
        System.out.println(Arrays.toString(updateCounts));
        con.commit();
        con.setAutoCommit(true);
        System.out.println("Added to zone");

    }

    public static void main(String[] args) throws SQLException, DatabaseException {
        List<Zone> list = getZoneList();
        for(Zone z : list){
            System.out.println(z.toString());
        }
    }

}
