package com.jcy.jcyycback.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 16:52
 **/

public class DateTool {
    public static String DateFormat_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static String DateFormat_yyyy_MM_dd = "yyyy-MM-dd";

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Long getCurrentLongTime() {
        return Long.valueOf((new Date()).getTime());
    }

    public static long StringToDateLong(String dateStr, String formart) {
        Date date = StringToDate(dateStr, formart);
        return (date == null) ? 0L : date.getTime();
    }

    public static Date StringToDate(String date, String formart) {
        if (StringUtils.isBlank(formart))
            formart = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(formart);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date StringToDate(String date) {
        return StringToDate(date, null);
    }

    public static String dateToString(Date date, String formart) {
        if (date == null)
            return "";
        if (StringUtils.isBlank(formart))
            formart = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(formart);
        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, null);
    }

    public static String getCurrDateString(String format) {
        format = StringUtils.isBlank(format) ? "yyyy-MM-dd HH:mm:ss" : format;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getCurrDateString() {
        return getCurrDateString("");
    }

    public static String getFirstOfWeek(String dataStr) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(dataStr));
        int d = 0;
        if (cal.get(7) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(7);
        }
        cal.add(7, d);
        String data1 = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
        cal.add(7, 6);
        return data1;
    }

    public static Date getFirstDayDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int last = cal.getActualMinimum(5);
        cal.set(5, last);
        return cal.getTime();
    }

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {", ", ", ", ", ", ","};
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(7) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String dateToWeek(Date datetime) {
        String[] weekDays = {", ", ", ", ", ", ","};
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(datetime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(7) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static Date getCurWeekFirstDay() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(7) - 1;
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(5, -week);
        return calendar.getTime();
    }

    public static int getCurWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(7) - 1;
        return week;
    }

    public static int getCurDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(5);
        return day;
    }

    public static Date getCurMonthDay(int day) {
        Calendar c = Calendar.getInstance();
        c.set(5, day);
        return c.getTime();
    }

    public static Date getCurMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        c.set(5, c.getActualMinimum(5));
        return c.getTime();
    }

    public static Date getCurMonthLastDay() {
        Calendar c = Calendar.getInstance();
        c.set(5, c.getActualMaximum(5));
        return c.getTime();
    }

    public static int getCurHour() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(11);
        return hour;
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int hour = c.get(11);
        return hour;
    }

    public static Date addDate(int days) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(5, days);
        return calendar.getTime();
    }

    public static boolean isSameDayHour(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        if (calendar1.get(1) == calendar2.get(1) && calendar1
                .get(2) == calendar2.get(2) && calendar1
                .get(5) == calendar2.get(5) && calendar1
                .get(11) == calendar2.get(11))
            return true;
        return false;
    }

    public static Date getNextHourDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(11, 1);
        Date nextHour = calendar.getTime();
        String nextHourStr = dateToString(nextHour, "yyyy-MM-dd HH");
        nextHourStr = nextHourStr + ":00:00";
        return StringToDate(nextHourStr);
    }
}

