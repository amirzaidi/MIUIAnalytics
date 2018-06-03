package com.miui.analytics.internal.util;

import android.util.Log;

public class aa {
    private static final String a = "SystemProperties";

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Throwable e) {
            Log.e(o.a(a), "get e", e);
            return str2;
        }
    }

    public static String a(String str) {
        return a(str, "");
    }

    public static long a(String str, Long l) {
        try {
            return ((Long) Class.forName("android.os.SystemProperties").getMethod("getLong", new Class[]{String.class, Long.TYPE}).invoke(null, new Object[]{str, l})).longValue();
        } catch (Throwable e) {
            Log.e(o.a(a), "getLong e", e);
            return l.longValue();
        }
    }
}
