package com.miui.analytics.internal.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.e;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.policy.PolicyGroup;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.z;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class a extends h<PolicyGroup> {
    public static final String a = "success";
    public static final String b = "detail";
    public static final String c = "https://sdkconfig.ad.xiaomi.com/api/detail/";
    private static final String e = "ConfigServer";
    public String d;
    private Context k;

    public a(Context context, String str) {
        super(c);
        this.k = context;
        this.d = af.a(str).toLowerCase(Locale.getDefault());
    }

    public HttpRequest a() {
        HttpRequest httpRequest = new HttpRequest(this.i + this.d);
        long d = d();
        String e = e();
        httpRequest.a("ts", "" + d);
        httpRequest.a(h.g, e);
        String b = e.b(this.k);
        String m = z.m();
        String c = z.c();
        String d2 = z.d();
        String d3 = z.d(this.k);
        String k = z.k();
        int d4 = r.d(this.k);
        String i = z.i();
        httpRequest.a("cv", b);
        httpRequest.a(f.U, m);
        httpRequest.a("v", c);
        httpRequest.a(f.X, d2);
        if (!n.a()) {
            httpRequest.a("i", d3);
        }
        httpRequest.a(f.T, k);
        httpRequest.a("n", d4 + "");
        httpRequest.a(f.S, i);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("packageNameMd5", this.d));
        arrayList.add(new BasicNameValuePair("ts", "" + d));
        arrayList.add(new BasicNameValuePair(h.g, e));
        arrayList.add(new BasicNameValuePair("cv", b));
        arrayList.add(new BasicNameValuePair(f.U, m));
        arrayList.add(new BasicNameValuePair("v", c));
        arrayList.add(new BasicNameValuePair(f.X, d2));
        if (!n.a()) {
            arrayList.add(new BasicNameValuePair("i", d3));
        }
        arrayList.add(new BasicNameValuePair(f.T, k));
        arrayList.add(new BasicNameValuePair("n", d4 + ""));
        arrayList.add(new BasicNameValuePair(f.S, i));
        httpRequest.a(h.h, a(arrayList));
        httpRequest.b("Accept-Encoding", "gzip, deflate");
        o.a(e, "request url: " + httpRequest.e());
        return httpRequest;
    }

    public g<PolicyGroup> a(d dVar) {
        String b = b(dVar);
        if (TextUtils.isEmpty(b)) {
            Log.w(o.a(e), "HttpResponse is empty");
        } else {
            try {
                JSONObject jSONObject = new JSONObject(b);
                if (jSONObject.getBoolean(a)) {
                    jSONObject = jSONObject.getJSONObject(b);
                    if (jSONObject != null) {
                        return g.a(new PolicyGroup(jSONObject));
                    }
                }
            } catch (Throwable e) {
                Log.d(o.a(e), "http response:" + b);
                Log.e(o.a(e), "Fail to parseHttpResponse: ", e);
            }
        }
        return g.a(new AnalyticsError());
    }
}
