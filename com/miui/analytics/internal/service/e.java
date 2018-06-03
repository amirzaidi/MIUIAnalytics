package com.miui.analytics.internal.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.service.HttpRequest.Method;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.b;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.p;
import org.json.JSONObject;

public class e extends h<JSONObject> {
    public static final String a = "status";
    public static final String b = "data";
    public static final String c = "message";
    public static final String d = "https://mds.mistat.xiaomi.com/api/query/list/range";
    private static final String k = "MdsConfigServer";
    private static final String l = "c";
    private static final String m = "a";
    private static final String n = "content";
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static final int r = 3;
    public String e;
    private Context s;

    public e(String str, Context context) {
        super(d);
        this.s = c.a(context);
        this.e = str;
    }

    public HttpRequest a() {
        HttpRequest httpRequest = new HttpRequest(this.i);
        httpRequest.a(Method.POST);
        long d = d();
        String e = e();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("c", this.e);
            jSONObject.put(m, n.b(this.s));
            httpRequest.a(n, b.a(jSONObject.toString(), af.b(e)));
            httpRequest.a("ts", String.valueOf(d));
            httpRequest.a(h.g, e);
            httpRequest.b("Accept-Encoding", "gzip, deflate");
            o.a(k, String.format("request url:%s, requestParam:%s", new Object[]{httpRequest.e(), httpRequest.c().toString()}));
        } catch (Throwable e2) {
            Log.e(o.a(k), "buildHttpRequest exception;", e2);
        }
        return httpRequest;
    }

    public g<JSONObject> a(d dVar) {
        String b = b(dVar);
        if (TextUtils.isEmpty(b)) {
            o.a(k, "http response is empty");
        } else {
            try {
                JSONObject jSONObject = new JSONObject(b);
                int optInt = jSONObject.optInt("status", -1);
                String optString = jSONObject.optString(c);
                o.a(k, String.format("responseStatus:%d, %s", new Object[]{Integer.valueOf(optInt), optString}));
                switch (optInt) {
                    case 0:
                        Object optJSONObject = jSONObject.optJSONObject(b);
                        if (optJSONObject != null) {
                            return g.a(optJSONObject);
                        }
                        break;
                    case 1:
                    case 2:
                        break;
                    case 3:
                        p.a(this.s).d();
                        break;
                    default:
                        break;
                }
            } catch (Throwable e) {
                Log.e(o.a(k), "fail to parseHttpResponse: " + b, e);
            }
        }
        return g.a(new AnalyticsError());
    }
}
