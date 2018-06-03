package com.miui.analytics.internal.policy;

import android.text.TextUtils;
import com.miui.analytics.internal.policy.a.d;
import com.miui.analytics.internal.policy.a.k;
import java.util.concurrent.ConcurrentHashMap;

public class b {
    public static final String a = "privacy_policy";
    public static final String b = "privacy_no";
    public static final String c = "privacy_user";
    private static ConcurrentHashMap<String, String> d = new ConcurrentHashMap();

    public static void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            d.put(str, str2);
        }
    }

    public static i a() {
        if (d.containsKey("privacy_policy")) {
            if (b.equals((String) d.get("privacy_policy"))) {
                return new d();
            }
        }
        return new k();
    }
}
