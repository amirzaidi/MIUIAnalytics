package com.miui.analytics.internal.service;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.Analytics;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.LogEvent.IdType;
import com.miui.analytics.internal.LogEvent.LogType;
import com.miui.analytics.internal.policy.a;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.z;
import java.net.URL;
import java.util.List;
import miui.external.f;
import org.json.JSONArray;
import org.json.JSONObject;

public class i {
    public static final String A = "z";
    public static final String B = "st";
    public static final String C = "intl.";
    private static final String D = "ServiceHelper";
    public static final String a = "sid";
    public static final String b = "pk";
    public static final String c = "cv";
    public static final String d = "key";
    public static final String e = "eventTime";
    public static final String f = "sn";
    public static final String g = "retryCnt";
    public static final String h = "sender";
    public static final String i = "model";
    public static final String j = "device";
    public static final String k = "imei";
    public static final String l = "mac";
    public static final String m = "android";
    public static final String n = "miui";
    public static final String o = "lang";
    public static final String p = "region";
    public static final String q = "product";
    public static final String r = "cv";
    public static final String s = "bn";
    public static final String t = "mi";
    public static final String u = "userid";
    public static final String v = "aaid";
    public static final String w = "n";
    public static final String x = "x";
    public static final String y = "w";
    public static final String z = "y";

    public static JSONObject a(Context context, Boolean bool, int i, int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(i, z.e());
            jSONObject.put(m, z.b());
            jSONObject.put(n, z.c());
            jSONObject.put(s, z.d());
            jSONObject.put(q, Build.PRODUCT);
            jSONObject.put(j, Build.DEVICE);
            jSONObject.put("cv", Analytics.getVersionName());
            jSONObject.put(o, z.g());
            jSONObject.put(p, z.i());
            jSONObject.put(t, n.a() ? "1" : "0");
            jSONObject.put(h, context.getPackageName());
            jSONObject.put(u, z.x());
            jSONObject.put("n", String.valueOf(r.d(context)));
            switch (IdType.a(i2)) {
                case TYPE_DEFAULT:
                    CharSequence charSequence = "";
                    if (f.a() && !(n.a() && i == LogType.TYPE_AD.a())) {
                        charSequence = n.b(context);
                        jSONObject.put("aaid", charSequence);
                    }
                    if (!n.a() && (bool.booleanValue() || TextUtils.isEmpty(charSequence))) {
                        jSONObject.put("mac", z.f(context));
                        jSONObject.put("imei", z.d(context));
                    }
                    if (n.a() && i != LogType.TYPE_AD.a() && TextUtils.isEmpty(charSequence)) {
                        jSONObject.put(x, z.o(context));
                        break;
                    }
                case TYPE_IMEI:
                    jSONObject.put("imei", z.d(context));
                    break;
                case TYPE_MAC:
                    jSONObject.put("mac", z.f(context));
                    break;
                case TYPE_ANDROID_ID:
                    jSONObject.put(z, z.k(context));
                    break;
                case TYPE_AAID:
                    jSONObject.put("aaid", n.b(context));
                    break;
                case TYPE_GAID:
                    jSONObject.put(x, z.o(context));
                    break;
                case TYPE_GUID:
                    jSONObject.put(A, z.a(context, str));
                    break;
            }
            a c = h.a(context).c(context.getPackageName());
            if (c != null) {
                List<String> a = c.a();
                if (a != null) {
                    for (String str2 : a) {
                        if (jSONObject.has(str2)) {
                            jSONObject.remove(str2);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(D), "composeTrackingHeaders exception: ", e);
        } catch (Throwable e2) {
            Log.e(o.a(D), "TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT) Error: ", e2);
        }
        return jSONObject;
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null && jSONObject.names() != null) {
            JSONArray names = jSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                String optString = names.optString(i);
                if (!TextUtils.isEmpty(optString)) {
                    CharSequence optString2 = jSONObject.optString(optString);
                    CharSequence optString3 = jSONObject2.optString(optString);
                    if (!TextUtils.isEmpty(optString3) && optString3.equals(optString2)) {
                        jSONObject2.remove(optString);
                    } else if (TextUtils.isEmpty(optString3) && TextUtils.isEmpty(optString2)) {
                        jSONObject2.remove(optString);
                    }
                }
            }
        }
    }

    public static String a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (!str.startsWith("http")) {
            return (z ? "https://" : "http://") + str;
        } else if (z) {
            return str.replaceFirst("http://", "https://");
        } else {
            return str;
        }
    }

    public static String a(String str) {
        try {
            if (n.a()) {
                CharSequence charSequence;
                if (!str.startsWith("http")) {
                    str = "https://" + str;
                }
                CharSequence host = new URL(str).getHost();
                String str2 = "";
                if (host.contains(".")) {
                    String[] split = host.split("\\.");
                    charSequence = str2;
                    for (int i = 0; i < split.length; i++) {
                        String str3;
                        if (i == split.length - 2) {
                            str3 = charSequence + C;
                        }
                        charSequence = str3 + split[i];
                        if (i < split.length - 1) {
                            charSequence = charSequence + ".";
                        }
                    }
                } else {
                    charSequence = C + host;
                }
                str = str.replace(host, charSequence);
            }
        } catch (Throwable e) {
            Log.e(o.a(D), "ensureInternationalServer exception", e);
        }
        return str;
    }

    public static JSONObject a(LogEvent logEvent) {
        JSONObject jSONObject;
        try {
            if (TextUtils.isEmpty(logEvent.j())) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(logEvent.j());
            }
        } catch (Throwable e) {
            Log.e(o.a(D), "event:" + logEvent.toString());
            Log.e(o.a(D), "getPayloadHeader e", e);
            jSONObject = null;
        }
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Throwable e2) {
                Log.e(o.a(D), "getPayloadHeader exception: ", e2);
                return new JSONObject();
            }
        }
        jSONObject.put("sid", logEvent.d());
        jSONObject.put(b, logEvent.c());
        jSONObject.put(d, logEvent.b());
        jSONObject.put(e, logEvent.e() + "");
        jSONObject.put("sn", logEvent.h());
        jSONObject.put(g, logEvent.k());
        return jSONObject;
    }

    public static JSONObject b(LogEvent logEvent) {
        try {
            if (!TextUtils.isEmpty(logEvent.g())) {
                return new JSONObject(logEvent.g());
            }
        } catch (Throwable e) {
            Log.e(o.a(D), "getPayloadBody exception: ", e);
        }
        return new JSONObject();
    }
}
