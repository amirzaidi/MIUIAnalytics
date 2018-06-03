package com.miui.analytics.internal.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.b.k;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;

public class af {
    private static final String a = "Utils";
    private static final double b = 1.0E-7d;

    public static String a(byte[] bArr) {
        String str = "";
        if (bArr != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bArr);
                BigInteger bigInteger = new BigInteger(1, instance.digest());
                str = String.format("%1$032X", new Object[]{bigInteger});
            } catch (Exception e) {
            }
        }
        return str.toLowerCase();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a(str.getBytes());
    }

    public static String b(String str) {
        return a(str).substring(0, 16);
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void a(final Context context) {
        try {
            ab.a(new Runnable() {
                public void run() {
                    if (k.a(context).a() > ((long) 10000)) {
                        Log.w(o.a(af.a), String.format("[ANALYTICS-DB-TRUNCATE] items size(%d) of analytics table reach max count(%d), delete them", new Object[]{Long.valueOf(r2), Integer.valueOf(10000)}));
                        k.a(context).i();
                    }
                    if (k.a(context).c() > ((long) 10000)) {
                        Log.w(o.a(af.a), String.format("[ANALYTICS-DBV2-TRUNCATE] items size(%d) of analytics table reach max count(%d), delete them", new Object[]{Long.valueOf(r2), Integer.valueOf(10000)}));
                        k.a(context).k();
                    }
                }
            });
        } catch (Throwable th) {
            Log.e(o.a(a), "fix DB bug exception: ", th);
        }
    }

    public static boolean a(double d) {
        if (Math.abs(d) < b) {
            return true;
        }
        return false;
    }

    public static boolean a() {
        if (!n.a()) {
            return g.f();
        }
        o.a(a, "Not allow to upload mds in international build.");
        return false;
    }
}
