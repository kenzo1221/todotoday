package com.example.todotoday.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TodayStringGetter {
    public static String execute() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        return sdf.format(cal.getTime());
    }
}
