package com.miui.analytics.internal.c.b;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.c.b;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import org.json.JSONObject;

public abstract class a {
    private static final String c = "AbstractGather";
    protected Context a;
    protected b b;
    private String d;
    private String e;
    private Object f = new Object();

    private class a implements Runnable {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public void run() {
            try {
                synchronized (this.a.f) {
                    if (this.a.b != null) {
                        this.a.b.a();
                    }
                    boolean b = this.a.f();
                    if (this.a.b != null) {
                        this.a.b.a(b);
                    }
                    if (b) {
                        b = this.a.b();
                        if (this.a.b != null) {
                            this.a.b.b(b);
                        }
                        if (b) {
                            JSONObject c = this.a.c();
                            if (this.a.b != null) {
                                this.a.b.a(c);
                            }
                            if (c != null && c.length() > 0) {
                                this.a.a(c);
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                Log.e(o.a(a.c), "GatherRunnable e", e);
            }
        }
    }

    protected abstract boolean b();

    protected abstract JSONObject c();

    protected abstract String d();

    protected abstract String e();

    public a(Context context) {
        this.a = c.a(context);
    }

    public void a() {
        ab.a(new a());
    }

    private boolean f() {
        try {
            if (n.e()) {
                return false;
            }
            o.a(c, "not catBuild");
            if (n.a(this.a, c)) {
                return false;
            }
            h a = h.a(this.a);
            if (!(a.b(d(), e()).a() && a.c(d(), e()).a(this.a))) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Log.e(o.a(c), "checkCommonGatherConditions e", e);
        }
    }

    protected void a(JSONObject jSONObject) {
        try {
            this.d = d();
            this.e = e();
            com.miui.analytics.internal.c.a.a(this.a, new LogEvent(this.a, this.d, this.e, jSONObject.toString()), this.b);
        } catch (Throwable e) {
            Log.e(o.a(c), "toDeliver e", e);
        }
    }
}
