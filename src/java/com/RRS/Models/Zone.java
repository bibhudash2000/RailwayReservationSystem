package com.RRS.Models;


public class Zone {
    Integer Zone_ID;
    String Zone_Abbreviation;
    String Zone_Name;
    String Zone_Headquater;
    String Zone_Divisions;

    public Zone(Integer Zone_ID, String Zone_Abbreviation, String Zone_Name, String Zone_Headquater, String Zone_Divisions) {
        this.Zone_ID = Zone_ID;
        this.Zone_Abbreviation = Zone_Abbreviation;
        this.Zone_Name = Zone_Name;
        this.Zone_Headquater = Zone_Headquater;
        this.Zone_Divisions = Zone_Divisions;
    }

    public Zone(String Zone_Abbreviation, String Zone_Name, String Zone_Headquater, String Zone_Divisions) {
        this.Zone_Abbreviation = Zone_Abbreviation;
        this.Zone_Name = Zone_Name;
        this.Zone_Headquater = Zone_Headquater;
        this.Zone_Divisions = Zone_Divisions;
    }

    
    public Zone() {
    }

    public Integer getZone_ID() {
        return Zone_ID;
    }

    public void setZone_ID(Integer Zone_ID) {
        this.Zone_ID = Zone_ID;
    }

    public String getZone_Abbreviation() {
        return Zone_Abbreviation;
    }

    public void setZone_Abbreviation(String Zone_Abbreviation) {
        this.Zone_Abbreviation = Zone_Abbreviation;
    }

    public String getZone_Name() {
        return Zone_Name;
    }

    public void setZone_Name(String Zone_Name) {
        this.Zone_Name = Zone_Name;
    }

    public String getZone_Headquater() {
        return Zone_Headquater;
    }

    public void setZone_Headquater(String Zone_Headquater) {
        this.Zone_Headquater = Zone_Headquater;
    }

    public String getZone_Divisions() {
        return Zone_Divisions;
    }

    public void setZone_Divisions(String Zone_Divisions) {
        this.Zone_Divisions = Zone_Divisions;
    }

    @Override
    public String toString() {
        return "Zone{" + "Zone_ID=" + Zone_ID + ", Zone_Abbreviation=" + Zone_Abbreviation + ", Zone_Name=" + Zone_Name + ", Zone_Headquater=" + Zone_Headquater + ", Zone_Divisions=" + Zone_Divisions + '}';
    }
    
    
    
}
