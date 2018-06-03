package com.miui.analytics.internal.collection;

import android.app.usage.UsageEvents.Event;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.j;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.y;
import com.miui.analytics.internal.util.z;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class k {
    private static final String A = "its";
    private static volatile k B = null;
    private static final Object F = new Object();
    private static boolean G = false;
    public static boolean a = false;
    public static final String b = "P";
    public static final String c = "T";
    public static final String d = "C";
    private static final String e = "UU";
    private static final String f = "tz";
    private static final String g = "si";
    private static final String h = "ts";
    private static final String i = "U";
    private static final String j = "I";
    private static final String k = "n";
    private static final String l = "v";
    private static final String m = "vn";
    private static final String n = "s";
    private static final String o = "b";
    private static final String p = "lut";
    private static final String q = "fit";
    private static final String r = "p";
    private static final String s = "t";
    private static final String t = "e";
    private static final String u = "eot";
    private static final String v = "pim";
    private static final String w = "es";
    private static final String x = "{}";
    private static final int y = 6;
    private static final String z = "il";
    private v C;
    private j D;
    private Context E;

    private k(Context context) {
        this.E = c.a(context);
        this.C = new v(context, u.d);
        this.D = new j(context, z);
    }

    public static synchronized k a(Context context) {
        k kVar;
        synchronized (k.class) {
            if (B == null) {
                B = new k(context);
            }
            kVar = B;
        }
        return kVar;
    }

    public void a() {
        if (!n.a(this.E, e)) {
            if (!g.D()) {
                o.a(e, "Upload usage V1 is disabled by remote.");
            } else if (g.c(this.E)) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        synchronized (k.F) {
                            try {
                                o.a(k.e, "enter upload usage");
                                if (VERSION.SDK_INT <= 19 || !z.z()) {
                                    this.a.e();
                                    b.b(this.a.E);
                                } else {
                                    this.a.d();
                                }
                                this.a.f();
                            } catch (Throwable th) {
                                ae.a(this.a.E, k.e, "run usage task exception", th);
                            }
                        }
                    }
                });
            }
        }
    }

    public void b() {
        if (!n.e()) {
            if (!g.D()) {
                o.a(e, "Upload usage V1 is disabled by remote.");
            } else if (g.c(this.E)) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            o.a(k.e, "enter saveInstallationList");
                            if (!ac.a(this.a.o()) || k.a) {
                                this.a.D.a(this.a.h().toString());
                                this.a.p();
                                o.a(k.e, String.format("time: %s, installed: %s", new Object[]{Long.valueOf(System.currentTimeMillis()), r0}));
                                return;
                            }
                            o.a(k.e, "installation saved today, skip");
                        } catch (Throwable e) {
                            o.b(k.e, "saveInstallationList task exception: ", e);
                        }
                    }
                });
            }
        }
    }

    private void d() {
        if (!ac.a(m()) || a) {
            o.a(e, "enter uploadRecentOneWeekAppsUsage");
            Map q = q();
            long a = ac.a();
            long b = ac.b();
            Object obj = null;
            for (int i = 0; i < y; i++) {
                if (!q.containsKey(ac.a(new Date(a)))) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(g, VERSION.SDK_INT);
                        jSONObject.put(f, ac.c());
                        jSONObject.put("ts", a);
                        jSONObject.put(i, m.a(this.E, a, b));
                        jSONObject.put(j, h());
                    } catch (Throwable e) {
                        ae.a(this.E, e, "get usage info exception", e);
                    }
                    com.miui.analytics.internal.k.a(this.E).a(new LogEvent(this.E, "com.miui.analytics", f.i, jSONObject.toString()));
                    o.a(e, String.format("uploading second space usage for %s, content:%s", new Object[]{ac.a(new Date(a)), r0}));
                    obj = 1;
                    a(a, 1);
                }
                a -= ac.b;
                b -= ac.b;
            }
            if (obj != null) {
                n();
                return;
            }
            return;
        }
        o.a(e, "uploadRecentOneWeekAppsUsage, usage uploaded today");
    }

    private void e() {
        if (a || !ac.a(i())) {
            String g = g();
            com.miui.analytics.internal.k.a(this.E).a(new LogEvent(this.E, "com.miui.analytics", f.i, g));
            j();
            o.a(e, "start to upload usage info: " + g);
            return;
        }
        o.a(e, "usage uploaded today");
    }

    private void f() {
        if (a || !ac.a(k())) {
            o.a(e, "start to upload usage sequence, " + new Date().toString());
            com.miui.analytics.internal.k.a(this.E).a(new LogEvent(this.E, "com.miui.analytics", f.j, r()));
            l();
            return;
        }
        o.a(e, "usage sequence uploaded today");
    }

    private String g() {
        JSONObject jSONObject = new JSONObject();
        try {
            Object h;
            jSONObject.put(g, VERSION.SDK_INT);
            jSONObject.put(f, ac.c());
            if (VERSION.SDK_INT < 23) {
                jSONObject.put("ts", ac.d());
            } else {
                jSONObject.put("ts", ac.d() - ac.b);
            }
            jSONObject.put(i, m.a(this.E));
            if (VERSION.SDK_INT < 23) {
                h = h();
                jSONObject.put(A, System.currentTimeMillis());
            } else {
                String str;
                CharSequence a = this.D.a();
                if (TextUtils.isEmpty(a)) {
                    str = "[]";
                } else {
                    CharSequence str2 = a;
                }
                h = new JSONArray(str2);
                if (h.length() > 0) {
                    jSONObject.put(A, o());
                } else {
                    h = h();
                    jSONObject.put(A, System.currentTimeMillis());
                }
            }
            jSONObject.put(j, h);
            return jSONObject.toString();
        } catch (Throwable e) {
            ae.a(this.E, e, "get usage info exception", e);
            return "";
        }
    }

    private JSONArray h() {
        JSONArray jSONArray = new JSONArray();
        List<PackageInfo> installedPackages = this.E.getPackageManager().getInstalledPackages(64);
        if (installedPackages != null) {
            o.a(e, "installed packages cnt=" + installedPackages.size());
            Set a = g.a(f.i);
            int i = 0;
            for (PackageInfo packageInfo : installedPackages) {
                int i2;
                if (!c.a(packageInfo.applicationInfo) || (a != null && a.contains(packageInfo.packageName))) {
                    jSONArray.put(a(packageInfo));
                    i2 = i;
                } else {
                    i2 = i + 1;
                }
                i = i2;
            }
            o.a(e, "installed system apps cnt=" + i);
        }
        return jSONArray;
    }

    private JSONObject a(PackageInfo packageInfo) {
        String installerPackageName = this.E.getPackageManager().getInstallerPackageName(packageInfo.packageName);
        JSONObject jSONObject = new JSONObject();
        try {
            String a = y.a(packageInfo.packageName);
            jSONObject.put(b, a);
            jSONObject.put("v", packageInfo.versionCode);
            jSONObject.put("vn", packageInfo.versionName);
            jSONObject.put("s", y.a(installerPackageName));
            jSONObject.put(p, packageInfo.lastUpdateTime);
            jSONObject.put("fit", packageInfo.firstInstallTime);
            jSONObject.put(o, z.a(a) ? 1 : 0);
            return jSONObject;
        } catch (Throwable e) {
            Log.e(o.a(e), "Fail to format package info of " + packageInfo.packageName, e);
            return null;
        }
    }

    private long i() {
        return this.C.b(u.n, 0);
    }

    private void j() {
        this.C.a(u.n, System.currentTimeMillis());
    }

    private long k() {
        return this.C.b(u.o, 0);
    }

    private void l() {
        this.C.a(u.o, System.currentTimeMillis());
    }

    private long m() {
        long b = this.C.b(u.m, 0);
        o.a(e, "getLastUploadSecondSpaceUsageTime " + b);
        return b;
    }

    private void n() {
        this.C.a(u.m, System.currentTimeMillis());
        o.a(e, "saveLastUploadSecondSpaceUsageTime " + System.currentTimeMillis());
    }

    private long o() {
        return this.C.b(u.l, 0);
    }

    private void p() {
        this.C.a(u.l, System.currentTimeMillis());
    }

    private Map<String, Integer> q() {
        Map<String, Integer> hashMap = new HashMap();
        try {
            long a = ac.a();
            long j = a - 432000000;
            if (j < 0) {
                j = 0;
            }
            int intValue = Integer.valueOf(ac.a(new Date(j))).intValue();
            int intValue2 = Integer.valueOf(ac.a(new Date(a + ac.b))).intValue();
            o.a(e, String.format("left:%d(%d), right:%d(%d)", new Object[]{Long.valueOf(j), Integer.valueOf(intValue), Long.valueOf(a), Integer.valueOf(intValue2)}));
            JSONObject jSONObject = new JSONObject(this.C.b(u.k, x));
            Iterator keys = jSONObject.keys();
            int i = 0;
            Object obj = null;
            int i2 = 0;
            while (keys.hasNext()) {
                Object obj2;
                int i3;
                String str = (String) keys.next();
                long intValue3 = (long) Integer.valueOf(str).intValue();
                o.a(e, String.format("SecondSpace Map: key:%s, value:%d", new Object[]{str, Integer.valueOf(jSONObject.getInt(str))}));
                if (intValue3 < ((long) intValue) || intValue3 > ((long) intValue2)) {
                    keys.remove();
                    jSONObject.remove(str);
                    o.a(e, String.format("the date %s is out-of-date", new Object[]{str}));
                    obj2 = 1;
                    i3 = i;
                } else {
                    hashMap.put(str, Integer.valueOf(jSONObject.getInt(str)));
                    Object obj3 = obj;
                    i3 = i + 1;
                    obj2 = obj3;
                }
                i2++;
                i = i3;
                obj = obj2;
            }
            if (obj != null) {
                this.C.a(u.k, jSONObject.toString());
            }
            o.a(e, String.format("get total %d date serial, %d are valid", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
        } catch (Throwable e) {
            ae.a(this.E, e, "getSecondSpaceDateSerial exception: ", e);
        }
        return hashMap;
    }

    private void a(long j, int i) {
        try {
            JSONObject jSONObject = new JSONObject(this.C.b(u.k, x));
            jSONObject.put(ac.a(new Date(j)), i);
            this.C.a(u.k, jSONObject.toString());
            o.a(e, String.format("saveSecondSpaceDateSerial,key:%s,value:%d", new Object[]{r0, Integer.valueOf(i)}));
        } catch (Throwable e) {
            Log.e(o.a(e), "saveSecondSpaceDateSerial exception:", e);
        }
    }

    private String r() {
        o.d(e, "enter getUsageSequenceContent...");
        JSONObject jSONObject = new JSONObject();
        try {
            List<Event> c = m.c(this.E);
            Map hashMap = new HashMap();
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            int i = 0;
            long j = 0;
            if (c != null) {
                for (Event event : c) {
                    String packageName = event.getPackageName();
                    if (hashMap.get(packageName) == null) {
                        int i2 = i + 1;
                        hashMap.put(packageName, String.valueOf(i));
                        i = i2;
                    }
                    if (j == 0) {
                        j = event.getTimeStamp() / 1000;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("p", Integer.valueOf((String) hashMap.get(packageName)));
                    jSONObject2.put(t, event.getEventType());
                    jSONObject2.put("t", (event.getTimeStamp() / 1000) - j);
                    jSONArray.put(jSONObject2);
                }
                for (Entry entry : hashMap.entrySet()) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put((String) entry.getKey(), Integer.valueOf((String) entry.getValue()));
                    jSONArray2.put(jSONObject3);
                }
                jSONObject.put("es", jSONArray);
                jSONObject.put(v, jSONArray2);
                jSONObject.put(u, j);
            }
        } catch (Throwable e) {
            Log.e(o.a(e), "getUsageSequenceContent exception: ", e);
        }
        String jSONObject4 = jSONObject.toString();
        if (a) {
            byte[] bytes = jSONObject4.getBytes();
            o.a(e, String.format("Usage Sequence size: %dKB(%dB), content:%s", new Object[]{Integer.valueOf(bytes.length / 1024), Integer.valueOf(bytes.length), jSONObject4}));
        }
        return jSONObject4;
    }

    public void b(Context context) {
        if (!G) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("SEQUENCE_TEST");
            context.registerReceiver(new BroadcastReceiver(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    o.d(k.e, "#### SEQUENCE_TEST broadcast");
                    this.a.r();
                    this.a.g();
                }
            }, intentFilter);
            G = true;
        }
        o.d(e, "register broadcast SEQUENCE_TEST");
    }
}
