package com.miui.analytics.internal.collection;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.p;

public class g {
    private static final String a = "UL";
    private static volatile g c;
    private static final Object e = new Object();
    private p b = p.a(this.d);
    private Context d;

    private g(Context context) {
        this.d = c.a(context);
    }

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (c == null) {
                c = new g(context);
            }
            gVar = c;
        }
        return gVar;
    }

    public void a() {
        o.a(a, "upload L...");
        if (!n.a(this.d, a) && af.a()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (g.e) {
                        try {
                            if (ac.b(this.a.b.c())) {
                                o.a(g.a, "L uploaded today, skip it...");
                            } else {
                                k.a(this.a.d).a(new LogEvent(this.a.d, "com.miui.analytics", f.h, this.a.b.a()));
                                this.a.b.g();
                            }
                        } catch (Throwable th) {
                            Log.e(o.a(g.a), "locationUpload exception" + th.getMessage());
                        }
                    }
                }
            });
        }
    }
}
