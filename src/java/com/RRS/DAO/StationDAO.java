package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Exceptions.StationException;
import com.RRS.Models.Station;
import com.RRS.Models.Train;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StationDAO {

    private static final String SQL_ADD_NEW_STATION = "INSERT INTO STATIONS(STATION_CODE,STATION_NAME,CITY,STATE,SHORT_DESCRIPTION,ZONE) VALUES(?,?,?,?,?,?)";

    private static final String SQL_LIST_HALT_STATIONS_OF_TRAIN_BY_TRAIN_NO = "SELECT COUNT(SCHEDULE_ID) FROM TRAINS_SCHEDULE WHERE TRAIN_NO = ?";
    
    private static final String SQL_LIST_ALL_STATIONS = "SELECT STATIONS.STATION_ID,STATIONS.STATION_NAME,STATIONS.STATION_CODE,"
            + "STATIONS.CITY,STATIONS.STATE FROM STATIONS";

//    private static final String SQL_LIST_ALL_STATIONS_USING_INDEXES = "SELECT STATIONS.STATION_ID,STATIONS.STATION_NAME,STATIONS.STATION_CODE,"
//            + "STATIONS.CITY,STATIONS.STATE,STATIONS.ZONE FROM STATIONS ORDER BY STATIONS.STATION_CODE LIMIT ?,?";
//    
    private static final String SQL_LIST_ALL_STATIONS_USING_INDEXES = "SELECT * FROM STATIONS ORDER BY STATIONS.STATION_CODE LIMIT ?,?";

    private static final String SQL_VIEW_STATION_BY_ID = "SELECT * FROM STATIONS WHERE STATION_ID=?";

    private static final String SQL_VIEW_STATION_BY_CODE = "SELECT * FROM STATIONS WHERE STATION_CODE=?";

    private static final String SQL_VIEW_STATION_WITH_DESC_BY_CODE = "SELECT STATIONS.STATION_ID,STATIONS.STATION_CODE, STATIONS.STATION_NAME, STATIONS.CITY,"
            + " STATIONS.STATE, STATION_INFO.SHORT_DESCRIPTION "
            + "FROM STATIONS,STATION_INFO WHERE "
            + "STATIONS.STATION_ID = STATION_INFO.STATION_ID AND "
            + "STATION_CODE=?";
//    private static final String SQL_VIEW_STATION_WITH_DESC_BY_CODE = "SELECT * "
//            + "FROM STATIONS,STATION_INFO WHERE "
//            + "STATION_CODE=?";

    private static final String SQL_LIST_STATIONS_WITH_DESC = "SELECT STATIONS.STATION_ID,STATIONS.STATION_CODE, STATIONS.STATION_NAME, STATIONS.CITY,"
            + " STATIONS.STATE, STATION_INFO.SHORT_DESCRIPTION "
            + "FROM STATIONS,STATION_INFO WHERE "
            + "STATIONS.STATION_ID = STATION_INFO.STATION_ID";

    private static final String SQL_LIST_STATION_BY_CODE = "SELECT * FROM STATIONS WHERE "
            + "STATION_NAME LIKE CONCAT( '%',?,'%') OR STATION_CODE LIKE CONCAT( '%',?,'%')";

    private static final String SQL_LIST_STATION_BY_CITY_OR_STATE_NAME = "SELECT * FROM STATIONS WHERE "
            + "CITY LIKE CONCAT( '%',?,'%') OR STATE LIKE CONCAT( '%',?,'%') OR STATION_CODE LIKE CONCAT( '%',?,'%')";

    private static final String SQL_GET_LIST_OF_TRAINS_PASSING_BY_THE_STATION_FROM_STATION_ID = "SELECT TRAINS.TRAIN_NO,TRAINS.TRAIN_NAME "
            + "FROM TRAINS_SCHEDULE,TRAINS WHERE "
            + "TRAINS.TRAIN_NO = TRAINS_SCHEDULE.TRAIN_NO AND "
            + "TRAINS_SCHEDULE.STATION_ID = ?";

    private static final String SQL_GET_LIST_OF_TRAINS_PASSING_BY_THE_STATION_FROM_STATION_CODE = "SELECT TRAINS.TRAIN_NO,TRAINS.TRAIN_NAME FROM "
            + "TRAINS_SCHEDULE,TRAINS,STATIONS WHERE "
            + "TRAINS.TRAIN_NO = TRAINS_SCHEDULE.TRAIN_NO AND "
            + "TRAINS_SCHEDULE.STATION_ID = STATIONS.STATION_ID AND "
            + "TRAINS_SCHEDULE.ARRIVAL !='00:00:00' AND "
            + "TRAINS_SCHEDULE.DEPARTURE !='00:00:00' AND "
            + "STATIONS.STATION_CODE = ?";

    private static final String SQL_GET_LIST_OF_TRAINS_STARTING_FROM_STATION_BY_STATION_CODE = "SELECT TRAINS.TRAIN_NO,TRAINS.TRAIN_NAME FROM "
            + "TRAINS_SCHEDULE,TRAINS,STATIONS WHERE "
            + "TRAINS.TRAIN_NO = TRAINS_SCHEDULE.TRAIN_NO AND "
            + "TRAINS_SCHEDULE.STATION_ID = STATIONS.STATION_ID AND "
            + "TRAINS_SCHEDULE.ARRIVAL ='00:00:00' AND "
            + "STATIONS.STATION_CODE = ?";

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public static Boolean addNewStation(Station s) throws SQLException, ClassNotFoundException, DatabaseException {
        int i = 0;
        Boolean added = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_NEW_STATION);
        pst.setString(++i, s.getStation_Code());
        pst.setString(++i, s.getStation_Name());
        pst.setString(++i, s.getCity());
        pst.setString(++i, s.getState());
        pst.setString(++i, s.getShort_Description());
        pst.setString(++i, s.getZone());
        added = pst.executeUpdate() > 0;
        return added;
    }
    
    public static String countHaltStationsListOfTrain(String TrainNo) throws SQLException, ClassNotFoundException, DatabaseException {
        String count = "";
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_HALT_STATIONS_OF_TRAIN_BY_TRAIN_NO);
        pst.setString(1, TrainNo);
        rs = pst.executeQuery();
        while (rs.next()) {
            count = rs.getString(1);
        }
        con.close();
        return count;
    }

    public static List<Station> getStationsList() throws SQLException, ClassNotFoundException, DatabaseException {
        List<Station> list = new ArrayList();
        Station s;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_ALL_STATIONS);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer Station_ID = rs.getInt("STATIONS.STATION_ID");
            String Station_Code = rs.getString("STATIONS.STATION_CODE");
            String Station_Name = rs.getString("STATIONS.STATION_NAME");
            String City = rs.getString("STATIONS.CITY");
            String State = rs.getString("STATIONS.STATE");
            list.add(new Station(Station_ID, Station_Code, Station_Name, City, State));

        }
        con.close();
        return list;
    }

    public static List<Station> getStationsList(int startIndex, int endIndex) throws SQLException, ClassNotFoundException, DatabaseException {
        List<Station> list = new ArrayList();
        Station s;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_ALL_STATIONS_USING_INDEXES);
        pst.setInt(1, startIndex);
        pst.setInt(2, endIndex);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer Station_ID = rs.getInt("STATIONS.STATION_ID");
            String Station_Code = rs.getString("STATIONS.STATION_CODE");
            String Station_Name = rs.getString("STATIONS.STATION_NAME");
            String City = rs.getString("STATIONS.CITY");
            String State = rs.getString("STATIONS.STATE");
            list.add(new Station(Station_ID, Station_Code, Station_Name, City, State));
        }
        con.close();
        return list;
    }

    public static Integer countStationsList() throws SQLException, ClassNotFoundException, DatabaseException {
        Integer count = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_ALL_STATIONS);
        rs = pst.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        con.close();
        return count;
    }

    public static List<Station> getStationsList(String Input) throws SQLException, ClassNotFoundException, DatabaseException {
        List<Station> list = new ArrayList();
        Station s;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_STATION_BY_CODE);
        pst.setString(1, Input);
        pst.setString(2, Input);
        rs = pst.executeQuery();
        while (rs.next()) {
            s = new Station();
            s.setStation_ID(rs.getInt("STATION_ID"));
            s.setStation_Code(rs.getString("STATION_CODE"));
            s.setStation_Name(rs.getString("STATION_NAME"));

            list.add(s);
        }
        con.close();
        return list;
    }

    public static List<Station> getStationsListByCityOrStateName(String Input) throws SQLException, ClassNotFoundException, DatabaseException {
        List<Station> list = new ArrayList();
        Station s;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_STATION_BY_CITY_OR_STATE_NAME);
        pst.setString(1, Input);
        pst.setString(2, Input);
        pst.setString(3, Input);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer Station_ID = rs.getInt("STATIONS.STATION_ID");
            String Station_Code = rs.getString("STATIONS.STATION_CODE");
            String Station_Name = rs.getString("STATIONS.STATION_NAME");
            String City = rs.getString("STATIONS.CITY");
            String State = rs.getString("STATIONS.STATE");
            list.add(new Station(Station_ID, Station_Code, Station_Name, City, State));
        }
        con.close();
        return list;
    }

    public static Integer getStationIDByStationCode(String SCode) throws DatabaseException, SQLException {
        int i = 0;
        Integer StationID = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VIEW_STATION_BY_CODE);
        pst.setString(++i, SCode);
        rs = pst.executeQuery();
        if (rs.next()) {
            StationID = rs.getInt("STATION_ID");
        }
        return StationID;
    }

    public static List<Station> getStationsListWithDesc() throws SQLException, ClassNotFoundException, DatabaseException {
        List<Station> list = new ArrayList();
        Station s;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_STATIONS_WITH_DESC);
        rs = pst.executeQuery();
        while (rs.next()) {
            s = new Station();
            s.setStation_ID(rs.getInt("STATION_ID"));
            s.setStation_Code(rs.getString("STATION_CODE"));
            s.setStation_Name(rs.getString("STATION_NAME"));
            s.setCity(rs.getString("CITY"));
            s.setState(rs.getString("STATE"));
            s.setShort_Description(rs.getString("SHORT_DESCRIPTION"));

            list.add(s);
        }
        con.close();
        return list;
    }

    public static Station viewStation(int ID) throws DatabaseException, SQLException {
        int i = 0;
        Station s = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VIEW_STATION_BY_ID);
        pst.setInt(++i, ID);
        rs = pst.executeQuery();
        if (rs.next()) {
            Integer Station_ID = rs.getInt("STATION_ID");
            String Station_Code = rs.getString("STATION_CODE");
            String Station_Name = rs.getString("STATION_NAME");
            String City = rs.getString("CITY");
            String State = rs.getString("STATE");

            s = new Station(Station_ID, Station_Code, Station_Name, City, State);
        }
        return s;
    }

    public static Station viewStation(String StationCode) throws DatabaseException, SQLException, StationException {
        int i = 0;
        Station s = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VIEW_STATION_BY_CODE);
        pst.setString(++i, StationCode);
        rs = pst.executeQuery();
        if (rs.next()) {
            Integer Station_ID = rs.getInt("STATION_ID");
            String Station_Code = rs.getString("STATION_CODE");
            String Station_Name = rs.getString("STATION_NAME");
            String City = rs.getString("CITY");
            String State = rs.getString("STATE");

            s = new Station(Station_ID, Station_Code, Station_Name, City, State);
        } else {
            throw new StationException("No Stations found with this Station_Code : " + StationCode);
        }
        return s;
    }

    public static Station viewStationWithDesc(String StationCode) throws DatabaseException, SQLException, StationException {
        int i = 0;
        Station s = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VIEW_STATION_BY_CODE);
        pst.setString(++i, StationCode);
        rs = pst.executeQuery();
        if (rs.next()) {
            /* STATIONS.STATION_ID STATIONS.STATION_CODE STATIONS.STATION_NAME STATIONS.CITY STATIONS.STATE STATION_INFO.SHORT_DESCRIPTION*/
            Integer Station_ID = rs.getInt("STATIONS.STATION_ID");
            String Station_Code = rs.getString("STATIONS.STATION_CODE");
            String Station_Name = rs.getString("STATIONS.STATION_NAME");
            String City = rs.getString("STATIONS.CITY");
            String State = rs.getString("STATIONS.STATE");
            String Short_Description = rs.getString("STATIONS.SHORT_DESCRIPTION");
            s = new Station(Station_ID, Station_Code, Station_Name, City, State, Short_Description);
        } else {
            throw new StationException("No Stations found with Station_Code : " + StationCode);
        }
        return s;
    }

    public static List<Train> listStationPasstingByTrains(Integer StationID) throws SQLException, ClassNotFoundException, DatabaseException {
        int i = 0;
        List<Train> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_LIST_OF_TRAINS_PASSING_BY_THE_STATION_FROM_STATION_ID);
        pst.setInt(++i, StationID);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TrainNo = rs.getString("TRAINS.TRAIN_NO");
            String TrainName = rs.getString("TRAINS.TRAIN_NAME");
            list.add(new Train(TrainNo, TrainName));
        }
        con.close();
        return list;
    }

    public static List<Train> listStationPasstingByTrains(String StationCOde) throws SQLException, ClassNotFoundException, DatabaseException {
        int i = 0;
        List<Train> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_LIST_OF_TRAINS_PASSING_BY_THE_STATION_FROM_STATION_CODE);
        pst.setString(++i, StationCOde);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TrainNo = rs.getString("TRAINS.TRAIN_NO");
            String TrainName = rs.getString("TRAINS.TRAIN_NAME");

            list.add(new Train(TrainNo, TrainName));
        }

        con.close();
        return list;
    }

    public static List<Train> listTrainsStartingFromStation(String StationCOde) throws SQLException, ClassNotFoundException, DatabaseException {
        int i = 0;
        List<Train> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_LIST_OF_TRAINS_STARTING_FROM_STATION_BY_STATION_CODE);
        pst.setString(++i, StationCOde);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TrainNo = rs.getString("TRAINS.TRAIN_NO");
            String TrainName = rs.getString("TRAINS.TRAIN_NAME");

            list.add(new Train(TrainNo, TrainName));
        }

        con.close();
        return list;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, DatabaseException, StationException, ParseException {
//        List<Station> list = getStationsListByCityOrStateName("NDLS");
//        int i = 0;
//        for (Station s : list) {
//            System.out.println(s.toString());
//        }

        System.out.println(countHaltStationsListOfTrain("22823"));

    }
}
