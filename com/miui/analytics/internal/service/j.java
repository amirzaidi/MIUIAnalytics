package com.miui.analytics.internal.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.LogEvent.IdType;
import com.miui.analytics.internal.LogEvent.LogType;
import com.miui.analytics.internal.c.b;
import com.miui.analytics.internal.collection.i;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class j extends h<Void> {
    private static final String a = "TrackingServer";
    public static final String c = "http://tracking.miui.com/track/v1";
    private List<LogEvent> b = new ArrayList();
    private Context d;
    private b e;
    private boolean k = true;
    private int l = LogType.TYPE_EVENT.a();
    private int m = IdType.TYPE_DEFAULT.a();
    private String n = "";

    public j(Context context, String str, LogEvent logEvent) {
        super(c(str));
        this.d = context;
        if (logEvent != null) {
            this.b.add(logEvent);
        }
    }

    public j(Context context, String str, List<LogEvent> list) {
        super(c(str));
        this.d = context;
        if (list != null) {
            this.b.addAll(list);
        }
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void a(int i) {
        this.l = i;
    }

    public void b(int i) {
        this.m = i;
    }

    public void b(String str) {
        this.n = str;
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    private static String c(String str) {
        return str != null ? str : c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.analytics.internal.service.HttpRequest a() {
        /*
        r12 = this;
        r1 = 1;
        r2 = 0;
        r0 = r12.e;
        if (r0 == 0) goto L_0x000d;
    L_0x0006:
        r0 = r12.e;
        r3 = r12.b;
        r0.e(r3);
    L_0x000d:
        r3 = new com.miui.analytics.internal.service.HttpRequest;
        r0 = r12.i;
        r3.<init>(r0);
        r0 = com.miui.analytics.internal.service.HttpRequest.Method.POST;
        r3.a(r0);
        r4 = r12.d();	 Catch:{ Exception -> 0x0115 }
        r6 = r12.e();	 Catch:{ Exception -> 0x0115 }
        r7 = r12.f();	 Catch:{ Exception -> 0x0115 }
        r0 = "TrackingServer";
        r8 = "eventType = %s\nurl = %s \npayload = %s";
        r9 = 3;
        r9 = new java.lang.Object[r9];	 Catch:{ Exception -> 0x0115 }
        r10 = 0;
        r11 = r12.l;	 Catch:{ Exception -> 0x0115 }
        r11 = java.lang.Integer.valueOf(r11);	 Catch:{ Exception -> 0x0115 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0115 }
        r10 = 1;
        r11 = r12.i;	 Catch:{ Exception -> 0x0115 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0115 }
        r10 = 2;
        r9[r10] = r7;	 Catch:{ Exception -> 0x0115 }
        r8 = java.lang.String.format(r8, r9);	 Catch:{ Exception -> 0x0115 }
        com.miui.analytics.internal.util.o.a(r0, r8);	 Catch:{ Exception -> 0x0115 }
        r0 = "";
        r8 = r12.d;	 Catch:{ Exception -> 0x0115 }
        r8 = com.miui.analytics.internal.policy.h.a(r8);	 Catch:{ Exception -> 0x0115 }
        r8 = r8.d();	 Catch:{ Exception -> 0x0115 }
        if (r8 == 0) goto L_0x0122;
    L_0x0052:
        r8 = r7.length();	 Catch:{ Exception -> 0x0115 }
        r9 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
        if (r8 <= r9) goto L_0x0122;
    L_0x005b:
        r8 = com.miui.analytics.internal.util.m.a(r7);	 Catch:{ Exception -> 0x0115 }
        if (r8 == 0) goto L_0x0122;
    L_0x0061:
        r0 = 10;
        r0 = android.util.Base64.encodeToString(r8, r0);	 Catch:{ Exception -> 0x0115 }
        if (r0 == 0) goto L_0x0122;
    L_0x0069:
        r8 = r0.length();	 Catch:{ Exception -> 0x0115 }
        r8 = (float) r8;	 Catch:{ Exception -> 0x0115 }
        r9 = r7.length();	 Catch:{ Exception -> 0x0115 }
        r9 = (float) r9;	 Catch:{ Exception -> 0x0115 }
        r8 = r8 / r9;
        r9 = "TrackingServer";
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0115 }
        r10.<init>();	 Catch:{ Exception -> 0x0115 }
        r11 = "compress = ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x0115 }
        r10 = r10.append(r8);	 Catch:{ Exception -> 0x0115 }
        r10 = r10.toString();	 Catch:{ Exception -> 0x0115 }
        com.miui.analytics.internal.util.o.a(r9, r10);	 Catch:{ Exception -> 0x0115 }
        r9 = 1050253722; // 0x3e99999a float:0.3 double:5.188942835E-315;
        r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1));
        if (r8 >= 0) goto L_0x0122;
    L_0x0093:
        r2 = "";
        if (r1 == 0) goto L_0x00fe;
    L_0x0097:
        r1 = "value";
        r3.a(r1, r0);	 Catch:{ Exception -> 0x0115 }
        r1 = "gzip";
        r2 = "1";
        r3.b(r1, r2);	 Catch:{ Exception -> 0x0115 }
    L_0x00a3:
        r1 = 3;
        r1 = new org.apache.http.NameValuePair[r1];	 Catch:{ Exception -> 0x0115 }
        r2 = 0;
        r7 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x0115 }
        r8 = "value";
        r7.<init>(r8, r0);	 Catch:{ Exception -> 0x0115 }
        r1[r2] = r7;	 Catch:{ Exception -> 0x0115 }
        r0 = 1;
        r2 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x0115 }
        r7 = "ts";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0115 }
        r8.<init>();	 Catch:{ Exception -> 0x0115 }
        r8 = r8.append(r4);	 Catch:{ Exception -> 0x0115 }
        r9 = "";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x0115 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x0115 }
        r2.<init>(r7, r8);	 Catch:{ Exception -> 0x0115 }
        r1[r0] = r2;	 Catch:{ Exception -> 0x0115 }
        r0 = 2;
        r2 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x0115 }
        r7 = "nonce";
        r2.<init>(r7, r6);	 Catch:{ Exception -> 0x0115 }
        r1[r0] = r2;	 Catch:{ Exception -> 0x0115 }
        r0 = r12.a(r1);	 Catch:{ Exception -> 0x0115 }
        r1 = "sign";
        r3.a(r1, r0);	 Catch:{ Exception -> 0x0115 }
        r0 = "ts";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0115 }
        r1.<init>();	 Catch:{ Exception -> 0x0115 }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0115 }
        r2 = "";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0115 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0115 }
        r3.a(r0, r1);	 Catch:{ Exception -> 0x0115 }
        r0 = "nonce";
        r3.a(r0, r6);	 Catch:{ Exception -> 0x0115 }
    L_0x00fd:
        return r3;
    L_0x00fe:
        r0 = r7.getBytes();	 Catch:{ Exception -> 0x0115 }
        r1 = 10;
        r0 = android.util.Base64.encodeToString(r0, r1);	 Catch:{ Exception -> 0x0115 }
        r1 = "value";
        r3.a(r1, r0);	 Catch:{ Exception -> 0x0115 }
        r1 = "gzip";
        r2 = "0";
        r3.b(r1, r2);	 Catch:{ Exception -> 0x0115 }
        goto L_0x00a3;
    L_0x0115:
        r0 = move-exception;
        r1 = "TrackingServer";
        r1 = com.miui.analytics.internal.util.o.a(r1);
        r2 = "buildHttpRequest exception: ";
        android.util.Log.e(r1, r2, r0);
        goto L_0x00fd;
    L_0x0122:
        r1 = r2;
        goto L_0x0093;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.service.j.a():com.miui.analytics.internal.service.HttpRequest");
    }

    private String f() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject a = i.a(this.d, Boolean.valueOf(this.k), this.l, this.m, this.n);
            a.put(i.B, "" + System.currentTimeMillis());
            jSONObject.put("H", a);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.b.size(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                LogEvent logEvent = (LogEvent) this.b.get(i);
                JSONObject a2 = i.a(logEvent);
                i.a(a, a2);
                jSONObject2.put("H", a2);
                jSONObject2.put("B", i.b(logEvent));
                jSONArray.put(jSONObject2);
                i.a(this.d).a(logEvent.c(), logEvent.b(), String.valueOf(r.d(this.d)), jSONObject2.toString(), this.i, logEvent.e());
            }
            jSONObject.put("B", jSONArray);
        } catch (Throwable e) {
            Log.e(o.a(a), "buildPayload exception: ", e);
        }
        return jSONObject.toString();
    }

    public g<Void> a(d dVar) {
        String b = b(dVar);
        if (TextUtils.isEmpty(b)) {
            Log.w(o.a(a), "http response is empty");
        } else {
            try {
                if (new JSONObject(b).optInt("code") == 1) {
                    if (this.e != null) {
                        this.e.a(true, this.b);
                    }
                    return g.a(null);
                }
            } catch (Throwable e) {
                Log.d(o.a(a), "http response:" + b);
                Log.e(o.a(a), "parseHttpResponse exception: ", e);
            }
        }
        if (this.e != null) {
            this.e.a(false, this.b);
        }
        return g.a(new AnalyticsError());
    }
}
