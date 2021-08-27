package com.RRS.Models;

public class TrainCoaches {

    public static final String SQL_TRAIN_COACHES_ID = "TRAIN_COACHES_ID";
    public static final String SQL_TRAIN_NO = "TRAIN_NO";
    public static final String SQL_FARE_ID = "FARE_ID";
    public static final String SQL_COACH_NAME = "COACH_NAME";
    public static final String SQL_COACH_POSITION = "COACH_POSITION";

    private String TRAIN_COACHES_ID;
    private String TRAIN_NO;
    private String FARE_ID;
    private String COACH_NAME;
    private Integer COACH_POSITION;

    public TrainCoaches(String TRAIN_COACHES_ID, String TRAIN_NO, String FARE_ID, String COACH_NAME, Integer COACH_POSITION) {
        this.TRAIN_COACHES_ID = TRAIN_COACHES_ID;
        this.TRAIN_NO = TRAIN_NO;
        this.FARE_ID = FARE_ID;
        this.COACH_NAME = COACH_NAME;
        this.COACH_POSITION = COACH_POSITION;
    }

    /*
    TRAIN_COACHES_ID
TRAIN_NO
FARE_ID
COACH_NAME
COACH_POSITION
     */
    public String getTRAIN_COACHES_ID() {
        return TRAIN_COACHES_ID;
    }

    public String getTRAIN_NO() {
        return TRAIN_NO;
    }

    public String getFARE_ID() {
        return FARE_ID;
    }

    public String getCOACH_NAME() {
        return COACH_NAME;
    }

    public Integer getCOACH_POSITION() {
        return COACH_POSITION;
    }
}
