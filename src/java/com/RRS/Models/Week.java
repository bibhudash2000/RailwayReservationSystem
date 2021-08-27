package com.RRS.Models;

public class Week {

    private Boolean Mon;
    private Boolean Tue;
    private Boolean Wed;
    private Boolean Thu;
    private Boolean Fri;
    private Boolean Sat;
    private Boolean Sun;

    public Week(Boolean Mon, Boolean Tue, Boolean Wed, Boolean Thu, Boolean Fri, Boolean Sat, Boolean Sun) {
        this.Mon = Mon;
        this.Tue = Tue;
        this.Wed = Wed;
        this.Thu = Thu;
        this.Fri = Fri;
        this.Sat = Sat;
        this.Sun = Sun;
    }

    public Boolean getMon() {
        return Mon;
    }

    public void setMon(Boolean Mon) {
        this.Mon = Mon;
    }

    public Boolean getTue() {
        return Tue;
    }

    public void setTue(Boolean Tue) {
        this.Tue = Tue;
    }

    public Boolean getWed() {
        return Wed;
    }

    public void setWed(Boolean Wed) {
        this.Wed = Wed;
    }

    public Boolean getThu() {
        return Thu;
    }

    public void setThu(Boolean Thu) {
        this.Thu = Thu;
    }

    public Boolean getFri() {
        return Fri;
    }

    public void setFri(Boolean Fri) {
        this.Fri = Fri;
    }

    public Boolean getSat() {
        return Sat;
    }

    public void setSat(Boolean Sat) {
        this.Sat = Sat;
    }

    public Boolean getSun() {
        return Sun;
    }

    public void setSun(Boolean Sun) {
        this.Sun = Sun;
    }

    

   

}
