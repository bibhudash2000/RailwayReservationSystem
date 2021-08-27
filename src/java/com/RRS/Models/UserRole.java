package com.RRS.Models;

import com.RRS.DAO.UserDAO;
import com.RRS.Exceptions.DatabaseException;
import java.sql.SQLException;

public class UserRole {

    public final String USER_ROLE_ADMIN = "ADMIN";
    public final String USER_ROLE_CUSTOMER = "CUSTOMER";

    public Boolean isUserAdmin(String UserID) throws DatabaseException, SQLException {
        String RoleName = UserDAO.getUserRole(UserID);
        if (RoleName.equals(USER_ROLE_ADMIN)) {
            return true;
        }
        return false;
    }

    public Boolean isUserCustomer(String UserID) throws DatabaseException, SQLException {
        String RoleName = UserDAO.getUserRole(UserID);
        if (RoleName.equals(USER_ROLE_CUSTOMER)) {
            return true;
        }
        return false;
    }
}
