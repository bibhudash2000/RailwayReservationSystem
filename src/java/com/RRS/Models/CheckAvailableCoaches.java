/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RRS.Models;

public class CheckAvailableCoaches {

    private Boolean FIRST_AC;
    private Boolean TWO_TIER_AC;
    private Boolean THREE_TIER_AC;
    private Boolean AC_CHAIR_CLASS;
    private Boolean SECOND_SEATING;
    private Boolean SLEEPER_CLASS;

    public CheckAvailableCoaches(Boolean FIRST_AC, Boolean TWO_TIER_AC, Boolean THREE_TIER_AC, Boolean AC_CHAIR_CLASS, Boolean SECOND_SEATING, Boolean SLEEPER_CLAS) {
        this.FIRST_AC = FIRST_AC;
        this.TWO_TIER_AC = TWO_TIER_AC;
        this.THREE_TIER_AC = THREE_TIER_AC;
        this.AC_CHAIR_CLASS = AC_CHAIR_CLASS;
        this.SECOND_SEATING = SECOND_SEATING;
        this.SLEEPER_CLASS = SLEEPER_CLAS;
    }

    
    
    
    public Boolean getFIRST_AC() {
        return FIRST_AC;
    }

    public void setFIRST_AC(Boolean FIRST_AC) {
        this.FIRST_AC = FIRST_AC;
    }

    public Boolean getTWO_TIER_AC() {
        return TWO_TIER_AC;
    }

    public void setTWO_TIER_AC(Boolean TWO_TIER_AC) {
        this.TWO_TIER_AC = TWO_TIER_AC;
    }

    public Boolean getTHREE_TIER_AC() {
        return THREE_TIER_AC;
    }

    public void setTHREE_TIER_AC(Boolean THREE_TIER_AC) {
        this.THREE_TIER_AC = THREE_TIER_AC;
    }

    public Boolean getAC_CHAIR_CLASS() {
        return AC_CHAIR_CLASS;
    }

    public void setAC_CHAIR_CLASS(Boolean AC_CHAIR_CLASS) {
        this.AC_CHAIR_CLASS = AC_CHAIR_CLASS;
    }

    public Boolean getSECOND_SEATING() {
        return SECOND_SEATING;
    }

    public void setSECOND_SEATING(Boolean SECOND_SEATING) {
        this.SECOND_SEATING = SECOND_SEATING;
    }

    public Boolean getSLEEPER_CLASS() {
        return SLEEPER_CLASS;
    }

    public void setSLEEPER_CLASS(Boolean SLEEPER_CLASS) {
        this.SLEEPER_CLASS = SLEEPER_CLASS;
    }

    
    
    
}
