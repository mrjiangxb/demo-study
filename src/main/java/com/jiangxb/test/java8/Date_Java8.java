package com.jiangxb.test.java8;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jiangxiangbo
 * @date 2020/7/21
 * @Description: java.time 包下的新api 线程安全。
 * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
 */
public class Date_Java8 {
    public static void main(String[] args) {
        // jdk 1.8 以前
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = simpleDateFormat.format(date);
        System.out.println("java.util.Date:  " + dateStr);
        // jdk 1.8 及以后
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String localDateStr = dateTimeFormatter.format(localDateTime);
        System.out.println("java.time.LocalDateTime:  " + localDateStr);

        // 当前月份
        int month = localDateTime.getMonthValue();
        // 当前月的第几天
        int dayOfMonth = localDateTime.getDayOfMonth();
        // 当前是几时
        int hour = localDateTime.getHour();
        System.out.println("month:"+month+"   "+"dayOfMonth:"+dayOfMonth+"   "+"hour:"+hour);

        // 获取某个月份
        System.out.println("Month.JULY.getValue():  " + Month.JULY.getValue());
        // 获取现在是当前月的第几天
        System.out.println("MonthDay.now():  " + MonthDay.now());

    }
}
