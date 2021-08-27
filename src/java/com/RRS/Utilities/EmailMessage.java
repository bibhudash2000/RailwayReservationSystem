package com.RRS.Utilities;

import com.RRS.DAO.ServerDAO;
import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.Server;
import java.sql.SQLException;

public class EmailMessage {

    private String EmailAddress;
    private String EmailSubject;
    private String EmailContent;
    private String EmailUserName;

    public EmailMessage() {
    }

    public static final String getEmailConfirmationFormat(String UrlAddress, String UserName) {
        return "<html>"
                + "    <body>"
                + "        <div style='background: #3f51b5; display: flex; align-items: center;'>"
                + "            <img src='http://localhost:8084/RailwayReservationSystem/img/train_ico.png' style='height: 50px; width: 50px;'>"
                + "            <h1 style='color: white;'>RRS</h1>"
                + "        </div>"
                + "        <div style='justify-content: center; text-align: center;background: #F6F6F6;'>"
                + "            <img src='http://localhost:8084/RailwayReservationSystem/img/email.png' style='height: 200px; width:500px;'>"
                + "        </div>"
                + "        <div style='justify-content: center; text-align: center; background: #ffffff; margin-left: 230px;margin-right: 230px;'>"
                + "            <h1 style='font-family: sans-serif; font-weight: 100;'>Email Confirmation</h1>"
                + "            <p style='font-size: 20px;'>Hey " + UserName + ", you're almost ready to start booking your trains."
                + "            Simply click the big blue button below to verify your email address.</p>"
                + "            <a href='"+UrlAddress+"' onmouseover='this.style.color='#000'' onmouseout=''this.style.color='#FFF'' style='transition: 0.2s ease-in ;background: #3f51b5; color: white;font-size: 20px; text-decoration: none; padding: 12px; margin-top: 5px;border-radius: 5px;'>"
                + "                Verify email address</a>"
                + "        </div>"
                + "    </body>"
                + "</html>";
    }
    
    public final String getEmailConfirmationFormat(String UrlAddress) throws ClassNotFoundException, SQLException, DatabaseException {
        Server s = ServerDAO.getServer();
        return "<html>"
                + "    <body>"
                + "        <div style='background: #3f51b5; display: flex; align-items: center;'>"
                + "            <img src='"+s.getServerAddress()+"/RailwayReservationSystem/img/train_ico.png' style='height: 50px; width: 50px;'>"
                + "            <h1 style='color: white;'>RRS</h1>"
                + "        </div>"
                + "        <div style='justify-content: center; text-align: center;background: #F6F6F6;'>"
                + "            <img src='"+s.getServerAddress()+"/RailwayReservationSystem/img/email.png' style='height: 200px; width:500px;'>"
                + "        </div>"
                + "        <div style='justify-content: center; text-align: center; background: #ffffff; margin-left: 230px;margin-right: 230px;'>"
                + "            <h1 style='font-family: sans-serif; font-weight: 100;'>Email Confirmation</h1>"
                + "            <p style='font-size: 20px;'>Hey " + this.EmailUserName + ", you're almost ready to start booking your trains."
                + "            Simply click the big blue button below to verify your email address.</p>"
                + "            <a href='"+UrlAddress+"' onmouseover='this.style.color='#000'' onmouseout=''this.style.color='#FFF'' style='transition: 0.2s ease-in ;background: #3f51b5; color: white;font-size: 20px; text-decoration: none; padding: 12px; margin-top: 5px;border-radius: 5px;'>"
                + "                Verify email address</a>"
                + "        </div>"
                + "    </body>"
                + "</html>";
    }
    
    public final String getEmailOTPVerificationFormat(String OTP) throws ClassNotFoundException, SQLException, DatabaseException {
        Server s = ServerDAO.getServer();
        return "<html>"
                + "    <body>"
                + "        <div style='background: #3f51b5; display: flex; align-items: center;'>"
                + "            <img src='"+s.getServerAddress()+"/RailwayReservationSystem/img/train_ico.png' style='height: 50px; width: 50px;'>"
                + "            <h1 style='color: white;'>RRS</h1>"
                + "        </div>"
                + "        <div style='justify-content: center; text-align: center;background: #F6F6F6;'>"
                + "            <img src='"+s.getServerAddress()+"/RailwayReservationSystem/img/email.png' style='height: 200px; width:500px;'>"
                + "        </div>"
                + "        <div style='justify-content: center; text-align: center; background: #ffffff; margin-left: 230px;margin-right: 230px;'>"
                + "            <h1 style='font-family: sans-serif; font-weight: 100;'>Email Confirmation</h1>"
                + "            <p style='font-size: 20px;'>Hey " + this.EmailUserName + ", your OTP is " + OTP + "."
                + "            Please Freeze your account if you have not initiated this operation.</p>"
                + "        </div>"
                + "    </body>"
                + "</html>";
    }

    public EmailMessage(String EmailAddress, String EmailSubject, String EmailContent, String EmailUserName) {
        this.EmailAddress = EmailAddress;
        this.EmailSubject = EmailSubject;
        this.EmailContent = EmailContent;
        this.EmailUserName = EmailUserName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getEmailSubject() {
        return EmailSubject;
    }

    public String getEmailContent() {
        return EmailContent;
    }

    public String getEmailUserName() {
        return EmailUserName;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public void setEmailSubject(String EmailSubject) {
        this.EmailSubject = EmailSubject;
    }

    public void setEmailContent(String EmailContent) {
        this.EmailContent = EmailContent;
    }

    public void setEmailUserName(String EmailUserName) {
        this.EmailUserName = EmailUserName;
    }

}
