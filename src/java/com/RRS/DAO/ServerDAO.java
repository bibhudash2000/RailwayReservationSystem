package com.RRS.DAO;


import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.Server;
import com.RRS.Utilities.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerDAO {

    private static final String GET_SERVER_ADDRESS = "SELECT * FROM SERVER WHERE SERVERID = 1";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public static Server getServer() throws ClassNotFoundException, SQLException, DatabaseException {
        Server s = null;
        con = Database.connect();
        pst = con.prepareStatement(GET_SERVER_ADDRESS);
        rs = pst.executeQuery();
        if (rs.next()) {
            String ServerID = rs.getString("ServerID");
            String ServerAddress = rs.getString("ServerAddress");
            s = new Server(ServerID, ServerAddress);
        }
        return s;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, DatabaseException {
        System.out.println(getServer());
    }
}
