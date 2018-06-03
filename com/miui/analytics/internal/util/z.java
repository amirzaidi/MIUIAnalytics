package com.miui.analytics.internal.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.miui.analytics.internal.b.d;
import com.miui.analytics.internal.policy.f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class z {
    private static final DecimalFormat A = new DecimalFormat("#0.#");
    private static volatile String B = "";
    private static volatile long C = 0;
    public static final int a = 24;
    public static final int b = 23;
    public static final int c = 22;
    public static final int d = 21;
    public static final int e = 19;
    public static final int f = 17;
    private static final String g = "SysUtils";
    private static String h = null;
    private static String i = null;
    private static String j = null;
    private static String k = null;
    private static String l = null;
    private static final String m = "ro.build.product";
    private static final String n = "ro.build.description";
    private static final String o = "ro.build.version.sdk";
    private static final String p = "ro.miui.cust_variant";
    private static final String q = "ro.miui.region";
    private static final String r = "ro.product.mod_device";
    private static final String s = "persist.radio.modem";
    private static final String t = "ro.board.platform";
    private static final String u = "\\d+.\\d+.\\d+(-internal)?";
    private static final long v = 1024;
    private static final long w = 1024;
    private static final long x = 1048576;
    private static final long y = 1073741824;
    private static final DecimalFormat z = new DecimalFormat("#0");

    public static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (Throwable e) {
            o.b(g, "SysUtils sleep failed!", e);
        }
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(h)) {
            try {
                h = h.g(context);
                if (TextUtils.isEmpty(h)) {
                    Log.w(g, "device id is null");
                }
            } catch (Throwable e) {
                Log.e(g, "getIMEI exception: ", e);
            }
        }
        return h;
    }

    public static String b(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Throwable e) {
            Log.e(o.a(g), "get imsi failed!", e);
            return "";
        }
    }

    public static String a() {
        try {
            String a = aa.a("ril.limit_service_mnc", "");
            String a2 = aa.a("ril.limit_service_mcc", "");
            if (TextUtils.isEmpty(a) || a.length() < 3) {
                if (!TextUtils.isEmpty(a2) && a2.length() == 3) {
                    try {
                        Integer.parseInt(a2);
                        return a2;
                    } catch (Throwable e) {
                        Log.e(o.a(g), "getMccInLimitService mcc_mtk e", e);
                    }
                }
                return "";
            }
            a2 = a.substring(a.length() - 3, a.length());
            try {
                Integer.parseInt(a2);
                return a2;
            } catch (Throwable e2) {
                Log.e(o.a(g), "getMccInLimitService network_mcc e", e2);
            }
        } catch (Throwable e22) {
            Log.e(o.a(g), "getMccInLimitService e", e22);
        }
    }

    public static String c(Context context) {
        if (!TextUtils.isEmpty(k)) {
            return k;
        }
        if (VERSION.SDK_INT >= 23) {
            k = E();
        }
        if (TextUtils.isEmpty(k)) {
            try {
                k = ((WifiManager) context.getSystemService(f.i)).getConnectionInfo().getMacAddress();
            } catch (Throwable e) {
                o.b(g, "get mac failed!", e);
            }
        }
        if (TextUtils.isEmpty(k)) {
            Log.w(g, "mac is null");
        }
        return k;
    }

    private static String E() {
        String str = "";
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (!(hardwareAddress == null || hardwareAddress.length == 0)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if ("wlan0".equals(networkInterface.getName())) {
                        return stringBuilder2;
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(g), "getMacAboveAndroidM exception: ", e);
        }
        return str;
    }

    public static synchronized String d(Context context) {
        String str;
        synchronized (z.class) {
            if (TextUtils.isEmpty(i)) {
                try {
                    v vVar = new v(context, u.d, u.i);
                    i = vVar.b(u.P, null);
                    if (TextUtils.isEmpty(i)) {
                        String a = a(context);
                        if (TextUtils.isEmpty(a)) {
                            str = "";
                        } else {
                            i = af.a(a);
                            if (!TextUtils.isEmpty(i)) {
                                vVar.a(u.P, i);
                            }
                        }
                    }
                } catch (Throwable e) {
                    Log.e(g, "getHashedIMEI exception: ", e);
                }
            }
            str = i;
        }
        return str;
    }

    public static String e(Context context) {
        String b = b(context);
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return af.a(b);
    }

    public static synchronized String f(Context context) {
        String str;
        synchronized (z.class) {
            if (TextUtils.isEmpty(l)) {
                try {
                    v vVar = new v(context, u.d, u.i);
                    l = vVar.b(u.Q, null);
                    if (TextUtils.isEmpty(l)) {
                        String c = c(context);
                        if (TextUtils.isEmpty(c)) {
                            str = "";
                        } else {
                            l = af.a(c);
                            if (!TextUtils.isEmpty(l)) {
                                vVar.a(u.Q, l);
                            }
                        }
                    }
                } catch (Throwable e) {
                    Log.e(g, "getHashedMac exception: ", e);
                }
            }
            str = l;
        }
        return str;
    }

    public static String g(Context context) {
        if (TextUtils.isEmpty(j)) {
            v vVar = new v(context, u.d, u.i);
            j = vVar.b(u.R, null);
            if (TextUtils.isEmpty(j)) {
                try {
                    Class cls = Class.forName("miui.telephony.TelephonyManager");
                    Method declaredMethod = cls.getDeclaredMethod("getDefault", new Class[0]);
                    declaredMethod.setAccessible(true);
                    Object invoke = declaredMethod.invoke(null, new Object[0]);
                    if (invoke != null) {
                        Method declaredMethod2 = cls.getDeclaredMethod("getMiuiDeviceId", new Class[0]);
                        declaredMethod2.setAccessible(true);
                        Object invoke2 = declaredMethod2.invoke(invoke, new Object[0]);
                        if (invoke2 != null && (invoke2 instanceof String)) {
                            j = (String) String.class.cast(invoke2);
                        }
                    }
                } catch (Throwable th) {
                    Log.w(o.a(g), "getMiuiDeviceId exception: ", th);
                }
                if (TextUtils.isEmpty(j)) {
                    o.c(g, "sStableImei is null");
                } else {
                    vVar.a(u.R, j);
                }
            }
        }
        return j;
    }

    public static String h(Context context) {
        String g = g(context);
        if (TextUtils.isEmpty(g)) {
            return "";
        }
        return af.a(g);
    }

    public static String i(Context context) {
        Object a = a(context);
        if (TextUtils.isEmpty(a)) {
            a = c(context);
        }
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(a.getBytes()), 8).substring(0, 16);
        } catch (Throwable e) {
            o.b(g, "getHashedDeviceId Exception: ", e);
            return "";
        }
    }

    public static String j(Context context) {
        try {
            return af.a(Secure.getString(context.getContentResolver(), "android_id"));
        } catch (Throwable e) {
            o.b(g, "getHashedAndroidId Exception: ", e);
            return "";
        }
    }

    public static String k(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable e) {
            o.b(g, "getAndroidId Exception: ", e);
            return "";
        }
    }

    public static String b() {
        return VERSION.RELEASE;
    }

    public static String c() {
        return VERSION.INCREMENTAL;
    }

    public static String d() {
        if (n.b()) {
            return "A";
        }
        if (n.d()) {
            return "S";
        }
        if (n.c()) {
            return "D";
        }
        return "";
    }

    public static String e() {
        return Build.MODEL;
    }

    public static String f() {
        try {
            String a = aa.a("ro.product.model.real", "");
            if (TextUtils.isEmpty(a)) {
                return e();
            }
            return a;
        } catch (Throwable e) {
            o.b(g, "getRealModel Exception: ", e);
            return "";
        }
    }

    public static String g() {
        if (TextUtils.isEmpty(aa.a("persist.sys.language", ""))) {
            return h();
        }
        return aa.a("persist.sys.language", "");
    }

    public static String h() {
        try {
            return Locale.getDefault().toString();
        } catch (Throwable e) {
            o.b(g, "getLocalLanguage Exception: ", e);
            return "";
        }
    }

    public static String i() {
        try {
            String a = aa.a(q, "");
            if (TextUtils.isEmpty(a)) {
                return aa.a("ro.product.locale.region", "");
            }
            return a;
        } catch (Throwable e) {
            o.b(g, "getRegion Exception: ", e);
            return "";
        }
    }

    public static String j() {
        try {
            String a = aa.a(r, "");
            if (TextUtils.isEmpty(a)) {
                return Build.DEVICE;
            }
            return a;
        } catch (Throwable e) {
            o.b(g, "getDeviceName Exception: ", e);
            return "";
        }
    }

    public static String l(Context context) {
        try {
            Object a = a(context);
            Object c = c(context);
            StringBuffer stringBuffer = new StringBuffer();
            if (!TextUtils.isEmpty(a)) {
                stringBuffer.append(a);
            }
            if (!TextUtils.isEmpty(c)) {
                stringBuffer.append("_");
                stringBuffer.append(c);
            }
            return af.a(stringBuffer.toString());
        } catch (Throwable e) {
            o.b(g, "getUUID Exception: ", e);
            return "";
        }
    }

    public static String k() {
        return aa.a(m, "");
    }

    public static String l() {
        return aa.a(n, "");
    }

    public static String m() {
        return aa.a(o, "");
    }

    public static String n() {
        return aa.a(p, "");
    }

    public static String o() {
        return aa.a(q, "");
    }

    public static String p() {
        return aa.a(s, "");
    }

    public static String m(Context context) {
        return a(s(context), true);
    }

    public static String q() {
        return a(F(), true);
    }

    public static String r() {
        return aa.a(t, "");
    }

    public static boolean s() {
        return !TextUtils.isEmpty(VERSION.INCREMENTAL) && VERSION.INCREMENTAL.matches(u);
    }

    public static boolean t() {
        return !TextUtils.isEmpty(VERSION.INCREMENTAL) && VERSION.INCREMENTAL.matches(u) && "user".equals(Build.TYPE);
    }

    public static boolean u() {
        return aa.a(r, "").endsWith("_alpha");
    }

    public static boolean v() {
        return aa.a(r, "").endsWith("_global");
    }

    public static int w() {
        try {
            Class cls = Class.forName("miui.telephony.TelephonyManager");
            return ((Integer) cls.getMethod("getPhoneCount", new Class[0]).invoke(cls.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]), new Object[0])).intValue();
        } catch (Throwable e) {
            o.b(g, "getPhoneCount Exception: ", e);
            return 0;
        }
    }

    private static long s(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 2048);
            String readLine = bufferedReader.readLine();
            readLine = readLine.substring(readLine.indexOf("MemTotal:"));
            bufferedReader.close();
            return ((long) Integer.parseInt(readLine.replaceAll("\\D+", ""))) * 1024;
        } catch (Throwable e) {
            o.b(g, "getTotalRAM Exception: ", e);
            return 0;
        }
    }

    public static double n(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return ((double) Math.round(((((double) registerReceiver.getIntExtra("level", -1)) / ((double) registerReceiver.getIntExtra("scale", -1))) * 100.0d) * 10.0d)) / 10.0d;
        } catch (Throwable e) {
            Log.e(o.a(g), "getBatteryInfo exception", e);
            return 0.0d;
        }
    }

    private static long F() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            if (blockCount >= 8589934592L) {
                return ((((blockCount / 8) / y) + 1) * y) * 8;
            }
            return ((blockCount / y) + 1) * y;
        } catch (Throwable e) {
            o.b(g, "getTotalROM Exception: ", e);
            return 0;
        }
    }

    public static String a(long j, boolean z) {
        if (z) {
            try {
                DecimalFormat decimalFormat = z;
            } catch (Throwable e) {
                o.b(g, "formatFileSize Exception: ", e);
                return "";
            }
        }
        decimalFormat = A;
        String str = "0MB";
        if (j < 1024 && j > 0) {
            return decimalFormat.format((double) j) + "B";
        }
        if (j < x) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        }
        if (j < y) {
            return decimalFormat.format(((double) j) / 1048576.0d) + "MB";
        }
        return decimalFormat.format(((double) j) / 1.073741824E9d) + "GB";
    }

    public static int x() {
        Integer num;
        Throwable e;
        Integer num2 = null;
        try {
            if (VERSION.SDK_INT >= 17) {
                Method declaredMethod = Class.forName("android.os.UserHandle").getDeclaredMethod("getUserId", new Class[]{Integer.TYPE});
                declaredMethod.setAccessible(true);
                int myUid = Process.myUid();
                num = (Integer) declaredMethod.invoke(null, new Object[]{Integer.valueOf(myUid)});
                try {
                    o.a(g, String.format("getUserId, uid:%d, userId:%d", new Object[]{Integer.valueOf(myUid), num}));
                } catch (Throwable e2) {
                    Throwable th = e2;
                    num2 = num;
                    e = th;
                    Log.e(o.a(g), "getUserId exception: ", e);
                    num = num2;
                    if (num == null) {
                        num = Integer.valueOf(0);
                    }
                    return num.intValue();
                }
            }
            num = null;
        } catch (Exception e3) {
            e = e3;
            Log.e(o.a(g), "getUserId exception: ", e);
            num = num2;
            if (num == null) {
                num = Integer.valueOf(0);
            }
            return num.intValue();
        }
        if (num == null) {
            num = Integer.valueOf(0);
        }
        return num.intValue();
    }

    public static boolean y() {
        if (x() == 0) {
            return true;
        }
        return false;
    }

    public static boolean z() {
        int x = x();
        if (x < 10 || x == 99 || x == 999) {
            return false;
        }
        o.a(g, "second space");
        return true;
    }

    public static boolean A() {
        if (x() == 999) {
            return true;
        }
        return false;
    }

    public static boolean a(String str) {
        try {
            return ((Boolean) Class.forName("miui.os.MiuiInit").getMethod("isPreinstalledPackage", new Class[]{String.class}).invoke(null, new Object[]{str})).booleanValue();
        } catch (Throwable e) {
            if (miui.external.f.a()) {
                Log.e(o.a(g), "checkPreinstallApp failed:", e);
            }
            return false;
        } catch (Throwable e2) {
            Log.e(o.a(g), "checkPreinstallApp failed:", e2);
            return false;
        }
    }

    public static String B() {
        long currentTimeMillis = System.currentTimeMillis();
        return af.a(String.valueOf(((double) currentTimeMillis) + new Random(currentTimeMillis).nextDouble())).substring(0, 24);
    }

    private static synchronized String t(Context context) {
        String str;
        synchronized (z.class) {
            try {
                Object a = a.a().a(context);
                if (!TextUtils.isEmpty(a)) {
                    B = a;
                    C = System.currentTimeMillis();
                    v vVar = new v(context, u.d);
                    vVar.a(u.ab, B);
                    vVar.a(u.ac, C);
                    str = B;
                }
            } catch (Exception e) {
                Log.e(o.a(g), "getGaidInner Exception:" + e);
            }
            str = "";
        }
        return str;
    }

    public static String o(final Context context) {
        try {
            if (!n.a()) {
                o.a(g, "Can not get GAID in non-international system.");
                return "";
            } else if (C()) {
                o.a(g, "getGaid in main thread.");
                if (TextUtils.isEmpty(B) || ac.a(C, (long) ac.b)) {
                    ab.a(new Runnable() {
                        public void run() {
                            z.t(context);
                        }
                    });
                }
                if (TextUtils.isEmpty(B)) {
                    return new v(context, u.d).b(u.ab, "");
                }
                return B;
            } else if (!TextUtils.isEmpty(B) && !ac.a(C, (long) ac.b)) {
                return B;
            } else {
                if (TextUtils.isEmpty(t(context))) {
                    return new v(context, u.d).b(u.ab, "");
                }
                return "";
            }
        } catch (Exception e) {
            Log.e(o.a(g), "getGaid Exception:" + e);
        }
    }

    public static void a(Context context, long j) {
        try {
            new v(context, u.d).a("app_first_active_time", j);
        } catch (Throwable e) {
            Log.e(o.a(g), "saveFirstActiveTime", e);
        }
    }

    public static long p(Context context) {
        long j = 0;
        try {
            j = new v(context, u.d).b("app_first_active_time", 0);
        } catch (Throwable e) {
            Log.e(o.a(g), "getFirstActiveTime e", e);
        }
        return j;
    }

    public static boolean C() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String D() {
        try {
            return aa.a("ro.product.manufacturer", "");
        } catch (Throwable e) {
            Log.e(o.a(g), "getProductManufacturer e", e);
            return "";
        }
    }

    public static String a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            o.a(g, "context or appId is null, and appId = " + str);
            return "";
        }
        try {
            String str2;
            long longValue;
            d a = d.a(context);
            Object[] c = a.c(str);
            String str3 = "";
            if (c != null) {
                str2 = (String) c[0];
                longValue = ((Long) c[1]).longValue();
            } else {
                str2 = str3;
                longValue = 0;
            }
            o.a(g, "appid = " + str + " guid = " + str2 + " createtime = " + longValue);
            if (!TextUtils.isEmpty(str2) && System.currentTimeMillis() - longValue <= 7776000000L) {
                return str2;
            }
            str2 = UUID.randomUUID().toString();
            o.a(g, "create new guid: " + str2);
            a.a(str, str2, System.currentTimeMillis());
            return str2;
        } catch (Throwable e) {
            Log.e(o.a(g), "getGUID exception", e);
            return "";
        }
    }

    public static void q(Context context) {
        try {
            v vVar = new v(context, u.d, u.i);
            if (!vVar.b(u.U, false)) {
                vVar.a("imei");
                vVar.a("mac");
                vVar.a(u.R);
                vVar.a(u.U, true);
            }
        } catch (Throwable e) {
            Log.e(o.a(g), "deleteDeviceIdInSpFile exception", e);
        }
    }
}
