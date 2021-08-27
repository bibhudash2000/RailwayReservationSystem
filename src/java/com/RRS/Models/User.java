package com.RRS.Models;

public class User {

    private String UserID;
    private String Name;
    private String Email;
    private String Contact;
    private String Password;
    private String LastLogin;
    private Boolean hasAccountFrozen;
    private String hasRole;

    public User(String UserID, String Name, String Email, String Contact, String Password) {
        this.UserID = UserID;
        this.Name = Name;
        this.Email = Email;
        this.Contact = Contact;
        this.Password = Password;
    }

    public User(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }

    public User(String UserID, String Name, String Email, String Contact) {
        this.UserID = UserID;
        this.Name = Name;
        this.Email = Email;
        this.Contact = Contact;
    }

    public User() {
    }
    

    public User(String Name, String Email, String Contact) {
        this.Name = Name;
        this.Email = Email;
        this.Contact = Contact;
    }

    public User(String Email) {
        this.Email = Email;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Boolean getHasAccountFrozen() {
        return hasAccountFrozen;
    }

    public void setHasAccountFrozen(Boolean hasAccountFrozen) {
        this.hasAccountFrozen = hasAccountFrozen;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(String LastLogin) {
        this.LastLogin = LastLogin;
    }

    public void setHasRole(String hasRole) {
        this.hasRole = hasRole;
    }

    public String getHasRole() {
        return hasRole;
    }

    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", Name=" + Name + ", Email=" + Email + ", Contact=" + Contact + ", Password=" + Password + ", LastLogin=" + LastLogin + ", hasAccountFrozen=" + hasAccountFrozen + '}';
    }

}
