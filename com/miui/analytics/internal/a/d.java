package com.miui.analytics.internal.a;

import android.content.Context;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.policy.g;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.List;

public class d extends g {
    private static final String c = "AdEventRealTimeSender";

    public d(Context context, List<LogEvent> list) {
        super(context, (List) list);
    }

    public d(Context context, LogEvent logEvent) {
        super(context, logEvent);
    }

    public void a() {
        if (this.b != null && this.b.size() != 0) {
            List<e> a = new a().a(this.a, this.b);
            if (a.size() > 0) {
                o.a(c, String.format("%d ad events to send.", new Object[]{Integer.valueOf(a.size())}));
                List<e> a2 = new c(this.a, a).a();
                if (a2 == null || a2.size() <= 0) {
                    o.a(c, "ad event sent failure.");
                } else {
                    o.a(c, String.format("%d events had been sent.", new Object[]{Integer.valueOf(a2.size())}));
                    for (e remove : a2) {
                        a.remove(remove);
                    }
                }
            }
            if (a.size() > 0) {
                o.a(c, String.format("%d events had been push into db. %d", new Object[]{Integer.valueOf(a.size()), Long.valueOf(System.currentTimeMillis())}));
                if (r.b(this.a)) {
                    for (e remove2 : a) {
                        remove2.a++;
                        o.a(c, "triggered, increase mSentCount, " + remove2.toString());
                    }
                } else {
                    o.a(c, "network not reachable");
                }
                new f(this.a, a).a();
            }
        }
    }
}
