package com.miui.analytics.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.R;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import java.io.File;

public class e {
    private static final String a = "Client";
    private static final String b = "analytics";
    private static final String c = "analytics_asset.apk";
    private static final String d = "analytics.apk";

    public static int a(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            if ("com.miui.analytics".equals(context.getPackageName())) {
                return c.b(context, "com.miui.analytics");
            }
            int b = b(d(context), context);
            if (b < 0) {
                b = b(e(context), context);
            }
            if (b < 0) {
                b = c.b(context, "com.miui.analytics");
            }
            if (b >= 0) {
                return b;
            }
            return 0;
        } catch (Throwable e) {
            Log.e(o.a(a), "getCoreVersionCode exception", e);
            return 0;
        }
    }

    public static String b(Context context) {
        if (context == null) {
            try {
                return "0.0.0";
            } catch (Throwable e) {
                Log.e(o.a(a), "getCoreVersionName exception", e);
            }
        } else {
            String str = "";
            if ("com.miui.analytics".equals(context.getPackageName())) {
                str = context.getString(R.string.analytics_version_name);
            } else {
                str = a(d(context), context);
                if (TextUtils.isEmpty(str)) {
                    str = a(e(context), context);
                }
                if (TextUtils.isEmpty(str)) {
                    str = c.a(context, "com.miui.analytics");
                }
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            return "0.0.0";
        }
    }

    private static String c(Context context) {
        return context.getDir("analytics", 0).getAbsolutePath();
    }

    private static String d(Context context) {
        return c(context) + "/" + d;
    }

    private static String e(Context context) {
        return c(context) + "/" + c;
    }

    private static String a(String str, Context context) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1).versionName;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "loadAnalyticsVersionName exception", e);
        }
        return null;
    }

    private static int b(String str, Context context) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1).versionCode;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "loadAnalyticsVersionCode exception", e);
        }
        return -1;
    }
}
