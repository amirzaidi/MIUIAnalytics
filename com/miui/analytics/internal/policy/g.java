package com.miui.analytics.internal.policy;

import android.content.Context;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.util.c;
import java.util.ArrayList;
import java.util.List;

public abstract class g {
    protected Context a;
    protected List<LogEvent> b = new ArrayList();

    public abstract void a();

    public g(Context context, LogEvent logEvent) {
        this.a = c.a(context);
        if (logEvent != null) {
            this.b.add(logEvent);
        }
    }

    public g(Context context, List<LogEvent> list) {
        this.a = c.a(context);
        if (list != null) {
            this.b.addAll(list);
        }
    }

    public g(Context context, LogEvent[] logEventArr) {
        this.a = c.a(context);
        if (logEventArr != null) {
            for (Object add : logEventArr) {
                this.b.add(add);
            }
        }
    }

    public void a(g gVar) {
        if (gVar != null) {
            this.b.addAll(gVar.b);
        }
    }
}
