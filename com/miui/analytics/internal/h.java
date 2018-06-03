package com.miui.analytics.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.miui.analytics.internal.a.f;
import com.miui.analytics.internal.collection.d;
import com.miui.analytics.internal.collection.i;
import com.miui.analytics.internal.collection.j;
import com.miui.analytics.internal.collection.k;
import com.miui.analytics.internal.collection.l;
import com.miui.analytics.internal.collection.m;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.e;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.z;

public class h {
    public static boolean a = false;
    private static final String b = "Debugger";
    private static final String c = "com.xiaomi.analytics.intent.DEBUG_ON";
    private static final String d = "com.xiaomi.analytics.intent.DEBUG_OFF";
    private static final String e = "com.xiaomi.analytics.intent.STAGING_ON";
    private static final String f = "com.xiaomi.analytics.intent.STAGING_OFF";
    private static final String g = "com.xiaomi.analytics.intent.EXPIRE_POLICY_CONFIG";
    private static final String h = "com.xiaomi.analytics.intent.USAGE_HELPER_DEBUG_ON";
    private static final String i = "com.xiaomi.analytics.intent.USAGE_HELPER_DEBUG_OFF";
    private static final String j = "analytics.usagev2";
    private static final String k = "analytics.scene";
    private static final String l = "analytics.aaid";
    private static final String m = "analytics.net";
    private static final String n = "analytics.stores";
    private static final String o = "analytics.stores.update";
    private static final String p = "analytics.print.usage";
    private static final String q = "analytics.mock.screen.on";
    private static final String r = "analytics.mock.screen.off";
    private static final String s = "analytics.print.batterystats";
    private static final String t = "analytics.deviceid";
    private static final String u = "analytics.deviceid.sp";
    private static h v;
    private Context w;
    private volatile boolean x = false;
    private BroadcastReceiver y = new BroadcastReceiver(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                Log.d(o.a(h.b), "action = " + action);
                if (h.c.equals(action)) {
                    h.b(true);
                } else if (h.d.equals(action)) {
                    h.b(false);
                } else if (h.e.equals(action)) {
                    h.a = true;
                } else if (h.f.equals(action)) {
                    h.a = false;
                } else if (h.g.equals(action)) {
                    com.miui.analytics.internal.policy.h.a(this.a.w).a();
                } else if (action.equals(h.j)) {
                    l.a(context).a();
                } else if (action.equals(h.k)) {
                    j.a(context).f();
                } else if (action.equals(h.l)) {
                    d.a(context).a();
                } else if (action.equals(h.m)) {
                    i.a(context).a();
                } else if (action.equals(h.n)) {
                    com.miui.analytics.internal.collection.h.a(context).b();
                } else if (action.equals(h.o)) {
                    com.miui.analytics.internal.collection.h.a(context).a();
                } else if (action.equals(h.p)) {
                    m.d(context);
                } else if (action.equals(h.h)) {
                    m.a = true;
                } else if (action.equals(h.i)) {
                    m.a = false;
                } else if (action.equals(h.q)) {
                    e.a(context).b(true);
                } else if (action.equals(h.r)) {
                    e.a(context).b(false);
                } else if (action.equals(h.s)) {
                    e.a(context).b();
                } else if (action.equals(h.t)) {
                    o.a(h.b, "SysUtils.getIMEI: " + z.a(context));
                    o.a(h.b, "SysUtils.getHashedIMEI: " + z.d(context));
                    o.a(h.b, "SysUtils.getMAC: " + z.c(context));
                    o.a(h.b, "SysUtils.getHashedMac: " + z.f(context));
                    String[] a = com.miui.analytics.internal.util.h.a(context);
                    o.a(h.b, "DeviceIdHelper.getDeviceId: id = " + a[0] + " type = " + a[1]);
                    o.a(h.b, "DeviceIdHelper.getImeiList: " + com.miui.analytics.internal.util.h.c(context));
                    o.a(h.b, "SysUtils.getIMSI: " + z.b(context));
                    o.a(h.b, "DeviceIdHelper.getImsiList: " + com.miui.analytics.internal.util.h.i(context));
                    o.a(h.b, "DeviceIdHelper.getImsiListMd5: " + com.miui.analytics.internal.util.h.j(context));
                } else if (action.equals(h.u)) {
                    v vVar = new v(context, u.d, u.i);
                    o.a(h.b, "imei in sp? " + vVar.b("imei"));
                    o.a(h.b, "mac in sp? " + vVar.b("mac"));
                    o.a(h.b, "stable_imei in sp? " + vVar.b(u.R));
                    o.a(h.b, "imei_md5 in sp? " + vVar.b(u.P) + " and = " + vVar.b(u.P, ""));
                    o.a(h.b, "mac_md5 in sp? " + vVar.b(u.Q) + " and = " + vVar.b(u.Q, ""));
                    o.a(h.b, "has_deleted_id in sp? " + vVar.b(u.U) + " and = " + vVar.b(u.U, false));
                }
            }
        }
    };

    private h(Context context) {
        this.w = c.a(context);
    }

    public static synchronized h a(Context context) {
        h hVar;
        synchronized (h.class) {
            if (v == null) {
                v = new h(context);
            }
            hVar = v;
        }
        return hVar;
    }

    public void a() {
        if (!this.x) {
            this.x = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(c);
            intentFilter.addAction(d);
            intentFilter.addAction(e);
            intentFilter.addAction(f);
            intentFilter.addAction(g);
            intentFilter.addAction(h);
            intentFilter.addAction(i);
            intentFilter.addAction(j);
            intentFilter.addAction(k);
            intentFilter.addAction(l);
            intentFilter.addAction(m);
            intentFilter.addAction(n);
            intentFilter.addAction(o);
            intentFilter.addAction(p);
            intentFilter.addAction(q);
            intentFilter.addAction(r);
            intentFilter.addAction(s);
            intentFilter.addAction(t);
            intentFilter.addAction(u);
            this.w.registerReceiver(this.y, intentFilter);
        }
    }

    public void b() {
        this.w.unregisterReceiver(this.y);
        this.x = false;
    }

    private static void b(boolean z) {
        o.a = z;
        k.a = z;
        l.a = z;
        f.a = z;
        Log.d(b, "changeDebugStatus " + z);
    }
}
