package com.marveliu.web.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:43
 * @Description: 时间相关工具类
 **/

public class DateUtil {

    public static final String START_TIMESTAMP_STR = "2018-07-01 00:00:00";
    public static final long HOUR_HAS_MILLI = 60 * 60 * 1000;

    private static final Locale DEFAULT_LOCALE = Locale.CHINA;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将String类型"2018-01-01 00:00:00" 转换为 毫秒值
     *
     * @param timestamp
     * @return
     */
    public static final Long getTimeStampMillis(String timestamp) {

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    /**
     * 将 毫秒值 转换为 String类型"2018-01-01 00:00:00"
     *
     * @param millisNum
     * @return
     */
    public static final String getDateStr(Long millisNum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(millisNum);
    }

    /**
     * 获取当前时间(HH:mm:ss)
     *
     * @return
     */
    public static String getDate() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd", DEFAULT_LOCALE);
    }

    /**
     * 获取当前时间(HH:mm:ss)
     *
     * @return
     */
    public static String getTime() {
        return DateFormatUtils.format(new Date(), "HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 获取当前时间(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getDateTime() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 转换日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 转换日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @param f
     * @return
     */
    public static String format(Date date, String f) {
        if (date == null) return "";
        return DateFormatUtils.format(date, f, DEFAULT_LOCALE);
    }

    /**
     * 时间戳日期
     *
     * @param time
     * @return
     */
    public static Date getDate(long time) {
        return new Date(Long.valueOf(time) * 1000L);
    }

    /**
     * 时间戳日期
     *
     * @param time
     * @return
     */
    public static Date getDate13(long time) {
        return new Date(Long.valueOf(time));
    }

    /**
     * 通过字符串时间获取时间戳
     *
     * @param date
     * @return
     */
    public static long getTime(String date) {
        try {
            return parse(sdf, date).getTime() / 1000;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 通过字符串时间获取时间戳
     *
     * @param date
     * @return
     */
    public static long getTime(SimpleDateFormat sdf, String date) {
        try {
            return parse(sdf, date).getTime() / 1000;
        } catch (ParseException e) {
            return 0;
        }
    }


    /**
     * 时间戳转换成日期字符串
     *
     * @param time
     * @return
     */
    public static String getDateString(long time) {
        return DateFormatUtils.format(new Date(time * 1000), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 时间戳转换成日期字符串,支持格式化输出
     *
     * @param time
     * @param f
     * @return
     */
    public static String getDateString(long time, String f) {
        return DateFormatUtils.format(new Date(time * 1000), f, DEFAULT_LOCALE);
    }

    /**
     * 以给定的时间格式来安全的解析时间字符串，并返回解析后所对应的时间对象
     *
     * @param fmt 时间格式
     * @param s   日期时间字符串
     * @return 该时间字符串对应的时间对象
     */
    public static Date parse(DateFormat fmt, String s) throws ParseException {
        return ((DateFormat) fmt.clone()).parse(s);
    }

    /**
     * 取Unix时间戳
     *
     * @return 时间戳
     */
    public static long getTS() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 取Unix时间戳
     *
     * @return 时间戳
     */
    public static long getTS13() {
        return System.currentTimeMillis();
    }

    /**
     * Date日期转Unix时间戳
     *
     * @param date 日期
     * @return 时间戳
     */
    public static long d2TS(Date date) {
        if (ObjectUtils.isEmpty(date)) {
            return getTS();
        } else {
            return date.getTime() / 1000;
        }
    }

    /**
     * 取得指定日期过 day 天后的日期 (当 day 为负数表示指日期之前);
     *
     * @param date 日期 为null时表示当天
     * @param day  相加(相减)的月数
     */
    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }

    /**
     * 取得指定日期过几个小时后的日期 (当 hour 为负数表示指日期之前);
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date nextHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(10, hour);
        return cal.getTime();
    }

    /**
     * 取得指定日期过几个分钟后的日期 (当 minute 为负数表示指日期之前);
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date nextMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(12, minute);
        return cal.getTime();
    }

    /**
     * 取得指定日期过几个秒钟后的日期 (当 second 为负数表示指日期之前);
     *
     * @param date
     * @param second
     * @return
     */
    public static Date nextSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(13, second);
        return cal.getTime();
    }

    /**
     * 获得年
     *
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Integer.toString(calendar.get(Calendar.YEAR));
    }

    /**
     * 获得月
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Integer.toString(calendar.get(Calendar.MONTH));
    }

    /**
     * 获取今夜时间
     *
     * @return
     */
    public static long getTonight() {
        long now = System.currentTimeMillis() / 1000L;
        long daySecond = 60 * 60 * 24;
        long dayTime = now - (now + 8 * 3600) % daySecond;
        return dayTime + daySecond;
    }

    /**
     * 获取指定夜时间
     *
     * @return
     */
    public static long getThatNight(Date date) {
        long now = d2TS(date);
        long daySecond = 60 * 60 * 24;
        long dayTime = now - (now + 8 * 3600) % daySecond;
        return dayTime + daySecond;
    }

    /**
     * 获取指定夜2点
     *
     * @return
     */
    public static Date getThatMiddleNight(Date date) {
        return DateUtil.nextHour(DateUtil.getDate(DateUtil.getThatNight(date)), 2);
    }

    /**
     * 获取今夜时间
     *
     * @return
     */
    public static Date getMiddleTonight() {
        return DateUtil.nextHour(DateUtil.getDate(DateUtil.getTonight()), 2);
    }

    /**
     * 获取半夜两点
     *
     * @return
     */
    public static Date getMiddleTommorwNight() {
        return DateUtil.nextHour(DateUtil.nextDay(DateUtil.getDate(DateUtil.getTonight()), 1), 2);
    }

    /**
     * 判断是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 判断是否是同一天
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 小时转换成秒
     *
     * @param hour
     * @return
     */
    public static long hourTS(int hour) {
        return hour * 60 * 60;
    }

    /**
     * 两个日期之间相差的天数
     *
     * @param startD
     * @param endD
     * @return
     */
    public static int differentDays(Date startD, Date endD) {
        return (int) ((endD.getTime() - startD.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 判断选择的日期是否是本月
     */
    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    /**
     * 判断日期是否是本季度
     *
     * @param time
     * @return
     */
    public static boolean isThisQuart(long time) {
        long startTime = getCurrentQuarterStartTime().getTime();
        long endTime = getCurrentQuarterEndTime().getTime();
        return time >= startTime && time <= endTime;
    }

    /**
     * 判断日期是否是本半年
     *
     * @param time
     * @return
     */
    public static boolean isThisHalfYear(long time) {
        long startTime = getHalfYearStartTime().getTime();
        long endTime = getHalfYearEndTime().getTime();
        return time >= startTime && time <= endTime;
    }

    /**
     * 判断选择的日期是否是本年
     */
    public static boolean isThisYear(long time) {
        return isThisTime(time, "yyyy");
    }

    /**
     * 判断选择的日期是否是本年
     */
    private static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }


    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间。即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentQuarterStartTime());
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }

    /**
     * 获取前/后半年的开始时间
     *
     * @return
     */
    public static Date getHalfYearStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;

    }

    /**
     * 获取前/后半年的结束时间
     *
     * @return
     */
    public static Date getHalfYearEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

}
