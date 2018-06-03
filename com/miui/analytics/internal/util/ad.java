package com.miui.analytics.internal.util;

import android.text.TextUtils;

public class ad {
    private static final String a = "Trace";
    private static String b;
    private static long c = 0;

    public static void a(String str) {
        b = str;
        c = System.currentTimeMillis();
        o.a(b(), "session start ");
    }

    public static void a() {
        o.a(b(), "session end. cost time is " + (System.currentTimeMillis() - c));
    }

    private static String b() {
        if (TextUtils.isEmpty(b)) {
            return a;
        }
        return b;
    }
}
