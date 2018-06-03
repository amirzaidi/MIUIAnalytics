package com.miui.analytics.district;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.service.e;
import com.miui.analytics.internal.service.g;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.q;
import org.json.JSONObject;

public class c {
    private static final String a = "MDS";

    public static void a(Context context, long j, String str, String str2, String str3, b bVar) {
        final String str4 = str2;
        final Context context2 = context;
        final long j2 = j;
        final String str5 = str;
        final String str6 = str3;
        final b bVar2 = bVar;
        ab.a(new Runnable() {
            public void run() {
                try {
                    g c = new e(str4, context2).c();
                    if (c.a != null) {
                        boolean a = q.a(context2).a(j2, str5, (JSONObject) c.a, str6);
                        o.a(c.a, String.format("requestCode=%d, packageName=%s， timeRange=%s, result=%b", new Object[]{Long.valueOf(j2), str5, str6, Boolean.valueOf(a)}));
                        bVar2.a(a);
                        return;
                    }
                    o.a(c.a, String.format("serverResult=null, message:%s", new Object[]{c.b}));
                } catch (Throwable e) {
                    Log.e(o.a(c.a), "registerMdsRequest:", e);
                }
            }
        });
    }

    public static String a(Context context, long j, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("matched", q.a(context).a(j, str));
            o.a(a, String.format("checkRealTimeMds requestCode: %d, isMatch: %b", new Object[]{Long.valueOf(j), Boolean.valueOf(r0)}));
        } catch (Throwable e) {
            Log.e(o.a(a), "checkRealTimeMds exception:", e);
        }
        return jSONObject.toString();
    }
}
