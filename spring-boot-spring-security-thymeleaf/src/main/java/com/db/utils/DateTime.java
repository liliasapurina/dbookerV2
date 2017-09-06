package com.db.utils;

import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    @SneakyThrows
    public static Date convertStringToDate(String dateInString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateInString);
        System.out.println(date);
        return date;
    }

    public static String convertDateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date plusDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
