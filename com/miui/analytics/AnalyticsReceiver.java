package com.miui.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.c.b.b;
import com.miui.analytics.internal.c.c;
import com.miui.analytics.internal.collection.d;
import com.miui.analytics.internal.collection.h;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.e;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.p;

public class AnalyticsReceiver extends BroadcastReceiver {
    private static final String PROVISION_COMPLETE_BROADCAST = "android.provision.action.PROVISION_COMPLETE";
    private static final String TAG = "AnalyticsReceiver";

    public void onReceive(final Context context, final Intent intent) {
        try {
            ab.a(new Runnable() {
                public void run() {
                    int i = 1;
                    try {
                        if (intent != null) {
                            String action = intent.getAction();
                            if (!TextUtils.isEmpty(action)) {
                                o.a(AnalyticsReceiver.TAG, "action:" + action);
                                if (action.equals(n.a)) {
                                    Bundle extras = intent.getExtras();
                                    if (extras != null && extras.containsKey(n.c)) {
                                        String string = extras.getString(n.c);
                                        if (!"com.miui.analytics".equals(extras.getString(n.d, ""))) {
                                            i = 0;
                                        }
                                        n.a(string, i);
                                    }
                                    d.a(context).a();
                                } else if (action.equals(AnalyticsReceiver.PROVISION_COMPLETE_BROADCAST)) {
                                    Log.d(o.a(AnalyticsReceiver.TAG), "receive broadcast provisioned");
                                    b.a(context).a(c.o);
                                    h.a(context).b();
                                } else if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                                    e.a(context).a(true);
                                } else if (action.equals("android.intent.action.ACTION_SHUTDOWN")) {
                                    e.a(context).a(false);
                                }
                                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                                    boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                                    boolean booleanExtra2 = intent.getBooleanExtra("isFailover", false);
                                    o.a(AnalyticsReceiver.TAG, String.format("network change, disconnected:%s, failover:%s", new Object[]{Boolean.valueOf(booleanExtra), Boolean.valueOf(booleanExtra2)}));
                                    if (!booleanExtra && !booleanExtra2) {
                                        p.a(context).j();
                                    }
                                }
                            }
                        }
                    } catch (Throwable e) {
                        Log.e(o.a(AnalyticsReceiver.TAG), "onReceive e", e);
                    }
                }
            });
        } catch (Throwable e) {
            Log.e(o.a(TAG), "onReceive e", e);
        }
    }
}
