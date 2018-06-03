package com.miui.analytics.internal.collection;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.z;
import com.xiaomi.a.a.a.a;
import com.xiaomi.security.devicecredential.SecurityDeviceCredentialManager;
import com.xiaomi.security.devicecredential.SecurityDeviceCredentialManager.OperationFailedException;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

public class b {
    public static final String a = "device_token_json";
    public static final String b = "is_time_out";
    public static final String c = "token";
    public static final String d = "sid";
    public static final String e = "i";
    public static final String f = "im";
    public static final String g = "s";
    public static final String h = "g";
    public static final String i = "n";
    public static final String j = "sn";
    private static final String k = "DDM";
    private static final String l = "/system/framework/security-device-credential-sdk.jar";
    private static final String m = "_{dev!@dett%^4MI";
    private static final int n = 3;
    private static b p;
    private static long s = 0;
    private static int t = 0;
    private Context o;
    private v q;
    private HashMap<String, Long> r = new HashMap();

    private b(Context context) {
        this.o = context;
        this.q = new v(this.o, u.d, u.b);
        t = this.q.b(ac.a(new Date()), 0);
        s = System.currentTimeMillis();
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (p == null) {
                p = new b(context);
            }
            bVar = p;
        }
        return bVar;
    }

    private static boolean b(Context context) {
        boolean z = false;
        if (a()) {
            try {
                z = SecurityDeviceCredentialManager.isThisDeviceSupported();
            } catch (OperationFailedException e) {
                a(context, "isSupportDevice errorCode=" + e.errorCode, null);
            } catch (Throwable e2) {
                Log.e(o.a(k), "isSupportDevice exception errorCode ", e2);
            }
        }
        return z;
    }

    private static String c(Context context) {
        String str = "";
        try {
            str = SecurityDeviceCredentialManager.getSecurityDeviceId();
        } catch (OperationFailedException e) {
            a(context, "getSecurityDeviceId errorCode=" + e.errorCode, null);
        } catch (Throwable e2) {
            Log.e(o.a(k), "getSecurityDeviceId exception errorCode ", e2);
        }
        return str;
    }

    private static byte[] a(Context context, byte[] bArr, boolean z) {
        byte[] signWithDeviceCredential;
        Exception signWithDeviceCredential2 = null;
        try {
            signWithDeviceCredential2 = SecurityDeviceCredentialManager.signWithDeviceCredential(bArr, z);
        } catch (OperationFailedException e) {
            a(context, "signWithDeviceCredential errorCode=" + e.errorCode, signWithDeviceCredential2);
        } catch (Throwable e2) {
            Log.e(o.a(k), "signWithDeviceCredential exception errorCode ", e2);
        }
        return signWithDeviceCredential2;
    }

    public Bundle a(final String str) {
        Object futureTask = new FutureTask(new Callable<Bundle>(this) {
            final /* synthetic */ b b;

            public /* synthetic */ Object call() throws Exception {
                return a();
            }

            public Bundle a() throws Exception {
                return this.b.b(str);
            }
        });
        new Thread(futureTask).start();
        try {
            return (Bundle) futureTask.get(5000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(b, true);
            return bundle;
        } catch (Throwable e2) {
            Log.e(o.a(k), "getDeviceValidationToken e", e2);
            return null;
        }
    }

    private Bundle b(String str) {
        Bundle bundle = new Bundle();
        try {
            if (!c(str)) {
                return bundle;
            }
            String a = z.a(this.o);
            String d = z.d(this.o);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("i", a);
            jSONObject.put(f, d);
            String valueOf = String.valueOf(System.currentTimeMillis());
            jSONObject.put("n", valueOf);
            String str2 = "";
            if (b(this.o)) {
                jSONObject.put(g, 1);
                String c = c(this.o);
                jSONObject.put("sid", c);
                jSONObject.put("g", 1);
                jSONObject.put("sn", a(a, d, valueOf, 1, 1, c));
            } else {
                jSONObject.put(g, 0);
                o.a(k, "not support device " + z.k());
            }
            String b = com.miui.analytics.internal.util.b.b(jSONObject.toString(), m);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(c, b);
            a = jSONObject2.toString();
            o.a(k, "token: " + b);
            bundle.putString(a, a);
            return bundle;
        } catch (Throwable e) {
            Log.e(k, "getDeviceValidationToken exception: ", e);
            bundle.putString(a, "");
        }
    }

    private String a(String str, String str2, String str3, int i, int i2, String str4) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        stringBuilder.append(str4);
        stringBuilder.append(str3);
        stringBuilder.append(i);
        stringBuilder.append(i2);
        byte[] a = a(this.o, stringBuilder.toString().getBytes(), true);
        return a == null ? "" : Base64.encodeToString(a, 10);
    }

    public static boolean a() {
        boolean z = false;
        try {
            z = new File(l).exists();
        } catch (Throwable e) {
            Log.e(k, "securityDeviceCredentialSdkExists exception: ", e);
        }
        Log.d(o.a(k), "secure sdk exists " + z);
        return z;
    }

    private boolean c(String str) {
        boolean z = true;
        o.a(k, "calling package: " + str);
        if (TextUtils.isEmpty(str) || !(c.d(this.o, str) || a.a(this.o, str) || d(str))) {
            z = false;
        }
        o.a(k, "verify result: " + z);
        return z;
    }

    private static void a(Context context, String str, Exception exception) {
        if (ac.a(s, (long) ac.b)) {
            s = System.currentTimeMillis();
            t = 0;
        }
        if (t >= 3) {
            o.a(k, "exceeded max daily report times 3");
            return;
        }
        t++;
        ae.a(context, k, str, exception);
        String a = ac.a(new Date());
        v vVar = new v(context, u.b);
        Map b = vVar.b();
        Editor a2 = vVar.a();
        if (b != null && b.size() > 1) {
            for (Entry entry : b.entrySet()) {
                o.a(k, "entry " + ((String) entry.getKey()));
                if (!a.equals(entry.getKey())) {
                    a2.remove((String) entry.getKey());
                }
            }
        }
        a2.putInt(a, t);
        a2.apply();
    }

    private boolean d(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.r.containsKey(str)) {
                long longValue = ((Long) this.r.get(str)).longValue();
                long E = g.E();
                if (ac.a(longValue, E)) {
                    this.r.put(str, Long.valueOf(currentTimeMillis));
                    return true;
                }
                o.c(k, "The interval is too short. lastTime: " + longValue + " interval: " + E);
                return false;
            }
            this.r.put(str, Long.valueOf(currentTimeMillis));
            return true;
        } catch (Throwable e) {
            Log.e(k, "checkAppGetInterval exception: ", e);
            return true;
        }
    }
}
