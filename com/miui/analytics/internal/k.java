package com.miui.analytics.internal;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.a.d;
import com.miui.analytics.internal.policy.g;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;

public class k {
    private static final String a = "Logger";
    private static volatile k b;
    private Context c;

    public static k a(Context context) {
        k kVar;
        synchronized (k.class) {
            if (b == null) {
                b = new k(context);
            }
            kVar = b;
        }
        return kVar;
    }

    private k(Context context) {
        this.c = c.a(context);
    }

    public void a(final LogEvent logEvent) {
        try {
            if (!n.e() && logEvent != null) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ k b;

                    public void run() {
                        try {
                            h.a(this.b.c).a(logEvent.c());
                            h.a(this.b.c).c(logEvent).a();
                            if (logEvent instanceof a) {
                                new d(this.b.c, logEvent).a();
                            }
                        } catch (Throwable th) {
                            Log.e(o.a(k.a), "send event exception", th);
                        }
                    }
                });
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "log event exception:", e);
        }
    }

    public void a(String str) {
        try {
            if (!n.e()) {
                LogEvent a = i.a(this.c, str);
                if (a != null) {
                    a(a);
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "log eventJson exception：", e);
        }
    }

    public void a(final String[] strArr) {
        try {
            if (!n.e() && strArr != null && strArr.length > 0) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ k b;

                    public void run() {
                        int i = 0;
                        try {
                            List arrayList = new ArrayList();
                            for (String str : strArr) {
                                LogEvent a = i.a(this.b.c, new String(str));
                                if (a != null) {
                                    h.a(this.b.c).a(a.c());
                                    arrayList.add(a);
                                }
                            }
                            List arrayList2 = new ArrayList();
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                arrayList2.add(h.a(this.b.c).c((LogEvent) arrayList.get(i2)));
                            }
                            List a2 = h.a(arrayList2);
                            while (i < a2.size()) {
                                ((g) a2.get(i)).a();
                                i++;
                            }
                            List a3 = a.a(arrayList);
                            if (a3 != null && a3.size() > 0) {
                                new d(this.b.c, a3).a();
                            }
                        } catch (Throwable th) {
                            Log.e(o.a(k.a), "send events exception", th);
                        }
                    }
                });
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "log events exception:", e);
        }
    }

    public synchronized void a() {
        synchronized (k.class) {
            b = null;
        }
    }
}
