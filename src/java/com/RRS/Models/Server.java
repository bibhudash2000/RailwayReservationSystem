package com.RRS.Models;


public class Server {
    private String ServerID;
    private String ServerAddress;

    public Server(String ServerID, String ServerAddress) {
        this.ServerID = ServerID;
        this.ServerAddress = ServerAddress;
    }

    public String getServerID() {
        return ServerID;
    }

    public String getServerAddress() {
        return ServerAddress;
    }
    
    
}
