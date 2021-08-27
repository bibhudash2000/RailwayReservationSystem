package com.RRS.Models;

import java.util.List;

public class PNR {

    Train train;
    List<Passenger> passengers;
    Ticket ticket;
    User user;
    Station srcStation, destStation;

    public PNR(Train train, List<Passenger> passengers, Ticket ticket, User user, Station srcStation, Station destStation) {
        this.train = train;
        this.passengers = passengers;
        this.ticket = ticket;
        this.user = user;
        this.srcStation = srcStation;
        this.destStation = destStation;
    }
    public PNR(Train train, Ticket ticket, User user, Station srcStation, Station destStation) {
        this.train = train;
        this.ticket = ticket;
        this.user = user;
        this.srcStation = srcStation;
        this.destStation = destStation;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Station getSrcStation() {
        return srcStation;
    }

    public void setSrcStation(Station srcStation) {
        this.srcStation = srcStation;
    }

    public Station getDestStation() {
        return destStation;
    }

    public void setDestStation(Station destStation) {
        this.destStation = destStation;
    }
    
    
}
