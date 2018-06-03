package com.miui.analytics.internal.a;

import android.content.Context;
import java.util.List;

public class c {
    private static final String a = "AdEventDispatcher";
    private List<e> b;
    private Context c;

    public c(Context context, List<e> list) {
        this.b = list;
        this.c = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.miui.analytics.internal.a.e> a() {
        /*
        r7 = this;
        r0 = "AdEventDispatcher";
        r1 = "dispatch start.";
        com.miui.analytics.internal.util.o.a(r0, r1);
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = r7.c;
        r2 = "AdEventDispatcher";
        r0 = com.miui.analytics.internal.util.n.a(r0, r2);
        if (r0 == 0) goto L_0x0018;
    L_0x0016:
        r0 = r1;
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r7.b;
        if (r0 == 0) goto L_0x00ac;
    L_0x001c:
        r0 = r7.b;
        r2 = r0.iterator();
    L_0x0022:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x00ac;
    L_0x0028:
        r0 = r2.next();
        r0 = (com.miui.analytics.internal.a.e) r0;
        r3 = r7.c;	 Catch:{ Exception -> 0x0092 }
        r4 = r0.d();	 Catch:{ Exception -> 0x0092 }
        com.miui.analytics.internal.util.c.h(r3, r4);	 Catch:{ Exception -> 0x0092 }
        r3 = new com.miui.analytics.internal.a.g;	 Catch:{ Exception -> 0x0092 }
        r4 = r7.c;	 Catch:{ Exception -> 0x0092 }
        r5 = r0.b();	 Catch:{ Exception -> 0x0092 }
        r3.<init>(r4, r5);	 Catch:{ Exception -> 0x0092 }
        r3 = r3.a();	 Catch:{ Exception -> 0x0092 }
        r4 = "AdEventDispatcher";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0092 }
        r5.<init>();	 Catch:{ Exception -> 0x0092 }
        r6 = "dispatch ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0092 }
        r6 = r0.b();	 Catch:{ Exception -> 0x0092 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0092 }
        r6 = " result:";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0092 }
        r5 = r5.append(r3);	 Catch:{ Exception -> 0x0092 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0092 }
        com.miui.analytics.internal.util.o.a(r4, r5);	 Catch:{ Exception -> 0x0092 }
        if (r3 == 0) goto L_0x0084;
    L_0x006e:
        r3 = r7.c;	 Catch:{ Exception -> 0x0092 }
        r3 = com.miui.analytics.internal.d.c.a(r3);	 Catch:{ Exception -> 0x0092 }
        r4 = r0.b();	 Catch:{ Exception -> 0x0092 }
        r3.a(r4);	 Catch:{ Exception -> 0x0092 }
        r1.add(r0);	 Catch:{ Exception -> 0x0092 }
    L_0x007e:
        r0 = r7.c;
        com.miui.analytics.internal.util.c.g(r0);
        goto L_0x0022;
    L_0x0084:
        r3 = r7.c;	 Catch:{ Exception -> 0x0092 }
        r3 = com.miui.analytics.internal.d.c.a(r3);	 Catch:{ Exception -> 0x0092 }
        r0 = r0.b();	 Catch:{ Exception -> 0x0092 }
        r3.b(r0);	 Catch:{ Exception -> 0x0092 }
        goto L_0x007e;
    L_0x0092:
        r0 = move-exception;
        r3 = "AdEventDispatcher";
        r3 = com.miui.analytics.internal.util.o.a(r3);	 Catch:{ all -> 0x00a5 }
        r4 = "dispatch e";
        android.util.Log.e(r3, r4, r0);	 Catch:{ all -> 0x00a5 }
        r0 = r7.c;
        com.miui.analytics.internal.util.c.g(r0);
        goto L_0x0022;
    L_0x00a5:
        r0 = move-exception;
        r1 = r7.c;
        com.miui.analytics.internal.util.c.g(r1);
        throw r0;
    L_0x00ac:
        r0 = "AdEventDispatcher";
        r2 = "dispatch end.";
        com.miui.analytics.internal.util.o.a(r0, r2);
        r0 = r1;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.a.c.a():java.util.List<com.miui.analytics.internal.a.e>");
    }
}
