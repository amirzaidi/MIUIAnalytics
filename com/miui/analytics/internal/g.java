package com.miui.analytics.internal;

import java.lang.Thread.UncaughtExceptionHandler;

public class g {
    public static UncaughtExceptionHandler a = null;

    public class a implements UncaughtExceptionHandler {
        final /* synthetic */ g a;

        public a(g gVar) {
            this.a = gVar;
        }

        public void uncaughtException(Thread thread, Throwable th) {
            if (g.a != null) {
                g.a.uncaughtException(thread, th);
            }
        }
    }

    public static void a() {
        a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(a);
    }
}
