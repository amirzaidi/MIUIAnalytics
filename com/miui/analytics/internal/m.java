package com.miui.analytics.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.a.b;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.k;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.z;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import org.json.JSONObject;

public class m {
    private static final String c = "UpdateManager";
    private static final String d = "https://sdkconfig.ad.xiaomi.com/api/checkupdate/lastusefulversion2?";
    private static final String e = "https://sdkconfig.ad.intl.xiaomi.com/api/checkupdate/lastusefulversion2?";
    private static m f = null;
    private static final String i = "modules";
    private static final String j = "av";
    private static final String k = "p";
    private static final String l = "ts";
    private static final String m = "nonce";
    private static final String n = "miui_sdkconfig_jafej!@#)(*e@!#";
    public Runnable a = new Runnable(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public void run() {
            Object optString;
            long currentTimeMillis = System.currentTimeMillis();
            int i = 0;
            while (true) {
                JSONObject jSONObject;
                Object optString2;
                int i2 = i + 1;
                if (i < 2) {
                    try {
                        String b = e.b(this.a.g);
                        String m = z.m();
                        String d = z.d();
                        String d2 = z.d(this.a.g);
                        String a = z.a(this.a.g, "com.miui.analytics");
                        String k = z.k();
                        int d3 = r.d(this.a.g);
                        String b2 = this.a.f();
                        String packageName = this.a.g.getPackageName();
                        String i3 = z.i();
                        String c = z.c();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("av0.0.0");
                        stringBuilder.append("cv" + b);
                        stringBuilder.append(f.U + m);
                        stringBuilder.append(f.X + d);
                        if (n.a()) {
                            stringBuilder.append("i" + a);
                        } else {
                            stringBuilder.append("i" + d2);
                        }
                        stringBuilder.append(f.T + k);
                        stringBuilder.append("n" + d3);
                        stringBuilder.append("nonce" + b2);
                        stringBuilder.append("p" + packageName);
                        stringBuilder.append(f.S + i3);
                        stringBuilder.append("ts" + currentTimeMillis);
                        stringBuilder.append("v" + c);
                        stringBuilder.append(m.n);
                        String toLowerCase = af.a(stringBuilder.toString()).toLowerCase(Locale.getDefault());
                        StringBuilder stringBuilder2 = new StringBuilder(n.a() ? m.e : m.d);
                        stringBuilder2.append("av=0.0.0");
                        stringBuilder2.append("&cv=" + b);
                        stringBuilder2.append("&d=" + m);
                        stringBuilder2.append("&f=" + d);
                        if (n.a()) {
                            stringBuilder2.append("&i=" + a);
                        } else {
                            stringBuilder2.append("&i=" + d2);
                        }
                        stringBuilder2.append("&m=" + k);
                        stringBuilder2.append("&n=" + d3);
                        stringBuilder2.append("&nonce=" + b2);
                        stringBuilder2.append("&p=" + packageName);
                        stringBuilder2.append("&r=" + i3);
                        stringBuilder2.append("&ts=" + currentTimeMillis);
                        stringBuilder2.append("&v=" + c);
                        stringBuilder2.append("&sign=" + toLowerCase);
                        o.a(m.c, "url:" + stringBuilder2.toString());
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(stringBuilder2.toString()).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(f.O);
                        httpURLConnection.connect();
                        b = new String(com.miui.analytics.internal.util.m.a(httpURLConnection.getInputStream()));
                        o.a(m.c, "result " + b);
                        jSONObject = new JSONObject(b);
                        optString2 = jSONObject.optString(b.b);
                        int optInt = jSONObject.optInt("code", 0);
                        optString = jSONObject.optString("v");
                        this.a.r = jSONObject.optInt("force", 0);
                        if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString)) {
                            break;
                        } else if (optInt == -8) {
                            currentTimeMillis = this.a.a(jSONObject.optString("failMsg"));
                            i = i2;
                        } else {
                            return;
                        }
                    } catch (Throwable e) {
                        this.a.a(0);
                        Log.e(o.a(m.c), "mUpdater task exception ", e);
                        i = i2;
                    }
                } else {
                    return;
                }
            }
            n nVar = new n(optString);
            if (n.b() || nVar.c == 0) {
                this.a.p = jSONObject.optString("md5");
                this.a.o = optString2;
                ab.a(this.a.b);
            }
        }
    };
    public Runnable b = new Runnable(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public void run() {
            Throwable th;
            Throwable e;
            Closeable closeable = null;
            try {
                Closeable fileOutputStream;
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a.o).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(f.O);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    byte[] bArr;
                    File file;
                    File file2;
                    byte[] a = com.miui.analytics.internal.util.m.a(httpURLConnection.getInputStream());
                    if (!TextUtils.isEmpty(this.a.p)) {
                        if (!this.a.p.equalsIgnoreCase(af.a(a))) {
                            bArr = null;
                            if (bArr != null) {
                                Log.d(o.a(m.c), "download apk success.");
                                file = new File(this.a.q + ".tmp");
                                fileOutputStream = new FileOutputStream(file);
                                try {
                                    fileOutputStream.write(bArr);
                                    if (com.xiaomi.a.a.a.a.a(c.a(this.a.g, file))) {
                                        Log.e(o.a(m.c), "verify signature failed");
                                        ae.a(this.a.g, "update app signature not matched", "downloadUrl: " + this.a.o);
                                    } else {
                                        Log.d(o.a(m.c), "verify signature success");
                                        file2 = new File(this.a.q);
                                        file.renameTo(file2);
                                        k.d(file2);
                                        this.a.g();
                                    }
                                    com.miui.analytics.internal.util.m.a(fileOutputStream);
                                } catch (Throwable e2) {
                                    th = e2;
                                    closeable = fileOutputStream;
                                    e = th;
                                    try {
                                        Log.e(o.a(m.c), "mDownloader e", e);
                                        com.miui.analytics.internal.util.m.a(closeable);
                                    } catch (Throwable th2) {
                                        e = th2;
                                        com.miui.analytics.internal.util.m.a(closeable);
                                        throw e;
                                    }
                                } catch (Throwable e22) {
                                    th = e22;
                                    closeable = fileOutputStream;
                                    e = th;
                                    com.miui.analytics.internal.util.m.a(closeable);
                                    throw e;
                                }
                            }
                        }
                    }
                    bArr = a;
                    if (bArr != null) {
                        Log.d(o.a(m.c), "download apk success.");
                        file = new File(this.a.q + ".tmp");
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(bArr);
                        if (com.xiaomi.a.a.a.a.a(c.a(this.a.g, file))) {
                            Log.e(o.a(m.c), "verify signature failed");
                            ae.a(this.a.g, "update app signature not matched", "downloadUrl: " + this.a.o);
                        } else {
                            Log.d(o.a(m.c), "verify signature success");
                            file2 = new File(this.a.q);
                            file.renameTo(file2);
                            k.d(file2);
                            this.a.g();
                        }
                        com.miui.analytics.internal.util.m.a(fileOutputStream);
                    }
                }
                fileOutputStream = null;
                com.miui.analytics.internal.util.m.a(fileOutputStream);
            } catch (Exception e3) {
                e = e3;
                Log.e(o.a(m.c), "mDownloader e", e);
                com.miui.analytics.internal.util.m.a(closeable);
            }
        }
    };
    private Context g;
    private v h;
    private String o = "";
    private String p = "";
    private String q;
    private int r = 0;
    private a s = null;

    interface a {
        void a(String str, boolean z);
    }

    public static m a(Context context) {
        synchronized (m.class) {
            if (f == null) {
                f = new m(context);
            }
        }
        return f;
    }

    public m(Context context) {
        this.g = c.a(context);
        this.h = new v(this.g, u.d, u.f);
    }

    public void a(a aVar) {
        this.s = aVar;
    }

    private String c() {
        try {
            String str = new File(d.a(this.g, i)).getAbsolutePath() + "/m.apk";
            o.a(c, "apk path:" + str);
            return str;
        } catch (Throwable e) {
            Log.e(o.a(c), "getApkPath exception:", e);
            return "";
        }
    }

    public void a() {
        try {
            File file = new File(d.a(this.g, i));
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    if (file2.exists() && file2.isFile()) {
                        String absolutePath = file2.getAbsolutePath();
                        boolean delete = file2.delete();
                        String a = o.a(c);
                        String str = "clean modules %s, %s ";
                        Object[] objArr = new Object[2];
                        objArr[0] = delete ? com.miui.analytics.internal.service.a.a : "failed";
                        objArr[1] = absolutePath;
                        Log.d(a, String.format(str, objArr));
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(c), "cleanCacheFiles exception:", e);
        }
    }

    public void b() {
        if (!n.a(this.g, c) && d()) {
            Log.d(o.a(c), "checkUpdate ");
            this.q = c();
            ab.a(this.a);
            a(System.currentTimeMillis());
        }
    }

    private boolean d() {
        if (n.e()) {
            return false;
        }
        long e = e();
        long nextLong = ac.b + (new Random().nextLong() % (ac.b / 4));
        o.a(c, String.format("last update check time is %s, check interval: %d", new Object[]{new Date(e).toString(), Long.valueOf(nextLong / 3600000)}));
        return ac.a(e, nextLong);
    }

    private synchronized long e() {
        return this.h.b(u.S, 0);
    }

    private synchronized void a(long j) {
        this.h.a(u.S, j);
    }

    private String f() {
        Random random = new Random(System.nanoTime());
        try {
            return af.a(this.g.getPackageName() + ":" + random.nextLong());
        } catch (Exception e) {
            return af.a(random.nextLong() + "");
        }
    }

    private long a(String str) {
        try {
            return Long.parseLong(str.split("-")[1]);
        } catch (Exception e) {
            return System.currentTimeMillis();
        }
    }

    private void g() {
        try {
            if (this.s != null) {
                this.s.a(this.q, this.r == 1);
            }
            if (this.r == 1) {
                new c().a(this.g, this.q);
            }
        } catch (Exception e) {
        }
    }
}
