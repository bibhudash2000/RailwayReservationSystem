package com.RRS.Models;


public class FAQsQuestion {
    String QuestionID;
    String CategoryID;
    String QuestionContent;
    String QuestionUniqueName;

    public FAQsQuestion(String QuestionID, String CategoryID, String QuestionContent, String QuestionUniqueName) {
        this.QuestionID = QuestionID;
        this.CategoryID = CategoryID;
        this.QuestionContent = QuestionContent;
        this.QuestionUniqueName = QuestionUniqueName;
    }

    

    public FAQsQuestion() {
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public String getQuestionUniqueName() {
        return QuestionUniqueName;
    }
    
    
}
