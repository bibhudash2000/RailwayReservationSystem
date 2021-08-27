package com.RRS.Models;

public class Station {

    Integer Station_ID;
    String Station_Code;
    String Station_Name;
    String City;
    String State;
    String Short_Description;
    String Zone;
    
    public Station(Integer Station_ID, String Station_Code, String Station_Name) {
        this.Station_ID = Station_ID;
        this.Station_Code = Station_Code;
        this.Station_Name = Station_Name;
    }

    public Station(String Station_Code, String Station_Name, String City) {
        this.Station_Code = Station_Code;
        this.Station_Name = Station_Name;
        this.City = City;
    }

    public Station(Integer Station_ID, String Station_Code, String Station_Name, String City, String State, String Short_Description) {
        this.Station_ID = Station_ID;
        this.Station_Code = Station_Code;
        this.Station_Name = Station_Name;
        this.City = City;
        this.State = State;
        this.Short_Description = Short_Description;
    }

    public Station(String Station_Code, String Station_Name, String City, String State, String Short_Description, String Zone) {
        this.Station_Code = Station_Code;
        this.Station_Name = Station_Name;
        this.City = City;
        this.State = State;
        this.Short_Description = Short_Description;
        this.Zone = Zone;
    }
    

    public Station() {
    }

    public Station(Integer Station_ID, String Station_Code, String Station_Name, String City, String State) {
        this.Station_ID = Station_ID;
        this.Station_Code = Station_Code;
        this.Station_Name = Station_Name;
        this.City = City;
        this.State = State;
    }

    public Integer getStation_ID() {
        return Station_ID;
    }

    public void setStation_ID(Integer Station_ID) {
        this.Station_ID = Station_ID;
    }

    public String getStation_Code() {
        return Station_Code;
    }

    public void setStation_Code(String Station_Code) {
        this.Station_Code = Station_Code;
    }

    public String getStation_Name() {
        return Station_Name;
    }

    public void setStation_Name(String Station_Name) {
        this.Station_Name = Station_Name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getShort_Description() {
        return Short_Description;
    }

    public void setShort_Description(String Short_Description) {
        this.Short_Description = Short_Description;
    }

    public String getZone() {
        return Zone;
    }

    @Override
    public String toString() {
        return "Station{" + "Station_ID=" + Station_ID + ", Station_Code=" + Station_Code + ", Station_Name=" + Station_Name + ", City=" + City + ", State=" + State + ", Short_Description=" + Short_Description + ", Zone=" + Zone + '}';
    }

    
    

}
