package com.nuanyou.rekognition.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class.getSimpleName());

    public static final DateFormat Standard = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final DateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

    public static final DateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

    public static final DateFormat HHmmss = new SimpleDateFormat("HHmmss");

    public static final DateFormat HH_mm_ss = new SimpleDateFormat("HH:mm:ss");

    public static final DateFormat MMdd = new SimpleDateFormat("MMdd");

    public static final DateFormat YYYY = new SimpleDateFormat("YYYY");

    public static final DateFormat MM = new SimpleDateFormat("MM");

    public static final DateFormat dd = new SimpleDateFormat("dd");

    public static Date parseDate(String str) {
        return parse(str, yyyy_MM_dd);
    }

    public static Date parseTime(String str) {
        return parse(str, HH_mm_ss);
    }

    public static Date parse(String str) {
        return parse(str, Standard);
    }

    public static Date parse(String str, DateFormat format) {
        try {
            return format.parse(str);
        } catch (ParseException e) {
            log.warn("日期格式错误" + str, e);
            return null;
        }
    }

    public static String formatDate(Date date) {
        return format(date, yyyy_MM_dd);
    }

    public static String formatTime(Date date) {
        return format(date, HH_mm_ss);
    }

    public static String format(Date date) {
        return format(date, Standard);
    }

    public static String format(Date date, DateFormat format) {
        if (date == null)
            return null;
        return format.format(date);
    }

    /** 计算年龄 */
    public static Integer getAge(Date birthday) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            return null;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    public static Date newDate() {
        return new Date();
    }
}