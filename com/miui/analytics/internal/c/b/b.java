package com.miui.analytics.internal.c.b;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.c.c;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import java.util.List;
import org.json.JSONObject;

public class b extends a {
    private static final String c = "ActiveGather";
    private static volatile b d;
    private v e;
    private c f;

    private b(Context context) {
        super(context);
        try {
            this.f = c.a(context);
            this.b = new com.miui.analytics.internal.c.b(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a() {
                    o.a(b.c, "onGatherStart");
                    this.a.f.a("01", "10", true);
                }

                public void a(boolean z) {
                    o.a(b.c, "onCheckCommonGatherConditions:" + z);
                    this.a.f.a("01", c.r, z);
                }

                public void b(boolean z) {
                    o.a(b.c, "onCheckCustomGatherConditions:" + z);
                    this.a.f.a("01", c.s, z);
                }

                public void a(JSONObject jSONObject) {
                    o.a(b.c, "onGatherFinished");
                    this.a.e.a(u.M, System.currentTimeMillis());
                    c a = this.a.f;
                    String str = "01";
                    String str2 = c.t;
                    boolean z = jSONObject != null && jSONObject.length() > 0;
                    a.a(str, str2, z);
                }

                public void a(List<LogEvent> list) {
                    o.a(b.c, "onAcceptEvents:" + (list == null ? "" : list.toString()));
                }

                public void b(List<LogEvent> list) {
                    o.a(b.c, "onDeliverEvents:" + (list == null ? "" : list.toString()));
                }

                public void c(List<LogEvent> list) {
                    o.a(b.c, "onPackStart :" + (list == null ? "" : list.toString()));
                }

                public void d(List<LogEvent> list) {
                    o.a(b.c, "onCheckPackConditions:" + (list == null ? "" : list.toString()));
                }

                public void e(List<LogEvent> list) {
                    o.a(b.c, "onUploadStart");
                    if (list != null && list.size() > 0) {
                        for (LogEvent b : list) {
                            if (f.g.equals(b.b())) {
                                this.a.f.a("01", "40", true);
                            }
                        }
                    }
                }

                public void a(boolean z, List<LogEvent> list) {
                    o.a(b.c, "onUploadResponse:" + z + " , " + (list == null ? "" : list.toString()));
                    if (list != null && list.size() > 0) {
                        for (LogEvent b : list) {
                            if (f.g.equals(b.b())) {
                                this.a.f.a("01", c.v, z);
                            }
                        }
                    }
                }
            };
            this.e = new v(this.a, u.d);
        } catch (Throwable e) {
            Log.e(o.a(c), "constructor e", e);
        }
    }

    public static b a(Context context) {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b(context);
                }
            }
        }
        return d;
    }

    protected boolean b() {
        boolean b = ac.b(this.e.b(u.M, 0));
        if (b) {
            o.a(c, "already gather today, skip!");
        }
        return !b;
    }

    protected JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f.J, f.L);
            com.miui.analytics.internal.collection.f.a(this.a, jSONObject);
        } catch (Throwable e) {
            Log.e(o.a(c), "gather e", e);
        }
        o.a(c, "content:" + jSONObject.toString());
        return jSONObject;
    }

    protected String d() {
        return "com.miui.analytics";
    }

    protected String e() {
        return f.g;
    }

    public void a(String str) {
        this.f.a("01", str, true);
        super.a();
    }

    public void a() {
        super.a();
    }
}
