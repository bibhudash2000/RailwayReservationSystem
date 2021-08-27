package com.RRS.Models;

public class Coach {

    public static final String FIRST_AC = "1A";
    public static final String TWO_TIER_AC = "2A";
    public static final String THREE_TIER_AC = "3A";
    public static final String AC_CHAIR_CLASS = "CC";
    public static final String SECOND_SEATING = "2S";
    public static final String SLEEPER_CLASS = "SL";

    public static final String SQL_COACH_ID = "COACH_ID";
    public static final String SQL_COACH_TYPE = "COACH_TYPE";
    public static final String SQL_COACH_CODE = "COACH_CODE";
    public static final String SQL_CLASS_CODE = "CLASS_CODE";
    public static final String SQL_COLOR_CODE = "COLOR_CODE";
    public static final String SQL_LOWER_BERTH_CAPACITY = "LOWER_BERTH_CAPACITY";
    public static final String SQL_MIDDLE_BERTH_CAPACITY = "MIDDLE_BERTH_CAPACITY";
    public static final String SQL_UPPER_BERTH_CAPACITY = "UPPER_BERTH_CAPACITY";
    public static final String SQL_SIDE_LOWER_BERTH_CAPACITY = "SIDE_LOWER_BERTH_CAPACITY";
    public static final String SQL_SIDE_UPPER_BERTH_CAPACITY = "SIDE_UPPER_BERTH_CAPACITY";
    public static final String SQL_WINDOW_SEAT = "WINDOW_SEAT";
    public static final String SQL_MIDDLE_SEAT = "MIDDLE_SEAT";
    public static final String SQL_ASILE_SEAT = "ASILE_SEAT";
    public static final String SQL_SEATING_CAPACITY = "SEATING_CAPACITY";

    private String COACH_ID;
    private String COACH_TYPE;
    private String COACH_CODE;
    private String CLASS_CODE;
    private String COLOR_CODE;
    private String LOWER_BERTH_CAPACITY;
    private String MIDDLE_BERTH_CAPACITY;
    private String UPPER_BERTH_CAPACITY;
    private String SIDE_LOWER_BERTH_CAPACITY;
    private String SIDE_UPPER_BERTH_CAPACITY;
    private String WINDOW_SEAT;
    private String MIDDLE_SEAT;
    private String ASILE_SEAT;
    private String SEATING_CAPACITY;

    public TrainFare TRAIN_FARE;
    public TrainCoaches TRAIN_COACHES;

    /*  COACH_ID COACH_TYPE COACH_CODE CLASS_CODE 
    LOWER_BERTH_CAPACITY MIDDLE_BERTH_CAPACITY UPPER_BERTH_CAPACITY 
    SIDE_LOWER_BERTH_CAPACITY SIDE_UPPER_BERTH_CAPACITY
    WINDOW_SEAT MIDDLE_SEAT ASILE_SEAT
    SEATING_CAPACITY
     */
    public Coach(String COACH_ID, String COACH_TYPE, String COACH_CODE, String CLASS_CODE, String LOWER_BERTH_CAPACITY, String MIDDLE_BERTH_CAPACITY, String UPPER_BERTH_CAPACITY, String SIDE_LOWER_BERTH_CAPACITY, String SIDE_UPPER_BERTH_CAPACITY, String WINDOW_SEAT, String MIDDLE_SEAT, String ASILE_SEAT, String SEATING_CAPACITY) {
        this.COACH_ID = COACH_ID;
        this.COACH_TYPE = COACH_TYPE;
        this.COACH_CODE = COACH_CODE;
        this.CLASS_CODE = CLASS_CODE;
        this.LOWER_BERTH_CAPACITY = LOWER_BERTH_CAPACITY;
        this.MIDDLE_BERTH_CAPACITY = MIDDLE_BERTH_CAPACITY;
        this.UPPER_BERTH_CAPACITY = UPPER_BERTH_CAPACITY;
        this.SIDE_LOWER_BERTH_CAPACITY = SIDE_LOWER_BERTH_CAPACITY;
        this.SIDE_UPPER_BERTH_CAPACITY = SIDE_UPPER_BERTH_CAPACITY;
        this.WINDOW_SEAT = WINDOW_SEAT;
        this.MIDDLE_SEAT = MIDDLE_SEAT;
        this.ASILE_SEAT = ASILE_SEAT;
        this.SEATING_CAPACITY = SEATING_CAPACITY;
    }

    public Coach(String COACH_ID, String COACH_TYPE, String COACH_CODE, String CLASS_CODE, String LOWER_BERTH_CAPACITY, String MIDDLE_BERTH_CAPACITY, String UPPER_BERTH_CAPACITY, String SIDE_LOWER_BERTH_CAPACITY, String SIDE_UPPER_BERTH_CAPACITY, String WINDOW_SEAT, String MIDDLE_SEAT, String ASILE_SEAT, String SEATING_CAPACITY, TrainFare TRAIN_FARE) {
        this.COACH_ID = COACH_ID;
        this.COACH_TYPE = COACH_TYPE;
        this.COACH_CODE = COACH_CODE;
        this.CLASS_CODE = CLASS_CODE;
        this.LOWER_BERTH_CAPACITY = LOWER_BERTH_CAPACITY;
        this.MIDDLE_BERTH_CAPACITY = MIDDLE_BERTH_CAPACITY;
        this.UPPER_BERTH_CAPACITY = UPPER_BERTH_CAPACITY;
        this.SIDE_LOWER_BERTH_CAPACITY = SIDE_LOWER_BERTH_CAPACITY;
        this.SIDE_UPPER_BERTH_CAPACITY = SIDE_UPPER_BERTH_CAPACITY;
        this.WINDOW_SEAT = WINDOW_SEAT;
        this.MIDDLE_SEAT = MIDDLE_SEAT;
        this.ASILE_SEAT = ASILE_SEAT;
        this.SEATING_CAPACITY = SEATING_CAPACITY;
        this.TRAIN_FARE = TRAIN_FARE;
    }

    public Coach(String COACH_ID, String COACH_TYPE, String COACH_CODE, String CLASS_CODE, String COLOR_CODE, String LOWER_BERTH_CAPACITY, String MIDDLE_BERTH_CAPACITY, String UPPER_BERTH_CAPACITY, String SIDE_LOWER_BERTH_CAPACITY, String SIDE_UPPER_BERTH_CAPACITY, String WINDOW_SEAT, String MIDDLE_SEAT, String ASILE_SEAT, String SEATING_CAPACITY) {
        this.COACH_ID = COACH_ID;
        this.COACH_TYPE = COACH_TYPE;
        this.COACH_CODE = COACH_CODE;
        this.CLASS_CODE = CLASS_CODE;
        this.COLOR_CODE = COLOR_CODE;
        this.LOWER_BERTH_CAPACITY = LOWER_BERTH_CAPACITY;
        this.MIDDLE_BERTH_CAPACITY = MIDDLE_BERTH_CAPACITY;
        this.UPPER_BERTH_CAPACITY = UPPER_BERTH_CAPACITY;
        this.SIDE_LOWER_BERTH_CAPACITY = SIDE_LOWER_BERTH_CAPACITY;
        this.SIDE_UPPER_BERTH_CAPACITY = SIDE_UPPER_BERTH_CAPACITY;
        this.WINDOW_SEAT = WINDOW_SEAT;
        this.MIDDLE_SEAT = MIDDLE_SEAT;
        this.ASILE_SEAT = ASILE_SEAT;
        this.SEATING_CAPACITY = SEATING_CAPACITY;
    }

    public Coach(String COACH_ID) {
        this.COACH_ID = COACH_ID;
    }

    public void setCOACH_ID(String COACH_ID) {
        this.COACH_ID = COACH_ID;
    }

    public static String getFIRST_AC() {
        return FIRST_AC;
    }

    public static String getTWO_TIER_AC() {
        return TWO_TIER_AC;
    }

    public static String getTHREE_TIER_AC() {
        return THREE_TIER_AC;
    }

    public static String getAC_CHAIR_CLASS() {
        return AC_CHAIR_CLASS;
    }

    public static String getSECOND_SEATING() {
        return SECOND_SEATING;
    }

    public static String getSLEEPER_CLASS() {
        return SLEEPER_CLASS;
    }

    public Coach() {
    }

    public Coach(String COACH_ID, String COACH_TYPE, String COACH_CODE, String CLASS_CODE) {
        this.COACH_ID = COACH_ID;
        this.COACH_TYPE = COACH_TYPE;
        this.COACH_CODE = COACH_CODE;
        this.CLASS_CODE = CLASS_CODE;
    }

    public String getCOACH_ID() {
        return COACH_ID;
    }

    public String getCOACH_TYPE() {
        return COACH_TYPE;
    }

    public String getCOACH_CODE() {
        return COACH_CODE;
    }

    public String getCOLOR_CODE() {
        return COLOR_CODE;
    }

    public String getCLASS_CODE() {
        return CLASS_CODE;
    }

    public String getLOWER_BERTH_CAPACITY() {
        return LOWER_BERTH_CAPACITY;
    }

    public String getMIDDLE_BERTH_CAPACITY() {
        return MIDDLE_BERTH_CAPACITY;
    }

    public String getUPPER_BERTH_CAPACITY() {
        return UPPER_BERTH_CAPACITY;
    }

    public String getSIDE_LOWER_BERTH_CAPACITY() {
        return SIDE_LOWER_BERTH_CAPACITY;
    }

    public String getSIDE_UPPER_BERTH_CAPACITY() {
        return SIDE_UPPER_BERTH_CAPACITY;
    }

    public String getWINDOW_SEAT() {
        return WINDOW_SEAT;
    }

    public String getMIDDLE_SEAT() {
        return MIDDLE_SEAT;
    }

    public String getASILE_SEAT() {
        return ASILE_SEAT;
    }

    public String getSEATING_CAPACITY() {
        return SEATING_CAPACITY;
    }

    public TrainFare getTRAIN_FARE() {
        return TRAIN_FARE;
    }

    public TrainCoaches getTRAIN_COACHES() {
        return TRAIN_COACHES;
    }

    public void setCLASS_CODE(String CLASS_CODE) {
        this.CLASS_CODE = CLASS_CODE;
    }

    public void setTRAIN_FARE(TrainFare TRAIN_FARE) {
        this.TRAIN_FARE = TRAIN_FARE;
    }

    public void setTRAIN_COACHES(TrainCoaches TRAIN_COACHES) {
        this.TRAIN_COACHES = TRAIN_COACHES;
    }

    @Override
    public String toString() {
        return "Coach{" + "COACH_ID=" + COACH_ID + ", COACH_TYPE=" + COACH_TYPE + ", COACH_CODE=" + COACH_CODE + ", CLASS_CODE=" + CLASS_CODE + ", LOWER_BERTH_CAPACITY=" + LOWER_BERTH_CAPACITY + ", MIDDLE_BERTH_CAPACITY=" + MIDDLE_BERTH_CAPACITY + ", UPPER_BERTH_CAPACITY=" + UPPER_BERTH_CAPACITY + ", SIDE_LOWER_BERTH_CAPACITY=" + SIDE_LOWER_BERTH_CAPACITY + ", SIDE_UPPER_BERTH_CAPACITY=" + SIDE_UPPER_BERTH_CAPACITY + ", WINDOW_SEAT=" + WINDOW_SEAT + ", MIDDLE_SEAT=" + MIDDLE_SEAT + ", ASILE_SEAT=" + ASILE_SEAT + ", SEATING_CAPACITY=" + SEATING_CAPACITY + '}';
    }

}
