package com.RRS.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;


public class IDGenerator {
    private static SimpleDateFormat sdf;
    private static final String FORMAT = "yyddMMhhmmss";
    
    public static String generateID(){
        sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(new Date());
    }
    public static void main(String[] args) {
        System.out.println(generateID());
    }
}
