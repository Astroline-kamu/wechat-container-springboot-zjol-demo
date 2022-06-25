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
 * 日期&时间 处理工具
 * Time和Date是分开的两个东西，但是分成两个文件去做工具的话会显得太麻烦了些，于是就合并成了DateTimeUtils
 * 我有些好奇我为什么要造这个轮子，joda有自己的DateTimeUtils，Apache，Java原生也都有很多相关的工具
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class DateTimeUtils {
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转换字符串 默认格式：yyyy-MM-dd
     *
     * @param date 日期
     * @return yyyy-MM-dd格式的字符串
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期转字符串 自行指定格式
     *
     * @param date 日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) date = new Date();
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 日期格式字符串转日期 默认格式：yyyy-MM-dd
     *
     * @param date 日期格式字符串
     * @return Date
     * @throws ParseException 解析异常
     */
    public static Date format(String date) throws ParseException {
        if (date.isBlank()) return null;
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式字符串转日期 自定义格式
     *
     * @param date 日期格式字符串
     * @param pattern 自定义格式
     * @return Date
     * @throws ParseException 解析异常
     */
    public static Date format(String date, String pattern) throws ParseException {
        if (date.isBlank()) return null;
        return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * 获取一周的区间
     * 比如今天周三，输入参数0（本周），返回date[0]（本周一），date[1]（本周日）
     *
     * @param week 周数 -1上周 0本周 1下周，以此类推
     * @return new Date[]{开始日期, 结束日期}
     */
    public static Date[] getWeekRange(int week){
        LocalDate date = new LocalDate(new DateTime().plusWeeks(week))
                .dayOfWeek().withMinimumValue();
        Date begin = date.toDate();
        Date end = date.plusWeeks(1).plusDays(-1).toDate();
        return new Date[]{begin, end};
    }

    /**
     * 获取一个月的区间
     * 比如今天2022-06-25，输入参数1（次月），返回date[0]（2022-07-01），date[1]（2022-07-31）
     *
     * @param month 间隔月 -1上月 0本月 1次月，以此类推
     * @return new Date[]{开始日期, 结束日期}
     */
    public static Date[] getMonthRange(int month){
        LocalDate date = new LocalDate(new DateTime().plusWeeks(month))
                .dayOfMonth().withMinimumValue();
        Date begin = date.toDate();
        Date end = date.plusMonths(1).plusDays(-1).toDate();
        return new Date[]{begin, end};
    }

    /**
     * 获取一年的区间
     * 比如今天2022-06-25，输入参数-1（去年），返回date[0]（2021-01-01），date[1]（2022-12-30）
     *
     * @param year 间隔年 -1去年 0本年 1明年，以此类推
     * @return new Date[]{开始日期, 结束日期}
     */
    public static Date[] getYearRange(int year){
        LocalDate date = new LocalDate(new DateTime().plusWeeks(year))
                .dayOfYear().withMinimumValue();
        Date begin = date.toDate();
        Date end = date.plusYears(1).plusDays(-1).toDate();
        return new Date[]{begin, end};
    }

    /**
     * 秒数加减
     *
     * @param date 目标日期
     * @param seconds 加减秒数
     * @return 计算后的日期
     */
    public static Date addSeconds(Date date, int seconds) {
        return new DateTime(date).plusSeconds(seconds).toDate();
    }

    /**
     * 分钟加减
     *
     * @param date 目标日期
     * @param minutes 加减分钟
     * @return 计算后的日期
     */
    public static Date addMinutes(Date date, int minutes) {
        return new DateTime(date).plusMinutes(minutes).toDate();
    }

    /**
     * 小时加减
     *
     * @param date 目标日期
     * @param hours 加减小时
     * @return 计算后的日期
     */
    public static Date addHours(Date date, int hours) {
        return new DateTime(date).plusHours(hours).toDate();
    }

    /**
     * 日期加减
     *
     * @param date 目标日期
     * @param days 加减日期
     * @return 计算后的日期
     */
    public static Date addDays(Date date, int days) {
        return new DateTime(date).plusDays(days).toDate();
    }

    /**
     * 周数加减
     *
     * @param date 目标日期
     * @param weeks 加减周数
     * @return 计算后的日期
     */
    public static Date addWeeks(Date date, int weeks) {
        return new DateTime(date).plusWeeks(weeks).toDate();
    }

    /**
     * 月份加减
     *
     * @param date 目标日期
     * @param months 加减月份
     * @return 计算后的日期
     */
    public static Date addMonths(Date date, int months) {
        return new DateTime(date).plusMonths(months).toDate();
    }

    /**
     * 年份加减
     *
     * @param date 目标日期
     * @param years 加减年份
     * @return 计算后的日期
     */
    public static Date addYears(Date date, int years) {
        return new DateTime(date).plusYears(years).toDate();
    }


}
