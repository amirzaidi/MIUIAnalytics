package com.miui.analytics.internal.c.a;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.policy.l;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class b extends a {
    private static final String f = "PollingDeliver";
    private static final int g = 200;
    private static long h;
    private static AtomicBoolean i = new AtomicBoolean(false);

    public b(Context context) {
        super(context, new com.miui.analytics.internal.c.c.b(context, null), null);
    }

    public b(Context context, com.miui.analytics.internal.c.b bVar) {
        super(context, new com.miui.analytics.internal.c.c.b(context, bVar), bVar);
    }

    protected boolean b() {
        if (!d.isAlive() || ac.a(h, (long) (h.a(this.c).e() * 2))) {
            i.compareAndSet(true, false);
        }
        if (i.get()) {
            return false;
        }
        return true;
    }

    protected List<LogEvent> d() {
        o.a(f, "----------checkDeliverConditions-----------");
        List<LogEvent> arrayList = new ArrayList();
        try {
            boolean a = r.a(this.c);
            if (r.b(this.c)) {
                k a2 = k.a(this.c);
                arrayList.addAll(a2.b(this.c));
                l a3 = h.a(this.c).a(h);
                if (h == 0 || a3.a(a2, 2)) {
                    o.a(f, "meet the trigger requirement or sLastDeliverTime == 0, sLastDeliverTime :" + h);
                    if (a) {
                        arrayList.addAll(a2.a(this.c, (int) g));
                    } else {
                        arrayList.addAll(a2.b(this.c, g));
                    }
                } else {
                    o.a(f, "not meet the requirement");
                }
                o.a(f, "after check conditions deliverEvents:" + arrayList.size());
            } else {
                o.a(f, "netAccess = false, deliver empty events");
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "checkDeliverConditions e", e);
        }
        return arrayList;
    }

    protected void b(List<LogEvent> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    h = System.currentTimeMillis();
                }
            } catch (Throwable e) {
                Log.e(o.a(f), "afterDoDeliver e", e);
                return;
            }
        }
        if (k.a(this.c).c() > 0) {
            e.postDelayed(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.c();
                }
            }, (long) h.a(this.c).e());
            i.compareAndSet(false, true);
            return;
        }
        i.compareAndSet(true, false);
    }
}
