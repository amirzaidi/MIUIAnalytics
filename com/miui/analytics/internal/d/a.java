package com.miui.analytics.internal.d;

import android.content.Context;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import java.util.Random;

public class a {
    private static volatile a a;
    private Context b;
    private v c = new v(this.b, u.d, u.h);

    private a(Context context) {
        this.b = c.a(context);
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a(context);
            }
            aVar = a;
        }
        return aVar;
    }

    private synchronized long b() {
        return this.c.b("time", 0);
    }

    private synchronized void a(long j) {
        this.c.a("time", j);
    }

    public void a() {
        if (!n.a(this.b, "Monitor") && c()) {
            a(System.currentTimeMillis());
            d();
        }
    }

    private boolean c() {
        if (System.currentTimeMillis() - b() < ac.c + (new Random().nextLong() % (ac.c / 2))) {
            return false;
        }
        return true;
    }

    private void d() {
        k.a(this.b).a(new LogEvent(this.b, "com.miui.analytics", f.m, ""));
    }
}
