package com.miui.analytics.internal.c.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.c.b;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;

public abstract class a {
    protected static HandlerThread d = null;
    protected static Handler e = null;
    private static final String f = "AbstractDeliver";
    protected com.miui.analytics.internal.c.c.a a;
    protected b b;
    protected Context c;

    protected abstract void b(List<LogEvent> list);

    protected abstract boolean b();

    protected abstract List<LogEvent> d();

    static {
        try {
            d = new HandlerThread(f);
            d.start();
            e = new Handler(d.getLooper());
        } catch (Throwable e) {
            Log.e(o.a(f), "e", e);
        }
    }

    public a(Context context, com.miui.analytics.internal.c.c.b bVar, b bVar2) {
        this.c = c.a(context);
        this.a = bVar;
        this.b = bVar2;
    }

    public void a() {
        a(new ArrayList());
    }

    public void a(LogEvent logEvent) {
        List arrayList = new ArrayList();
        if (logEvent != null) {
            arrayList.add(logEvent);
        }
        a(arrayList);
    }

    public void a(final List<LogEvent> list) {
        o.a(f, "---------- acceptEvents:");
        try {
            if (this.b != null) {
                this.b.a((List) list);
            }
            e.post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    try {
                        o.a(a.f, "save to db," + (list == null ? "null" : Integer.valueOf(list.size())));
                        k.a(this.b.c).g(list);
                        if (this.b.b()) {
                            this.b.c();
                        } else {
                            o.a(a.f, "不马上执行deliver");
                        }
                    } catch (Throwable e) {
                        Log.e(o.a(a.f), "acceptEvents e", e);
                    }
                }
            });
        } catch (Throwable e) {
            Log.e(o.a(f), "acceptEvents e", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void c() {
        /*
        r4 = this;
        r1 = 0;
        r1 = r4.d();	 Catch:{ Exception -> 0x004d }
        r2 = "AbstractDeliver";
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004d }
        r0.<init>();	 Catch:{ Exception -> 0x004d }
        r3 = "---------- deliverEvents :";
        r3 = r0.append(r3);	 Catch:{ Exception -> 0x004d }
        if (r1 != 0) goto L_0x0042;
    L_0x0014:
        r0 = "null";
    L_0x0016:
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x004d }
        r0 = r0.toString();	 Catch:{ Exception -> 0x004d }
        com.miui.analytics.internal.util.o.a(r2, r0);	 Catch:{ Exception -> 0x004d }
        r0 = r4.b;	 Catch:{ Exception -> 0x004d }
        if (r0 == 0) goto L_0x0031;
    L_0x0025:
        r2 = r4.b;	 Catch:{ Exception -> 0x004d }
        if (r1 != 0) goto L_0x004b;
    L_0x0029:
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x004d }
        r0.<init>();	 Catch:{ Exception -> 0x004d }
    L_0x002e:
        r2.b(r0);	 Catch:{ Exception -> 0x004d }
    L_0x0031:
        if (r1 == 0) goto L_0x003e;
    L_0x0033:
        r0 = r1.size();	 Catch:{ Exception -> 0x004d }
        if (r0 <= 0) goto L_0x003e;
    L_0x0039:
        r0 = r4.a;	 Catch:{ Exception -> 0x004d }
        r0.b(r1);	 Catch:{ Exception -> 0x004d }
    L_0x003e:
        r4.b(r1);
    L_0x0041:
        return;
    L_0x0042:
        r0 = r1.size();	 Catch:{ Exception -> 0x004d }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x004d }
        goto L_0x0016;
    L_0x004b:
        r0 = r1;
        goto L_0x002e;
    L_0x004d:
        r0 = move-exception;
        r2 = "AbstractDeliver";
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ all -> 0x005d }
        r3 = "doDeliver e";
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x005d }
        r4.b(r1);
        goto L_0x0041;
    L_0x005d:
        r0 = move-exception;
        r4.b(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.c.a.a.c():void");
    }
}
