package com.yzycoc.custom;

import java.sql.Date;
import java.text.*;
import java.time.*;
import java.util.Calendar;

/**
 * @program: cscocutil
 * @description: 时间工具类
 * @author: yzy
 * @create: 2020-08-10 22:14
 * @Version 1.0
 **/
public class TimeUtiles {

    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Date getNowTime() {
        LocalDate date = LocalDate.now();
        Date date1 = Date.valueOf(date);
        return date1;
    }

    /**
     *
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new java.util.Date();
        String dateString = formatter.format(time);
        return dateString;
    }

    public static String getStringDate(String string) {
        java.util.Date time = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(string);
        String dateString = formatter.format(time);
        return dateString;
    }
    /**
     *
     * 获取时间
     *
     * @param time 填入格式：HH:mm:ss
     * @return返回date HH:mm:ss
     */
    public static java.util.Date getTime(String time) {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        java.util.Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     *
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd
     */
    public static String getStringTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time = new java.util.Date();
        String dateString = format.format(time);
        return dateString;
    }

    /**
     *
     * 获取现在时间
     *
     * @return返回字符串格式 HH:mm:ss
     */
    public static String getTime() {
        java.util.Date time = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(time);
        return dateString;
    }
    /**
     *
     * 获取现在时间
     *
     * @return返回字符串格式 HH:mm:ss
     */
    public static String getTimeS() {
        java.util.Date time = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH时mm分");
        String dateString = formatter.format(time);
        return dateString;
    }
    /**
     * 获取当前系统时间一年后的时间
     *
     * @return
     */
    public static Date getDeadline() {
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.plusYears(1);
        Date date2 = Date.valueOf(date1);
        return date2;
    }

    /***
     * 判断时间是否为之后时间 与现在时间比较
     *
     * @param time 被比较时间
     * @return
     */

    public static boolean AfterTime(Date time) {
        LocalDate date = LocalDate.now();
        Date date1 = Date.valueOf(date);
        if (time.after(date1)) {
            return true;
        } else {

            return false;
        }
    }

    /***
     * 判断时间是否为之前时间 与现在时间比较
     *
     * @param time 被比较时间
     * @return
     */

    public static boolean BeforeTime(Date time) {
        LocalDate date = LocalDate.now();
        Date date1 = Date.valueOf(date);
        if (time.before(date1)) {
            return true;
        } else {

            return false;
        }
    }


    /****
     * 当前时间于string 之间相差多少秒
     * string < 当前时间 返回正数
     * @param string	传入需要对比的时间
     * @return
     */
    public static long datadifference(String string) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new java.util.Date();
        String format = formatter.format(time);//当前时间
        java.util.Date now;
        try {
            now = formatter.parse(string);
            java.util.Date date=formatter.parse(format);
            long l=(date.getTime()-now.getTime())/1000;
            return l;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }


    }

    /****
     * string 时间 和 当前时间差多少
     * string > 当前时间
     * @param string	传入需要对比的时间
     * @return
     */
    public static long timedifference(String string) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new java.util.Date();
        String format = formatter.format(time);//当前时间
        java.util.Date now;
        try {
            now = formatter.parse(string);
            java.util.Date date=formatter.parse(format);
            long l=(now.getTime()-date.getTime())/1000;
            return l;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
    /****
     * 获取当前时间后一天的时间
     * @return
     */
    public static String endDd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return format.format(c.getTime());
    }

    /****
     * 获取当前时间data分钟的时间
     * @return
     */
    public static String endMin(int data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, data);
        return formatter.format(c.getTime());
    }
    /****
     * 获取当前时间后一天的时间
     * @return
     */
    public static String endDdHhmmss() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return formatter.format(c.getTime());
    }
    /****
     * 获取当前时间后X月的时间
     * @return
     */
    public static String endMm(int i) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, i);
        return formatter.format(c.getTime());
    }

    /***
     * 改变时间
     * 时间加减  -- 分钟
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @param endMin 减少:负数  增加:正数
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String DateAlterMin(String dateTime,Integer endMin){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parse = formatter.parse(dateTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            cal.add(Calendar.MINUTE, endMin);
            return formatter.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /***
     * 改变时间
     * 时间加减  -- 月
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @param endMin 减少:负数  增加:正数
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String DateAlterMonth(String dateTime,Integer endMin){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parse = formatter.parse(dateTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            cal.add(Calendar.MONTH, endMin);
            return formatter.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    /***
     * 获取年月日最简组合
     * @return
     */
    public static String getAbcdefg() {
        java.util.Date time = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        int parseInt = Integer.parseInt(formatter.format(time));
        parseInt = parseInt - 200115;
        return DlNumber(parseInt);
    }

    public static String DlNumber(int num){
        String tcMsg = "";
        char sl = 0;
        if (-1 < num && num < 10) {
            tcMsg = "" + num;
        } else if (9 < num && num < 36) {
            sl = (char) (num - 42 + (int) 'a');
            tcMsg = "" + sl;
        }else if(num >= 36){
            int yushu = num/36 - 1;
            int ys = num%36;
            tcMsg = DlNumber(yushu)+DlNumber(ys);
        }
        return tcMsg;
    }

    public static CharSequence LongTime(Long lastTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(lastTime);
            String format = formatter.format(date);
            return format;
        }catch (Exception e){
            return "";
        }


    }
}
