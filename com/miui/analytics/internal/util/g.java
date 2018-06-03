package com.miui.analytics.internal.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.Analytics;
import com.miui.analytics.internal.f;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class g {
    private static final String A = "process_sample";
    private static final String B = "zero_clock_access_peak_upload_sample";
    private static final String C = "max_delay_hour_in_zero_clock";
    private static final String D = "min_delay_hour_in_zero_clock";
    private static final String E = "need_delay_hours";
    private static final String F = "enable_screen_origin_data";
    private static final String G = "usagev2_cancel_sample";
    private static final String H = "usagev2_cancel_hour";
    private static final String I = "encrypted_imei_len";
    private static final String J = "encrypted_meid_len";
    private static final String K = "enable_usage";
    private static final String L = "token_interval";
    private static final String M = "enable_scaned_spot";
    private static final int N = 2;
    private static final int O = 10;
    private static final int P = 50;
    private static final int Q = 600000;
    private static final int R = 10800000;
    private static final int S = 300000;
    private static final int T = 3600000;
    private static final int U = 3;
    private static final int V = 60;
    private static final int W = 3;
    private static final boolean X = false;
    private static final boolean Y = false;
    private static final boolean Z = true;
    public static final String a = "upload_time";
    private static final boolean aa = false;
    private static final boolean ab = false;
    private static final long ac = 300000;
    private static final long ad = 300000;
    private static final long ae = 604800000;
    private static final long af = 60000;
    private static final boolean ag = false;
    private static final double ah = 0.5d;
    private static final int ai = 0;
    private static final int aj = 8;
    private static final int ak = 8;
    private static final boolean al = true;
    private static final long am = 2000;
    private static final boolean an = false;
    private static final double ao = 0.3d;
    private static final int ap = 3;
    private static final int aq = 2;
    private static final int[] ar = new int[]{0};
    private static g as = null;
    public static final String b = "drop_day";
    public static final long c = 300000;
    public static final long d = 1800000;
    public static final boolean e = false;
    private static final String f = "ConfigProvider";
    private static final String g = "enableDeviceId";
    private static final String h = "interval_sec";
    private static final String i = "mds_enable";
    private static final String j = "gps_location_enable";
    private static final String k = "min_record_mds_interval_sec";
    private static final String l = "enable_global_mds";
    private static final String m = "max_mds_conditions_per_day";
    private static final String n = "max_retry_count";
    private static final String o = "mds_match_interval_sec";
    private static final String p = "max_mds_loc_count_in_mobile_network";
    private static final String q = "scene";
    private static final String r = "scene_interval";
    private static final String s = "scene_screen_off_time";
    private static final String t = "interval";
    private static final String u = "force_interval";
    private static final String v = "nearest_store_days";
    private static final String w = "store_counts";
    private static final String x = "nearest_store_enable";
    private static final String y = "j_initial";
    private static final String z = "system_app";
    private Context at;

    private g(Context context) {
        this.at = context;
    }

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (as == null) {
                as = new g(context);
            }
            gVar = as;
        }
        return gVar;
    }

    private static JSONObject b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object clientExtra = Analytics.getClientExtra(str, str2);
            if (!TextUtils.isEmpty(clientExtra)) {
                return new JSONObject(clientExtra);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getConfigClientExtra exception:", e);
        }
        return jSONObject;
    }

    public boolean a(String str, String str2) {
        return b(str, str2).optBoolean(g, true);
    }

    public int a() {
        return b("com.miui.analytics", f.m).optInt(n, 2);
    }

    public long b() {
        long optInt = (long) (b("com.miui.analytics", f.h).optInt(k, 0) * ac.f);
        if (optInt != 0) {
            return optInt;
        }
        return 600000;
    }

    public static long c() {
        long optInt = ((long) b("com.miui.analytics", f.h).optInt(h, 0)) * 1000;
        if (optInt == 0) {
            optInt = 10800000;
        }
        return Math.max(optInt, 3600000);
    }

    public static long d() {
        long optInt = ((long) b("com.miui.analytics", f.h).optInt(o, 0)) * 1000;
        if (optInt != 0) {
            return optInt;
        }
        return c;
    }

    public static boolean e() {
        return b("com.miui.analytics", f.h).optBoolean(l, false);
    }

    public static boolean f() {
        return b("com.miui.analytics", f.h).optBoolean(i, false);
    }

    public static boolean g() {
        return b("com.miui.analytics", f.h).optBoolean(j, false);
    }

    public static int h() {
        return b("com.miui.analytics", f.h).optInt(m, O);
    }

    public static int i() {
        return b("com.miui.analytics", f.h).optInt(p, P);
    }

    public static boolean b(Context context) {
        boolean z = false;
        if (n.e(context)) {
            try {
                Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.o);
                if (!TextUtils.isEmpty(clientExtra)) {
                    z = new JSONObject(clientExtra).optBoolean(q, false);
                }
            } catch (Throwable e) {
                Log.e(o.a(f), "enableUploadScene exception:", e);
            }
        } else {
            o.a(f, "uploadThroughSystemKeepAliveService is not SupportXMSFUpload, unable upload scene");
        }
        return z;
    }

    public static long j() {
        long j = d;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.o);
            if (!TextUtils.isEmpty(clientExtra)) {
                j = new JSONObject(clientExtra).optLong(r, d);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getUploadSceneInterval exception:", e);
        }
        return j < c ? c : j;
    }

    public static long k() {
        long j = c;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.o);
            if (!TextUtils.isEmpty(clientExtra)) {
                j = new JSONObject(clientExtra).optLong(s, c);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getUploadSceneScreenOffTime exception:", e);
        }
        o.a(f, "getUploadSceneScreenOffTime:" + j);
        return j;
    }

    public static int[] l() {
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.k);
            if (!TextUtils.isEmpty(clientExtra)) {
                JSONArray optJSONArray = new JSONObject(clientExtra).optJSONArray(a);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int[] iArr = new int[optJSONArray.length()];
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        iArr[i] = optJSONArray.optInt(i);
                    }
                    return iArr;
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getUsageUploadTime exception:", e);
        }
        return null;
    }

    public static int m() {
        int i = 3;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.k);
            if (!TextUtils.isEmpty(clientExtra)) {
                i = new JSONObject(clientExtra).optInt(b, 3);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getDefaultUsageFileDropDays exception:", e);
        }
        return i;
    }

    public static long n() {
        long j = c;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.q);
            if (!TextUtils.isEmpty(clientExtra)) {
                j = new JSONObject(clientExtra).optLong(t, c);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getDefaultUploadAaidInterval exception:", e);
        }
        return j;
    }

    public static long o() {
        long j = 604800000;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.q);
            if (!TextUtils.isEmpty(clientExtra)) {
                j = new JSONObject(clientExtra).optLong(u, 604800000);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getForceUploadAaidInterval exception:", e);
        }
        return j;
    }

    public static int p() {
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.r);
            if (!TextUtils.isEmpty(clientExtra)) {
                int optInt = new JSONObject(clientExtra).optInt(v, V);
                o.a(f, "nearest stores upload days :" + optInt);
                return optInt;
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getNearestStoreUploadDays exception:", e);
        }
        return V;
    }

    public static int q() {
        int i = 3;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.r);
            if (!TextUtils.isEmpty(clientExtra)) {
                i = new JSONObject(clientExtra).optInt(w, 3);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getNearestStoreUploadCounts exception:", e);
        }
        return i;
    }

    public static boolean r() {
        boolean z = false;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.r);
            if (!TextUtils.isEmpty(clientExtra)) {
                z = new JSONObject(clientExtra).optBoolean(x, false);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "enableUploadNearestStore exception:", e);
        }
        return z;
    }

    public static long s() {
        long j = af;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.f);
            if (!TextUtils.isEmpty(clientExtra)) {
                j = new JSONObject(clientExtra).optLong(y, af);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getJobSchedulerInitialBackoffMillis exception:", e);
        }
        return j;
    }

    public static Set<String> a(String str) {
        Set<String> hashSet = new HashSet();
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", str);
            if (!TextUtils.isEmpty(clientExtra)) {
                JSONArray optJSONArray = new JSONObject(clientExtra).optJSONArray(z);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        hashSet.add(optJSONArray.optString(i));
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getUsageWhiteList e", e);
        }
        return hashSet;
    }

    public static boolean c(Context context) {
        if (!n.a() || n.a(context)) {
            return true;
        }
        o.a(f, "international build and miui upload log not enable, disable usage");
        return false;
    }

    public static double t() {
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                return new JSONObject(clientExtra).getDouble(A);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getProcessReporterSample exception", e);
        }
        return 0.0d;
    }

    public static double u() {
        double d = ao;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                d = new JSONObject(clientExtra).optDouble(B, ao);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getAccessPeakInZeroClockUploadSample exception", e);
        }
        return d;
    }

    public static int v() {
        int i = 3;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                i = new JSONObject(clientExtra).optInt(C, 3);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getDelayInMaxHour exception", e);
        }
        return i;
    }

    public static int w() {
        int i = 2;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                i = new JSONObject(clientExtra).optInt(D, 2);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getDelayInMinHour exception", e);
        }
        return i;
    }

    public static int[] x() {
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                JSONArray optJSONArray = new JSONObject(clientExtra).optJSONArray(E);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int[] iArr = new int[optJSONArray.length()];
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        iArr[i] = optJSONArray.optInt(i);
                    }
                    return iArr;
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getActiveNeedDelayLocalHours exception", e);
        }
        return ar;
    }

    public static boolean y() {
        boolean z = false;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.w);
            if (!TextUtils.isEmpty(clientExtra)) {
                z = new JSONObject(clientExtra).optBoolean(F, false);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "enableScreenOriginData exception:", e);
        }
        return z;
    }

    public static double z() {
        double d = ah;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                d = new JSONObject(clientExtra).optDouble(G, ah);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getUsageV2CancelSample exception", e);
        }
        return d;
    }

    public static int A() {
        int i = 0;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                i = new JSONObject(clientExtra).optInt(H, 0);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getUsageV2CancelHour exception", e);
        }
        return i;
    }

    public static int B() {
        int i = 8;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                i = new JSONObject(clientExtra).optInt(I, 8);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getEncryptedImeiLength exception", e);
        }
        return i;
    }

    public static int C() {
        int i = 8;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                i = new JSONObject(clientExtra).optInt(J, 8);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getEncryptedMeidLength exception", e);
        }
        return i;
    }

    public static boolean D() {
        boolean z = true;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.i);
            if (!TextUtils.isEmpty(clientExtra)) {
                z = new JSONObject(clientExtra).optBoolean(K, true);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "enableUploadUsageV1 exception:", e);
        }
        return z;
    }

    public static long E() {
        long j = am;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.s);
            if (!TextUtils.isEmpty(clientExtra)) {
                j = new JSONObject(clientExtra).optLong(L, am);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getDeviceTokenInterval exception:", e);
        }
        return j;
    }

    public static boolean F() {
        boolean z = false;
        try {
            Object clientExtra = Analytics.getClientExtra("com.miui.analytics", f.w);
            if (!TextUtils.isEmpty(clientExtra)) {
                z = new JSONObject(clientExtra).optBoolean(M, false);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "enableUploadScanedSpot exception:", e);
        }
        return z;
    }
}
