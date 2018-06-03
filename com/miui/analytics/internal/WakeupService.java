package com.miui.analytics.internal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.miui.analytics.internal.c.c;
import com.miui.analytics.internal.collection.e;
import com.miui.analytics.internal.collection.g;
import com.miui.analytics.internal.collection.h;
import com.miui.analytics.internal.collection.i;
import com.miui.analytics.internal.collection.j;
import com.miui.analytics.internal.collection.k;
import com.miui.analytics.internal.collection.l;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.p;

public class WakeupService extends Service {
    public static final String a = "command";
    public static final String b = "wakeup";
    public static final String c = "active";
    public static final String d = "recordLocation";
    public static final String e = "uploadLocation";
    public static final String f = "usage";
    public static final String g = "usagev2";
    public static final String h = "installation";
    public static final String i = "installationv2";
    public static final String j = "uploadScene";
    public static final String k = "uploadNetFlow";
    public static final String l = "updateStoreInfo";
    public static final String m = "uploadNearestStores";
    public static final String n = "gatherBatteryInfo";
    public static final String o = "uploadBattery";
    private static final String p = "WakeupService";

    public int onStartCommand(final Intent intent, int i, int i2) {
        try {
            ab.a(new Runnable(this) {
                final /* synthetic */ WakeupService b;

                public void run() {
                    try {
                        String stringExtra = intent.getStringExtra(WakeupService.a);
                        o.a(WakeupService.p, "onStartCommand " + stringExtra);
                        if (WakeupService.b.equals(stringExtra)) {
                            this.b.a();
                        } else if (WakeupService.c.equals(stringExtra)) {
                            c.a(this.b).a("00", "01", true);
                            e.a(this.b).c();
                        } else if (WakeupService.d.equals(stringExtra)) {
                            p.a(this.b).j();
                        } else if (WakeupService.e.equals(stringExtra)) {
                            g.a(this.b).a();
                        } else if ("usage".equals(stringExtra)) {
                            k.a(this.b).a();
                        } else if (WakeupService.g.equals(stringExtra)) {
                            c.a(this.b).a(c.g, "01", true);
                            l.a(this.b).a();
                        } else if (WakeupService.h.equals(stringExtra)) {
                            k.a(this.b).b();
                        } else if (WakeupService.i.equals(stringExtra)) {
                            l.a(this.b).b();
                        } else if (WakeupService.j.equals(stringExtra)) {
                            j.a(this.b).f();
                        } else if (WakeupService.k.equals(stringExtra)) {
                            i.a(this.b).a();
                        } else if (WakeupService.l.equals(stringExtra)) {
                            h.a(this.b).a();
                        } else if (WakeupService.m.equals(stringExtra)) {
                            h.a(this.b).b();
                        } else if (WakeupService.n.equals(stringExtra)) {
                            com.miui.analytics.internal.util.e.a(this.b).c();
                        } else if (WakeupService.o.equals(stringExtra)) {
                            com.miui.analytics.internal.util.e.a(this.b).a();
                        }
                    } catch (Throwable e) {
                        Log.e(o.a(WakeupService.p), "onStartCommand exception: ", e);
                    }
                }
            });
        } catch (Throwable e) {
            Log.e(o.a(p), "onStartCommand exception: ", e);
        }
        return 0;
    }

    private void a() {
        b();
    }

    private void b() {
        m.a(getApplicationContext()).b();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
