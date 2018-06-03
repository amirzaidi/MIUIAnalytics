package com.miui.analytics.internal.collection;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.service.AnalyticsError;
import com.miui.analytics.internal.service.d;
import com.miui.analytics.internal.service.g;
import com.miui.analytics.internal.service.j;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class c {
    public static final String a = "RegisterDeviceServer";
    public static final String b = "https://miuiboot.tracking.miui.com/bootlog";
    private static final long c = 5;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = -1;
    private static final String g = "code";
    private static final String h = "code";
    private static volatile c i;

    public static c a() {
        if (i == null) {
            synchronized (c.class) {
                if (i == null) {
                    i = new c();
                }
            }
        }
        return i;
    }

    private c() {
    }

    private String b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f.J, f.K);
            f.a(context, jSONObject);
        } catch (Throwable e) {
            Log.e(o.a(a), "register device getContent exception:", e);
        }
        o.a(a, "register device active info: " + jSONObject.toString());
        return jSONObject.toString();
    }

    public Bundle a(final Context context) {
        Bundle bundle = new Bundle();
        if (n.a()) {
            bundle.putInt("code", -1);
            return bundle;
        }
        Bundle bundle2;
        Object futureTask = new FutureTask(new Callable<Bundle>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ Object call() throws Exception {
                return a();
            }

            public Bundle a() throws Exception {
                final Bundle bundle = new Bundle();
                new j(this, context, c.b, new LogEvent(context, "com.miui.analytics", f.n, this.b.b(context))) {
                    final /* synthetic */ AnonymousClass1 b;

                    public g<Void> a(d dVar) {
                        Object b = b(dVar);
                        if (TextUtils.isEmpty(b)) {
                            bundle.putInt("code", 3);
                            Log.w(o.a(c.a), "http response is empty");
                        } else {
                            try {
                                JSONObject jSONObject = new JSONObject(b);
                                if (jSONObject.has("code")) {
                                    bundle.putInt("code", jSONObject.getInt("code"));
                                } else {
                                    bundle.putInt("code", 3);
                                }
                                if (jSONObject.optInt("code") == 1) {
                                    return g.a(null);
                                }
                            } catch (Exception e) {
                                Log.e(o.a(c.a), String.format("http response:%s, parseHttpResponse exception:%s", new Object[]{b, e.toString()}));
                            }
                        }
                        return g.a(new AnalyticsError());
                    }
                }.c();
                return bundle;
            }
        });
        new Thread(futureTask).start();
        try {
            bundle2 = (Bundle) futureTask.get(c, TimeUnit.SECONDS);
        } catch (Throwable e) {
            bundle.putInt("code", 2);
            Log.e(o.a(a), "register device exception:", e);
            bundle2 = bundle;
        }
        return bundle2;
    }
}
