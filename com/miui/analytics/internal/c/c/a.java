package com.miui.analytics.internal.c.c;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.c.b;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import java.util.List;

public abstract class a {
    private static final String c = "AbstractPacker";
    protected Context a;
    protected b b;

    protected abstract List<LogEvent> a(List<LogEvent> list);

    protected abstract void c(List<LogEvent> list);

    public a(Context context, b bVar) {
        this.a = c.a(context);
        this.b = bVar;
    }

    public void b(List<LogEvent> list) {
        try {
            o.a(c, "---------------packAndSend " + (list == null ? "null" : Integer.valueOf(list.size())));
            if (this.b != null) {
                this.b.c(list);
            }
            List a = a(list);
            if (this.b != null) {
                this.b.d(a);
            }
            c(a);
        } catch (Throwable e) {
            Log.e(o.a(c), "packAndSend e", e);
        }
    }
}
