package com.miui.analytics.internal.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.f;
import com.miui.analytics.internal.policy.j;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.z;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
    private static volatile c A = null;
    public static final String a = "00";
    public static final String b = "01";
    public static final String c = "10";
    public static final String d = "20";
    public static final String e = "21";
    public static final String f = "22";
    public static final String g = "30";
    public static final String h = "31";
    public static final String i = "40";
    public static final String j = "--";
    public static final String k = "00";
    public static final String l = "01";
    public static final String m = "02";
    public static final String n = "03";
    public static final String o = "04";
    public static final String p = "05";
    public static final String q = "10";
    public static final String r = "11";
    public static final String s = "12";
    public static final String t = "13";
    public static final String u = "40";
    public static final String v = "41";
    public static final String w = "50";
    public static final String x = "51";
    public static final String y = "53";
    private static final String z = "ProcessReporter";
    private Context B;
    private f C = new f(this.B);

    public static c a(Context context) {
        if (A == null) {
            synchronized (c.class) {
                if (A == null) {
                    A = new c(context);
                }
            }
        }
        return A;
    }

    private c(Context context) {
        this.B = com.miui.analytics.internal.util.c.a(context);
    }

    public void a(String str, String str2, boolean z) {
        if (c()) {
            try {
                o.a(z, "report:" + str + "," + str2 + "," + z);
                this.C.a(str, str2, z);
                b();
            } catch (Throwable e) {
                Log.e(o.a(z), "report e", e);
            }
        }
    }

    private void b() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray b = this.C.b();
            if (b != null && b.length() != 0) {
                jSONObject.put("i", z.d(this.B));
                jSONObject.put("a", b);
                a.a(this.B, new LogEvent(this.B, "com.miui.analytics", com.miui.analytics.internal.f.s, jSONObject.toString()), null);
                this.C.a();
            }
        } catch (Throwable e) {
            Log.e(o.a(z), "getConent e", e);
        }
    }

    public void a(Context context, List<LogEvent> list) {
        if (c() && list != null) {
            try {
                if (list.size() != 0) {
                    for (LogEvent logEvent : list) {
                        if (!com.miui.analytics.internal.f.s.equals(logEvent.b())) {
                            this.C.a(a(logEvent.b()), w, true);
                        }
                    }
                    b();
                }
            } catch (Throwable e) {
                Log.e(o.a(z), "reportDeleteByDiscard exception:", e);
            }
        }
    }

    public void b(Context context, List<LogEvent> list) {
        if (c() && list != null) {
            try {
                if (list.size() != 0) {
                    for (LogEvent logEvent : list) {
                        if (!com.miui.analytics.internal.f.s.equals(logEvent.b())) {
                            this.C.a(a(logEvent.b()), x, true);
                        }
                    }
                    b();
                }
            } catch (Throwable e) {
                Log.e(o.a(z), "reportDeleteBySendCount exception:", e);
            }
        }
    }

    public void a() {
        a(j, y, true);
    }

    private String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(com.miui.analytics.internal.f.f)) {
                return "00";
            }
            if (str.equals(com.miui.analytics.internal.f.g)) {
                return "01";
            }
            if (str.equals(com.miui.analytics.internal.f.q)) {
                return "10";
            }
            if (str.equals(com.miui.analytics.internal.f.i)) {
                return d;
            }
            if (str.equals(com.miui.analytics.internal.f.j)) {
                return e;
            }
            if (str.equals(com.miui.analytics.internal.f.e)) {
                return f;
            }
            if (str.equals(com.miui.analytics.internal.f.k)) {
                return g;
            }
            if (str.equals(com.miui.analytics.internal.f.l)) {
                return h;
            }
            if (str.equals(com.miui.analytics.internal.f.r)) {
                return "40";
            }
        }
        return j;
    }

    private boolean c() {
        double t = g.t();
        o.a(z, "sample:" + t);
        if (t <= 0.0d) {
            return false;
        }
        if (new j(t).a()) {
            o.a(z, "sample apply");
            return true;
        }
        o.a(z, "sample not apply");
        return false;
    }
}
