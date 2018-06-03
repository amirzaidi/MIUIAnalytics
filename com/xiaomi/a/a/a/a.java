package com.xiaomi.a.a.a;

import android.content.Context;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;

public class a {
    public static final String a = "701478a1e3b4b7e3978ea69469410f13";
    public static final String b = "8ddb342f2da5408402d7568af21e29f9";
    public static final String c = "443477abb7aebcc8ca41230297fd5c9c";
    public static final String d = "8ddb342f2da5408402d7568af21e29f9";
    public static final String e = "c2b0b497d0389e6de1505e7fd8f4d539";
    private static final String f = "CertUtils";

    public static boolean a(Context context, String str, String str2) {
        return a(c.f(context, str), str2);
    }

    private static boolean a(Signature[] signatureArr, String str) {
        o.d(f, "sigs: " + (signatureArr == null ? 0 : signatureArr.length));
        o.d(f, "sigMd5String: " + str);
        if (signatureArr == null || signatureArr.length <= 0 || signatureArr[0] == null || TextUtils.isEmpty(str)) {
            return false;
        }
        o.d(f, "sigs md5: " + af.a(signatureArr[0].toByteArray()));
        return str.equalsIgnoreCase(af.a(signatureArr[0].toByteArray()));
    }

    public static boolean a(Context context, String str) {
        return a(c.f(context, str));
    }

    public static boolean a(Signature[] signatureArr) {
        return a(signatureArr, a);
    }
}
