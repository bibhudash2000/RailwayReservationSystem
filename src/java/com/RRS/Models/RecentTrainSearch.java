package com.RRS.Models;

public class RecentTrainSearch {

    public static final String SQL_USER_ID = "USER_ID";
    public static final String SQL_SOURCE_STATION_ID = "SOURCE_STATION_ID";
    public static final String SQL_DESTINATION_STATION_ID = "DESTINATION_STATION_ID";
    public static final String SQL_DATE = "DATE";
    public static final String SQL_CLASS_TYPE = "CLASS_TYPE";

    String USER_ID;
    Integer SOURCE_STATION_ID;
    Integer DESTINATION_STATION_ID;
    String DATE;
    String CLASS_TYPE;

    public RecentTrainSearch(String USER_ID, Integer SOURCE_STATION_ID, Integer DESTINATION_STATION_ID, String DATE, String CLASS_TYPE) {
        this.USER_ID = USER_ID;
        this.SOURCE_STATION_ID = SOURCE_STATION_ID;
        this.DESTINATION_STATION_ID = DESTINATION_STATION_ID;
        this.DATE = DATE;
        this.CLASS_TYPE = CLASS_TYPE;
    }

    public RecentTrainSearch() {
    }
    

    public String getUSER_ID() {
        return USER_ID;
    }

    public Integer getSOURCE_STATION_ID() {
        return SOURCE_STATION_ID;
    }

    public Integer getDESTINATION_STATION_ID() {
        return DESTINATION_STATION_ID;
    }

    public String getDATE() {
        return DATE;
    }

    public String getCLASS_TYPE() {
        return CLASS_TYPE;
    }
    
    

}
