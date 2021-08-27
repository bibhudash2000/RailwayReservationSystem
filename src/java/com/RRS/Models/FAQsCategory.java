package com.RRS.Models;


public class FAQsCategory {
    String CategoryID;
    String CategoryUniqueName;
    String CategoryName;
    String CategoryDescription;

    public FAQsCategory(String CategoryID, String CategoryUniqueName, String CategoryName, String CategoryDescription) {
        this.CategoryID = CategoryID;
        this.CategoryUniqueName = CategoryUniqueName;
        this.CategoryName = CategoryName;
        this.CategoryDescription = CategoryDescription;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public String getCategoryUniqueName() {
        return CategoryUniqueName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }
    
    
    
}
