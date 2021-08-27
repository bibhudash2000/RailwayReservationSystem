
package com.RRS.Utilities;


public class WeekDay {
    Boolean Sunday;
    Boolean Monday;
    Boolean Tuesday;
    Boolean Wednesday;
    Boolean Thursday;
    Boolean Friday;
    Boolean Saturday;

    public WeekDay(Boolean Sunday, Boolean Monday, Boolean Tuesday, Boolean Wednesday, Boolean Thursday, Boolean Friday, Boolean Saturday) {
        this.Sunday = Sunday;
        this.Monday = Monday;
        this.Tuesday = Tuesday;
        this.Wednesday = Wednesday;
        this.Thursday = Thursday;
        this.Friday = Friday;
        this.Saturday = Saturday;
    }

    public WeekDay() {
    }

    
    
    public Boolean getSunday() {
        return Sunday;
    }

    public void setSunday(Boolean Sunday) {
        this.Sunday = Sunday;
    }

    public Boolean getMonday() {
        return Monday;
    }

    public void setMonday(Boolean Monday) {
        this.Monday = Monday;
    }

    public Boolean getTuesday() {
        return Tuesday;
    }

    public void setTuesday(Boolean Tuesday) {
        this.Tuesday = Tuesday;
    }

    public Boolean getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Boolean Wednesday) {
        this.Wednesday = Wednesday;
    }

    public Boolean getThursday() {
        return Thursday;
    }

    public void setThursday(Boolean Thursday) {
        this.Thursday = Thursday;
    }

    public Boolean getFriday() {
        return Friday;
    }

    public void setFriday(Boolean Friday) {
        this.Friday = Friday;
    }

    public Boolean getSaturday() {
        return Saturday;
    }

    public void setSaturday(Boolean Saturday) {
        this.Saturday = Saturday;
    }
    public static Boolean check(String m){
        if(m.equals("0")){
            return false;
        }else{
            return true;
        }
    }
    
}
