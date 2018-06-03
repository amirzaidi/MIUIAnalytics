package com.miui.analytics.internal.collection;

import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b;
import com.miui.analytics.internal.collection.UploadUsageHelper.a;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.policy.j;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.y;
import com.miui.analytics.internal.util.z;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l {
    private static volatile l A = null;
    private static final Object E = new Object();
    public static boolean a = false;
    private static final String b = "UUV2";
    private static final String c = "tz";
    private static final String d = "si";
    private static final String e = "ts";
    private static final String f = "U";
    private static final String g = "P";
    private static final String h = "T";
    private static final String i = "C";
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
    private static final String z = "its";
    private v B;
    private UploadUsageHelper C;
    private Context D;

    private l(Context context) {
        try {
            this.D = c.a(context);
            this.B = new v(context, u.d);
            this.C = UploadUsageHelper.a(context);
        } catch (Throwable e) {
            Log.e(o.a(b), "UploadUsageV2 constructor e", e);
        }
    }

    public static synchronized l a(Context context) {
        l lVar;
        synchronized (l.class) {
            if (A == null) {
                A = new l(context);
            }
            lVar = A;
        }
        return lVar;
    }

    private boolean d() {
        double z = g.z();
        if (z <= 0.0d) {
            return false;
        }
        if (new j(z).a()) {
            o.a(b, String.format("sample(%f) apply", new Object[]{Double.valueOf(z)}));
            return true;
        }
        o.a(b, String.format("sample(%f) NOT apply", new Object[]{Double.valueOf(z)}));
        return false;
    }

    private boolean e() {
        if (ac.c(System.currentTimeMillis()) != g.A()) {
            return false;
        }
        o.a(b, "in need cancel hour");
        return true;
    }

    public void a() {
        if (!n.e() && g.c(this.D)) {
            ab.a(new Runnable(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (l.E) {
                        try {
                            o.a(l.b, "enter upload usage v2");
                            if (VERSION.SDK_INT <= 19 || !z.z()) {
                                this.a.g();
                                b.c(this.a.D);
                            } else {
                                this.a.f();
                            }
                            this.a.h();
                        } catch (Throwable th) {
                            ae.a(this.a.D, l.b, "run usage v2 task exception", th);
                        }
                    }
                }
            });
        }
    }

    public void b() {
        if (!n.e() && g.c(this.D)) {
            ab.a(new Runnable(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        o.a(l.b, "enter saveInstallationList");
                        this.a.C.a(this.a.j().toString());
                        this.a.r();
                    } catch (Throwable e) {
                        o.b(l.b, "saveInstallationList task exception: ", e);
                    }
                }
            });
        }
    }

    private void f() {
        if (!ac.a(o()) || a) {
            o.a(b, "enter uploadRecentOneWeekAppsUsage");
            Map s = s();
            long a = ac.a();
            long b = ac.b();
            Object obj = null;
            for (int i = 0; i < y; i++) {
                if (!s.containsKey(ac.a(new Date(a)))) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(d, VERSION.SDK_INT);
                        jSONObject.put(c, ac.c());
                        jSONObject.put("ts", a);
                        jSONObject.put(f, m.a(this.D, a, b));
                        jSONObject.put(j, j());
                    } catch (Throwable e) {
                        ae.a(this.D, b, "get usage info exception", e);
                    }
                    k.a(this.D).a(new LogEvent(this.D, "com.miui.analytics", f.k, jSONObject.toString()));
                    o.a(b, String.format("uploading second space usage for %s, content:%s", new Object[]{ac.a(new Date(a)), r0}));
                    obj = 1;
                    a(a, 1);
                }
                a -= ac.b;
                b -= ac.b;
            }
            if (obj != null) {
                p();
                return;
            }
            return;
        }
        o.a(b, "uploadRecentOneWeekAppsUsage, usage uploaded today");
    }

    private void g() {
        String i = i();
        if (!TextUtils.isEmpty(i)) {
            if (VERSION.SDK_INT <= 22) {
                this.C.a("usage", i);
                o.a(b, "save daily usage info: " + i);
                ArrayList b = this.C.b("usage");
                if (b != null) {
                    Iterator it = b.iterator();
                    while (it.hasNext()) {
                        a aVar = (a) it.next();
                        if (!TextUtils.isEmpty(aVar.c)) {
                            k.a(this.D).a(new LogEvent(this.D, "com.miui.analytics", f.k, aVar.c));
                            this.C.c(aVar.b);
                            l();
                            o.a(b, "start to upload usage info: " + aVar.c);
                        }
                    }
                }
            } else if (ac.a(k())) {
                o.a(b, "android version  > 5.1, already upload usage and install today");
            } else {
                k.a(this.D).a(new LogEvent(this.D, "com.miui.analytics", f.k, i));
                l();
                o.a(b, "start to upload usage info: " + i);
            }
        }
    }

    private void h() {
        String t = t();
        if (!TextUtils.isEmpty(t)) {
            if (VERSION.SDK_INT <= 22) {
                this.C.a(UploadUsageHelper.a, t);
                o.a(b, "save daily usage sequence info: " + t);
                ArrayList b = this.C.b(UploadUsageHelper.a);
                if (b != null) {
                    Iterator it = b.iterator();
                    while (it.hasNext()) {
                        a aVar = (a) it.next();
                        if (!TextUtils.isEmpty(aVar.c)) {
                            k.a(this.D).a(new LogEvent(this.D, "com.miui.analytics", f.l, aVar.c));
                            this.C.c(aVar.b);
                            n();
                            o.a(b, "start to upload usage sequence info: " + aVar.c);
                        }
                    }
                }
            } else if (ac.a(m())) {
                o.a(b, "android version  > 5.1, already upload sequence today");
            } else {
                k.a(this.D).a(new LogEvent(this.D, "com.miui.analytics", f.l, t));
                n();
                o.a(b, "start to upload usage sequence info: " + t);
            }
        }
    }

    private String i() {
        JSONObject jSONObject = new JSONObject();
        try {
            Object j;
            jSONObject.put(d, VERSION.SDK_INT);
            jSONObject.put(c, ac.c());
            if (VERSION.SDK_INT < 23) {
                jSONObject.put("ts", ac.d());
            } else {
                jSONObject.put("ts", ac.d() - ac.b);
            }
            jSONObject.put(f, m.a(this.D));
            if (VERSION.SDK_INT < 23) {
                j = j();
                jSONObject.put(z, System.currentTimeMillis());
            } else {
                j = new JSONArray(this.C.b());
                if (j.length() > 0) {
                    jSONObject.put(z, q());
                } else {
                    j = j();
                    jSONObject.put(z, System.currentTimeMillis());
                }
            }
            jSONObject.put(j, j);
            return jSONObject.toString();
        } catch (Throwable e) {
            ae.a(this.D, b, "getContent get usage info exception", e);
            return "";
        }
    }

    private void a(Map<String, m.a> map, JSONArray jSONArray) throws JSONException {
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                m.a aVar = (m.a) entry.getValue();
                if (aVar.b != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONArray.put(jSONObject);
                    jSONObject.put("P", str);
                    jSONObject.put("T", aVar.b.b);
                    jSONObject.put("C", aVar.b.c);
                    if (VERSION.SDK_INT >= 16 && aVar.c != null && aVar.c.size() > 0) {
                        for (int i = 0; i < aVar.c.size(); i++) {
                            long keyAt = aVar.c.keyAt(i);
                            m.c cVar = (m.c) aVar.c.get(keyAt);
                            if (cVar != null) {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("T", cVar.b);
                                jSONObject2.put("C", cVar.c);
                                jSONObject.put(String.valueOf(keyAt), jSONObject2);
                            }
                        }
                    }
                }
            }
        }
    }

    private JSONArray j() {
        JSONArray jSONArray = new JSONArray();
        List<PackageInfo> installedPackages = this.D.getPackageManager().getInstalledPackages(64);
        if (installedPackages != null) {
            o.a(b, "installed packages cnt=" + installedPackages.size());
            Set a = g.a(f.k);
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
            o.a(b, "installed system apps cnt=" + i);
        }
        return jSONArray;
    }

    private JSONObject a(PackageInfo packageInfo) {
        String installerPackageName = this.D.getPackageManager().getInstallerPackageName(packageInfo.packageName);
        JSONObject jSONObject = new JSONObject();
        try {
            String a = y.a(packageInfo.packageName);
            jSONObject.put("P", a);
            jSONObject.put("v", packageInfo.versionCode);
            jSONObject.put("vn", packageInfo.versionName);
            jSONObject.put("s", y.a(installerPackageName));
            jSONObject.put(p, packageInfo.lastUpdateTime);
            jSONObject.put("fit", packageInfo.firstInstallTime);
            jSONObject.put(o, z.a(a) ? 1 : 0);
            return jSONObject;
        } catch (Throwable e) {
            Log.e(o.a(b), "Fail to format package info of " + packageInfo.packageName, e);
            return null;
        }
    }

    private long k() {
        return this.B.b(u.s, 0);
    }

    private void l() {
        this.B.a(u.s, System.currentTimeMillis());
    }

    private long m() {
        return this.B.b(u.t, 0);
    }

    private void n() {
        this.B.a(u.t, System.currentTimeMillis());
    }

    private long o() {
        long b = this.B.b(u.r, 0);
        o.a(b, "getLastUploadSecondSpaceUsageTime " + b);
        return b;
    }

    private void p() {
        this.B.a(u.r, System.currentTimeMillis());
        o.a(b, "saveLastUploadSecondSpaceUsageTime " + System.currentTimeMillis());
    }

    private long q() {
        return this.B.b(u.q, 0);
    }

    private void r() {
        this.B.a(u.q, System.currentTimeMillis());
    }

    private Map<String, Integer> s() {
        Map<String, Integer> hashMap = new HashMap();
        try {
            long a = ac.a();
            long j = a - 432000000;
            if (j < 0) {
                j = 0;
            }
            int intValue = Integer.valueOf(ac.a(new Date(j))).intValue();
            int intValue2 = Integer.valueOf(ac.a(new Date(a + ac.b))).intValue();
            o.a(b, String.format("left:%d(%d), right:%d(%d)", new Object[]{Long.valueOf(j), Integer.valueOf(intValue), Long.valueOf(a), Integer.valueOf(intValue2)}));
            JSONObject jSONObject = new JSONObject(this.B.b(u.p, x));
            Iterator keys = jSONObject.keys();
            int i = 0;
            Object obj = null;
            int i2 = 0;
            while (keys.hasNext()) {
                Object obj2;
                int i3;
                String str = (String) keys.next();
                long intValue3 = (long) Integer.valueOf(str).intValue();
                o.a(b, String.format("SecondSpace Map: key:%s, value:%d", new Object[]{str, Integer.valueOf(jSONObject.getInt(str))}));
                if (intValue3 < ((long) intValue) || intValue3 > ((long) intValue2)) {
                    keys.remove();
                    jSONObject.remove(str);
                    o.a(b, String.format("the date %s is out-of-date", new Object[]{str}));
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
                this.B.a(u.p, jSONObject.toString());
            }
            o.a(b, String.format("get total %d date serial, %d are valid", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
        } catch (Throwable e) {
            ae.a(this.D, b, "getSecondSpaceDateSerial exception: ", e);
        }
        return hashMap;
    }

    private void a(long j, int i) {
        try {
            JSONObject jSONObject = new JSONObject(this.B.b(u.p, x));
            jSONObject.put(ac.a(new Date(j)), i);
            this.B.a(u.p, jSONObject.toString());
            o.a(b, String.format("saveSecondSpaceDateSerial,key:%s,value:%d", new Object[]{r0, Integer.valueOf(i)}));
        } catch (Throwable e) {
            Log.e(o.a(b), "saveSecondSpaceDateSerial exception:", e);
        }
    }

    private String t() {
        o.d(b, "enter getUsageSequenceContent...");
        JSONObject jSONObject = new JSONObject();
        try {
            List<Event> c = m.c(this.D);
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
            Log.e(o.a(b), "getUsageSequenceContent exception: ", e);
        }
        String jSONObject4 = jSONObject.toString();
        if (a) {
            byte[] bytes = jSONObject4.getBytes();
            o.a(b, String.format("Usage Sequence size: %dKB(%dB), content:%s", new Object[]{Integer.valueOf(bytes.length / 1024), Integer.valueOf(bytes.length), jSONObject4}));
        }
        return jSONObject4;
    }
}
