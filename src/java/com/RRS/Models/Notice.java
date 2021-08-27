package com.RRS.Models;

import com.RRS.Utilities.DateTime;


public class Notice {
    private String NoticeID;
    private String NoticeTitle;
    private DateTime NoticePostedOn;
    private String NoticeContent;
    private Boolean HasPDF;

    public Notice(String NoticeTitle, String NoticeContent, Boolean NoticeHasPDF) {
        this.NoticeTitle = NoticeTitle;
        this.NoticeContent = NoticeContent;
        this.HasPDF = NoticeHasPDF;
    }

    public Notice(String NoticeTitle, DateTime NoticePostedOn, String NoticeContent, Boolean NoticeHasPDF) {
        this.NoticeTitle = NoticeTitle;
        this.NoticePostedOn = NoticePostedOn;
        this.NoticeContent = NoticeContent;
        this.HasPDF = NoticeHasPDF;
    }

    public Notice(String NoticeID, String NoticeTitle, DateTime NoticePostedOn, String NoticeContent, Boolean NoticeHasPDF) {
        this.NoticeID = NoticeID;
        this.NoticeTitle = NoticeTitle;
        this.NoticePostedOn = NoticePostedOn;
        this.NoticeContent = NoticeContent;
        this.HasPDF = NoticeHasPDF;
    }

    public Notice() {
    }

    public String getNoticeID() {
        return NoticeID;
    }

    public String getNoticeTitle() {
        return NoticeTitle;
    }

    public DateTime getNoticePostedOn() {
        return NoticePostedOn;
    }

    public String getNoticeContent() {
        return NoticeContent;
    }

    public Boolean getHasPDF() {
        return HasPDF;
    }
    
    
    
}
