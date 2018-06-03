package com.miui.analytics.internal.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.e;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import org.json.JSONObject;

public class ae {
    private static final String a = "TrackUtils";

    public static void a(Context context, String str, String str2, Throwable th) {
        try {
            String a = af.a(th);
            String b = e.b(context);
            int a2 = e.a(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pkg", context.getPackageName());
            jSONObject.put("vc", a2);
            jSONObject.put("vn", b);
            jSONObject.put("es", a);
            if (th != null) {
                jSONObject.put(f.H, th.getMessage());
            }
            jSONObject.put(f.I, str2);
            if (str2 == null) {
                str2 = "";
            }
            Log.e(o.a(str), String.format("%s: %s", new Object[]{str2, a}));
            k.a(context).a(new LogEvent(context, "com.miui.analytics", f.t, jSONObject.toString()));
        } catch (Throwable e) {
            Log.e(o.a(a), "trackException exception:", e);
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            int a = e.a(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pkg", context.getPackageName());
            jSONObject.put("vc", a);
            jSONObject.put(f.I, str);
            jSONObject.put(f.H, str2);
            k.a(context).a(new LogEvent(context, "com.miui.analytics", f.t, jSONObject.toString()));
        } catch (Throwable e) {
            Log.e(o.a(a), "trackMessage exception:", e);
        }
    }

    public static void b(Context context, String str, String str2) {
        if (context == null) {
            o.c(a, "trackStartUpgrade, context is not initialized");
            return;
        }
        try {
            String packageName = context.getPackageName();
            if (!"com.miui.analytics".equals(packageName)) {
                o.c(a, "trackStartUpgrade, context package not support, skip to report, " + packageName);
            } else if ("com.miui.analytics".equals(str)) {
                packageName = String.valueOf(c.b(context, str));
                String valueOf = String.valueOf(VERSION.SDK_INT);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f.x, str2);
                jSONObject.put(f.z, a(valueOf, str2));
                jSONObject.put(f.B, a(packageName, str2));
                Log.d(o.a(a), String.format("start upgrade pkg:%s, currentVersion:%s, newVersion:%s, androidSdkLevel:%s", new Object[]{str, packageName, str2, valueOf}));
                k.a(context).a(new LogEvent(context, "com.miui.analytics", f.u, jSONObject.toString()));
            } else {
                o.c(a, "trackStartUpgrade, package not support, skip to report, " + packageName);
            }
        } catch (Throwable e) {
            a(context, a, "trackStartUpgrade exception:", e);
        }
    }

    public static void c(Context context, String str, String str2) {
        if (context == null) {
            o.c(a, "trackEndUpgrade, context is not initialized");
            return;
        }
        try {
            String packageName = context.getPackageName();
            if (!"com.miui.analytics".equals(packageName)) {
                o.c(a, "trackEndUpgrade, context package not support, skip to report, " + packageName);
            } else if ("com.miui.analytics".equals(str)) {
                packageName = String.valueOf(c.b(context, str));
                String valueOf = String.valueOf(VERSION.SDK_INT);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f.y, packageName);
                jSONObject.put("es", a(valueOf, packageName));
                jSONObject.put(f.C, a(str2, packageName));
                Log.d(o.a(a), String.format("End upgrade pkg:%s, currentVersion:%s, oldVersion:%s, androidSdkLevel:%s", new Object[]{str, packageName, str2, valueOf}));
                k.a(context).a(new LogEvent(context, "com.miui.analytics", f.u, jSONObject.toString()));
            } else {
                o.c(a, "trackEndUpgrade, package not support, skip to report, " + packageName);
            }
        } catch (Throwable e) {
            a(context, a, "trackEndUpgrade exception:", e);
        }
    }

    private static String a(String str, String str2) {
        if (str == null) {
            str = "null";
        }
        return TextUtils.isEmpty(str2) ? str : str + "_" + str2;
    }
}
