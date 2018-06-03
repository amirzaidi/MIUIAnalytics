package com.miui.analytics.internal.c;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.z;

public class a {
    private static final String a = "DeliverFactory";

    public static void a(Context context, LogEvent logEvent) {
        a(context, logEvent, null);
    }

    public static void a(final Context context, final LogEvent logEvent, final b bVar) {
        if (logEvent != null) {
            try {
                if (z.C()) {
                    ab.a(new Runnable() {
                        public void run() {
                            a.c(context, logEvent, bVar);
                        }
                    });
                } else {
                    c(context, logEvent, bVar);
                }
            } catch (Throwable e) {
                Log.e(o.a(a), "createDeliver e", e);
            }
        }
    }

    private static void c(Context context, LogEvent logEvent, b bVar) {
        try {
            h.a(context).a(logEvent.c());
            com.miui.analytics.internal.c.a.a a = h.a(context).a(context, logEvent, bVar);
            o.a(a, "deliver:" + a.getClass().getName());
            a.a(logEvent);
        } catch (Throwable e) {
            Log.e(o.a(a), "createDeliverSync e", e);
        }
    }
}
