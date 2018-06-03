package com.miui.analytics.internal.util;

import android.util.Log;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ab {
    private static final String a = "TaskRunner";
    private static ThreadPoolExecutor b = new ThreadPoolExecutor(c, c, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private static int c = Runtime.getRuntime().availableProcessors();

    static {
        b.allowCoreThreadTimeOut(true);
    }

    public static void a(Runnable runnable) {
        try {
            b.execute(runnable);
        } catch (Throwable e) {
            Log.e(o.a(a), "execute e", e);
        }
    }
}
