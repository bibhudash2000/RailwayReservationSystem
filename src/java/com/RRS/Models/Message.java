/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RRS.Models;

/**
 *
 * @author HP
 */
public class Message {

    public static String ALERT_PRIMARY = "alert-primary";
    public static String ALERT_SUCCESS = "alert-success";
    public static String ALERT_DANGER = "alert-danger";
    
    public static final String INVALID_CVV_NUMBER = "Invalid CVV Number Entered ! Please Try again";

    public static final String STATION_ADDED_SUCCESS = "Station Successfully Added";
    public static final String STATION_ADD_FAILED = "Failed to add station";
    public static final String STATION_NULL_VALUE = "Invalid stations selected";
    
    public static final String INVALID_INPUT = "Invalid Train Number or Name";
    private String Content;
    private String CSS;

    public Message(String Content, String CSS) {
        this.Content = Content;
        this.CSS = CSS;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getCSS() {
        return CSS;
    }

    public void setCSS(String CSS) {
        this.CSS = CSS;
    }

}
