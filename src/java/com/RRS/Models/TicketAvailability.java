package com.RRS.Models;


public class TicketAvailability {
    Integer TicketCount;
    String AvailabilityMessage;

    public TicketAvailability(Integer TicketCount, String AvailabilityMessage) {
        this.TicketCount = TicketCount;
        this.AvailabilityMessage = AvailabilityMessage;
    }

    
    
    public Integer getTicketCount() {
        return TicketCount;
    }

    public String getAvailabilityMessage() {
        return AvailabilityMessage;
    }
    
    
}
