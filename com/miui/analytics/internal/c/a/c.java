package com.miui.analytics.internal.c.a;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.c.c.b;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.List;

public class c extends a {
    private static final String f = "RealTimeDeliver";

    public c(Context context) {
        super(context, new b(context, null), null);
    }

    public c(Context context, com.miui.analytics.internal.c.b bVar) {
        super(context, new b(context, bVar), bVar);
    }

    protected boolean b() {
        return true;
    }

    protected List<LogEvent> d() {
        o.a(f, "---------------checkDeliverConditions---------------");
        try {
            if (r.b(this.c)) {
                List<LogEvent> b = k.a(this.c).b(this.c);
                o.a(f, "after check conditions,deliver :" + (b == null ? "null" : Integer.valueOf(b.size())));
                return b;
            }
            o.a(f, "net not access!nothing to deliver");
            return null;
        } catch (Throwable e) {
            Log.e(o.a(f), "checkDeliverConditions e", e);
            return null;
        }
    }

    protected void b(List<LogEvent> list) {
    }
}
