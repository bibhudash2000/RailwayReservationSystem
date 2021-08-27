package com.RRS.Utilities;

public class Calculator {

    public static Double getNetValue(Double rate, Double BasePrice) {
        return (BasePrice * rate / 100) + BasePrice;
    }
    
    public static Double getTaxAmount(Double rate, Double BasePrice) {
        return (BasePrice * rate / 100);
    }
    
    public static void main(String[] args) {
        System.out.println(getNetValue(5.0, 510.0));
    }
}
