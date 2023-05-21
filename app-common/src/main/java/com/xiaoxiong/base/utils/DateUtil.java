package com.xiaoxiong.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lu
 * @since 2019-06-11 19:27
 */
@Slf4j
public class DateUtil {

    public final static String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public final static String YMDHM = "yyyy-MM-dd HH:mm";

    public final static String YMDH = "yyyyMMdd_HH";

    public final static String YMD_WITHOUT_LINE = "yyyyMMdd";

    public final static String YMD_WITH_LINE = "yyyy-MM-dd";

    public final static String YMD_WITH_POINT = "yyyy.MM.dd";

    public final static String YMD_HM_WITH_POINT = "yyyy.MM.dd HH:mm";

    public final static String YM_WITHOUT_LINE="yyyyMM";

    public final static String YEAR_WITH_LINE = "yyyy";

    public final static String HM="HH:mm";

    public final static String HMS="HH:mm:ss";

    public final static String YMD_T_HMS_Z="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public final static String MD_WITH_WORD = "MM月dd日";

    public final static String YMD_WITH_LINE_AND_ZEROPOINT = "yyyy-MM-dd" + " 00:00:00";

    public final static String YMD_WITH_LINE_AND_END = "yyyy-MM-dd" + " 23:59:59";

    public final static String YMDHM_WITH_WORD = "yyyy年MM月dd日 HH:mm";

    /**
     * 1秒毫秒数
     */
    public final static Long ONE_MILLISECONDS = 1000L;

    /** 1分钟秒数 */
    public final static Long ONE_MINUTE_SECONDS = 60L;

    /** 1分钟毫秒数 */
    public final static Long ONE_MINUTE_MILLISECONDS = 60 * 1000L;

    /** 半小时秒数 */
    public final static Long HALF_AN_HOUR_SECONDS = 30 * 60L;

    /** 1小时秒数 */
    public final static Long ONE_HOUR_SECONDS = 60 * 60L;

    /** 1小时毫秒数 */
    public final static Long ONE_HOUR_MILLISECONDS = 60 * 60 * 1000L;

    /** 1天秒数 */
    public final static Long ONE_DAY_SECONDS = 24 * 60 * 60L;

    /** 1天毫秒数 */
    public final static Long ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000L;

    /** 1周秒数 */
    public final static Long ONE_WEEK_SECONDS = 7 * 24 * 60 * 60L;

    /** 1周毫秒数 */
    public final static Long ONE_WEEK_MILLISECONDS = 7 * 24 * 60 * 60 * 1000L;

    /** 半月秒数 */
    public final static Long HALF_MONTH_SECOND = 15 * 24 * 60 * 60L;

    /** 1月秒数 */
    public final static Long ONE_MONTH_SECOND = 30 * 24 * 60 * 60L;

    /** 1月毫秒数 */
    public final static Long ONE_MONTH_MILLISECOND = 30 * 24 * 60 * 60 * 1000L;


    /**
     * 计算两个日期的天数差，d2 - d1
     *
     * @param d1    日期1
     * @param d2    日期2
     * @return  相差天数
     */
    public static long diff(Date d1, Date d2) {

        if (d1 == null || d2 == null || d1 == d2) {
            return 0;
        }

        return (d2.getTime() - d1.getTime()) / ONE_DAY_MILLISECONDS;
    }

    /***
     * 计算差额 - 秒
     * @param d1    日期1
     * @param d2    日期2
     * @return  相差秒数
     */
    public static long diffSeconds(Date d1, Date d2) {
        Long long1 = d1.getTime();
        Long long2 = d2.getTime();
        return  (long1 - long2)/1000;
    }

    /***
     * 获取当前n天
     * @param n         天数
     * @param format    格式
     * @return  n天以前，每天的日期
     */
    public static List<String> getNDaysBefore(Integer n, String format) {
        Calendar c = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat(format);
        if (n <= 0) {
            n = 1;//至少是2天
        }
        List<String> days = new ArrayList<>();
        if (n == 1) {
            if (StringUtils.isNotBlank(format(c.getTime(), df))) {
                days.add(format(c.getTime(), df));
            }
            return days;
        }
        //遍历每一天
        for (int i = 0; i < n; i++) {
            if (StringUtils.isNotBlank(format(c.getTime(), df))) {
                days.add(format(c.getTime(), df));
            }
            c.add(Calendar.DATE, -1);
        }
        return days;
    }
    /***
     * 获取包含指定日期的前n天
     * @param date         指定日期
     * @param n         天数
     * @param format    格式
     * @return  n天以前，每天的日期
     */
    public static List<String> getNDaysBefore(Date date, Integer n, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        DateFormat df = new SimpleDateFormat(format);
        if (n <= 0) {
            n = 1;//至少是2天
        }
        List<String> days = new ArrayList<>();
        if (n == 1) {
            if (StringUtils.isNotBlank(format(c.getTime(), df))) {
                days.add(format(c.getTime(), df));
            }
            return days;
        }
        //遍历每一天
        for (int i = 0; i < n; i++) {
            if (StringUtils.isNotBlank(format(c.getTime(), df))) {
                days.add(format(c.getTime(), df));
            }
            c.add(Calendar.DATE, -1);
        }
        return days;
    }

    public static Date getNDaysBefore(Integer n){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -n);
        return c.getTime();
    }

    /**
     * 获取date n天 前/后 的日期
     * @param date  日期
     * @param n     天数
     * @return  Date
     */
    public static Date getDateNDaysBefore(Date date, Integer n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, -n);
        return c.getTime();
    }

    /**
     * 获取date n天 前/后 format格式的日期
     * @param date  日期
     * @param n     n天
     * @return  String
     */
    public static String getDateNDaysBeforeFormat(Date date, Integer n, String format) {
        Date dateNDaysBefore = getDateNDaysBefore(date, n);
        return new SimpleDateFormat(format).format(dateNDaysBefore);
    }

    /**
     * 获取指定日期 n 天前
     * @param n         天数
     * @param date      指定日期
     * @return  日期
     */
    public static Date getNDaysBeforeDate(Integer n, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -n);
        return c.getTime();
    }

    /**
     * 获取当前日子的数字类型
     * @param date  日期
     * @return  Long
     */
    public static Long getDayDateNum(Date date){
        String yyyyMMdd = format(date, "yyyyMMdd");
        return Long.valueOf(yyyyMMdd);
    }



    public static Date getNHoursBefore(Integer n){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -n);
        return c.getTime();
    }

    public static Date getNHoursBefore(Date date, Integer n){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, -n);
        return c.getTime();
    }

    public static Date getNMinutesBefore(Integer n){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -n);
        return c.getTime();
    }

    public static Date getNSecondBefore(Integer n){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, -n);
        return c.getTime();
    }

    public static Date getNYearsBefore(Integer n){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -n);
        return c.getTime();
    }

    public static Date getNMonthBefore(Integer n){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -n);
        return c.getTime();
    }

    public static String lastDay(String format) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return format(c.getTime(), new SimpleDateFormat(format));
    }

    public static Date lastDayDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return  c.getTime() ;
    }

    /**
     * 获得某天最大时间 yyyy-MM-dd 23:59:59
     *
     * @param date  日期
     * @return  Date
     */
    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 yyyy-MM-dd 00:00:00
     *
     * @param date  日期
     * @return  Date
     */
    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定时间那分钟开始时的时间戳（秒）
     * @param date  日期
     * @return  Long
     */
    public static Long getStartOfMinute(Date date) {
        if (null == date) {
            return null;
        }
        String dateStr = DateUtil.format(date, DateUtil.YMDHM) + ":00";
        Date result = DateUtil.format(dateStr, DateUtil.YMDHMS);
        return result == null ? null : result.getTime()/1000;
    }

    /**
     * 获取指定时间那分钟开始时的时间戳（豪秒）
     * @param date  日期
     * @return  Long
     */
    public static Long getStartMillOfMinute(Date date) {
        if (null == date) {
            return null;
        }
        String dateStr = DateUtil.format(date, DateUtil.YMDHM) + ":00";
        Date result = DateUtil.format(dateStr, DateUtil.YMDHMS);
        return result == null ? null : result.getTime();
    }

    /**
     * 获取指定时间那分钟结束时的时间戳（豪秒）
     * @param date  日期
     * @return  Long
     */
    public static Long getEndMillOfMinute(String date) {
        if (null == date) {
            return null;
        }
        Date result = DateUtil.format(date, DateUtil.YMDHMS);
        return result == null ? null : result.getTime();
    }


    public static String format(Date data, DateFormat sdf) {
        try {
            return sdf.format(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String format(Date data, String f) {
        try {
            DateFormat sdf = new SimpleDateFormat(f);
            return sdf.format(data);
        } catch (Exception e) {
            e.printStackTrace();
            return "--------------";
        }
    }

    public static String format(LocalDateTime date, String f) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(f);
            return date.format(formatter);
        } catch (Exception e) {
            log.error("error", e);
            return "";
        }
    }

    /**
     * string --> date
     *
     * @param time      时间字符串
     * @param format    格式
     * @return  Date
     */
    public static Date format(String time, String format) {

        if (StringUtils.isBlank(format)) {
            format = YMDHMS;
        }

        DateFormat df = new SimpleDateFormat(format);

        try {
            return df.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date formatToDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        String d = df.format(date);
        try {
            return df.parse(d);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取上周同一天的日期
     *
     * @param date 指定日期
     * @return     上周同一天日期
     */
    public static Date getLastWeekDay(Date date) {
        if (date == null) {
            throw new RuntimeException();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取上周同一天的日期
     *
     * @param date 指定日期
     * @return     上月同一天日期
     */
    public static Date getLastMonthDay(Date date) {
        if (date == null) {
            throw new RuntimeException();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取当天所在周是今年的第几周
     *
     * @param date 指定日期
     * @return  int
     */
    public static int getWeekInYear(Date date) {

        if (date == null) {
            return 0;
        }

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return theDay.get(WeekFields.ISO.weekOfWeekBasedYear());
    }

    /**
     * 获取指定日期到明年的当天的天数
     *
     * @param date 指定日期
     * @return  Long
     */
    public static Long getDaysToNextYear(Date date) {
        if (date == null) {
            return 0L;
        }
        Date formatDate = formatToDate(date, DateUtil.YMD_WITH_LINE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatDate);
        calendar.add(Calendar.YEAR, 1);
        Date nextYearDate = calendar.getTime();
        return (nextYearDate.getTime() - formatDate.getTime()) / (60 * 60 * 24 * 1000);
    }

    /**
     * 获取月份
     *
     * @param date  日期
     * @return  int
     */
    public static int getMonthInYear(Date date) {

        if (date == null) {
            return 0;
        }

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return theDay.getMonthValue();
    }

    /**
     * 获取年份
     *
     * @param date  日期
     * @return  int
     */
    public static int getYear(Date date) {

        if (date == null) {
            return 0;
        }

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return theDay.getYear();
    }

    /**
     * 获取指定日期所在周的每一天
     *
     * @param date   指定日期
     * @param format 格式化
     * @return       本周日期
     */
    public static List<String> getWeek(Date date, String format) {

        if (date == null) {
            return new ArrayList<>();
        }

        if (StringUtils.isBlank(format)) {
            format = YMD_WITHOUT_LINE;
        }

        List<String> thisWeek = new ArrayList<>(7);

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            thisWeek.add(localDate.with(dayOfWeek).format(formatter));
        }

        return thisWeek;
    }

    /**
     * 获取指定日期所在月的每一天
     *
     * @param date   指定日期
     * @param format 格式化
     * @return       本月日期
     */
    public static List<String> getMonth(Date date, String format) {

        if (date == null) {
            return new ArrayList<>();
        }

        if (StringUtils.isBlank(format)) {
            format = YMD_WITHOUT_LINE;
        }

        List<String> thisMonth = new ArrayList<>(31);

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate firstDayOfMonth = theDay.withDayOfMonth(1);
        LocalDate lastDayOfMonth = theDay.withDayOfMonth(theDay.lengthOfMonth());

        List<LocalDate> dates =
                Stream.iterate(firstDayOfMonth, localDate -> localDate.plusDays(1))
                .limit(ChronoUnit.DAYS.between(firstDayOfMonth, lastDayOfMonth) + 1)
                .collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        for (LocalDate localDate : dates) {
            thisMonth.add(localDate.format(formatter));
        }

        return thisMonth;
    }

    /**
     * 获取指定日期所在周的开始结束时间
     *
     * @param date 指定日期
     * @return     left=周一零点，right=周日 23:59:59
     */
    public static Pair<Date, Date> getStartEndDateOfWeek(Date date) {

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate monday = theDay.with(DayOfWeek.MONDAY);
        LocalDate sunday = theDay.with(DayOfWeek.SUNDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YMD_WITH_LINE);

        String m = monday.format(formatter) + " 00:00:00";
        String s = sunday.format(formatter) + " 23:59:59";

        return Pair.of(format(m, YMDHMS), format(s, YMDHMS));
    }

    /**
     * 获取指定日期所在周的周一日期
     *
     * @param date 指定日期
     * @return     周一零点
     */
    public static String getStartDateOfWeek(Date date) {

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate monday = theDay.with(DayOfWeek.MONDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YMD_WITH_LINE);

        return monday.format(formatter);
    }

    /**
     * 获取指定日期所在周的周一日期
     *
     * @param date   指定日期
     * @param format 格式化
     * @return 周一零点
     */
    public static String getStartDateOfWeek(Date date, String format) {

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate monday = theDay.with(DayOfWeek.MONDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return monday.format(formatter);
    }

    /**
     * 获取指定日期所在周的周末日期
     *
     * @param date 指定日期
     * @param format 格式化
     * @return     周末零点
     */
    public static String getEndDateOfWeek(Date date, String format) {

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate monday = theDay.with(DayOfWeek.SUNDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return monday.format(formatter);
    }


    /**
     * 获取指定日期所在月的开始和结束时间
     *
     * @param date 指定日期
     * @return     left=当月1号零点，right=当月最后一天 23:59:59
     */
    public static Pair<Date, Date> getStartEndDateOfMonth(Date date) {

        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate firstDayOfMonth = theDay.withDayOfMonth(1);
        LocalDate lastDayOfMonth = theDay.withDayOfMonth(theDay.lengthOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YMD_WITH_LINE);

        String first = firstDayOfMonth.format(formatter) + " 00:00:00";
        String last = lastDayOfMonth.format(formatter) + " 23:59:59";

        return Pair.of(format(first, YMDHMS), format(last, YMDHMS));
    }

    /**
     * 昨天日期 yyyy-MM-dd 00:00:00
     *
     * @return  Date
     */
    public static Date yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Integer getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取过去limit周的开始结束日期，不包含本周
     *
     * @param date   指定时间
     * @param format 格式
     * @param limit  多少周
     * @return       时间范围集合
     */
    public static List<Pair<String, String>> getRecentlyWeeks(Date date, String format, Integer limit) {
        List<Pair<String, String>> list = new ArrayList<>(limit);

        Date lastWeekDay = date;

        for (int i = 0; i < limit; i++) {
            lastWeekDay = getLastWeekDay(lastWeekDay);
            Pair<Date, Date> lastWeek = getStartEndDateOfWeek(lastWeekDay);
            list.add(Pair.of(format(lastWeek.getLeft(), format), format(lastWeek.getRight(), format)));
        }

        return list;
    }

    /**
     * 获取过去limit月的开始结束时间，不包含本月
     *
     * @param date      日期
     * @param format    日期格式
     * @param limit     月数
     * @return  List<Pair<String, String>>
     */
    public static List<Pair<String, String>> getRecentlyMonths(Date date, String format, Integer limit) {
        List<Pair<String, String>> list = new ArrayList<>(limit);
        Date lastMonthDay = date;
        for (int i = 0; i < limit; i++) {
            lastMonthDay = getLastMonthDay(lastMonthDay);
            Pair<Date, Date> lastMonth = getStartEndDateOfMonth(lastMonthDay);
            list.add(Pair.of(format(lastMonth.getLeft(), format), format(lastMonth.getRight(), format)));
        }
        return list;
    }

    /**
     * 判断是否是同一周
     *
     * @param date1 日期
     * @param date2 日期
     * @return      true=同一周
     */
    public static boolean isSameWeek(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if (getYear(date1) != getYear(date2)) {
            return false;
        }
        return getWeekInYear(date1) == getWeekInYear(date2);
    }

    /**
     * 是否是同一个月
     *
     * @param date1 日期
     * @param date2 日期
     * @return      true=同一个月
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if (getYear(date1) != getYear(date2)) {
            return false;
        }
        return getMonthInYear(date1) == getMonthInYear(date2);
    }

    //由出生日期获得年龄
    public static Integer getAge(String birthday) {
        if (StringUtils.isBlank(birthday)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(birthday);
            return getAge(date);
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    //由出生日期获得年龄
    public static Integer getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            }else{
                age--;
            }
        }
        return age;
    }

    /**
     * 获取几天是周几
     * @param date  日期
     * @return  星期几，周一～周六：1～6；周日：0
     */
    public static Integer getWeekDay(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }

    /**
     * 当前周,上一周周一的日期
     * @return String
     */
    public static String getLastWeekMondayDate() {
        LocalDate now = LocalDate.now();
        System.out.println("当前日期: " + now + " " + now.getDayOfWeek());
        // 求这个日期上一周的周一
        LocalDate todayOfLastWeek = now.minusDays(7);
        LocalDate monday = todayOfLastWeek.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
        System.out.println(monday);
        return String.valueOf(monday);
    }

    /**
     * 日期是否早于当前日期(早于返回true)
     * @param str 日期字符串
     * @return  boolean
     */
    public static boolean isPastDate(String str){
        boolean flag = false;
        Date nowDate = new Date();
        Date pastDate;
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //在日期字符串非空时执行
        if (str != null && !"".equals(str)) {
            try {
                //将字符串转为日期格式，如果此处字符串为非合法日期就会抛出异常。
                pastDate = sdf.parse(str);
                //调用Date里面的before方法来做判断
                flag = pastDate.before(nowDate);
            } catch (ParseException e) {
                log.error("isPastDate error ", e);
            }
        }
        return flag;
    }

    /**
     * 查询当前天的上一天日期
     * @param date 日期
     * @return Date
     */
    public static Date getBeforeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取昨天的 yyyyMMdd 日期字符串
     * @return String
     */
    public static String getYesterdayYyyyMMdd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 取前一天的
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.YMD_WITHOUT_LINE);
        return format.format(date);
    }

    /**
     * 获取当前的 yyyyMMdd 日期字符串
     * @return String
     */
    public static String getNowYyyyMMdd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.YMD_WITHOUT_LINE);
        return format.format(date);
    }

    /**
     * 获取当前的 yyyyMMdd 日期字符串
     * @return String
     */
    public static String getNowYMDH() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.YMDH);
        return format.format(date);
    }

    /**
     * 获取当前分钟数
     * @param date 日期
     * @return int
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 时间戳转化为秒
     * @param date 时间戳
     * @return long
     */
    public static long toSeconds(long date) {
        return date / 1000L;
    }

    /**
     * 时间戳转化为分钟
     * @param date 时间戳
     * @return long
     */
    public static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    /**
     * 时间戳转化小时
     * @param date 时间戳
     * @return long
     */
    public static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    /**
     * 时间戳转换成时间
     * @param timeStamp 时间戳
     * @return String
     */
    public static String timeStampToDate(Long timeStamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(timeStamp));
    }

    /**
     * 校验当前是否在规定时间内
     * @param date      指定时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return  Boolean
     */
    public static Boolean checkoutTimeBetween(Date date, String startTime, String endTime) {
        String format = "HH:mm:ss";
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        String checkTime = sf.format(date);
        try {
            Date checkDate = new SimpleDateFormat(format).parse(checkTime);
            Date startDate = new SimpleDateFormat(format).parse(startTime);
            Date endDate = new SimpleDateFormat(format).parse(endTime);
            if (checkDate.getTime() >= startDate.getTime() && checkDate.getTime() <= endDate.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            log.error("checkoutTimeBetween error ", e);
        }
        return false;
    }

    /**
     * pattern类型转换为UTC类型
     * @param pattern 格式
     * @param time 时间
     * @return
     */
    public static String parseToUTC(String pattern, String time){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat(pattern).parse(time));
        } catch (Exception e) {
            throw new RuntimeException();
        }
        c.add(Calendar.HOUR, -8);
        return new SimpleDateFormat(YMD_T_HMS_Z).format(c.getTime());
    }

    public static void main(String[] args) {
        System.out.println(parseToUTC(DateUtil.YMDHM, "2021-07-02 14:46"));
    }

    /**
     * 获取指定日期的上周周天
     * @param date
     * @return
     */
    public static String getPreSunday(Date date, String format){
        LocalDate theDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate monday = theDay.with(DayOfWeek.MONDAY).plusDays(-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return monday.format(formatter);
    }

    /**
     * 将 ${data} 转换成 yyyyMMdd 格式数字
     *
     * @param data 日期
     * @return
     */
    public static Long formatLong(Date data) {
        try {
            DateFormat sdf = new SimpleDateFormat(DateUtil.YMD_WITHOUT_LINE);
            String format = sdf.format(data);
            return Long.parseLong(format);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long formatLong(Date data, String f) {
        try {
            DateFormat sdf = new SimpleDateFormat(f);
            String format = sdf.format(data);
            return Long.parseLong(format);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
