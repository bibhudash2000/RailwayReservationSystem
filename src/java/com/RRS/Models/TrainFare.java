package com.RRS.Models;

public class TrainFare {
    
    

     public static final String SQL_FARE_ID = "FARE_ID";
     public static final String SQL_TRAIN_NO = "TRAIN_NO";
     public static final String SQL_COACH_ID = "COACH_ID";
     public static final String SQL_MINIMUM_FARE = "MINIMUM_FARE";
     public static final String SQL_MINIMUM_DISTANCE = "MINIMUM_DISTANCE";
     public static final String SQL_FARE_PER_KM = "FARE_PER_KM";
    
    private String FARE_ID;
    private String TRAIN_NO;
    private String COACH_ID;
    Coach coach;
    private double MINIMUM_FARE;
    private int MINIMUM_DISTANCE;
    private double FARE_PER_KM;

    /*FARE_ID
TRAIN_NO
COACH_ID
MINIMUM_FARE
     */
    public TrainFare(String FARE_ID, String TRAIN_NO, String COACH_ID, double FARE) {
        this.FARE_ID = FARE_ID;
        this.TRAIN_NO = TRAIN_NO;
        this.COACH_ID = COACH_ID;
        this.MINIMUM_FARE = FARE;
    }

    public TrainFare(String FARE_ID, String TRAIN_NO, String COACH_ID, double MINIMUM_FARE, int MINIMUM_DISTANCE, double FARE_PER_KM) {
        this.FARE_ID = FARE_ID;
        this.TRAIN_NO = TRAIN_NO;
        this.COACH_ID = COACH_ID;
        this.MINIMUM_FARE = MINIMUM_FARE;
        this.MINIMUM_DISTANCE = MINIMUM_DISTANCE;
        this.FARE_PER_KM = FARE_PER_KM;
    }
    

    public TrainFare() {

    }

    public String getFARE_ID() {
        return FARE_ID;
    }

    public String getTRAIN_NO() {
        return TRAIN_NO;
    }

    public String getCOACH_ID() {
        return COACH_ID;
    }

    public double getMINIMUM_FARE() {
        return MINIMUM_FARE;
    }

    public void setFARE_ID(String FARE_ID) {
        this.FARE_ID = FARE_ID;
    }

    public void setTRAIN_NO(String TRAIN_NO) {
        this.TRAIN_NO = TRAIN_NO;
    }

    public void setCOACH_ID(String COACH_ID) {
        this.COACH_ID = COACH_ID;
    }

    public void setMINIMUM_FARE(double MINIMUM_FARE) {
        this.MINIMUM_FARE = MINIMUM_FARE;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public double getMINIMUM_DISTANCE() {
        return MINIMUM_DISTANCE;
    }

    public void setMINIMUM_DISTANCE(int MINIMUM_DISTANCE) {
        this.MINIMUM_DISTANCE = MINIMUM_DISTANCE;
    }

    public double getFARE_PER_KM() {
        return FARE_PER_KM;
    }

    public void setFARE_PER_KM(double FARE_PER_KM) {
        this.FARE_PER_KM = FARE_PER_KM;
    }
    

}
