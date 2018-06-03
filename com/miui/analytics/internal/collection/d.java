package com.miui.analytics.internal.collection;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.Analytics;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.service.b;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.h;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.z;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class d {
    private static final String b = "aaid";
    private static final String c = "last_aaid";
    private static final String d = "curr_aaid";
    private static final String e = "d";
    private static final String f = "i";
    private static final String g = "m";
    private static final String h = "f";
    private static final String i = "g";
    private static final String j = "1.36.0";
    private static volatile d k;
    Runnable a = new Runnable(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void run() {
            try {
                long b = this.a.m.b(u.F, 0);
                if (r.b(this.a.l) && ac.a(b, g.n())) {
                    String b2 = this.a.m.b("last_aaid", "");
                    String b3 = n.b(this.a.l);
                    String c = n.c(this.a.l);
                    String b4 = this.a.m.b(u.C, "");
                    String d = z.d(this.a.l);
                    String b5 = this.a.m.b(u.D, "");
                    String d2 = h.d(this.a.l);
                    String b6 = this.a.m.b(u.E, "");
                    String f = h.f(this.a.l);
                    String b7 = this.a.m.b(u.H, "");
                    String o = z.o(this.a.l);
                    if (this.a.a(d, b4, d2, b5, f, b6, b3, b2, o, b7, b)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(d.d, b3);
                        jSONObject.put("last_aaid", b2);
                        jSONObject.put("d", d);
                        jSONObject.put("i", d2);
                        jSONObject.put("m", f);
                        jSONObject.put("f", Integer.valueOf(c));
                        jSONObject.put("g", o);
                        o.a("aaid", "upload content:" + jSONObject.toString());
                        LogEvent logEvent = new LogEvent(this.a.l, "com.miui.analytics", f.q, jSONObject.toString());
                        List arrayList = new ArrayList();
                        arrayList.add(logEvent);
                        List a = new b(this.a.l, arrayList).a();
                        if (a == null || a.isEmpty()) {
                            o.a("aaid", "upload new AAID failed!");
                            return;
                        }
                        o.a("aaid", "upload new AAID successfully");
                        this.a.m.a().putLong(u.F, System.currentTimeMillis()).putString("last_aaid", b3).putString(u.C, d).putString(u.D, d2).putString(u.E, f).putString(u.H, o).apply();
                        if (n.a() && Analytics.getVersionName().equals(d.j) && !this.a.m.b(u.G, false)) {
                            this.a.m.a(u.G, true);
                            return;
                        }
                        return;
                    }
                    o.a("aaid", "does not meet the upload requirements");
                    return;
                }
                o.a("aaid", "Network is not accessible or interval is not meet");
            } catch (Throwable e) {
                Log.e("aaid", "upload aaid exception: ", e);
            }
        }
    };
    private Context l;
    private v m;

    private d(Context context) {
        this.l = c.a(context);
        this.m = new v(this.l, u.d, "aaid");
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (k == null) {
                k = new d(context);
            }
            dVar = k;
        }
        return dVar;
    }

    public void a() {
        ab.a(this.a);
    }

    private boolean a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, long j) {
        if (n.a() && Analytics.getVersionName().equals(j) && (!this.m.b(u.G) || !this.m.b(u.G, false))) {
            return true;
        }
        if (ac.a(j, g.o())) {
            o.a("aaid", "The interval of forcing upload analytics_aaid is meet.");
            return true;
        } else if (TextUtils.equals(str, str2) && TextUtils.equals(str3, str4) && TextUtils.equals(str5, str6) && ((TextUtils.isEmpty(str7) || TextUtils.equals(str7, str8)) && (TextUtils.isEmpty(str9) || TextUtils.equals(str9, str10)))) {
            return false;
        } else {
            return true;
        }
    }
}
