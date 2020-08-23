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
        java.util.Date time = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        java.util.Date time = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(time);
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

    /***
     *
     * @param time 时间String
     * @return 星期 String
     */
    public static String WeekDay(String time) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = format.parse(time);
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate localDate = instant.atZone(zoneId).toLocalDate();
            DayOfWeek day = localDate.getDayOfWeek();
            String WeekDay = "";

            switch (day) {

                case MONDAY:

                    WeekDay = "星期一";

                    break;

                case FRIDAY:

                    WeekDay = "星期五";

                    break;

                case SATURDAY:

                    WeekDay = "星期六";

                    break;

                case SUNDAY:

                    WeekDay = "星期日";

                    break;

                case THURSDAY:

                    WeekDay = "星期四";

                    break;

                case TUESDAY:

                    WeekDay = "星期二";

                    break;

                case WEDNESDAY:

                    WeekDay = "星期三";

                    break;

            }

            return WeekDay;
        } catch (ParseException e) {
            return "报错了";
        }
    }


    /****
     * 判读时间段为什么时候
     * 返回 上午 下午 晚上
     * @param time 起始时间
     * @param endtime	结束时间
     */
    public static String getDay(String time, String endtime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            java.util.Date datetime = formatter.parse(time);
            java.util.Date dateendtime = formatter.parse(endtime);
            if (datetime.after(getTime("05:59:59")) && datetime.before(getTime("12:00:01")) && dateendtime.after(getTime("05:59:59")) && dateendtime.before(getTime("12:00:01"))) {
                return "上午";
            }else if(datetime.after(getTime("11:59:59")) && datetime.before(getTime("18:00:01")) && dateendtime.after(getTime("11:59:59")) && dateendtime.before(getTime("18:00:01"))) {
                return "下午";
            }else if((datetime.after(getTime("19:59:59")) && datetime.before(getTime("23:59:59")) && dateendtime.after(getTime("19:59:59")) && dateendtime.before(getTime("23:59:59")))) {
                return "晚上";
            }else {
                return "全天";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "全天";
    }

    /****
     * 当前时间于string 之间相差多少秒
     * string < 当前时间 返回正数
     * @param string	传入需要对比的时间
     * @return
     */
    public static long datadifference(String string) {
        java.util.Date time = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(time);//当前时间
        java.util.Date now;
        try {
            now = df.parse(string);
            java.util.Date date=df.parse(format);
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
        java.util.Date time = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(time);//当前时间
        java.util.Date now;
        try {
            now = df.parse(string);
            java.util.Date date=df.parse(format);
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sf.format(c.getTime());
    }

    /****
     * 获取当前时间data分钟的时间
     * @return
     */
    public static String endMin(int data) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, data);
        return sf.format(c.getTime());
    }
    /****
     * 获取当前时间后一天的时间
     * @return
     */
    public static String endDdHhmmss() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sf.format(c.getTime());
    }
    /****
     * 获取当前时间后X月的时间
     * @return
     */
    public static String endMm(int i) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, i);
        return sf.format(c.getTime());
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
        return AZ(parseInt);
    }

    public static String AZ(int num){
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
            tcMsg = AZ(yushu)+AZ(ys);
        }
        return tcMsg;
    }
}
