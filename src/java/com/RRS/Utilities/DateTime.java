package com.RRS.Utilities;

import com.RRS.Models.Week;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DateTime {

    public static String DATE_TIME_hhmm = "hh:mm";
    public static String DATE_TIME_HHmm = "HH:mm";
    public static String DATE_TIME_yyyyMMdd = "yyyy-MM-dd";
    public static String DATE_TIME_ddMMyyyy = "dd/MM/yyyy";
    public static String DATE_TIME_Day_Date_Mon = "EEE dd MMMMM";
    public static String DATE_TIME_HHmm_Day_Date_Mon_Year = "dd MMMMM, yyyy (EEE) | HH:mm aa ";
    public static String DATE_TIME_ddMM_EEE = "dd MMM, EEE";
    public static String DATE_TIME_yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";

    private String Date;
    private String Time;
    private String Value;

    static SimpleDateFormat sdf;

    public DateTime(String Date, String Value) {
        this.Date = Date;
        this.Value = Value;
    }

    public DateTime() {
    }

    public String getDate() {
        return Date;
    }

    public String getValue() {
        return Value;
    }

    public String getTime() {
        return Time;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public static String filterDate(String date) throws ParseException {
        sdf = new SimpleDateFormat(DATE_TIME_HHmm);
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        Date d = sdf1.parse(date);
        return sdf.format(d);
    }

    public static String getDateTime(String InputDateTime, String FromDateFormat, String ToDateFormat) throws ParseException {
        sdf = new SimpleDateFormat(ToDateFormat);
        SimpleDateFormat sdf1 = new SimpleDateFormat(FromDateFormat);
        Date d = sdf1.parse(InputDateTime);
        return sdf.format(d);
    }

    public static String getDateTime(String DateTime) {
        sdf = new SimpleDateFormat(DateTime);
        return sdf.format(new Date());
    }

    public static String getDateTime() {
        sdf = new SimpleDateFormat(DATE_TIME_ddMMyyyy);
        return sdf.format(new Date());
    }

    public static String addDate(String Date, int noOfDays, String Format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Format);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(Date));
        c.add(Calendar.DAY_OF_MONTH, noOfDays);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }

    public static String addDate(String Date, int noOfDays) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_ddMMyyyy);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(Date));
        c.add(Calendar.DAY_OF_MONTH, noOfDays);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }

    public static String addDateUpto4Months() throws ParseException {
        sdf = new SimpleDateFormat(DATE_TIME_yyyyMMdd);
        String Date = sdf.format(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(Date));
        c.add(Calendar.MONTH, 4);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }

    public static String addDateUpto15Days() throws ParseException {
        sdf = new SimpleDateFormat(DATE_TIME_yyyyMMdd);
        String Date = sdf.format(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(Date));
        c.add(Calendar.DATE, 15);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }

    public static String findDifference(String start_date, String end_date) {
        String res = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);
            long difference_In_Time = d2.getTime() - d1.getTime();
            long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time) % 60;
            long difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;
            long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;
            if (difference_In_Days > 0) {
                res = (difference_In_Days + "D " + difference_In_Hours + "H " + difference_In_Minutes + "M");
            } else if (difference_In_Hours > 0) {
                res = (difference_In_Hours + "H " + difference_In_Minutes + "M");
            } else {
                res = (difference_In_Minutes + "M");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Boolean validate(Week week, String Day) {
        Boolean result = false;
        if (week.getMon()) {
            if (Day.equals("Mon")) {
                result = true;
            }
        }
        if (week.getTue()) {
            if (Day.equals("Tue")) {
                result = true;
            }
        }
        if (week.getWed()) {
            if (Day.equals("Wed")) {
                result = true;
            }
        }
        if (week.getThu()) {
            if (Day.equals("Thu")) {
                result = true;
            }
        }
        if (week.getFri()) {
            if (Day.equals("Fri")) {
                result = true;
            }
        }
        if (week.getSat()) {
            if (Day.equals("Sat")) {
                result = true;
            }
        }
        if (week.getSun()) {
            if (Day.equals("Sun")) {
                result = true;
            }
        }

        return result;
    }

    public static String getDayName(String Date) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = sdf1.parse(Date);
        return sdf.format(d);
    }

    public static List<DateTime> listDates(Week week) throws ParseException {
        List<DateTime> list = new ArrayList();
        DateTime dt;
        int counter = 0;
        String date = "";
        String formatDate = "";
        while (counter <= 119) {
            date = (addDate(getDateTime(), counter, DATE_TIME_ddMMyyyy));
            formatDate = getDateTime(date, DATE_TIME_ddMMyyyy, DATE_TIME_ddMM_EEE);
            if (week.getMon()) {
                if (getDayName(date).equals("Mon")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getTue()) {
                if (getDayName(date).equals("Tue")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }

            }
            if (week.getWed()) {
                if (getDayName(date).equals("Wed")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getThu()) {
                if (getDayName(date).equals("Thu")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getFri()) {
                if (getDayName(date).equals("Fri")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSat()) {
                if (getDayName(date).equals("Sat")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSun()) {
                if (getDayName(date).equals("Sun")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            counter++;

        }

        return list;
    }
    
    public static List<DateTime> listDatesUpto7Days(Week week) throws ParseException {
        List<DateTime> list = new ArrayList();
        DateTime dt;
        int counter = 0;
        String date = "";
        String formatDate = "";
        while (counter <= 7) {
            date = (addDate(getDateTime(), counter, DATE_TIME_ddMMyyyy));
            formatDate = getDateTime(date, DATE_TIME_ddMMyyyy, DATE_TIME_ddMM_EEE);
            if (week.getMon()) {
                if (getDayName(date).equals("Mon")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getTue()) {
                if (getDayName(date).equals("Tue")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }

            }
            if (week.getWed()) {
                if (getDayName(date).equals("Wed")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getThu()) {
                if (getDayName(date).equals("Thu")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getFri()) {
                if (getDayName(date).equals("Fri")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSat()) {
                if (getDayName(date).equals("Sat")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSun()) {
                if (getDayName(date).equals("Sun")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            counter++;

        }

        return list;
    }

    public static Boolean checkTrainAvailableDays(Week week, String Date) throws ParseException {
        List<DateTime> list = new ArrayList();
        Boolean isTrainAvailable = false;
        DateTime dt;
        int counter = 0;
        String date = "";
        String formatDate = "";
        while (counter <= 119) {
            date = (addDate(getDateTime(), counter, DATE_TIME_ddMMyyyy));
            formatDate = getDateTime(date, DATE_TIME_ddMMyyyy, DATE_TIME_ddMM_EEE);
            if (week.getMon()) {
                if (getDayName(date).equals("Mon")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getTue()) {
                if (getDayName(date).equals("Tue")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }

            }
            if (week.getWed()) {
                if (getDayName(date).equals("Wed")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getThu()) {
                if (getDayName(date).equals("Thu")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getFri()) {
                if (getDayName(date).equals("Fri")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSat()) {
                if (getDayName(date).equals("Sat")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            if (week.getSun()) {
                if (getDayName(date).equals("Sun")) {
                    dt = new DateTime(formatDate, date);
                    list.add(dt);
                }
            }
            counter++;
        }
        for (DateTime s : list) {
            if (Date.equals(s.getValue())) {
                isTrainAvailable = true;
            }
        }
        return isTrainAvailable;
    }

    public static List<DateTime> listDatesupto15Days() throws ParseException {
        List<DateTime> list = new ArrayList();
        DateTime dt;
        int counter = 0;
        String date = "";
        String formatDate = "";
        while (counter <= 14) {
            date = addDate(getDateTime(DATE_TIME_yyyyMMdd), counter, DATE_TIME_yyyyMMdd);
            formatDate = getDateTime(date, DATE_TIME_yyyyMMdd, DATE_TIME_ddMM_EEE);
            dt = new DateTime(formatDate, date);
            list.add(dt);
            counter++;
        }
        return list;
    }
    public static List<DateTime> listDatesupto7Days() throws ParseException {
        List<DateTime> list = new ArrayList();
        DateTime dt;
        int counter = 0;
        String date = "";
        String formatDate = "";
        while (counter <= 14) {
            date = addDate(getDateTime(DATE_TIME_yyyyMMdd), counter, DATE_TIME_yyyyMMdd);
            formatDate = getDateTime(date, DATE_TIME_yyyyMMdd, DATE_TIME_ddMM_EEE);
            dt = new DateTime(formatDate, date);
            list.add(dt);
            counter++;
        }
        return list;
    }

    public static List<DateTime> listDatesupto15Days(String fromDate, String dateFormat) throws ParseException {
        List<DateTime> list = new ArrayList();
        DateTime dt;
        int counter = 0;
        String date = "";
        String formatDate = "";
        while (counter <= 14) {
            date = addDate(fromDate, counter, dateFormat);
            formatDate = getDateTime(date, dateFormat, DATE_TIME_ddMM_EEE);
            dt = new DateTime(formatDate, date);
            list.add(dt);
            counter++;
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(getDayName("25/04/2021"));
//        System.out.println(getDateTime("2019-04-2",DATE_TIME_yyyyMMdd,DATE_TIME_Day_Date_Mon));
//        System.out.println(getDateTime(DATE_TIME_yyyyMMdd));
        //  System.out.println(addDateUpto4Months());

//         System.out.println(addDate(getDateTime(DATE_TIME_yyyyMMdd), 1,DATE_TIME_yyyyMMdd));
//         System.out.println(getDateTime(DATE_TIME_yyyyMMdd));
//        String dt = getDateTime();
//        Week week = new Week(true, true, false, true, true, false, false);
//        List<DateTime> list = listDates(week);
//        int i = 1;
//        for (DateTime s : list) {
////            System.out.println("Day (" + i + "): " + s);
//            if (dt.equals(s.getValue())) {
//                System.out.println(s.getValue());
//                i++;
//            }
//            
//        }
//System.out.println(getDayName("13/06/2000"));
//        List<DateTime> date = listDatesupto15Days("2021-06-07",DATE_TIME_yyyyMMdd);
//        int j = 0;
//        for (DateTime d : date) {
//            System.out.println(++j+"Date :" + d.getDate() + "| Value :" + d.getValue());
//        }
//        System.out.println(getDateTime(DATE_TIME_yyyyMMdd));
//        Week w = new Week(true,true,false,true,true,false,false);
//        Boolean b = checkTrainAvailableDays(w, "07/06/2021");
//        System.out.println(b);
//        String dt = getDateTime("2021-06-07 01:14:37.0", DATE_TIME_yyyyMMdd_HHmmss, DATE_TIME_HHmm_Day_Date_Mon_Year);
//        System.out.println(dt);
        System.out.println(getDateTime(DATE_TIME_yyyyMMdd_HHmmss));
    }

}
