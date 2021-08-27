package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Exceptions.UserException;
import com.RRS.Models.User;
import com.RRS.Utilities.Database;
import com.RRS.Utilities.DateTime;
import static com.RRS.Utilities.DateTime.DATE_TIME_yyyyMMdd_HHmmss;
import com.RRS.Utilities.IDGenerator;
import com.RRS.Utilities.LoginSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class UserDAO {

    private static final String SQL_AUTHENTICATE_USER = "SELECT USER.USERID,USER.NAME,USER.CONTACT,USER.EMAIL,USER_ACC.HAS_ACCOUNT_FROZEN FROM "
            + "USERS AS USER,USER_ACCOUNTS AS USER_ACC WHERE "
            + "USER_ACC.USERID = USER.USERID AND "
            + "USER.EMAIL = ? AND USER_ACC.PASSWORD = ?";
    private static final String SQL_ADD_USER = "INSERT INTO USERS(USERID,NAME,EMAIL,CONTACT) VALUES(?,?,?,?)";

    private static final String SQL_VIEW_USER_BY_EMAIL = "SELECT * FROM USERS WHERE EMAIL = ?";

    private static final String SQL_CHECK_USER_IF_EXISTS = "SELECT * FROM USERS WHERE EMAIL = ? OR CONTACT = ? ";

    private static final String SQL_ADD_USER_PASSWORD = "INSERT INTO USER_ACCOUNTS(USERID,PASSWORD) VALUES(?,?)";

    private static final String SQL_VERIFY_USER_PASSWORD = "SELECT USER_ACCOUNTS.PASSWORD FROM USER_ACCOUNTS WHERE USER_ACCOUNTS.USERID = ?";

    private static final String SQL_ADD_LAST_LOGIN = "INSERT INTO USER_LOGINS(USER_ID,BROWSER_NAME_VERSION,OS_PLATFORM,IP_ADDRESS,SESSION_ID) VALUES(?,?,?,?,?)";

    private static final String SQL_FETCH_LAST_LOGIN = "SELECT * FROM USER_LOGINS WHERE USER_ID = ? ORDER BY ID DESC LIMIT 1,1";

    private static final String SQL_SET_USER_ACCOUNT_FREEZE = "UPDATE USER_ACCOUNTS SET HAS_ACCOUNT_FROZEN = 1 WHERE USERID=?";

    private static final String SQL_SET_USER_ACCOUNT_UNFREEZE = "UPDATE USER_ACCOUNTS SET HAS_ACCOUNT_FROZEN = 0 WHERE USERID=?";

    private static final String SQL_GET_ALL_SESSIONS_NOT_HAVING_LOGOUT = "SELECT * FROM USER_LOGINS WHERE USER_ID = ? AND LOGOUT IS NULL";

    private static final String SQL_UPDATE_USER_LOGOUT = "UPDATE USER_LOGINS SET LOGOUT = ? WHERE SESSION_ID = ?";

    private static final String SQL_FORCE_UPDATE_ALL_USER_SESSIONS_WHERE_LOGOUT_IS_NULL = "UPDATE USER_LOGINS SET LOGOUT = CURRENT_TIMESTAMP() "
            + "WHERE USER_ID = ? AND LOGOUT IS NULL";

    private static final String SQL_VALIDATE_IF_SESSION_EXISTS = "SELECT LOGOUT FROM USER_LOGINS WHERE SESSION_ID = ?";

    private static final String SQL_COUNT_CUSTOMER_LIST = "SELECT COUNT(*) FROM USER_ACCOUNTS,USERS,USER_ROLES "
            + "WHERE USERS.USERID = USER_ACCOUNTS.USERID AND USER_ACCOUNTS.HAS_ROLE = USER_ROLES.ROLE_ID AND USER_ROLES.ROLE_NAME <> 'ADMIN' ";

    private static final String SQL_GET_USER_ROLE = "SELECT * FROM USER_ACCOUNTS,USER_ROLES WHERE "
            + "USER_ACCOUNTS.HAS_ROLE = USER_ROLES.ROLE_ID AND USER_ACCOUNTS.USERID = ?";

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public static String getUserRole(String UserID) throws DatabaseException, SQLException {
        String RoleName = "";
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_USER_ROLE);
        pst.setString(1, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            RoleName = rs.getString("ROLE_NAME");
        }
        return RoleName;
    }

    // This is incomplete
    public static Boolean validateIfSessionExpired(String SessionID) throws DatabaseException, SQLException {
        Boolean expired = true;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VALIDATE_IF_SESSION_EXISTS);
        pst.setString(++i, SessionID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String Logout = rs.getString("LOGOUT");
            if (Logout == null) {
                expired = false;
            }
        }
        return expired;
    }

    public static LoginSession fetchLastLoginSession(String UserID) throws DatabaseException, SQLException {
        LoginSession ls = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_FETCH_LAST_LOGIN);
        pst.setString(1, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String ID = rs.getString(LoginSession.SQL_SQL_ID);
            UserID = rs.getString(LoginSession.SQL_UserID);
            String LastLogin = rs.getString(LoginSession.SQL_LastLogin);
            String Logout = rs.getString(LoginSession.SQL_Logout);
            String OS_Platform = rs.getString(LoginSession.SQL_OS_Platform);
            String BrowserName_Version = rs.getString(LoginSession.SQL_BrowserName_Version);
            String IP_Address = rs.getString(LoginSession.SQL_IP_Address);
            String SessionID = rs.getString(LoginSession.SQL_SessionID);
            ls = new LoginSession(ID, UserID, LastLogin, Logout, OS_Platform, BrowserName_Version, IP_Address, SessionID);
        }
        return ls;
    }

    public static LoginSession fetchLastLoginSession(User user) throws DatabaseException, SQLException {
        LoginSession ls = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_FETCH_LAST_LOGIN);
        pst.setString(1, user.getUserID());
        rs = pst.executeQuery();
        if (rs.next()) {
            String ID = rs.getString(LoginSession.SQL_SQL_ID);
            String UserID = rs.getString(LoginSession.SQL_UserID);
            String LastLogin = rs.getString(LoginSession.SQL_LastLogin);
            String Logout = rs.getString(LoginSession.SQL_Logout);
            String OS_Platform = rs.getString(LoginSession.SQL_OS_Platform);
            String BrowserName_Version = rs.getString(LoginSession.SQL_BrowserName_Version);
            String IP_Address = rs.getString(LoginSession.SQL_IP_Address);
            String SessionID = rs.getString(LoginSession.SQL_SessionID);
            ls = new LoginSession(ID, UserID, LastLogin, Logout, OS_Platform, BrowserName_Version, IP_Address, SessionID);
        }
        return ls;
    }

    public static Boolean forceUpdateAllUserSessionsWhereLogoutIsNull(String UserID) throws SQLException, DatabaseException {
        int i = 0;
        Boolean updated = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_FORCE_UPDATE_ALL_USER_SESSIONS_WHERE_LOGOUT_IS_NULL);
        pst.setString(++i, UserID);
        updated = pst.executeUpdate() > 0;
        return updated;
    }

    public static Boolean forceUpdateAllUserSessionsWhereLogoutIsNull(User user) throws SQLException, DatabaseException {
        int i = 0;
        Boolean updated = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_FORCE_UPDATE_ALL_USER_SESSIONS_WHERE_LOGOUT_IS_NULL);
        pst.setString(++i, user.getUserID());
        updated = pst.executeUpdate() > 0;
        return updated;
    }

    public static List<LoginSession> listAllActiveSessions(String UserID) throws DatabaseException, SQLException {
        int i = 0;
        List<LoginSession> list = new ArrayList();
        LoginSession ls;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_ALL_SESSIONS_NOT_HAVING_LOGOUT);
        pst.setString(++i, UserID);
        rs = pst.executeQuery();
        while (rs.next()) {
            String ID = rs.getString("ID");
            UserID = rs.getString("USER_ID");
            String LastLogin = rs.getString("LAST_LOGIN");
            String Logout = rs.getString("LOGOUT");
            String OS_PLATFORM = rs.getString("OS_PLATFORM");
            String SessionID = rs.getString("SESSION_ID");
            ls = new LoginSession(ID, UserID, LastLogin, Logout, OS_PLATFORM, SessionID);
            list.add(ls);
        }
        return list;
    }

    public static Boolean updateUserLastLogin(HttpSession session) throws DatabaseException, SQLException {
        int i = 0;
        Boolean updated = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_UPDATE_USER_LOGOUT);
        pst.setString(++i, DateTime.getDateTime(DATE_TIME_yyyyMMdd_HHmmss));
        pst.setString(++i, session.getId());
        updated = pst.executeUpdate() > 0;
        return updated;
    }

    public static Boolean addLastLoginRecord(User u, LoginSession session) throws SQLException, ClassNotFoundException, DatabaseException {
        int i = 0;
        Boolean isAdded = false;
        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_LAST_LOGIN);
        pst.setString(++i, u.getUserID());
        pst.setString(++i, session.getBrowserName_Version());
        pst.setString(++i, session.getOS_Platform());
        pst.setString(++i, session.getIP_Address());
        pst.setString(++i, session.getSessionID());
        isAdded = pst.executeUpdate() > 0;
        return isAdded;
    }

    public static User authenticateUser(User visitor, LoginSession session) throws SQLException, ClassNotFoundException, DatabaseException {
        int i = 0;
        con = Database.connect();
        User user = null;
        pst = con.prepareStatement(SQL_AUTHENTICATE_USER);
        pst.setString(++i, visitor.getEmail());
        pst.setString(++i, visitor.getPassword());
        rs = pst.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setUserID(rs.getString("USER.USERID"));
            user.setName(rs.getString("USER.NAME"));
            user.setEmail(rs.getString("USER.EMAIL"));
            user.setContact(rs.getString("USER.CONTACT"));
            user.setHasAccountFrozen(rs.getBoolean("USER_ACC.HAS_ACCOUNT_FROZEN"));
            String Role = getUserRole(user.getUserID());
            user.setHasRole(Role);
            addLastLoginRecord(user, session);
        }
        con.close();
        return user;
    }

    public static String fetchLastLogin(User u) throws SQLException, ClassNotFoundException, DatabaseException {
        int i = 0;
        String LastLogin = "";
        con = Database.connect();
        pst = con.prepareStatement(SQL_FETCH_LAST_LOGIN);
        pst.setString(++i, u.getUserID());
        rs = pst.executeQuery();
        if (rs.next()) {
            LastLogin = rs.getString("LAST_LOGIN");
        }
        con.close();
        return LastLogin;
    }

    public static Boolean addUser(User user) throws SQLException, ClassNotFoundException, UserException, DatabaseException {
        Boolean isAdded = false;
        int j = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_CHECK_USER_IF_EXISTS);
        pst.setString(++j, user.getEmail());
        pst.setString(++j, user.getContact());
        rs = pst.executeQuery();
        if (rs.next()) {
            throw new UserException("The Email or contact number is linked with another user.....Please try another");
        } else {
            int i = 0;
            pst = con.prepareStatement(SQL_ADD_USER);
            pst.setString(++i, IDGenerator.generateID());
            pst.setString(++i, user.getName());
            pst.setString(++i, user.getEmail());
            pst.setString(++i, user.getContact());
            isAdded = pst.executeUpdate() > 0;
        }
        return isAdded;
    }

    public static Boolean addUsersPassword(String UserID, String Password) throws SQLException, ClassNotFoundException, DatabaseException {
        Boolean isAdded = false;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_USER_PASSWORD);
        pst.setString(++i, UserID);
        pst.setString(++i, Password);

        isAdded = pst.executeUpdate() > 0;
        return isAdded;
    }

    public static Boolean verifyUserPassword(String UserID, String Password) throws SQLException, ClassNotFoundException, DatabaseException, UserException {
        Boolean Verified = false;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_VERIFY_USER_PASSWORD);
        pst.setString(++i, UserID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String UPass = rs.getString("PASSWORD");
            if (Password.equals(UPass)) {
                Verified = true;
            }
        } else {
            throw new UserException("User ID doesn't exist");
        }
        return Verified;
    }

    public static User viewUserByEmail(User visitor) throws SQLException, ClassNotFoundException, DatabaseException {
        con = Database.connect();
        int i = 0;
        User user = null;
        pst = con.prepareStatement(SQL_VIEW_USER_BY_EMAIL);
        pst.setString(++i, visitor.getEmail());
        rs = pst.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setUserID(rs.getString("USERID"));
            user.setName(rs.getString("NAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setContact(rs.getString("CONTACT"));
        }
        con.close();
        return user;
    }

    public static Boolean checkNewUserPasswordStatus(String Email) throws SQLException, ClassNotFoundException, DatabaseException {
        Boolean isAdded = false;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_ADD_USER_PASSWORD);
        pst.setString(++i, Email);

        isAdded = pst.executeUpdate() > 0;
        return isAdded;
    }

    public static Boolean setUserAccountFreeze(User Visitor) throws DatabaseException, SQLException {
        Boolean isActive = false;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_SET_USER_ACCOUNT_FREEZE);
        pst.setString(++i, Visitor.getUserID());

        isActive = pst.executeUpdate() > 0;
        return isActive;
    }

    public static Boolean setUserAccountUnfreeze(User Visitor) throws DatabaseException, SQLException {
        Boolean isActive = false;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_SET_USER_ACCOUNT_UNFREEZE);
        pst.setString(++i, Visitor.getUserID());
        isActive = pst.executeUpdate() > 0;
        return isActive;
    }

    public static Integer countCustomerList() throws SQLException, ClassNotFoundException, DatabaseException {
        Integer count = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_COUNT_CUSTOMER_LIST);
        rs = pst.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        con.close();
        return count;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, UserException, DatabaseException {

//        List<LoginSession> list = listAllActiveSessions("212805084146");
//        for (LoginSession ls : list) {
//            System.out.println(ls.getSessionID());
//        }
        //System.out.println(validateIfSessionExpired("0F1CD3CDB4956BE3EA3B459F97C06AFC"));
        System.out.println(getUserRole("212805084146"));

    }

}
