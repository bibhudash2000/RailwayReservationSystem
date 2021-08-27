package com.RRS.Models;


public class FAQsAnswer {
    String AnswerID;
    String QuestionID;
    String Answer_Unique_Name;
    String AnswerContent;
    String ReferenceScreenshot;

    public FAQsAnswer(String AnswerID, String QuestionID, String Answer_Unique_Name, String AnswerContent, String ReferenceScreenshot) {
        this.AnswerID = AnswerID;
        this.QuestionID = QuestionID;
        this.Answer_Unique_Name = Answer_Unique_Name;
        this.AnswerContent = AnswerContent;
        this.ReferenceScreenshot = ReferenceScreenshot;
    }

    

    

    public FAQsAnswer() {
    }

    public String getAnswerID() {
        return AnswerID;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public String getAnswerContent() {
        return AnswerContent;
    }

    public String getReferenceScreenshot() {
        return ReferenceScreenshot;
    }

    public String getAnswer_Unique_Name() {
        return Answer_Unique_Name;
    }
    
    
}
