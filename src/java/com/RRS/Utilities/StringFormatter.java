package com.RRS.Utilities;

public class StringFormatter {

    public static String getLast4Digits(String Input) {
        String Output = "";
        if (Input.length() > 4) {
            Output = Input.substring(Input.length() - 4);
        }
        return Output;
    }

    public static String formatCardNumber(String CardNumber) {
        return CardNumber.replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(maskCardNumber(formatCardNumber("5103-2122-3456-1212")));
    }
    // 5103-2122-3456-1212

    public static String maskCardNumber(String cardNumber, String mask) {

        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            switch (c) {
                case '#':
                    maskedNumber.append(cardNumber.charAt(index));
                    index++;
                    break;
                case 'x':
                    maskedNumber.append(c);
                    index++;
                    break;
                default:
                    maskedNumber.append(c);
                    break;
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }
    
    public static String maskCardNumber(String cardNumber) {

        // format the number
        int index = 0;
        String mask = "##XX-XXXX-XXXX-XX##";
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            switch (c) {
                case '#':
                    maskedNumber.append(cardNumber.charAt(index));
                    index++;
                    break;
                case 'X':
                    maskedNumber.append(c);
                    index++;
                    break;
                default:
                    maskedNumber.append(c);
                    break;
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }

    public static String maskString(String input) {
        String replaced = input.replaceAll("(^[^@]{1}|(?!^)\\G)[^@]", "$1*");
        return replaced;
    }
}
