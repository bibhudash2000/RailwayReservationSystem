package com.RRS.Models;


public class Seat {
    private Integer FIRST_AC;
    private Integer TWO_TIER_AC;
    private Integer THREE_TIER_AC;
    private Integer AC_CHAIR_CLASS;
    private Integer SECOND_SEATING;
    private Integer SLEEPER_CLASS;

    public Seat(Integer FIRST_AC, Integer TWO_TIER_AC, Integer THREE_TIER_AC, Integer AC_CHAIR_CLASS, Integer SECOND_SEATING, Integer SLEEPER_CLASS) {
        this.FIRST_AC = FIRST_AC;
        this.TWO_TIER_AC = TWO_TIER_AC;
        this.THREE_TIER_AC = THREE_TIER_AC;
        this.AC_CHAIR_CLASS = AC_CHAIR_CLASS;
        this.SECOND_SEATING = SECOND_SEATING;
        this.SLEEPER_CLASS = SLEEPER_CLASS;
    }
    

    public Integer getFIRST_AC() {
        return FIRST_AC;
    }

    public void setFIRST_AC(Integer FIRST_AC) {
        this.FIRST_AC = FIRST_AC;
    }

    public Integer getTWO_TIER_AC() {
        return TWO_TIER_AC;
    }

    public void setTWO_TIER_AC(Integer TWO_TIER_AC) {
        this.TWO_TIER_AC = TWO_TIER_AC;
    }

    public Integer getTHREE_TIER_AC() {
        return THREE_TIER_AC;
    }

    public void setTHREE_TIER_AC(Integer THREE_TIER_AC) {
        this.THREE_TIER_AC = THREE_TIER_AC;
    }

    public Integer getAC_CHAIR_CLASS() {
        return AC_CHAIR_CLASS;
    }

    public void setAC_CHAIR_CLASS(Integer AC_CHAIR_CLASS) {
        this.AC_CHAIR_CLASS = AC_CHAIR_CLASS;
    }

    public Integer getSECOND_SEATING() {
        return SECOND_SEATING;
    }

    public void setSECOND_SEATING(Integer SECOND_SEATING) {
        this.SECOND_SEATING = SECOND_SEATING;
    }

    public Integer getSLEEPER_CLASS() {
        return SLEEPER_CLASS;
    }

    public void setSLEEPER_CLASS(Integer SLEEPER_CLASS) {
        this.SLEEPER_CLASS = SLEEPER_CLASS;
    }
    
    
    
}
