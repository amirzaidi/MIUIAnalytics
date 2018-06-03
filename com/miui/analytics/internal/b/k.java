package com.miui.analytics.internal.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.a.b;
import com.miui.analytics.internal.a.e;
import com.miui.analytics.internal.collection.UploadUsageHelper.HybridInfo;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.z;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class k implements h {
    public static final int a = 1;
    public static final int b = 2;
    private static final String c = "TrackerStore";
    private static k h;
    private a d;
    private b e;
    private b f;
    private e g;

    public static synchronized k a(Context context) {
        k kVar;
        synchronized (k.class) {
            if (h == null) {
                h = new k(c.a(context));
            }
            kVar = h;
        }
        return kVar;
    }

    private k(Context context) {
        this.d = new a(context);
        this.e = new b(context);
        this.f = new b(context);
        this.g = new e(context);
    }

    public void a(List<e> list) {
        if (list != null && list.size() != 0) {
            this.f.a((List) list);
        }
    }

    public List<e> e() {
        List<e> arrayList = new ArrayList();
        arrayList.addAll(this.f.a());
        return arrayList;
    }

    public void b(List<e> list) {
        if (list != null && list.size() != 0) {
            o.a(c, String.format(" popEvents %d ad events from db. ", new Object[]{Integer.valueOf(list.size())}));
            this.f.b((List) list);
        }
    }

    public void a(long j) {
        o.a(c, "rmAdEventByTime  " + j);
        this.f.a(j);
    }

    public void f() {
        try {
            this.f.c();
        } catch (Exception e) {
        }
    }

    public void c(List<e> list) {
        try {
            this.f.c(list);
        } catch (Exception e) {
        }
    }

    public void d(List<LogEvent> list) {
        if (list != null && list.size() != 0) {
            o.a(c, String.format("pushEvent %d events into db. ", new Object[]{Integer.valueOf(list.size())}));
            this.d.b((List) list);
        }
    }

    public List<LogEvent> g() {
        List<LogEvent> arrayList = new ArrayList();
        try {
            arrayList = this.d.c();
        } catch (Exception e) {
        }
        return arrayList;
    }

    public long h() {
        List<LogEvent> g = g();
        if (g == null) {
            return 0;
        }
        long j = 0;
        for (LogEvent logEvent : g) {
            if (logEvent != null) {
                long e;
                if (j == 0 || logEvent.e() < j) {
                    e = logEvent.e();
                } else {
                    e = j;
                }
                j = e;
            }
        }
        return j;
    }

    public void b(long j) {
        o.a(c, "popEventsByTime  " + j);
        this.d.a(j);
    }

    public void a(String str) {
        o.a(c, "deleteEventsByAppId  " + str);
        this.d.a(str);
    }

    public void e(List<LogEvent> list) {
        if (list != null && list.size() != 0) {
            o.a(c, String.format("popEvents %d events from db. ", new Object[]{Integer.valueOf(list.size())}));
            this.d.c((List) list);
        }
    }

    public long a() {
        try {
            return this.d.d();
        } catch (Throwable e) {
            Log.e(o.a(c), "getStoreCount e", e);
            return 0;
        }
    }

    public long b() {
        Object e = this.d.e();
        if (TextUtils.isEmpty(e)) {
            return 0;
        }
        long length = new File(e).length();
        o.a(c, "db size is  " + length);
        return length;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.d != null) {
                this.d.close();
            }
            if (this.f != null) {
                this.f.close();
            }
            if (this.e != null) {
                this.e.close();
            }
        } catch (Exception e) {
        }
        super.finalize();
    }

    public void i() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.d.a();
            Log.w(o.a(c), String.format("[ANALYTICS-DB-TRUNCATE] clean table analytics done, cost %dms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        } catch (Throwable th) {
            Log.e(o.a(c), "deleteAllItemsInAnalyticsTable failed", th);
        }
    }

    public void j() {
        try {
            this.d.b();
        } catch (Exception e) {
        }
    }

    public void f(List<LogEvent> list) {
        try {
            this.d.a((List) list);
        } catch (Exception e) {
        }
    }

    public void g(List<LogEvent> list) {
        if (list != null && list.size() != 0) {
            try {
                o.a(c, String.format("pushToEventDBV2 %d events into db. ", new Object[]{Integer.valueOf(list.size())}));
                this.e.a((List) list);
            } catch (Throwable e) {
                Log.e(o.a(c), "pushToEventDBV2 e", e);
            }
        }
    }

    public void h(List<LogEvent> list) {
        if (list != null && list.size() != 0) {
            o.a(c, String.format("popEventsDBV2 %d events from db. ", new Object[]{Integer.valueOf(list.size())}));
            try {
                this.e.b((List) list);
            } catch (Throwable e) {
                Log.e(o.a(c), "popEventsDBV2 e", e);
            }
        }
    }

    public void k() {
        try {
            new Thread(new Runnable(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        this.a.e.a();
                        Log.w(o.a(k.c), String.format("[ANALYTICS-DBV2-TRUNCATE] clean table analyticsv2 done, cost %dms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
                    } catch (Throwable th) {
                        Log.e(o.a(k.c), "deleteAllItemsInAnalyticsV2Table failed", th);
                    }
                }
            }).start();
        } catch (Throwable e) {
            Log.e(o.a(c), "deleteAnalyticsTableContent exception: ", e);
        }
    }

    public void c(long j) {
        o.a(c, "popEventsV2ByTime  " + j);
        this.e.a(j);
    }

    public long l() {
        long c = this.e.c();
        o.a(c, "getEarliestEventTimeV2 :" + c);
        return c;
    }

    public List<LogEvent> b(Context context) {
        List<LogEvent> arrayList = new ArrayList();
        try {
            h.a(context).c().a(context, 2);
            arrayList = this.e.d();
        } catch (Exception e) {
        }
        return arrayList;
    }

    public List<LogEvent> a(Context context, int i) {
        List<LogEvent> arrayList = new ArrayList();
        try {
            h.a(context).c().a(context, 2);
            arrayList = this.e.a(i);
            o.a(c, "queryPollingEventsFromEventDBV2 :" + arrayList.toString());
            return arrayList;
        } catch (Exception e) {
            return arrayList;
        }
    }

    public List<LogEvent> b(Context context, int i) {
        List<LogEvent> arrayList = new ArrayList();
        try {
            h.a(context).c().a(context, 2);
            arrayList = this.e.b(i);
            o.a(c, "queryPollingEventNetAllFromEventDBV2 :" + arrayList.toString());
            return arrayList;
        } catch (Exception e) {
            return arrayList;
        }
    }

    public void m() {
        try {
            this.e.b();
        } catch (Exception e) {
        }
    }

    public void d(long j) {
        o.a(c, "popTimeExpiredFromDBV2 " + j);
        try {
            this.e.b(j);
        } catch (Exception e) {
        }
    }

    public void i(List<LogEvent> list) {
        try {
            this.e.c((List) list);
        } catch (Exception e) {
        }
    }

    public long c() {
        try {
            return this.e.e();
        } catch (Throwable e) {
            Log.e(o.a(c), "getStoreV2Count e", e);
            return 0;
        }
    }

    public long d() {
        Object f = this.e.f();
        if (TextUtils.isEmpty(f)) {
            return 0;
        }
        long length = new File(f).length();
        o.a(c, "db size is  " + length);
        return length;
    }

    public List<HybridInfo> a(long j, long j2) {
        try {
            return this.g.a(j, j2);
        } catch (Throwable e) {
            Log.e(o.a(c), "queryHybridInfos e", e);
            return null;
        }
    }

    public void a(String str, String str2, long j) {
        try {
            if (z.C()) {
                final String str3 = str;
                final String str4 = str2;
                final long j2 = j;
                ab.a(new Runnable(this) {
                    final /* synthetic */ k d;

                    public void run() {
                        this.d.g.a(str3, str4, j2);
                    }
                });
                return;
            }
            this.g.a(str, str2, j);
        } catch (Throwable e) {
            Log.e(o.a(c), "saveHybridInfo e", e);
        }
    }

    public void n() {
        try {
            if (z.C()) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.g.a();
                    }
                });
            } else {
                this.g.a();
            }
        } catch (Throwable e) {
            Log.e(o.a(c), "deleteHybridOneWeekAgo e", e);
        }
    }
}
