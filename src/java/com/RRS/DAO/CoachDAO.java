package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Exceptions.DefaultException;
import com.RRS.Models.Coach;
import com.RRS.Models.Train;
import com.RRS.Models.TrainFare;
import com.RRS.Models.TrainsSchedule;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachDAO {

    private static final String SQL_COUNT_COACHES_AVAILABLE_BY_COACH_TYPE = "SELECT COUNT(*) FROM "
            + "TRAIN_COACHES WHERE "
            + "TRAIN_COACHES.TRAIN_NO = ?";

    private static final String SQL_GET_FARE_WITH_COACH_CLASS_CODE = "SELECT TRAIN_FARES.MINIMUM_FARE,TRAIN_FARES.MINIMUM_DISTANCE,TRAIN_FARES.FARE_PER_KM,"
            + "COACH_TYPES.CLASS_CODE,COACH_TYPES.COACH_ID "
            + "FROM train_fares,coach_types "
            + "WHERE "
            + "train_fares.COACH_ID = coach_types.COACH_ID AND "
            + "train_fares.TRAIN_NO = ? AND "
            + "COACH_TYPES.CLASS_CODE <> 'EOG' AND "
            + "COACH_TYPES.CLASS_CODE <> 'L' AND "
            + "COACH_TYPES.CLASS_CODE <> 'PC'";

    private static final String SQL_GET_AVAILABLE_COACHES = "SELECT * "
            + "FROM coach_types,train_fares "
            + "WHERE "
            + "train_fares.COACH_ID = coach_types.COACH_ID AND "
            + "train_fares.TRAIN_NO = ? AND "
            + "COACH_TYPES.CLASS_CODE <> 'EOG' AND "
            + "COACH_TYPES.CLASS_CODE <> 'L' AND "
            + "COACH_TYPES.CLASS_CODE <> 'PC'";

    private static final String SQL_GET_COACH_BY_CLASS_CODE_AND_TRAIN_NO = "SELECT * FROM COACH_TYPES,"
            + "TRAIN_FARES WHERE TRAIN_FARES.COACH_ID = COACH_TYPES.COACH_ID "
            + "AND COACH_TYPES.CLASS_CODE = ? AND "
            + "TRAIN_FARES.TRAIN_NO = ?";

    private static final String SQL_GET_COACH_INFORMATION_BY_COACH_ID = "SELECT * FROM COACH_TYPES WHERE COACH_ID = ?";

    private static final String SQL_LIST_COACH_TYPES = "SELECT * FROM COACH_TYPES ORDER BY SEATING_CAPACITY ASC";

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public static Integer getCountCoachesOfTrain(String TrainNo) throws DatabaseException, SQLException {
        con = Database.connect();
        pst = con.prepareStatement(SQL_COUNT_COACHES_AVAILABLE_BY_COACH_TYPE);
        pst.setString(1, TrainNo);
        rs = pst.executeQuery();
        Integer noOfCoaches = 0;
        while (rs.next()) {
            noOfCoaches = rs.getInt(1);
        }
        return noOfCoaches;
    }

    public static Integer getCountCoachesOfTrain(Train train) throws DatabaseException, SQLException {
        con = Database.connect();
        pst = con.prepareStatement(SQL_COUNT_COACHES_AVAILABLE_BY_COACH_TYPE);
        pst.setString(1, train.getTrainNo());
        rs = pst.executeQuery();
        Integer noOfCoaches = 0;
        while (rs.next()) {
            noOfCoaches = rs.getInt(1);
        }
        return noOfCoaches;
    }

    public static List<TrainFare> getFareList(String TrainNo) throws DatabaseException, SQLException {
        List<TrainFare> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_FARE_WITH_COACH_CLASS_CODE);
        pst.setString(1, TrainNo);
        rs = pst.executeQuery();
        while (rs.next()) {
            Double MinimumFare = rs.getDouble("TRAIN_FARES.MINIMUM_FARE");
            double FarePerKM = rs.getDouble("TRAIN_FARES.FARE_PER_KM");
            Integer MinimumDistance = rs.getInt("TRAIN_FARES.MINIMUM_DISTANCE");
            String ClassCode = rs.getString("COACH_TYPES.CLASS_CODE");
            String CoachID = rs.getString("COACH_TYPES.COACH_ID");

            TrainFare tf = new TrainFare();
            Coach c = new Coach();
            c.setCLASS_CODE(ClassCode);
            c.setCOACH_ID(CoachID);
            tf.setCoach(c);
            tf.setMINIMUM_FARE(MinimumFare);
            tf.setMINIMUM_DISTANCE(MinimumDistance);
            tf.setFARE_PER_KM(FarePerKM);
            list.add(tf);
        }
        return list;
    }

    public static List<Coach> getAvailableCoaches(String TrainNo) throws DatabaseException, SQLException {
        List<Coach> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_AVAILABLE_COACHES);
        pst.setString(1, TrainNo);
        rs = pst.executeQuery();
        while (rs.next()) {
            String ClassCode = rs.getString("CLASS_CODE");
            String CoachID = rs.getString("COACH_ID");
            String COACH_TYPE = rs.getString("COACH_TYPE");
            String COACH_CODE = rs.getString("COACH_CODE");
            Coach c = new Coach(CoachID, COACH_TYPE, COACH_CODE, ClassCode);
            list.add(c);
        }
        return list;
    }

    public static List<Coach> getAvailableCoaches(Train train) throws DatabaseException, SQLException {
        List<Coach> list = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_AVAILABLE_COACHES);
        pst.setString(1, train.getTrainNo());
        rs = pst.executeQuery();
        while (rs.next()) {
            String ClassCode = rs.getString("CLASS_CODE");
            String CoachID = rs.getString("COACH_ID");
            String COACH_TYPE = rs.getString("COACH_TYPE");
            String COACH_CODE = rs.getString("COACH_CODE");
            Coach c = new Coach(CoachID, COACH_TYPE, COACH_CODE, ClassCode);
            list.add(c);
        }
        return list;
    }

//    public static Boolean bookTicket(Ticket t) throws DatabaseException, SQLException {
//        Boolean isBooked = false;
//        Random random = new Random();
//        Boolean stationsAvailable = false;
//        List<TrainFare> list = getFareList("22823");
//        Double TicketAmount = 0.0;
//        Boolean IfCoachAvailable = false;
//        for (TrainFare tf : list) {
//            if (t.getClass_Type().equals(tf.getCoach().getCOACH_ID())) {
//                IfCoachAvailable = true;
//                TicketAmount = (Double) tf.getMINIMUM_FARE();
//            }
//        }
//
//        if (IfCoachAvailable) {
//            // Book Ticket of seats available
//            TicketAvailability ta = ReservationDAO.checkAvailableSeats(t.getTrainNo(), t.getClass_Type(), t.getDate_Of_Journey());
//            if (ta.getTicketCount() >= t.getTotalPersons() && ta.getAvailabilityMessage() != "WL") {
//
//                List<TrainsSchedule> slist = TrainDAO.getSchedule(t.getTrainNo());
//                stationsAvailable = checkIfTrainAvailableforStations(slist, t.getSourceStationID(), t.getDestinationStationID());
//                if (stationsAvailable) {
//                    String PNR = String.valueOf(random.nextInt(999999999));
//                    Double TotalAmount = TicketAmount * t.getTotalPersons();
//                    ReservationStatus rs = ReservationStatus.CNF;
//
//                    Ticket ticket = new Ticket(PNR, t.getTrainNo(), t.getSourceStationID(), t.getDestinationStationID(), t.getClass_Type(), t.getDate_Of_Journey(), t.getTotalPersons(), TotalAmount, rs.CNF, t.getBookedBy());
//                    System.out.println(ticket);
//                }
//                //System.out.println("Booked");
//            } else {
//                // System.out.println("Not booked");
//            }
//
//        } else {
//            // Coach not available
//            System.out.println("coach not available");
//        }
//
//        return isBooked;
//    }

    private static Boolean checkIfTrainAvailableforStations(List<TrainsSchedule> list, Integer Src, Integer Dest) {
        Boolean srcAvailable = false;
        Boolean destAvailable = false;
        List<TrainsSchedule> slist = list;
        for (TrainsSchedule ts : slist) {
            if (Src.equals(ts.getStationID())) {
                srcAvailable = true;
            }
            if (Dest.equals(ts.getStationID())) {
                destAvailable = true;
            }
        }
        if (srcAvailable && destAvailable) {
            return true;
        } else {
            return false;
        }
    }

    public static Coach getCoachByClassCode(String ClassCode, String TrainNo) throws DatabaseException, SQLException, DefaultException {
        con = Database.connect();
        Coach c = null;
        pst = con.prepareStatement(SQL_GET_COACH_BY_CLASS_CODE_AND_TRAIN_NO);
        pst.setString(1, ClassCode);
        pst.setString(2, TrainNo);
        rs = pst.executeQuery();
        if (rs.next()) {
            String COACH_ID = rs.getString("COACH_ID");
            String COACH_TYPE = rs.getString("COACH_TYPE");
            String COACH_CODE = rs.getString("COACH_CODE");
            String CLASS_CODE = rs.getString("CLASS_CODE");
            String LOWER_BERTH_CAPACITY = rs.getString("LOWER_BERTH_CAPACITY");
            String MIDDLE_BERTH_CAPACITY = rs.getString("MIDDLE_BERTH_CAPACITY");
            String UPPER_BERTH_CAPACITY = rs.getString("UPPER_BERTH_CAPACITY");
            String SIDE_LOWER_BERTH_CAPACITY = rs.getString("SIDE_LOWER_BERTH_CAPACITY");
            String SIDE_UPPER_BERTH_CAPACITY = rs.getString("SIDE_UPPER_BERTH_CAPACITY");
            String WINDOW_SEAT = rs.getString("WINDOW_SEAT");
            String MIDDLE_SEAT = rs.getString("MIDDLE_SEAT");
            String ASILE_SEAT = rs.getString("ASILE_SEAT");
            String SEATING_CAPACITY = rs.getString("SEATING_CAPACITY");
            String FareID = rs.getString("FARE_ID");
            String TRAIN_NO = rs.getString("TRAIN_NO");
            double MINIMUM_FARE = rs.getDouble("MINIMUM_FARE");
            int MINIMUM_DISTANCE = rs.getInt("MINIMUM_DISTANCE");
            double FARE_PER_KM = rs.getDouble("FARE_PER_KM");
            TrainFare tf = new TrainFare(FareID, TRAIN_NO, COACH_ID, MINIMUM_FARE, MINIMUM_DISTANCE, FARE_PER_KM);

            c = new Coach(COACH_ID, COACH_TYPE, COACH_CODE, CLASS_CODE, LOWER_BERTH_CAPACITY, MIDDLE_BERTH_CAPACITY, UPPER_BERTH_CAPACITY, SIDE_LOWER_BERTH_CAPACITY, SIDE_UPPER_BERTH_CAPACITY, WINDOW_SEAT, MIDDLE_SEAT, ASILE_SEAT, SEATING_CAPACITY, tf);
        } else {
            throw new DefaultException("Invalid Class Code :" + ClassCode);
        }
        return c;
    }

    public static Coach getCoachInformationByCoachID(String CoachID) throws DatabaseException, SQLException, DefaultException {
        con = Database.connect();
        Coach c = null;
        pst = con.prepareStatement(SQL_GET_COACH_INFORMATION_BY_COACH_ID);
        pst.setString(1, CoachID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String COACH_ID = rs.getString("COACH_ID");
            String COACH_TYPE = rs.getString("COACH_TYPE");
            String COACH_CODE = rs.getString("COACH_CODE");
            String CLASS_CODE = rs.getString("CLASS_CODE");
            String COLOR_CODE = rs.getString("COLOR_CODE");
            String LOWER_BERTH_CAPACITY = rs.getString("LOWER_BERTH_CAPACITY");
            String MIDDLE_BERTH_CAPACITY = rs.getString("MIDDLE_BERTH_CAPACITY");
            String UPPER_BERTH_CAPACITY = rs.getString("UPPER_BERTH_CAPACITY");
            String SIDE_LOWER_BERTH_CAPACITY = rs.getString("SIDE_LOWER_BERTH_CAPACITY");
            String SIDE_UPPER_BERTH_CAPACITY = rs.getString("SIDE_UPPER_BERTH_CAPACITY");
            String WINDOW_SEAT = rs.getString("WINDOW_SEAT");
            String MIDDLE_SEAT = rs.getString("MIDDLE_SEAT");
            String ASILE_SEAT = rs.getString("ASILE_SEAT");
            String SEATING_CAPACITY = rs.getString("SEATING_CAPACITY");

            c = new Coach(COACH_ID, COACH_TYPE, COACH_CODE, CLASS_CODE, COLOR_CODE, LOWER_BERTH_CAPACITY, MIDDLE_BERTH_CAPACITY, UPPER_BERTH_CAPACITY, SIDE_LOWER_BERTH_CAPACITY, SIDE_UPPER_BERTH_CAPACITY, WINDOW_SEAT, MIDDLE_SEAT, ASILE_SEAT, SEATING_CAPACITY);
        } else {
            throw new DefaultException("Invalid Coach ID :" + CoachID);
        }
        return c;
    }

    public static List<Coach> listCoachTypes() throws DatabaseException, SQLException {
        List<Coach> coachList = new ArrayList();
        con = Database.connect();
        Coach c = null;
        pst = con.prepareStatement(SQL_LIST_COACH_TYPES);
        rs = pst.executeQuery();
        while (rs.next()) {
            String COACH_ID = rs.getString(Coach.SQL_COACH_ID);
            String COACH_TYPE = rs.getString(Coach.SQL_COACH_TYPE);
            String COACH_CODE = rs.getString(Coach.SQL_COACH_CODE);
            String CLASS_CODE = rs.getString(Coach.SQL_CLASS_CODE);
            String COLOR_CODE = rs.getString(Coach.SQL_COLOR_CODE);
            String LOWER_BERTH_CAPACITY = rs.getString(Coach.SQL_LOWER_BERTH_CAPACITY);
            String MIDDLE_BERTH_CAPACITY = rs.getString(Coach.SQL_MIDDLE_BERTH_CAPACITY);
            String UPPER_BERTH_CAPACITY = rs.getString(Coach.SQL_UPPER_BERTH_CAPACITY);
            String SIDE_LOWER_BERTH_CAPACITY = rs.getString(Coach.SQL_SIDE_LOWER_BERTH_CAPACITY);
            String SIDE_UPPER_BERTH_CAPACITY = rs.getString(Coach.SQL_SIDE_UPPER_BERTH_CAPACITY);
            String WINDOW_SEAT = rs.getString(Coach.SQL_WINDOW_SEAT);
            String MIDDLE_SEAT = rs.getString(Coach.SQL_MIDDLE_SEAT);
            String ASILE_SEAT = rs.getString(Coach.SQL_ASILE_SEAT);
            String SEATING_CAPACITY = rs.getString(Coach.SQL_SEATING_CAPACITY);
            c = new Coach(COACH_ID, COACH_TYPE, COACH_CODE, CLASS_CODE, COLOR_CODE, LOWER_BERTH_CAPACITY, MIDDLE_BERTH_CAPACITY, UPPER_BERTH_CAPACITY, SIDE_LOWER_BERTH_CAPACITY, SIDE_UPPER_BERTH_CAPACITY, WINDOW_SEAT, MIDDLE_SEAT, ASILE_SEAT, SEATING_CAPACITY);
            coachList.add(c);
        }
        return coachList;
    }

    public static void main(String[] args) throws DatabaseException, SQLException, DefaultException {
        List<TrainFare> list = getFareList("22823");
        for (TrainFare tf : list) {
            System.out.println(tf.getCoach().getCLASS_CODE());
        }
//        List<TrainsSchedule> slist = TrainDAO.getSchedule("22823");
//        for (TrainsSchedule ts : slist) {
//
//        }
//        System.out.println(checkIfTrainAvailableforStations(slist, 656, 321));
//        Ticket t = new Ticket();
//        t.setTrainNo("22823");
//        System.out.println(bookTicket(t));
//        List<Coach> list = getAvailableCoaches(new Train("22823"));
//        System.out.println(list.size());
//        for (Coach c : list) {
//            System.out.println(c.getCLASS_CODE());
//        }

        Coach c = getCoachInformationByCoachID("12");
        System.out.println(c.getCOACH_TYPE());

    }

}


/*
SELECT DISTINCT train_fares.FARE,coach_types.CLASS_CODE FROM train_coaches,train_fares,coach_types WHERE 
train_coaches.COACH_ID = train_fares.FARE_ID AND 
train_fares.COACH_ID = coach_types.COACH_ID AND train_coaches.TRAIN_NO = '22823' AND coach_types.CLASS_CODE <> 'EOG' AND coach_types.CLASS_CODE <> 'PC'
 */
 /*
Parameters : train no,classid


select (coach_types.SEATING_CAPACITY * (select count(train_coaches.TRAIN_COACHES_ID) from train_coaches,coach_types,train_fares where train_coaches.COACH_ID = train_fares.FARE_ID and train_fares.COACH_ID = coach_types.COACH_ID and COACH_ID = 1 and train_coaches.TRAIN_NO = '22823' )) AS SeatCount

select count(*) from train_coaches,coach_types,train_fares where 
train_coaches.COACH_ID = train_fares.FARE_ID and 
train_fares.COACH_ID = coach_types.COACH_ID and 
coach_types.COACH_ID = 1 and train_coaches.TRAIN_NO = '22823'

 */
