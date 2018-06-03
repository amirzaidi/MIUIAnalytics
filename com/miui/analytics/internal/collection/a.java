package com.miui.analytics.internal.collection;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.c;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.service.e;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.z;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class a {
    public static final String a = "appExtra";
    public static final String b = "Downloaded";
    public static final String c = "Installed";
    public static final String d = "Replaced";
    public static final String e = "Uninstalled";
    public static final String f = "FirstLaunch";
    public static final String g = "sourcePkg";
    public static final String h = "vc";
    public static final String i = "vn";
    public static final String j = "ts";
    public static final String k = "action";
    public static final String l = "pkg";
    public static final String m = "installer";
    public static final String n = "fit";
    public static final String o = "p";
    public static final int p = 180000;
    private static final String q = "AppenderManager";
    private static a r = null;
    private static final String s = "result";
    private static final long t = 1000;
    private static final HashMap<String, String> x = new AppenderManager$1();
    private Context u;
    private c v;
    private Map<String, Long> w = new ConcurrentHashMap();

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (r == null) {
                r = new a(context);
            }
            aVar = r;
        }
        return aVar;
    }

    private a(Context context) {
        this.u = com.miui.analytics.internal.util.c.a(context);
        this.v = c.a(context);
        a();
    }

    private void a() {
        final v vVar = new v(this.u, u.d);
        if (!vVar.b(u.Z, false)) {
            ab.a(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    try {
                        this.b.v.a();
                        vVar.a(u.Z, true);
                    } catch (Throwable e) {
                        Log.e(o.a(a.q), "clearAppenderManagerDB exception", e);
                    }
                }
            });
        }
    }

    public void a(Intent intent) {
        try {
            if (g.c(this.u)) {
                Object stringExtra = intent.getStringExtra(a);
                if (!TextUtils.isEmpty(stringExtra)) {
                    JSONObject jSONObject = new JSONObject(stringExtra);
                    stringExtra = jSONObject.optString("pkg");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("pkg", stringExtra);
                        jSONObject2.put(k, b);
                        jSONObject2.put("ts", System.currentTimeMillis());
                        jSONObject2.put(g, jSONObject.optString(g));
                        jSONObject2.put("vn", jSONObject.optString("vn"));
                        jSONObject2.put("vc", jSONObject.optString("vc"));
                        k.a(this.u).a(new LogEvent(this.u, "com.miui.analytics", f.e, jSONObject2.toString()));
                        o.a(q, "record Downloaded event, pkg: " + stringExtra);
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(q), "recordAppDownloadAction exception: ", e);
        }
    }

    public void b(Intent intent) {
        try {
            if (g.c(this.u)) {
                String stringExtra = intent.getStringExtra("package");
                int intExtra = intent.getIntExtra("userId", -1);
                String stringExtra2 = intent.getStringExtra(m);
                o.a(q, String.format("fisrt launch : %s, %s, %d", new Object[]{stringExtra, stringExtra2, Integer.valueOf(intExtra)}));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pkg", stringExtra);
                jSONObject.put(k, f);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(m, stringExtra2);
                jSONObject.put(n, com.miui.analytics.internal.util.c.a(this.u, stringExtra, 16384).firstInstallTime);
                k.a(this.u).a(new LogEvent(this.u, "com.miui.analytics", f.e, jSONObject.toString()));
                o.a(q, "record first launch event, pkg: " + stringExtra);
            }
        } catch (Throwable e) {
            Log.e(o.a(q), "recordAppFirstLaunchAction exception: ", e);
        }
    }

    public void a(String str) {
        try {
            if (g.c(this.u) && !TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pkg", str);
                jSONObject.put(k, c);
                jSONObject.put("ts", System.currentTimeMillis());
                PackageInfo a = com.miui.analytics.internal.util.c.a(this.u, str, 16384);
                long j = a.firstInstallTime;
                jSONObject.put(n, a.firstInstallTime);
                if (ac.a(j, 180000)) {
                    o.a(q, "installation expired, " + str);
                    return;
                }
                k.a(this.u).a(new LogEvent(this.u, "com.miui.analytics", f.e, jSONObject.toString()));
                o.a(q, "record install event, pkg: " + str);
            }
        } catch (Throwable e) {
            Log.e(o.a(q), "recordAppInstalledAction exception: ", e);
        }
    }

    public void b(String str) {
        try {
            if (g.c(this.u) && !TextUtils.isEmpty(str)) {
                Thread.sleep(10000);
                if (com.miui.analytics.internal.util.c.e(this.u, str)) {
                    o.a(q, "package upgrading, " + str);
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pkg", str);
                jSONObject.put(k, e);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put("p", z.a(str) ? 1 : 0);
                k.a(this.u).a(new LogEvent(this.u, "com.miui.analytics", f.e, jSONObject.toString()));
                o.a(q, "record uninstall event, pkg: " + str);
            }
        } catch (Throwable e) {
            Log.e(o.a(q), "recordAppUninstalledAction exception: ", e);
        }
    }

    public void c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pkg", str);
                jSONObject.put(k, d);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(n, com.miui.analytics.internal.util.c.a(this.u, str, 16384).firstInstallTime);
                o.a(q, "record replaced event, pkg: " + str);
            }
        } catch (Throwable e) {
            Log.e(o.a(q), "recordAppUninstalledAction exception: ", e);
        }
    }

    public Bundle a(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        try {
            Long l = (Long) this.w.get(str);
            if (l == null || l.longValue() == 0) {
                this.w.put(str, Long.valueOf(0));
            }
            if (d(str)) {
                if (!ac.a(l == null ? 0 : l.longValue(), (long) t)) {
                    bundle2.putString(s, String.format("frequent, try %dms later", new Object[]{Long.valueOf(t)}));
                } else if (bundle != null) {
                    this.w.put(str, Long.valueOf(System.currentTimeMillis()));
                    JSONObject jSONObject = new JSONObject(bundle.getString(e.b));
                    CharSequence optString = jSONObject.optString("i");
                    long optLong = jSONObject.optLong(f.J, 0);
                    CharSequence optString2 = jSONObject.optString("v");
                    o.a(q, String.format("BIME app input: %s, %d, %s, %s, %s", new Object[]{optString, Long.valueOf(optLong), optString2, jSONObject.optString("n"), jSONObject.optString("k")}));
                    if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                        bundle2.putString(s, "input is empty");
                        return bundle2;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("i", optString);
                    jSONObject2.put("it", optLong);
                    jSONObject2.put("iv", optString2);
                    jSONObject2.put("in", r6);
                    jSONObject2.put("ik", r2);
                    if (n.e(this.u)) {
                        a(this.u, jSONObject2);
                    } else {
                        k.a(this.u).a(new LogEvent(this.u, "com.miui.analytics", f.v, jSONObject2.toString()));
                        o.a(q, "upload material locally");
                    }
                    bundle2.putString(s, com.miui.analytics.internal.service.a.a);
                }
            } else {
                bundle2.putString(s, "not permitted");
            }
        } catch (Throwable e) {
            Log.e(o.a(q), "recordInputMaterials exception: ", e);
            bundle2.putString(s, e.getMessage());
        }
        o.a(q, String.format("pkg:%s, result:%s", new Object[]{str, bundle2.getString(s, "")}));
        return bundle2;
    }

    private void a(Context context, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                jSONObject.put("aaid", n.b(this.u));
                jSONObject.put("iced", z.d(context));
                Intent intent = new Intent("com.xiaomi.xmsf.push.XMSF_UPLOAD_ACTIVE");
                intent.putExtra("pkgname", context.getPackageName());
                intent.putExtra("category", "analytics_app_material");
                intent.putExtra("name", "pulse");
                intent.putExtra(e.b, jSONObject.toString());
                context.sendBroadcast(intent, "com.xiaomi.xmsf.permission.USE_XMSF_UPLOAD");
                o.a(q, "upload material via keep alive: " + jSONObject.toString());
            } catch (Throwable e) {
                Log.e(o.a(q), "uploadThroughSystemKeepAliveService exception", e);
            }
        }
    }

    private boolean d(String str) {
        String str2 = (String) x.get(str);
        o.a(q, String.format("app: %s, sign:%s", new Object[]{str, str2}));
        if (com.xiaomi.a.a.a.a.a(this.u, str, str2)) {
            o.a(q, "signature matched");
            return true;
        } else if (!com.miui.analytics.internal.util.c.d(this.u, str) && !com.xiaomi.a.a.a.a.a(this.u, str)) {
            return false;
        } else {
            o.a(q, "sys app or platform sign");
            return true;
        }
    }
}
