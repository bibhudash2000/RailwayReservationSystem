package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.Notice;
import com.RRS.Utilities.Database;
import com.RRS.Utilities.DateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoticeDAO {

    private static final String SQL_GET_LIST_NOTICES = "SELECT * FROM NOTICE_BOARD ORDER BY NOTICE_ID DESC";
    private static final String SQL_GET_NOTICE_BY_ID = "SELECT * FROM NOTICE_BOARD WHERE NOTICE_ID = ?";

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    /*
    	NOTICE_ID	NOTICE_TITLE	NOTICE_POSTED_ON	NOTICE_CONTENT	HAS_PDF
     */
    public static List<Notice> getNoticeList() throws SQLException, DatabaseException {
        List<Notice> list = new ArrayList();
        Notice notice;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_LIST_NOTICES);
        rs = pst.executeQuery();
        while (rs.next()) {
            String NoticeID = rs.getString("NOTICE_ID");
            String NoticeTitle = rs.getString("NOTICE_TITLE");
            DateTime NoticePostedOn = new DateTime();
            NoticePostedOn.setDate(rs.getDate("NOTICE_POSTED_ON").toString());
            NoticePostedOn.setTime(rs.getTime("NOTICE_POSTED_ON").toString());
            String NoticeContent = rs.getString("NOTICE_CONTENT");
            Boolean HasPDF = rs.getBoolean("HAS_PDF");
            notice = new Notice(NoticeID, NoticeTitle, NoticePostedOn, NoticeContent, HasPDF);
            list.add(notice);
        }
        return list;
    }

    public static Notice getNoticeByID(String NoticeID) throws SQLException, DatabaseException {
        Notice notice = null;
        int i = 0;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_NOTICE_BY_ID);
        pst.setString(++i, NoticeID);
        rs = pst.executeQuery();
        if (rs.next()) {
            NoticeID = rs.getString("NOTICE_ID");
            String NoticeTitle = rs.getString("NOTICE_TITLE");
            DateTime NoticePostedOn = new DateTime();
            NoticePostedOn.setDate(rs.getDate("NOTICE_POSTED_ON").toString());
            NoticePostedOn.setTime(rs.getTime("NOTICE_POSTED_ON").toString());
            String NoticeContent = rs.getString("NOTICE_CONTENT");
            Boolean HasPDF = rs.getBoolean("HAS_PDF");
            notice = new Notice(NoticeID, NoticeTitle, NoticePostedOn, NoticeContent, HasPDF);
        }
        return notice;
    }

    public static void main(String[] args) {
        try {
            List<Notice> list = getNoticeList();
            
            for(Notice n : list){
                System.out.println(n.getNoticePostedOn().getTime());
            }
            
            
            
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(NoticeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
