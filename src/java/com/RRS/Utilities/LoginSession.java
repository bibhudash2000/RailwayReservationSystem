package com.RRS.Utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginSession {

   public static final String SQL_SQL_ID = "ID";
   public static final String SQL_UserID = "User_ID";
   public static final String SQL_LastLogin = "Last_Login";
   public static final String SQL_Logout = "Logout";
   public static final String SQL_OS_Platform = "OS_Platform";
   public static final String SQL_BrowserName_Version = "Browser_Name_Version";
   public static final String SQL_IP_Address = "IP_Address";
   public static final String SQL_SessionID = "Session_ID";
    
    String ID;
    String UserID;
    String LastLogin;
    String Logout;
    String OS_Platform;
    String BrowserName_Version;
    String IP_Address;
    String SessionID;

    public LoginSession(String ID, String UserID, String LastLogin, String Logout, String OS_Platform, String SessionID) {
        this.ID = ID;
        this.UserID = UserID;
        this.LastLogin = LastLogin;
        this.Logout = Logout;
        this.OS_Platform = OS_Platform;
        this.SessionID = SessionID;
    }

    public LoginSession(String ID, String UserID, String LastLogin, String Logout, String OS_Platform, String BrowserName_Version, String IP_Address, String SessionID) {
        this.ID = ID;
        this.UserID = UserID;
        this.LastLogin = LastLogin;
        this.Logout = Logout;
        this.OS_Platform = OS_Platform;
        this.BrowserName_Version = BrowserName_Version;
        this.IP_Address = IP_Address;
        this.SessionID = SessionID;
    }

    
    
    public LoginSession() {
    }
    public LoginSession(String UserAgent,HttpServletRequest request,HttpSession session) {
        this.IP_Address = getIPInfo(request);
        this.OS_Platform = getOSInfo(UserAgent);
        this.BrowserName_Version = getBrowserInfo(UserAgent);
        this.SessionID = session.getId();
    }

    public String getID() {
        return ID;
    }

    public String getUserID() {
        return UserID;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public String getLogout() {
        return Logout;
    }

    public String getOS_Platform() {
        return OS_Platform;
    }

    public String getSessionID() {
        return SessionID;
    }

    public String getBrowserName_Version() {
        return BrowserName_Version;
    }

    public String getIP_Address() {
        return IP_Address;
    }

    private String getIPInfo(HttpServletRequest request) {
        LoginSession ls = new LoginSession();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static String getOSInfo(String UserAgent) {
        UserAgent = UserAgent.toLowerCase();
        String os = "";
        if (UserAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (UserAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (UserAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (UserAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (UserAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown";
        }
        return os;
    }

    private static String getBrowserInfo(String UserAgent) {
        String browserDetails = UserAgent;
        String userAgent = browserDetails;
        String user = userAgent.toLowerCase();
        String browser = "";
        if (user.contains("msie")) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
            }
        } else if (user.contains("chrome")) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
        } else {
            browser = "UnKnown";
        }
        return browser;
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
