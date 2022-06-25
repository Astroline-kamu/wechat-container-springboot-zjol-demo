/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/23/22, 7:34 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.common.utils;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class DateUtils {
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date == null) date = new Date();
        return new SimpleDateFormat(pattern).format(date);
    }
    public static Date format(String date) throws ParseException {
        if (date.isBlank()) return null;
        return format(date, DATE_PATTERN);
    }

    public static Date format(String date, String pattern) throws ParseException {
        if (date.isBlank()) return null;
        return new SimpleDateFormat(pattern).parse(date);
    }

    public static Date[] getWeekRange(int week){
        LocalDate date = new LocalDate(new DateTime().plusWeeks(week))
                .dayOfWeek().withMinimumValue();
        Date begin = date.toDate();
        Date end = date.plusWeeks(1).plusDays(-1).toDate();
        return new Date[]{begin, end};
    }

    public static Date[] getMonthRange(int month){
        LocalDate date = new LocalDate(new DateTime().plusWeeks(month))
                .dayOfMonth().withMinimumValue();
        Date begin = date.toDate();
        Date end = date.plusMonths(1).plusDays(-1).toDate();
        return new Date[]{begin, end};
    }

    public static Date[] getYearRange(int year){
        LocalDate date = new LocalDate(new DateTime().plusWeeks(year))
                .dayOfYear().withMinimumValue();
        Date begin = date.toDate();
        Date end = date.plusYears(1).plusDays(-1).toDate();
        return new Date[]{begin, end};
    }

    public static Date addDays(Date date, int days) {
        return null;
    }
    public static Date addWeeks(Date date, int days) {
        return null;
    }
    public static Date addMonths(Date date, int days) {
        return null;
    }
    public static Date addYears(Date date, int days) {
        return null;
    }


}
