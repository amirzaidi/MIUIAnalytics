package com.miui.analytics.internal.collection;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.c.b.b;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.policy.j;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import java.util.Calendar;
import org.json.JSONObject;

public class e {
    private static final String b = "UA";
    private static volatile e c;
    private static final Object e = new Object();
    a a = new a(this, this.d);
    private Context d;

    private class a {
        final /* synthetic */ e a;
        private v b;

        a(e eVar, Context context) {
            this.a = eVar;
            a(context);
        }

        private void a(Context context) {
            this.b = new v(context, u.d);
        }

        public void a(long j) {
            this.b.a(u.L, j);
        }

        public long a() {
            return this.b.b(u.L, 0);
        }

        public void a(boolean z) {
            Editor a = this.b.a();
            a.putInt(u.I, 0);
            a.putLong(u.K, 0);
            a.putLong(u.J, 0);
            a.apply();
            if (z) {
                b();
            }
        }

        public void b() {
            int b = this.b.b(u.I, 0) | (1 << (Calendar.getInstance().get(7) - 1));
            Editor a = this.b.a();
            a.putInt(u.I, b);
            if (this.b.b(u.J, 0) == 0) {
                a.putLong(u.J, System.currentTimeMillis());
            }
            a.apply();
        }

        public void c() {
            Long valueOf = Long.valueOf(this.b.b(u.J, 0));
            if (valueOf.longValue() > 0) {
                Long valueOf2 = Long.valueOf(System.currentTimeMillis());
                Editor a = this.b.a();
                if (valueOf2.longValue() > valueOf.longValue()) {
                    Long l;
                    int b = this.b.b(u.I, 0);
                    Calendar instance = Calendar.getInstance();
                    int i = b;
                    for (l = valueOf2; l.longValue() >= valueOf.longValue(); l = Long.valueOf(l.longValue() - ac.b)) {
                        instance.setTimeInMillis(l.longValue());
                        i |= 1 << (instance.get(7) - 1);
                    }
                    a.putInt(u.I, i);
                    l = Long.valueOf(this.b.b(u.K, 0));
                    a.putLong(u.K, (((valueOf2.longValue() - valueOf.longValue()) / 1000) / 60) + l.longValue());
                }
                a.putLong(u.J, 0);
                a.apply();
            }
        }

        public int d() {
            return this.b.b(u.I, 0);
        }

        public long e() {
            return this.b.b(u.K, 0);
        }
    }

    private e(Context context) {
        this.d = c.a(context);
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                c = new e(context);
            }
            eVar = c;
        }
        return eVar;
    }

    private boolean e() {
        double u = g.u();
        o.a(b, "sample:" + u);
        if (u <= 0.0d) {
            return false;
        }
        if (new j(u).a()) {
            o.a(b, "sample apply");
            return true;
        }
        o.a(b, "sample not apply");
        return false;
    }

    public void a() {
        if (r.a(this.d)) {
            this.a.b();
        } else {
            this.a.c();
        }
    }

    private String f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f.J, f.L);
            f.a(this.d, jSONObject);
        } catch (Throwable e) {
            Log.e(o.a(b), "getContent e", e);
        }
        return jSONObject.toString();
    }

    public void b() {
        if (!g() || e()) {
            c();
            b.a(this.d).a();
            return;
        }
        com.miui.analytics.internal.b.a(this.d);
    }

    private boolean g() {
        int c = ac.c(System.currentTimeMillis());
        int[] x = g.x();
        if (x != null) {
            for (int i : x) {
                if (i == c) {
                    return true;
                }
            }
            return false;
        } else if (c == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void c() {
        if (!n.e()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (e.e) {
                        try {
                            if (ac.b(this.a.a.a())) {
                                o.a(e.b, "uploaded active today, skip it.");
                            } else {
                                String a = this.a.f();
                                k.a(this.a.d).a(new LogEvent(this.a.d, "com.miui.analytics", f.f, a));
                                this.a.a.a(r.a(this.a.d));
                                this.a.a.a(System.currentTimeMillis());
                                o.a(e.b, "uploading active info: " + a);
                            }
                        } catch (Throwable th) {
                            ae.a(this.a.d, e.b, "upload active task exception:", th);
                        }
                    }
                }
            });
        }
    }
}
