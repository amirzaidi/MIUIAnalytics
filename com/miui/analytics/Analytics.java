package com.miui.analytics;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.a.f;
import com.miui.analytics.internal.c.a.b;
import com.miui.analytics.internal.d.a;
import com.miui.analytics.internal.h;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.n;
import com.miui.analytics.internal.policy.a.e;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.z;

public class Analytics {
    private static final String TAG = "Analytics";
    private static Context sContext;
    private static boolean sInitialized = false;
    private static int sVersion = 0;
    private static String sVersionName = "0.0.0";

    public static void initialize(Context context, int i, String str) {
        try {
            if (!sInitialized) {
                sVersion = i;
                sVersionName = str;
                sContext = c.a(context);
                if (sContext == null) {
                    Log.e(o.a(TAG), "context is null when Analytics.initialize()!");
                }
                new e(context).a();
                new f(context).a();
                new b(context).a();
                if (context.getPackageName().equals("com.miui.analytics") || new n(c.a(context, "com.miui.analytics")).b(new n("1.5.0")) < 0) {
                    ab.a(new com.miui.analytics.internal.d.b(context));
                }
                h.a(sContext).a();
                af.a(sContext);
                z.q(sContext);
                sInitialized = true;
            }
        } catch (Throwable th) {
            Log.e(o.a(TAG), "initialize exception", th);
        } finally {
            sInitialized = true;
        }
    }

    public static void setDebugOn(boolean z) {
        o.a = z;
    }

    public static String getClientExtra(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str2)) {
                com.miui.analytics.internal.policy.f a = com.miui.analytics.internal.policy.h.a(sContext).a(str, str2);
                if (a != null) {
                    return a.h();
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(TAG), "getClientExtra exception: ", e);
        }
        return "";
    }

    public static boolean isPolicyReady(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                if (com.miui.analytics.internal.policy.h.a(sContext).c(str) != null) {
                    return true;
                }
                return false;
            } else if (com.miui.analytics.internal.policy.h.a(sContext).a(str, str2) == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void trackEvent(String str) {
        if (str != null && sContext != null) {
            try {
                o.a(TAG, "trackEvent");
                k.a(sContext).a(str);
                a.a(sContext).a();
            } catch (Exception e) {
            }
        }
    }

    public static void trackEvents(String[] strArr) {
        if (strArr != null && sContext != null) {
            try {
                o.a(TAG, "trackEvents size " + strArr.length);
                k.a(sContext).a(strArr);
                a.a(sContext).a();
            } catch (Exception e) {
            }
        }
    }

    public static void setDefaultPolicy(String str, String str2) {
        try {
            com.miui.analytics.internal.policy.b.a(str, str2);
        } catch (Exception e) {
        }
    }

    public static String getVersionName() {
        return sVersionName;
    }

    public static void deleteAllEvents(String str) {
        o.a(TAG, "deleteAllEvents");
        com.miui.analytics.internal.b.k.a(sContext).a(str);
    }
}
