package com.hoping.owl.flymock.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by houping wang on 2019/3/13
 *
 * @author houping wang
 */
public class DateUtil {

    public static String[] PATTERN = new String[]{"yyyy-MM", "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd",
            "yyyy/MM/dd", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};

    private static final Integer THREE_MONTH = 3;

    private static final Integer SIX_MONTH = 6;

    private static final Integer NINE_MONTH = 0;

    public static Date convertToDate(String source, String pattern) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertToString(Date source, String pattern) {
        String str = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            str = dateFormat.format(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static Date getDayBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得当前时间的整点
     *
     * @param date date
     * @return Date
     */
    public static Date getDayOnTheHour(Date date) {
        return getHourBeginTime(date);
    }

    /**
     * 获得当前时间的前一个整点
     *
     * @param date date
     * @return Date
     */
    public static Date getDayOnTheHourBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得 day天之后的date 如果day为负数 则获得day天之前的date
     *
     * @param date date
     * @param day  day
     * @return Date
     */
    public static Date getDayBefore(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date getDayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getWeekBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getWeekEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getMonthBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getMonthEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getTenMinuteBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if (minute.length() > 1) {
            minute = minute.substring(0, 1) + "0";
        } else {
            minute = "0";
        }
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getMinuteBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getMinuteEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getSecondBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 1);
        return calendar.getTime();
    }

    public static Date getSecondEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 1);
        return calendar.getTime();
    }

    public static Date getTenMinuteEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if (minute.length() > 1) {
            minute = minute.substring(0, 1) + "9";
        } else {
            minute = "9";
        }
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getHourBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getHourEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getQuarterBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month <= THREE_MONTH) {
            calendar.set(Calendar.MONTH, 0);
        } else if (month <= SIX_MONTH) {
            calendar.set(Calendar.MONTH, 3);
        } else if (month <= NINE_MONTH) {
            calendar.set(Calendar.MONTH, 6);
        } else {
            calendar.set(Calendar.MONTH, 9);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getQuarterEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month <= THREE_MONTH) {
            calendar.set(Calendar.MONTH, 2);
        } else if (month <= SIX_MONTH) {
            calendar.set(Calendar.MONTH, 5);
        } else if (month <= NINE_MONTH) {
            calendar.set(Calendar.MONTH, 8);
        } else {
            calendar.set(Calendar.MONTH, 11);
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getHalfYearBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (month < SIX_MONTH) {
            calendar.set(Calendar.MINUTE, 0);
        } else {
            calendar.set(Calendar.MINUTE, 5);
        }
        return calendar.getTime();
    }

    public static Date getHalfYearEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (month < SIX_MONTH) {
            calendar.set(Calendar.MINUTE, 6);
        } else {
            calendar.set(Calendar.MINUTE, 11);
        }
        return calendar.getTime();
    }

    public static Date getYearBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getYearEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 判断超时
     *
     * @param histime 历史时间
     * @param timeout 超时时差
     * @return 超时返回treu, 否则返回false
     */
    public static Boolean isTimeout(Date histime, Integer timeout) {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        Date curtime = calendar.getTime();
        calendar.setTime(histime);
        calendar.add(Calendar.MILLISECOND, timeout);
        if (calendar.getTime().after(curtime)) {
            flag = true;
        }
        return flag;
    }

    public static DateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static DateFormat getDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static int getDayByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMonthBeginTime(date));
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getMinutesOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour * 60 + minute;
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 根据输入年月获取当前月的天数.
     *
     * @param yearmonth yearmonth
     * @return int
     */
    public static int daysofmonth(String yearmonth) {
        Calendar rightNow = Calendar.getInstance();
        //如果写成年月日的形式的话，要写小d，如："yyyy/MM/dd"
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM");
        try {
            rightNow.setTime(simpleDate.parse(yearmonth));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    /**
     * 根据年和周获取周的开始日期
     *
     * @param year   year
     * @param weekNo weekNo
     * @return String
     */
    public static String getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * 根据年和周获取周的结束日期
     *
     * @param year   year
     * @param weekNo weekNo
     * @return String
     */
    public static String getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }
}
