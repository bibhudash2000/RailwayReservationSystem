package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Exceptions.DefaultException;
import com.RRS.Exceptions.StationException;
import com.RRS.Models.CheckAvailableCoaches;
import com.RRS.Models.Seat;
import com.RRS.Models.Train;
import com.RRS.Models.TrainCategory;
import com.RRS.Models.TrainCoaches;
import com.RRS.Models.TrainsSchedule;
import com.RRS.Models.Week;
import com.RRS.Utilities.Database;
import com.RRS.Utilities.DateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {

    private static final String SQL_ADD_TRAIN = "INSERT INTO trains"
            + "(TRAIN_NO, TRAIN_NAME, TRAIN_CATEGORY, SOURCE_STATION, DESTINATION_STATION)"
            + " VALUES (?,?,?,?,?)";

    private static final String SQL_GET_TRAIN_INFO_BY_TRAIN_NO = "SELECT TRAIN_NAME,TRAIN_NO,SOURCE_STATION,DESTINATION_STATION FROM TRAINS WHERE TRAIN_NO = ?";

    private static final String SQL_GET_TRAIN_TOTAL_TRIP_DISTANCE_BY_TRAIN_NO = "SELECT MAX(DISTANCE) FROM TRAINS_SCHEDULE WHERE TRAINS_SCHEDULE.TRAIN_NO = ?";

    private static final String SQL_GET_TRAIN_TOTAL_TRIP_DURATION = "SELECT SRC.DEPARTURE,DEST.ARRIVAL,DEST.DAY FROM "
            + "TRAINS_SCHEDULE AS SRC,TRAINS_SCHEDULE AS DEST WHERE "
            + "SRC.ARRIVAL = DEST.DEPARTURE AND "
            + "SRC.TRAIN_NO = DEST.TRAIN_NO AND SRC.TRAIN_NO = ?";

    private static final String SQL_DIRECT_TRAIN_LIST_BY_STATION_ID = "SELECT TRAINS.TRAIN_NO,TRAINS.TRAIN_NAME,"
            + "TRAINS.FIRST_AC,TRAINS.TWO_TIER_AC,TRAINS.THREE_TIER_AC,TRAINS.AC_CHAIR_CLASS,TRAINS.SECOND_SEATING,TRAINS.SLEEPER_CLASS,"
            + "TRAINS.MON,TRAINS.TUE,TRAINS.WED,TRAINS.THU,TRAINS.FRI,TRAINS.SAT,TRAINS.SUN,"
            + "SRC_STATION.STATION_NAME,SRC_STATION.STATION_CODE,SOURCE.DEPARTURE,"
            + "DEST_STATION.STATION_NAME,DEST_STATION.STATION_CODE,DEST.ARRIVAL,DEST.DISTANCE-SOURCE.DISTANCE,DEST.DAY FROM "
            + "TRAINS_SCHEDULE AS SOURCE,TRAINS_SCHEDULE AS DEST,TRAINS,STATIONS AS SRC_STATION,STATIONS AS DEST_STATION "
            + "WHERE TRAINS.TRAIN_NO = SOURCE.TRAIN_NO AND "
            + "TRAINS.TRAIN_NO = DEST.TRAIN_NO AND "
            + "SRC_STATION.STATION_ID =  SOURCE.STATION_ID AND "
            + "DEST_STATION.STATION_ID =  DEST.STATION_ID AND "
            + "DEST.DISTANCE > SOURCE.DISTANCE AND "
            + "DEST.DEPARTURE  = 'DESTINATION' AND SOURCE.ARRIVAL = 'SOURCE' AND "
            + "SRC_STATION.STATION_ID = ? AND "
            + "DEST_STATION.STATION_ID = ?";

    private static final String SQL_DIRECT_TRAIN_LIST_BY_STATION_CODE = "SELECT TRAINS.TRAIN_NO,TRAINS.TRAIN_NAME," // Trains Name ,No
            + "TRD.MON,TRD.TUE,TRD.WED,TRD.THU,TRD.FRI,TRD.SAT,TRD.SUN," // Running Days
            + "SRC_STATION.STATION_NAME,SRC_STATION.STATION_CODE,SOURCE.DEPARTURE," // Source station details
            + "DEST_STATION.STATION_NAME,DEST_STATION.STATION_CODE,DEST.ARRIVAL,(DEST.DISTANCE-SOURCE.DISTANCE) AS DISTANCE,DEST.DAY FROM "
            + "TRAINS_SCHEDULE AS SOURCE,TRAINS_SCHEDULE AS DEST,TRAINS,STATIONS AS SRC_STATION,STATIONS AS DEST_STATION,TRAIN_RUNNING_DAYS AS TRD "
            + "WHERE TRAINS.TRAIN_NO = SOURCE.TRAIN_NO AND "
            + "TRAINS.TRAIN_NO = DEST.TRAIN_NO AND "
            + "SRC_STATION.STATION_ID =  SOURCE.STATION_ID AND "
            + "DEST_STATION.STATION_ID =  DEST.STATION_ID AND "
            + "DEST.DISTANCE > SOURCE.DISTANCE AND "
            + "TRAINS.SOURCE_STATION = SRC_STATION.STATION_ID AND "
            + "TRAINS.DESTINATION_STATION = DEST_STATION.STATION_ID AND "
            + "TRAINS.TRAIN_NO = TRD.TRAIN_NO AND "
            + "SRC_STATION.STATION_CODE = ? AND "
            + "DEST_STATION.STATION_CODE = ?";

    private static final String SQL_INTERMEDIATE_STATION_LIST_BY_STATION_ID = "SELECT TRAINS.TRAIN_NO,TRAINS.TRAIN_NAME,"
            + "TRAINS.FIRST_AC,TRAINS.TWO_TIER_AC,TRAINS.THREE_TIER_AC,TRAINS.AC_CHAIR_CLASS,TRAINS.SECOND_SEATING,TRAINS.SLEEPER_CLASS,"
            + "TRAINS.MON,TRAINS.TUE,TRAINS.WED,TRAINS.THU,TRAINS.FRI,TRAINS.SAT,TRAINS.SUN,"
            + "SRC_STATION.STATION_NAME,SRC_STATION.STATION_CODE,SOURCE.DEPARTURE, "
            + "SEATS.FIRST_AC,SEATS.TWO_TIER_AC,SEATS.THREE_TIER_AC,SEATS.AC_CHAIR_CLASS,SEATS.SECOND_SEATING,SEATS.SLEEPER_CLASS, "
            + "DEST_STATION.STATION_NAME,DEST_STATION.STATION_CODE,DEST.ARRIVAL,DEST.DISTANCE-SOURCE.DISTANCE,DEST.DAY FROM "
            + "TRAINS_SCHEDULE AS SOURCE,TRAINS_SCHEDULE AS DEST,TRAINS,STATIONS AS SRC_STATION,STATIONS AS DEST_STATION,SEATS "
            + "WHERE TRAINS.TRAIN_NO = SOURCE.TRAIN_NO AND "
            + "TRAINS.TRAIN_NO = SEATS.TRAIN_NO AND "
            + "TRAINS.TRAIN_NO = DEST.TRAIN_NO AND "
            + "SRC_STATION.STATION_ID =  SOURCE.STATION_ID AND "
            + "DEST_STATION.STATION_ID =  DEST.STATION_ID AND "
            + "DEST.DISTANCE > SOURCE.DISTANCE AND "
            + "DEST.DEPARTURE  != '00:00:00' AND SOURCE.ARRIVAL != '00:00:00' OR "
            + "SRC_STATION.STATION_ID = ? AND "
            + "DEST_STATION.STATION_ID = ?";

    private static final String SQL_INTERMEDIATE_STATION_LIST_BY_STATION_CODE = "SELECT TRAINS.TRAIN_NO,TRAINS.TRAIN_NAME,"
            + "TRD.MON,TRD.TUE,TRD.WED,TRD.THU,TRD.FRI,TRD.SAT,TRD.SUN," // Running Days
            + "SRC_STATION.STATION_NAME,SRC_STATION.STATION_CODE,SOURCE.DEPARTURE,"
            + "DEST_STATION.STATION_NAME,DEST_STATION.STATION_CODE,DEST.ARRIVAL,(DEST.DISTANCE-SOURCE.DISTANCE) AS DISTANCE,DEST.DAY FROM "
            + "TRAINS_SCHEDULE AS SOURCE,TRAINS_SCHEDULE AS DEST,TRAINS,STATIONS AS SRC_STATION,STATIONS AS DEST_STATION,TRAIN_RUNNING_DAYS AS TRD "
            + "WHERE TRAINS.TRAIN_NO = SOURCE.TRAIN_NO AND "
            + "TRAINS.TRAIN_NO = DEST.TRAIN_NO AND "
            + "SRC_STATION.STATION_ID =  SOURCE.STATION_ID AND "
            + "DEST_STATION.STATION_ID =  DEST.STATION_ID AND "
            + "DEST.DISTANCE > SOURCE.DISTANCE AND "
            + "DEST.DEPARTURE <> SOURCE.ARRIVAL AND "
            + "TRAINS.TRAIN_NO = TRD.TRAIN_NO AND "
            + "SRC_STATION.STATION_CODE = ? AND "
            + "DEST_STATION.STATION_CODE = ?";

    private static final String SQL_VERIFY_IF_TRAIN_IS_CANCELLED_ON_DATE = "SELECT * FROM CANCELLED_TRAINS WHERE DATE = ? AND TRAIN_NO = ?";

    private static final String SQL_SET_CANCEL_TRAIN_ON_DATE = "INSERT INTO CANCELLED_TRAINS(DATE,TRAIN_NO) VALUES(?,?)";

    private static final String SQL_GET_TRAINS_LIST_BY_TRAIN_NAME = "SELECT * FROM TRAINS WHERE "
            + "TRAIN_NAME LIKE CONCAT('%',?,'%')";

    private static final String SQL_GET_TRAIN_SCHEDULE_BY_TRAIN_NO = "SELECT Trains.Train_No,Stations.Station_ID, Stations.Station_Code, Stations.Station_Name,"
            + "TIME_FORMAT(trains_schedule.DEPARTURE,'%H:%i') AS DEPARTURE,TIME_FORMAT(trains_schedule.ARRIVAL,'%H:%i') AS ARRIVAL,"
            + "TRAINS_SCHEDULE.Halt,TIMEDIFF(TRAINS_SCHEDULE.DEPARTURE,TRAINS_SCHEDULE.ARRIVAL) AS Haltm,TRAINS_SCHEDULE.Day,TRAINS_SCHEDULE.Distance "
            + "FROM TRAINS_SCHEDULE,TRAINS,STATIONS WHERE "
            + "TRAINS.TRAIN_NO = TRAINS_SCHEDULE.TRAIN_NO AND "
            + "TRAINS_SCHEDULE.STATION_ID = STATIONS.STATION_ID AND "
            + "TRAINS.TRAIN_NO = ? "
            + "ORDER BY TRAINS_SCHEDULE.DISTANCE ASC";

    private static final String SQL_LIST_TRAINS_COACHES = "SELECT * FROM TRAIN_COACHES WHERE TRAIN_NO = ? ORDER BY COACH_POSITION";

    private static final String SQL_LIST_ALL_TRAINS = "SELECT COUNT(*) FROM TRAINS";

    private static final String SQL_GET_TRAIN_CATEGORY = "SELECT * FROM TRAIN_CATEGORIES,TRAINS "
            + "WHERE TRAINS.TRAIN_CATEGORY = TRAIN_CATEGORIES.TRAIN_CATEGORY_ID AND "
            + "TRAINS.TRAIN_NO = ?";

    private static final String SQL_LIST_TRAIN_CATEGORIES = "SELECT * FROM TRAIN_CATEGORIES";
    
    private static final String SQL_LIST_TRAINS= "SELECT * FROM TRAINS";

    private static final String SQL_LIST_TRAIN_BY_TRAIN_NO_OR_TRAIN_NAME = "SELECT * FROM TRAINS WHERE "
            + "TRAIN_NO LIKE CONCAT('%',?,'%') OR TRAIN_NAME LIKE CONCAT('%',?,'%')";

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Train train;
    private static List<Train> trainList;
    private static List<TrainCategory> trainCategoryList;
    private static TrainCategory trainCategory;
    private static Week week;
    private static final String SQL_GET_TRAIN_RUNNING_DAYS = "SELECT * FROM TRAIN_RUNNING_DAYS WHERE TRAIN_NO = ?";

    public static List<Train> getTrainListByTrainNoOrTrainName(String input) throws DatabaseException, SQLException {
        int i = 0;
        trainList = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_TRAIN_BY_TRAIN_NO_OR_TRAIN_NAME);
        pst.setString(++i, input);
        pst.setString(++i, input);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TrainNo = rs.getString("TRAIN_NO");
            String TrainName = rs.getString("TRAIN_NAME");
            train = new Train(TrainNo, TrainName);
            trainList.add(train);
        }
        con.close();
        return trainList;
    }
    
    public static List<Train> getTrainList() throws DatabaseException, SQLException {
        int i = 0;
        trainList = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_TRAINS);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TrainNo = rs.getString("TRAIN_NO");
            String TrainName = rs.getString("TRAIN_NAME");
            train = new Train(TrainNo, TrainName);
            trainList.add(train);
        }
        con.close();
        return trainList;
    }

    public static Boolean verifyIfTrainIsCancelled(String Date, String TrainNo) throws DatabaseException, SQLException {
        Boolean isCancelled = false;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VERIFY_IF_TRAIN_IS_CANCELLED_ON_DATE);
        pst.setString(++i, Date);
        pst.setString(++i, TrainNo);
        rs = pst.executeQuery();
        if (rs.next()) {
            isCancelled = true;
        }
        return isCancelled;
    }

    public static Integer countTrainsList() throws SQLException, ClassNotFoundException, DatabaseException {
        Integer count = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_ALL_TRAINS);
        rs = pst.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        con.close();
        return count;
    }

    public static Boolean setCancelTrainOnDate(String Date, String TrainNo) throws DatabaseException, SQLException {
        Boolean preCheck = TrainDAO.verifyIfTrainIsCancelled(Date, TrainNo);
        Boolean isCancelled = false;
        if (preCheck) {
            isCancelled = true;
        } else {
            int i = 0;
            con = Database.connect();
            pst = con.prepareStatement(SQL_SET_CANCEL_TRAIN_ON_DATE);
            pst.setString(++i, Date);
            pst.setString(++i, TrainNo);
            isCancelled = pst.executeUpdate() > 0;
        }

        return isCancelled;
    }

    public static List<TrainCategory> listTrainCategories() throws DatabaseException, SQLException, DefaultException {
        trainCategoryList = new ArrayList();
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_TRAIN_CATEGORIES);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TRAIN_CATEGORY_ID = rs.getString(TrainCategory.SQL_TRAIN_CATEGORY_ID);
            String CATEGORY_NAME = rs.getString(TrainCategory.SQL_CATEGORY_NAME);
            String CATEGORY_DESCRIPTION = rs.getString(TrainCategory.SQL_CATEGORY_DESCRIPTION);

            trainCategoryList.add(new TrainCategory(TRAIN_CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION));

        }
        return trainCategoryList;
    }

    public static TrainCategory getTrainCategory(String TrainNo) throws DatabaseException, SQLException, DefaultException {
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TRAIN_CATEGORY);
        pst.setString(++i, TrainNo);
        rs = pst.executeQuery();
        if (rs.next()) {
            String TRAIN_CATEGORY_ID = rs.getString(TrainCategory.SQL_TRAIN_CATEGORY_ID);
            String CATEGORY_NAME = rs.getString(TrainCategory.SQL_CATEGORY_NAME);
            String CATEGORY_DESCRIPTION = rs.getString(TrainCategory.SQL_CATEGORY_DESCRIPTION);
            trainCategory = new TrainCategory(TRAIN_CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);
        } else {
            throw new DefaultException("No Trains found with Train No :" + TrainNo);
        }
        return trainCategory;
    }

    public static TrainCategory getTrainCategory(Train train) throws DatabaseException, SQLException {
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TRAIN_CATEGORY);
        pst.setString(++i, train.getTrainNo());
        rs = pst.executeQuery();
        if (rs.next()) {
            String TRAIN_CATEGORY_ID = rs.getString(TrainCategory.SQL_TRAIN_CATEGORY_ID);
            String CATEGORY_NAME = rs.getString(TrainCategory.SQL_CATEGORY_NAME);
            String CATEGORY_DESCRIPTION = rs.getString(TrainCategory.SQL_CATEGORY_DESCRIPTION);
            trainCategory = new TrainCategory(TRAIN_CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);
        }
        return trainCategory;
    }

    public static Week getTrainRunningDays(String TrainNo) throws DatabaseException, SQLException {
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TRAIN_RUNNING_DAYS);
        pst.setString(++i, TrainNo);
        rs = pst.executeQuery();
        if (rs.next()) {
            Boolean Mon = rs.getBoolean("MON");
            Boolean Tue = rs.getBoolean("TUE");
            Boolean Wed = rs.getBoolean("WED");
            Boolean Thu = rs.getBoolean("THU");
            Boolean Fri = rs.getBoolean("FRI");
            Boolean Sat = rs.getBoolean("SAT");
            Boolean Sun = rs.getBoolean("SUN");
            week = new Week(Mon, Tue, Wed, Thu, Fri, Sat, Sun);
        }

        return week;
    }

    public static Week getTrainRunningDays(Train train) throws DatabaseException, SQLException {
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TRAIN_RUNNING_DAYS);
        pst.setString(++i, train.getTrainNo());
        rs = pst.executeQuery();
        if (rs.next()) {
            Boolean Mon = rs.getBoolean("MON");
            Boolean Tue = rs.getBoolean("TUE");
            Boolean Wed = rs.getBoolean("WED");
            Boolean Thu = rs.getBoolean("THU");
            Boolean Fri = rs.getBoolean("FRI");
            Boolean Sat = rs.getBoolean("SAT");
            Boolean Sun = rs.getBoolean("SUN");
            week = new Week(Mon, Tue, Wed, Thu, Fri, Sat, Sun);
        }

        return week;
    }

    public static Boolean addTrain(Train train) throws DatabaseException, SQLException {
        Boolean isAdded = false;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_TRAIN);
        pst.setString(++i, train.getTrainNo());
        pst.setString(++i, train.getTrainName());
        pst.setString(++i, train.getSourceStation());
        pst.setString(++i, train.getDestinationStation());
        
        isAdded = pst.executeUpdate() > 0;
        con.close();
        return isAdded;
    }

    public static Train getTrainInfo(String TrainNo) throws StationException, DatabaseException, SQLException, ClassNotFoundException, ParseException {
        Train t = null;
        if (TrainNo == null || TrainNo.equals("")) {
            throw new StationException("Invalid Train No");
        } else {
            int i = 0;
            con = Database.connect();
            pst = con.prepareStatement(SQL_GET_TRAIN_INFO_BY_TRAIN_NO);
            pst.setString(++i, TrainNo);
            rs = pst.executeQuery();
            if (rs.next()) {
                String TrainName = rs.getString("TRAIN_NAME");  //  TRAINS.TRAIN_NAME ,TRAINS.TOTAL_COACHES ,TRAINS.TRAIN_NO,TRAINS.SOURCE_STATION,TRAINS.DESTINATION_STATION,TRAINS.DISTANCE,TRAINS.DURATION
                Integer TotalCoaches = CoachDAO.getCountCoachesOfTrain(TrainNo);     // HALT_STATIONS,MON,TUE,WED,THU,FRI,SAT,SUN,FIRST_AC,TWO_TIER_AC,THREE_TIER_AC,AC_CHAIR_CLASS
                TrainNo = rs.getString("TRAIN_NO");                     // SECOND_SEATING,SLEEPER_CLASS
                String SourceStation = rs.getString("SOURCE_STATION");
                String DestinationSTation = rs.getString("DESTINATION_STATION");
                String TotalDistance = TrainDAO.getTrainTotalTripDistance(TrainNo);
                String Duration = TrainDAO.getTrainTotalTripDuration(TrainNo);
                String HaltStations = StationDAO.countHaltStationsListOfTrain(TrainNo);

                Week Week = TrainDAO.getTrainRunningDays(TrainNo);
                t = new Train(TrainName, TotalCoaches, TrainNo, SourceStation, DestinationSTation, TotalDistance, Duration, HaltStations, Week);
            }
            con.close();
        }
        return t;
    }

    public static String getTrainTotalTripDistance(String TrainNo) throws StationException, DatabaseException, SQLException {
        String Distance = "";
        if (TrainNo.equals(null) || TrainNo.equals("")) {
            throw new StationException("Invalid Train No");
        } else {
            int i = 0;
            con = Database.connect();
            pst = con.prepareStatement(SQL_GET_TRAIN_TOTAL_TRIP_DISTANCE_BY_TRAIN_NO);
            pst.setString(++i, TrainNo);
            rs = pst.executeQuery();
            if (rs.next()) {
                Distance = rs.getString(1);
            }
        }
        return Distance;
    }

    public static String getTrainTotalTripDuration(String trainNo) throws DatabaseException, SQLException, ParseException {
        Integer DayDecrementVal = 1;
        String TripDuration = "";
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TRAIN_TOTAL_TRIP_DURATION);
        pst.setString(++i, trainNo);
        rs = pst.executeQuery();
        if (rs.next()) {
            String deptTime = rs.getString("SRC.DEPARTURE");
            String arrTime = rs.getString("DEST.ARRIVAL");
            String StartDate = DateTime.getDateTime();
            Integer Day = rs.getInt("DEST.DAY");
            String EndDate = DateTime.addDate(StartDate, Day - DayDecrementVal);
            TripDuration = DateTime.findDifference(StartDate + " " + deptTime, EndDate + " " + arrTime);
        }
        return TripDuration;
    }

    public static List<TrainsSchedule> getSchedule(String TrainNo) throws DatabaseException, SQLException {
        List<TrainsSchedule> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TRAIN_SCHEDULE_BY_TRAIN_NO);
        pst.setString(1, TrainNo);
        rs = pst.executeQuery();
        while (rs.next()) {
            TrainNo = rs.getString("Train_No");
            Integer StationID = rs.getInt("Station_ID");
            String StationCode = rs.getString("Station_Code");
            String StationName = rs.getString("Station_Name");
            String Departure;                                                                   // Train_No,Station_Code,Station_Name,Departure,Arrival,Halt,Day,Distance
            String Arrival;
            if (rs.getString("DEPARTURE").equals("00:00")) {
                Departure = "Destination";
            } else {
                Departure = rs.getString("DEPARTURE");
            }
            if (rs.getString("ARRIVAL").equals("00:00")) {
                Arrival = "Source";
            } else {
                Arrival = rs.getString("ARRIVAL");
            }
            String Halt = rs.getString("Halt");
            Integer Day = rs.getInt("Day");
            Integer Distance = rs.getInt("TRAINS_SCHEDULE.Distance");

            list.add(new TrainsSchedule(TrainNo, StationID, StationCode, StationName, Departure, Arrival, Halt, Day, Distance));

        }
        con.close();

        return list;
    }

    /* String StationCode String StationName String Departure String Arrival String Halt Integer Day String Distance  */
    public static List<TrainsSchedule> getDirectTrainListBetweenStations(int Source, int Destination, String Date) throws DatabaseException, SQLException, StationException, ParseException {
        List<TrainsSchedule> list = new ArrayList();
        Week Week;
        if (Source == Destination) {
            throw new StationException("The Source station can't be same as Destination station");
        } else {
            con = Database.connect();
            pst = con.prepareStatement(SQL_DIRECT_TRAIN_LIST_BY_STATION_ID);
            pst.setInt(1, Source);
            pst.setInt(2, Destination);
            rs = pst.executeQuery();
            while (rs.next()) {
                String TrainNo = rs.getString("TRAINS.TRAIN_NO");
                String TrainName = rs.getString("TRAINS.TRAIN_NAME");
                String SourceStation = rs.getString("SRC_STATION.STATION_NAME");
                String SourceStationCode = rs.getString("SRC_STATION.STATION_CODE");
                String Departure = rs.getString("SOURCE.DEPARTURE");
                String DestinationStation = rs.getString("DEST_STATION.STATION_NAME");
                String DestinationStationCode = rs.getString("DEST_STATION.STATION_CODE");
                String Arrival = rs.getString("DEST.ARRIVAL");
                Integer Distance = rs.getInt("DEST.DISTANCE-SOURCE.DISTANCE");
                Integer JourneyDays = rs.getInt("DEST.DAY");
                String StartDate = Date;
                String EndDate = DateTime.addDate(Date, JourneyDays - 1);
                String TripDuration = DateTime.findDifference(StartDate + " " + Departure, EndDate + " " + Arrival);
                Boolean Mon = rs.getBoolean("Mon");
                Boolean Tue = rs.getBoolean("Tue");
                Boolean Wed = rs.getBoolean("Wed");
                Boolean Thu = rs.getBoolean("Thu");
                Boolean Fri = rs.getBoolean("Fri");
                Boolean Sat = rs.getBoolean("Sat");
                Boolean Sun = rs.getBoolean("Sun");
                Week = new Week(Mon, Tue, Wed, Thu, Fri, Sat, Sun);
                Boolean FIRST_AC = rs.getBoolean("TRAINS.FIRST_AC");
                Boolean TWO_TIER_AC = rs.getBoolean("TRAINS.TWO_TIER_AC");
                Boolean THREE_TIER_AC = rs.getBoolean("TRAINS.THREE_TIER_AC");
                Boolean AC_CHAIR_CLASS = rs.getBoolean("TRAINS.AC_CHAIR_CLASS");
                Boolean SECOND_SEATING = rs.getBoolean("TRAINS.SECOND_SEATING");
                Boolean SLEEPER_CLASS = rs.getBoolean("TRAINS.SLEEPER_CLASS");
                Integer sFIRST_AC = rs.getInt("SEATS.FIRST_AC");
                Integer sTWO_TIER_AC = rs.getInt("SEATS.TWO_TIER_AC");
                Integer sTHREE_TIER_AC = rs.getInt("SEATS.THREE_TIER_AC");
                Integer sAC_CHAIR_CLASS = rs.getInt("SEATS.AC_CHAIR_CLASS");
                Integer sSECOND_SEATING = rs.getInt("SEATS.SECOND_SEATING");
                Integer sSLEEPER_CLASS = rs.getInt("SEATS.SLEEPER_CLASS");
                Seat seat = new Seat(sFIRST_AC, sTWO_TIER_AC, sTHREE_TIER_AC, sAC_CHAIR_CLASS, sSECOND_SEATING, sSLEEPER_CLASS);
                CheckAvailableCoaches coaches = new CheckAvailableCoaches(FIRST_AC, TWO_TIER_AC, THREE_TIER_AC, AC_CHAIR_CLASS, SECOND_SEATING, SLEEPER_CLASS);

                list.add(new TrainsSchedule(TrainNo, TrainName, Week, coaches, seat, Departure, Arrival, JourneyDays,
                        Distance, TripDuration, SourceStation, SourceStationCode, StartDate, DestinationStation,
                        DestinationStationCode, EndDate));
            }
        }
        con.close();
        return list;
    }

    public static List<TrainsSchedule> getDirectTrainListBetweenStations(String Source, String Destination, String Date) throws DatabaseException, SQLException, StationException, ParseException {
        List<TrainsSchedule> list = new ArrayList();
        Week Week;
        if (Source.equals(Destination)) {
            throw new StationException("The Source station can't be same as Destination station");
        } else {
            con = Database.connect();
            pst = con.prepareStatement(SQL_DIRECT_TRAIN_LIST_BY_STATION_CODE);
            pst.setString(1, Source);
            pst.setString(2, Destination);
            rs = pst.executeQuery();
            while (rs.next()) {
                String TrainNo = rs.getString("TRAINS.TRAIN_NO");
                String TrainName = rs.getString("TRAINS.TRAIN_NAME");
                String SourceStation = rs.getString("SRC_STATION.STATION_NAME");
                String SourceStationCode = rs.getString("SRC_STATION.STATION_CODE");
                String Departure = rs.getString("SOURCE.DEPARTURE");
                String DestinationStation = rs.getString("DEST_STATION.STATION_NAME");
                String DestinationStationCode = rs.getString("DEST_STATION.STATION_CODE");
                String Arrival = rs.getString("DEST.ARRIVAL");
                Integer Distance = rs.getInt("DISTANCE");
                Integer JourneyDays = rs.getInt("DEST.DAY");
                String StartDate = Date;
                String EndDate = DateTime.addDate(Date, JourneyDays - 1);
                String TripDuration = DateTime.findDifference(StartDate + " " + Departure, EndDate + " " + Arrival);
                Boolean Mon = rs.getBoolean("Mon");
                Boolean Tue = rs.getBoolean("Tue");
                Boolean Wed = rs.getBoolean("Wed");
                Boolean Thu = rs.getBoolean("Thu");
                Boolean Fri = rs.getBoolean("Fri");
                Boolean Sat = rs.getBoolean("Sat");
                Boolean Sun = rs.getBoolean("Sun");
                Week = new Week(Mon, Tue, Wed, Thu, Fri, Sat, Sun);
                Boolean isTrainAvailable = Train.checkTrainAvailableDays(Week, Date);
                if (isTrainAvailable) {
                    list.add(new TrainsSchedule(TrainNo, TrainName, Week, Departure, Arrival, JourneyDays,
                            Distance, TripDuration, SourceStation, SourceStationCode, StartDate, DestinationStation,
                            DestinationStationCode, EndDate));

                }
            }
        }
        System.out.println("Direct Trains Function called....");
        System.out.println("Found " + list.size() + " Trains");
        con.close();
        return list;
    }

    public static List<TrainsSchedule> listIntermediateTrains(int Src, int Dest, String Date) throws SQLException, ClassNotFoundException, StationException, DatabaseException, ParseException {
        int i = 0;
        List<TrainsSchedule> list = new ArrayList();
        Week Week;
        if (Src != Dest) {
            DateTime.getDayName(Date);
            con = Database.connect();
            pst = con.prepareStatement(SQL_INTERMEDIATE_STATION_LIST_BY_STATION_ID);
            pst.setInt(++i, Src);
            pst.setInt(++i, Dest);
            rs = pst.executeQuery();
            while (rs.next()) {
                String TrainNo = rs.getString("TRAINS.TRAIN_NO");
                String TrainName = rs.getString("TRAINS.TRAIN_NAME");
                String SourceStation = rs.getString("SRC_STATION.STATION_NAME");
                String SourceStationCode = rs.getString("SRC_STATION.STATION_CODE");
                String Departure = rs.getString("SOURCE.DEPARTURE");
                String DestinationStation = rs.getString("DEST_STATION.STATION_NAME");
                String DestinationStationCode = rs.getString("DEST_STATION.STATION_CODE");
                String Arrival = rs.getString("DEST.ARRIVAL");
                Integer Distance = rs.getInt("DEST.DISTANCE-SOURCE.DISTANCE");
                Integer JourneyDays = rs.getInt("DEST.DAY");
                String StartDate = Date;
                String EndDate = DateTime.addDate(Date, JourneyDays - 1);
                String TripDuration = DateTime.findDifference(StartDate + " " + Departure, EndDate + " " + Arrival);
                Boolean Mon = rs.getBoolean("Mon");
                Boolean Tue = rs.getBoolean("Tue");
                Boolean Wed = rs.getBoolean("Wed");
                Boolean Thu = rs.getBoolean("Thu");
                Boolean Fri = rs.getBoolean("Fri");
                Boolean Sat = rs.getBoolean("Sat");
                Boolean Sun = rs.getBoolean("Sun");
                Week = new Week(Mon, Tue, Wed, Thu, Fri, Sat, Sun);
                Boolean FIRST_AC = rs.getBoolean("TRAINS.FIRST_AC");
                Boolean TWO_TIER_AC = rs.getBoolean("TRAINS.TWO_TIER_AC");
                Boolean THREE_TIER_AC = rs.getBoolean("TRAINS.THREE_TIER_AC");
                Boolean AC_CHAIR_CLASS = rs.getBoolean("TRAINS.AC_CHAIR_CLASS");
                Boolean SECOND_SEATING = rs.getBoolean("TRAINS.SECOND_SEATING");
                Boolean SLEEPER_CLASS = rs.getBoolean("TRAINS.SLEEPER_CLASS");
                Integer sFIRST_AC = rs.getInt("SEATS.FIRST_AC");
                Integer sTWO_TIER_AC = rs.getInt("SEATS.TWO_TIER_AC");
                Integer sTHREE_TIER_AC = rs.getInt("SEATS.THREE_TIER_AC");
                Integer sAC_CHAIR_CLASS = rs.getInt("SEATS.AC_CHAIR_CLASS");
                Integer sSECOND_SEATING = rs.getInt("SEATS.SECOND_SEATING");
                Integer sSLEEPER_CLASS = rs.getInt("SEATS.SLEEPER_CLASS");

                Seat seat = new Seat(sFIRST_AC, sTWO_TIER_AC, sTHREE_TIER_AC, sAC_CHAIR_CLASS, sSECOND_SEATING, sSLEEPER_CLASS);
                CheckAvailableCoaches coaches = new CheckAvailableCoaches(FIRST_AC, TWO_TIER_AC, THREE_TIER_AC, AC_CHAIR_CLASS, SECOND_SEATING, SLEEPER_CLASS);

                list.add(new TrainsSchedule(TrainNo, TrainName, Week, coaches, seat, Departure, Arrival, JourneyDays,
                        Distance, TripDuration, SourceStation, SourceStationCode, StartDate, DestinationStation,
                        DestinationStationCode, EndDate));
            }
        } else {
            throw new StationException("The Source can't be same as Destination");
        }
        con.close();
        return list;
    }

    public static List<TrainsSchedule> listIntermediateTrains(String Src, String Dest, String Date) throws SQLException, ClassNotFoundException, StationException, DatabaseException, ParseException {

        int i = 0;
        List<TrainsSchedule> list = new ArrayList();
        Week Week;
        Src = Src.toUpperCase();
        Dest = Dest.toUpperCase();
        if (!Src.equals(Dest)) {
            con = Database.connect();
            pst = con.prepareStatement(SQL_INTERMEDIATE_STATION_LIST_BY_STATION_CODE);
            pst.setString(++i, Src);
            pst.setString(++i, Dest);
            rs = pst.executeQuery();
            while (rs.next()) {
                String TrainNo = rs.getString("TRAINS.TRAIN_NO");
                String TrainName = rs.getString("TRAINS.TRAIN_NAME");
                String SourceStation = rs.getString("SRC_STATION.STATION_NAME");
                String SourceStationCode = rs.getString("SRC_STATION.STATION_CODE");
                String Departure = rs.getString("SOURCE.DEPARTURE");
                String DestinationStation = rs.getString("DEST_STATION.STATION_NAME");
                String DestinationStationCode = rs.getString("DEST_STATION.STATION_CODE");
                String Arrival = rs.getString("DEST.ARRIVAL");
                Integer Distance = rs.getInt("DISTANCE");
                Integer JourneyDays = rs.getInt("DEST.DAY");
                String StartDate = Date;
                String EndDate = DateTime.addDate(Date, JourneyDays - 1);
                String TripDuration = DateTime.findDifference(StartDate + " " + Departure, EndDate + " " + Arrival);
                Boolean Mon = rs.getBoolean("Mon");
                Boolean Tue = rs.getBoolean("Tue");
                Boolean Wed = rs.getBoolean("Wed");
                Boolean Thu = rs.getBoolean("Thu");
                Boolean Fri = rs.getBoolean("Fri");
                Boolean Sat = rs.getBoolean("Sat");
                Boolean Sun = rs.getBoolean("Sun");
                Week = new Week(Mon, Tue, Wed, Thu, Fri, Sat, Sun);
                Boolean isTrainAvailable = Train.checkTrainAvailableDays(Week, Date);
                if (isTrainAvailable) {
                    list.add(new TrainsSchedule(TrainNo, TrainName, Week, Departure, Arrival, JourneyDays,
                            Distance, TripDuration, SourceStation, SourceStationCode, StartDate, DestinationStation,
                            DestinationStationCode, EndDate));
                }
            }
        } else {
            throw new StationException("The Source can't be same as Destination");
        }
        System.out.println("Intermediate Trains Function called");
        System.out.println("Found " + list.size() + " Trains");

        con.close();
        return list;
    }

    public static List<TrainCoaches> listTrainsCoachPositions(String TrainNo) throws DatabaseException, SQLException {
        List<TrainCoaches> coachList = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_TRAINS_COACHES);
        pst.setString(1, TrainNo);
        rs = pst.executeQuery();
        while (rs.next()) {
            String TRAIN_COACHES_ID = rs.getString(TrainCoaches.SQL_TRAIN_COACHES_ID);
            String TRAIN_NO = rs.getString(TrainCoaches.SQL_TRAIN_NO);
            String FARE_ID = rs.getString(TrainCoaches.SQL_FARE_ID);
            String COACH_NAME = rs.getString(TrainCoaches.SQL_COACH_NAME);
            Integer COACH_POSITION = rs.getInt(TrainCoaches.SQL_COACH_POSITION);
            TrainCoaches coach = new TrainCoaches(TRAIN_COACHES_ID, TRAIN_NO, FARE_ID, COACH_NAME, COACH_POSITION);
            coachList.add(coach);
        }
        return coachList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, DatabaseException, StationException, ParseException, DefaultException {

        List<Train> list = getTrainListByTrainNoOrTrainName("22");

        for (Train t : list) {
            System.out.println(t.toString());
        }

    }

}


/*
SELECT DISTINCT train_fares.FARE,coach_types.CLASS_CODE FROM train_coaches,train_fares,coach_types WHERE 
train_coaches.COACH_ID = train_fares.FARE_ID AND 
train_fares.COACH_ID = coach_types.COACH_ID AND train_coaches.TRAIN_NO = '22823' AND coach_types.CLASS_CODE <> 'EOG' AND coach_types.CLASS_CODE <> 'PC'
 */
