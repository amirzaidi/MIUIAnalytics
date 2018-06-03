package com.miui.analytics.internal.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings.Global;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class c {
    private static final String a = "AndroidUtils";

    public static Context a(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return context;
        }
        return context.getApplicationContext();
    }

    public static String b(Context context) {
        return a(context, context.getPackageName());
    }

    public static String a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    public static int c(Context context) {
        return b(context, context.getPackageName());
    }

    public static int b(Context context, String str) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (Exception e) {
            return i;
        }
    }

    public static boolean c(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 1) != 0;
    }

    public static boolean d(Context context, String str) {
        boolean z = false;
        try {
            z = a(a(context, str, 0).applicationInfo);
        } catch (Exception e) {
        }
        return z;
    }

    public static PackageInfo a(Context context, String str, int i) {
        try {
            return context.getPackageManager().getPackageInfo(str, i);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean e(Context context, String str) {
        PackageInfo a = a(context, str, 0);
        if (a == null || a.applicationInfo == null) {
            return false;
        }
        return true;
    }

    public static Signature[] a(Context context, File file) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(file.getPath(), 64).signatures;
        } catch (Exception e) {
            return null;
        }
    }

    public static Signature[] f(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean d(Context context) {
        return TextUtils.equals(context.getPackageName(), f(context));
    }

    public static boolean e(Context context) {
        return TextUtils.equals(f(context), "com.miui.analytics");
    }

    public static boolean a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String f(Context context) {
        String str;
        int myPid = Process.myPid();
        String str2 = "";
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    str = runningAppProcessInfo.processName;
                    break;
                }
            }
        } catch (Throwable e) {
            o.b(a, "get current process name exception ", e);
        }
        str = str2;
        o.d(a, "current process name " + str + ", pid=" + myPid);
        return str;
    }

    public static List<String> a(Context context, int i) {
        List<String> arrayList = new ArrayList();
        try {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            if (packagesForUid != null) {
                arrayList = Arrays.asList(packagesForUid);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "getPackagesByUidAndPid exception", e);
        }
        return arrayList;
    }

    public static List<String> a(Context context, int i, int i2) {
        List<String> a = a(context, i);
        if (a == null || a.isEmpty()) {
            return null;
        }
        String[] strArr;
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == i2) {
                    strArr = runningAppProcessInfo.pkgList;
                    break;
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "get caller pkgList exception ", e);
        }
        strArr = null;
        List<String> arrayList = new ArrayList();
        if (strArr != null) {
            for (Object obj : strArr) {
                if (a.contains(obj)) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList.size() > 0 ? arrayList : a;
    }

    public static int g(Context context, String str) {
        int i = -1;
        try {
            return context.getPackageManager().getApplicationInfo(str, 1).uid;
        } catch (Exception e) {
            Log.e(o.a(a), "getUidByPackage exception: " + str);
            return i;
        }
    }

    public static void h(Context context, String str) {
        int g = g(context, str);
        if (g >= 0) {
            o.a(a, "packageName:" + str + ", uid:" + g);
            try {
                if (c(context, "android.permission.UPDATE_DEVICE_STATS")) {
                    o.a(a, "has setThreadStatsUid permission");
                    Class.forName("android.net.TrafficStats").getMethod("setThreadStatsUid", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(g)});
                    return;
                }
                o.a(a, "not has setThreadStatsUid permission");
            } catch (Throwable e) {
                Log.e(o.a(a), "setThreadStatsUid e", e);
            }
        }
    }

    public static void g(Context context) {
        try {
            if (c(context, "android.permission.UPDATE_DEVICE_STATS")) {
                o.a(a, "has setThreadStatsUid permission");
                Class.forName("android.net.TrafficStats").getMethod("clearThreadStatsUid", new Class[0]).invoke(null, new Object[0]);
                return;
            }
            o.a(a, "not has setThreadStatsUid permission");
        } catch (Throwable e) {
            Log.e(o.a(a), "clearThreadStatsUid e", e);
        }
    }

    @TargetApi(17)
    public static boolean h(Context context) {
        boolean z = false;
        try {
            if (VERSION.SDK_INT < 17) {
                return true;
            }
            if (Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0) {
                z = true;
            }
            if (!z) {
                o.c(a, "Provisioned: " + z);
            }
            return z;
        } catch (Throwable e) {
            o.b(a, "isDeviceProvisioned exception", e);
            return true;
        }
    }
}
