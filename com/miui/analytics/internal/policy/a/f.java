package com.miui.analytics.internal.policy.a;

import android.content.Context;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.policy.g;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.service.b;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class f extends g {
    private static final String c = "RealtimeSender";

    public f(Context context, List<LogEvent> list) {
        super(context, (List) list);
    }

    public f(Context context, LogEvent logEvent) {
        super(context, logEvent);
    }

    public void a() {
        if (this.b.size() != 0) {
            Collection<LogEvent> arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            h a = h.a(this.a);
            for (LogEvent logEvent : this.b) {
                if (a.b(logEvent).a()) {
                    o.a(c, "sample rate match.");
                    if (r.a(this.a)) {
                        arrayList.add(logEvent);
                    } else if (a.g(logEvent)) {
                        arrayList2.add(logEvent);
                    } else {
                        arrayList.add(logEvent);
                    }
                }
            }
            if (arrayList.size() > 0) {
                o.a(c, String.format("%d events to send.", new Object[]{Integer.valueOf(arrayList.size())}));
                List<LogEvent> a2 = new b(this.a, this.b).a();
                if (a2 != null) {
                    o.a(c, String.format("%d events had been sent.", new Object[]{Integer.valueOf(a2.size())}));
                    for (LogEvent logEvent2 : a2) {
                        arrayList.remove(logEvent2);
                    }
                }
            }
            if (r.b(this.a) && arrayList.size() > 0) {
                for (LogEvent logEvent22 : arrayList) {
                    logEvent22.l();
                }
            }
            arrayList2.addAll(arrayList);
            if (arrayList2.size() > 0) {
                o.a(c, String.format("%d events had been put into polling queue..", new Object[]{Integer.valueOf(arrayList2.size())}));
                new e(this.a, arrayList2).a();
            }
        }
    }
}
