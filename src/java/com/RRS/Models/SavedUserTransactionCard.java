package com.RRS.Models;

/*
TRANSACTION_CARD_ID	USER_ID	CARD_NUMBER	EXPIRY_MONTH	EXPIRY_YEAR	CVV	CARD_HOLDER_NAME
 */
public class SavedUserTransactionCard {

    public static final String SQL_TRANSACTION_CARD_ID = "TRANSACTION_CARD_ID";
    public static final String SQL_USER_ID = "USER_ID";
    public static final String SQL_CARD_NUMBER = "CARD_NUMBER";
    public static final String SQL_EXPIRY_MONTH = "EXPIRY_MONTH";
    public static final String SQL_EXPIRY_YEAR = "EXPIRY_YEAR";
    public static final String SQL_CVV = "CVV";
    public static final String SQL_CARD_HOLDER_NAME = "CARD_HOLDER_NAME";

    String TRANSACTION_CARD_ID;
    String USER_ID;
    String CARD_NUMBER;
    String EXPIRY_MONTH;
    String EXPIRY_YEAR;
    String CVV;
    String CARD_HOLDER_NAME;

    public SavedUserTransactionCard(String TRANSACTION_CARD_ID, String USER_ID, String CARD_NUMBER, String EXPIRY_MONTH, String EXPIRY_YEAR, String CVV, String CARD_HOLDER_NAME) {
        this.TRANSACTION_CARD_ID = TRANSACTION_CARD_ID;
        this.USER_ID = USER_ID;
        this.CARD_NUMBER = CARD_NUMBER;
        this.EXPIRY_MONTH = EXPIRY_MONTH;
        this.EXPIRY_YEAR = EXPIRY_YEAR;
        this.CVV = CVV;
        this.CARD_HOLDER_NAME = CARD_HOLDER_NAME;
    }

    public SavedUserTransactionCard(String USER_ID, String CARD_NUMBER, String EXPIRY_MONTH, String EXPIRY_YEAR, String CVV, String CARD_HOLDER_NAME) {
        this.USER_ID = USER_ID;
        this.CARD_NUMBER = CARD_NUMBER;
        this.EXPIRY_MONTH = EXPIRY_MONTH;
        this.EXPIRY_YEAR = EXPIRY_YEAR;
        this.CVV = CVV;
        this.CARD_HOLDER_NAME = CARD_HOLDER_NAME;
    }

    public String getTRANSACTION_CARD_ID() {
        return TRANSACTION_CARD_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public String getCARD_NUMBER() {
        return CARD_NUMBER;
    }

    public String getEXPIRY_MONTH() {
        return EXPIRY_MONTH;
    }

    public String getEXPIRY_YEAR() {
        return EXPIRY_YEAR;
    }

    public String getCVV() {
        return CVV;
    }

    public String getCARD_HOLDER_NAME() {
        return CARD_HOLDER_NAME;
    }

    @Override
    public String toString() {
        return "SavedUserTransactionCard{" + "TRANSACTION_CARD_ID=" + TRANSACTION_CARD_ID + ", USER_ID=" + USER_ID + ", CARD_NUMBER=" + CARD_NUMBER + ", EXPIRY_MONTH=" + EXPIRY_MONTH + ", EXPIRY_YEAR=" + EXPIRY_YEAR + ", CVV=" + CVV + ", CARD_HOLDER_NAME=" + CARD_HOLDER_NAME + '}';
    }

}
