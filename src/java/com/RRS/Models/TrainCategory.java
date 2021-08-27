package com.RRS.Models;

/*
TRAIN_CATEGORY_ID
CATEGORY_NAME
CATEGORY_DESCRIPTION

 */
public class TrainCategory {

    public static final String SQL_TRAIN_CATEGORY_ID = "TRAIN_CATEGORY_ID";
    public static final String SQL_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String SQL_CATEGORY_DESCRIPTION = "CATEGORY_DESCRIPTION";

    String TRAIN_CATEGORY_ID;
    String CATEGORY_NAME;
    String CATEGORY_DESCRIPTION;

    public TrainCategory(String TRAIN_CATEGORY_ID, String CATEGORY_NAME, String CATEGORY_DESCRIPTION) {
        this.TRAIN_CATEGORY_ID = TRAIN_CATEGORY_ID;
        this.CATEGORY_NAME = CATEGORY_NAME;
        this.CATEGORY_DESCRIPTION = CATEGORY_DESCRIPTION;
    }

    public TrainCategory(String CATEGORY_NAME, String CATEGORY_DESCRIPTION) {
        this.CATEGORY_NAME = CATEGORY_NAME;
        this.CATEGORY_DESCRIPTION = CATEGORY_DESCRIPTION;
    }

    public String getTRAIN_CATEGORY_ID() {
        return TRAIN_CATEGORY_ID;
    }

    public String getCATEGORY_NAME() {
        return CATEGORY_NAME;
    }

    public String getCATEGORY_DESCRIPTION() {
        return CATEGORY_DESCRIPTION;
    }

}
