package com.RRS.DAO;

import com.RRS.Exceptions.DatabaseException;
import com.RRS.Models.FAQsAnswer;
import com.RRS.Models.FAQsCategory;
import com.RRS.Models.FAQsQuestion;
import com.RRS.Utilities.Database;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FAQsDAO {

    public static final String SQL_GET_ALL_FAQS_CATEGORY_LIST = "SELECT * FROM FAQ_CATEGORIES";
    public static final String SQL_GET_ALL_QUESTIONS_UNDER_CATEGORY_LIST_BY_CATEGORY_ID = "SELECT * FROM FAQ_QUESTIONS WHERE CATEGORY_ID = ?";
    public static final String SQL_GET_ANSWER_BY_QUESTION_ID = "SELECT * FROM FAQ_ANSWERS WHERE QUESTION_ID = ?";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    /*
    
CATEGORY_ID
CATEGORY_UNIQUE_NAME
CATEGORY_DESCRIPTION
CATEGORY_NAME

     */
    public static List<FAQsCategory> getAllFAQsCategoryList() throws DatabaseException, SQLException {
        List<FAQsCategory> list = new ArrayList();
        FAQsCategory faq;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_ALL_FAQS_CATEGORY_LIST);
        rs = pst.executeQuery();
        while (rs.next()) {
            String CategoryID = rs.getString("CATEGORY_ID");
            String CategoryUniqueName = rs.getString("CATEGORY_UNIQUE_NAME");
            String CategoryName = rs.getString("CATEGORY_NAME");
            String CategoryDescription = rs.getString("CATEGORY_DESCRIPTION");
            faq = new FAQsCategory(CategoryID, CategoryUniqueName, CategoryName, CategoryDescription);
            list.add(faq);
        }
        return list;
    }

    public static List<FAQsQuestion> getAllFAQsQustionList(String CategoryID) throws DatabaseException, SQLException {
        List<FAQsQuestion> list = new ArrayList();
        FAQsQuestion faq;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_ALL_QUESTIONS_UNDER_CATEGORY_LIST_BY_CATEGORY_ID);
        pst.setString(1, CategoryID);
        rs = pst.executeQuery();
        while (rs.next()) {
            String QuestionID = rs.getString("QUESTION_ID");
            CategoryID = rs.getString("CATEGORY_ID");
            String QuestionContent = rs.getString("QUESTION_CONTENT");
            String QuestionUniqueName = rs.getString("QUESTION_UNIQUE_NAME");
            faq = new FAQsQuestion(QuestionID, CategoryID, QuestionContent, QuestionUniqueName);
            list.add(faq);
        }
        return list;
    }

    public static List<FAQsQuestion> getAllFAQsQustionList(FAQsCategory c) throws DatabaseException, SQLException {
        List<FAQsQuestion> list = new ArrayList();
        FAQsQuestion faq;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_ALL_QUESTIONS_UNDER_CATEGORY_LIST_BY_CATEGORY_ID);
        pst.setString(1, c.getCategoryID());
        rs = pst.executeQuery();
        while (rs.next()) {
            String QuestionID = rs.getString("QUESTION_ID");
            String CategoryID = rs.getString("CATEGORY_ID");
            String QuestionContent = rs.getString("QUESTION_CONTENT");
            String QuestionUniqueName = rs.getString("QUESTION_UNIQUE_NAME");
            faq = new FAQsQuestion(QuestionID, CategoryID, QuestionContent, QuestionUniqueName);
            list.add(faq);
        }
        return list;
    }

    public static FAQsAnswer getAnswerOfFAQ(String QuestionID) throws DatabaseException, SQLException {
        FAQsAnswer answer = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_ANSWER_BY_QUESTION_ID);
        pst.setString(1, QuestionID);
        rs = pst.executeQuery();
        if (rs.next()) {
            String AnswerID = rs.getString("ANSWER_ID");
            String AnswerUniqueName = rs.getString("ANSWER_UNIQUE_NAME");
            String AnswerContent = rs.getString("ANSWER_CONTENT");
            String ReferenceScreenshot = rs.getString("REFERENCE_SCREENSHOT");
            answer = new FAQsAnswer(AnswerID, QuestionID, AnswerUniqueName, AnswerContent, ReferenceScreenshot);
        }
        return answer;
    }

    public static FAQsAnswer getAnswerOfFAQ(FAQsQuestion q) throws DatabaseException, SQLException {
        FAQsAnswer answer = null;
        con = Database.connect();
        pst = con.prepareStatement(SQL_GET_ANSWER_BY_QUESTION_ID);
        pst.setString(1, q.getQuestionID());
        rs = pst.executeQuery();
        if (rs.next()) {
            String AnswerID = rs.getString("ANSWER_ID");
            String QuestionID = rs.getString("QUESTION_ID");
            String AnswerUniqueName = rs.getString("ANSWER_UNIQUE_NAME");
            String AnswerContent = rs.getString("ANSWER_CONTENT");
            String ReferenceScreenshot = rs.getString("REFERENCE_SCREENSHOT");
            answer = new FAQsAnswer(AnswerID, QuestionID, AnswerUniqueName, AnswerContent, ReferenceScreenshot);
        }
        return answer;
    }

    public static void main(String[] args) throws DatabaseException, SQLException {
//        List<FAQsQuestion> list = getAllFAQsQustionList("1");
//        System.out.println(list.size());

        FAQsAnswer ans = getAnswerOfFAQ("1");
        String Folder = System.getProperty("user.dir");
        System.out.println(setPath(Folder));
        //System.out.println(Folder + "/" + "Screenshots" + "/" + ans.getReferenceScreenshot());
    }

    public static String setPath(String Path) {
        Path = Path.replace("\\", "/");
        return Path;
    }
}
