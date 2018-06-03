package com.miui.analytics.internal.util;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import miui.external.f;
import miui.os.Build;
import miui.util.FeatureParser;

public class n {
    public static final String a = "miui.intent.action.ad.AAID_RESET";
    public static final String b = "old_aaid";
    public static final String c = "new_aaid";
    public static final String d = "from";
    public static final int e = 0;
    public static final int f = 1;
    private static final String g = "MIUI";
    private static final String h = "ad_aaid";
    private static volatile String[] i = new String[2];
    private static final Set<String> j = new HashSet(Arrays.asList(new String[]{"AT", "BE", "BG", "CY", "CZ", "DE", "DK", "EE", "ES", "FI", "FR", "GB", "GR", "HR", "HU", "IE", "IT", "LT", "LU", "LV", "MT", "NL", "PL", "PT", "RO", "SE", "SI", "SK"}));

    public static boolean a(Context context) {
        if (!f.a()) {
            return true;
        }
        boolean z;
        boolean z2;
        try {
            z = Build.IS_DEVELOPMENT_VERSION;
        } catch (Throwable th) {
            o.b(g, "isUploadLogEnabled: " + th);
            z = false;
        }
        try {
            int i;
            Class cls = Class.forName("miui.provider.ExtraSettings$Secure");
            Method method = cls.getMethod("getInt", new Class[]{ContentResolver.class, String.class, Integer.TYPE});
            Object[] objArr = new Object[3];
            objArr[0] = context.getContentResolver();
            objArr[1] = cls.getField("UPLOAD_LOG").get(null);
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            objArr[2] = Integer.valueOf(i);
            if (((Integer) method.invoke(null, objArr)).intValue() != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
        } catch (Throwable th2) {
            o.b(g, "isUploadLogEnabled: " + th2);
            z2 = z;
        }
        return z2;
    }

    public static boolean a() {
        try {
            if (f.a()) {
                return Build.IS_INTERNATIONAL_BUILD;
            }
            String toUpperCase = j().toUpperCase();
            o.a(g, "not miui and country = " + toUpperCase);
            if (!"CN".equals(toUpperCase)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            o.b(g, "isInternationalBuild: " + th);
        }
    }

    public static boolean b() {
        try {
            return Build.IS_ALPHA_BUILD;
        } catch (Throwable th) {
            o.b(g, "isAlphaBuild: " + th);
            return false;
        }
    }

    public static boolean c() {
        try {
            return Build.IS_DEVELOPMENT_VERSION;
        } catch (Throwable th) {
            o.b(g, "isDevBuild: " + th);
            return false;
        }
    }

    public static boolean d() {
        try {
            return Build.IS_STABLE_VERSION;
        } catch (Throwable th) {
            o.b(g, "isStableBuild: " + th);
            return false;
        }
    }

    public static boolean e() {
        try {
            return Build.IS_CTA_BUILD;
        } catch (Throwable th) {
            o.b(g, "isCtaBuild: " + th);
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        if (e()) {
            o.c(str, "should not access network or location, cta");
            return true;
        } else if (c.h(context)) {
            return false;
        } else {
            o.c(str, "should not access network or location, not provisioned");
            return true;
        }
    }

    public static boolean f() {
        try {
            return Build.IS_TABLET;
        } catch (Throwable th) {
            o.b(g, "isTablet: " + th);
            return false;
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            z = FeatureParser.getBoolean(str, z);
        } catch (Throwable th) {
            o.b(g, "getBooleanFeature: " + th);
        }
        return z;
    }

    private static String f(Context context) {
        try {
            if (!f.a()) {
                return "";
            }
            Object invoke = Class.forName("android.provider.MiuiSettings$Ad").getDeclaredMethod("getAaid", new Class[]{ContentResolver.class}).invoke(null, new Object[]{context.getContentResolver()});
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return "";
        } catch (Exception e) {
        }
    }

    public static synchronized void a(String str, int i) {
        synchronized (n.class) {
            try {
                o.a(g, "cacheAaid:" + str + " from:" + i);
                if (i == null || i.length < 2) {
                    i = new String[2];
                }
                i[0] = str;
                i[1] = String.valueOf(i);
            } catch (Throwable e) {
                Log.e(o.a(g), "cacheAaid e", e);
            }
        }
    }

    private static String[] g(Context context) {
        try {
            if (i != null && i.length >= 2 && !TextUtils.isEmpty(i[0])) {
                return i;
            }
            String f = f(context);
            if (TextUtils.isEmpty(f)) {
                if (VERSION.SDK_INT < 17) {
                    f = h(context);
                } else {
                    f = i(context);
                }
                if (TextUtils.isEmpty(f)) {
                    f = d(context);
                    if (TextUtils.isEmpty(f)) {
                        f = j(context);
                        if (!TextUtils.isEmpty(f)) {
                            a(f, 1);
                            return i;
                        }
                        return new String[2];
                    }
                    a(f, 0);
                    return i;
                }
                a(f, 1);
                return i;
            }
            a(f, 0);
            return i;
        } catch (Throwable e) {
            Log.e(o.a(g), "getAaidV2 e", e);
        }
    }

    public static String b(Context context) {
        try {
            if (!f.a()) {
                return "";
            }
            String[] g = g(context);
            if (!(g == null || g.length < 2 || TextUtils.isEmpty(g[0]))) {
                return g[0];
            }
            return "";
        } catch (Throwable e) {
            Log.e(o.a(g), "getAaidV2 e", e);
        }
    }

    public static String c(Context context) {
        String[] g = g(context);
        if (g == null || g.length < 2 || TextUtils.isEmpty(g[1])) {
            return "";
        }
        return g[1];
    }

    private static String h(Context context) {
        return System.getString(context.getContentResolver(), h);
    }

    @TargetApi(17)
    private static String i(Context context) {
        return Global.getString(context.getContentResolver(), h);
    }

    public static String d(Context context) {
        try {
            Class.forName("android.provider.MiuiSettings$Ad").getDeclaredMethod("resetAaid", new Class[]{Context.class}).invoke(null, new Object[]{context});
            return f(context);
        } catch (Throwable e) {
            o.b(g, "reset resetAaidV1 exception: ", e);
            return "";
        }
    }

    private static String j(Context context) {
        try {
            String h;
            String str = "";
            str = UUID.randomUUID().toString();
            if (VERSION.SDK_INT < 17) {
                h = h(context);
                if (c.c(context, "android.permission.WRITE_SETTINGS")) {
                    System.putString(context.getContentResolver(), h, str);
                } else {
                    o.a(g, "没有WRITE_SETTINGS权限");
                    return "";
                }
            }
            h = i(context);
            if (c.c(context, "android.permission.WRITE_SECURE_SETTINGS")) {
                Global.putString(context.getContentResolver(), h, str);
            } else {
                o.a(g, "没有WRITE_SECURE_SETTINGS权限");
                return "";
            }
            Intent intent = new Intent(a);
            if (TextUtils.isEmpty(h)) {
                h = "";
            }
            intent.putExtra(b, h);
            intent.putExtra(c, str);
            intent.putExtra(d, "com.miui.analytics");
            context.sendBroadcast(intent);
            return str;
        } catch (Throwable e) {
            Log.e(o.a(g), "resetAaidV2 e", e);
            return "";
        }
    }

    public static String g() {
        return aa.a("ro.carrier.name", "");
    }

    public static boolean e(Context context) {
        try {
            if (context.getApplicationContext().getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0).versionCode >= 108) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            Log.w(o.a(g), "isSupportXMSFUpload failed:", e);
            return false;
        }
    }

    public static String h() {
        return aa.a("ro.miui.xms.version", "");
    }

    public static boolean i() {
        Object a = aa.a("ro.miui.region", "unknown");
        if (TextUtils.isEmpty(a) || TextUtils.equals(a, "unknown")) {
            return true;
        }
        return j.contains(a.toUpperCase());
    }

    private static String j() {
        Throwable e;
        CharSequence a = aa.a("ro.miui.region", "");
        String str;
        try {
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
            if (VERSION.SDK_INT >= 24) {
                Object invoke = Class.forName("android.os.LocaleList").getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
                Object invoke2 = invoke.getClass().getMethod("size", new Class[0]).invoke(invoke, new Object[0]);
                if ((invoke2 instanceof Integer) && ((Integer) invoke2).intValue() > 0) {
                    invoke2 = invoke.getClass().getMethod("get", new Class[]{Integer.TYPE}).invoke(invoke, new Object[]{Integer.valueOf(0)});
                    invoke2 = invoke2.getClass().getMethod("getCountry", new Class[0]).invoke(invoke2, new Object[0]);
                    if (invoke2 instanceof String) {
                        str = (String) invoke2;
                        if (TextUtils.isEmpty(str)) {
                            return str;
                        }
                        return Locale.getDefault().getCountry();
                    }
                }
            }
            CharSequence str2 = a;
            try {
                if (TextUtils.isEmpty(str2)) {
                    return str2;
                }
                return Locale.getDefault().getCountry();
            } catch (Exception e2) {
                e = e2;
                o.b(g, "getRegion e: ", e);
                return str2;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            str2 = a;
            e = th;
            o.b(g, "getRegion e: ", e);
            return str2;
        }
    }
}
