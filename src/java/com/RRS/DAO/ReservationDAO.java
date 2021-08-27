package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Exceptions.ReservationException;
import com.RRS.Models.Coach;
import com.RRS.Models.PNR;
import com.RRS.Models.Passenger;
import com.RRS.Models.Quota;
import com.RRS.Models.ReservationStatus;
import com.RRS.Models.Station;
import com.RRS.Models.Ticket;
import com.RRS.Models.TicketAvailability;
import com.RRS.Models.Train;
import com.RRS.Models.User;
import com.RRS.Utilities.Database;
import com.RRS.Utilities.DateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    /*
PID
TICKET_NO
NAME
AGE
GENDER
QUOTA
SEAT_NO*/
    private static final String SQL_ADD_PASSESNGERS = "INSERT INTO PASSENGERS(TICKET_NO,NAME,AGE,GENDER) VALUES(?,?,?,?)";

    private static final String SQL_LIST_BOOKED_TICKETS_DATE = "SELECT DISTINCT DATE_OF_JOURNEY FROM TICKETS WHERE BOOKED_BY = ? ORDER BY DATE_OF_JOURNEY DESC";

    private static final String SQL_LIST_BOOKED_TICKETS_BY_DATE = "SELECT * FROM TICKETS WHERE BOOKED_BY = ? AND DATE_OF_JOURNEY = ?";

    private static final String SQL_GET_TICKET_INFO_BY_PNR_NO = "SELECT * FROM TICKETS WHERE PNR_NO = ?";

    private static final String SQL_ADD_TICKET = "INSERT INTO TICKETS"
            + "(PNR_NO,TRAIN_NO,SOURCE,DESTINATION,DATE_OF_JOURNEY,TOTAL_PERSONS,FARE_ID,AMOUNT,RESERVATION_STATUS,BOOKED_BY) VALUES(?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_CHECK_PNR_STATUS = "SELECT TRAIN.TRAIN_NO,TRAIN.TRAIN_NAME,"
            + "SRC_STATION.STATION_CODE,SRC_STATION.STATION_NAME,SRC_STATION.CITY,"
            + "SRC.DEPARTURE,"
            + "DEST_STATION.STATION_CODE,DEST_STATION.STATION_NAME,DEST_STATION.CITY,"
            + "T.TICKET_NO,T.PNR_NO,T.DATE_OF_JOURNEY,T.TOTAL_PERSONS,T.FARE_ID,T.RESERVATION_STATUS,U.UserID,U.Name,U.Email,U.Contact "
            + "FROM TICKETS AS T,STATIONS AS SRC_STATION,STATIONS AS DEST_STATION,TRAINS_SCHEDULE AS SRC,TRAINS AS TRAIN,USERS AS U WHERE "
            + "TRAIN.TRAIN_NO = T.TRAIN_NO AND "
            + "SRC_STATION.STATION_ID = T.SOURCE AND "
            + "DEST_STATION.STATION_ID = T.DESTINATION AND "
            + "SRC.STATION_ID = SRC_STATION.STATION_ID AND "
            + "U.USERID = T.BOOKED_BY AND "
            + "T.PNR_NO = ?";
    private static final String SQL_GET_TOTAL_SEATS_TICKETS = "SELECT (COUNT(TRAIN_COACHES.TRAIN_COACHES_ID) *COACH_TYPES.SEATING_CAPACITY) AS SEATS "
            + "FROM TRAIN_COACHES,COACH_TYPES,TRAIN_FARES WHERE "
            + "TRAIN_COACHES.FARE_ID = TRAIN_FARES.FARE_ID AND "
            + "TRAIN_FARES.COACH_ID = COACH_TYPES.COACH_ID AND "
            + "COACH_TYPES.COACH_ID = ? AND TRAIN_COACHES.TRAIN_NO = ?";

    private static final String SQL_GET_AVAILABLE_TICKETS_IN_A_COACH = "SELECT (SUM(TICKETS.TOTAL_PERSONS)) AS AVAILABLE_SEATS FROM "
            + "TICKETS,TRAIN_FARES,COACH_TYPES WHERE "
            + "TICKETS.FARE_ID = TRAIN_FARES.FARE_ID AND "
            + "TRAIN_FARES.COACH_ID = COACH_TYPES.COACH_ID AND "
            + "TICKETS.TRAIN_NO = ? AND "
            + "DATE_OF_JOURNEY = ? AND "
            + "COACH_TYPES.COACH_ID = ?";
    private static final String SQL_LIST_PASSENGERS_FROM_PNR = "SELECT PASSENGERS.PID,PASSENGERS.NAME,PASSENGERS.AGE,"
            + "PASSENGERS.GENDER,PASSENGERS.QUOTA,PASSENGERS.SEAT_NO "
            + "FROM PASSENGERS,TICKETS WHERE "
            + "PASSENGERS.TICKET_NO = TICKETS.TICKET_NO AND "
            + "TICKETS.PNR_NO = ?";

    private static final String SQL_GET_PNR_NUMBER = "SELECT * FROM TICKETS WHERE TICKET_NO = ?";

    private static final String SQL_FETCH_BOOKED_TICKETS_IN_DATE = "SELECT * FROM TICKETS WHERE DATE_OF_JOURNEY = ? AND TRAIN_NO = ? AND FARE_ID = ?";

    private static final String SQL_GET_SEAT_FOR_QUOTA = "SELECT SEAT_MAP.SEAT_NO FROM SEAT_MAP,SEAT_TYPES WHERE "
            + "SEAT_MAP.SEAT_ID = SEAT_TYPES.SEAT_ID AND "
            + "SEAT_TYPES.SEAT_ABBREVIATION <> 'SUB' AND "
            + "SEAT_TYPES.SEAT_ABBREVIATION <> 'UB' AND "
            + "SEAT_TYPES.SEAT_ABBREVIATION <> 'MB' AND "
            + "SEAT_MAP.COACH_ID = ? ORDER BY SEAT_MAP.SEAT_NO ASC";

    private static final String SQL_GET_SEAT_FOR_GENERAL = "SELECT SEAT_MAP.SEAT_NO FROM SEAT_MAP,SEAT_TYPES WHERE "
            + "SEAT_MAP.SEAT_ID = SEAT_TYPES.SEAT_ID AND "
            + "SEAT_TYPES.SEAT_ABBREVIATION <> 'LB' AND "
            + "SEAT_TYPES.SEAT_ABBREVIATION <> 'SLB' AND "
            + "SEAT_MAP.COACH_ID = ? ORDER BY SEAT_MAP.SEAT_NO ASC";

    private static final String SQL_GET_PASSENGER_COUNT_ON_COACH_ON_DATE = "SELECT passengers.NAME FROM passengers,tickets WHERE "
            + "passengers.TICKET_NO = tickets.TICKET_NO AND "
            + "tickets.DATE_OF_JOURNEY = ? AND "
            + "tickets.FARE_ID = ? AND "
            + "tickets.TRAIN_NO = ?";

    private static final String SQL_GET_TODAYS_BOOKED_TICKET_COUNT = "SELECT COUNT(*) FROM TICKETS WHERE DATE_OF_JOURNEY = ?";

    public static Integer addTicket(Ticket ticket) throws DatabaseException, SQLException, ReservationException {
        int i = 0;
        con = Database.connect();
        Boolean TicketAdded = false;
        pst = con.prepareStatement(SQL_ADD_TICKET);
        pst.setString(++i, ticket.getPNR_No());
        pst.setString(++i, ticket.getTrainNo());
        pst.setInt(++i, ticket.getSourceStationID());
        pst.setInt(++i, ticket.getDestinationStationID());
        pst.setString(++i, ticket.getDate_Of_Journey());
        pst.setInt(++i, ticket.getTotalPersons());
        pst.setString(++i, ticket.getFare_ID());
        pst.setDouble(++i, ticket.getNetAmount());
        pst.setString(++i, ticket.getStatus().name());
        pst.setString(++i, ticket.getBookedBy());
        TicketAdded = pst.executeUpdate() > 0;
        if (TicketAdded) {
            pst = con.prepareStatement(SQL_GET_TICKET_INFO_BY_PNR_NO);
            pst.setString(1, ticket.getPNR_No());
            rs = pst.executeQuery();
            if (rs.next()) {
                ticket.setTicketNo(rs.getInt("TICKET_NO"));
            } else {
                throw new ReservationException("No Tickets found with PNR-No :" + ticket.getPNR_No());
            }
        } else {
            throw new ReservationException("Unable to Reserve Seat");
        }
        return ticket.getTicketNo();
    }

    public static Boolean addPassengers(List<Passenger> list) throws DatabaseException, SQLException {
        con = Database.connect();
        Boolean arePassengersAdded = false;
        pst = con.prepareStatement(SQL_ADD_PASSESNGERS);
        for (Passenger passenger : list) {
            pst.setInt(1, passenger.getTicketNo());
            pst.setString(2, passenger.getName());
            pst.setString(3, passenger.getAge());
            pst.setString(4, passenger.getGender());
            arePassengersAdded = pst.executeUpdate() > 0;
        }
        return arePassengersAdded;
    }

    public static Boolean addPassengers(List<Passenger> list, Integer TicketNo) throws DatabaseException, SQLException {
        con = Database.connect();
        Boolean arePassengersAdded = false;
        pst = con.prepareStatement(SQL_ADD_PASSESNGERS);
        for (Passenger passenger : list) {
            pst.setInt(1, TicketNo);
            pst.setString(2, passenger.getName());
            pst.setString(3, passenger.getAge());
            pst.setString(4, passenger.getGender());
            arePassengersAdded = pst.executeUpdate() > 0;
        }
        return arePassengersAdded;
    }

    public static List<Integer> getSeatListForQuota(String CoachID) throws DatabaseException, SQLException {
        List<Integer> seatList = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_SEAT_FOR_QUOTA);
        pst.setString(1, CoachID);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer SeatNo = rs.getInt("SEAT_NO");
            seatList.add(SeatNo);
        }
        return seatList;
    }

    public static List<Integer> getSeatListForGeneral(String CoachID) throws DatabaseException, SQLException {
        List<Integer> seatList = new ArrayList();
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_SEAT_FOR_GENERAL);
        pst.setString(1, CoachID);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer SeatNo = rs.getInt("SEAT_NO");
            seatList.add(SeatNo);
        }
        return seatList;
    }

    public static void bookNewTicket(Ticket ticket, List<Passenger> passengersList) throws DatabaseException, SQLException, ReservationException {
        ticket.getFare_ID();
        String LastbookedSeatNoOnThatCoach;
        String PassengerAge;

    }

    public static QuotaCategory getPassengersQuotas(List<Passenger> list) {
        int ssCount = 0;
        int genCount = 0;
        for (Passenger p : list) {
            if (Integer.parseInt(p.getAge()) >= 45 || (p.getGender().equals("Female")) && Integer.parseInt(p.getAge()) >= 45) {
                ssCount = ssCount + 1;
            } else {
                genCount = genCount + 1;
            }
        }
        QuotaCategory category = new QuotaCategory(ssCount, genCount);
        return category;
    }

    public static void getSeatNumbers(List<Passenger> passengersList, Integer lastBookedSeat) throws DatabaseException, SQLException {
        QuotaCategory cat = getPassengersQuotas(passengersList);
        int ssCount = cat.getSeniorCitizen();
        int genCount = cat.getGeneral();

        if (lastBookedSeat <= 0) {
            lastBookedSeat = 0;
        }
        List<Integer> quotaSeatList = getSeatListForQuota("5");
        List<Integer> generalSeatList = getSeatListForGeneral("5");

    }

//    public static Boolean isSeatNumberAvailable(String CoachID, String SeatNo) throws DatabaseException, SQLException {
//        con = Database.connect();
//        pst = con.prepareStatement(SQL_IS_SEAT_NUMBER_AVAILABLE);
//    }
//    
//    public static String assignSeat(String FareID, Quota quota) {
//
//    }
    public static List<Ticket> getBookedTickets(String Date_Of_Journey, String TrainNo, String FareID) throws DatabaseException, SQLException {
        int i = 0;
        List<Ticket> ticketList = new ArrayList();
        Ticket ticket;
        con = Database.connect();
        pst = con.prepareStatement(SQL_FETCH_BOOKED_TICKETS_IN_DATE);
        pst.setString(++i, Date_Of_Journey);
        pst.setString(++i, TrainNo);
        pst.setString(++i, FareID);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer TicketNo = rs.getInt(Ticket.SQL_TICKET_NO);
            String PNR_No = rs.getString(Ticket.SQL_PNR_NO);
            TrainNo = rs.getString(Ticket.SQL_TRAIN_NO);
            Integer SourceStationID = rs.getInt(Ticket.SQL_SOURCE);
            Integer DestinationStationID = rs.getInt(Ticket.SQL_DESTINATION);
            Date_Of_Journey = rs.getString(Ticket.SQL_DATE_OF_JOURNEY);
            Integer TotalPersons = rs.getInt(Ticket.SQL_TOTAL_PERSONS);
            Double Amount = rs.getDouble(Ticket.SQL_AMOUNT);
            FareID = rs.getString(Ticket.SQL_FARE_ID);
            ReservationStatus Status = ReservationStatus.valueOf(rs.getString(Ticket.SQL_RESERVATION_STATUS));
            String BookedBy = rs.getString(Ticket.SQL_BOOKED_BY);
            ticket = new Ticket(TicketNo, PNR_No, TrainNo, SourceStationID, DestinationStationID, Date_Of_Journey, TotalPersons, Amount, FareID, Status, BookedBy);
            ticketList.add(ticket);
        }
        return ticketList;
    }

    //TRAIN.TRAIN_NO TRAIN.TRAIN_NAME 
    //SRC_STATION.STATION_CODE SRC_STATION.STATION_NAME SRC_STATION.CITY 
    //DEST_STATION.STATION_CODE DEST_STATION.STATION_NAME DEST_STATION.CITY
    //T.TICKET_NO T.PNR_NO T.DATE_OF_JOURNEY T.TOTAL_PERSONS T.CLASS_TYPE T.RESERVATION_STATUS
    //U.UserID U.Name U.Email U.Contact
    //P.PID P.NAME P.AGE P.GENDER P.QUOTA P.SEAT_NO
    public static String getLastSeatBookedOnCoachOfTrainOnDate(String CoachID, String TrainNo, String Date) {
        
    }

    public static PNR checkPNRStatus(String PNR_NO) throws DatabaseException, SQLException, ParseException, ReservationException {
        Train train;
        Ticket ticket;
        User user;
        Station srcStation, destStation;
        int i = 0;
        PNR pnr = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_CHECK_PNR_STATUS);
        pst.setString(++i, PNR_NO);
        rs = pst.executeQuery();
        if (rs.next()) {
            String TrainNo = rs.getString("TRAIN.TRAIN_NO");
            String TrainName = rs.getString("TRAIN.TRAIN_NAME");
            train = new Train(TrainNo, TrainName);

            Integer TicketNo = rs.getInt("T.TICKET_NO");
            String PNR_No = rs.getString("T.PNR_NO");
            String Date_Of_Journey = DateTime.getDateTime(rs.getString("T.DATE_OF_JOURNEY"), DateTime.DATE_TIME_yyyyMMdd, DateTime.DATE_TIME_Day_Date_Mon);
            String DepartureTime = rs.getString("SRC.DEPARTURE");
            Integer TotalPersons = rs.getInt("T.TOTAL_PERSONS");
            String Class_Type = rs.getString("T.FARE_ID");
            ReservationStatus Status = ReservationStatus.valueOf(rs.getString("T.RESERVATION_STATUS"));
            ticket = new Ticket(TicketNo, PNR_No, Date_Of_Journey, DepartureTime, TotalPersons, Class_Type, Status);

            String SourceStationName = rs.getString("SRC_STATION.STATION_NAME");
            String SourceStationCode = rs.getString("SRC_STATION.STATION_CODE");
            String SourceStationCity = rs.getString("SRC_STATION.CITY");
            srcStation = new Station(SourceStationCode, SourceStationName, SourceStationCity);

            String DestinationStationName = rs.getString("DEST_STATION.STATION_NAME");
            String DestinationStationCode = rs.getString("DEST_STATION.STATION_CODE");
            String DestinationStationCity = rs.getString("DEST_STATION.CITY");
            destStation = new Station(DestinationStationCode, DestinationStationName, DestinationStationCity);

            String UserID = rs.getString("U.UserID");
            String UName = rs.getString("U.Name");
            String Email = rs.getString("U.Email");
            String Contact = rs.getString("U.Contact");
            user = new User(UserID, UName, Email, Contact);
            pnr = new PNR(train, ticket, user, srcStation, destStation);
        }
        return pnr;
    }

    public static List<Passenger> listPassengersFromPNR(String PNR_NO) throws DatabaseException, SQLException, ParseException, ReservationException {
        List<Passenger> passengers = new ArrayList();
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_PASSENGERS_FROM_PNR);
        pst.setString(++i, PNR_NO);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer PID = rs.getInt("PASSENGERS.PID");
            String Name = rs.getString("PASSENGERS.NAME");
            String Age = rs.getString("PASSENGERS.AGE");
            String Gender = rs.getString("PASSENGERS.GENDER");
            String SeatNo = rs.getString("PASSENGERS.SEAT_NO");
            Quota quota = Quota.valueOf(rs.getString("PASSENGERS.QUOTA"));
            passengers.add(new Passenger(PID, Name, Age, Gender, SeatNo, quota));
        }
        return passengers;
    }

    public static TicketAvailability checkAvailableSeats(String TrainNo, String CoachID, String Date) throws DatabaseException, SQLException {
        int i = 0;
        TicketAvailability ta = null;
        Integer TotalSeats = getTotalSeatsOfClassOfTrain(TrainNo, CoachID);
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_AVAILABLE_TICKETS_IN_A_COACH);
        pst.setString(++i, TrainNo);
        pst.setString(++i, Date);
        pst.setString(++i, CoachID);
        rs = pst.executeQuery();
        if (rs.next()) {
            //Available ticket fetched 
            Integer Temp = rs.getInt("AVAILABLE_SEATS");
            Temp = TotalSeats - Temp;
            ta = new TicketAvailability(Temp, "AVAILABLE");
            if (Temp < 1) {
                ta = new TicketAvailability(01, "WL");
            }
        } else {
            ta = new TicketAvailability(TotalSeats, "AVAILABLE");
        }
        con.close();
        return ta;
    }

    public static TicketAvailability checkAvailableSeats(String TrainNo, Coach c, String Date) throws DatabaseException, SQLException {
        int i = 0;
        TicketAvailability ta = null;
        Integer TotalSeats = getTotalSeatsOfClassOfTrain(TrainNo, c.getCOACH_ID());
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_AVAILABLE_TICKETS_IN_A_COACH);
        pst.setString(++i, TrainNo);
        pst.setString(++i, Date);
        pst.setString(++i, c.getCOACH_ID());
        rs = pst.executeQuery();
        if (rs.next()) {
            //Available ticket fetched 
            Integer Temp = rs.getInt("AVAILABLE_SEATS");
            Temp = TotalSeats - Temp;
            ta = new TicketAvailability(Temp, "AVAILABLE");
            if (Temp <= 0) {
                ta = new TicketAvailability(1, "WL");
            }
        } else {
            ta = new TicketAvailability(TotalSeats, "AVAILABLE");
        }
        con.close();
        return ta;
    }

    public static Integer getTotalSeatsOfClassOfTrain(String TrainNo, String CoachID) throws DatabaseException, SQLException {
        int i = 0;
        Integer TicketCount = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TOTAL_SEATS_TICKETS);
        pst.setString(++i, CoachID);
        pst.setString(++i, TrainNo);
        rs = pst.executeQuery();
        if (rs.next()) {
            //Available ticket fetched 
            TicketCount = rs.getInt("SEATS");
        }
        con.close();
        return TicketCount;
    }

    public static List<String> listDatesOfBookedTickets(String UserID) throws DatabaseException, SQLException {
        List<String> dateList = new ArrayList();
        String date;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_BOOKED_TICKETS_DATE);
        pst.setString(1, UserID);
        rs = pst.executeQuery();
        while (rs.next()) {
            date = rs.getString("DATE_OF_JOURNEY");
            dateList.add(date);
        }
        return dateList;
    }

    public static List<String> listDatesOfBookedTickets(User user) throws DatabaseException, SQLException {
        List<String> dateList = new ArrayList();
        String date;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_BOOKED_TICKETS_DATE);
        pst.setString(1, user.getUserID());
        rs = pst.executeQuery();
        while (rs.next()) {
            date = rs.getString("DATE_OF_JOURNEY");
            dateList.add(date);
        }
        return dateList;
    }

    public static List<Ticket> listTicketsBookedOnDate(String UserID, String Date) throws DatabaseException, SQLException {
        List<Ticket> ticketsList = new ArrayList();
        Ticket ticket = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_BOOKED_TICKETS_BY_DATE);
        pst.setString(1, UserID);
        pst.setString(2, Date);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer TicketNo = rs.getInt("TICKET_NO");
            String PNR_No = rs.getString("PNR_NO");
            String TrainNo = rs.getString("TRAIN_NO");
            Integer SourceStationID = rs.getInt("SOURCE");
            Integer DestinationStationID = rs.getInt("DESTINATION");
            String Date_Of_Journey = rs.getString("Date_Of_Journey");
            Integer TotalPersons = rs.getInt("Total_Persons");
            Double NetAmount = rs.getDouble("Amount");
            String Class_Type = rs.getString("CLASS_ID");
            ReservationStatus Status = ReservationStatus.valueOf(rs.getString("RESERVATION_STATUS"));
            String BookedBy = rs.getString("BOOKED_BY");
            ticket = new Ticket(TicketNo, PNR_No, TrainNo, SourceStationID, DestinationStationID, Date_Of_Journey, TotalPersons, NetAmount, Class_Type, Status, BookedBy);

            ticketsList.add(ticket);
        }

        return ticketsList;
    }

    public static List<Ticket> listTicketsBookedOnDate(User user, String Date) throws DatabaseException, SQLException {
        List<Ticket> ticketsList = new ArrayList();
        Ticket ticket = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_LIST_BOOKED_TICKETS_BY_DATE);
        pst.setString(1, user.getUserID());
        pst.setString(2, Date);
        rs = pst.executeQuery();
        while (rs.next()) {
            Integer TicketNo = rs.getInt("TICKET_NO");
            String PNR_No = rs.getString("PNR_NO");
            String TrainNo = rs.getString("TRAIN_NO");
            Integer SourceStationID = rs.getInt("SOURCE");
            Integer DestinationStationID = rs.getInt("DESTINATION");
            String Date_Of_Journey = rs.getString("Date_Of_Journey");
            Integer TotalPersons = rs.getInt("Total_Persons");
            Double NetAmount = rs.getDouble("Amount");
            String Fare_ID = rs.getString("FARE_ID");
            ReservationStatus Status = ReservationStatus.valueOf(rs.getString("RESERVATION_STATUS"));
            String BookedBy = rs.getString("BOOKED_BY");
            ticket = new Ticket(TicketNo, PNR_No, TrainNo, SourceStationID, DestinationStationID, Date_Of_Journey, TotalPersons, NetAmount, Fare_ID, Status, BookedBy);

            ticketsList.add(ticket);
        }

        return ticketsList;
    }

    public static String getPNRNumber(String TicketNo) throws DatabaseException, SQLException {
        String PNR = "";
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_PNR_NUMBER);
        pst.setString(1, TicketNo);
        rs = pst.executeQuery();
        if (rs.next()) {
            PNR = rs.getString("PNR_NO");
        }

        return PNR;
    }

    public static Integer countTodaysBookedTicketList() throws SQLException, ClassNotFoundException, DatabaseException {
        Integer count = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_TODAYS_BOOKED_TICKET_COUNT);
        pst.setString(1, DateTime.getDateTime(DateTime.DATE_TIME_yyyyMMdd));
        rs = pst.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        con.close();
        return count;
    }

    public static void main(String[] args) throws DatabaseException, SQLException, ParseException, ReservationException {
//        PNR p = checkPNRStatus("735231813");
//        System.out.println(p.getTrain().getTrainNo());
//
//        List<Passenger> list = ReservationDAO.listPassengersFromPNR(p.getTicket().getPNR_No());
//        System.out.println(list.size());
//        List<String> list = listDatesOfBookedTickets("212805084146");
//        for (String s : list) {
//            List<Ticket> ticketList = listTicketsBookedOnDate("212805084146", s);
//            System.out.println(s);
//            for (Ticket t : ticketList) {
//                System.out.println(t.toString());
//            }
//        }

//       List<Ticket> list = getBookedTickets("2021-07-30", "22823", "2");
//       for(Ticket t : list){
//           System.out.println(t.toString());
//       }
//        Ticket t = new Ticket();
        List<Passenger> list = new ArrayList();
        list.add(new Passenger("A", "45", "Female"));
        list.add(new Passenger("B", "50", "Male"));
        list.add(new Passenger("C", "40", "Female"));
        list.add(new Passenger("D", "21", "Male"));
        list.add(new Passenger("E", "64", "Male"));

        //getSeatNumbers(list, 59);
//        List<Integer> qList = getSeatListForQuota("5");
//        for (Integer q : qList) {
//            System.out.println(q);
//        }
    }


    /*
    
    select count(TICKET_NO) from tickets,train_fares,coach_types where
tickets.CLASS_ID = train_fares.FARE_ID AND
train_fares.COACH_ID = coach_types.COACH_ID AND
tickets.TRAIN_NO = '22823' and DATE_OF_JOURNEY = '2021-06-07' and coach_types.COACH_ID = 1
    
    
    
    
    select (count(*) *coach_types.SEATING_CAPACITY) as seats from train_coaches,coach_types,train_fares where 
train_coaches.COACH_ID = train_fares.FARE_ID and 
train_fares.COACH_ID = coach_types.COACH_ID and 
coach_types.COACH_ID = 5 and train_coaches.TRAIN_NO = '22823'
    
     */
}

class QuotaCategory {

    Integer SeniorCitizen;
    Integer General;

    public QuotaCategory(Integer SeniorCitizen, Integer General) {
        this.SeniorCitizen = SeniorCitizen;
        this.General = General;
    }

    public Integer getSeniorCitizen() {
        return SeniorCitizen;
    }

    public Integer getGeneral() {
        return General;
    }

}
