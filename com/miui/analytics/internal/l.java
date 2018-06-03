package com.miui.analytics.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.util.o;

public class l {
    private static final String a = "PowerKeeper";
    private static final String b = "miui.intent.action.POWER_SAVE_MODE_CHANGED";
    private static final String c = "POWER_SAVE_MODE_OPEN";
    private static final int d = 1;
    private static final int e = 3;
    private static volatile int f = 1;

    public void a(Context context) {
        if (b(context)) {
            f = 3;
        }
        o.a(a, "interval multi factor : " + f);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(b);
            context.registerReceiver(new BroadcastReceiver(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    CharSequence action = intent.getAction();
                    if (intent != null && !TextUtils.isEmpty(action) && l.b.equals(action)) {
                        if (intent.getBooleanExtra(l.c, false)) {
                            l.f = 3;
                        } else {
                            l.f = 1;
                        }
                        o.a(l.a, "interval multi factor : " + l.f);
                    }
                }
            }, intentFilter);
        } catch (Throwable e) {
            Log.e(o.a(a), "init exception:", e);
        }
    }

    private boolean b(Context context) {
        try {
            return System.getInt(context.getContentResolver(), c, 0) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static int a(int i) {
        if (f != 1) {
            o.a(a, "using interval multi factor : " + f);
        }
        return f * i;
    }
}
