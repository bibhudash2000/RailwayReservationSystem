package com.RRS.Utilities;

import com.RRS.Exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection con;

    public static Connection connect() throws DatabaseException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RRS", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseException("There was unexpected problem when trying to contact the Database."
                    + " This could be a result of local network issue or problem with the machine hosting the Database. "
                    + "Please speak to your Administrator.");
        }

        return con;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, DatabaseException {
        System.out.println(connect());
    }
}
