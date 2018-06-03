package com.miui.analytics.internal.d;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.a.b;
import com.miui.analytics.internal.b.g;
import com.miui.analytics.internal.collection.a;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.util.NetState;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.v;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class c {
    private static final String b = "ReporterService";
    private static final boolean c = false;
    private static volatile c d = null;
    private static final String e = "event";
    private static final String f = "reportUploadStatus";
    private static final String g = "com.miui.analytics";
    private static final String h = "eid";
    private static final String i = "counter";
    private static final String j = "http://api.ad.xiaomi.com/video/uploadLog?appKey=PHONE_VIDEO";
    private static final String k = "success";
    private static final String l = "failure";
    public HashMap<String, List<String>> a = new HashMap();
    private Context m;
    private g n;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (d == null) {
                d = new c(context);
            }
            cVar = d;
        }
        return cVar;
    }

    private c(Context context) {
        this.m = com.miui.analytics.internal.util.c.a(context);
        this.n = g.a(context);
        a();
    }

    private void a() {
        ab.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    String str;
                    v vVar = new v(this.a.m, "ReporterService");
                    Map b = vVar.b();
                    if (!(b == null || b.isEmpty())) {
                        for (Entry entry : b.entrySet()) {
                            str = (String) entry.getKey();
                            String str2 = (String) entry.getValue();
                            if (!TextUtils.isEmpty(str2)) {
                                this.a.n.a(str, str2);
                            }
                        }
                        vVar.c();
                        v.a(this.a.m, "ReporterService");
                    }
                    String a = this.a.a(new Date(System.currentTimeMillis() - ac.b));
                    String a2 = this.a.a(new Date(System.currentTimeMillis()));
                    List<Pair> a3 = this.a.n.a();
                    if (a3 != null) {
                        for (Pair pair : a3) {
                            str = (String) pair.second;
                            if (str == null || !(str.contains(a) || str.contains(a2))) {
                                this.a.n.b((String) pair.first);
                            } else {
                                JSONObject jSONObject = new JSONObject(str);
                                JSONObject optJSONObject = jSONObject.optJSONObject(a);
                                this.a.n.a((String) pair.first, new JSONObject().put(a, optJSONObject).put(a2, jSONObject.optJSONObject(a2)).toString());
                            }
                        }
                    }
                } catch (Throwable e) {
                    Log.e(o.a("ReporterService"), "init e", e);
                }
            }
        });
    }

    private String a(Date date) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return (instance.get(2) + 1) + "-" + instance.get(5);
    }

    private boolean c(LogEvent logEvent) {
        if (logEvent == null || f.s.equals(logEvent.b())) {
            return false;
        }
        return true;
    }

    private boolean c(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith(j)) {
            return false;
        }
        return true;
    }

    public synchronized void a(LogEvent logEvent, String str) {
        if (!c(logEvent) || d(d(logEvent))) {
        }
    }

    public synchronized void a(String str, String str2) {
        if (c(str) && r.b(this.m) && !d(e(str))) {
        }
    }

    public synchronized void a(LogEvent logEvent) {
        if (c(logEvent)) {
            a(d(logEvent), true);
        }
    }

    public synchronized void a(String str) {
        if (c(str)) {
            a(e(str), true);
        }
    }

    public synchronized void b(LogEvent logEvent) {
        if (c(logEvent) && r.b(this.m)) {
            a(d(logEvent), false);
        }
    }

    public synchronized void b(String str) {
        if (c(str) && r.b(this.m)) {
            a(e(str), false);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r11, boolean r12) {
        /*
        r10 = this;
        r8 = 1;
        r0 = new java.util.Date;	 Catch:{ Exception -> 0x0076 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0076 }
        r4 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r2 = r2 - r4;
        r0.<init>(r2);	 Catch:{ Exception -> 0x0076 }
        r0 = r10.a(r0);	 Catch:{ Exception -> 0x0076 }
        r1 = new java.util.Date;	 Catch:{ Exception -> 0x0076 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0076 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0076 }
        r2 = r10.a(r1);	 Catch:{ Exception -> 0x0076 }
        r1 = r10.n;	 Catch:{ Exception -> 0x0076 }
        r3 = r1.a(r11);	 Catch:{ Exception -> 0x0076 }
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0076 }
        r4.<init>();	 Catch:{ Exception -> 0x0076 }
        r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0076 }
        r1.<init>();	 Catch:{ Exception -> 0x0076 }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x0076 }
        if (r5 != 0) goto L_0x007f;
    L_0x0036:
        r5 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0076 }
        r5.<init>(r3);	 Catch:{ Exception -> 0x0076 }
        r0 = r5.optJSONObject(r0);	 Catch:{ Exception -> 0x0076 }
        if (r0 == 0) goto L_0x0044;
    L_0x0041:
        r10.a(r11, r0);	 Catch:{ Exception -> 0x0076 }
    L_0x0044:
        r0 = r5.optJSONObject(r2);	 Catch:{ Exception -> 0x0076 }
        if (r0 == 0) goto L_0x007f;
    L_0x004a:
        r4.put(r2, r0);	 Catch:{ Exception -> 0x0076 }
        if (r12 == 0) goto L_0x0067;
    L_0x004f:
        r1 = "success";
        r2 = "success";
        r6 = 0;
        r2 = r0.optLong(r2, r6);	 Catch:{ Exception -> 0x0076 }
        r2 = r2 + r8;
        r0.put(r1, r2);	 Catch:{ Exception -> 0x0076 }
    L_0x005d:
        r0 = r10.n;	 Catch:{ Exception -> 0x0076 }
        r1 = r4.toString();	 Catch:{ Exception -> 0x0076 }
        r0.a(r11, r1);	 Catch:{ Exception -> 0x0076 }
    L_0x0066:
        return;
    L_0x0067:
        r1 = "failure";
        r2 = "failure";
        r6 = 0;
        r2 = r0.optLong(r2, r6);	 Catch:{ Exception -> 0x0076 }
        r2 = r2 + r8;
        r0.put(r1, r2);	 Catch:{ Exception -> 0x0076 }
        goto L_0x005d;
    L_0x0076:
        r0 = move-exception;
        r1 = "ReporterService";
        r2 = "handleSentEvent";
        com.miui.analytics.internal.util.o.b(r1, r2, r0);
        goto L_0x0066;
    L_0x007f:
        r0 = r1;
        goto L_0x004a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.d.c.a(java.lang.String, boolean):void");
    }

    private void a(String str, JSONObject jSONObject) {
    }

    private boolean d(String str) {
        NetState c = r.c(this.m);
        if (c == null || c == NetState.NONE || TextUtils.isEmpty(str)) {
            return false;
        }
        return true;
    }

    private String d(LogEvent logEvent) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ck", logEvent.b());
            if (!TextUtils.isEmpty(logEvent.g())) {
                JSONObject jSONObject2 = new JSONObject(logEvent.g());
                CharSequence optString = jSONObject2.optString("_action_", null);
                if (!TextUtils.isEmpty(optString)) {
                    jSONObject.put(a.k, optString);
                }
                optString = jSONObject2.optString("_category_", null);
                if (!TextUtils.isEmpty(optString)) {
                    jSONObject.put("category", optString);
                }
                CharSequence optString2 = jSONObject2.optString("_event_id_", null);
                if (!TextUtils.isEmpty(optString2)) {
                    jSONObject.put("event_id", optString2);
                }
            }
        } catch (Exception e) {
        }
        return jSONObject.toString();
    }

    private String e(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object str2;
            if (str2.startsWith(j)) {
                str2 = str2.substring(0, j.length());
            }
            jSONObject.put(b.b, str2);
        } catch (Exception e) {
        }
        return jSONObject.toString();
    }
}
