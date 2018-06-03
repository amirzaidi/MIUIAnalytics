package com.miui.analytics.internal.util;

import android.util.Log;

public class o {
    public static boolean a = false;
    private static final int b = 3000;
    private static final String c = "Analytics-Core-";
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;

    public static void a(String str, String str2) {
        if (a) {
            a(a(str), str2, 3);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            Log.d(a(str), str2, th);
        }
    }

    public static void b(String str, String str2) {
        if (a) {
            a(a(str), str2, 0);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a) {
            Log.e(a(str), str2, th);
        }
    }

    public static void c(String str, String str2) {
        if (a) {
            a(a(str), str2, 1);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (a) {
            Log.w(a(str), str2, th);
        }
    }

    public static void d(String str, String str2) {
        if (a) {
            a(a(str), str2, 2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a) {
            Log.i(a(str), str2, th);
        }
    }

    private static void a(String str, String str2, int i) {
        for (int i2 = 0; i2 <= str2.length() / b; i2++) {
            int i3 = i2 * b;
            int min = Math.min(str2.length(), (i2 + 1) * b);
            if (i3 < min) {
                String substring = str2.substring(i3, min);
                switch (i) {
                    case 0:
                        Log.e(str, substring);
                        break;
                    case 1:
                        Log.w(str, substring);
                        break;
                    case 2:
                        Log.i(str, substring);
                        break;
                    case 3:
                        Log.d(str, substring);
                        break;
                    case 4:
                        Log.v(str, substring);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static String a(String str) {
        return c + str;
    }
}
