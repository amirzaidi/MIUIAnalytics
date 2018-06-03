package com.miui.analytics.internal.util;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ac {
    public static final long a = 604800000;
    public static final long b = 86400000;
    public static final long c = 43200000;
    public static final int d = 3600000;
    public static final int e = 60000;
    public static final int f = 1000;
    public static final String g = "yyyyMMdd";
    private static final String h = "TimeUtils";

    public static boolean a(long j) {
        long d = d();
        return d <= j && j < b + d;
    }

    public static boolean b(long j) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        long j2 = b + timeInMillis;
        if (timeInMillis > j || j >= j2) {
            return false;
        }
        return true;
    }

    public static Date a(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(5, i);
        return instance.getTime();
    }

    public static String a(Date date) {
        return new SimpleDateFormat(g).format(date);
    }

    public static Date a(String str) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(g).parse(str);
        } catch (Throwable e) {
            Log.e(o.a(h), "integerStringToDate exception:", e);
        }
        return date;
    }

    public static long a() {
        long currentTimeMillis = System.currentTimeMillis();
        currentTimeMillis = (currentTimeMillis - (currentTimeMillis % b)) - b;
        if (currentTimeMillis < 0) {
            return 0;
        }
        return currentTimeMillis;
    }

    public static long b() {
        return (a() + b) - 1;
    }

    public static String c() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static long d() {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        o.a(h, "TodayUTC0BeginTime " + timeInMillis);
        return timeInMillis;
    }

    public static long e() {
        long d = (d() + b) - 1;
        o.a(h, "TodayUTC0EndTime " + d);
        return d;
    }

    public static boolean a(long j, long j2) {
        return Math.abs(System.currentTimeMillis() - j) >= j2;
    }

    public static int c(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(11);
    }
}
