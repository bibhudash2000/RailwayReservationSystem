package com.RRS.Models;


public class OTP {
    private String OTP_ID;
    private String OTP;
    private String GENERATED_FOR;

    public OTP(String OTP_ID, String OTP, String GENERATED_FOR) {
        this.OTP_ID = OTP_ID;
        this.OTP = OTP;
        this.GENERATED_FOR = GENERATED_FOR;
    }

    public OTP(String OTP, String GENERATED_FOR) {
        this.OTP = OTP;
        this.GENERATED_FOR = GENERATED_FOR;
    }

    public OTP() {
    }

    public String getOTP_ID() {
        return OTP_ID;
    }

    public void setOTP_ID(String OTP_ID) {
        this.OTP_ID = OTP_ID;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getGENERATED_FOR() {
        return GENERATED_FOR;
    }

    public void setGENERATED_FOR(String GENERATED_FOR) {
        this.GENERATED_FOR = GENERATED_FOR;
    }
    
    
}
