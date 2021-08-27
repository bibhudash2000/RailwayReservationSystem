package com.RRS.Utilities;


public class BoolValue {
    public static Boolean getBooleanValue(String val) {
        if ((val == null) || val.equals("") || val.equals("false")) {
            return false;
        } else if (val.equals("on") || val.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
}
