package com.miui.analytics.internal;

import com.miui.analytics.internal.util.y;
import java.util.HashMap;

public class j {
    public static HashMap<String, Long> a = new HashMap();

    public static String a(String str) {
        String valueOf;
        synchronized (a) {
            String a = y.a(str);
            if (a.containsKey(a)) {
                Long valueOf2 = Long.valueOf(((Long) a.get(a)).longValue() + 1);
                a.put(a, valueOf2);
                valueOf = String.valueOf(valueOf2);
            } else {
                long nanoTime = System.nanoTime();
                a.put(a, Long.valueOf(nanoTime));
                valueOf = String.valueOf(nanoTime);
            }
        }
        return valueOf;
    }
}
