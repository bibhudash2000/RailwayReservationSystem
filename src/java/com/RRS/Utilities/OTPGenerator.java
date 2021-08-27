package com.RRS.Utilities;

public class OTPGenerator {

    public static String generateOTP() { 
        int randomPin = (int) (Math.random() * 900000) + 100000;
        String otp = String.valueOf(randomPin);
        return otp;
    }
    public static void main(String[] args) {
        System.out.println(generateOTP());
    }
}
