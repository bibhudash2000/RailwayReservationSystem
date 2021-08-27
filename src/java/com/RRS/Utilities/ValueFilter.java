package com.RRS.Utilities;

public class ValueFilter {

    public static String filterContent(String Content) {
        int maxLength = (Content.length() < 50) ? Content.length() : 50;
        Content = Content.substring(0, maxLength);

        return Content;
    }
    public static void main(String[] args) {
        String text = "The first digit is the minimum length the string will be left padded if it's shorter), the second digit is the maxiumum length and the string will be truncated if it's longer. So";
        System.out.println(filterContent(text));
    }
}
